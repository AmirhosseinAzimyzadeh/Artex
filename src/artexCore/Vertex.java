package artexCore;

import interfaces.Move;

import java.util.Objects;

/**
 * vertices are points for 3D vectors
 * vertex id shows line number in final file and use for facing vertices
 * @see Face
 *
 * @author Amirhossein Azimyzadeh
 * */

public class Vertex implements Move {
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
    /**
     * @return new (copy) Vertex with new X value
     * */
    @Override
    public Vertex moveX(float amount) {
        return new Vertex(x+amount,y,z);
    }
    /**
     * @return new (copy) Vertex with new Y value
     * */
    @Override
    public Vertex moveY(float amount) {
        return new Vertex(x,y+amount,z);
    }
    /**
     * @return new (copy) Vertex with new Z value
     * */
    @Override
    public Vertex moveZ(float amount) {
        return new Vertex(x,y,z+amount);
    }
    /**
     * @return new (copy) Vertex with new values
     * */
    @Override
    public Vertex move(float x, float y, float z){
        return new Vertex(this.x + x,this.y + y, this.z +z);
    }

    @Override
    public String toString() {
        return "v "+x+" "+y+" "+z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return Float.compare(vertex.x, x) == 0 &&
                Float.compare(vertex.y, y) == 0 &&
                Float.compare(vertex.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
