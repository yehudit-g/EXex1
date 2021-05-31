package geometries;

abstract  class RadialGeometry extends Geometry {
   protected final double _radius;

    protected RadialGeometry(double radius) {
        _radius = radius;
    }

    public double getRadius() {
        return _radius;
    }
}
