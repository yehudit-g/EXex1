package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry implements Intersectable {
    private Color _emission = Color.BLACK;
    private Material _material = new Material();

    public Material getMaterial() {
        return _material;
    }

    /**
     * @return
     */
    public Color getEmission() {
        return _emission;
    }

    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }

    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     * The interface units the geometries and make them implement the getNormal func
     * The func gets a point on the geometry and return the normal vector to this point
     */
    public abstract Vector getNormal(Point3D p);
}
