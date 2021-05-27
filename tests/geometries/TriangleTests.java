package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTests {
    /**
     * Test method for
     * {@link geometries.Triangle#Triangle(Point3D, Point3D, Point3D)}
     */
    @Test
    void getNormal() {
        Triangle t = new Triangle(new Point3D(0, 2, 0), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Vector v=new Vector(0,0,-1);
        assertEquals( v, t.getNormal(new Point3D(0, 1, 0)),"Bad normal to triangle");
    }
    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(new Point3D(1, 0, 0),new Point3D(-1, 0, 0),new Point3D(0, 2, 0));
        Vector v = new Vector(0, 0, 1);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line crosses inside the triangle (1 points)
        List<Point3D> result = triangle.findIntersections(new Ray(new Point3D(0, 1, -1), v));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point3D(0,1,0), result.get(0), "Wrong intersection");

        // TC02: Ray crosses outside the triangle, against edge (0 points)
        result = triangle.findIntersections(new Ray(new Point3D(1, 1, -1), v));
        assertEquals(null, result, "Ray doesn't cross the triangle");

        // TC03: Ray crosses outside the triangle, against vertex (0 points)
        result = triangle.findIntersections(new Ray(new Point3D(0, 3, -1), v));
        assertEquals(null, result, "Ray doesn't cross the triangle");

        // =============== Boundary Values Tests ==================
        //TC11: Ray intersects on edge(0 points)
        result = triangle.findIntersections(new Ray(new Point3D(0.5, 0, -1), v));
        assertEquals(null, result, "Ray doesn't cross the triangle");

        //TC12: Ray crosses in vertex(0 points)
        result = triangle.findIntersections(new Ray(new Point3D(0, 2, -1), v));
        assertEquals(null, result, "Ray doesn't cross the triangle");

        //TC13: Ray intersects on edge's continuation(0 points)
        result = triangle.findIntersections(new Ray(new Point3D(2, 0, -1), v));
        assertEquals(null, result, "Ray doesn't cross the triangle");
    }
}
