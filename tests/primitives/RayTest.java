package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class RayTest {

    /**
     * Test method for
     * {@link primitives.Ray#findClosestPoint(List<Point3D>)}
     */
    @Test
    void findClosestPoint() {
        Ray ray=new Ray(Point3D.ZERO, new Vector(1, 0, 0));
        Point3D p1= new Point3D(2,0,0);
        Point3D p2= new Point3D(1.5, 0,0);
        Point3D p3= new Point3D(3, 0,0);
        List<Point3D> point3DList=List.of(p1, p2, p3);

        // ============ Equivalence Partitions Tests ==============
        //TC01: The closest point is in the middle of the list
        assertEquals(ray.findClosestPoint(point3DList), p2, "Wrong point");

        // =============== Boundary Values Tests ==================
        //TC11: There are no points in the list
        assertNull(ray.findClosestPoint(null));

        //TC12: The closest point is the first in the list
        point3DList=List.of(p2, p1, p3);
        assertEquals(ray.findClosestPoint(point3DList), p2, "Wrong point");

        //TC13: The closest point is the last in the list
        point3DList=List.of(p1, p3, p2);
        assertEquals(ray.findClosestPoint(point3DList), p2, "Wrong point");
    }
}