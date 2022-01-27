import java.util.ArrayList;
import java.util.Random;

public class Bank {
	
	

	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	/**
	 * create new object with empty list of users and accounts
	 * @param name the name of the bank
	 */
		
	public Bank(String name) {
		
		this.name=name;
		this.users=new ArrayList<User>();
		this.accounts=new ArrayList<Account>();
	}
	public String  getNewUserUUID() {
		
		//initialize UUID
		String uuid;
		Random rng =new Random();
		int len=6;
		boolean nonUnique ;
		//continue looping until we get a unique ID
		do {
			//generate the number
			uuid="";
			for(int c=0;c<len;c++) {
				
				uuid +=((Integer)rng.nextInt(10)).toString();//concept of strings
			}
			
			//check to make sure its unique
			nonUnique=false;
			for(User u: this.users) {
				if(uuid.compareTo(u.getUUID())==0) {//concept of strings
					nonUnique=true;
					break;
				}
			}
				
		}while(nonUnique);
		
		
		return uuid;
	}
	/**
	 * Generate a new universally unique ID for the user.
	 * @return the id 
	 */
	
	public String getNewAccountUUID() {
		
		//initialize uuid
		String uuid;
		Random rng =new Random();
		int len=10;
		boolean nonUnique ;
		//continue looping until we get a unique ID
		do {
			//generate the number
			uuid="";
			for(int c=0;c<len;c++) {
				
				uuid +=((Integer)rng.nextInt(10)).toString();//concept of strings
			}
			
			//check to make sure its unique
			nonUnique=false;
			for(Account a: this.accounts) {
				if(uuid.compareTo(a.getUUID())==0) {
					nonUnique=true;
					break;
				}
			}
				
		}while(nonUnique);
		
		
		return uuid;
		
	}
	/**
	 * add an account 
	 * @param anAcct an account to add
	 */
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	/**
	 * create a new user of the bank
	 * @param firstName the users first name
	 * @param lastName  the users last name
	 * @param pin       the users pin
	 * @return          the new User object
	 */
     public User addUser(String firstName,String lastName,String pin) {
    	 
    	 //create a new User object and add it to our list
    	 User newUser =new User(firstName,  lastName,  pin,  this);
    	 this.users.add(newUser);
    	 
    	 
    	 //create a savings account for the user object 
    	 Account newAccount =new Account ("Savings", newUser ,this);
 		 newUser.addAccount(newAccount);
    	// this.accounts.add(newAccount);
 		 this.addAccount(newAccount);
    	 
    	 return newUser;
     }
     
     /**
      * get the user ID object associated with a particular userID and pin if they or valid 
      * @param userID   the UUID of the user to log in
      * @param pin      the pin of the user 
      * @return         the user object ,if login is successful,or null,if it is not
      */
     public User userLogin(String userID , String pin) {
    	 
    	 //search through the list of users 
    	 for(User u:this.users) {
    		 
    		 //check userID is correct
    		 if(u.getUUID().compareTo(userID)==0&& u.validatePin(pin)){
    			 return u;
    		 }
    	 }
    	 
    	 // if we haven't found the user or have an incorrect pin
    	 
    	return null ;
    	 
     }
     /**
      * get the name of the bank
      * @return bank name
      */
     public String getName() {
    	 return this.name;
    	 
     }
}