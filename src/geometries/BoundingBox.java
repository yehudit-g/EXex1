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
public class BoundingBox{ // implements Intersectable
    protected double maxX = Double.POSITIVE_INFINITY;
    protected double maxY = Double.POSITIVE_INFINITY;
    protected double maxZ = Double.POSITIVE_INFINITY;
    protected double minX = Double.NEGATIVE_INFINITY;
    protected double minY = Double.NEGATIVE_INFINITY;
    protected double minZ = Double.NEGATIVE_INFINITY;

    //c-tors
    public BoundingBox(){}
    public BoundingBox(double maxX, double maxY, double maxZ, double minX, double minY, double minZ) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
    }

    /**
     * reset the box with opposite values to get the Min/Max values of an object/list
     */
    public void ResetOppositeValuesBox() {
        setMaxX(Double.NEGATIVE_INFINITY);
        setMaxY(Double.NEGATIVE_INFINITY);
        setMaxZ(Double.NEGATIVE_INFINITY);
        setMinX(Double.POSITIVE_INFINITY);
        setMinY(Double.POSITIVE_INFINITY);
        setMinZ(Double.POSITIVE_INFINITY);
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

    /**
     * The calculation in this func was written according to this nice file(p.18):
     * https://www.cs.tau.ac.il/~dcor/Graphics/pdf.slides/RT_acceleration2016.pdf
     * @param ray
     * @return true if the ray intersect the box
     */
    public boolean IsIntersectionInBox(Ray ray) {
        Point3D rayP0 = ray.getP0();
        Point3D rayDirection = ray.getDir().getHead();

        double p0_x = rayP0.getX(), p0_y = rayP0.getY(), p0_z = rayP0.getZ();
        double dir_x = rayDirection.getX(), dir_y = rayDirection.getY(), dir_z = rayDirection.getZ();

        //pseudo-code explanation:
        //t1x=distance(p0, intersections[0])
        //...
        //t2x=distance(p0, intersections[1])
        //...
        //t_near=max (t1_x, t1y, t1z)
        //t_far=min(t2x,t2y, t2z)
        //if(t_near>t_far) return false
        //return true

        double t_maxX, t_maxY, t_maxZ, t_minX, t_minY, t_minZ;
        if (dir_x < 0) {
            t_maxX = (minX - p0_x) / dir_x;
            if (t_maxX <= 0) {
                return false; //box is behind
            }
            t_minX = (maxX - p0_x) / dir_x;
        } else if (dir_x > 0) {
            t_maxX = (maxX - p0_x) / dir_x;
            if (t_maxX <= 0) {
                return false; //box is behind
            }
            t_minX = (minX - p0_x) / dir_x;
        } else { //dirX = 0
            if (p0_x >= maxX || p0_x <= minX)
                return false; //parallel
            else {
                t_maxX = Double.POSITIVE_INFINITY;
                t_minX = Double.NEGATIVE_INFINITY;
            }
        }

        if (dir_y < 0) {
            t_maxY = (minY - p0_y) / dir_y;
            if (t_maxY <= 0) {
                return false; //box is behind
            }
            t_minY = (maxY - p0_y) / dir_y;
        } else if (dir_y > 0) {
            t_maxY = (maxY - p0_y) / dir_y;
            if (t_maxY <= 0) {
                return false; //box is behind
            }
            t_minY = (minY - p0_y) / dir_y;
        } else { //dirY=0
            if (p0_y >= maxY || p0_y <= minY)
                return false;
            else {
                t_maxY = Double.POSITIVE_INFINITY;
                t_minY = Double.NEGATIVE_INFINITY;
            }
        }

        if (dir_z < 0) {
            t_maxZ = (minZ - p0_z) / dir_z;
            if (t_maxZ <= 0) {
                return false; //box is behind
            }
            t_minZ = (maxZ - p0_z) / dir_z;
        } else if (dir_z > 0) {
            t_maxZ = (maxZ - p0_z) / dir_z;
            if (t_maxZ <= 0) {
                return false; //box is behind
            }
            t_minZ = (minZ - p0_z) / dir_z;
        } else { //dirZ=0
            if (p0_z >= maxZ || p0_z <= minZ)
                return false;
            else {
                t_maxZ = Double.POSITIVE_INFINITY;
                t_minZ = Double.NEGATIVE_INFINITY;
            }
        }


        double t_near = Math.max(t_minX, t_minY);
        t_near = Math.max(t_near, t_minZ);
        double t_far = Math.min(t_maxX, t_maxY);
        t_far = Math.min(t_far, t_maxZ);

        if (t_far < t_near) //box is missed
            return false;
        return true; //box survived all the tests...
    }
}

