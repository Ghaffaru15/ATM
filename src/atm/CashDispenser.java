package atm;

//Represents the cash dispenser of the atm
public class CashDispenser 
{
	//the default initial number of bills in the cash dispenser
	private static final int INITIAL_COUNT = 500;
	private int count; //number of $20 bills remaining
	
	//no-argument CashDispenser constructor initializes count to default
	public CashDispenser()
	{
		count = INITIAL_COUNT; //set count attribute to default
	}//end CashDispenser constructor
	
	//simulates dispensing of specific amount of cash
	public void dispenseCash( int amount)
	{
		int billsRequired = amount /20; // Number of $20 bills required
		count -= billsRequired; //update the count of bills
	}//end method dispenseCash
	
	//indicate whether cash dispenser can dispense desired amount
	public boolean isSufficientCashAvailable( int amount)
	{
		int billsRequired = amount/20; //number of $20 bills required
		
		if ( count >= billsRequired)
			return true;	//enough bills available
		else
			return false; //not enough bills available
	}
} //end class CashDispenser
