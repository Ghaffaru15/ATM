package atm;

//Represents the bank account information database
public class BankDatabase 
{
	private Account accounts[]; //array of accounts
	
	//no-arguments BankDatabase constructor initializes accounts
	public BankDatabase()
	{
		accounts = new Account[2]; //just two accounts for testing
		accounts[0] = new Account(12345,54321,1000.0,1200.0);
		accounts[1] = new Account(98765,56789,200.0,200.0);
		
	}// end no-argument constructor
	
	//retrieve account object containing specified account number
	private Account getAccount(int accountNumber)
	{
		//loop through accounts searching for a match
		for (Account acc : accounts)
		{
			//return current account if match
			if (acc.getAccountNumber() == accountNumber)
				return acc;
		}//end for
		return null; //if no match if found, return null
		
	}//end method getAccount()
	
	//determine whether user-specified account number and PIN match
	//those of the account in the database
	public boolean authenticateUser(int userAccountNumber, int userPIN)
	{
		//attempt to retrieve account with the account number
		Account userAccount = getAccount(userAccountNumber);
		
		//if account exists, return result of Account method validatePIN
		if ( userAccount != null )
		{
			return userAccount.validatePin(userPIN);
		}else
		{
			return false; //account number not found, so return false
		}
	} // end method authenticate user
	
	//return available balance of Account with specified account number
	public double getAvailableBalance(int userAccountNumber )
	{
		return getAccount( userAccountNumber ).getAvailableBalance();
	}//end method getAvailableBalance
	
	//return total balance of the Account with specified account number
	public double getTotalBalance( int userAccountNumber )
	{
		return getAccount( userAccountNumber ).getTotalBalance();
	} // end method getTotalBalance
	
	//credit an amount to Account with specified information
	public void credit( int userAccountNumber , double amount)
	{
		getAccount( userAccountNumber ).credit(amount);
	}//end method credit
	
	//debit an amount from Account with specified information
	public void debit ( int userAccountNumber, double amount)
	{
		getAccount (userAccountNumber).debit(amount);
	} // end method debit
	
} // end class BankDatabase
