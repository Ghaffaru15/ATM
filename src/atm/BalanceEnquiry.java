package atm;

//Represents a balance enquiry of ATM
public class BalanceEnquiry extends Transaction
{
	//BalanceEnquiry constructor
	public BalanceEnquiry( int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase )
	{
		super(userAccountNumber, atmScreen, atmBankDatabase);
	}// end balanceEnquiry constructor
	
	//performs the transaction
	public void execute()
	{
		//get references to bank database and screen 
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		//get the available balance for the account involved
		double availableBalance = bankDatabase.getAvailableBalance( getAccountNumber() );
	
		double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
		
		//display the balance information on the screen
		screen.displayMessageLine("\nBalance Information: ");
		screen.displayMessage(" - Available Balance: ");
		screen.displayDollarAmount(availableBalance);
		screen.displayMessage("\n - Total Balance");
		screen.displayDollarAmount(totalBalance);
		screen.displayMessageLine("");
	} //end method execute
}//end class BalanceEnquiry
