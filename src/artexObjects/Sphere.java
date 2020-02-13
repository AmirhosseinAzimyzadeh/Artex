package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
/**
 * @see Face
 * @see utils.GeoMath
 * @author Amirhossein Azimyzadeh
 * */
public class Sphere {
    private Face[] faces;
    private int segments;

    public Sphere(int segments, float radius){

    }

    public ComplexObject convertToComplexObject(){
        return new ComplexObject(this.faces);
    }
}
