package elements;

import geometries.Intersectable;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration test of camera ray intersections with 3D body
 */
public class CameraRayIntersectionsIntegrationTests {
    /***
     * Helping func to compare number of intersections with the expected one
     * @param camera for the test
     * @param body 3D geometry for the test
     * @param expected intersections number
     */
    private void countIntersections(Camera camera, Intersectable body, int expected) {
        int count = 0;
        camera.setViewPlaneSize(3, 3).setDistance(1);
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                var intersections = body.findIntersections(camera.constructRayThroughPixel(3, 3, j, i));
                count += intersections == null ? 0 : intersections.size();
            }
        assertEquals(expected, count, "Wrong amount of intersections");
    }

    /**
     * Integration test of camera ray intersections with sphere
     */
    @Test
    public  void CameraRaySphereIntegration(){
        Camera camera1 =new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0,-1,0));
        Camera camera2 =new Camera(new Point3D(0,0,0.5), new Vector(0, 0, -1), new Vector(0,-1,0));

        //TC01: Small sphere (2 points)
        //TC02: Big sphere (18 points)
        //TC03: Medium sphere (10 points)
        //TC04: Inside sphere (9 points)
        // TC05: Beyond sphere (0 points)

    }

    /**
     * Integration test of camera ray intersections with triangle
     */
    @Test
    public  void CameraRayTriangleIntegration(){
        Camera camera =new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0,-1,0));

        //TC01: Small triangle (1 points)
        //TC02: Medium triangle (2 points)
    }

    /**
     * Integration test of camera ray intersections with plane
     */
    @Test
    public  void CameraRayPlaneIntegration(){
        Camera camera =new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0,-1,0));

        //TC01: plane against camera (9 points)
        //TC02: plane with small angle (9 points)
        //TC03: plane parallel to lower rays (6 points)
        // TC04: Beyond plane (0 points)
    }

}
