package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import utils.Axis;
import utils.GeoMath;

/**
 * Cylindrical Class a cylindrical 3D object with a base, height and axis
 * {@code axis} specify the axis of height
 * side faces start with index {@code 1 } to {@code this.AllFaces.length -1 }
 *
 * @author Amirhossein Azimyzadeh
 */
public class Cylindrical {
    private float height;
    private Face base;
    private Face[] AllFaces;
    private Axis axis;

    public Cylindrical(Face base, float height, Axis axis) {
        this.height = height;
        this.base = base;
        this.axis = axis;
        create();
    }

    private void create() {
        int numberOfSides = this.base.size();
        this.AllFaces = new Face[numberOfSides + 2];
        // bottom face ->
        this.AllFaces[0] = this.base;
        // top face ->
        Vertex[] top = new Vertex[numberOfSides];
        switch (this.axis) {
            case X:
                top = GeoMath.copyX(this.height, this.base.getVerticesInArray());
                break;
            case Y:
                top = GeoMath.copyY(this.height, this.base.getVerticesInArray());
                break;
            case Z:
                top = GeoMath.copyZ(this.height, this.base.getVerticesInArray());
                break;
        }
        // add top face to array
        this.AllFaces[this.AllFaces.length - 1] = new Face(top);
        // add sides to array
        Face[] sideFaces = GeoMath.createSideFaces(base.getVerticesInArray(), top);
        System.arraycopy(sideFaces, 0, this.AllFaces, 1, sideFaces.length);
    }

    public Face[] getSideFaces() {
        Face[] sides = new Face[this.AllFaces.length - 2];
        System.arraycopy(this.AllFaces, 1, sides, 0, sides.length);
        return sides;
    }

    public ComplexObject convertToComplexObject() {
        return new ComplexObject(AllFaces);
    }
}
