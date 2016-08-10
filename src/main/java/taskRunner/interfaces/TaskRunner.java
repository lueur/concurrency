package taskRunner.interfaces;

import task.interfaces.Task;


public interface TaskRunner {
    <X, Y> X run(Task<X, Y> task, Y value);
}
