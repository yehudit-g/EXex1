package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *The class represent a prototype for light-source in 3D scene
 */
public interface LightSource {
    /**
     *
     * todo
     * @param p point that the light is directed to
     * @return
     */
    public Color getIntensity(Point3D p);

    /**
     * todo
     * @param p
     * @return
     */
    public Vector getL(Point3D p);

    /** return the distance from light origin to the point
     * @param point
     * @return distance between the lightSource and the point
     */
    double getDistance(Point3D point);
}
