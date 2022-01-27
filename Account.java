

import java.util.ArrayList;
public class Account {
	
	 private String name;
	 
		/**
		 * first name of the user.
		 */
	 
		 
	 private String uuid;
	 
	 /**
	 * id number of the user.
	 */
	 
	 private User holder;
	
	 /**
	 * The User object that owns this account.
	 */
	
	 private ArrayList<Transaction> transactions;
	
	 /**
	 * list of transaction for this account.
	 */
	 /**
	  * create a new account 
	  * @param name      name of the account
	  * @param holder    the User object that owns this account 
	  * @param theBank   the bank that issues the account
	  */
	 public Account(String name,User holder,Bank theBank) {
		 //set the account name and holder
		 this.name=name;
		 this.holder=holder;
		 
		 //get new account UUID
		 this.uuid=theBank.getNewAccountUUID();
		 
		 // init transactions
		 
		 this.transactions=new ArrayList<Transaction>();
		 
		 //add to holder and bank lists
		 
		
		//holder.accounts.add(this);
	 }
		 /**
		  * Get the account ID
		  * @return the uuid
		  */
		 public String getUUID() {
			 return this.uuid;
		 }
		 /**
		  * get summary line for the account 
		  * @return the string summary
		  */
		 public String getSummaryLine() {
			 
			 //get the accounts balance
			 double balance =this.getBalance();
			 
			 //format the summary line depending upon whether balance is negative
			 
			 if(balance>=0) {
				 return String.format("%s : Rs%.02f : %s", this.uuid ,balance,this.name);
				 
			 }
			 else {
				 return String.format("%s : Rs(%.02f) : %s", this.uuid ,balance,this.name); 
			 }
		 }
		 /**
		  * Get the balance of this account by adding the amounts of the transactions
		  * @return the balance value
		  */
		 public double getBalance() {
			 double balance=0;
			 
			 for(Transaction t:this.transactions) {
				 balance +=t.getAmount();
			 }
			 return balance;
		 }
		 /**
		  * Print the transaction history for the account
		  */
		 public void printTransHistory() {
				System.out.printf("\n Transaction history for account %s\n",this.uuid);
				for(int t=this.transactions.size()-1;t>=0;t--) {
					System.out.printf(this.transactions.get(t).getSummaryLine());
					
				}
				System.out.println();
				
			}
		 public void addTransaction(double amount ,String memo) {
				
				//create new transaction object and add it to our list
				
				Transaction newTrans=new Transaction(amount, memo,  this);
				this.transactions.add(newTrans);
			}
}