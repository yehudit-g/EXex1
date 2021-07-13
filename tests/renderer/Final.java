package renderer;

import elements.*;
import geometries.Geometries;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
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
                .setViewPlaneSize(30, 30).setDistance(850).setFocalPlane(250, 1, 1);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.background = new Color(2, 12, 90);
        Material mat = new Material().setKd(0.2).setKs(0.2).setShininess(20).setKt(0.3).setKr(0.1);
        Color wineButtle = new Color(50, 0, 0);
        Material buttleMat = new Material().setKd(0.2).setKs(0.4).setShininess(30).setKt(0.1).setKr(0.1);


        scene.geometries.add(
                new Sphere(10, new Point3D(-3, -3, -250))//center mount
                        .setEmission(new Color(20, 150, 0)),
                new Sphere(13, new Point3D(-20, 0, -300))//left mount
                        .setEmission(new Color(140, 102, 45)),
                new Sphere(15, new Point3D(20, 0, -300))//right mount
                        .setEmission(new Color(100, 75, 30)),
                new Sphere(3.2, new Point3D(20, 23, -350))//moon
                        .setEmission(new Color(180, 200, 200))
                        .setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(50).setKt(0.8)),
                new Polygon(new Point3D(-30, -3, -250), new Point3D(30, -3, -250),//floor
                        new Point3D(30, -20, 0), new Point3D(-30, -20, 0))
                        .setEmission(new Color(148, 153, 107)),
                //===
                new Polygon(new Point3D(0.8, -1.5, -100), new Point3D(0.9, -1, -100),
                        new Point3D(8.4, -0.2, -100), new Point3D(8.3, -0.7, -100))
                        .setEmission(new Color(140, 102, 45))//branch left
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0.3)),

                new Polygon(new Point3D(14.4, -1.5, -99), new Point3D(14.5, -1, -99),
                        new Point3D(8.2, -0.2, -99), new Point3D(8.1, -0.7, -99))
                        .setEmission(new Color(140, 102, 45))//branch right
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0.3)),

                new Polygon(new Point3D(8.8, 2, -99), new Point3D(9.2, 2, -99),
                        new Point3D(8.4, -0.3, -99), new Point3D(8, -0.3, -99))
                        .setEmission(new Color(140, 102, 45))//branch up
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0.3)),

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
                        .setMaterial(mat),


                new Sphere(0.45, new Point3D(-11, -12.4, -80))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.5, new Point3D(-12.45, -11.5, -80))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.6, new Point3D(-12.5, -12.7, -80))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(0.6, new Point3D(-9.5, -12.5, -78))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),

                //wine battle
                new Polygon(new Point3D(-11, -12, -90), new Point3D(-10, -11, -91),
                        new Point3D(-6, -11, -91), new Point3D(-7, -12, -90))
                        .setEmission(wineButtle)//wine battle-floor
                        .setMaterial(buttleMat),

                new Polygon(new Point3D(-11, -6, -90), new Point3D(-7, -6, -90),
                        new Point3D(-7, -12, -90), new Point3D(-11, -12, -90))
                        .setEmission(wineButtle)//wine battle-front
                        .setMaterial(buttleMat),//(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.7)),

                new Polygon(new Point3D(-10, -5, -91), new Point3D(-6, -5, -91),
                        new Point3D(-6, -11, -91), new Point3D(-10, -11, -91))
                        .setEmission(wineButtle)//wine battle-back
                        .setMaterial(buttleMat),

//                new Polygon(new Point3D(-10, -5, -90), new Point3D(-6, -5, -90),
//                        new Point3D(-7, -6, -90), new Point3D(-11, -6, -90))
//                        .setEmission(new Color(85,0,0))//wine battle-up
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.8)),

                new Polygon(new Point3D(-6, -5, -91), new Point3D(-7, -6, -90),
                        new Point3D(-7, -12, -90), new Point3D(-6, -11, -91))
                        .setEmission(wineButtle)//wine battle-right
                        .setMaterial(buttleMat),

                new Polygon(new Point3D(-11, -6, -90), new Point3D(-10, -5, -91),
                        new Point3D(-10, -11, -91), new Point3D(-11, -12, -90))
                        .setEmission(wineButtle)//wine battle-left
                        .setMaterial(buttleMat),

                new Polygon(new Point3D(-9.25, -5, -91), new Point3D(-8.25, -4, -91),
                        new Point3D(-10, -5, -90), new Point3D(-11, -6, -90))
                        .setEmission(wineButtle)//wine battle-left triangle
                        .setMaterial(buttleMat),

                new Polygon(new Point3D(-7, -6, -90), new Point3D(-8.75, -5, -90),
                        new Point3D(-7.75, -4, -91), new Point3D(-6, -5, -91))
                        .setEmission(wineButtle)//wine battle-right triangle
                        .setMaterial(buttleMat),

                new Polygon(new Point3D(-10, -5, -91), new Point3D(-6, -5, -91),
                        new Point3D(-7.75, -4, -91), new Point3D(-8.25, -4, -91))
                        .setEmission(wineButtle)//wine battle-back triangle
                        .setMaterial(buttleMat),

                new Polygon(new Point3D(-11, -6, -90), new Point3D(-9.25, -5, -90),
                        new Point3D(-8.75, -5, -90), new Point3D(-7, -6, -90))
                        .setEmission(wineButtle)//wine battle-front triangle
                        .setMaterial(buttleMat),
                //neck:
                new Polygon(new Point3D(-9.25, -5, -90), new Point3D(-8.75, -5, -90),
                        new Point3D(-8.5, -1, -90), new Point3D(-9.5, -1, -90))
                        .setEmission(new Color(100, 0, 10))//wine battle-front Bottleneck
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0.8)),

                new Polygon(new Point3D(-8.25, -4, -91), new Point3D(-7.75, -4, -91),
                        new Point3D(-7.5, 0, -91), new Point3D(-8.5, 0, -91))
                        .setEmission(new Color(100, 0, 10))//wine battle-back Bottleneck
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0.8)),

                new Polygon(new Point3D(-9.25, -5, -90), new Point3D(-8.25, -4, -91),
                        new Point3D(-8.5, 0, -91), new Point3D(-9.5, -1, -90))
                        .setEmission(new Color(100, 0, 10))//wine battle-left Bottleneck
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0.8)),

                new Polygon(new Point3D(-7.75, -4, -91), new Point3D(-8.75, -5, -90),
                        new Point3D(-8.5, -1, -90), new Point3D(-7.5, 0, -91))
                        .setEmission(new Color(100, 0, 10))//wine battle-right Bottleneck
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0.8)),

                //label
                new Polygon(new Point3D(-10, -10, -80), new Point3D(-7, -10, -80),
                        new Point3D(-7, -8, -80), new Point3D(-10, -8, -80))
                        .setEmission(new Color(10, 10, 10)),//wine battle-label
                // .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.8)),

                new Triangle(new Point3D(-7, -8, -79.9), new Point3D(-7, -10, -79.9), new Point3D(-8, -9, -79.9))
                        .setEmission(new Color(214,193,255))
        );
        //stars:
        Material starMat = new Material().setKd(0.2).setKs(0.2).setShininess(50);
        //x between -30,30. y between 9,25.
        for (int i = 0; i < 20; i++) {
            scene.geometries.add(new Sphere(0.9, new Point3D(Math.random() * 60 - 30, Math.random() * 16 + 9, -350))
                    .setEmission(new Color(java.awt.Color.orange))
                    .setMaterial(starMat));
        }

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-70, 100, 250), new Vector(60, -100, -250))
                .setKl(0.0001).setKq(0.0001));
        scene.lights.add(new DirectionalLight(new Color(700, 400, 400), new Vector(-20, -20, -6)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(0, 6, -260), new Vector(0, -50, 200)).setKl(0.0001).setKq(0.0001));
//        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(30, 5, -300), new Vector(-1, 0, 0)));

        scene.lights.add(new PointLight(new Color(java.awt.Color.white), new Point3D(0, 800, 800)));


        ImageWriter imageWriter = new ImageWriter("Tova Haaretz", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
               // .setMultithreading(3) //for threading improvement
               // .setDebugPrint()
                .setRayTracer(new BasicRayTracer(scene)); //BVH
        render.renderImage(81);
        render.writeToImage();
    }
}
