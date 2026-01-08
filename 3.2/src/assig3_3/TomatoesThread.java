package assig3_3;

public class TomatoesThread extends Thread {

    private final SlicerMachine machine;

    public TomatoesThread(SlicerMachine machine) {
        super("TomatoesThread");
        this.machine = machine;
    }

    @Override
    public void run() {
        try {
            while (!machine.isDone()) {
                machine.addOneTomato();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
