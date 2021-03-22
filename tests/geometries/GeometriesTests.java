package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeometriesTests {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane( new Point3D(1, 1, 1),new Vector(-1,0,1));
        Sphere sphere = new Sphere( new Point3D(4, 5, 6),1d);
        Triangle triangle = new Triangle(new Point3D(1, 0, 2),new Point3D(1, 10, 2),new Point3D(1, 5, 10));

        Geometries geometries = new Geometries(plane, sphere, triangle);
        Geometries geometriesEmpty = new Geometries();

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line crosses many objects but not everyone (2 points)
        List<Point3D> result = geometries.findIntersections(new Ray(new Point3D(0, 5, 3), new Vector(1, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");

        // =============== Boundary Values Tests ==================

        // **** Group: Empty collection
        //TC11: Ray's line doesn't cross any object (0 points)
        result = geometriesEmpty.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 0, 0)));
        assertEquals(null, result, "Wrong number of points");

        // **** Group: BVA of not empty collection
        //TC12: Ray's line doesn't cross any object (0 points)
        result = geometries.findIntersections(new Ray(new Point3D(0, 0, 20), new Vector(1, 0, 1)));
        assertEquals(null, result, "Wrong number of points");

        //TC13: Ray's line crosses one object (1 points)
        result = geometries.findIntersections(new Ray(new Point3D(0, 0, 20), new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");

        //TC14: Ray's line crosses all the objects (4 points)
        result = geometries.findIntersections(new Ray(new Point3D(0, 5, 6), new Vector(1, 0, 0)));
        assertEquals(4, result.size(), "Wrong number of points");
    }
}