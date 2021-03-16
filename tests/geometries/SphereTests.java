package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphereTests {
    /**
     * Test method for
     * {@link geometries.Sphere#Sphere(Point3D, double)}
     */
    @Test
    void getNormal() {
        Vector v=new Vector(0,-1,0);
        Sphere s=new Sphere(new Point3D(0,0,-1),1);
        assertEquals( v, s.getNormal(new Point3D(0, -1, -1)),"Bad normal to sphere");
    }
}
