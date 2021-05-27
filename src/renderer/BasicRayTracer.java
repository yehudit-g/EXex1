package renderer;

import elements.LightSource;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * The class is responsible about the scanning rays
 */
public class BasicRayTracer extends RayTracerBase {
    /**
     * Minimal shift of the beginning of the ray to avoid errors due to inaccuracy in small decimal numbers
     */
    private static final double DELTA = 0.1;

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
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
        List<GeoPoint> geoList = _scene.geometries.findGeoIntersections(ray);
        if (geoList == null) //no intersections
            return Color.BLACK; //_scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(geoList);
        return calcColor(closestPoint, ray);
    }


    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint geopoint, Ray ray, int level, double k) {
        Color color = geopoint.geometry.getEmission();
        color = color.add(calcLocalEffects(geopoint, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(geopoint, ray, level, k));
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, double k) {
        Vector v = ray.getDir();
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.Kr;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp, v), level, material.Kr, kkr);
        double kkt = k * material.Kt;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp, v), level, material.Kt, kkt));
        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        List<GeoPoint> gpList = _scene.geometries.findGeoIntersections(ray);
        GeoPoint gp = ray.findClosestGeoPoint(gpList);
        return (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx)
                .scale(kx));
    }

    private Ray constructRefractedRay(GeoPoint gp, Vector v) {
        return new Ray(gp.point, v);
    }

    private Ray constructReflectedRay(GeoPoint gp, Vector v) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(gp.point, r);
    }

    /**
     * @param gp - point in the
     * @return the color of p
     */
//    public Color calcColor(GeoPoint gp, Ray ray) {
//        return _scene.ambientLight.getIntensity()
//                .add(gp.geometry.getEmission())
//                // add calculated light contribution from all light sources)
//                .add(calcLocalEffects(gp, ray));
//    }


    /**
     * @param gp  - the asked GeoPoint
     * @param ray - from the camera, that crosses at gp
     * @return the color created by the light sources in specific point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) { //the geometry is invisible in gp
            return Color.BLACK;
        }
        Material material = gp.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.Kd;
        double ks = material.Ks;
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                if (unshaded(lightSource, l, n, gp)) {
                    Color lightIntensity = lightSource.getIntensity(gp.point);
//                color = color.add(lightIntensity.scale(calcDiffusive(kd, l, n)),
//                        lightIntensity.scale(calcSpecular(ks, l, n, v, nShininess)));
                    double das = calcDiffusive(kd, l, n) + calcSpecular(ks, l, n, v, nShininess);
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
     * @param ks         of the geometry's material
     * @param l          - the light direction
     * @param n          - geometry's normal
     * @param v          - the ray direction
     * @param nShininess of the geometry's material
     * @return the speculation level
     */
    private double calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess) {
        double ln = l.dotProduct(n);
        Vector r = l.add(n.scale(ln * -2));
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
    private boolean unshaded(LightSource ls, Vector l, Vector n, GeoPoint gp) {
        Ray lightRay = new Ray(gp.point, ls, n, DELTA);

        List<GeoPoint> intersections = _scene.geometries
                .findGeoIntersections(lightRay, ls.getDistance(gp.point));
        if (intersections != null) {
            for (GeoPoint p : intersections) {
                if (p.geometry.getMaterial().Kt == 0)
                    return false;
            }
        }
        return true;
        //   return intersections==null;
    }


}
