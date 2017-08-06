#Make Change Project
## Week 1 Homework for Skill Distillery
When prompted for password the initial password is 12345.
In the initial screen 101, and 505 give access to hidden features.

### Background
Given this as an assignment on the fifth day of programming java. 

Tasked with creating a program that should calculate the amount of change due to someone based on what they owe and what they paid. Bills should go from pennies to twenties.
First : the user should be prompted for the asking price of the item for sale.
Second : the user should be prompted for how much was tendered by the customer.
Third : display a message to notify the user whether the customer provided too little, exact or too much money.
Fourth : if the amount tendered is more than the amount owed. Show how to give proper change.

After completing the basic requirements work was continued on the project. It was viewed as if it were a cash register and only number inputs could be used. Features added include a managers menu, and the ability to cap certain bill types.
### Basics
When you first use the program you are prompted to check out a customer or exit the program. 

Checkout:
	Once in the checkout method you are prompted for the price of the first item. After that is entered a SUBTOTAL appears and you are prompted for the price of the second item or [0] to get the final total. This is repeated until final total is chosen.	
		
 Exit:
 	Password: as the manager is the one who would likely sign out a cashier the manager password is required to exit. The total amount in the tray is then 	returned. If the manager had not manually entered an amount at the beginning of the day. The total money received throughout the day will be displayed. If the password is incorrect the user is returned to the checkout menu.
 	
Final Total: 
	If you select final total then you are prompted for what did they pay.

Correct amount: 
	""Paid in full no change due." is displayed
	
Too Little:
	"X still due. [0] to restart or [1] to add more customer payment: " is displayed. If the user had an error when entering the values this is where they would restart. Or the user can add enough money to get past this issue.
	
Too Much:
	Total change due and how to produce it is displaed.
	
This emcompasses all of the basic requirements

####Hidden Features
lowTray this option is available at the prompt to check out a customer or exit. Instead enter 505. you will be prompted to change a bill type's maximum, in case you are low on some bills. As we don't know what bills are coming in this will only work for the next calculation. The bill maximums will reset afterwards.

To access tray information, change the password or override tray amount enter 101 at this same menu. You will be required to enter the password to get into this.
