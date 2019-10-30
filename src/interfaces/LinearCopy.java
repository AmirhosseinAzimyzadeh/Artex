package interfaces;

public interface LinearCopy<T> {
    public T copyX(int count, float maxDistance);
    public T copyY(int count, float maxDistance);
    public T copyZ(int count, float maxDistance);
}
