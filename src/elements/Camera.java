package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/***
 * Class Camera represents a Camera in 3D space
 * by its Point3D location and 3 direction vectors(up, right and forward).
 * It also contains data about the view plane: it's height and width and the distance from the camera.
 */
public class Camera {
    final Point3D _p0;
    final Vector _vUp;
    final Vector _vTo;
    final Vector _vRight;
    private double _distance;
    private double _width;
    private double _height;

    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        _p0 = p0;
        _vUp = vUp.normalized();
        _vTo = vTo.normalized();
        if(!(isZero(_vUp.dotProduct(_vTo)))){
            throw new IllegalArgumentException("vTo and vUp are not orthogonal");
        }
        _vRight = _vTo.crossProduct(_vUp).normalize();
    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getVUp() {
        return _vUp;
    }

    public Vector getVTo() {
        return _vTo;
    }

    public Vector getVRight() {
        return _vRight;
    }

    public double getDistance() {
        return _distance;
    }

    public double getWidth() {
        return _width;
    }

    public double getHeight() {
        return _height;
    }


    //expands the c-tor
    public Camera setViewPlaneSize(double width, double height){
        _width=width;
        _height=height;
        return this;
    }
    public Camera setDistance(double distance){
        _distance=distance;
        return this;
    }

    /**
     * @param nX - number of pixels on the width
     * @param nY - number of pixels on the height
     * @param j - the pixel's number on the width
     * @param i - the pixel's number on the height
     * @return a ray from the camera to the asked pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        Point3D pc=_p0.add(_vTo.scale(_distance)); //view plane center

        if(isZero(nX) || isZero(nY))
            throw new IllegalArgumentException("Pixels' number cannot be zero");
        double Ry=_height/nY; //height of one pixel
        double Rx=_width/nX; //width of one pixel

        //pixel's distance from pc:
        double Xj=(j-(nX-1)/2d)*Rx;
        double Yi=-(i-(nY-1)/2d)*Ry;

        Point3D Pij=pc;
        if(!isZero(Xj))
            Pij=Pij.add(_vRight.scale(Xj));
        if(!isZero(Yi))
            Pij=Pij.add(_vUp.scale(Yi));

        Vector Vij=Pij.subtract(_p0); //Vector from the camera to the pixel

        return new Ray(_p0,Vij);
    }

    //chaining methods
    /**
     * rotation method to the sides
     * @param angle :the degrees number to turn (positive- to the right, else to the left)
     */
    public Camera turnToSide(double angle){

    return  this;
    }

    /**
     * method of turning up and down
     * @param angle :the degrees number to turn (positive- up, else -down)
     */
    public Camera turnUp(double angle){
        return this;
    }

    /**
     * method of zoom
     * @param dis :the distance to the desired zoom (positive- getting closer, else- zoom out)
     */
    public Camera zoom(double dis){
       // _p0=_p0.add(_vTo.scale(dis));
        return this;
    }
}
