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
        Vector v1=new Vector(getHead().x-v.getHead().x, getHead().y-v.getHead().y, getHead().z-v.getHead().z);
        return v1;
    }

    public Vector add (Vector v){
        Vector v1=new Vector(getHead().x+v.getHead().x, getHead().y+v.getHead().y, getHead().z+v.getHead().z);
        return v1;
    }

    public Vector scale (double scalar){
        Vector v1=new Vector(getHead().x*scalar, getHead().y*scalar, getHead().z*scalar);
        return v1;
    }

    public double dotProduct (Vector v){
       return getHead().x*v.getHead().x + getHead().y*v.getHead().y + getHead().z*v.getHead().z;
    }

    public Vector crossProduct (Vector v){
        Vector v1=new Vector((getHead().y*v.getHead().z)-(getHead().z*v.getHead().y), (getHead().z*v.getHead().x)-(getHead().x*v.getHead().z), (head.x*v.head.y)-(head.y*v.head.x));
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
        double len=length();
        head.x= head.x/len;
        head.y= head.y/len;
        head.z= head.z/len;
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
