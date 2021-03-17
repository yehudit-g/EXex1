package geometries;
import primitives.Point3D;
import primitives.Ray;
import java.util.List;

/**
 * The interface is responsible about finding intersections with geometries
 */
public interface Intersectable {
    /**
     * find the geometry's intersections with the given ray.
     * @param ray
     * @return list of intersections between the given ray and the geometry
     */
    List<Point3D> findIntersections(Ray ray);
}
