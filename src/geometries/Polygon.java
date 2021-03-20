
package geometries;

import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 */
public class Polygon implements Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane plane;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point3D... vertices) {
       if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    /**
     * Checking if a ray intersection with polygon's plane is inside it's borders
     * @param ray
     * @return true if the intersection is inside the polygon and else if not
     */
    public boolean isInside(Ray ray){
        Point3D P0=ray.getP0();
        int n=vertices.size();
        //creating a list of the vectors between P0 to the vertices
        Vector[] v=new Vector[n];
        for(int i = 0; i < n; ++i)
            v[i] = this.vertices.get(i).subtract(P0);

        //creating a list of the normals of each face in the 3D polyhedron
        //created between the vertices and P0
        Vector[] normals=new Vector[n];
        for(int i = 0; i < n-1; ++i)
            normals[i] = (v[i].crossProduct(v[i+1])).normalize();
        //the last vector
        normals[n-1]=(v[n-1].crossProduct(v[0])).normalize();

        //checking the signs according to the first one
        int sign;
        Vector V= ray.getDir();
        if(alignZero(V.dotProduct(normals[0]))==0) //if the product=0 (v0 and normal0 are orthogonal)
            return false;
        if(V.dotProduct(normals[0])>0)
            sign=1;
        else
            sign=0;
        for(int i = 1; i < n; ++i) {
            double vn=alignZero(V.dotProduct(normals[i]));
            if (sign == 1) {
                if (vn <= 0)
                    return false;
            }
            else if (vn >= 0)
                return false;
        }
        return true;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        if(isInside(ray))
            return plane.findIntersections(ray);
        return null;    }
}

