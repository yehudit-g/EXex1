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
public class Plane extends Geometry {

    private final Point3D _q0;
    private final Vector _normal;

    public Plane(Point3D q0, Vector normal) {
        _q0 = q0;
        _normal = normal.normalize();
    }

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;

        //create 2 vectors on the plane
        Vector U = p1.subtract(p2);
        Vector V = p1.subtract(p3);

        //calculate the normal vector by cross product
        _normal = U.crossProduct(V).normalize();
    }

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    public Point3D getQ0() {
        return _q0;
    }

    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + _q0 +
                ", normal=" + _normal +
                '}';
    }


    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Point3D P0 = ray.getP0();
        Point3D Q0 = getQ0();
        Vector v = ray.getDir();

        //Ray starts in q0
        if (Q0.equals(P0))
            return null;

        double nv = _normal.dotProduct(v);
        //Ray contained in the plane
        if (isZero(nv))
            return null;

        Vector PQ =Q0.subtract(P0);

        double t = alignZero(_normal.dotProduct(PQ) / nv);
        if (t > 0 && alignZero(t - maxDistance) <= 0) {
            Point3D p = ray.getTargetPoint(t);
            return List.of(new GeoPoint(this, p));
        }
        return null;
    }
}
