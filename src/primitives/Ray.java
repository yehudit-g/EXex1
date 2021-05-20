package primitives;

import geometries.Intersectable;

import static geometries.Intersectable.GeoPoint;

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

    /**
     * @param t the asked distance from p0
     * @return the resulting point from the progression of t in the direction of the ray's vector
     */
    public Point3D getTargetPoint(double t) {
        return getP0().add(getDir().scale(t));
    }

    /**
     * @param geoPointList - GeoPoint intersections on the ray
     * @return the GeoPoint from the list that is the closest to p0
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {
        double distance = Double.POSITIVE_INFINITY;
        GeoPoint nearPoint = null;
        if (geoPointList != null) {
            for (GeoPoint geo : geoPointList) {
                double dis = geo.point.distance(p0);
                if (dis < distance) {
                    distance = dis;
                    nearPoint = geo;
                }
            }
        }
        return nearPoint;
    }


    /**
     * ---for first tests only!---
     * @param point3DList intersections on the ray
     * @return the point from the list that is the closest to p0
     */
    public Point3D findClosestPoint(List<Point3D> point3DList) {
        double distance = Double.POSITIVE_INFINITY;
        Point3D nearPoint = null;
        if (point3DList != null) {
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
