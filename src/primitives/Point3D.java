package primitives;

import java.awt.*;
import java.util.Objects;
/**
 * Class Point3D represents a three dimensional point in Cartesian
 * coordinate system.
 */
public class Point3D {
    final Coordinate x;
    final Coordinate y;
    final Coordinate z;

    public double getX() {
        return x.coord;
    }

    public double getY() {
        return y.coord;
    }

    public double getZ() {
        return z.coord;
    }

    public static Point3D ZERO=new Point3D(0,0,0);

    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate (y);
        this.z = new Coordinate (z);
    }

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //return the resulting vector of sub between 2 points
    public Vector subtract(Point3D p){
        Vector v1=new Vector(x, y, z);
        Vector v2=new Vector(p.x, p.y, p.z);
        return v1.subtract(v2);
    }

    //return the resulting point of adding vector to the current point.
    public Point3D add (Vector v){
        Point3D p=new Point3D(x.coord+v.head.x.coord, y.coord+v.head.y.coord, z.coord+v.head.z.coord);
        return p;
    }

    //return the squared distance between 2 points.
    public double distanceSquared(Point3D p){
        double px=p.x.coord;
        double py=p.y.coord;
        double pz=p.z.coord;
        return (x.coord-px)*(x.coord-px)+(y.coord-py)*(y.coord-py)+(z.coord-pz)*(z.coord-pz);
    }

    //return the distance between 2 points.
    public double distance(Point3D p){
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return Double.compare(point3D.x.coord, x.coord) == 0 && Double.compare(point3D.y.coord, y.coord) == 0 && Double.compare(point3D.z.coord, z.coord) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return /*Point3D*/"{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
