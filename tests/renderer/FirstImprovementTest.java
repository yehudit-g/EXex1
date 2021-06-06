package renderer;

import elements.*;
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

    @Test
    public void FirstImprovement() {
        Camera camera = new Camera(new Point3D(-500, 40, 10), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
                .setViewPlaneSize(200, 200).setDistance(1000).setFocalPlane(20, 2, 2);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add(
                new Sphere(15, new Point3D(50, 30, -2)) //big
                        .setEmission(new Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(8, new Point3D(10, 5, -15)) //medium
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(6, new Point3D(30, 50, -10)) //small
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(10).setKt(0.6)),
                new Sphere(8, new Point3D(-50, 53, 20)) //medium
                        .setEmission(new Color(270,85,20)) //orange
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(6, new Point3D(15, 0, 30)) //small
                        .setEmission(new Color(java.awt.Color.orange))
                        .setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(30).setKt(0.0)),
                new Sphere(8, new Point3D(0, 20, 35)) //medium
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(6, new Point3D(-15, 70, 11)) //small
                        .setEmission(new Color(java.awt.Color.yellow))
                        .setMaterial(new Material().setKd(0.3).setKs(0.1).setShininess(30).setKt(0.6)),
                new Sphere(8, new Point3D(-50, 30, 25)) //medium
                        .setEmission(new Color(java.awt.Color.magenta))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(6, new Point3D(30, 5, 12)) //small
                        .setEmission(new Color(java.awt.Color.cyan))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),

                new Sphere(15, new Point3D(-5, 80, -10)) //big
                        .setEmission(new Color(java.awt.Color.black))
                        .setMaterial(new Material().setKd(0.15).setKs(0.2).setShininess(10).setKt(0.6)),
                new Sphere(6, new Point3D(-20, 65, 40)) //small
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),

                new Plane(new Point3D(45, -50, -25.005), new Point3D(110, 0, -25), //floor
                        new Point3D(65, 90, -25))
                        .setEmission(new Color(220,220,200))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
        );

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-180, -180, 40), new Vector(10, 30, -0.5))
                .setKl(0.0001).setKq(0.000001));
//        scene.lights.add(new PointLight(new Color(700, 400, 400), new Point3D(-100, -500, 50))
//                .setKl(0.0001).setKq(0.000001));
        scene.lights.add(new PointLight(new Color(700, 400, 400), new Point3D(50, 30, 1000)) //
                .setKl(0.0001).setKq(0.00001));

        ImageWriter imageWriter = new ImageWriter("FirstImprovement", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void improvement1() {
        Camera camera = new Camera(new Point3D(0, 0, -500), new Vector(0, 0, 1), new Vector(1, 0, 0)) //
                .setViewPlaneSize(30, 30).setDistance(450).setFocalPlane(50, 1, 1);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add(
                new Sphere(4, new Point3D(-7, -6, 0))
                        .setEmission(new Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(4, new Point3D(-5, -5, 50))
                        .setEmission(new Color(java.awt.Color.gray))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(7, new Point3D(15, 15, 100))
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(4, new Point3D(5, 5, 75))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(3, new Point3D(0, 0, 150))
                        .setEmission(new Color(java.awt.Color.pink))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0))

        );

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-180, -80, 50), new Vector(10, 30, -0.5))
                .setKl(0.0001).setKq(0.000001));
        scene.lights.add(new DirectionalLight(new Color(700, 400, 400), new Vector(0,10,-5)));

        ImageWriter imageWriter = new ImageWriter("improvement", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage(81);
        render.writeToImage();
    }
}