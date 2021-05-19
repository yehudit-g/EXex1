package geometries;

abstract  class RadialGeometry extends Geometry {
   protected final double radius;

    RadialGeometry(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
