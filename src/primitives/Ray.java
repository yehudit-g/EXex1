package primitives;

import java.util.Objects;
/**
 * Class Ray represents a ray in Cartesian coordinate system
 * by 3D beginning point and direction vector
 */
public class Ray {
    //the ray's beginning point
    Point3D p0;
    //the ray's direction vector
    Vector dir;

    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", direction " + dir +
                '}';
    }

    public Point3D getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
}
