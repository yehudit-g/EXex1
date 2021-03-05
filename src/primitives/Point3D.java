package primitives;

import java.awt.*;
import java.util.Objects;

public class Point3D {
    double x;
    double y;
    double z;
    public static Point3D ZERO=new Point3D(0,0,0);

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x.coord;
        this.y = y.coord;
        this.z = z.coord;
    }

    public Vector subtract(Point3D p){
        Vector v1=new Vector(x, y, z);
        Vector v2=new Vector(p.x, p.y, p.z);
        return v1.subtract(v2);
    }

    public Point3D add (Vector v){

        Point3D p=new Point3D(x+v.head.x, y+v.head.y, z+v.head.z);
      //  Vector v1=new Vector(x, y, z);
        return p;
    }

    public double distanceSquared(Point3D p){
        return (x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)+(z-p.z)*(z-p.z);
    }

    public double distance(Point3D p){
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return Double.compare(point3D.x, x) == 0 && Double.compare(point3D.y, y) == 0 && Double.compare(point3D.z, z) == 0;
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
