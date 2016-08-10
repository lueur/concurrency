package task.impl;

import task.interfaces.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TaskImpl<X, Y> implements Task<X, Y> {

    private X x;
    private Y y;

    public TaskImpl(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X run(Y value) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x;
    }
}

