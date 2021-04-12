package primitives;

/**
 * Class Vector represents a Vector in Cartesian coordinate system
 * by its three dimensional point head
 */
public class Vector {

    Point3D _head;

    public Vector(Point3D head) {
        this._head = head;
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D p = new Point3D(x, y, z);
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("The zero vector is illegal");
        this._head = p;
    }

    public Vector(double x, double y, double z) {
        Point3D p = new Point3D(x, y, z);
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("The zero vector is illegal");
        this._head = p;
    }

    public Point3D getHead() {
        return _head;
    }

    //return the resulting vector of sub between 2 vectors
    public Vector subtract(Vector v) {
        Vector v1 = new Vector(_head.x.coord - v._head.x.coord, _head.y.coord - v._head.y.coord, _head.z.coord - v._head.z.coord);
        return v1;
    }

    //return the resulting vector of adding vector to the current vector.
    public Vector add(Vector v) {
        return new Vector(_head.x.coord + v._head.x.coord, _head.y.coord + v._head.y.coord, _head.z.coord + v._head.z.coord);
    }

    //return the resulting vector of scalar product on the current vector.
    public Vector scale(double scalar) {
        return new Vector(_head.x.coord * scalar, _head.y.coord * scalar, _head.z.coord * scalar);
    }

    //return the resulting vector of vector product on the current vector.
    public double dotProduct(Vector v) {
        double u1=_head.x.coord;
        double u2=_head.y.coord;
        double u3=_head.z.coord;
        double v1=v._head.x.coord;
        double v2=v._head.y.coord;
        double v3=v._head.z.coord;
        return u1 * v1 + u2 * v2 + u3 * v3;
    }

    //return the resulting vector of cross product on the current vector.
    public Vector crossProduct(Vector v) {
        double u1=_head.x.coord;
        double u2=_head.y.coord;
        double u3=_head.z.coord;
        double v1=v._head.x.coord;
        double v2=v._head.y.coord;
        double v3=v._head.z.coord;
        return new Vector((u2 * v3) - (u3 * v2), (u3 * v1) - (u1 * v3), (u1 * v2) - (u2 * v1));
    }

    //return the squared length of the vector.
    public double lengthSquared() {
        Point3D p = new Point3D(_head.x, _head.y, _head.z);
        return p.distanceSquared(Point3D.ZERO);
    }

    //return the length of the vector.
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    //normalize the vector (to length = 1) and return it.
    public Vector normalize() {
        double len = length();
        _head =new Point3D(_head.x.coord / len, _head.y.coord / len, _head.z.coord / len);
        return this;
    }

    //return a normalized vector of the given one.
    public Vector normalized() {
        Vector v1 = new Vector(_head.x, _head.y, _head.z);
        return v1.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector.getHead());
    }


    @Override
    public String toString() {
        return "Vector{" +
                "head=" + getHead() +
                '}';
    }
}
