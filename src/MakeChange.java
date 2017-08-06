import java.util.Scanner;

public class MakeChange {
	static int password = 12345, total = 0;
	static int[][] bills = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 10_000, 10_000, 10_000, 10_000, 10_000, 10_000, 10_000, 10_000, 10_000 },
			{ 0, 10_000, 2_000, 1_000, 500, 100, 25, 10, 5, 1 } };// exact bill tallies, bills[0] will be used for
																	// making change, bills[1] will be used for setting
																	// maximums using the lowTray method [2] is used for
																	// numeric values of currency times 100 because of
																	// integer casting
	static String[][] currency = {
			{ "remainder", "hundreds", "twenties", "tens", "fives", "ones", "quarters", "dimes", "nickels", "pennies" },
			{ "remainder", "hundred", "twenty", "ten", "five", "one", "quarter", "dime", "nickel", "penny" } };
	// sets up an array to  spit out bill labels later, [0] is plurals [1] is singular.

	public static void main(String[] args) {

		Scanner keyb = new Scanner(System.in); // bringing in the scanner keyboard (keyb) for use. will be passed along
												// a lot
		menu1(keyb); // runs the main menu screen
		keyb.close(); // closed the scanner
	}

	public static void menu1(Scanner keyb) { // the main menue method
		int opt = 0; // setting up the opt variable which will be used in a switch below to determine
						// the next course of action
		while (opt == 0) { // keeps the loop running as long as opt is 0
			System.out.print("Enter [0] To checkout customer or [1] Exit: "); // displays the main two options to the
																				// cashier
			opt = getInput(keyb); // changes int opt to whatever the keyboard entry is
			switch (opt) { // begins a switch measuring opt

			case 0: // the first case is initiate with a 0 this will run the method that calculates
					// change and bills due back
				bills[0][0] = checkout(keyb);// runs the checkout method to determine the change(as a total is returned)
				billCalc(keyb); // calculates the type of bills to be returned to the customer places them in
							// the array bills
				display(); // prints out the total amount of change due, as well as the bills to create it
				billsReset(); // resets the bills to 100 each, this is in case lowTray has been used on the
								// previous billCalc method
				break;
			case 100: // second case is initiated with a 1, using 100 here because of the conversion
						// in getInput, exits the menu
				managePass(keyb, 0);
				break;
			case 10100: // hidden case enacted by typing in 101
				managePass(keyb, 1); // asks for the managers password, then will give tray info and options with the
										// tray and password
				menu1(keyb); // sens us back into this menu once we are done using managePass()
				break;
			case 50500: // hidden case enacted by typing in 505
				lowTray(keyb); // sets maximums on bill types
				menu1(keyb); // brings us back to the top of this menu
				break;
			default:
				System.out.println("Invalid entry."); // communicates that one of the options was not selected
				menu1(keyb); // gives an option again
				break;
			}
		}

		return;
	}

	public static void billsReset() {
		/*
		 * for (int i = 1; i < bills.length; i++) { bills[1][i]=10_000; }
		 */
		bills[1][1] = 10_000;
		bills[1][2] = 10_000;
		bills[1][3] = 10_000;
		bills[1][4] = 10_000;
		bills[1][5] = 10_000;
		bills[1][6] = 10_000;
		bills[1][7] = 10_000;
		bills[1][8] = 10_000;
		bills[1][9] = 10_000;
		return;
		// this method resets all bill maximums to 100 for some reason the for loop
		// above would not work.
	}

	static int checkout(Scanner keyb) { // runs the checkout interface where you can sum up costs and enter what the
										// person pays
		System.out.print("What is the price of the item: $");// prompting for the price of the item
		int price = getInput(keyb); // gets an entry for price
		int pTotal = price; // sets the total price = price for the first entry
		totalling: while (price != 0) { // loop that allows us to enter more and more entries
			System.out.println("Subtotal: $" + (double) pTotal / 100.00); // displays the current sub-total after each
																			// entry beginning with the second
			System.out.print("Enter the price of the next item or [0] For end total: $"); // offers a way out of adding
																							// up costs
			price = getInput(keyb); // gathers the price of the current object for entry 2 and on
			pTotal = pTotal + price; // sums up the total price so far
		}
		System.out.println("Total: $" + (double) pTotal / 100.00); // displays the total after all entries are made,
																	// converts ptotal back to a double so it can match
																	// pricing format
		int paid = 0;
		do {System.out.print("What did they pay or [0] to remove item price: $");// prompt for amount of money the custome paid
		paid = getInput(keyb);// method receiving the amount paid for the tab
			if (paid==0) {
				do {
				System.out.print("How much do you want to remove or [0] For end total: $");
				price = getInput(keyb); // gathers the price of the item to be removed
				pTotal = pTotal - price; // sums up the total price minus the amount removed
				String r = (price !=0?"Subtotal":"Total");
				System.out.println(r + " $" + (double)pTotal / 100.00);
				} while(price!=0);
			}} while (paid==0);
		// converts ptotal back to a double so it can match
		// pricing format
		int diff = paid - pTotal; // calculates the the amount of change due
		if (diff == 0) { // verifies whether or not the exact amount was paid
			System.out.println("Paid in full, no change due.");
		} else if (diff < 0) {// the difference should never be negative
			System.out.print("$" + Math.abs((double) (diff) / 100.00)
					+ " still due. Enter [0] To restart or [1] To add more customer payment: "); // essentially an error
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
					while (diff < 0) { // sets up a while loop for as long as they owe money
						System.out.print("$" + Math.abs((double) (diff) / 100.00)
								+ " still due. How much more did they pay [0] To exit: $"); // prompts for more money
						int more = getInput(keyb); // receiving how much more money the customer paid
						if (more == 0) {
							checkout(keyb);
						}
						diff = more + diff;
					} // the new diff is calculated by adding how much more they paid to what the diff
					String exact = (diff != 0 ? "" : "Paid in full, no change due.\n");// determines if exact should show
																						// up
					System.out.print(exact); // prints exact messag if it exists
					break; // was (it is negative so you add to get the new total)
				} else {
					System.out.print("Invalid entry. Enter [0] To restart or [1] To add more customer payment: ");// prompts
																													// for
																													// a
																													// valid
																													// entry
				}
			}
		}
		bills[2][0] = pTotal; // this allows the transfer of the price total in case exact change isn't possible and this price needs to be removed from the tray total
		total = total + pTotal; // adds the transaction total to the tray total
		return diff;
	}

	static int getInput(Scanner keyb) { // this method is used to gather all input throughout this program, assumed to
		// be a cash register so doubles are the only accepted inputs
		int temp = 0; // defines temp
		while (!keyb.hasNextDouble()) { // checks to make sure entry is a double

			System.out.print("Invalid Entry. Use numbers only: "); // notifies user of invalid entry
			keyb.next(); // requests another entry
		}

		temp = (int) ((Math.round(keyb.nextDouble() * 100))); // converts entry to an integer to make everything pennies
		if (temp < 0) { // verifies that enrtry is positive
			System.out.print("Invalid Entry. Please make a valid entry:"); // notifies user of invalid entry
			temp = getInput(keyb); // gets a new entry hopefully valid this time
		}
		return temp; // returns newly converted integer
	}

	static int[][] billCalc(Scanner keyb) {// runs the math on the remainder to determine which
		bills[1][0] = bills[0][0]; // saves the remainder to use for change total later
		for (int i = 1; i < bills[0].length; i++) { // this will calculate the amounts of all the bill types start at 1
													// since the bills[x][0] are place holders for remainder
			int ph = bills[0][0]; // saves remainder for calculation purposes below, in a current loop remainder

			bills[0][0] = bills[0][0] % bills[2][i]; // this calculates and assigns the new remainder
			bills[0][i] = (ph - bills[0][0]) / bills[2][i]; // initial remainder - current remainder divided by the bill
															// value to determine number of bills
			bills[0][i] = (bills[0][i] <= bills[1][i] ? bills[0][i] : bills[1][i]); // verifies the number of bills is
																					// below any max set
			bills[0][0] = (ph - bills[0][i] * bills[2][i]); // current loop remainder minus number of bills and bill
															// value to determine the remainder for the next time
															// through the loop

		}
		if (bills[0][0]>0) {
			System.out.println("Exact change impossible. Transaction aborted.");
			total = total - bills[2][0];
			bills[1][0] = 0;
			
			for (int i = 0; i < bills.length; i++) {
				bills[1][i] = 0;
			}
			
		}
		return bills;
	}

	static void display() { // method to display all change information
		if (bills[1][0]!=0) {
		System.out.println("Change due: $" + (double) bills[1][0] / 100.0); // total change amount due
		int y = 0; // establishes an int y ouside the loop so it is not local to the loop
		String show = "", comma = ""; // establishes two strings outside the loop so it is not loop local
		for (int j = 0; j < currency[0].length; j++) { // starts a for loop for making display choices
			int x = 0, z = 0; // sets up x and z outside the inner loop
			for (int i = (currency[0].length - 1); i > j; i--) { // for loop to calculate if a bill is the last that
																	// will be used for making the change starts at the
																	// end and works back to the current bill type
				x = bills[0][i];
				z = x + z;
			}
			y = bills[0][j]; // used to determine if the bill is singular or plural
			comma = ((z != 0 && bills[0][j] != 0) ? ", " : ""); // prints a comma if there is a bill used and it is not
																// the last bill used
			show = (bills[0][j] != 0 ? (bills[0][j] + " " + currency[(y == 1 ? 1 : 0)][j]) : ""); // prints the bill
																									// number and type
																									// (ternary used to
																									// determine if
																									// singular bill or
																									// not)
			System.out.print(show + comma); // prints each bill amount, type and a comma if necessary
		}
		System.out.print(".\n");}// sends everything to the next line
	}

	static void manage(Scanner keyb) { // method for the managers menu
		System.out.println("Tray total should match: $" + (double) (total) / 100); // tells manager how much money
																					// should be in the tray converts
																					// the total to be displayable as
																					// currency
		manage: while (true) { // sets up a infinite loop that is only exited by entering a 2
			System.out.print("[0]Reset tray or [1] Change password or [2] Exit manager mode: "); // prompts the three
																									// acceptable
																									// commands for the
																									// managers menu
			int opt = getInput(keyb); // gets input for the switch option below
			switch (opt) { // switch for the managers menu
			case 0: // if 0 is used the manager is sent to the trayReset method where they can
					// override the amount that should be me in the tray
				trayReset(keyb); // method to run tray management
				continue manage; // sends you back into the manage menu
			case 100: // if 1 is entered and converted to 100 this case will start
				passwordChange(keyb); // manager can change the machine password through this method
				continue manage; // continues method manage

			case 200: // exits method
				break;
			default:
				System.out.println("Invalid Entry."); // notifies user of using an invalid command
				continue manage;
			}
			break;
		}

	}

	static int trayReset(Scanner keyb) { // method allowing manager to override amount of money in the tray
		trayReset: while (true) {
			System.out.print("Tray Reset: [0] Zero tray [1] Enter tray amount [2] Exit tray reset: "); // prompts a
																										// command in
																										// the trayReset
																										// menu
			int opt = getInput(keyb); // takes in the option from the user
			switch (opt) {
			case 0: // option zeros out the tray
				total = 0;
				break;
			case 100: // option allows manager to override tray amount
				System.out.print("Enter tray amount: $"); // prompts new tray amount
				total = getInput(keyb); // creates the new total from user input
				break;
			case 200: // exits menu
				break;
			default:
				System.out.println("Invalid Entry."); // notifies user of the invalid entry
				continue trayReset; // allows another input into the trayReset method
			}
			break;
		}
		return total;
	}

	static int passwordChange(Scanner keyb) { // method that allows the manager to change the password
		System.out.println("Old Password: "); // prompts for the old password
		int pCheck = getInput(keyb) / 100; // takes in the user entry

		if (pCheck == password) { // if statement checking that the password was correct
			System.out.println("New Password: "); // prompts for the new password
			int password1 = getInput(keyb) / 100; // gets the user input
			System.out.println("Verify Password: "); // prompts for a password verification
			int pCheck2 = getInput(keyb) / 100; // takes in verification
			if (pCheck2 == password1) { // verifies that both passwords entered match
				password = password1; // changes password to new one if accurate
			} else {
				System.out.println("Passwords did not match. Password not changed."); // notifies user that passwords
																						// did not get changed

			}
		} else {
			System.out.println("Incorrect password."); // tells user that the password entered was incorrect

		}
		return password;

	}

	static void managePass(Scanner keyb, int p) { // method that checks password entry
		System.out.print("Password: "); // prompts password entry
		if (getInput(keyb) / 100 == password) { // checks that passwords match
			if (p == 1) {
				manage(keyb);
			} else {
				exitMessage(); // sends the user to the managers menu
			}
		} else {
			System.out.println("Password incorrect."); // notifies user that password did not match
		}
		return;
	}

	static void exitMessage() {
		System.out.println("Tray Total: $" + (double) total / 100.0); // as the cashier exits they will get the total
																		// that should match the money in the tray
	}

	static int[][] lowTray(Scanner keyb) { // runs a method to put a maximum on bill types
		System.out.print("What bill denomination is running low: $"); // promps user for bill type to set a max on
		int denom = getInput(keyb), h = 0; // gets user choice
		switch (denom) { // switch that sets a value for h
		case 1:
			h=9;
			break;
		case 5:
			h = 8;
			break;
		case 10:
			h = 7;
			break;
		case 25:
			h = 6;
			break;
		case 100:
			h = 5;
			break;
		case 500:
			h = 4;
			break;
		case 1_000:
			h = 3;
			break;
		case 2_000:
			h = 2;
			break;
		case 10_000:
			h = 1;
			break;
		default:
			h = 0;
			System.out.println("Invalid entry."); // notifies user that their value was not valid
			break;
		}
		if (h > 0) { // if any non exit entry is used then the user will be able to change the number
						// of bills
			System.out.print("What is the maximun number of " + currency[0][h] + ": "); // prompts the user for the
																						// change
			bills[1][h] = getInput(keyb) / 100; // changes the bill number in the bills array
		}
		System.out.print("[0] Change another denomination [1] Exit: ");
		option2: while (true) { // created an infinite loop that will keep running until a valid entry is used
			int opt = getInput(keyb); // receiving input for the option, once again used numbers as options assuming
										// its a cash register
			if (opt == 0) { // if they choose 0 the whole checkout method will run again
				lowTray(keyb); // reruns checkout and returns the diff this is necessary because after it
				break; // breaks out of the infinite loop because a valid choice is entered
			} else if (opt == 100) { // if they choose 1 (which is converted to 100 by the input method they will
				break; // was (it is negative so you add to get the new total)
			} else {
				System.out.print("Invalid entry. Enter [0] To change another denomination [1] Exit: "); // notifies the
																										// user of an
																										// invalid entry
				continue option2;
			}

		}
		return bills;
	}

}
