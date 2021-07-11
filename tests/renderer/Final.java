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

public class Final {
    private Scene scene = new Scene("Test scene");

    @Test
    public void FinalPic() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1000), new Vector(0, 1, 0)) //
                .setViewPlaneSize(30, 30).setDistance(850).setFocalPlane(250, 1.2, 1.2);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.background = new Color(2, 12, 90);
Material mat=new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.4).setKr(0.1);

        scene.geometries.add(
                new Sphere(10, new Point3D(-3, -3, -250))//center mount
                        .setEmission(new Color(20, 150, 0)),
                new Sphere(13, new Point3D(-20, 0, -300))//left mount
                        .setEmission(new Color(140, 102, 45)),
                new Sphere(15, new Point3D(20, 0, -300))//right mount
                        .setEmission(new Color(100, 75, 30)),
                new Polygon(new Point3D(-30, -3, -250), new Point3D(30, -3, -250),//floor
                        new Point3D(30, -30, 0), new Point3D(-30, -30, 0))
                        .setEmission(new Color(10, 10, 10)),
                //grapes
                new Sphere(2, new Point3D(0, -3, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
             new Sphere(2.2, new Point3D(4.25, -3, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
             new Sphere(1.9, new Point3D(8.3, -3, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
             new Sphere(2, new Point3D(12.32, -2.8, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),

                new Sphere(1.9, new Point3D(0, -7.3, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1, new Point3D(2, -5.2, -100))//little
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.5, new Point3D(3.5, -7.7, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(1.9, new Point3D(6.5, -6.7, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat),
                new Sphere(2, new Point3D(10.55, -6.5, -100))
                        .setEmission(new Color(java.awt.Color.blue))
                        .setMaterial(mat)


        );


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
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage(9);
        render.writeToImage();
    }
}
