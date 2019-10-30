package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import utility.Utility;
import interfaces.Move;
import interfaces.Rotate;
import interfaces.Scale;


public class Polygon {
    private Vertex anchor;
    private Face face;
    private float radius;
    private int sides;

    private Polygon(Polygon.Builder builder){
        this.anchor = builder.anchor;
        this.face = builder.face;
        this.sides =builder.sides;
    }

    public Vertex getAnchor() {
        return anchor;
    }

    public Face getFace() {
        return face;
    }

    public int getNumberOfSides() {
        return sides;
    }

    public static class Builder implements Move<Polygon.Builder>, Rotate<Polygon.Builder>,
            Scale<Polygon.Builder> {
        private Vertex anchor;
        private Face face;
        private float radius;
        private int sides;

        public Builder(Vertex anchor, int sides, float radius){
            this.anchor = anchor;
            this.sides = sides;
            this.radius = radius;
        }

        public Builder(int sides, float radius){
            this.anchor = Utility.ORIGIN_VERTEX;
            this.sides = sides;
            this.radius = radius;
        }

        @Override
        public Builder moveX(float amount) {
            //TODO
            return null;
        }

        @Override
        public Builder moveY(float amount) {
            //TODO
            return null;
        }

        @Override
        public Builder moveZ(float amount) {
            //TODO
            return null;
        }

        @Override
        public Builder rotateX(float degree) {
            //TODO
            return null;
        }

        @Override
        public Builder rotateY(float degree) {
            //TODO
            return null;
        }

        @Override
        public Builder rotateZ(float degree) {
            //TODO
            return null;
        }

        @Override
        public Builder resize(float amount) {
            //TODO
            return null;
        }

        public Polygon build(){
            return new Polygon(this);
        }
    }
}
