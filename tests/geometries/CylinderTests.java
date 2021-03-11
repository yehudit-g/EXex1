package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CylinderTests {
    @Test
    void getNormal() {
        Cylinder c=new Cylinder(new Ray(new Point3D(0,0,0),new Vector(0,0,1)),1,5);
        // ============ Equivalence Partitions Tests ==============
        //EP1: point on the shell
        assertEquals( new Vector(1,0,0), c.getNormal(new Point3D(1, 0, 1)),"Bad normal to cylinder's shell");
        //EP2: point on the bottom base
        assertEquals( new Vector(0,0,-1), c.getNormal(new Point3D(0, 0, 0)),"Bad normal to cylinder's bottom base");
        //EP3: point on the top base
        assertEquals( new Vector(0,0,1), c.getNormal(new Point3D(0, 0, 5)),"Bad normal to cylinder's top base");

        // =============== Boundary Values Tests ==================
        //BVA1: point on the round of the bottom base
        assertEquals( new Vector(1,0,0), c.getNormal(new Point3D(1, 0, 0)),"Bad normal to cylinder's bottom base's round");
        //BVA2: point on the round of the top base
        assertEquals( new Vector(1,0,0), c.getNormal(new Point3D(1, 0, 5)),"Bad normal to cylinder's top base's round");

    }
}
