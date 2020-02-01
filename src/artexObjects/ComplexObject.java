package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import utils.Axis;
import utils.GeoMath;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ComplexObject is an object with multiple faces
 *
 * @author Amirhossein
 */
public class ComplexObject {
    private ArrayList<Face> faces;

    public ComplexObject(Face... faces) {
        this.faces = new ArrayList<>(faces.length);
        Collections.addAll(this.faces, faces);
    }

    public ComplexObject() {
        this.faces = new ArrayList<>();
    }

    public ComplexObject(ArrayList<Face> faces) {
        this.faces = faces;
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    public Face getFace(int i) {
        return faces.get(i);
    }

    public Face[] getFacesInArray() {
        Face[] faces = new Face[this.faces.size()];
        for (int i = 0; i < faces.length; i++) {
            faces[i] = this.faces.get(i);
        }
        return faces;
    }

    public void linearCopyX(Face face, int numberOfCopies, float length) {
        Collections.addAll(this.faces, linearCopy(face, numberOfCopies, length, Axis.X));
    }

    public void linearCopyY(Face face, int numberOfCopies, float length) {
        Collections.addAll(this.faces, linearCopy(face, numberOfCopies, length, Axis.Y));
    }

    public void linearCopyZ(Face face, int numberOfCopies, float length) {
        Collections.addAll(this.faces, linearCopy(face, numberOfCopies, length, Axis.Z));
    }

    public void rotationalCopyX(Face face, int numberOfCopies) {
        Collections.addAll(this.faces, rotationalCopy(face, numberOfCopies, Axis.X));
    }

    public void rotationalCopyY(Face face, int numberOfCopies) {
        Collections.addAll(this.faces, rotationalCopy(face, numberOfCopies, Axis.Y));
    }

    public void rotationalCopyZ(Face face, int numberOfCopies) {
        Collections.addAll(this.faces, rotationalCopy(face, numberOfCopies, Axis.Z));
    }
    /**
     * handle rotational Copy of faces in ComplexObject
     *
     * @param axis identify axis for copy
     */
    private Face[] rotationalCopy(Face face, int numberOfCopies, Axis axis) {
        Face[] copiedFaces = new Face[numberOfCopies];
        float stepAmount = (float) (2 * Math.PI / numberOfCopies);
        float angle = stepAmount;

        for (int i = 0; i < numberOfCopies; i++) {
            switch (axis) {
                case X:
                    copiedFaces[i] = new Face(GeoMath.rotateXFace(face, angle));
                    break;
                case Y:
                    copiedFaces[i] = new Face(GeoMath.rotateYFace(face, angle));
                    break;
                case Z:
                    copiedFaces[i] = new Face(GeoMath.rotateZFace(face, angle));
                    break;
            }
            angle += stepAmount;
        }

        return copiedFaces;
    }

    /**
     * handle linear Copy of faces in ComplexObject
     *
     * @param axis identify axis for copy
     */
    private Face[] linearCopy(Face face, int numberOfCopies, float length, Axis axis) {
        float stepAmount = length / numberOfCopies;
        Face[] copiedFaces = new Face[numberOfCopies];

        for (int i = 0; i < numberOfCopies; i++) {
            Vertex[] vertices = new Vertex[face.size()];
            for (int j = 0; j < face.size(); j++) {
                switch (axis) {
                    case X:
                        vertices[j] = (i != 0) ? copiedFaces[i - 1].getVertex(j).moveX(stepAmount)
                                : face.getVertex(j).moveX(stepAmount);
                        break;
                    case Y:
                        vertices[j] = (i != 0) ? copiedFaces[i - 1].getVertex(j).moveY(stepAmount)
                                : face.getVertex(j).moveY(stepAmount);
                        break;
                    case Z:
                        vertices[j] = (i != 0) ? copiedFaces[i - 1].getVertex(j).moveZ(stepAmount)
                                : face.getVertex(j).moveZ(stepAmount);
                        break;
                }
            }
            copiedFaces[i] = new Face(vertices);
        }

        return copiedFaces;
    }
}
