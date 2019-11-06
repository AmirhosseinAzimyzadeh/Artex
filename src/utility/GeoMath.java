package utility;

import artexCore.Face;
import artexCore.Vertex;
/**
 * Geometric Math class created for mathematical utility in other classes
 * @author Amirhossein Azimyzadeh
 * */
public class GeoMath {

    public static Vertex[] regularPolygonVertices(int points, float radius){
        Vertex[] result = new Vertex[points];
        float[] degrees = new float[points];
        float degreeAmount = (float) (2*Math.PI/points);
        float sum=0;
        for(int i = 0 ;i<degrees.length ;i++){
            sum += degreeAmount;
            degrees[i] = sum;
        }
        for (int i = 0; i < result.length; i++) {
            float x = (float) (radius* Math.cos( degrees[i]));
            float y = (float) (radius* Math.sin( degrees[i]));
            float z = 0.0f;
            Vertex v = new Vertex(x,y,z);
            result[i] = v;
        }
        return result;
    }

    public static Vertex[] rotateXFace(Face face, Vertex anchor){
        Vertex[] result = new Vertex[face.size()];
        return result;
    }

    public static Vertex[] rotateYFace(Face face, Vertex anchor){
        Vertex[] result = new Vertex[face.size()];
        return result;
    }

    public static Vertex[] rotateZFace(Face face, Vertex anchor){
        Vertex[] result = new Vertex[face.size()];
        return result;
    }

}
