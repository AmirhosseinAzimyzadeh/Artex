package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import utility.GeoMath;
import utility.Statics;
import interfaces.Move;
import interfaces.Rotate;
import java.util.ArrayList;
import java.util.Collections;


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
            //TODO
            return this;
        }

        @Override
        public Builder moveY(float amount) {
            //TODO
            return this;
        }

        @Override
        public Builder moveZ(float amount) {
            //TODO
            return this;
        }

        @Override
        public Builder rotateX(float degree) {
            //TODO
            return this;
        }

        @Override
        public Builder rotateY(float degree) {
            //TODO
            return this;
        }

        @Override
        public Builder rotateZ(float degree) {
            //TODO
            return this;
        }

        public Polygon build(){
            return new Polygon(this);
        }
    }
}
