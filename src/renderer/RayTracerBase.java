package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.awt.*;

/**
 *The class is responsible about the rays scanning
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

     /**
     * @param rays
     * @return the average color of the rays pass through pixel
     */
     public abstract Color traceRay(java.util.List<Ray> rays);

}
