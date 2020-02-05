package interfaces;

public interface Rotate<T> {
    T rotateX(float degree);

    T rotateY(float degree);

    T rotateZ(float degree);

    T rotate(float X, float Y, float Z);
}
