package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import utils.Axis;

public class Cube extends Cylindrical {
    public Cube(float height, float width, float length) {
        super(createBase(width, length), height, Axis.Z);
    }

    private static Face createBase(float width, float length) {
        //TODO : complete the cube
        Vertex[] baseVertices = new Vertex[4];
        baseVertices[0] = new Vertex(width / 2, length / 2, 0);
        baseVertices[1] = new Vertex(width / 2, -(length / 2), 0);
        baseVertices[2] = new Vertex(-(width / 2), -(length / 2), 0);
        baseVertices[3] = new Vertex(-(width / 2), length / 2, 0);
        return new Face(baseVertices);
    }

}
