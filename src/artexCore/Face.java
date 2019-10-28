package artexCore;

import java.util.ArrayList;
/**
 * Face class create a page with it's vertices
 * each faces or 3D Objects have one anchor , anchors used to rotate,
 * resize, move and copy objects and faces
 *
 *
 * @author Amirhossein Azimyzadeh
 */

public class Face {
    private ArrayList<Vertex> vertices;
    private Vertex anchor;

    public Face(ArrayList<Vertex> vertices, Vertex anchor) {
        this.vertices = vertices;
        this.anchor = anchor;
    }

    public Face(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
        if(vertices.size() != 0)
            this.anchor = vertices.get(0);
        else
            this.anchor = new Vertex(0,0,0);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int index){
        if(vertices.size()!=0)
            return vertices.get(index);
        else
            return null;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Vertex getAnchor() {
        return anchor;
    }

    public void setAnchor(Vertex anchor) {
        this.anchor = anchor;
    }

}
