package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class for all intersectable objects
 */
public class Geometries implements Intersectable {
    private List<Intersectable> intersectable=null;

    //Linked list -explanation:
    //Linked list has constant addition time.
    //We want to build a scene so we need a good adding-running-time to the 3D-geometries' collection.
    //In the alternative, array list, the adding will take O(n).

    public Geometries() {
        intersectable=new LinkedList<>();
    }

    public Geometries(Intersectable... lst) {
        intersectable = new LinkedList<>();
        add(lst);
    }

    public void add(Intersectable... lst) {
        intersectable.addAll(Arrays.asList(lst));
    }

//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//        return null;
//    }



    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> result=null;
        for(Intersectable element: intersectable){
            List<GeoPoint>elemList= element.findGeoIntersections(ray, maxDistance);
            if(elemList!=null){
                if(result==null){
                    result=new LinkedList<>();
                }
                result.addAll((elemList));
            }
        }
        return result;
    }

}
