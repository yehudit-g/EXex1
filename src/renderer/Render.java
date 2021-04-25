package renderer;

import elements.Camera;
import jdk.jshell.spi.ExecutionControl;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {
    public ImageWriter _imageWriter;
    public Scene _scene;
    public Camera _camera;
    public RayTracerBasic _rayTracer;

    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    public Render setScene(Scene scene) {
        _scene = scene;
        return this;
    }

    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBasic rayTracer) {
        _rayTracer = rayTracer;
        return this;
    }

    /**
     *
     * @throws ExecutionControl.NotImplementedException
     */
    public void renderImage() { //throws ExecutionControl.NotImplementedException {
        if (_camera == null)
            throw new MissingResourceException("", "Render", "_camera");
        if (_imageWriter == null)
            throw new MissingResourceException("", "Render", "_imageWriter");
        if (_scene == null)
            throw new MissingResourceException("", "Render", "_scene");
        if (_rayTracer == null)
            throw new MissingResourceException("", "Render", "_rayTracer");
        //throw new ExecutionControl.NotImplementedException("");
        Ray ray;
        int nX=_imageWriter.getNx();
        int nY=_imageWriter.getNy();
        for (int i = 0; i <  nY; i++) {
            for (int j = 0; j <  nX; j++) {
                ray = _camera.constructRayThroughPixel(nX, nY,j,i);
                _imageWriter.writePixel(j,i,_rayTracer.traceRay(ray));
            }
        }
    }

    /**
     *create the grid in the desired color
     * @param interval - pixel length and width
     * @param color
     */
    public void printGrid(int interval, Color color){
        if (_imageWriter == null)
            throw new MissingResourceException("", "Render", "_imageWriter");
        int nX=_imageWriter.getNx();
        int nY=_imageWriter.getNy();
        for (int i = 0; i <  nY; i++) {
            for (int j = 0; j <  nX; j++) {
                    if(i%interval==0 || j%interval==0)
                        _imageWriter.writePixel(j, i, color);
                }
            }
        //_imageWriter.writeToImage();
    }

    /**
     *
     */
    public void writeToImage(){
        if (_imageWriter == null)
            throw new MissingResourceException("", "Render", "_imageWriter");
        _imageWriter.writeToImage();
    }
}
