package _examples_;

import artexIO.ArtexWriter;
import artexObjects.ComplexObject;
import artexObjects.Polygon;

import java.io.File;
import java.io.IOException;

public class ComplexObjectExample {
    public static void main(String[] args) throws IOException {
        // build base polygon for ComplexObject
        Polygon circleBase = new Polygon.Builder(100,10f).build();


        // build Complex Object by coping base
        ComplexObject c = new ComplexObject();
        c.rotationalCopyX(circleBase.getFace(), 50);
        c.rotationalCopyY(circleBase.getFace(), 50);

        //write object
        File dir = new File(".\\src\\_examples_");
        ArtexWriter writer = new ArtexWriter(dir,"test2",c.getFacesInArray());
        writer.write();
    }
}
