package assig3_4;

public class Task4Main {
    public static void main(String[] args) {
        Coordinator coordinator = new Coordinator();

        Thread t1 = new Thread(new Worker(coordinator, Worker.Kind.A), "t1");
        Thread t2 = new Thread(new Worker(coordinator, Worker.Kind.B), "t2");
        Thread t3 = new Thread(new Worker(coordinator, Worker.Kind.C), "t3");
        Thread t4 = new Thread(new Worker(coordinator, Worker.Kind.D), "t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
