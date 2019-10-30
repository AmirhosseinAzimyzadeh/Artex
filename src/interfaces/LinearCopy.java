package interfaces;

public interface LinearCopy<T> {
    T copyX(int count, float maxDistance);
    T copyY(int count, float maxDistance);
    T copyZ(int count, float maxDistance);
}
