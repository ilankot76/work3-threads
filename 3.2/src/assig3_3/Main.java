package assig3_3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Please Type How Many Salads To Prepare:");
		Scanner scan = new Scanner(System.in);
		final int numOfSaladsToPrepare = scan.nextInt();
		System.out.println("Preparing " + numOfSaladsToPrepare + " Salads...");

		// YOUR CODE HERE: use threads to prepare N salads (as the user requested)
		
		System.out.println("Done");
		scan.close();
	}

}
