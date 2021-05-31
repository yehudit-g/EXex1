package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The class represents a spot light in 3D scene
 */
public class SpotLight extends PointLight {
    /**
     * todo
     */
    private final Vector _direction;

    /**
     * c-tor
     * @param intensity
     * @param position
     * @param direction
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        Color baseIntensity = super.getIntensity(p);
        Vector l = super.getL(p);
        double factor = Math.max(0, _direction.dotProduct(l));
        return baseIntensity.scale(factor);
    }
}
