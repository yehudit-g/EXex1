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

    //Array list -explanation:
    //Array list has constant access time, and it's addition/removal time is O(n) (worst case).
    //We want to build a scene so we will define the 3D-geometries' collection once (or not match more)
    //and we wont remove any object from the list (according to the requirements),
    //and then we will need to access the information many times(every ray's intersections etc.).
    //In addition, this implements uses less memory than linked list.

    public Geometries() {
        intersectable=new ArrayList<>();
    }

    public Geometries(Intersectable... lst) {
        intersectable = new ArrayList<>();
        add(lst);
    }

    private void add(Intersectable... lst) {
        intersectable.addAll(Arrays.asList(lst));
    }

//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//        return null;
//    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result=null;
        for(Intersectable element: intersectable){
            List<Point3D>elemList= element.findIntersections(ray);
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
