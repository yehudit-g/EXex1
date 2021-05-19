package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The interface is responsible about finding intersections with geometries
 */
public interface Intersectable {

    /**
     * The class represent a 3D point on a geometry.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * c-tor
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }
    }

    /**
     *find the geometry's intersections with the given ray.
     * @param ray
     * @return list of geoPoint intersections between the given ray and the geometry
     */
     List<GeoPoint> findGeoIntersections(Ray ray);

    /**
     * find the geometry's intersections with the given ray.
     * @param ray
     * @return list of intersections between the given ray and the geometry
     */
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }


}
