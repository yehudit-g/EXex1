package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Polygon;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.util.Random;

public class Final {
    private Scene scene = new Scene("Test scene");

    @Test
    public void FinalPic() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1000), new Vector(0, 1, 0)) //
                .setViewPlaneSize(30, 30).setDistance(850).setFocalPlane(250, 1.2, 1.2);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.background = new Color(2, 12, 90);
        Material mat = new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.4).setKr(0.1);

        scene.geometries.add(
                new Sphere(10, new Point3D(-3, -3, -250))//center mount
                        .setEmission(new Color(20, 150, 0)),
                new Sphere(13, new Point3D(-20, 0, -300))//left mount
                        .setEmission(new Color(140, 102, 45)),
                new Sphere(15, new Point3D(20, 0, -300))//right mount
                        .setEmission(new Color(100, 75, 30)),
                new Sphere(3.2, new Point3D(20, 20, -250))//moon
                        .setEmission(new Color(180, 200, 250))
                        .setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(50)),
                new Polygon(new Point3D(-30, -3, -250), new Point3D(30, -3, -250),//floor
                        new Point3D(30, -20, 0), new Point3D(-30, -20, 0))
                        .setEmission(new Color(10, 10, 10)),
                //grapes:
                new Sphere(1.5, new Point3D(2.7, -3, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.7, new Point3D(6.2, -3, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.4, new Point3D(9.5, -3, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.5, new Point3D(12.5, -2.8, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),

                new Sphere(1.4, new Point3D(2.6, -6.3, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.6, new Point3D(4.2, -4.5, -100))//little
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1, new Point3D(5.3, -6.2, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.4, new Point3D(8, -5.9, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.5, new Point3D(11.2, -5.8, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),

                new Sphere(0.85, new Point3D(1.5, -8.9, -95))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.2, new Point3D(3.8, -8.7, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.4, new Point3D(6.45, -8.4, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.5, new Point3D(9.5, -8.45, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),

                new Sphere(0.6, new Point3D(-2.6, -11.6, -80))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.8, new Point3D(-1.2, -11, -80))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.1, new Point3D(0.8, -10.8, -85))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1, new Point3D(3, -10.8, -95))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.6, new Point3D(5, -10.2, -95)) //little
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.2, new Point3D(7, -11, -95))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
//                new Sphere(0.6, new Point3D(7.7, -9.8, -95)) //little
//                        .setEmission(new Color(java.awt.Color.blue))
//                        .setMaterial(mat),
//                new Sphere(1.1, new Point3D(8.7, -11.3, -95))
//                        .setEmission(new Color(java.awt.Color.blue))
//                        .setMaterial(mat),

                new Sphere(0.6, new Point3D(4.8, -11.8, -90)) //little
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.1, new Point3D(3.2, -12.7, -90))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.8, new Point3D(6, -12.7, -90))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.8, new Point3D(1, -12.85, -85))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.8, new Point3D(-1, -12.7, -80))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.6, new Point3D(-3.2, -13, -80))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat)

        );
        //stars:
        Material starMat = new Material().setKd(0.2).setKs(0.2).setShininess(50);
        //x between -30,30. y between 15,25.
        for (int i = 0; i < 20; i++) {
            scene.geometries.add(new Sphere(0.9, new Point3D(Math.random()*60-30, Math.random()*13+15, -350))
                    .setEmission(new Color(java.awt.Color.orange))
                    .setMaterial(starMat));
        }

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-70, 100, 0), new Vector(12, -70, 0.5))
                .setKl(0.0001).setKq(0.00001));
        scene.lights.add(new DirectionalLight(new Color(700, 400, 400), new Vector(0, 10, -5)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(8, -9, -10), new Vector(0, -1, 0)));
        //scene.lights.add(new SpotLight(new Color(java.awt.Color.white), new Point3D(5, 0, 0), new Vector(0, -1, -0.25)));

        ImageWriter imageWriter = new ImageWriter("Tova Haaretz", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
                .setMultithreading(3) //fot threading improvement
                .setDebugPrint()
                .setRayTracer(new BasicRayTracer(scene.turnOnUsingBVH())); //BVH
        render.renderImage(81);
        render.writeToImage();
    }
}
