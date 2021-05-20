package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The class represents a directional light source in 3D scene
 */
public class DirectionalLight extends Light implements LightSource{
    private final Vector _direction;

    /**
     * c-tor
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    @Override
    //return the light's direction
    public Vector getL(Point3D p) {
        return _direction;
    }
}
