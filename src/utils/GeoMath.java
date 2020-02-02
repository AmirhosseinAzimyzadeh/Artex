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
     * */
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

    public static Vertex[] rotateXFace(Face face, float degree) {
        Vertex[] result = new Vertex[face.size()];
        for (int i = 0; i < result.length; i++) {

            double y = face.getVertex(i).getY() * Math.cos(degree) - face.getVertex(i).getZ() * Math.sin(degree);
            double z = face.getVertex(i).getY() * Math.sin(degree) - face.getVertex(i).getZ() * Math.cos(degree);

            result[i] = new Vertex(face.getVertex(i).getX(), (float) y, (float) z);
        }
        return result;
    }

    public static Vertex[] rotateYFace(Face face, float degree) {
        Vertex[] result = new Vertex[face.size()];
        for (int i = 0; i < result.length; i++) {

            double x = face.getVertex(i).getX() * Math.cos(degree) + face.getVertex(i).getZ() * Math.sin(degree);
            double z = -face.getVertex(i).getX() * Math.sin(degree) + face.getVertex(i).getZ() * Math.cos(degree);

            result[i] = new Vertex((float) x, face.getVertex(i).getY(), (float) z);
        }
        return result;
    }

    public static Vertex[] rotateZFace(Face face, float degree) {
        Vertex[] result = new Vertex[face.size()];
        for (int i = 0; i < result.length; i++) {

            double x = face.getVertex(i).getX() * Math.cos(degree) - face.getVertex(i).getY() * Math.sin(degree);
            double y = face.getVertex(i).getX() * Math.sin(degree) + face.getVertex(i).getY() * Math.cos(degree);

            result[i] = new Vertex((float) x, (float) y, face.getVertex(i).getZ());
        }
        return result;
    }

    public static Vertex[] copyX(float distance, Vertex... vertices){
        return copyVertices(Axis.X, distance, vertices);
    }
    public static Vertex[] copyY(float distance, Vertex... vertices){
        return copyVertices(Axis.Y, distance, vertices);
    }
    public static Vertex[] copyZ(float distance, Vertex... vertices){
        return copyVertices(Axis.Z, distance,vertices);
    }

    private static Vertex[] copyVertices(Axis axis, float distance, Vertex... vertices){
        Vertex[] result = new Vertex[vertices.length];
        for (int i = 0; i < result.length; i++) {
            switch (axis){
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

}
