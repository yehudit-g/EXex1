package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 *The class is responsible about the scanning rays
 */
public abstract class RayTracerBase {
    protected Scene _scene;

    /**
     * c-tor
     * @param scene
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     * @param ray
     * @return the color of the pixel that the ray passes through
     */
     public abstract Color traceRay(Ray ray);
}
