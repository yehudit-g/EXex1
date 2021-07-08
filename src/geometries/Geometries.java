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
    private List<Intersectable> intersectable = null;
    public boolean isUsingBVH = false; //flag for BVH improvement

    //Linked list -explanation:
    //Linked list has constant addition time.
    //We want to build a scene so we need a good adding-running-time to the 3D-geometries' collection.
    //In the alternative, array list, the adding will take O(n).

    public Geometries() {
        intersectable = new LinkedList<>();
    }

    //using the BVH improvement. chaining method
    public Geometries turnOnBVH() {
        isUsingBVH = true;
        return this;
    }

    /**
     * set the box borders according to the Min/Max values of the geometries' list
     */
    @Override
    public Intersectable setBox() {
        _box.ResetOppositeValuesBox();
        BoundingBox b;
        for (Intersectable element : intersectable) {
            b = element.getBox();
            _box.maxX = Math.max(_box.getMaxX(), b.getMaxX());
            _box.maxY = Math.max(_box.getMaxY(), b.getMaxY());
            _box.maxZ = Math.max(_box.getMaxZ(), b.getMaxZ());
            _box.minX = Math.min(_box.getMinX(), b.getMinX());
            _box.minY = Math.min(_box.getMinY(), b.getMinY());
            _box.minZ = Math.min(_box.getMinZ(), b.getMinZ());
        }
        return this;
    }

    public Geometries(Intersectable... lst) {
        intersectable = new LinkedList<>();
        add(lst);
        this.setBox();
    }

    public void add(Intersectable... lst) {
        intersectable.addAll(Arrays.asList(lst));
        this.setBox();
    }

//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//        return null;
//    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;
        if (!isUsingBVH || getBox().IsIntersectionInBox(ray)) {
            for (Intersectable element : intersectable) {
                if (!isUsingBVH ||element.getBox().IsIntersectionInBox(ray)) {
                    List<GeoPoint> elemList = element.findGeoIntersections(ray, maxDistance);
                    if (elemList != null) {
                        if (result == null) {
                            result = new LinkedList<>();
                        }
                        result.addAll((elemList));
                    }
                }
            }
        }
        return result;
    }

    /**
     * build the bounding boxes tree.
     * if there is a plane it will be attached to the root.
     */
    public void BuildTree() {
        List<Intersectable> lst = new ArrayList<>();
        List<Intersectable> infinities = new ArrayList<>();
        for (int i = 0; i < intersectable.size(); i++) {
            if (intersectable.get(i).getClass() == Plane.class)
                infinities.add(intersectable.get(i));
            else
                lst.add(intersectable.get(i));
        }

        //Attach pairs of close boxes, until all the geometries are in 1 big box
        //In every loop the closest pair will be attached
        int firstIndex = 0, secondIndex = 0;
        double miniDistance, tmpDistance;
        while (lst.size() > 1) {
            miniDistance = Double.POSITIVE_INFINITY;
            for (int i = 0; i < lst.size(); i++) {
                for (int j = i + 1; j < lst.size(); j++) {
                    tmpDistance = lst.get(i).getBox().findDistance(lst.get(j).getBox());
                    if (tmpDistance < miniDistance) {
                        firstIndex = i;
                        secondIndex = j;
                        miniDistance = tmpDistance;
                    }
                }
            }
            Geometries tmp = new Geometries(lst.get(firstIndex), lst.get(secondIndex));
            lst.remove(secondIndex); //before the first - to protect the index value.
            lst.remove(firstIndex);
            lst.add(tmp.turnOnBVH());
        }

        intersectable.clear();
        intersectable.add(lst.get(0));
        for (int i = 0; i < infinities.size(); i++)
            intersectable.add(infinities.get(i));
    }
}
