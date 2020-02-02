package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import utils.Axis;
import utils.GeoMath;

import java.util.ArrayList;
import java.util.List;

/**
 * Cylindrical Class a cylindrical 3D object with a base, height and axis
 * {@code axis} specify the axis of height
 *
 * @author Amirhossein Azimyzadeh
 * */
public class Cylindrical {
    // primitive data
    private float height;
    private Face base;
    private Face[] AllFaces;
    private Axis axis;

    public Cylindrical(Face base, float height, Axis axis){
        this.height = height;
        this.base = base;
        this.axis = axis;
        create();
    }

    private void create(){
        int numberOfSides = this.base.size();
        this.AllFaces = new Face[numberOfSides + 2];
        // bottom face ->
        this.AllFaces[0] = this.base;
        // top face ->
        Vertex[] top = new Vertex[numberOfSides];
        switch (this.axis){
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
        this.AllFaces[this.AllFaces.length-1] = new Face(top);

        // creating side faces ->
        List<Face> sideFaces = new ArrayList<>(numberOfSides);
        for (int i = 0; i < base.size(); i++) {
            Vertex[] vertices = new Vertex[4];
            if(i == base.size()-1){
                vertices[0] = this.base.getVertex(i);
                vertices[1] = this.base.getVertex(0);
                vertices[2] = this.AllFaces[this.AllFaces.length -1].getVertex(0);
                vertices[3] = this.AllFaces[this.AllFaces.length -1].getVertex(i);
            }else {
                vertices[0] = this.base.getVertex(i);
                vertices[1] = this.base.getVertex(i+1);
                vertices[2] = this.AllFaces[this.AllFaces.length -1].getVertex(i+1);
                vertices[3] = this.AllFaces[this.AllFaces.length -1].getVertex(i);
            }
            sideFaces.add(new Face(vertices));
        }
        // add side Faces to array
        for (int i = 0; i < sideFaces.size(); i++) {
            this.AllFaces[i+1] = sideFaces.get(i);
        }
    }

    public ComplexObject convertToComplexObject(){
        return new ComplexObject(AllFaces);
    }
}
