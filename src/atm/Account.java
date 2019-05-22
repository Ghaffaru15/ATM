package atm;

//Represents a bank Account
public class Account
{
	private int accountNumber; // account number
	private int pin; //PIN for authentication
	private double availableBalance; //funds available for withdrawal
	private double totalBalance; //funds available + pending posts
	
	//Account constructor initializes attributes
	public Account ( int theAccountNumber, int thePin, double theAvailableBalance, double theTotalBalance)
	{
		accountNumber = theAccountNumber;
		pin = thePin;
		availableBalance = theAvailableBalance;
		totalBalance = theTotalBalance;
	}//end Account constructor
	
	//determines whether a user-specified PIN matches PIN in account
	public boolean validatePin( int p)
	{
		if (p == pin)
			return true;
		else
			return false;
	}//end method validatePin
	
	//returns the total balance
	public double getTotalBalance()
	{
		return totalBalance;
	}//end method getTotalBalance
	
	//returns available balance
	public double getAvailableBalance()
	{
		return availableBalance;
	}//end method getAvailableBalance
	
	//credits an amount to the account
	public void credit( double amount)
	{
		totalBalance +=amount;// adds to the total balance
	}//end method credit
	
	//debits an amount from the account
	public void debit (double amount )
	{
		availableBalance -=amount; //subtract from available balance
		totalBalance -=amount; //subtracts from total balance
		
	}//end method debit
	
	//returns account number
	public int getAccountNumber()
	{
		return accountNumber; 
	}//end method getAccountNumber
	
}//end class Account
