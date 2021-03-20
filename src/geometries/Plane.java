package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
        //create 2 vectors on the plane
        Vector U = p1.subtract(p2);
        Vector V = p2.subtract(p3);
        //calculate the normal vector by cross product
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

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Point3D Q0 = getQ0();
        Vector v = ray.getDir();

        //Ray starts in q0
        if (Q0.equals(P0))
            return null;

        double nv = getNormal().dotProduct(v);
        //Ray contained in the plane
        if (isZero(nv))
            return null;

        double t = alignZero(getNormal().dotProduct(Q0.subtract(P0)) / nv );
        if(t<=0)
            return null;
        Point3D p = ray.getTargetPoint(t);
        return List.of(p);
    }
}
