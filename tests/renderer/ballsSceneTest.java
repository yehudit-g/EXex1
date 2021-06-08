package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

public class ballsSceneTest {
    private Scene scene = new Scene("Test scene");

    @Test
    public void balls() {
        Camera camera = new Camera(new Point3D(-500, 40, 10), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
                .setViewPlaneSize(200, 200).setDistance(1000);//.setFocalPlane(200, 2, 2);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add(
                new Sphere(30, new Point3D(50, 30, -20)) //big
                        .setEmission(new Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),
                new Sphere(16, new Point3D(10, 5, -150)) //medium
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(12, new Point3D(30, 50, -100)) //small
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(10).setKt(0.6)),
                new Sphere(16, new Point3D(-50, 53, 200)) //medium
                        .setEmission(new Color(270, 85, 20)) //orange
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(12, new Point3D(15, 0, 300)) //small
                        .setEmission(new Color(java.awt.Color.orange))
                        .setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(30).setKt(0.0)),
                new Sphere(16, new Point3D(0, 20, 350)) //medium
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(12, new Point3D(-15, 70, 110)) //small
                        .setEmission(new Color(java.awt.Color.yellow))
                        .setMaterial(new Material().setKd(0.3).setKs(0.1).setShininess(30).setKt(0.6)),
                new Sphere(16, new Point3D(-50, 30, 250)) //medium
                        .setEmission(new Color(java.awt.Color.magenta))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(12, new Point3D(30, 5, 120)) //small
                        .setEmission(new Color(java.awt.Color.cyan))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),

                new Sphere(30, new Point3D(-5, 80, -100)) //big
                        .setEmission(new Color(java.awt.Color.black))
                        .setMaterial(new Material().setKd(0.15).setKs(0.2).setShininess(10).setKt(0.6)),
                new Sphere(12, new Point3D(-20, 65, 400)) //small
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(50).setKt(0.0)),

                new Plane(new Point3D(45, -50, -25.005), new Point3D(110, 0, -25), //floor
                        new Point3D(65, 90, -25))
                        .setEmission(new Color(220, 220, 200))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
        );

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-180, -180, 40), new Vector(10, 30, -0.5))
                .setKl(0.0001).setKq(0.000001));
//        scene.lights.add(new PointLight(new Color(700, 400, 400), new Point3D(-100, -500, 50))
//                .setKl(0.0001).setKq(0.000001));
        scene.lights.add(new PointLight(new Color(700, 400, 400), new Point3D(50, 30, 1000)) //
                .setKl(0.0001).setKq(0.00001));

        ImageWriter imageWriter = new ImageWriter("balls", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

}
