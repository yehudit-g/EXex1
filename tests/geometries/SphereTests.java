package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphereTests {
    /**
     * Test method for
     * {@link Sphere#Sphere(double, Point3D)}
     */
    @Test
    void getNormal() {
        Vector v=new Vector(0,-1,0);
        Sphere s=new Sphere(1, new Point3D(0,0,-1));
        assertEquals( v, s.getNormal(new Point3D(0, -1, -1)),"Bad normal to sphere");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));
    //The messages tell the user what needs to be
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals(null, sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.06515307716504659, 0.35505102572168223, 0);
        Point3D p2 = new Point3D(1.5348469228349528, 0.8449489742783177, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Wrong intersections");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0.5, 0), new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point3D(1,1,0), result.get(0), "Wrong intersection");

        // TC04: Ray starts after the sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(3, 1, 0)));
        assertEquals(null, result, "Ray doesn't cross sphere");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        double temp=Math.sqrt(2);
        Vector v=new Vector(1, -1, 0);

        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 1,0), v));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point3D(1.9999999999999998,0,0), result.get(0), "Wrong intersection"); //1.9999999999999998

        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(2, 0,0), v));
        assertEquals(null, result, "Ray doesn't cross the sphere");

        // **** Group: Ray's line goes through the center
        v=new Vector(0,-1,0);
        p1=new Point3D(1,1,0);
        p2=new Point3D(1,-1,0);
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 2,0), v));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Wrong intersections");

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 1,0), v));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0), "Wrong intersection");

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0.5,0), v));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0), "Wrong intersection");

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0,0), v));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0), "Wrong intersection");

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, -0.5,0), v));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0), "Wrong intersection");

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, -2,0), v));
        assertEquals(null, result, "Ray doesn't cross sphere");


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        v=new Vector(1,0,0);

        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(0, 1,0), v));
        assertEquals(null, result, "Ray doesn't cross sphere");

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(1, 1,0), v));
        assertEquals(null, result, "Ray doesn't cross sphere");

        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(2, 1,0), v));
        assertEquals(null, result, "Ray doesn't cross sphere");


        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point3D(1, 2,0), v));
        assertEquals(null, result, "Ray doesn't cross sphere");

    }

}
