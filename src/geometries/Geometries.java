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
        this.ResetBox();
    }

    /**
     * reset the box with opposite values to get the Min/Max borders of the list
     */
    private void ResetBox() {
        _box.setMaxX(Double.NEGATIVE_INFINITY);
        _box.setMaxY(Double.NEGATIVE_INFINITY);
        _box.setMaxZ(Double.NEGATIVE_INFINITY);
        _box.setMinX(Double.POSITIVE_INFINITY);
        _box.setMinY(Double.POSITIVE_INFINITY);
        _box.setMinZ(Double.POSITIVE_INFINITY);
    }

    public Geometries(Intersectable... lst) {
        intersectable = new LinkedList<>();
        add(lst);
    }

    public void add(Intersectable... lst) {
        //intersectable.addAll(Arrays.asList(lst));
        for (Intersectable geo:lst) {
            intersectable.add(geo);
            if(geo._box.minX<this._box.minX)
                _box.setMinX(geo._box.minX);
            if(geo._box.minY<this._box.minY)
                _box.setMinY(geo._box.minY);
            if(geo._box.minZ<this._box.minZ)
                _box.setMinZ(geo._box.minZ);
            if(geo._box.maxX>this._box.maxX)
                _box.setMaxX(geo._box.maxX);
            if(geo._box.maxY>this._box.maxY)
                _box.setMaxY(geo._box.maxY);
            if(geo._box.maxZ>this._box.maxZ)
                _box.setMaxZ(geo._box.maxZ);
        }
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

    /**
     *build the bounding boxes tree.
     *if there is a plane it will be attached to the root.
     */
    public void BuildTree(){
        List<BoundingBox> lst= new ArrayList<>();
        List<Intersectable> infinities= new ArrayList<>();
        for(int i=0; i<intersectable.size(); i++){
            if(intersectable.get(i).getClass()== Plane.class)
                infinities.add(intersectable.get(i));
            else
                lst.add(intersectable.get(i).getBox());
        }
        intersectable.clear();

        //Attach pairs of close boxes, until all the geometries are in 1 big box
        //In every loop the closest pair will be attached
        int firstIndex = 0, secondIndex=0;
        double miniDistance, tmpDistance;
        while (lst.size()>2) {
            miniDistance=Double.POSITIVE_INFINITY;
            for (int i = 0; i < lst.size(); i++) {
                firstIndex=i;
                for (int j = i+1; j < lst.size(); j++) {
                    tmpDistance=lst.get(i).findDistance(lst.get(j));
                    if (tmpDistance < miniDistance){
                        secondIndex=j;
                        miniDistance=tmpDistance;
                    }
                }
            }
            BoundingBox first=lst.get(firstIndex);
            BoundingBox second=lst.get(secondIndex);
            BoundingBox b=new BoundingBox(Math.max(first.getMaxX(),second.getMaxX()),Math.max(first.getMaxY(),second.getMaxY()),
                    Math.max(first.getMaxZ(),second.getMaxZ()),Math.min(first.getMinX(),second.getMinX()),
                    Math.min(first.getMinY(),second.getMinY()),Math.min(first.getMinZ(),second.getMinZ()));
            b._geometries.add(first);
            b._geometries.add(second);
            lst.add(b);
            lst.remove(secondIndex); //before the first - to protect the index value.
            lst.remove(firstIndex);
        }
        //box of scene's borders
        //include planes!

        //lst.get(0).addAll(infinities);
    }
 }
