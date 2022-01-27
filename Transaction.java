import java.util.Date;

public class Transaction {
	
	private double amount;
	
	/**
	 * The amount of this transaction.
	 */
	
	private Date timestamp;
	
	/**
	 * The time and date of this transaction.
	 */
	
	private String memo;
	
	/**
	 * The memo for this transaction.
	 */
	
	private Account inAccount;
	
	/**
	 * The account in which the transaction was performed.
	 */
	/**
	 * CREATE A NEW TRANSACTION
	 * @param amount  amount to be transacted
	 * @param inAccount the account the transaction belongs to
	 */
	
	public Transaction(double amount,Account inAccount ) {
		
		this.amount=amount;
		this.inAccount=inAccount;
		this.timestamp=new Date();
		this.memo="";
		
	}
	public Transaction(double amount,String memo,Account inAccount) {
		
		//call the two-org constructor first
		this(amount,inAccount);
		
		//set the memo
		this.memo=memo;
		
		
	}
	/**
	 * Get the amount of the transaction
	 * @return the amount
	 */
	public double getAmount() {
		return this.amount;
	}
	/*
	 * get a string summarizing the transaction
	 */
	
	public String getSummaryLine() {
		if(this.amount >=0) {
			return 	String.format("%s : Rs%.02f : %s\n\n ", this.timestamp.toString(),this.amount,this.memo);
		}
		else {
			return 	String.format("%s : Rs(%.02f) : %s\n\n", this.timestamp.toString(),this.amount,this.memo);
			
		}
					
					
	}
}