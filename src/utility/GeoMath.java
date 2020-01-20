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
        float[] radians = new float[points];
        float degreeAmount = (float) (2*Math.PI/points);
        float sum=0;
        for(int i = 0 ;i<radians.length ;i++){
            sum += degreeAmount;
            radians[i] = sum;
        }
        for (int i = 0; i < result.length; i++) {
            float x = (float) (radius* Math.cos( radians[i]));
            float y = (float) (radius* Math.sin( radians[i]));
            float z = 0.0f;
            Vertex v = new Vertex(x,y,z);
            result[i] = v;
        }
        return result;
    }

    public static Vertex[] rotateXFace(Face face, Vertex anchor, float degree){
        Vertex[] result = new Vertex[face.size()];
        //TODO
        for(int i=0; i<result.length; i++){
            // radius calculation
            double x = face.getVertex(i).getX();
            double y = face.getVertex(i).getY() * Math.cos(degree) - face.getVertex(i).getZ() * Math.sin(degree);
            double z = face.getVertex(i).getY() * Math.cos(degree) + face.getVertex(i).getZ() * Math.sin(degree);
            Vertex newVertex = new Vertex((float) x, (float) y, (float) z);
            result[i] = newVertex;
        }
        return result;
    }

    public static Vertex[] rotateYFace(Face face, Vertex anchor, float degree){
        Vertex[] result = new Vertex[face.size()];
        //TODO
        return result;
    }

    public static Vertex[] rotateZFace(Face face, Vertex anchor, float degree){
        Vertex[] result = new Vertex[face.size()];
        //TODO
        return result;
    }

}
