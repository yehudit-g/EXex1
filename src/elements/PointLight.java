package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * The class represents a point light in 3D scene
 */
public class PointLight extends Light implements LightSource {

    private final Point3D _position;
    private double _Kc = 1d;
    private double _Kl = 0d;
    private double _Kq = 0d;

    /**
     * c-tor
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;
    }

    public PointLight setKc(double kc) {
        _Kc = kc;
        return this;
    }

    public PointLight setKl(double kl) {
        _Kl = kl;
        return this;
    }

    public PointLight setKq(double kq) {
        _Kq = kq;
        return this;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double d = p.distance(_position);
        double attenuation = 1d / (_Kc + _Kl * d + _Kq * d * d);
        return _intensity.scale(attenuation);
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalized();
    }
}
