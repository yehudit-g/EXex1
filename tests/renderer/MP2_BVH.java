package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

public class MP2_BVH {
    private Scene scene = new Scene("Test scene");

    @Test
    public void improvement2() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(30, 30).setDistance(350);//.setFocalPlane(100, 1, 1);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

//        Geometries pyramid = new Geometries(
//                new Triangle(new Point3D(8, -9, 5), new Point3D(2, -9, 5), new Point3D(4, -11, 0))
//                        .setEmission(new Color(java.awt.Color.blue))
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.0)), //base
//                new Triangle(new Point3D(4, -11, 0), new Point3D(8, -9, 5), new Point3D(5, 0, 0))
//                        .setEmission(new Color(250, 30, 100)) //
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
//                new Triangle(new Point3D(8, -9, 5), new Point3D(2, -9, 5), new Point3D(5, 0, 0))
//                        .setEmission(new Color(250, 30, 100)) //
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
//                new Triangle(new Point3D(2, -9, 5), new Point3D(4, -11, 0), new Point3D(5, 0, 0))
//                        .setEmission(new Color(250, 30, 100)) //
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))
//        );

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

        scene.geometries.add( cube,
                //The room:
                new Polygon(new Point3D(35,35, -500), new Point3D(35, -35, -500),
                        new Point3D(-35, -35, -500), new Point3D(-35, 35, -500))
                        .setEmission(new Color(java.awt.Color.blue)) //back
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(35,35, -500), new Point3D(35, -35, -500),
                        new Point3D(1035, -1035, 500), new Point3D(1035, 1035, 500))
                        .setEmission(new Color(java.awt.Color.blue)) //right wall
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(-1035,1035, 500), new Point3D(-1035, -1035, 500),
                        new Point3D(-35, -35, -500), new Point3D(-35, 35, -500))
                        .setEmission(new Color(java.awt.Color.blue)) //left wall
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(35,35, -500), new Point3D(1035, 1020, 500),
                        new Point3D(-1035, 1020, 500), new Point3D(-35, 35, -500))
                        .setEmission(new Color(java.awt.Color.blue)) //ceiling
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon(new Point3D(1035,-1020, 500), new Point3D(35, -35, -500),
                        new Point3D(-35, -35, -500), new Point3D(-1035, -1020, 500))
                        .setEmission(new Color(java.awt.Color.blue)) //floor
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),

                new Sphere(5, new Point3D(-10, -10, -20)) //lower left
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
                new Sphere(5, new Point3D(15, 15, 100)) //right up
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

        ImageWriter imageWriter = new ImageWriter("improvementBVH", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void BVHpic() {
        Camera camera = new Camera(new Point3D(-200, 0, 1000), new Vector(200, 0, -1000), new Vector(0, 1, 0)) //
                .setViewPlaneSize(30, 30).setDistance(350);//.setFocalPlane(100, 1, 1);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.background=Color.BLACK;

//        Geometries cube = new Geometries(
//                new Polygon(new Point3D(35,-10, 30), new Point3D(35, -40, -40),
//                        new Point3D(-38, -12, -40),  new Point3D(-38, -8, 28))
//                        .setEmission(new Color(java.awt.Color.blue)) //cube
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),new Polygon(new Point3D(9, -8, 0), new Point3D(12, -10, -10), new Point3D(9, -12, -20), new Point3D(6, -10, -10))
//                        .setEmission(new Color(java.awt.Color.blue))
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
//                new Polygon(new Point3D(9, -14, -20), new Point3D(9, -12, -20), new Point3D(12, -10, -10), new Point3D(12, -12, -10))
//                        .setEmission(new Color(java.awt.Color.blue))
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
//                new Polygon(new Point3D(9, -8, 0), new Point3D(9, -10, 0), new Point3D(12, -12, -10), new Point3D(12, -10, -10))
//                        .setEmission(new Color(java.awt.Color.blue))
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
//                new Polygon(new Point3D(9, -8, 0), new Point3D(9, -10, 0), new Point3D(6, -12, -10), new Point3D(6, -10, -10))
//                        .setEmission(new Color(java.awt.Color.blue))
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
//                new Polygon(new Point3D(9, -14, -20), new Point3D(9, -12, -20), new Point3D(6, -10, -10), new Point3D(6, -12, -10))
//                        .setEmission(new Color(java.awt.Color.blue))
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))
//        );



        scene.geometries.add(
                new Polygon(new Point3D(35,-10, -30), new Point3D(35, -40, 40),
                        new Point3D(-40, -40, 40),  new Point3D(-40, -10, -30))
                        .setEmission(new Color(java.awt.Color.blue)) //floor
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
//                new Polygon(new Point3D(35,-9, 30), new Point3D(35, -11, 28),
//                        new Point3D(30, -11, 28),  new Point3D(30, -9, 30))
//                        .setEmission(new Color(java.awt.Color.GREEN)) //floor
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))
                new Sphere(1, new Point3D(0,-25,0))
                .setEmission(new Color(java.awt.Color.GREEN))
//                ,new Polygon(new Point3D(0,0, 1500), new Point3D(55, -35, -500),
//                        new Point3D(-30, -50, -1500), new Point3D(-30, 0, 1500))
//                        .setEmission(new Color(java.awt.Color.GREEN)) //floor
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))
        );
//        for(int i=35;i>-40;i-=5)
//            for(int j=0;j<15;j++){
//                scene.geometries.add(
//                        new Polygon(new Point3D(i,-10, 30), new Point3D(i, -40, 0),
//                                new Point3D(i-, -40, 0),  new Point3D(-40, -10, 30))
//                                .setEmission(new Color(java.awt.Color.blue)));
//            }

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(-70, 100, 0), new Vector(12, -70, 0.5))
                .setKl(0.0001).setKq(0.00001));
        scene.lights.add(new DirectionalLight(new Color(700, 400, 400), new Vector(0, 10, -5)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(8, -9, -10), new Vector(0,-1,0)));
        //scene.lights.add(new SpotLight(new Color(java.awt.Color.white), new Point3D(5, 0, 0), new Vector(0, -1, -0.25)));

        ImageWriter imageWriter = new ImageWriter("BVHpic", 600, 600);
        Render render = new Render()
                .setImageWriter(imageWriter)
                .setCamera(camera)
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }


//    @Test
//    public void catTest() {
//        Scene scene = new Scene("Test scene");
//        scene.distance(1000.0D);
//        scene.setBackground(new Color(java.awt.Color.BLACK));
//        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15D));
//        scene.geometries.add(new Intersectable[]{new Triangle(new Point3D(-50.0D, -50.0D, 500.0D), new Point3D(50.0D, -50.0D, 500.0D), new Point3D(-50.0D, 50.0D, 500.0D)).setMaterial(new Material(0.5D, 0.5D, 60, 0.0D, 0.0D)) , new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.0D, 0.0D), new Point3D(50.0D, 50.0D, 500.0D), new Point3D(50.0D, -50.0D, 500.0D), new Point3D(-50.0D, 50.0D, 500.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.0D, 0.0D), new Point3D(50.0D, 50.0D, 500.0D), new Point3D(0.0D, 100.0D, 400.0D), new Point3D(-50.0D, 50.0D, 500.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.0D, 0.0D), new Point3D(0.0D, 100.0D, 400.0D), new Point3D(-50.0D, 50.0D, 500.0D), new Point3D(-100.0D, 100.0D, 400.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.0D, 0.0D), new Point3D(-50.0D, -50.0D, 500.0D), new Point3D(-50.0D, 50.0D, 500.0D), new Point3D(-100.0D, 100.0D, 400.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.0D, 0.0D), new Point3D(-50.0D, -50.0D, 500.0D), new Point3D(-100.0D, 0.0D, 400.0D), new Point3D(-100.0D, 100.0D, 400.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.5D, 0.0D), new Point3D(-50.0D, -50.0D, 500.0D), new Point3D(-100.0D, 0.0D, 400.0D), new Point3D(0.0D, 0.0D, 400.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.5D, 0.0D), new Point3D(-50.0D, -50.0D, 500.0D), new Point3D(50.0D, -50.0D, 500.0D), new Point3D(0.0D, 0.0D, 400.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.3D, 0.0D), new Point3D(0.0D, 100.0D, 400.0D), new Point3D(50.0D, -50.0D, 500.0D), new Point3D(0.0D, 0.0D, 400.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.3D, 0.0D), new Point3D(0.0D, 100.0D, 400.0D), new Point3D(50.0D, -50.0D, 500.0D), new Point3D(50.0D, 50.0D, 500.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.3D, 0.0D), new Point3D(0.0D, 100.0D, 400.0D), new Point3D(-100.0D, 100.0D, 400.0D), new Point3D(0.0D, 0.0D, 400.0D)), new Triangle(new Color(255.0D, 72.0D, 84.0D), new Material(0.5D, 0.5D, 60, 0.3D, 0.0D), new Point3D(-100.0D, 0.0D, 400.0D), new Point3D(-100.0D, 100.0D, 400.0D), new Point3D(0.0D, 0.0D, 400.0D)), new Sphere(new Material(0.2D, 0.6D, 30, 0.0D, 0.0D), new Color(java.awt.Color.BLACK), 8.0D, new Point3D(-50.0D, 50.0D, 400.0D)), new Sphere(new Material(0.2D, 0.6D, 30, 0.0D, 0.0D), new Color(java.awt.Color.BLACK), 8.0D, new Point3D(-75.0D, 25.0D, 400.0D)), new Sphere(new Material(0.2D, 0.6D, 30, 0.0D, 0.0D), new Color(java.awt.Color.BLACK), 8.0D, new Point3D(-75.0D, 75.0D, 400.0D)), new Sphere(new Material(0.2D, 0.6D, 30, 0.0D, 0.0D), new Color(java.awt.Color.BLACK), 8.0D, new Point3D(-25.0D, 25.0D, 400.0D)), new Sphere(new Material(0.2D, 0.6D, 30, 0.0D, 0.0D), new Color(java.awt.Color.BLACK), 8.0D, new Point3D(-25.0D, 75.0D, 400.0D)), new Sphere(new Material(0.2D, 0.6D, 30, 0.0D, 0.0D), new Color(java.awt.Color.BLACK), 8.0D, new Point3D(25.0D, 25.0D, 450.0D)), new Sphere(new Material(0.2D, 0.6D, 30, 0.0D, 0.0D), new Color(java.awt.Color.BLACK), 8.0D, new Point3D(-25.0D, -25.0D, 450.0D)), new Sphere(new Material(0.2D, 0.6D, 30, 0.0D, 0.0D), new Color(java.awt.Color.BLACK), 8.0D, new Point3D(-38.0D, -38.0D, 475.0D)), new Sphere(new Material(0.2D, 0.6D, 30, 0.0D, 0.0D), new Color(java.awt.Color.BLACK), 8.0D, new Point3D(-12.5D, -12.5D, 425.0D)), new Plane(new Material(0.2D, 0.2D, 30, 0.0D, 1.0D), new Point3D(0.0D, 0.0D, 550.0D), new Vector(0.0D, 0.0D, -1.0D), new Color(java.awt.Color.black)), new Plane(new Material(0.2D, 0.2D, 30, 0.0D, 0.0D), new Point3D(0.0D, 150.0D, 0.0D), new Vector(0.0D, -1.0D, 0.0D), new Color(java.awt.Color.black))});
//
//    byte z;
//    int j;
//    int i;
//        for(j = -12; j < 1; ++j) {
//        if (j % 2 == 0) {
//            z = 1;
//        } else {
//            z = 0;
//        }
//
//        for(i = -1; i < 4; ++i) {
//            scene.geometries.add(new Intersectable[]{new Triangle(new Color(115.0D, 110.0D, 110.0D), new Material(0.5D, 0.5D, 20, 0.0D, 1.0D), new Point3D((double)(-100 + i * 200 + z * 100), 150.0D, (double)(450 + j * 100)), new Point3D((double)(0 + i * 200 + z * 100), 150.0D, (double)(450 + j * 100)), new Point3D((double)(-100 + i * 200 + z * 100), 150.0D, (double)(550 + j * 100))), new Triangle(new Color(115.0D, 110.0D, 110.0D), new Material(0.5D, 0.5D, 20, 0.0D, 1.0D), new Point3D((double)(0 + i * 200 + z * 100), 150.0D, (double)(550 + j * 100)), new Point3D((double)(0 + i * 200 + z * 100), 150.0D, (double)(450 + j * 100)), new Point3D((double)(-100 + i * 200 + z * 100), 150.0D, (double)(550 + j * 100)))});
//        }
//    }
//
//        for(j = -12; j < 1; ++j) {
//            if (j % 2 == 0) {
//                z = 0;
//            } else {
//                z = 1;
//            }
//
//            for (i = -1; i < 3; ++i) {
//                scene.geometries.add(new Intersectable[]{new Triangle(new Color(-150.0D, -150.0D, -150.0D), new Material(0.5D, 0.5D, 20, 0.0D, 1.0D), new Point3D((double) (-100 + i * 200 + z * 100), 150.0D, (double) (450 + j * 100)), new Point3D((double) (0 + i * 200 + z * 100), 150.0D, (double) (450 + j * 100)), new Point3D((double) (-100 + i * 200 + z * 100), 150.0D, (double) (550 + j * 100))), new Triangle(new Color(-150.0D, -150.0D, -150.0D), new Material(0.5D, 0.5D, 20, 0.0D, 1.0D), new Point3D((double) (0 + i * 200 + z * 100), 150.0D, (double) (550 + j * 100)), new Point3D((double) (0 + i * 200 + z * 100), 150.0D, (double) (450 + j * 100)), new Point3D((double) (-100 + i * 200 + z * 100), 150.0D, (double) (550 + j * 100)))});
//            }
//
//        }
//        ImageWriter imageWriter = new ImageWriter("cat BVH test", 200.0D, 200.0D, 600, 600);
//        Render render = (new Render(imageWriter, scene)).setMultithreading(5).setDebugPrint();
//        render.renderImage();
//        render.writeToImage();
//    }

}
//
//    int z;
//    int j;
//    int i;
//        for (j = -12; j < 1; ++j) {
//        if (j % 2 == 0) {
//        z = 1;
//        } else {
//        z = 0;
//        }
//
//        for (i = -1; i < 4; ++i) {
//        scene.geometries.add(new Intersectable[]{new Triangle(new Point3D((double) (-100 + i * 200 + z * 100), 150.0D, (double) (450 + j * 100)), new Point3D((double) (0 + i * 200 + z * 100), 150.0D, (double) (450 + j * 100)), new Point3D((double) (-100 + i * 200 + z * 100), 150.0D, (double) (550 + j * 100))).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(20)).setEmission(new Color(115.0D, 110.0D, 110.0D)),
//        new Triangle(new Point3D((double) (0 + i * 200 + z * 100), 150.0D, (double) (550 + j * 100)), new Point3D((double) (0 + i * 200 + z * 100), 150.0D, (double) (450 + j * 100)), new Point3D((double) (-100 + i * 200 + z * 100), 150.0D, (double) (550 + j * 100))).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(20)).setEmission(new Color(115.0D, 110.0D, 110.0D))});
//        }
//        }
//
//        for (j = -12; j < 1; ++j) {
//        if (j % 2 == 0) {
//        z = 0;
//        } else {
//        z = 1;
//        }
//
//        for (i = -1; i < 3; ++i) {
//        scene.geometries.add(new Intersectable[]{new Triangle(new Point3D((double) (-100 + i * 200 + z * 100), 150.0D, (double) (450 + j * 100)), new Point3D((double) (0 + i * 200 + z * 100), 150.0D, (double) (450 + j * 100)), new Point3D((double) (-100 + i * 200 + z * 100), 150.0D, (double) (550 + j * 100))).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(20)).setEmission(new Color(0.0D, 0.0D, 0.0D)),
//        new Triangle(new Point3D((double) (0 + i * 200 + z * 100), 150.0D, (double) (550 + j * 100)), new Point3D((double) (0 + i * 200 + z * 100), 150.0D, (double) (450 + j * 100)), new Point3D((double) (-100 + i * 200 + z * 100), 150.0D, (double) (550 + j * 100))).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(20)).setEmission(new Color(0.0D, 0.0D, 0.0D))});
//        }
//
//        }
