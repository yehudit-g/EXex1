package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 * Plane class represents 2D Plane in 3D Cartesian coordinate system
 * by 3D point on the plane and its normal vector
 */
public class Plane implements Geometry {

    Point3D q0;
    Vector normal;

    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this.q0 = p1;
        Vector U = p1.subtract(p2);
        Vector V = p2.subtract(p3);

        Vector N = U.crossProduct(V);

        this.normal = N.normalize();
    }

    @Override
    public Vector getNormal(Point3D p) {
        return normal;
    }

    public Point3D getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
