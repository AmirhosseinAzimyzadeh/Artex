package artexIO;

import artexCore.Face;
import artexCore.Vertex;
import artexObjects.ComplexObject;
import artexObjects.Polygon;
import utils.Statics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArtexWriter {

    private String fileName;
    private Face[] faces;
    private static final String FILE_EXTENSION = ".obj";

    public ArtexWriter(String fileName, Face... faces){
        this.faces = faces;
        this.fileName = fileName;
    }

    public ArtexWriter(String fileName, ComplexObject complexObject){
        this.faces = complexObject.getFacesInArray();
        this.fileName = fileName;
    }

    public ArtexWriter(String fileName, Polygon... polygons){
        List<Face> faces = new ArrayList<>(polygons.length);
        faces = Arrays.stream(polygons).map(Polygon::getFace).collect(Collectors.toList());
        this.faces = faceListToArray(faces);
        this.fileName = fileName;
    }

    public void write() throws IOException {
        String fileContent = prepareString();
        File file = new File(fileName+FILE_EXTENSION);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(fileContent.getBytes());
        fos.close();
    }

    private String prepareString() {
        if(faces == null || faces.length==0)
            return "";

        StringBuilder str = new StringBuilder();
        int vertexCounter = 0;

        str.append(Statics.FILE_SIGNATURE);

        for(Face face: faces){
            for(Vertex vertex : face.getVertices()){
                str.append(vertex.toString()).append("\n");
            }

            str.append("f ");
            for(int i = vertexCounter+1 ;i <= vertexCounter+face.size() ; i++)
                str.append(i).append(" ");
            str.append("\n");
            vertexCounter +=face.size();
        }
        return str.toString();
    }

    private Face[] faceListToArray(List<Face> faces){
        Face[] result = new Face[faces.size()];
        for (int i = 0; i < faces.size(); i++) {
            result[i] = faces.get(i);
        }
        return result;
    }
}
