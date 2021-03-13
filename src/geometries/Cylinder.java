package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represents cylinder as a tube with limited height
 * in 3D Cartesian coordinate system
 */
public class Cylinder extends Tube {

    double height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D p) {
        double t=axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
        Point3D o=axisRay.getP0().add(axisRay.getDir().scale(t));
        if(p.subtract(o).length()==radius) {
                return super.getNormal(p);
        }
        return axisRay.getDir().normalized();
    }
}
