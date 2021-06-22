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
     * The box described by the upper-close-right corner and the far-lower-left corner
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

    public boolean IsIntersectionInBox(Ray ray){
        Point3D rayP0=ray.getP0();
        Point3D rayDirection=ray.getDir().getHead();

        double p0_x=rayP0.getX(), p0_y=rayP0.getY(), p0_z=rayP0.getZ();
        double dir_x=rayDirection.getX(), dir_y=rayDirection.getY(), dir_z=rayDirection.getZ();

        List<Point3D> intersections=this.findIntersections(ray);
        if(intersections.size()!=2){
            return  false;
        }

        //t1x=distance(p0, intersections[0])
        //...
        //t2x=distance(p0, intersections[1])
        //...
        //t_near=max (t1_x, t1y, t1z)
        //t_far=min(t2x,t2y, t2z)
        //if(t_near!=t_far) return false
        //return true

//
//        if(rayDirection.getX()<0){
//            double maxX=(_box.lowerLeft.getX()-rayP0.getX())/rayDirection.getX();
//            if(maxX<=0){
//                return false;
//            }
//            double minX=(_box.rightUp.getX()-rayP0.getX())/rayDirection.getX();
//        }
    }

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
