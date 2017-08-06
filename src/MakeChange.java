import java.util.Scanner;

public class MakeChange {
	static int password = 12345, total = 0;

	public static void main(String[] args) {
		int[][] bills = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 500, 500, 500, 500, 500, 500, 500, 500, 500 },
				{ 0, 10_000, 2_000, 1_000, 500, 100, 25, 10, 5, 1 } };// exact bill tallies, bills[0]
		// will be used for making
		// change, bills[1] will be
		// used for setting maximums
		// using either the lowTray
		// method or the managers
		// menu bills[2] is used for numeric values of currency times 100 because of
		// integer casting
		String[] currency = { "remainder", "hundreds", "twenties", "tens", "fives", "ones", "quarters", "dimes",
				"nickels", "pennies" };// sets up an array to spit out bill labels later, remainder will never be used
										// but is a place holder.

		Scanner keyb = new Scanner(System.in); // since this is the only place we use a scanner (see comment above) we
		// will open and close the scanner in this method
		int opt = 0;
		while (opt == 0) {
			System.out.print("Enter [0] to checkout customer [1] to quit: ");
			opt = getInput(keyb);
			switch (opt) {

			case 0:
				int diff = checkout(keyb);
				bills[0][0] = diff;
				bills = billCalc(bills);
				display(bills, currency);
				System.out.println();
				break;
			case 1:
				break;
			}
		}
		System.out.println("Tray Total: $" + (double) total / 100.0);
		keyb.close();
	}

	static int checkout(Scanner keyb) {

		System.out.print("What is the price of the  item: $");// prompting for the price of the item
		int price = getPrice(keyb);

		int pTotal = price;
		totalling: while (price != 0) {
			System.out.println("Subtotal: $" + (double) pTotal / 100.00);
			System.out.print("Enter the price of the next item or [0] for end total: $");
			price = getInput(keyb);
			pTotal = pTotal + price;
		}
		// if (price == -505) { // -505 is not a valid entry so using it to access a
		// separate menu should cause
		// // no problems
		// lowTray();// this will allow a cashier to put maximums on bill types when the
		// tray is low
		// } else if (price == -101) { // -101 is not a valid entry so using it to
		// access a separate menu should cause
		// // no problems
		// manage();// this will access a managers tally so they know how much should be
		// in the
		// // drawer at any given time
		// }
		System.out.println("Total: $" + (double) pTotal / 100.00);
		System.out.print("What did they pay: $");// prompting
		int paid = getInput(keyb);// method receiving the amount paid for the tab
		int diff = paid - pTotal; // calculates the the amount of change due
		if (diff == 0) { // verifies whether or not the exact amount was paid
			System.out.println("Paid in full, no change due.");
		} else if (diff < 0) {// the difference should never be negative
			System.out.print("$" + Math.abs((double) (diff / 100))
					+ " still due. Enter [0] to restart or [1] to add more customer payment:"); // essentially an error
																								// message saying more
																								// money is required.
			option: while (true) { // created an infinite loop that will keep running until a valid entry is used
				int opt = getInput(keyb); // receiving input for the option, once again used numbers as options assuming
											// its a cash register
				if (opt == 0) { // if they choose 0 the whole checkout method will run again
					diff = checkout(keyb); // reruns checkout and returns the diff this is necessary because after it
											// breaks out of this loop diff will still be returned by the initial
											// checkout
											// method
					break; // breaks out of the infinite loop because a valid choice is entered
				} else if (opt == 100) { // if they choose 1 (which is converted to 100 by the input method they will
											// enter how much more money the person paid
					System.out.print("How much more did they pay: $");
					int more = getInput(keyb); // receiving how much more money the customer paid
					diff = more + diff; // the new diff is calculated by adding how much more they paid to what the diff
					String exact = (diff != 0 ? "" : "Paid in full, no change due.");
					System.out.print(exact);
					break; // was (it is negative so you add to get the new total)
				} else {
					System.out.println("Invalid entry. Enter [0] to restart or [1] to add more customer payment.");
				}
			}
		}
		total = total + pTotal;
		return diff;
	}

	static int getPrice(Scanner keyb) {
		int price = 0;
		initPrice: while (true) {
			price = getInput(keyb);// method receiving the price of the item
			if (price < 0) {
				System.out.println("Invalid entry.");
				continue initPrice;
			} else {
				break;
			}
		}
		return price;
	}

	static int getInput(Scanner keyb) { // this method is used to gather all input throughout this program, assumed to
		// be a cash register so doubles are the only accepted inputs
		int temp = 0;
		while (true) {
			while (!keyb.hasNextDouble()) {
				System.out.print("Invalid Entry. Please enter a valid price: ");
				keyb.nextDouble();
			}
			temp = (int) (keyb.nextDouble() * 100);// converts the double which has decimals to int to simplify the rest
													// of the
			// program, essentially everything is measured in pennies
			if ((temp >= 0) || (temp == -10100) || (temp == -50500)) {
				break;
			} else {
				System.out.print("Invalid Entry. Please enter a valid price:");
				keyb.nextDouble();
			}
			if (temp == -10100) {
				manage(keyb);
			}
		}
		return temp; // returns integers back to the program

	}

	static int[][] billCalc(int[][] bills) {// runs the math on the remainder to determine which
		for (int i = 1; i < bills[0].length; i++) { // start at 1 since the bills[x][0] are place holders for remainder
			int ph = bills[0][0];

			bills[0][0] = bills[0][0] % bills[2][i]; // this calculates and assigns the new remainder
			bills[0][i] = (ph - bills[0][0]) / bills[2][i];
			bills[0][i] = (bills[0][i] <= bills[1][i] ? bills[0][i] : bills[1][i]);
			bills[0][0] = (ph - bills[0][i] * bills[2][i]);
		}
		return bills;
	}

	static void display(int[][] bills, String[] currency) {
		int y = 0, z = 0;
		String show = "", comma = "";
		for (int j = 0; j < currency.length; j++) {
			for (int i = (currency.length - 1); i > j; i--) {
				int x = bills[0][i];
				z = x + y;
				y = x;
				comma = (z != 0 && bills[0][j] != 0 ? ", " : "");
			}
			show = (bills[0][j] != 0 ? (bills[0][j] + " " + currency[j] + (bills[0][j] == 1 ? "\b" : "")) : "");
			System.out.print(show + comma);
		}

	}

	static void manage(Scanner keyb) {
		System.out.println("Tray total should match: $" + (double) (total) / 100);
		manage: while (true) {
			System.out.println("[0]Reset tray or [1] change password or [2] exit manager mode");
			int opt = getInput(keyb);
			switch (opt) {
			case 0:
				trayReset(keyb);
				break;
			case 1:
				passwordChange(keyb);
				break;
			case 2:
				break;
			default:
				System.out.println("Invalid entry.");
				continue manage;
			}
			break;
		}

	}

	static int trayReset(Scanner keyb) {
		trayReset: while (true) {
			System.out.print("Tray Reset: [0] Zero tray [1] enter tray amount [2] exit tray reset: ");
			int opt = getInput(keyb);
			switch (opt) {
			case 0:
				total = 0;
				break;
			case 1:
				System.out.print("Enter tray amount: $");
				total = getInput(keyb);
				break;
			case 2:
				break;
			default:
				System.out.println("Invalid Entry.");
				continue trayReset;
			}
			break;
		}
		return total;
	}

	static int passwordChange(Scanner keyb) {
		System.out.println("Old Password: ");
		int pCheck = getInput(keyb);
		pChange: while (true) {
			if (pCheck == password) {
				System.out.println("New Password: ");
				password = getInput(keyb);
				System.out.println("Verify Password: ");
				int pCheck2 = getInput(keyb);
				if (pCheck2 == password) {
					break;
				} else {
					System.out.println("Passwords did not match. Try Again");
					continue pChange;
				}
			}
		}
		return password;

	}

	static int[][] lowTray(Scanner keyb, int[][] bills) {
		System.out.println("What bill denomination is running low?");
		int denom = getInput(keyb);
		switch (denom) {
		case 1:
			System.out.print("What is the maximun number of " + bills[2][9] + ": ");
			bills[1][9] = getInput(keyb)/100;
			break;
		case 5:
			System.out.print("What is the maximun number of " + bills[2][9] + ": ");
			bills[1][8] = getInput(keyb)/100;
			break;
		case 10:
			System.out.print("What is the maximun number of " + bills[2][9] + ": ");
			bills[1][7] = getInput(keyb)/100;
			break;
		case 25:
			System.out.print("What is the maximun number of " + bills[2][9] + ": ");
			bills[1][6] = getInput(keyb)/100;
			break;
		case 100:
			System.out.print("What is the maximun number of " + bills[2][9] + ": ");
			bills[1][5] = getInput(keyb)/100;
			break;
		case 500:
			System.out.print("What is the maximun number of " + bills[2][9] + ": ");
			bills[1][4] = getInput(keyb)/100;
			break;
		case 1_000:
			System.out.print("What is the maximun number of " + bills[2][9] + ": ");
			bills[1][3] = getInput(keyb)/100;
			break;
		case 2_000:
			System.out.print("What is the maximun number of " + bills[2][9] + ": ");
			bills[1][2] = getInput(keyb)/100;
			break;
		case 10_000:
			System.out.print("What is the maximun number of " + bills[2][9] + ": ");
			bills[1][1] = getInput(keyb)/100;
			break;
		default:
			System.out.println("Invalid entry.");
			break;
		}
		System.out.println("[0] Change another denomination [1] Exit");
		option2: while (true) { // created an infinite loop that will keep running until a valid entry is used
			int opt = getInput(keyb); // receiving input for the option, once again used numbers as options assuming
										// its a cash register
			if (opt == 0) { // if they choose 0 the whole checkout method will run again
				lowTray(keyb, bills); // reruns checkout and returns the diff this is necessary because after it
				break; // breaks out of the infinite loop because a valid choice is entered
			} else if (opt == 100) { // if they choose 1 (which is converted to 100 by the input method they will
				break; // was (it is negative so you add to get the new total)
			} else {
				System.out.println("Invalid entry. Enter [0] to change another denomination [1] to Exit.");
				continue option2;
			}

		}
		return bills;
	}

}
