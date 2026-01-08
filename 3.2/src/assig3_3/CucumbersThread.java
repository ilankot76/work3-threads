package assig3_3;

public class CucumbersThread extends Thread {

    private final SlicerMachine machine;

    public CucumbersThread(SlicerMachine machine) {
        super("CucumbersThread");
        this.machine = machine;
    }

    @Override
    public void run() {
        try {
            while (!machine.isDone()) {
                machine.addOneCucumber();
            }
        } catch (InterruptedException e) {
            // allow thread to terminate
            Thread.currentThread().interrupt();
        }
    }
}
