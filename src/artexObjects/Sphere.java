package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import artexIO.ArtexWriter;
import utils.GeoMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Amirhossein Azimyzadeh
 * @see Face
 * @see utils.GeoMath
 * @see Polygon
 */
public class Sphere {
    private Face[] faces;
    private int segments;
    private float radius;

    /**
     * @param segments always map to an even value
     *                 increasing segment value cause an accurate sphere
     */

    public Sphere(int segments, float radius) {
        //normalize segments value
        if (segments <= 3)
            segments = 4;
        if (segments % 2 == 1)
            segments += 1;

        this.segments = segments;
        this.radius = radius;
        buildSphere();
    }

    private void buildSphere() {
        //todo build sphere faces
        Polygon base = new Polygon.Builder(this.segments, this.radius)
                .rotateY((float) Math.PI / 2)
                .build();
        // find top and bottom vertex
        Vertex top = new Vertex(0, 0, 0);
        Vertex bottom = new Vertex(0, 0, 0);
        float zMin = Float.MAX_VALUE;
        float zMax = Float.MIN_VALUE;
        for (Vertex vertex : base.getFace().getVertices()) {
            if (vertex.getZ() <= zMin) {
                bottom = vertex;
                zMin = bottom.getZ();
            }
            if (vertex.getZ() >= zMax) {
                top = vertex;
                zMax = top.getZ();
            }
        }

        // create mid Polygons
        // y is radius and z is move z
        ArrayList<Face> helper = new ArrayList<>(this.segments -2);
        for(Vertex vertex: base.getFace().getVertices()){
            if(vertex.equals(top) || vertex.equals(bottom))
                continue;
            float radius = vertex.getY();
            float moveZ = vertex.getZ();

            Polygon p = new Polygon.Builder(this.segments, radius).moveZ(moveZ).build();
            helper.add(p.getFace());
        }
        Face[] sphereEdges = new Face[helper.size()];
        for (int i = 0; i < sphereEdges.length; i++) {
            sphereEdges[i] = helper.get(i);
        }

        // connecting mid faces  this.segments/2 * this.segments
        ArrayList<Face> sphereSurface = new ArrayList<>();
        Face lasFace = sphereEdges[0];
        for (int i = 1; i < sphereEdges.length; i++) {
            Face[] connectedFaces = GeoMath.createSideFaces(sphereEdges[i].getVerticesInArray()
                    ,lasFace.getVerticesInArray());
            lasFace = sphereEdges[i];
            Collections.addAll(sphereSurface,connectedFaces);
        }

        this.faces = new Face[sphereSurface.size()];
        for (int i = 0; i < this.faces.length; i++) {
            this.faces[i] = sphereSurface.get(i);
        }

    }

    private Vertex[][] createMidVertices() {

        return null;
    }

    public int getSegments() {
        return segments;
    }

    public ComplexObject convertToComplexObject() {
        return new ComplexObject(this.faces);
    }
}
