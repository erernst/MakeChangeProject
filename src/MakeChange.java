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
	}int bills[0][0]=

	checkout()

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
		} else if (diff < 0) {// the difference should never be negative
			System.out.println("$" + (double) (diff / 100)
					+ " still due. Enter [0] to restart or [1] to add more customer payment."); //essentially an error message saying more money is required.
			option: while (true) { //created an infinite loop that will keep running until a valid entry is used
				int opt = getInput(); // receiving input for the option, once again used numbers as options assuming its a cash register
				if (opt == 0) { // if they choose 0 the whole checkout method will run again
					diff = checkout(); //reruns checkout and returns the diff this is necessary because after it breaks out of this loop diff will still be returned by the initial checkout method
					break; // breaks out of the infinite loop because a valid choice is entered
				} else if (opt == 100) { // if they choose 1 (which is converted to 100 by the input method they will enter how much more money the person paid
					int more = getInput(); // receiving how much more money the customer paid
					diff = more + diff; // the new diff is calculated by adding how much more they paid to what the diff was (it is negative so you add to get the new total)
					break; 
				} else {
					System.out.println("Invalid entry. Enter [0] to restart or [1] to add more custome payment.");
				}
				return diff;
			}
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

	static int calc(int difference, int[][] bills, String[] currency);// runs the math on the remainder to determine which
																	// bills to give back

	for(

	int i = 0;i<array.length;i++)
	{
			(bills[0][1] % )
			
		}
}

}
