package primitives;

import elements.LightSource;

import static geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

/**
 * Class Ray represents a ray in Cartesian coordinate system
 * by 3D beginning point and direction vector
 */
public class Ray {

    private static final double DELTA = 0.1;

    //the ray's beginning point
    Point3D p0;
    //the ray's direction vector
    Vector dir;

    public Ray(Point3D p0, Vector direction) {
        this.p0 = p0;
        this.dir = direction.normalized();
    }

    /**
     * Ray constructor ith offset normal vector
     * @param point original point - will be offset by the normal
     * @param direction vector of the ray dirction
     * @param n normal vector
     */
    public Ray(Point3D point, Vector direction, Vector n) {
        Vector delta = n.scale(n.dotProduct(direction) > 0 ? DELTA : -DELTA);
        p0 = point.add(delta);
        dir = direction.normalized();
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
        if (t != 0) {
            return getP0().add(getDir().scale(t));
        }
        return p0;
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
     *
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
