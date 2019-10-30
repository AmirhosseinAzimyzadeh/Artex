package interfaces;

public interface Move<T> {
    T moveX(float amount);
    T moveY(float amount);
    T moveZ(float amount);
}
