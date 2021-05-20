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
            return _scene.background;
        GeoPoint closestPoint=ray.findClosestGeoPoint(geoList);
        return calcColor(closestPoint, ray);
    }

    /**
     * @param gp - point in the
     * @return the color of p
     */
    public Color calcColor(GeoPoint gp, Ray ray) {
        return _scene.ambientLight.getIntensity()
                .add(gp.geometry.getEmission())
                // add calculated light contribution from all light sources)
                .add(calcLocalEffects(gp, ray));
    }

    /**
     * @param gp - the asked GeoPoint
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
        return color;
    }

    /**
     * @param ks of the geometry's material
     * @param l - the light direction
     * @param n - geometry's normal
     * @param v - the ray direction
     * @param nShininess of the geometry's material
     * @return the speculation level
     */
    private double calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess) {
        double ln=l.dotProduct(n);
        Vector r=l.add(n.scale(ln*-2));
        double minusVR=v.scale(-1).dotProduct(r);
        return ks*Math.max(0, Math.pow(minusVR, nShininess));
    }

    /**
     * @param kd of the material
     * @param l - the light direction
     * @param n - geometry's normal
     * @return the diffuse level
     */
    private double calcDiffusive(double kd, Vector l, Vector n) {
        double ln = Math.abs(l.dotProduct(n));
        return kd * ln;
    }
}
