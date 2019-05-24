package atm;

//represents an automated teller machine
public class ATM {
	
	private boolean userAuthenticated; //whether use is authenticated
	private int currentAccountNumber; //current user's account number
	private Screen screen;	//ATM's screen
	private Keypad keypad;	//ATM's keypad
	private CashDispenser cashDispenser; //ATM's cash Dispenser
	private DepositSlot depositSlot; 	//ATM's deposit slot
	private BankDatabase bankDatabase; //account information database
	
	//constants corresponding to the main menu options
	private static final int BALANCE_ENQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;
	
	//ATM's default Constructor
	public ATM()
	{
		userAuthenticated = false; //user is not authenticated to start
		currentAccountNumber = 0; //No account number to start
		screen = new Screen(); //create screen
		keypad = new Keypad(); //create keypad
		cashDispenser = new CashDispenser(); //create cash dispenser
		depositSlot = new DepositSlot(); //create deposit slot
		bankDatabase = new BankDatabase(); //create account info database
	}//end default constructor
	
	//Start ATM
	public void run()
	{
		//welcome and authenticate user; perform transactions
		while ( true )
		{
			//loop while the user is not authenticated yet
			while (!userAuthenticated)
			{
				screen.displayMessageLine("\nWelcome\n");
				authenticateUser(); //authenticate user
			}//end while
			
			performTransactions(); //user is now authenticated
			userAuthenticated = false; //reset before next session
			currentAccountNumber = 0; 
			screen.displayMessageLine("\nThank you!, Good bye!");
		}//end while
	} // end method run()
	
	//attempts to authenticate user against database
	private void authenticateUser()
	{
		screen.displayMessageLine("\nPlease enter your account Number: ");
		int accountNumber = keypad.getInput(); //input account number
		screen.displayMessageLine("\nPlease enter your pin: "); //prompt for pin
		int pin = keypad.getInput(); //input pin
		
		userAuthenticated = bankDatabase.authenticateUser(accountNumber,pin);
		
		//check where authentication succeed
		if ( userAuthenticated )
		{
			currentAccountNumber = accountNumber;
		}
		else
		{
			screen.displayMessageLine("Invalid Account Number or PIN, please try again.");
		}
	}//end method authenticateUser()
	
	//display the main menu and perform transactions
	private void performTransactions()
	{
		//local variable to store the transaction currently being processed
		Transaction currentTransaction = null;
		boolean userExited = false; //user has not chosen to exit
		
		//loop while the user has not chosen option to exit
		while ( !userExited )
		{
			int mainMenuSelection = displayMainMenu();
			
			//decide how to proceed based on users' menu selection
			switch ( mainMenuSelection )
			{
				//user chose to perform one of the three transaction types
			case BALANCE_ENQUIRY:
			case WITHDRAWAL:
			case DEPOSIT:
				//initialize as new object of the chosen type
				currentTransaction = createTransaction( mainMenuSelection );
				currentTransaction.execute(); //execute transaction
				break;
			case EXIT:
				screen.displayMessageLine("\nExiting the system...");
				userExited = true; //this ATM session should end
				break;
				default:
					screen.displayMessageLine("\nYou did not enter a valid selection, Try again");
					break;
			}//end switch
			
		}//end while
		
	}//end method performTransactions
	
	//display the main menu and return an input selection
	private int displayMainMenu()
	{
		screen.displayMessageLine("\nMain Menu:");
		screen.displayMessageLine("1 - View my balance");
		screen.displayMessageLine("2 - Withdraw cash");
		screen.displayMessageLine("3 - Deposit Funds");
		screen.displayMessageLine("4 - Exit\n");
		screen.displayMessageLine("Enter a choice: ");
		return keypad.getInput(); //return user's selection
	}//end method displayMainMenu
	
	//return object of specified Transaction subclass
	private Transaction createTransaction( int type )
	{
		Transaction temp = null; //temporary transaction variable
		
		//determine which type of transaction to create
		switch ( type )
		{
		case BALANCE_ENQUIRY: //create new balance enquiry transaction
			temp = new BalanceEnquiry( currentAccountNumber,screen,bankDatabase );
			break;
		case WITHDRAWAL: //create withdrawal transaction
			temp = new Withdrawal( currentAccountNumber, screen, bankDatabase, keypad, cashDispenser );
			break;
		case DEPOSIT: //create new Deposit Transaction
			temp  = new Deposit( currentAccountNumber , screen, bankDatabase, keypad, depositSlot );
			break;
			
		}//end switch
		return temp; //retrn newly created object
	}//end method createTransaction()
}//end class ATM
