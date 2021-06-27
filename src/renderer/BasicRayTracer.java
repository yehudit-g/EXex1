package renderer;

import elements.LightSource;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import geometries.BoundingBox;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * The class is responsible about the scanning rays
 */
public class BasicRayTracer extends RayTracerBase {
    //The Max recursion level for color calculation
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    //The minimum power of the effect(according to K), to continue the recursion
    private static final double MIN_CALC_COLOR_K = 0.001;
    //The attenuation coefficient. increases in each recursion call.
    private static final double INITIAL_K = 1.0;

    /**
     * c-tor
     *
     * @param scene
     */
    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersectionPoint(ray);
        if (closestPoint == null) //no intersections
            return _scene.background; //Color.BLACK;
        return calcColor(closestPoint, ray);
    }

    //for beam of rays
    @Override
    public Color traceRay(List<Ray> rays) {
        Color average = new Color(0,0,0);
        int counter = 0;
        for (Ray ray : rays) {
                average=average.add(traceRay(ray));
            counter++;
        }
        return average.reduce(counter);
    }

    /**
     * @param ray
     * @return the closest GeoPoint that intersects the ray
     */
    private GeoPoint findClosestIntersectionPoint(Ray ray) {
        List<GeoPoint> geoList = _scene.geometries.findGeoIntersections(ray);
//        if(_scene.usingBVH)
//            return ray.findClosestGeoPoint(BVHrec(ray,geoList));
        return ray.findClosestGeoPoint(geoList);
    }

//    /**
//     * scan the BVH tree in recursion to find intersections
//     * @param ray
//     * @return the GeoPoint's list that intersects the ray
//     */
//    private List<GeoPoint> BVHrec(Ray ray, List<GeoPoint> geoList){
//        Geometry element;
//        if(geoList==null)
//            return null;
//        else{
//            for (int i = 0; i < geoList.size(); i++) {
//                element = geoList.get(i).geometry;
//                if(element.IsIntersectionInBox(ray))
//                    geoList.addAll(((element).findGeoIntersections(ray)));
//            }
//        }
//
//    }

    /**
     * Wrapping function for color-calculation
     *
     * @param geopoint
     * @param ray
     * @return the color of geopoint, including environmental effects
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    /**
     * @param geopoint
     * @param ray
     * @param level
     * @param k
     * @return the color of geopoint, including environmental effects
     */
    private Color calcColor(GeoPoint geopoint, Ray ray, int level, double k) {
        Color color = geopoint.geometry.getEmission();
        color = color.add(calcLocalEffects(geopoint, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(geopoint, ray.getDir(), level, k));
    }

    /**
     * @param gp  - the asked GeoPoint
     * @param ray - from the camera, that crosses at gp
     * @return the color created by the light sources in specific point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, double k) {
        Vector v = ray.getDir();
        Point3D point = gp.point;
        Vector n = gp.geometry.getNormal(point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) { //the geometry is invisible in gp
            return Color.BLACK;
        }
        Material material = gp.geometry.getMaterial();
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                double ktr = transparency(lightSource, l, n, gp);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(point).scale(ktr);
                    double das = calcDiffusive(material.Kd, l, n) + calcSpecular(material.Ks, l, n, v, material.nShininess);
                    if (das != 0) {
                        Color diffuseAndSpecular = lightIntensity.scale(das);
                        color = color.add(diffuseAndSpecular);
                    }
                }
            }
        }
        return color;
    }

    /**
     * calls the recursive function of global effects calculation
     *
     * @param gp
     * @param v     -the ray direction
     * @param level -the recurse level
     * @param k     -the attenuation coefficient
     * @return the color of reflection and refraction effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.Kr;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.Kr, kkr);
        double kkt = k * material.Kt;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.Kt, kkt));
        return color;
    }

    /**
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return the color of reflection and refraction effects
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersectionPoint(ray);
        Color color = (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx));
        return color.scale(kx);
    }

    /**
     * @param point
     * @param v     -the original ray direction
     * @param n     -the geometry(which intersects the ray), for the DELTA moving
     * @return a refracted ray
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    /**
     * @param point
     * @param v     -the original ray direction
     * @param n     -the geometry(which intersects the ray), for the DELTA moving
     * @return a reflected ray
     */
    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(v.dotProduct(n) * 2d));
        return new Ray(point, r, n);
    }

    /**
     * @param ks         of the geometry's material
     * @param l          - the light direction
     * @param n          - geometry's normal
     * @param v          - the ray direction
     * @param nShininess of the geometry's material
     * @return the speculation level
     */
    private double calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess) {
        double ln = l.dotProduct(n);
        Vector r = l.subtract(n.scale(ln * 2));
        double minusVR = v.scale(-1).dotProduct(r);
        return ks * Math.max(0, Math.pow(minusVR, nShininess));
    }

    /**
     * @param kd of the material
     * @param l  - the light direction
     * @param n  - geometry's normal
     * @return the diffuse level
     */
    private double calcDiffusive(double kd, Vector l, Vector n) {
        double ln = Math.abs(l.dotProduct(n));
        return kd * ln;
    }

    /**
     * @param ls -light source
     * @param l  -The light direction
     * @param n  -geometry's normal
     * @param gp -the point being tested
     * @return true if gp is unshaded, and false if it is shaded
     */
    @Deprecated
    private boolean unshaded(LightSource ls, Vector l, Vector n, GeoPoint gp) {
        Ray lightRay = new Ray(gp.point, l, n);

        List<GeoPoint> intersections = _scene.geometries
                .findGeoIntersections(lightRay, ls.getDistance(gp.point));
        if (intersections != null) {
            for (GeoPoint p : intersections) {
                if (p.geometry.getMaterial().Kt == 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * @param ls -light source
     * @param l  -light direction
     * @param n- geometry's normal
     * @param gp -point on the geometry
     * @return shading coefficient
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1);
        Point3D point = gp.point;
        Ray lightRay = new Ray(point, lightDirection, n);
        double lightDistance = ls.getDistance(point);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay, lightDistance);
        if (intersections == null) {
            return 1.0;
        }
        double ktr = 1.0;

        for (GeoPoint geoPoint : intersections) {
            ktr *= geoPoint.geometry.getMaterial().Kt;
            if (ktr < MIN_CALC_COLOR_K) {
                return 0.0;
            }
        }
        return ktr;
    }
}
