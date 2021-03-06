package artexObjects;

import artexCore.Face;
import artexCore.Vertex;
import interfaces.Move;
import interfaces.Rotate;
import utils.GeoMath;
import utils.Util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Polygon class create a simple 2D shape (regular-polygon)
 *
 * @author Amirhossein Azimyzadeh
 * @see GeoMath
 * you can also create circle by increasing "side" value in "Builder" Class
 */

public class Polygon {
    private Vertex anchor;
    private Face face;

    private Polygon(Polygon.Builder builder) {
        this.anchor = builder.anchor;
        this.face = builder.face;
    }

    public Vertex getAnchor() {
        return anchor;
    }

    public Face getFace() {
        return face;
    }

    public static class Builder implements Move<Polygon.Builder>, Rotate<Polygon.Builder> {
        private Vertex anchor;
        private Face face;
        private float radius;
        private int sides;


        public Builder(int sides, float radius) {
            this.anchor = Util.originVertex();
            this.sides = sides;
            this.radius = radius;
            this.face = buildFace();
        }

        private Face buildFace() {
            ArrayList<Vertex> vertices = new ArrayList<>();
            Collections.addAll(vertices, GeoMath.regularPolygonVertices(sides, radius));
            return new Face(vertices);
        }

        public Builder setAnchor(Vertex anchor) {
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

        /**
         * Move whole Polygon with
         */
        @Override
        public Builder move(float amountX, float amountY, float amountZ) {
            return this.moveX(amountX).moveY(amountY).moveZ(amountZ);
        }

        /**
         * Rotate Polygon around X axis
         *
         * @param amount unit is Radian
         */
        @Override
        public Builder rotateX(float amount) {
            this.face = new Face(this.anchor, GeoMath.rotateX(amount, this.face.getVerticesInArray()));
            return this;
        }

        /**
         * Rotate Polygon around Y axis
         *
         * @param amount unit is Radian
         */
        @Override
        public Builder rotateY(float amount) {
            this.face = new Face(this.anchor, GeoMath.rotateY(amount, this.face.getVerticesInArray()));
            return this;
        }

        /**
         * Rotate Polygon around Z axis
         *
         * @param amount unit is Radian
         */
        @Override
        public Builder rotateZ(float amount) {
            this.face = new Face(this.anchor, GeoMath.rotateZ(amount, this.face.getVerticesInArray()));
            return this;
        }

        @Override
        public Builder rotate(float X, float Y, float Z) {
            return this;
        }

        public Polygon build() {
            return new Polygon(this);
        }
    }
}
