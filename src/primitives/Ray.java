package primitives;

import java.util.List;
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

    public Point3D getTargetPoint(double t) {
        return getP0().add(getDir().scale(t));
    }

    public Point3D findClosestPoint(List<Point3D> point3DList) {
        double distance = Double.POSITIVE_INFINITY;
        Point3D nearPoint=null;
        if(point3DList!=null) {
            for (Point3D p : point3DList) {
                double dis = p.distance(p0);
                if (dis < distance) {
                    distance = dis;
                    nearPoint = p;
                }
            }
        }
        return nearPoint;
    }
}
