package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/***
 * Class Camera represents a Camera in 3D space
 * by its Point3D location and 3 direction vectors(up, right and forward).
 * It also contains data about the view plane: it's height and width and the distance from the camera.
 *                         and the focal plane:
 */
public class Camera {
    private Point3D _p0;
    private Vector _vUp; //private -> enable rotation
    private Vector _vTo;
    private Vector _vRight;
    private double _distance; //from V-Plane
    private double _width; //of V-Plane
    private double _height; //of V-Plane
    private double _fDistance; //from focal plane
    private double _apertureWidth;
    private double _apertureHeight;

    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        _p0 = p0;
        _vUp = vUp.normalized();
        _vTo = vTo.normalized();
        if (!(isZero(_vUp.dotProduct(_vTo)))) {
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

    public double getFDistance() {
        return _fDistance;
    }

    public double getApertureWidth() {
        return _apertureWidth;
    }

    public double getApertureHeight() {
        return _apertureHeight;
    }

    //expands the c-tor for V-Plane
    public Camera setViewPlaneSize(double width, double height) {
        _width = width;
        _height = height;
        //default reset of focal-plane
        _apertureWidth = width;
        _apertureHeight = height;
        return this;
    }

    public Camera setDistance(double distance) {
        _distance = distance;
        //default reset of focal-plane
        _fDistance = distance;
        return this;
    }

    //expands the c-tor for F-Plane
    public Camera setFocalPlane(double distance, double width, double height) {
        _fDistance = distance;
        _apertureWidth = width;
        _apertureHeight = height;
        return this;
    }

    /**
     * @param nX - number of pixels on the width
     * @param nY - number of pixels on the height
     * @param j  - the pixel's number on the width
     * @param i  - the pixel's number on the height
     * @return a ray from the camera to the asked pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D pc = _p0.add(_vTo.scale(_distance)); //view plane center

        if (isZero(nX) || isZero(nY))
            throw new IllegalArgumentException("Pixels' number cannot be zero");
        double Ry = _height / nY; //height of one pixel
        double Rx = _width / nX; //width of one pixel

        //pixel's distance from pc:
        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        Point3D Pij = pc;
        if (!isZero(Xj))
            Pij = Pij.add(_vRight.scale(Xj));
        if (!isZero(Yi))
            Pij = Pij.add(_vUp.scale(Yi));

        Vector Vij = Pij.subtract(_p0); //Vector from the camera to the pixel

        return new Ray(_p0, Vij);
    }

    /**
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return rays list of the beam through the asked pixel
     */
    public List<Ray> constructRaysBeamThroughPixel(int nX, int nY, int j, int i) {
//        Point3D pc = _p0.add(_vTo.scale(_distance)); //view plane center
//
//        if (isZero(nX) || isZero(nY))
//            throw new IllegalArgumentException("Pixels' number cannot be zero");
//        double Ry = _height / nY; //height of one pixel
//        double Rx = _width / nX; //width of one pixel
//
//        //pixel's distance from pc:
//        double Xj = (j - (nX - 1) / 2d) * Rx;
//        double Yi = -(i - (nY - 1) / 2d) * Ry;
//
//        Point3D Pij = pc;
//        if (!isZero(Xj))
//            Pij = Pij.add(_vRight.scale(Xj));
//        if (!isZero(Yi))
//            Pij = Pij.add(_vUp.scale(Yi));
//
//        Vector Vij = Pij.subtract(_p0); //Vector from the camera to the pixel
//
//        return new Ray(_p0, Vij);
    }


    //chaining methods:

    /**
     * rotation method to the sides
     *
     * @param angle :the degrees number to turn (positive- to the right, else to the left)
     */
    public Camera turnToSide(double angle) {
        if (angle == 0) {
            return this;
        }
        if (angle < 0) {
            throw new IllegalArgumentException("Angle can't be negative");
        }
        if (angle > 360) {
            angle = angle % 360;
        }

        if (angle % 90 == 0) { //rotation on the axes of the direction vectors
            for (int i = 0; i < (int) angle / 90; i++) {
                _vRight = turn90(_vRight);
            }
            return this;
        }

        int count = (int) angle / 90;
        angle %= 90;

        for (int i = 0; i < count; i++) {
            _vRight = turn90(_vRight);
        }
        turn(angle, _vRight);
        _vRight = _vTo.crossProduct(_vUp).normalize();

        return this;
    }

    //help func to turn vTo according to v (vRight/vUp)
    private void turn(double angle, Vector v) {
        double x = Math.tan(angle);
        Point3D p = _vTo.getHead();
        Ray r = new Ray(p, v);
        _vTo = r.getTargetPoint(x).subtract(_p0);
    }

    //helper func to turn 90 degrees to the right
    private Vector turn90(Vector v) {
        _vTo = v;
        if (v == _vRight)
            return _vTo.crossProduct(_vUp).normalize();
        if (v == _vUp)
            return _vRight.crossProduct(_vTo).normalize();
        throw new IllegalArgumentException("get just vRight or vUp");
    }

    /**
     * method of turning up and down
     *
     * @param angle :the degrees number to turn (positive- up, else -down)
     */
    public Camera turnUp(double angle) {
        if (angle == 0) {
            return this;
        }
        if (angle < 0) {
            throw new IllegalArgumentException("Angle can't be negative");
        }
        if (angle > 360) {
            angle = angle % 360;
        }

        if (angle % 90 == 0) { //rotation on the axes of the direction vectors
            for (int i = 0; i < (int) angle / 90; i++) {
                _vUp = turn90(_vUp);
            }
            return this;
        }

        int count = (int) angle / 90;
        angle %= 90;

        for (int i = 0; i < count; i++) {
            _vUp = turn90(_vUp);
        }
        turn(angle, _vUp);
        _vUp = _vTo.crossProduct(_vRight).normalize(); ////check the direction!!!!!!

        return this;
    }

    /**
     * method of zoom
     *
     * @param dis :the distance to the desired zoom (positive- getting closer, else- zoom out)
     */
    public Camera zoom(double dis) {
        if (dis > 0)
            _p0 = _p0.add(_vTo.scale(dis));
        else if (dis < 0) { //reverse vector
            Ray r = new Ray(_p0, _vTo);
            Vector v = _p0.subtract(r.getTargetPoint(1));
            _p0 = _p0.add(v.scale(Math.abs(dis)));
        }
        return this;
    }
}
