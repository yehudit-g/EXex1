package geometries;

import primitives.*;

/**
 * The class represent a prototype for 3D geometry
 */
public abstract class Geometry implements Intersectable {
    private Color _emission = Color.BLACK;
    private Material _material = new Material();

    /**
     * @return the geometry's material
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * @return the color that the geometry emits
     */
    public Color getEmission() {
        return _emission;
    }
    /**
     * Set the color that the geometry emits
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }

    /**
     * setter
     * @param material
     * @return the geometry - chaining method
     */
    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     * The interface units the geometries and make them implement the getNormal func
     * The func gets a point on the geometry and return the normal vector to this point
     */
    public abstract Vector getNormal(Point3D p);

//    /**
//     * for BVH improvement
//     * @param ray
//     * @return if the ray intersects the box or not
//     */
//    public boolean IsIntersectionInBox(Ray ray){
//        return true;
//    }
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

}
