package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import utility.GeoMath;
import utility.Statics;
import interfaces.Move;
import interfaces.Rotate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Polygon class creat simple 2D shape (regular-polygon)
 * @see GeoMath
 * you can also creat circle by increasing "side" value
 *
 * @author Amirhossein Azimyzadeh
 * */

public class Polygon {
    private Vertex anchor;
    private Face face;

    private Polygon(Polygon.Builder builder){
        this.anchor = builder.anchor;
        this.face = builder.face;
    }

    public Vertex getAnchor() {
        return anchor;
    }

    public Face getFace() {
        return face;
    }

    public static class Builder implements Move<Polygon.Builder>, Rotate<Polygon.Builder>{
        private Vertex anchor;
        private Face face;
        private float radius;
        private int sides;


        public Builder(int sides, float radius){
            this.anchor = Statics.ORIGIN_VERTEX;
            this.sides = sides;
            this.radius = radius;
            this.face = buildFace();
        }

        private Face buildFace() {
            ArrayList<Vertex> vertices = new ArrayList<>();
            Collections.addAll(vertices,GeoMath.regularPolygonVertices(sides,radius));
            return new Face(vertices);
        }

        public Builder setAnchor(Vertex anchor){
            this.anchor = anchor;
            return this;
        }

        @Override
        public Builder moveX(float amount) {
            for (Vertex vertex : this.face.getVertices()) {
                vertex.addX(amount);
            }
            this.anchor.addX(amount);
            return this;
        }

        @Override
        public Builder moveY(float amount) {
            for (Vertex vertex : this.face.getVertices()) {
                vertex.addY(amount);
            }
            this.anchor.addY(amount);
            return this;
        }

        @Override
        public Builder moveZ(float amount) {
            for (Vertex vertex : this.face.getVertices()) {
                vertex.addZ(amount);
            }
            this.anchor.addZ(amount);
            return this;
        }

        public Builder move(float amountX, float amountY, float amountZ){
            return this.moveX(amountX).moveY(amountY).moveZ(amountZ);
        }

        @Override
        public Builder rotateX(float degree) {
            this.face = new Face(this.anchor, GeoMath.rotateXFace(this.face, this.anchor, degree));
            return this;
        }

        @Override
        public Builder rotateY(float degree) {
            this.face = new Face(this.anchor, GeoMath.rotateYFace(this.face, this.anchor, degree));
            return this;
        }

        @Override
        public Builder rotateZ(float degree) {
            this.face = new Face(this.anchor, GeoMath.rotateZFace(this.face, this.anchor, degree));
            return this;
        }

        public Polygon build(){
            return new Polygon(this);
        }
    }
}
