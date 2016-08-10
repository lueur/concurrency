package client;


import task.impl.TaskImpl;
import task.interfaces.Task;
import taskRunner.interfaces.TaskRunner;

public class Client implements Runnable {
    private TaskRunner taskRunner;
    private static int counter;
    private final int id = counter++;

    public Client(TaskRunner taskRunner) {
        this.taskRunner = taskRunner;
    }

    private  <X,Y> Task<X,Y> generateTask(X x, Y y) {
        return new TaskImpl<X, Y>(x, y);
    }

    private void generateTasks() {
        for (int i=1; i< 10000; i++) {
            Task<String, Integer> task = generateTask("task" + i, i);
            String result = taskRunner.run(task, i);
            System.out.println("Client " + id + "; task " + i + " executed. Result is "+ result);

        }
    }

    @Override
    public void run() {
        generateTasks();
    }


    //queue.add(task);
    //queue.notify();
    //task.wait();



}
