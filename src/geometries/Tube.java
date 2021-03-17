package geometries;

import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

/**
 * Tube class represents a tube in 3D Cartesian coordinate system
 * by central axis ray and the radius length
 */
public class Tube implements Geometry {
    Ray axisRay;
    double radius;

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
        double t=axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
        Point3D o=axisRay.getP0().add(axisRay.getDir().scale(t));
        return (p.subtract(o)).normalize();
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
