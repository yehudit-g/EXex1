package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.BoundingBox;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Plane;
import primitives.Color;
import renderer.Render;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The class represents a 3D scene
 * by it's name, background color, light sources and geometric bodies
 */
public class Scene {
    private final String _name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries;
    public List<LightSource> lights = new LinkedList<>();

    public Scene(String name) {
        _name = name;
        geometries = new Geometries();
    }

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * setter
     *
     * @param background
     * @return the scene - chaining method
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter
     *
     * @param ambientLight
     * @return the scene - chaining method
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setter
     *
     * @param geometries
     * @return the scene - chaining method
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * turning on the BVH improvement
     * @return this. chaining method
     */
    public Scene turnOnUsingBVH() {
        geometries.turnOnBVH();
        geometries.BuildTree();
        return this;
    }
}
