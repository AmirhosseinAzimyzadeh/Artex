package artexIO;

import artexCore.Face;
import artexCore.Vertex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArtexWriter {

    private String fileName;
    private Face[] faces;
//    private int vertexCounter;
    public static final String FILE_EXTENSION = ".obj";

    public ArtexWriter(String fileName, Face... faces){
        this.faces = faces;
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
}
