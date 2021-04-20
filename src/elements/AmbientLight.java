package elements;

import primitives.Color;
/**
 * The class represents an ambient light for scene, by its color and intensity
 */
public class AmbientLight {
    //one field for the color at the desired intensity
    final private Color _intensity;

    public AmbientLight(Color Ia, double Ka) {
        _intensity = Ia.scale(Ka);
    }

    public Color getIntensity() {
        return _intensity;
    }
}
