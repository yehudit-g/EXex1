package primitives;

import static geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Class Ray represents a ray in Cartesian coordinate system
 * by 3D beginning point and direction vector
 */
public class Ray {

    private static final double DELTA = 0.1;

    //the ray's beginning point
    private final Point3D _p0;
    //the ray's direction vector
    private final Vector _dir;

    public Ray(Point3D p0, Vector direction) {
        _p0 = p0;
        _dir = direction.normalized();
    }

    /**
     * Ray constructor ith offset normal vector
     * @param point original point - will be offset by the normal
     * @param direction vector of the ray dirction
     * @param n normal vector
     */
    public Ray(Point3D point, Vector direction, Vector normal) {
        double offset = normal.dotProduct(direction) > 0 ? DELTA : -DELTA;
        Vector delta = normal.scale(offset);
        _p0 = point.add(delta);
        _dir = direction.normalized();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

   @Override
    public String toString() {
        return "Ray{" +
                "p0=" + _p0 +
                ", direction " + _dir +
                '}';
    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    /**
     * @param t the asked distance from p0
     * @return the resulting point from the progression of t in the direction of the ray's vector
     */
    public Point3D getTargetPoint(double t) {
        if (t != 0) {
            return getP0().add(getDir().scale(t));
        }
        return _p0;
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
                double dis = geo.point.distance(_p0);
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
                double dis = p.distance(_p0);
                if (dis < distance) {
                    distance = dis;
                    nearPoint = p;
                }
            }
        }
        return nearPoint;
    }
}
