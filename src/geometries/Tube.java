package geometries;

import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
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
        return null;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
