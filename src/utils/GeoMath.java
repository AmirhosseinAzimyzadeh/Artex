package utils;

import artexCore.Face;
import artexCore.Vertex;

/**
 * Geometric Math class created for mathematical utility in other classes
 *
 * @author Amirhossein Azimyzadeh
 */
public class GeoMath {
    /**
     * Calculate location of each point for regular polygon in 3D space
     */
    public static Vertex[] regularPolygonVertices(int points, float radius) {
        Vertex[] result = new Vertex[points];
        float[] radians = new float[points];
        float degreeAmount = (float) (2 * Math.PI / points);
        float sum = 0;
        for (int i = 0; i < radians.length; i++) {
            sum += degreeAmount;
            radians[i] = sum;
        }
        for (int i = 0; i < result.length; i++) {
            float x = (float) (radius * Math.cos(radians[i]));
            float y = (float) (radius * Math.sin(radians[i]));
            float z = 0.0f;
            Vertex v = new Vertex(x, y, z);
            result[i] = v;
        }
        return result;
    }

    public static Vertex[] rotateX(float degree, Vertex... vertices) {
        Vertex[] result = new Vertex[vertices.length];
        for (int i = 0; i < result.length; i++) {

            double y = vertices[i].getY() * Math.cos(degree) - vertices[i].getZ() * Math.sin(degree);
            double z = vertices[i].getY() * Math.sin(degree) - vertices[i].getZ() * Math.cos(degree);

            result[i] = new Vertex(vertices[i].getX(), (float) y, (float) z);
        }
        return result;
    }

    public static Vertex[] rotateY(float degree, Vertex... vertices) {
        Vertex[] rotatedVertices = new Vertex[vertices.length];
        for (int i = 0; i < rotatedVertices.length; i++) {

            double x = vertices[i].getX() * Math.cos(degree) + vertices[i].getZ() * Math.sin(degree);
            double z = -vertices[i].getX() * Math.sin(degree) + vertices[i].getZ() * Math.cos(degree);

            rotatedVertices[i] = new Vertex((float) x, vertices[i].getY(), (float) z);
        }
        return rotatedVertices;
    }

    public static Vertex[] rotateZ(float degree, Vertex... vertices) {
        Vertex[] rotatedVertices = new Vertex[vertices.length];
        for (int i = 0; i < rotatedVertices.length; i++) {

            double x = vertices[i].getX() * Math.cos(degree) - vertices[i].getY() * Math.sin(degree);
            double y = vertices[i].getX() * Math.sin(degree) + vertices[i].getY() * Math.cos(degree);

            rotatedVertices[i] = new Vertex((float) x, (float) y, vertices[i].getZ());
        }
        return rotatedVertices;
    }

    public static Vertex[] rotate(float x, float y, float z, Vertex... vertices) {
        Vertex[] rotatedVertices = rotateX(x, vertices);
        rotatedVertices = rotateY(y, rotatedVertices);
        rotatedVertices = rotateZ(z, rotatedVertices);
        return rotatedVertices;
    }

    public static Vertex[] copyX(float distance, Vertex... vertices) {
        return copyVertices(Axis.X, distance, vertices);
    }

    public static Vertex[] copyY(float distance, Vertex... vertices) {
        return copyVertices(Axis.Y, distance, vertices);
    }

    public static Vertex[] copyZ(float distance, Vertex... vertices) {
        return copyVertices(Axis.Z, distance, vertices);
    }

    /**
     * handle linear Copy of faces in ComplexObject
     *
     * @param axis identify axis for copy
     */
    public static Face[] linearCopy(Face face, int numberOfCopies, float length, Axis axis) {
        if (numberOfCopies == 0)
            return new Face[]{face};

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

    /**
     * handle rotational Copy of faces in ComplexObject
     *
     * @param axis identify axis for copy
     */
    public static Face[] rotationalCopy(Face face, int numberOfCopies, Axis axis) {
        if (numberOfCopies == 0)
            return new Face[]{face};

        Face[] copiedFaces = new Face[numberOfCopies];
        float stepAmount = (float) (2 * Math.PI / numberOfCopies);
        float angle = stepAmount;

        for (int i = 0; i < numberOfCopies; i++) {
            switch (axis) {
                case X:
                    copiedFaces[i] = new Face(GeoMath.rotateX(angle, face.getVerticesInArray()));
                    break;
                case Y:
                    copiedFaces[i] = new Face(GeoMath.rotateY(angle, face.getVerticesInArray()));
                    break;
                case Z:
                    copiedFaces[i] = new Face(GeoMath.rotateZ(angle, face.getVerticesInArray()));
                    break;
            }
            angle += stepAmount;
        }

        return copiedFaces;
    }

    private static Vertex[] copyVertices(Axis axis, float distance, Vertex... vertices) {
        Vertex[] result = new Vertex[vertices.length];
        for (int i = 0; i < result.length; i++) {
            switch (axis) {
                case X:
                    result[i] = new Vertex(vertices[i].getX() + distance,
                            vertices[i].getY(),
                            vertices[i].getZ());
                    break;
                case Y:
                    result[i] = new Vertex(vertices[i].getX(),
                            vertices[i].getY() + distance,
                            vertices[i].getZ());
                    break;
                case Z:
                    result[i] = new Vertex(vertices[i].getX(),
                            vertices[i].getY(),
                            vertices[i].getZ() + distance);
                    break;
            }
        }
        return result;
    }

    /**
     * connecting @param bottom , @param top vertices to create Array of side Faces
     */
    public static Face[] createSideFaces(Vertex[] bottom, Vertex[] top) {
        if (bottom.length != top.length)
            throw new RuntimeException("bottom and top Array length are not equal !");

        int numberOfSides = bottom.length;
        Face[] result = new Face[numberOfSides];

        for (int i = 0; i < numberOfSides; i++) {
            Vertex[] vertices = new Vertex[4];
            if (i == bottom.length - 1) {
                vertices[0] = bottom[i];
                vertices[1] = bottom[0];
                vertices[2] = top[0];
                vertices[3] = top[i];
            } else {
                vertices[0] = bottom[i];
                vertices[1] = bottom[i + 1];
                vertices[2] = top[i + 1];
                vertices[3] = top[i];
            }
            result[i] = new Face(vertices);
        }
        return result;
    }

}
