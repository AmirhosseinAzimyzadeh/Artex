package utils;

import artexCore.Vertex;

import java.util.ArrayList;

public class VertexContainer  {
    private ArrayList<Vertex> data;

    public VertexContainer(int initialCapacity){
        this.data = new ArrayList<>(initialCapacity);
    }

    public VertexContainer(){
        this.data = new ArrayList<>();
    }

    public int size(){
        return this.data.size();
    }

    public void add(Vertex vertex){
        for (int i = 0; i < size(); i++) {
            if(vertex.equals(this.data.get(i)))
                return;
        }
        this.data.add(vertex);
    }

    public Vertex getVertex(int i){
        return this.data.get(i);
    }

    public Vertex contain(Vertex vertex){
        for (int i = 0; i < size(); i++) {
            if(vertex.equals(this.data.get(i)))
                return this.data.get(i);
        }
        return null;
    }

    public Vertex[] getVertices(){
        Vertex[] result = new Vertex[size()];
        for (int i = 0; i < size(); i++) {
            result[i] = this.data.get(i);
        }
        return result;
    }
}
