package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TubeTests {
    @Test
    void getNormal() {
        Vector v=new Vector(1,0,0);
        Tube t=new Tube(new Ray(new Point3D(0,0,1),new Vector(0,0,1)),1);
        assertEquals( v, t.getNormal(new Point3D(1, 0, 10)),"Bad normal to tube");
    }
}
