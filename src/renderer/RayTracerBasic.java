package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 *The class is responsible about the scanning rays
 */
public class RayTracerBasic extends RayTracerBase{

    /**
     * c-tor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point3D> point3DList=_scene._geometries.findIntersections(ray);
        if(point3DList==null) //no intersections
            return _scene._background;
         return calcColor(ray.findClosestPoint(point3DList));
    }

    /**
     * @param p - point in the
     * @return the color of p
     */
    public Color calcColor(Point3D p){
        return _scene._ambientLight.getIntensity();
    }
}
