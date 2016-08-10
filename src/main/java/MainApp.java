import client.Client;
import taskRunner.impl.TaskRunnerImpl;
import taskRunner.interfaces.TaskRunner;

public class MainApp {

    private static final int CLIENTS = 10;

    public static void main(String... args) {
        TaskRunner taskRunner = new TaskRunnerImpl<>(7);
        for (int i=0; i < CLIENTS; i++) {
            new Thread(new Client(taskRunner)).start();
        }
    }
}
