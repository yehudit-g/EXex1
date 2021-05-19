package elements;

import primitives.Color;
/**
 * The class represents an ambient light for scene, by its color and intensity
 */
public class AmbientLight extends Light {

    //default c-tor, reset black background
    public AmbientLight() {
        super(Color.BLACK);
    }

    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }
}
