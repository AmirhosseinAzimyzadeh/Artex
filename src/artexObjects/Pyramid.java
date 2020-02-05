package artexObjects;

import artexCore.Face;
import artexCore.Vertex;

/**
 * Pyramid use a Polygon face to create a Pyramid or Cone
 * you can create a cone by increasing number of sides in
 * constructor
 *
 * @author Amirhossein Azimyzadeh
 * @see Polygon
 */
public class Pyramid {

    private Vertex apex;
    private Polygon base;
    private Face[] faces;

    public Pyramid(int numberOfSides, float radius, float height) {
        this.base = new Polygon.Builder(numberOfSides, radius).build();
        this.apex = new Vertex(0, 0, height);
        this.faces = createFaces(numberOfSides);
    }

    private Face[] createFaces(int numberOfSides) {
        Face[] faces = new Face[numberOfSides + 1];
        faces[0] = this.base.getFace();
        Face[] sides = new Face[numberOfSides];
        for (int i = 0; i < sides.length; i++) {
            Vertex[] vertices = new Vertex[3];
            if (i == sides.length - 1) {
                vertices[0] = apex;
                vertices[1] = base.getFace().getVertex(i);
                vertices[2] = base.getFace().getVertex(0);
            } else {
                vertices[0] = apex;
                vertices[1] = base.getFace().getVertex(i);
                vertices[2] = base.getFace().getVertex(i + 1);
            }
            sides[i] = new Face(vertices);
        }
        System.arraycopy(sides, 0, faces, 1, sides.length);
        faces[0] = this.base.getFace();
        return faces;
    }

    public Face[] getFaces() {
        return faces;
    }

    public Polygon getBase() {
        return base;
    }

    public Vertex getApex() {
        return apex;
    }

    public ComplexObject convertToComplexObject() {
        return new ComplexObject(this.faces);
    }
}
