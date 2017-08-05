import java.util.Scanner;

public class MakeChange {

	public static void main(String[] args) {
		int[][] bills = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };// exact bill tallies, bills[0]
																							// will be used for making
																							// change, bills[1] will be
																							// used for setting maximums
																							// using either the lowTray
																							// method or the managers
																							// menu
		String[] currency = { "remainder", "hundreds", "twenties", "tens", "fives", "ones", "quarters", "dimes",
				"nickels", "pennies" };// sets up an array to spit out bill labels later, remainder will never be used
										// but is a place holder.
	}

	static int checkout() {
		System.out.print("What is the price of the item: ");// prompting for the price of the item
		int price = getInput();// method receiving the price of the item
		if (price == -505) { // -505 is not a valid entry so using it to access a separate menu should cause
								// no problems
			lowTray();// this will allow a cashier to put maximums on bill types when the tray is low
		} else if (price == -101) { // -101 is not a valid entry so using it to access a separate menu should cause
									// no problems
			manage();// this will access a managers tally so they know how much should be in the
						// drawer at any given time
		}
		System.out.print("What did they pay: ");// prompting
		int paid = getInput();// method receiving the amount paid for the tab
		int diff = price - paid; // calculates the the amount of change due
		if (diff > 0) { // verifies that change is due
			return diff; // if change is due the amount is returned so that the types of money to be
							// returned can be calculated
		} else if (diff == 0) { // verifies whether or not the exact amount was paid
			System.out.println("Paid in full, no change due."); // notifies the cashier that no change is due
		}

	}

	static int getInput() { // this method is used to gather all input throughout this program, assumed to
							// be a cash register so doubles are the only accepted inputs
		Scanner keyb = new Scanner(System.in); // since this is the only place we use a scanner (see comment above) we
												// will open and close the scanner in this method
		double input = keyb.nextDouble();// receives the input
		int intput = (int) (input) * 100;// converts the double which has decimals to int to simplify the rest of the
											// program, essentially everything is measured in pennies
		keyb.close(); // close the scanner
		return intput; // returns integers back to the program

	}

	static int calc();// runs the math on the remainder to determine which bills to give back

	change(int difference) {
		for 
	}

}
