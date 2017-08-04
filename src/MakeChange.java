import java.util.Scanner;

public class MakeChange {

	public static void main(String[] args) {
		
	
	}
	
	static int checkout() {
			System.out.print("What is the price of the iten: ");
		int price = getInput();
		System.out.print("What did they pay: ");
		int paid = getInput();
		(price-paid) > 0
		return price - paid;
	}
	
	static int getInput() {
		Scanner keyb = new Scanner(System.in);
		double input = keyb.nextDouble();
		int intput =  (int)(input)*100;
		keyb.close();
		return intput;
		
	
	}
	static int calc change() {
		
	}
	

}
