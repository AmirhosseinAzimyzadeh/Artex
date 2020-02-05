package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import interfaces.Move;
import interfaces.Rotate;
import utils.Axis;
import utils.GeoMath;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ComplexObject is an object with multiple faces
 *
 * @author Amirhossein Azimyzadeh
 */
public class ComplexObject implements Move<ComplexObject>, Rotate<ComplexObject> {
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

    public int size() {
        return faces.size();
    }

    public void linearCopyX(Face face, int numberOfCopies, float length) {
        Collections.addAll(this.faces, GeoMath.linearCopy(face, numberOfCopies, length, Axis.X));
    }

    public void linearCopyY(Face face, int numberOfCopies, float length) {
        Collections.addAll(this.faces, GeoMath.linearCopy(face, numberOfCopies, length, Axis.Y));
    }

    public void linearCopyZ(Face face, int numberOfCopies, float length) {
        Collections.addAll(this.faces, GeoMath.linearCopy(face, numberOfCopies, length, Axis.Z));
    }

    public void rotationalCopyX(Face face, int numberOfCopies) {
        Collections.addAll(this.faces, GeoMath.rotationalCopy(face, numberOfCopies, Axis.X));
    }

    public void rotationalCopyY(Face face, int numberOfCopies) {
        Collections.addAll(this.faces, GeoMath.rotationalCopy(face, numberOfCopies, Axis.Y));
    }

    public void rotationalCopyZ(Face face, int numberOfCopies) {
        Collections.addAll(this.faces, GeoMath.rotationalCopy(face, numberOfCopies, Axis.Z));
    }

    @Override
    public ComplexObject moveX(float amount) {
        return this.move(amount, 0, 0);
    }

    @Override
    public ComplexObject moveY(float amount) {
        return this.move(0, amount, 0);
    }

    @Override
    public ComplexObject moveZ(float amount) {
        return this.move(0, 0, amount);
    }

    @Override
    public ComplexObject move(float x, float y, float z) {
        for (Face face : faces) {
            for (Vertex vertex : face.getVertices()) {
                vertex.addX(x);
                vertex.addY(y);
                vertex.addZ(z);
            }
        }
        return this;
    }

    @Override
    public ComplexObject rotateX(float amount) {
        return rotate(amount, 0, 0);
    }

    @Override
    public ComplexObject rotateY(float amount) {
        return rotate(0, amount, 0);
    }

    @Override
    public ComplexObject rotateZ(float amount) {
        return rotate(0, 0, amount);
    }

    @Override
    public ComplexObject rotate(float x, float y, float z) {
        for (int i = 0; i < this.size(); i++) {
            Face removedFace = faces.remove(i);
            faces.add(i, new Face(GeoMath.rotate(x, y, z, removedFace.getVerticesInArray())));
        }
        return this;
    }
}