package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

/**
 * The test create a scene to present our first picture improvement:
 * Improving ray tracing - depth of field
 */
public class FirstImprovementTest {
    private Scene scene = new Scene("Test scene");
    @Test
    public void effectsPresentation() {
        Camera camera = new Camera(new Point3D(-500, 40, 10), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
                .setViewPlaneSize(200, 200).setDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

//        Geometries pyramid = new Geometries(
//                new Polygon(new Point3D(100, 0, -10), new Point3D(80, 0, -10), new Point3D(80, 20, -10), new Point3D(100, 20, -10))
//                        .setEmission(new Color(java.awt.Color.BLUE)) //
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)),
//                new Triangle(new Point3D(100, 0, -10), new Point3D(100, 20, -10), new Point3D(90, 10, 30))
//                        .setEmission(new Color(java.awt.Color.PINK)) //
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)),
//                new Triangle(new Point3D(100, 0, -10), new Point3D(80, 0, -10), new Point3D(90, 10, 30))
//                        .setEmission(new Color(java.awt.Color.BLUE)) //
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)),
//                new Triangle(new Point3D(100, 20, -10), new Point3D(80, 20, -10), new Point3D(90, 10, 30))
//                        .setEmission(new Color(java.awt.Color.PINK)) //
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)),
//                new Triangle(new Point3D(80, 20, -10), new Point3D(80, 0, -10), new Point3D(90, 10, 30))
//                        .setEmission(new Color(java.awt.Color.BLUE)) //
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)));

        scene.geometries.add(
                new Sphere(15, new Point3D(50, 30, -2)) //big
                        .setEmission(new Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(8, new Point3D(10, 13, -8)) //medium
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(6, new Point3D(30, 40, -10)) //small
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),

//                new Polygon(new Point3D(110, 0, 60), new Point3D(110, 0, -20), //mirror
//                        new Point3D(65, 90, -20), new Point3D(65, 90, 80))
//                        .setEmission(new Color(java.awt.Color.gray))
//                        .setMaterial(new Material().setKr(0.6))
//                ,new Polygon(new Point3D(45, -50, -30), new Point3D(110, 0, -20), //floor
//                        new Point3D(65, 90, -20), new Point3D(0, 40, -30))
//                        .setEmission(new Color(java.awt.Color.lightGray))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                new Plane(new Point3D(45, -50, -25.005), new Point3D(110, 0, -25),
                        new Point3D(65, 90, -25)) //floor
                        .setEmission(new Color(java.awt.Color.lightGray))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
        );

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-180, -80, 50), new Vector(10, 30, -0.5))
                .setKl(0.0001).setKq(0.000001));
//        scene.lights.add(new PointLight(new Color(700, 400, 400), new Point3D(50, 30, 800)) //
//                .setKl(0.0001).setKq(0.000001));

        ImageWriter imageWriter = new ImageWriter("FirstImprovement", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
}
