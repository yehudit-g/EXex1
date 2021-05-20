package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *The class represent a prototype for light-source in 3D scene
 */
public interface LightSource {
    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);
}
