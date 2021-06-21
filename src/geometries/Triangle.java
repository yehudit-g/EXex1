package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Triangle class represents triangle as a 2D polygon
 * in 3D Cartesian coordinate system
 */
public class Triangle extends Polygon {

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);

    }

//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//        List<Point3D> lst = super.findIntersections(ray);
//        if(lst!=null && isInside(lst.get(0)))
//            return lst;
//        return null;
//    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

}
