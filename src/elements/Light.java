package elements;

import primitives.Color;

/**
 *The abstract class represent characteristic of light
 */
abstract class Light {
    protected final Color _intensity;

    /**
     * c-tor
     * @param intensity- of the light
     */
    protected Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     * @return the light's intensity
     */
    public Color getIntensity() {
        return _intensity;
    }
}
