package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The interface is responsible about finding intersections with geometries
 */
public interface Intersectable {
    BoundingBox _box = new BoundingBox(); //for BVH improvement

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
     *The class represent a box which is parallel to the axis
     * and contain an Intersectable in the scene.
     * The box described by the upper-far-right corner and the close-lower-left corner
     * that get infinity values as default, and will be updated to the Geometry's borders.
     */
    class BoundingBox{
        protected Point3D rightUp=new Point3D(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
        protected Point3D lowerLeft=new Point3D(Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY);

        public void setLowerLeft(Point3D lowerLeft) {
            this.lowerLeft = lowerLeft;
        }

        public void setRightUp(Point3D rightUp) {
            this.rightUp = rightUp;
        }

        public Point3D getRightUp() {
            return rightUp;
        }

        public Point3D getLowerLeft() {
            return lowerLeft;
        }
    }

    /**
     * Update the box corners according to the Geometry's borders
     */
    void setBox();

    /**
     *find the geometry's intersections with the given ray, at the given disance only
     * @param ray
     * @param maxDistance limits the intersections' search.
     * @return list of geoPoint intersections between the given ray and the geometry
     */
     List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

    /**
     * @param ray
     * @return list of geoPoint intersections with no limit of distance.
     */
    default List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

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
