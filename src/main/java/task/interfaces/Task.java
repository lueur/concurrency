package task.interfaces;

public interface Task<X, Y> {
    X run(Y value);
}
