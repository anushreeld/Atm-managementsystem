import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class User {

	private String firstName;
	/**
	 * first name of the user.
	 */
	
	private String lastName;
	/**
	 * last name of the user.
	 */
	
	private String uuid;
	/**
	 * id number of the user.
	 */
	
	private byte pinHash[];
	/**
	 * pin number of the user.
	 */
	
	
	private ArrayList<Account> accounts;
	/**
	 * list of the accounts of the user.
	 */
	/**
	 * create a new user
	 * @param firstname of the user
	 * @param lastname of the user
	 * @param pin of the account
	 * @param theBank the bank object that the user is a customer of
	 */
	public User (String firstName,String lastName,String pin,Bank theBank) {
		//set users name
		this.firstName=firstName;//this keyword
		this.lastName=lastName;
		
		//store the pin MD5 hash ,rather than the original value,for security 
		
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			this.pinHash=md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error,cought NoSuchAlgorithmException");
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		//get a new ,universal ID for the user
		this.uuid=theBank.getNewUserUUID();
		
		//create empty list of accounts
		this.accounts=new ArrayList<Account>();
		
		//print log message
		 System.out.println("New user "+firstName+" "+lastName+" with user ID "+this.uuid);
	}
	/**
	 * Add an account for the user
	 * @param anAcct the account to add
	 */
	
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	/**
	 * returns the user ID
	 * @return the id
	 */
	public String getUUID() {
		
		return this.uuid;
	}
	
	/**
	 * check whether the pin matches the true user pin
	 * @param aPin the pin to check
	 * @return whether the pin is valid or not
	 */
	public boolean  validatePin(String aPin) {
		
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error,cought NoSuchAlgorithmException");
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
	public String getfirstName() {
		return this.firstName;
	}
	/**
	 * print the summary of the user account
	 */
	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's account summary\n\n ",this.firstName );
		for(int a=0;a<this.accounts.size();a++) {
			System.out.printf("  %d) %s\n",a+1, this.accounts.get(a).getSummaryLine());
			
		}
			System.out.println();
	}
	
	/**
	 * Get the number of accounts of the user
	 * @return the number of accounts
	 */
	public int numAccounts() {
		return this.accounts.size();
	}
	
	/*
	 * print the transaction history of a particular account
	 * account index of the account to use
	 */
	
	public void printAcctTransHistory(int acctIdx) {
		
		this.accounts.get(acctIdx).printTransHistory();
	}
	/**
	 * get the balance of the particular account 
	 * @param acctIdx the index of the account to use
	 * @return the balance of the account
	 */
	
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	/**
	 * Get the UUID of a particular account 
	 * @param acctIdx the index of the account to use
	 * @return   the UUID of the account
	 */
			
	public String getAcctUUID(int acctIdx) {
		
		return this.accounts.get(acctIdx).getUUID();
		
		}
	/*
	 * add a transaction to a particular account
	 *   account index of the account
	 *   the amount of the transaction
	 *   memo of the transaction
	 */
	public void addAcctTransaction(int acctIdx,double amount ,String memo) {
		
		this.accounts.get(acctIdx).addTransaction(amount,memo);
	}
	
}