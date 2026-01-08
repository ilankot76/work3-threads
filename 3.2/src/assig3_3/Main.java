package assig3_3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Please Type How Many Salads To Prepare:");
		Scanner scan = new Scanner(System.in);
		final int numOfSaladsToPrepare = scan.nextInt();
		System.out.println("Preparing " + numOfSaladsToPrepare + " Salads...");

		SlicerMachine machine = new SlicerMachine(numOfSaladsToPrepare);

		Thread cucumbersThread = new CucumbersThread(machine);
		Thread tomatoesThread = new TomatoesThread(machine);
		Thread onionThread = new OnionThread(machine);
		Thread slicerThread = new SlicerThread(machine);

		cucumbersThread.start();
		tomatoesThread.start();
		onionThread.start();
		slicerThread.start();

		try {
			// Wait for the slicer to reach the target count (it sets machine to done)
			slicerThread.join();
			cucumbersThread.join();
			tomatoesThread.join();
			onionThread.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		System.out.println("Done");
		scan.close();
	}

}
