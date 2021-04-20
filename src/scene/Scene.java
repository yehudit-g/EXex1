package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;
/**
 * The class represents a 3D scene
 * by it's name, background color, light sources and geometric bodies
 */
public class Scene {
    public final String _name;
    public Color _background;
    public AmbientLight _ambientLight;
    public Geometries _geometries;

    public Scene(String name) {
        _name = name;
        _geometries=new Geometries();
    }

    //chaining methods
    public Scene setBackground(Color background) {
        _background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        _geometries = geometries;
        return this;
    }
}
