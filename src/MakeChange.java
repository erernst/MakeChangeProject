import java.util.Scanner;

public class MakeChange {

	public static void main(String[] args) {
		int[] bills = {0,0,0,0,0,0,0,0,0};
		String[] currency = {"remainder", "hundreds", "twenties", "tens", "fives", "ones", "quarters", "dimes", "nickels", "pennies"};
	}

	static int checkout() {
		System.out.print("What is the price of the iten: ");
		int price = getInput();
		if (price == -505) {
			lowTray();
		}
		else if (price == -101) {
			manage();
		}
		System.out.print("What did they pay: ");
		int paid = getInput();
		int diff = price - paid
		if (diff > 0) {
			return diff;
		} 
		else if (diff == 0) {
			System.out.println("They paid correctly.");
		} 

	}

	static int getInput() {
		Scanner keyb = new Scanner(System.in);
		double input = keyb.nextDouble();
		int intput = (int) (input) * 100;
		keyb.close();
		return intput;

	}

	static int calc

	change(int difference) {
		for 
	}

}
