package renderer;

import elements.*;
import geometries.*;
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
public class MP1depthOfFieldTest {
    private Scene scene = new Scene("Test scene");

    @Test
    public void improvement() {
        Camera camera = new Camera(new Point3D(0, 0, -500), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(30, 30).setDistance(400).setFocalPlane(100, 1, 1);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        Geometries pyramid = new Geometries(
                new Triangle(new Point3D(8, -9, 5), new Point3D(2, -9, 5), new Point3D(4, -11, 0))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)), //base
                new Triangle(new Point3D(4, -11, 0), new Point3D(8, -9, 5), new Point3D(5, 0, 0))
                        .setEmission(new Color(250, 30, 100)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Triangle(new Point3D(8, -9, 5), new Point3D(2, -9, 5), new Point3D(5, 0, 0))
                        .setEmission(new Color(250, 30, 100)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Triangle(new Point3D(2, -9, 5), new Point3D(4, -11, 0), new Point3D(5, 0, 0))
                        .setEmission(new Color(250, 30, 100)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))
        );

        Geometries cube = new Geometries(
                new Polygon(new Point3D(9, -10, 0), new Point3D(12, -12, -10), new Point3D(9, -14, -20), new Point3D(6, -12, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)), //base
                new Polygon(new Point3D(9, -8, 0), new Point3D(12, -10, -10), new Point3D(9, -12, -20), new Point3D(6, -10, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(9, -14, -20), new Point3D(9, -12, -20), new Point3D(12, -10, -10), new Point3D(12, -12, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(9, -8, 0), new Point3D(9, -10, 0), new Point3D(12, -12, -10), new Point3D(12, -10, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(9, -8, 0), new Point3D(9, -10, 0), new Point3D(6, -12, -10), new Point3D(6, -10, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(9, -14, -20), new Point3D(9, -12, -20), new Point3D(6, -10, -10), new Point3D(6, -12, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))
        );

        scene.geometries.add(pyramid, cube,
                new Sphere(5, new Point3D(-10, -10, -20)) //lower right
                        .setEmission(new Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(-7, -7, 0))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(-3, -3, 25))
                        .setEmission(new Color(java.awt.Color.yellow))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(2, 2, 50))
                        .setEmission(new Color(java.awt.Color.gray))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(8, 8, 75))
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(15, 15, 100)) //left up
                        .setEmission(new Color(java.awt.Color.cyan))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),

                new Sphere(3.5, new Point3D(13, -3, 20))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.4)),
                new Triangle(new Point3D(18, -2, 20), new Point3D(11, -3, 25), new Point3D(9, -6, 16))
                        .setEmission(new Color(250, 30, 100)) //cut the ball
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0))
        );

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-70, 100, 0), new Vector(12, -70, 0.5))
                .setKl(0.0001).setKq(0.00001));
        scene.lights.add(new DirectionalLight(new Color(700, 400, 400), new Vector(0, 10, -5)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(8, -9, -10), new Vector(0,-1,0)));
        //scene.lights.add(new SpotLight(new Color(java.awt.Color.white), new Point3D(5, 0, 0), new Vector(0, -1, -0.25)));

        ImageWriter imageWriter = new ImageWriter("improvementDOF", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage(81);
        render.writeToImage();
    }

    @Test
    public void beforeImprovement() {
        Camera camera = new Camera(new Point3D(0, 0, -500), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(30, 30).setDistance(400);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        Geometries pyramid = new Geometries(
                new Triangle(new Point3D(8, -9, 5), new Point3D(2, -9, 5), new Point3D(4, -11, 0))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)), //base
                new Triangle(new Point3D(4, -11, 0), new Point3D(8, -9, 5), new Point3D(5, 0, 0))
                        .setEmission(new Color(250, 30, 100)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Triangle(new Point3D(8, -9, 5), new Point3D(2, -9, 5), new Point3D(5, 0, 0))
                        .setEmission(new Color(250, 30, 100)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Triangle(new Point3D(2, -9, 5), new Point3D(4, -11, 0), new Point3D(5, 0, 0))
                        .setEmission(new Color(250, 30, 100)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))
        );

        Geometries cube = new Geometries(
                new Polygon(new Point3D(9, -10, 0), new Point3D(12, -12, -10), new Point3D(9, -14, -20), new Point3D(6, -12, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)), //base
                new Polygon(new Point3D(9, -8, 0), new Point3D(12, -10, -10), new Point3D(9, -12, -20), new Point3D(6, -10, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(9, -14, -20), new Point3D(9, -12, -20), new Point3D(12, -10, -10), new Point3D(12, -12, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(9, -8, 0), new Point3D(9, -10, 0), new Point3D(12, -12, -10), new Point3D(12, -10, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(9, -8, 0), new Point3D(9, -10, 0), new Point3D(6, -12, -10), new Point3D(6, -10, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(9, -14, -20), new Point3D(9, -12, -20), new Point3D(6, -10, -10), new Point3D(6, -12, -10))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))
        );

        scene.geometries.add(pyramid, cube,
                new Sphere(5, new Point3D(-10, -10, -20)) //lower right
                        .setEmission(new Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(-7, -7, 0))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(-3, -3, 25))
                        .setEmission(new Color(java.awt.Color.yellow))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(2, 2, 50))
                        .setEmission(new Color(java.awt.Color.gray))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(8, 8, 75))
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(5, new Point3D(15, 15, 100)) //left up
                        .setEmission(new Color(java.awt.Color.cyan))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),

                new Sphere(3.5, new Point3D(13, -3, 20))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.4)),
                new Triangle(new Point3D(18, -2, 20), new Point3D(11, -3, 25), new Point3D(9, -6, 16))
                        .setEmission(new Color(250, 30, 100)) //cut the ball
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0))
        );

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-70, 100, 0), new Vector(12, -70, 0.5))
                .setKl(0.0001).setKq(0.00001));
        scene.lights.add(new DirectionalLight(new Color(700, 400, 400), new Vector(0, 10, -5)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(8, -9, -10), new Vector(0,-1,0)));
        //scene.lights.add(new SpotLight(new Color(java.awt.Color.white), new Point3D(5, 0, 0), new Vector(0, -1, -0.25)));

        ImageWriter imageWriter = new ImageWriter("beforeImprovement", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
}