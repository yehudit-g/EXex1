package elements;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        Ray ray;
        int counter=0;
        camera.setViewPlaneSize(3, 3).setDistance(1);
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                ray=camera.constructRayThroughPixel(3, 3, j, i);
                List<Point3D> intersections=body.findIntersections(ray);
                if(intersections!=null)
                    counter+=intersections.size();
            }
        }
        assertEquals(expected, counter, "Wrong number of intersections");
    }

    Camera camera1 =new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0,1,0));
    Camera camera2 =new Camera(new Point3D(0,0,0.5), new Vector(0, 0, -1), new Vector(0,1,0));

    /**
     * Integration test of camera ray intersections with sphere
     */
    @Test
    public  void CameraRaySphereIntegration(){

        //TC01: Small sphere (2 points)
        Sphere sphere=new Sphere(1, new Point3D(0,0,-3));
        countIntersections(camera1, sphere, 2);

        //TC02: Big sphere (18 points)
        sphere=new Sphere(2.5, new Point3D(0,0,-2.5));
        countIntersections(camera2, sphere, 18);

        //TC03: Medium sphere (10 points)
        sphere=new Sphere(2, new Point3D(0,0,-2));
        countIntersections(camera2, sphere, 10);

        //TC04: Inside sphere (9 points)
        sphere=new Sphere(4, new Point3D(0,0,-1));
        countIntersections(camera1, sphere, 9);

        // TC05: Beyond sphere (0 points)
        sphere=new Sphere(0.5, new Point3D(0,0,1));
        countIntersections(camera1, sphere, 0);

    }

    /**
     * Integration test of camera ray intersections with triangle
     */
    @Test
    public  void CameraRayTriangleIntegration(){
        Camera camera =new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0,-1,0));

        //TC01: Small triangle (1 points)
        Triangle triangle=new Triangle(new Point3D(0,1,-2), new Point3D(1,-1,-2), new Point3D(-1,-1,-2));
        countIntersections(camera1, triangle, 1);

        //TC02: Medium triangle (2 points)
        triangle=new Triangle(new Point3D(0,20,-2), new Point3D(1,-1,-2), new Point3D(-1,-1,-2));
        countIntersections(camera1, triangle, 2);
    }

    /**
     * Integration test of camera ray intersections with plane
     */
    @Test
    public  void CameraRayPlaneIntegration(){
        //TC01: plane against camera (9 points)
        Plane plane=new Plane(new Point3D(0,1,-2), new Vector(0, 0, -1));
        countIntersections(camera1, plane, 9);

        //TC02: plane with small angle (9 points)
        plane=new Plane(new Point3D(1,1,-2), new Point3D(0,0,-2.5), new Point3D(0,-1,-3));
        countIntersections(camera1, plane, 9);

        //TC03: plane parallel to lower rays (6 points)
        plane=new Plane(new Point3D(0,0,-5), new Vector(0, -1, -1));
        countIntersections(camera1, plane, 6);

        // TC04: Beyond plane (0 points)
        plane=new Plane(new Point3D(0,1,2), new Vector(0, 0, -1));
        countIntersections(camera1, plane, 0);

    }

}
