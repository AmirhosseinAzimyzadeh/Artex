package utility;

import artexCore.Face;
import artexCore.Vertex;

import java.awt.event.MouseAdapter;

/**
 * Geometric Math class created for mathematical utility in other classes
 *
 * @author Amirhossein Azimyzadeh
 */
public class GeoMath {

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

}
