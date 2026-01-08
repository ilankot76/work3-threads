package assig3_3;

/**
 * Thread-safe salad slicer machine.
 *
 * Constraints (from assignment):
 * - Add cucumber only if there are < 5 cucumbers in the chamber
 * - Add tomato only if there are < 3 tomatoes in the chamber
 * - Add onion only if there is no onion in the chamber
 * - Slice (prepare a salad) only if there are 1 onion + 5 cucumbers + 3
 * tomatoes
 * - After preparing N salads, all threads stop and the program exits
 */
public class SlicerMachine {

	private int numOfCucumbers = 0;
	private int numOfTomatoes = 0;
	private int numOfOnions = 0;
	private int numOfPreparedSalads = 0;

	private final int cucumbersNeededForOneSalad = 5;
	private final int tomatoesNeededForOneSalad = 3;
	private final int onionsNeededForOneSalad = 1;

	private final int targetSalads;
	private boolean done;

	public SlicerMachine(int targetSalads) {
		this.targetSalads = Math.max(0, targetSalads);
		this.done = (this.targetSalads == 0);
	}

	public synchronized boolean isDone() {
		return done;
	}

	// add one cucumber into the slicer chamber
	public synchronized void addOneCucumber() throws InterruptedException {
		while (!done && numOfCucumbers >= cucumbersNeededForOneSalad) {
			wait();
		}
		if (done) {
			return;
		}

		System.out.println("adding one cucumber to the machine");
		numOfCucumbers++;
		notifyAll();
	}

	// add one onion into the slicer chamber
	public synchronized void addOneOnion() throws InterruptedException {
		while (!done && numOfOnions >= onionsNeededForOneSalad) {
			wait();
		}
		if (done) {
			return;
		}

		System.out.println("adding one onion to the machine");
		numOfOnions++;
		notifyAll();
	}

	// add one tomato into the slicer chamber
	public synchronized void addOneTomato() throws InterruptedException {
		while (!done && numOfTomatoes >= tomatoesNeededForOneSalad) {
			wait();
		}
		if (done) {
			return;
		}

		System.out.println("adding one tomato to the machine");
		numOfTomatoes++;
		notifyAll();
	}

	// if there are enough vegetables in the slicer chamber, make another salad
	public synchronized void sliceVegetables() throws InterruptedException {
		while (!done && !hasIngredientsForSalad()) {
			wait();
		}
		if (done) {
			return;
		}

		makeNewSalad();
		notifyAll();
	}

	private boolean hasIngredientsForSalad() {
		return (numOfOnions >= onionsNeededForOneSalad)
				&& (numOfCucumbers >= cucumbersNeededForOneSalad)
				&& (numOfTomatoes >= tomatoesNeededForOneSalad);
	}

	private void makeNewSalad() {
		System.out.println("== preparing one more salad ==");
		numOfPreparedSalads++;

		// update stock
		numOfTomatoes -= tomatoesNeededForOneSalad;
		numOfCucumbers -= cucumbersNeededForOneSalad;
		numOfOnions -= onionsNeededForOneSalad;

		if (numOfPreparedSalads >= targetSalads) {
			done = true;
		}
	}

	public synchronized int getNumOfPreparedSalads() {
		return numOfPreparedSalads;
	}
}
