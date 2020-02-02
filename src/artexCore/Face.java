package artexCore;

import utils.Statics;

import java.util.ArrayList;
import java.util.Collections;

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
    private String name;

    public Face(ArrayList<Vertex> vertices, Vertex anchor) {
        this.vertices = vertices;
        this.anchor = anchor;
    }

    public Face(Vertex anchor, Vertex ... vertices){
        this.vertices = new ArrayList<>(vertices.length);
        Collections.addAll(this.vertices,vertices);
        this.anchor = anchor;
    }

    public Face(Vertex ...vertices){
        this.vertices = new ArrayList<>(vertices.length);
        Collections.addAll(this.vertices, vertices);
        this.anchor = Statics.originVertex();
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

    public Vertex[] getVerticesInArray() {
        Vertex[] result = new Vertex[this.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = vertices.get(i);
        }
        return result;
    }

    public Vertex getVertex(int index){
        if(vertices.size()!=0)
            return vertices.get(index);
        else
            return null;
    }
    /**
     * @return number of vertices in the face
     * */
    public int size(){
        return vertices.size();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
