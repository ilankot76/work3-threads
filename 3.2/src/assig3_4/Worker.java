package assig3_4;

public class Worker implements Runnable {
    public enum Kind {
        A, B, C, D
    }

    private final Coordinator coordinator;
    private final Kind kind;

    public Worker(Coordinator coordinator, Kind kind) {
        this.coordinator = coordinator;
        this.kind = kind;
    }

    @Override
    public void run() {
        try {
            while (true) {
                switch (kind) {
                    case A -> coordinator.doA();
                    case B -> coordinator.doB();
                    case C -> coordinator.doC();
                    case D -> coordinator.doD();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
