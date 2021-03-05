package primitives;

import java.util.Objects;

public class Vector {

    Point3D head;

    public Vector(Point3D head) {
        this.head = head;
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D p=new Point3D(x,y,z);
        if(p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("The zero vector is illegal");
        this.head = p;
    }

    public Vector(double x, double y, double z) {
        Point3D p=new Point3D(x,y,z);
        if(p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("The zero vector is illegal");
        this.head = p;
    }

    public Point3D getHead() {
        return head;
    }

    public Vector subtract(Vector v){
        Vector v1=new Vector(head.x-v.head.x, head.y-v.head.y, head.z-v.head.z);
        return v1;
    }

    public Vector add (Vector v){
        Vector v1=new Vector(head.x+v.head.x, head.y+v.head.y, head.z+v.head.z);
        return v1;
    }

    public Vector scale (double scalar){
        Vector v1=new Vector(head.x*scalar, head.y*scalar, head.z*scalar);
        return v1;
    }

    public double dotProduct (Vector v){
       return head.x*v.head.x + head.y*v.head.y + head.z*v.head.z;
    }

    public Vector crossProduct (Vector v){
        Vector v1=new Vector((head.y*v.head.z)-(head.z*v.head.y), (head.z*v.head.x)-(head.x*v.head.z), (head.x*v.head.y)-(head.y*v.head.x));
        return v1;
    }

    public double lengthSquared(){
    Point3D p= new Point3D(head.x, head.y, head.z);
    return p.distanceSquared(Point3D.ZERO);
    }

    public double length(){
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize(){
        head.x= head.x/length();
        head.y= head.y/length();
        head.z= head.z/length();
        return this;
    }

    public Vector normalized(){
        Vector v1=new Vector(head.x, head.y, head.z);
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
    public int hashCode() {
        return Objects.hash(head);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }
}
