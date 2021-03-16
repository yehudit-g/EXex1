package geometries;

import primitives.Point3D;
import primitives.Vector;

public interface Geometry extends Intersectable {
    /**
     * The interface units the geometries and make them implement the getNormal func
     * The func gets a point on the geometry and return the normal vector to this point
     */
    public Vector getNormal(Point3D p);
}
