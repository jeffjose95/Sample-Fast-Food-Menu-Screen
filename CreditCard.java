package CS151.HW4;

public abstract class CreditCard {
	
	protected String accountNumber;		//account Number
	protected double balance;			//balance left in card
	protected double cardLimit;			//Card limit - balance cannot cross the cardLimit for cc
	
	public CreditCard(String cardNumber) 
	{
		accountNumber = cardNumber;
		balance =  0;					//Can be improved by adding a server with a list of all 
		cardLimit = 100;				//	credit card information for each accountNumber
	}
	

	public boolean toPay(double d)		//Returns true and pays if possible else prints error message
	{
		if(cardLimit> balance + d)
		{
			balance += d;
			return true;
		}
		else
		{
			System.out.println("ERROR OVER BALANCE LIMIT");
			return false;
		}
	}	
	
	public abstract String accountNumberHidden();	//Common method for all subclasses


}
