
import java.util.Scanner;

public class Atm{
	
	public static void main(String args[]) {
		
	

	Scanner s=new Scanner(System.in);
			
	//init Bank
	
	Bank theBank=new Bank("Bank of dayananda sagar");
	
	//add a user to the bank and create a savings account

	
	User aUser=theBank.addUser("Shyam", " Roy", "1234");
	
	//add a checking account for our user
	
	Account newAccount =new Account("Checking",aUser,theBank);
	aUser.addAccount(newAccount);
	theBank.addAccount(newAccount);
	
	 User cuUser;
		while(true) {
			//stay in the login prompt until successful login
          cuUser=Atm.mainMenuPrompt(theBank, s);
			
			//stay in main menu until user quits
			Atm.printUserMenu(aUser ,s);
		}
	}
	/**
	 * print the Atm 's login menu
	 * @param theBank  the bank object whose account to use
	 * @param s         the scanner object to read the inputs
	 * @return          authUser
	 */
	public static User mainMenuPrompt(Bank theBank ,Scanner s) {
		//inits
		String userID;
		String pin;
		User authUser;
		//prompt the user id for ID/pin combo until a correct one is reached
		do {
			System.out.printf("\n\nWelcome to %s  \n\n",theBank.getName());
			System.out.print("Enter the user ID : ");
			userID =s.nextLine();
			System.out.printf("Enter pin: ");
			pin=s.nextLine();
			
			// try to get user object corresponding to the ID and pin combo 
			authUser =theBank.userLogin(userID, pin);
			
			  if(authUser==null){
		            System.out.println("ok ,lets start.");
		    }

	} while(authUser!=null);// Continuing looping until successful login
		        return authUser;

}	
	public static void printUserMenu(User aUser,Scanner s) {
		
		//print the summary of the account
		aUser.printAccountsSummary();
		
		int choice;
		
		//user menu
		do {
			System.out.printf("Welcome %s,what would you like to do?;",aUser.getfirstName());
			System.out.println(" \n1) Show account transaction history");
			System.out.println(" 2) Withdraw ");
			System.out.println(" 3) Deposit");
			System.out.println(" 4) Transfer");
			System.out.println(" 5) Quit");
			System.out.println();
			System.out.println("Enter choice : ");
			choice=s.nextInt();
			
			if(choice<1||choice >5) {
				System.out.println("Invalid choice ,Please Enter the choose between 1-5");
				
			}
			
		}while(choice<1||choice >5);
		
		//process the choice
		switch(choice) {
		
		
		case 1:
			Atm.showTransHistory(aUser ,s);
			break;
			
		case 2:
			Atm.withdrawlHistory(aUser,s);
			break;
		case 3:
			Atm.depositFunds(aUser,s);
			break;
		case 4:
			Atm.transferFunds(aUser,s);
			break;
		}
		// redisplay the user menu until the user wants to quit
		if ( choice != 5) {
			Atm.printUserMenu(aUser, s);
			
		}
	}
	
	/**
	 * show the transaction history for an account 
	 * @param theUser the logged-in user object
	 * @param s the Scanner object used for user input
	 */
	
	public static void showTransHistory(User aUser,Scanner s) {
		
		int theAcct;
		
		do {
			
			//get account whose transaction history to look at
			System.out.printf("Enter the number (1-%d) of the account "+ 
			"whose transactions you want to see : ",aUser.numAccounts());
			
			theAcct =s.nextInt()-1;
			if(theAcct <0|| theAcct >=aUser.numAccounts()) {
				System.out.println("Invalid account .please try again.");
			}
		}while(theAcct <0|| theAcct >=aUser.numAccounts());
		// print the transaction history
		
		aUser.printAcctTransHistory(theAcct);
	}
	/**
	 * transferring funds from one account to another
	 * @param       theUser the logged-in user object 
	 * @param s     the Scanner object used for user input  
	 */

public static void transferFunds(User aUser ,Scanner s) {
		
		//inits
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		//get the account to transfer from
		
		do {
			System.out.printf("Enter the number (1-%d) of the account \n"+"to transfer from :",aUser.numAccounts());
			fromAcct=s.nextInt()-1;
			
			if(fromAcct<0||fromAcct>=aUser.numAccounts()) {
				System.out.println("Invalid account ,please try again.");
			}
		}while(fromAcct <0|| fromAcct >=aUser.numAccounts());
		acctBal=aUser.getAcctBalance(fromAcct);
		
		//get the account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) of the account \n"+"to transfer to :",aUser.numAccounts());
			toAcct=s.nextInt()-1;
			
			if(toAcct<0||toAcct>=aUser.numAccounts()) {
				System.out.println("Invalid account ,please try again.");
			}
		}while(toAcct <0|| toAcct >=aUser.numAccounts());
		
		//get the amount to transfer
		
		do {
			System.out.printf("Enter the amount to transfer (max Rs%.02f): Rs",acctBal);
			
			amount =s.nextDouble();
			if(amount<0) {
				
				System.out.println("Amount must be greater than ZERO.");
			}
			else if(amount>acctBal) {
				
				System.out.printf("Amount must not be greater than \n"+"balance of Rs.02f.\n",acctBal);
				
			}
		}while(amount <0||amount >acctBal);
		
		//finally do the transfer
		
aUser .addAcctTransaction(fromAcct ,-1*amount ,String.format("Transfer to account %s",aUser.getAcctUUID(toAcct)));
aUser .addAcctTransaction(toAcct ,amount ,String.format("Transfer to account %s",aUser.getAcctUUID(fromAcct)));	
	}
	
	/**
	 * process the fund withdraw from an account 
	 * @param   theUser  the logged-in User object
	 * @param s the scanner object user for user input 
    */
public static  void withdrawlHistory(User aUser ,Scanner s) {
	
	//inits
	
	int fromAcct;
	double amount;
	double acctBal;
	String memo;
	
	//get the account to transfer from
	
	do {
		System.out.printf("Enter the number (1-%d) of the account to withdraw  from : ",aUser.numAccounts());
		fromAcct=s.nextInt()-1;
		
		if(fromAcct<0||fromAcct>=aUser.numAccounts()) {
			System.out.println("Invalid account ,please try again.");
		}
	}while(fromAcct <0|| fromAcct >=aUser.numAccounts());
	acctBal=aUser.getAcctBalance(fromAcct);
	
	//get the amount to transfer
	
	do {
		System.out.printf("Enter the amount to withdraw(max Rs%.02f): Rs",acctBal);
		
		amount =s.nextDouble();
		if(amount<0) {
			
			System.out.println("Amount must be greater than ZERO.");
		}
		else if(amount>acctBal) {
			
			System.out.printf("Amount must not be greater than \n"+"balance of Rs%.02f.\n",acctBal);
			
		}
	}while(amount <0||amount >acctBal);
	
	
	//gobble up rest of previous input
	 s.nextLine();
	 
	 //get a memo
	 
	 System.out.println("Enter a memo : ");
	 memo=s.nextLine();
	 
	 //do the withdrawal
	 aUser.addAcctTransaction(fromAcct, -1*amount, memo);
}
/**
 * process a fund deposit to an account 
 * @param theUser  the logged-in User object 
 * @param s        the Scanner-object used for user input
 */

public static void depositFunds(User aUser ,Scanner s) {
	
	//inits
	
			int toAcct;
			double amount;
			double acctBal;
			String memo;
			
			//get the account to transfer from
			
			do {
				System.out.printf("Enter the number (1-%d) of the account\n"+"to deposit in:",aUser.numAccounts());
				toAcct=s.nextInt()-1;
				
				if(toAcct<0||toAcct>=aUser.numAccounts()) {
					System.out.println("Invalid account ,please try again.");
				}
			}while(toAcct <0|| toAcct >=aUser.numAccounts());
			acctBal=aUser.getAcctBalance(toAcct);
			
			//get the amount to transfer
			
			do {
				System.out.println("Amount must be greater than ZERO.");
				System.out.printf("Enter the amount to deposit (max Rs%.02f): Rs",acctBal);
				
				amount =s.nextDouble();
				if(amount<0) {
					
					System.out.println("Amount must be greater than ZERO.");
				}
				//else if(amount>acctBal) {
					
					//System.out.printf("Amount must  be greater than \n"+"balance of Rs%.02f.\n",acctBal);
					
				//}
			}while(amount <0);
			
			 s.nextLine();
			 
			 //get a memo
			 
			 System.out.println("Enter a memo : ");
			 memo=s.nextLine();
			 
			 //do the withdrawal
			 aUser.addAcctTransaction(toAcct, amount, memo);
	
}
}