package assig3_3;

public class SlicerMachine {
	
	int numOfCucumbers = 0;
	int numOfTomatoes = 0;
	int numOfOnions = 0;
	int numOfPreparedSalads = 0;
	
	final int cucumbersNeededForOneSalad = 5;
	final int tomatoesNeededForOneSalad = 3;
	final int onionsNeededForOneSalad = 3;
	
	// add one cucumber into the slicer chamber
	void addOneCucumber() {
		if (numOfCucumbers < cucumbersNeededForOneSalad) {
			System.out.println("adding one cucumber to the machine");
			numOfCucumbers++;
		}
	}

	// add one onion into the slicer chamber
	void addOneOnion() {
		if (numOfOnions < onionsNeededForOneSalad) {
			System.out.println("adding one onion to the machine");
			numOfOnions++;
		}
	}

	// add one tomato into the slicer chamber
	void addOneTomato() {
		if (numOfTomatoes < tomatoesNeededForOneSalad) {
			System.out.println("adding one tomato to the machine");
			numOfTomatoes++;
		}
	}
	
	// if there are enough vegetables in the slicer
	// chamber, make another salad
	void sliceVegetables() {
		if ((numOfOnions >= onionsNeededForOneSalad) && (numOfCucumbers >= cucumbersNeededForOneSalad) && (numOfTomatoes >= tomatoesNeededForOneSalad)) {
			makeNewSalad();
		}
	}

	private void makeNewSalad() {
		System.out.println("== preparing one more salad ==");
		numOfPreparedSalads++; 
		// update stock
		numOfTomatoes = numOfTomatoes - tomatoesNeededForOneSalad;
		numOfCucumbers = numOfCucumbers - cucumbersNeededForOneSalad;
		numOfOnions = numOfOnions - onionsNeededForOneSalad;
	}	
	
	int getNumOfPreparedSalads() {
		return numOfPreparedSalads;
	}

}
