package utility;

import artexCore.Vertex;
/**
 * Statics will save most used variables
 *
 * @author Amirhossein Azimyzadeh
 * */
public final class Statics {
    public final static String FILE_SIGNATURE = "# Generated by Artex library \n";

    public static Vertex originVertex(){
        return new Vertex(0,0,0);
    }
}
