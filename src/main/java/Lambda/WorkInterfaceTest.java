package Lambda;

/**
 * Created by eli9 on 3/16/2017.
 */
@FunctionalInterface
interface WorkInterface {
    public void doSomeWork();
}

public class WorkInterfaceTest {
    public static void execute(WorkInterface worker){
        worker.doSomeWork();
    }

    public static void main(String[] args) {
        execute(() -> System.out.println("Worker invoke using lambda"));
    }
}