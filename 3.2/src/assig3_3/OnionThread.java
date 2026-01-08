package assig3_3;

public class OnionThread extends Thread {

    private final SlicerMachine machine;

    public OnionThread(SlicerMachine machine) {
        super("OnionThread");
        this.machine = machine;
    }

    @Override
    public void run() {
        try {
            while (!machine.isDone()) {
                machine.addOneOnion();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
