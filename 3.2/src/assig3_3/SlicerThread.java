package assig3_3;

public class SlicerThread extends Thread {

    private final SlicerMachine machine;

    public SlicerThread(SlicerMachine machine) {
        super("SlicerThread");
        this.machine = machine;
    }

    @Override
    public void run() {
        try {
            while (!machine.isDone()) {
                machine.sliceVegetables();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
