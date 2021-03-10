package geometries;

import primitives.Point3D;
import primitives.Vector;
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
        return null;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
