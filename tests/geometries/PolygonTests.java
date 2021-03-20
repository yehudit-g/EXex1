package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PolygonTests {
    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertex on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertices on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 1, 0), new Point3D(1, 0, 0), new Point3D(2, 2, 0));
        //double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(0, 0, 1), pl.getNormal(new Point3D(0.75, 0.75, 0)), "Bad normal to polygon");

    }

    /**
     * Test method for {@link geometries.Polygon#isInside(Ray)} (primitives.Point3D)}.
     */
    @Test
    public void testIsInside(){
        Polygon pl = new Polygon(new Point3D(1, 0, 0),new Point3D(-1, 0, 0),new Point3D(0, 2, 0));
        Vector v = new Vector(0, 0, 1);
        assertEquals(false, pl.isInside(new Ray(new Point3D(1, 1, -1), v)),"Point is not in polygon");
        assertEquals(true, pl.isInside(new Ray(new Point3D(0, 1, -1), v)),"Point is in polygon");
        //point on vertex
        assertEquals(false, pl.isInside(new Ray(new Point3D(0, 2, -1), v)),"Point is not in polygon");
        //point on edge
        assertEquals(false, pl.isInside(new Ray(new Point3D(0.5, 0, -1), v)),"Point is not in polygon");
    }

}