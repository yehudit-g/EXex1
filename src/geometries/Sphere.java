package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Sphere class represents 3D sphere in Cartesian coordinate system
 * by 3D center point and radius length
 */
public class Sphere implements Geometry {

    Point3D center;
    double radius;

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return p.subtract(getCenter()).normalize();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0=ray.getP0();
        Vector v=ray.getDir();
        if(getCenter().equals(P0))
            return List.of(P0.add(v.scale(getRadius())));
        Vector u=getCenter().subtract(P0);
        double tm=v.dotProduct(u);
        double d=alignZero( Math.sqrt(u.lengthSquared()-tm*tm));
        //Ray cross out of the sphere
        if(d>getRadius())
            return null;

        double th=alignZero( Math.sqrt(getRadius()*getRadius()-d*d));
        //tangent
        if(isZero(th))
            return null;
        double p1=alignZero(tm-th);
        double p2=alignZero(tm+th);
        //2 intersections
        if(p1>0&&p2>0)
            return List.of(ray.getTargetPoint(p1),ray.getTargetPoint(p2));
        //Ray starts inside the sphere
        if(p1>0)
            return List.of(ray.getTargetPoint(p1));
        if(p2>0)
            return List.of(ray.getTargetPoint(p2));
        return null;
    }
}
