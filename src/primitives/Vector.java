package primitives;

import java.util.Objects;

public class Vector {

    Point3D head;

    public Vector(Point3D head) {
        this.head = head;
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D p = new Point3D(x, y, z);
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("The zero vector is illegal");
        this.head = p;
    }

    public Vector(double x, double y, double z) {
        Point3D p = new Point3D(x, y, z);
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("The zero vector is illegal");
        this.head = p;
    }

    public Point3D getHead() {
        return head;
    }

    public Vector subtract(Vector v) {
        Vector v1 = new Vector(getHead().x.coord - v.getHead().x.coord, getHead().y.coord - v.getHead().y.coord, getHead().z.coord - v.getHead().z.coord);
        return v1;
    }

    public Vector add(Vector v) {
        Vector v1 = new Vector(getHead().x.coord + v.getHead().x.coord, getHead().y.coord + v.getHead().y.coord, getHead().z.coord + v.getHead().z.coord);
        return v1;
    }

    public Vector scale(double scalar) {
        Vector v1 = new Vector(getHead().x.coord * scalar, getHead().y.coord * scalar, getHead().z.coord * scalar);
        return v1;
    }

    public double dotProduct(Vector v) {
        double u1=getHead().x.coord;
        double u2=getHead().y.coord;
        double u3=getHead().z.coord;
        double v1=v.getHead().x.coord;
        double v2=v.getHead().y.coord;
        double v3=v.getHead().z.coord;
        return u1 * v1 + u2 * v2 + u3 * v3;
    }

    public Vector crossProduct(Vector v) {
        double u1=getHead().x.coord;
        double u2=getHead().y.coord;
        double u3=getHead().z.coord;
        double v1=v.getHead().x.coord;
        double v2=v.getHead().y.coord;
        double v3=v.getHead().z.coord;
        return new Vector((u2 * v3) - (u3 * v2), (u3 * v1) - (u1 * v3), (u1 * v2) - (u2 * v1));
    }

    public double lengthSquared() {
        Point3D p = new Point3D(head.x, head.y, head.z);
        return p.distanceSquared(Point3D.ZERO);
    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize() {
        double len = length();
        head=new Point3D(head.x.coord / len, head.y.coord / len, head.z.coord / len);
        return this;
    }

    public Vector normalized() {
        Vector v1 = new Vector(head.x, head.y, head.z);
        return v1.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }


    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }
}
