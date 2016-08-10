package taskRunner.impl;

import task.interfaces.Task;
import taskRunner.interfaces.TaskRunner;

import java.util.LinkedList;
import java.util.Queue;


public class TaskRunnerImpl<X,Y> implements TaskRunner {
//    private Queue<Task> queue = new taskRunner.impl.Queue<>(1000);
    private Queue<Task> queue = new LinkedList<>();
    private int numberOfThreads = 10;

    public TaskRunnerImpl(int numberOfThreads) {
       this.numberOfThreads = numberOfThreads;
    }

    public <X, Y> X run(Task<X, Y> task, Y value) {
        synchronized (queue) {
            queue.offer(task);
            queue.notify();
//        while (queue.peek()!=task) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        queue.poll();
//        notifyAll();
//        return task.run(value);
        }
        synchronized (task) {
            return task.run(value);
        }
    }

    public void executeTasks() {
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new Runnable() {
                public void run() {
                    synchronized (queue) {
                        while (queue.isEmpty())
                            try {
                                queue.wait();

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }
                    Task task = queue.poll();
                    task.notify();
                }
            }).start();
        }
    }

    //while (queue.isEmpty())
    // queue.wait();
    //Task t = queue.get();

    //t.notify();




//    public <X, Y> void addTaskToQueue(Task<X, Y> task) {
//        queue.add(task);
//    }
}
