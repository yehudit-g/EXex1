package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTests {
    @Test
    void getNormal() {
        Vector v=new Vector(0,0,1);
        Plane pl=new Plane(new Point3D(2,0,0),new Point3D(0,1,0),new Point3D(1,0,0));
        assertEquals( v, pl.getNormal(new Point3D(0, 0, 1)),"Bad normal to plane");
    }

}