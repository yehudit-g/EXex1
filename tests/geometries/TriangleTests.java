package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

public class TriangleTests {
    @Test
    void getNormal() {
        Triangle t = new Triangle(new Point3D(0, 2, 0), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Vector v=new Vector(0,0,-1);
        assertEquals( v, t.getNormal(new Point3D(0, 1, 0)),"Bad normal to triangle");
    }
}
