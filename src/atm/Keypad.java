package atm;

import java.util.Scanner;

//represents the keypad of the atm
public class Keypad 
{
	private Scanner input;	//program uses scanner to obtain input
	
	//default Constructor
	public Keypad()
	{
		input = new Scanner( System.in );
	}//end default constructor
	
	//return an integer value entered by the user
	public int getInput()
	{
		return input.nextInt(); //we assume that the user enters an int
	}//end method getInput()
}//end class Keypad
