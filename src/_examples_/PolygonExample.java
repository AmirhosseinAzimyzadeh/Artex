package _examples_;

import artexIO.ArtexWriter;
import artexObjects.Polygon;

import java.io.File;
import java.io.IOException;

public class PolygonExample {
    public static void main(String[] args) throws IOException {
        // build simple Polygon
        Polygon p1 = new Polygon.Builder(6,10.5f).build();
        // build Polygon and then rotate it
        Polygon p2 = new Polygon.Builder(10,6f).rotateX((float) Math.PI/2.0f).build();

        //write object
        File dir = new File(".\\src\\_examples_");
        ArtexWriter writer = new ArtexWriter(dir,"test1",p1.getFace(),p2.getFace());
        writer.write();
    }
}
