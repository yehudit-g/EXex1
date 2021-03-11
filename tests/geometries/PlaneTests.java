package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTests {
    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        //check  the c-tor which create the normal
        Vector v=new Vector(0,0,1);
        //Plane pl1=new Plane(new Point3D(1,0,0),v);
        Plane pl2=new Plane(new Point3D(2,0,0),new Point3D(0,1,0),new Point3D(1,0,0));
        //assertEquals( v, pl1.getNormal(new Point3D(0, 0, 1)),"Bad normal to plane from point & vector");
        assertEquals( v, pl2.getNormal(new Point3D(0, 0, 1)),"Bad normal to plane from 3 points");
        // =============== Boundary Values Tests ==================
        //check point that does not exist on the plane
        try {
            pl2.getNormal(new Point3D(0,0,2));
            fail("Error. The point is not on the plane");
        } catch (Exception e) {}

    }

}