package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTests {
    /**
     * Test method for
     * {@link geometries.Plane#Plane(Point3D, Vector)}
     */
    @Test
    void getNormal() {
        Vector v=new Vector(0,0,1);
        Plane pl=new Plane(new Point3D(2,0,0),new Point3D(0,1,0),new Point3D(1,0,0));
        assertEquals( v, pl.getNormal(new Point3D(0, 0, 1)),"Bad normal to plane");
    }


    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane( new Point3D(1, 1, 0),new Vector(0,0,1));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is crosses the plane (1 points)
        List<Point3D> result = plane.findIntersections(new Ray(new Point3D(-1, 0, -1), new Vector(1, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point3D(0,0,0), result.get(0), "Wrong intersection");

        // TC02: The ray is parallel to the plane (0 points)
        result = plane.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 0, 0)));
        assertEquals(null, result, "Ray is parallel to the plane");

        // TC03: Ray is contained in the plane (0 point)
        result = plane.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(null, result, "Ray contained in the plane");

        // TC04: Ray starts after the plane (0 points)
        result = plane.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 1)));
        assertEquals(null, result, "Ray doesn't cross the plane");

        // =============== Boundary Values Tests ==================
        // **** Group: TC11: Ray starts on the plane
        result = plane.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 1)));
        assertEquals(null, result, "Ray doesn't cross the plane");

        // **** Group: Ray's line goes through q0
        //TC12: Ray's line goes through q0 (1 points)
        result = plane.findIntersections(new Ray(new Point3D(0, 0, -1), new Vector(1, 1, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(plane.getQ0(), result.get(0), "Wrong intersection");

        //TC13: Ray's line starts in q0 (0 points)
        result = plane.findIntersections(new Ray(plane.getQ0(), new Vector(1, 1, 1)));
        assertEquals(null, result, "Ray doesn't cross the plane");

        //TC14: Ray's tail goes through q0 (0 points)
        result = plane.findIntersections(new Ray(new Point3D(2, 2, 1), new Vector(1, 1, 1)));
        assertEquals(null, result, "Ray doesn't cross the plane");

        // **** Group: Ray's line is orthogonal to the plane (but not cross q0)
        //TC15: Ray's line is orthogonal to the plane (1 points)
        result = plane.findIntersections(new Ray(new Point3D(-1, 0, -1), plane.getNormal()));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point3D(-1,0,0), result.get(0), "Wrong intersection");

        //TC16: Ray's line is orthogonal and start on the plane (0 points)
        result = plane.findIntersections(new Ray(new Point3D(-1, 0, 0), plane.getNormal()));
        assertEquals(null, result, "Ray doesn't cross the plane");

        //TC17: Ray's tail is orthogonal to the plane (0 points)
        result = plane.findIntersections(new Ray(new Point3D(-1, 0, 1), plane.getNormal()));
        assertEquals(null, result, "Ray doesn't cross the plane");

        // **** Group: Ray's line is orthogonal to the plane (cross q0)
        //TC18: Ray's line is orthogonal to the plane and cross q0 (1 points)
        result = plane.findIntersections(new Ray(new Point3D(1, 1, -1), plane.getNormal()));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(plane.getQ0(), result.get(0), "Wrong intersection");

        //TC19: Ray's line is orthogonal and start in q0 (0 points)
        result = plane.findIntersections(new Ray(new Point3D(1, 1, 0), plane.getNormal()));
        assertEquals(null, result, "Ray doesn't cross the plane");

        //TC20: Ray's tail is orthogonal to the plane and cross q0 (0 points)
        result = plane.findIntersections(new Ray(new Point3D(1, 1, 1), plane.getNormal()));
        assertEquals(null, result, "Ray doesn't cross the plane");

        // **** Group: Ray's line is contained in the plane (cross p0)
        //TC21: Ray's line is contained in the plane and cross q0 (0 points)
        result = plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0,1,0)));
        assertEquals(null, result, "Ray doesn't cross the plane");

        //TC22: Ray's line is contained in the plane and start in q0 (0 points)
        result = plane.findIntersections(new Ray(plane.getQ0(),  new Vector(0,1,0)));
        assertEquals(null, result, "Ray doesn't cross the plane");

        //TC23: Ray is contained in the plane and it's tail crosses q0 (0 points)
        result = plane.findIntersections(new Ray(new Point3D(1, 2, 0),  new Vector(0,1,0)));
        assertEquals(null, result, "Ray doesn't cross the plane");

        // **** Group: TC24: Ray's line is parallel to the plane, ray is orthogonal to ray start on q0 (0 points)
        result = plane.findIntersections(new Ray(new Point3D(1, 1, 1),  new Vector(0,1,0)));
        assertEquals(null, result, "Ray doesn't cross the plane");
    }
}