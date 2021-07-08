package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The interface is responsible about finding intersections with geometries
 */
public abstract class Intersectable {
    public BoundingBox _box = new BoundingBox();//new BoundingBox(); //for BVH improvement

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
     * getter for BVH improvement
     * @return the geometry's box
     */
    public BoundingBox getBox() {
        this.setBox();
        return _box;
    }

    /**
     * Update the box corners according to the Geometry's borders
     */
   public Intersectable setBox(){return this;};

    /**
     *find the geometry's intersections with the given ray, at the given disance only
     * @param ray
     * @param maxDistance limits the intersections' search.
     * @return list of geoPoint intersections between the given ray and the geometry
     */
     abstract List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

    /**
     * @param ray
     * @return list of geoPoint intersections with no limit of distance.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * find the geometry's intersections with the given ray.
     * @param ray
     * @return list of intersections between the given ray and the geometry
     */
     public List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }


}
