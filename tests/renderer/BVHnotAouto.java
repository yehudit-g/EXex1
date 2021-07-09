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

public class BVHnotAouto {
    private Scene scene = new Scene("Test scene");
@Test
    public void BVHpic() {
        Camera camera = new Camera(new Point3D(-300, 0, 1000), new Vector(300, 0, -1000), new Vector(0, 1, 0)) //
                .setViewPlaneSize(30, 30).setDistance(350);//.setFocalPlane(200, 1, 1);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.background = Color.BLACK;

        scene.geometries.add(
                new Geometries(
                        new Polygon(new Point3D(35, -15, -30), new Point3D(35, -40, 50),
                                new Point3D(-40, -40, 50), new Point3D(-40, -15, -30))
                                .setEmission(new Color(java.awt.Color.blue)) //floor
                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),
                        new Geometries(
                                new Geometries(
                                        new Polygon(new Point3D(-28, -10, -30), new Point3D(-28, -15, -28),
                                                new Point3D(-38, -15, -28), new Point3D(-38, -10, -30))
                                                .setEmission(new Color(java.awt.Color.red)) //cube1floor
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

//                new Polygon(new Point3D(-28,-5, -30), new Point3D(-28, -10, -28),
//                        new Point3D(-38, -10, -28),  new Point3D(-38, -5, -30))
//                        .setEmission(new Color(java.awt.Color.gray)) //cube1up
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),

                                        new Polygon(new Point3D(-28, -5, -30), new Point3D(-28, -10, -30),
                                                new Point3D(-38, -15, -28), new Point3D(-38, -10, -28))
                                                .setEmission(new Color(java.awt.Color.red)) //cube1-triangle up
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-28, -5, -30), new Point3D(-28, -10, -30),
                                                new Point3D(-28, -15, -28), new Point3D(-28, -10, -28))
                                                .setEmission(new Color(java.awt.Color.red)) //cube1right
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-38, -15, -28),
                                                new Point3D(-28, -15, -28), new Point3D(-28, -10, -28))
                                                .setEmission(new Color(java.awt.Color.red)) //cube1-triangle-front
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-38, -10, -30),
                                                new Point3D(-28, -5, -30), new Point3D(-28, -10, -30))
                                                .setEmission(new Color(java.awt.Color.red)) //cube1-triangle-back
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox()
                                ).turnOnBVH().setBox(),
                                new Geometries(
                                        new Polygon(new Point3D(-25, -10, -30), new Point3D(-25, -15, -28),
                                                new Point3D(-15, -15, -28), new Point3D(-15, -10, -30))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube2 floor
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-25, -5, -30), new Point3D(-25, -10, -28),
                                                new Point3D(-15, -10, -28), new Point3D(-15, -5, -30))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube2 up
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-15, -5, -30), new Point3D(-15, -10, -28),
                                                new Point3D(-15, -15, -28), new Point3D(-15, -10, -30))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube2 right
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-25, -5, -30), new Point3D(-25, -10, -28),
                                                new Point3D(-25, -15, -28), new Point3D(-25, -10, -30))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube2 left
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-25, -10, -30), new Point3D(-25, -5, -30),
                                                new Point3D(-15, -5, -30), new Point3D(-15, -10, -30))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube2 back
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-25, -10, -28), new Point3D(-25, -15, -28),
                                                new Point3D(-15, -15, -28), new Point3D(-15, -10, -28))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube2 front
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox()
                                ).turnOnBVH().setBox(),
                                new Geometries(
                                        new Polygon(new Point3D(-8, -10, -30), new Point3D(-8, -15, -28),
                                                new Point3D(-13.5, -15, -28), new Point3D(-13.5, -10, -30))
                                                .setEmission(new Color(java.awt.Color.red)) //cube3 floor
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-8, -5, -30), new Point3D(-8, -10, -28),
                                                new Point3D(-13.5, -10, -28), new Point3D(-13.5, -5, -30))
                                                .setEmission(new Color(java.awt.Color.red)) //cube3 up
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-8, -10, -30), new Point3D(-8, -15, -28),
                                                new Point3D(-8, -10, -28), new Point3D(-8, -5, -30))
                                                .setEmission(new Color(java.awt.Color.red)) //cube3 right
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-13.5, -10, -30), new Point3D(-13.5, -15, -28),
                                                new Point3D(-13.5, -10, -28), new Point3D(-13.5, -5, -30))
                                                .setEmission(new Color(java.awt.Color.red)) //cube3 left
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-8, -10, -30), new Point3D(-8, -5, -30),
                                                new Point3D(-13.5, -5, -30), new Point3D(-13.5, -10, -30))
                                                .setEmission(new Color(java.awt.Color.red)) //cube3 back
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-8, -10, -28), new Point3D(-8, -15, -28),
                                                new Point3D(-13.5, -15, -28), new Point3D(-13.5, -10, -28))
                                                .setEmission(new Color(java.awt.Color.red)) //cube3 front
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox()
                                ).turnOnBVH().setBox(),
                                new Geometries(
                                        new Polygon(new Point3D(-23, -2, -30), new Point3D(-23, -7, -28),
                                                new Point3D(-10, -7, -28), new Point3D(-10, -2, -30))
                                                .setEmission(new Color(java.awt.Color.green)).setBox(), //cube7 floor
                                        //   .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),

                                        new Polygon(new Point3D(-23, 0, -30), new Point3D(-23, -5, -28),
                                                new Point3D(-10, -5, -28), new Point3D(-10, 0, -30))
                                                .setEmission(new Color(java.awt.Color.green)) //cube7 up
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-10, -2, -30), new Point3D(-10, -7, -28),
                                                new Point3D(-10, -5, -28), new Point3D(-10, 0, -30))
                                                .setEmission(new Color(java.awt.Color.green)) //cube7 right
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-23, -2, -30), new Point3D(-23, -7, -28),
                                                new Point3D(-23, -5, -28), new Point3D(-23, 0, -30))
                                                .setEmission(new Color(java.awt.Color.green)) //cube7 left
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-23, -2, -30), new Point3D(-23, 0, -30),
                                                new Point3D(-10, 0, -30), new Point3D(-10, -2, -30))
                                                .setEmission(new Color(java.awt.Color.green)) //cube7 back
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(-23, -5, -28), new Point3D(-23, -7, -28),
                                                new Point3D(-10, -7, -28), new Point3D(-10, -5, -28))
                                                .setEmission(new Color(java.awt.Color.green)) //cube7 front
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox()
                                ).turnOnBVH().setBox()
                        ).turnOnBVH().setBox(),
                        new Geometries(
                                new Geometries(
                                        new Polygon(new Point3D(20, -10, -30), new Point3D(20, -15, -28),
                                                new Point3D(10, -15, -28), new Point3D(10, -10, -30))
                                                .setEmission(new Color(java.awt.Color.blue)) //cube4 floor
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(20, -5, -30), new Point3D(20, -10, -28),
                                                new Point3D(10, -10, -28), new Point3D(10, -5, -30))
                                                .setEmission(new Color(java.awt.Color.blue)) //cube4 up
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(20, -10, -30), new Point3D(20, -15, -28),
                                                new Point3D(20, -10, -28), new Point3D(20, -5, -30))
                                                .setEmission(new Color(java.awt.Color.blue)) //cube4 right
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(10, -10, -30), new Point3D(10, -15, -28),
                                                new Point3D(10, -10, -28), new Point3D(10, -5, -30))
                                                .setEmission(new Color(java.awt.Color.blue)) //cube4 left
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(10, -10, -30), new Point3D(20, -10, -30),
                                                new Point3D(20, -5, -30), new Point3D(10, -5, -30))
                                                .setEmission(new Color(java.awt.Color.blue)) //cube4 back
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(20, -15, -28), new Point3D(10, -15, -28),
                                                new Point3D(10, -10, -28), new Point3D(20, -10, -28))
                                                .setEmission(new Color(java.awt.Color.blue)) //cube4 front
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox()
                                ).turnOnBVH().setBox(),
                                new Geometries(
                                        new Polygon(new Point3D(35, -10, -30), new Point3D(35, -15, -28),
                                                new Point3D(21, -15, -28), new Point3D(21, -10, -30))
                                                .setEmission(new Color(java.awt.Color.gray)) //cube5 floor
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(35, -5, -30), new Point3D(35, -10, -28),
                                                new Point3D(21, -10, -28), new Point3D(21, -5, -30))
                                                .setEmission(new Color(java.awt.Color.green)) //cube5 up
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(35, -10, -30), new Point3D(35, -15, -28),
                                                new Point3D(35, -10, -28), new Point3D(35, -5, -30))
                                                .setEmission(new Color(java.awt.Color.green)) //cube5 right
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(21, -10, -30), new Point3D(21, -15, -28),
                                                new Point3D(21, -10, -28), new Point3D(21, -5, -30))
                                                .setEmission(new Color(java.awt.Color.green)) //cube5 left
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(21, -10, -30), new Point3D(35, -10, -30),
                                                new Point3D(35, -5, -30), new Point3D(21, -5, -30))
                                                .setEmission(new Color(java.awt.Color.green)) //cube5 back
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(35, -15, -28), new Point3D(21, -15, -28),
                                                new Point3D(21, -10, -28), new Point3D(35, -10, -28))
                                                .setEmission(new Color(java.awt.Color.green)) //cube5 front
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox()
                                ).turnOnBVH().setBox(),
                                new Geometries(
                                        new Polygon(new Point3D(25, -2, -30), new Point3D(25, -8, -28),
                                                new Point3D(13, -8, -28), new Point3D(13, -2, -30))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube6 floor
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(25, 8, -30), new Point3D(25, 2, -28),
                                                new Point3D(13, 2, -28), new Point3D(13, 8, -30))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube6 up
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(25, -2, -30), new Point3D(25, 8, -30),
                                                new Point3D(25, 2, -28), new Point3D(25, -8, -28))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube6 right
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(13, -2, -30), new Point3D(13, 8, -30),
                                                new Point3D(13, 2, -28), new Point3D(13, -8, -28))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube6 left
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(25, -2, -30), new Point3D(25, 8, -30),
                                                new Point3D(13, 2, -30), new Point3D(13, -8, -30))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube6 back
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(25, -2, -28), new Point3D(25, 8, -28),
                                                new Point3D(13, 2, -28), new Point3D(13, -8, -28))
                                                .setEmission(new Color(java.awt.Color.orange)) //cube6 front
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox()
                                ).turnOnBVH().setBox()
                        ).turnOnBVH().setBox(),
                        new Geometries(
                                new Geometries(
                                        new Polygon(new Point3D(30, -28, -30), new Point3D(30, -23, -28),
                                                new Point3D(21, -23, -28), new Point3D(21, -28, -30))
                                                .setEmission(new Color(java.awt.Color.gray)) //cube8_ floor
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(30, -23, -30), new Point3D(30, -18, -28),
                                                new Point3D(21, -18, -28), new Point3D(21, -23, -30))
                                                .setEmission(new Color(java.awt.Color.gray)) //cube_ up
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(30, -28, -30), new Point3D(30, -23, -28),
                                                new Point3D(30, -18, -28), new Point3D(30, -23, -30))
                                                .setEmission(new Color(java.awt.Color.gray)) //cube_ right
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(21, -28, -30), new Point3D(21, -23, -28),
                                                new Point3D(21, -18, -28), new Point3D(21, -23, -30))
                                                .setEmission(new Color(java.awt.Color.gray)) //cube_ left
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(30, -23, -28), new Point3D(21, -23, -28),
                                                new Point3D(21, -18, -28), new Point3D(30, -18, -28))
                                                .setEmission(new Color(java.awt.Color.gray)) //cube_ back
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),

                                        new Polygon(new Point3D(30, -28, -30), new Point3D(21, -28, -30),
                                                new Point3D(21, -23, -30), new Point3D(30, -23, -30))
                                                .setEmission(new Color(java.awt.Color.gray)) //cube_ front
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox()
                                ).turnOnBVH().setBox(),
                                new Geometries(
                                        new Sphere(3, new Point3D(-10, -28, 0))
                                                .setEmission(new Color(java.awt.Color.magenta))
                                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),
                                        new Sphere(1, new Point3D(0, -23, 0))
                                                .setEmission(new Color(java.awt.Color.GREEN)).setBox()

//                        new Sphere(3, new Point3D(0, 5, 200))
//                                .setEmission(new Color(java.awt.Color.cyan))
//                                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBox(),


                                ).turnOnBVH().setBox()
                        ).turnOnBVH().setBox()
                ).turnOnBVH().setBox()

        );


        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-70, 100, 0), new Vector(12, -70, 0.5))
                .setKl(0.0001).setKq(0.00001));
        scene.lights.add(new DirectionalLight(new Color(700, 400, 400), new Vector(0, 10, -5)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(8, -9, -10), new Vector(0, -1, 0)));
        //scene.lights.add(new SpotLight(new Color(java.awt.Color.white), new Point3D(5, 0, 0), new Vector(0, -1, -0.25)));

        ImageWriter imageWriter = new ImageWriter("BVHnotAouto", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
                 .setMultithreading(3) //fot threading improvement
                  .setDebugPrint()
                .setRayTracer(new BasicRayTracer(scene.turnOnUsingBVH()));//.turnOnUsingBVH()
        render.renderImage();
        render.writeToImage();
    }
}
