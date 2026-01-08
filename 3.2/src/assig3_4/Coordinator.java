package task4;

/*
 * Task 4 solution (HW3):
 * Required order:
 * - First: A exactly 3 times (t1)
 * - Then repeat forever: B* (t2) -> C twice (t3) -> D* (t4)
 *   Where B* and D* are not predetermined; they depend on scheduling.
 */
public class Coordinator {

    // Phases:
    // A_ONLY: only t1 can run (exactly 3 times total).
    // B_MUST: only t2 can run once (guarantee at least one B).
    // B_OR_C: t2 can run B repeatedly OR t3 can "claim" starting C-sequence.
    // C_ONLY: only t3 runs exactly 2 times.
    // D_MUST: only t4 can run once (guarantee at least one D).
    // D_OR_B: t4 can run D repeatedly OR t2 can "claim" starting the next B-section.
    private enum Phase { A_ONLY, B_MUST, B_OR_C, C_ONLY, D_MUST, D_OR_B }

    private Phase phase = Phase.A_ONLY;

    private int aRemaining = 3;
    private int cRemaining = 0;

    public synchronized void doA() throws InterruptedException {
        while (phase != Phase.A_ONLY) {
            wait();
        }
        System.out.print("A");

        aRemaining--;
        if (aRemaining == 0) {
            phase = Phase.B_MUST;
        }
        notifyAll();
    }

    public synchronized void doB() throws InterruptedException {
        while (!(phase == Phase.B_MUST || phase == Phase.B_OR_C || phase == Phase.D_OR_B)) {
            wait();
        }

        // If we are in D_OR_B, t2 is allowed to end the D-section and start a new B-section.
        if (phase == Phase.D_OR_B) {
            System.out.print("B");
            phase = Phase.B_OR_C; // after at least one B, allow t3 to claim C
            notifyAll();
            return;
        }

        // B_MUST or B_OR_C
        System.out.print("B");
        if (phase == Phase.B_MUST) {
            phase = Phase.B_OR_C;
        }
        notifyAll();
    }

    public synchronized void doC() throws InterruptedException {
        while (!(phase == Phase.B_OR_C || phase == Phase.C_ONLY)) {
            wait();
        }

        // Claim the C^2 sequence if we're still in the open B_OR_C phase.
        if (phase == Phase.B_OR_C) {
            phase = Phase.C_ONLY;
            cRemaining = 2;
        }

        System.out.print("C");
        cRemaining--;

        if (cRemaining == 0) {
            phase = Phase.D_MUST; // require at least one D
        }
        notifyAll();
    }

    public synchronized void doD() throws InterruptedException {
        while (!(phase == Phase.D_MUST || phase == Phase.D_OR_B)) {
            wait();
        }

        System.out.print("D");

        if (phase == Phase.D_MUST) {
            phase = Phase.D_OR_B;
        }
        notifyAll();
    }
}
