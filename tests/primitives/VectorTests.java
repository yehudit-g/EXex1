package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * JUnit5 tests for primitives.Vector
 * @author Yehudit Gutman & Shira Orbach
 */
class VectorTests {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    Point3D p1 = new Point3D(1, 2, 3);

    @Test
    void subtract() {
        if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)))
            fail("ERROR: Point - Point does not work correctly");
    }

    @Test
    void add() {
        if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
            fail("ERROR: Point + Vector does not work correctly");
    }

    @Test
    void dotProduct() {
        if (!isZero(v1.dotProduct(v3)))
            fail("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            fail("ERROR: dotProduct() wrong value");
    }

    @Test
    void crossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals( v1.length() * v3.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue( isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-product of co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

    }

    @Test
    void lengthSquared() {
        if (!isZero(v1.lengthSquared() - 14))
        fail("ERROR: lengthSquared() wrong value");

    }

    @Test
    void length() {
        if (!isZero(new Vector(0, 3, 4).length() - 5))
          fail("ERROR: length() wrong value");
    }

    @Test
    void normalize() {
        Vector vCopy = new Vector(v1.getHead());
        Vector vCopyNormalize = vCopy.normalize();

        //check if created a unit vector
        if (!isZero(vCopyNormalize.length() - 1))
            fail("ERROR: normalize() result is not a unit vector");
        //check if the current vector changed
        if (vCopy != vCopyNormalize)
            fail("ERROR: normalize() function creates a new vector");
    }

    @Test
    void normalized() {
        Vector u = v1.normalized();
        if (u == v1)
            fail("ERROR: normalized() function does not create a new vector");
    }
}