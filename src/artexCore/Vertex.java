package artexCore;

/**
 * vertices are points for 3D vectors
 * vertex id shows line number in final file and use for facing vertices
 * @see Face
 *
 * @author Amirhossein Azimyzadeh
 * */

public class Vertex {
    private float x;
    private float y;
    private float z;
    private int id;

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex(float x, float y, float z, int id) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public int getId() {
        return id;
    }

    public void addX(float amount){
        this.x += amount;
    }

    public void addY(float amount){
        this.y += amount;
    }

    public void addZ(float amount){
        this.z += amount;
    }
    /**
     * only use when reading from .obj file or reading from file
     * */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "v "+x+" "+y+" "+z;
    }
}
