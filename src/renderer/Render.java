package renderer;

import elements.Camera;
import jdk.jshell.spi.ExecutionControl;
import primitives.Color;
import primitives.Ray;

import java.util.MissingResourceException;

public class Render {
    public ImageWriter _imageWriter;
    public Camera _camera;
    public BasicRayTracer _rayTracer;

    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    public Render setRayTracer(BasicRayTracer rayTracer) {
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
      if (_rayTracer == null)
            throw new MissingResourceException("", "Render", "_rayTracer");
        Ray ray;
        //object beam; (ray/s)
        int nX=_imageWriter.getNx();
        int nY=_imageWriter.getNy();
        for (int i = 0; i <  nY; i++) {
            for (int j = 0; j <  nX; j++) {
                ray = _camera.constructRayThroughPixel(nX, nY,j,i);
                _imageWriter.writePixel(j,i,_rayTracer.traceRay(ray));
//                beam = _camera.constructRaysBeamThroughPixel(nX, nY,j,i);
//                _imageWriter.writePixel(j,i,_rayTracer.traceRay(beam));
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
