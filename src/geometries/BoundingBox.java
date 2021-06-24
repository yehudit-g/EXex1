package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 *The class represent a box which is parallel to the axis
 * and contain an Intersectable in the scene.
 * The box described by the upper-close-right corner and the far-lower-left corner
 * that get infinity values as default, and will be updated to the Geometry's borders.
 */
public class BoundingBox implements Intersectable{
    protected double maxX = Double.POSITIVE_INFINITY;
    protected double maxY = Double.POSITIVE_INFINITY;
    protected double maxZ = Double.POSITIVE_INFINITY;
    protected double minX = Double.NEGATIVE_INFINITY;
    protected double minY = Double.NEGATIVE_INFINITY;
    protected double minZ = Double.NEGATIVE_INFINITY;
    public Geometries _geometries;

    //c-tors
    public BoundingBox(){};
    public BoundingBox(double maxX, double maxY, double maxZ, double minX, double minY, double minZ) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public double getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(double maxZ) {
        this.maxZ = maxZ;
    }

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMinZ() {
        return minZ;
    }

    public void setMinZ(double minZ) {
        this.minZ = minZ;
    }

    /**
     * @param b - the second box
     * @return the distance between the centers of 2 boxes
     */
    public double findDistance(BoundingBox b){
        double mx=(getMaxX()-getMinX())/2;
        double my=(getMaxY()-getMinY())/2;
        double mz=(getMaxZ()-getMinZ())/2;
        Point3D center=new Point3D(getMinX()+mx,getMinY()+my,getMinZ()+mz);
        mx=(b.getMaxX()-b.getMinX())/2;
        my=(b.getMaxY()-b.getMinY())/2;
        mz=(b.getMaxZ()-b.getMinZ())/2;
        Point3D b_center=new Point3D(b.getMinX()+mx,b.getMinY()+my,b.getMinZ()+mz);
        return center.distance(b_center);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return null;
    }
}


//    public boolean IsIntersectionInBox(Ray ray){
//        Point3D rayP0=ray.getP0();
//        Point3D rayDirection=ray.getDir().getHead();
//
//        double p0_x=rayP0.getX(), p0_y=rayP0.getY(), p0_z=rayP0.getZ();
//        double dir_x=rayDirection.getX(), dir_y=rayDirection.getY(), dir_z=rayDirection.getZ();
//
//        List<Point3D> intersections=this.findIntersections(ray);
//        if(intersections.size()!=2){
//            return  false;
//        }
//
//        //t1x=distance(p0, intersections[0])
//        //...
//        //t2x=distance(p0, intersections[1])
//        //...
//        //t_near=max (t1_x, t1y, t1z)
//        //t_far=min(t2x,t2y, t2z)
//        //if(t_near!=t_far) return false
//        //return true
//
////
////        if(rayDirection.getX()<0){
////            double maxX=(_box.lowerLeft.getX()-rayP0.getX())/rayDirection.getX();
////            if(maxX<=0){
////                return false;
////            }
////            double minX=(_box.rightUp.getX()-rayP0.getX())/rayDirection.getX();
////        }
//    }

