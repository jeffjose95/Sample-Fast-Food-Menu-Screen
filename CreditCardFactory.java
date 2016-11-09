package CS151.HW4;

//Class used to create the different credit card types
public class CreditCardFactory {

	//Enum used to store the different card types
	public enum cardType
	{
		Mastercard,Visa,AmericanExpress,Discover
	}
	
	//Returns a credit card based on the card type
	public CreditCard CreditCardFactory(String cardNumber)
	{		
		
		cardType theCard = Count(cardNumber);	//checks the card type based on the input given
		
		CreditCard myCard = null;
		
		if (theCard ==cardType.AmericanExpress)
		{
			myCard = new AmExCC(cardNumber);
		}
		else if (theCard ==cardType.Discover)
		{
			myCard = new Discover(cardNumber);
		}
		else if (theCard ==cardType.Mastercard)
		{
			myCard = new MasterCC(cardNumber);
		}
		else if (theCard ==cardType.Visa)
		{
			myCard = new VisaCC(cardNumber);
		}
		
		return myCard;
	}
	
	//returns what card type we have
	public cardType Count(String theNumber) {
	int secondDigit = Integer.parseInt(theNumber.substring(1, 2));
	String part = theNumber.substring(0,4); 

	if(theNumber.startsWith("5") && (secondDigit> 0 && secondDigit< 6) && theNumber.length() == 16)
	{
		return cardType.Mastercard;
	}
	else if(theNumber.startsWith("4") &&(theNumber.length() == 13 || theNumber.length() == 16))
	{
		return cardType.Visa;
	}
	else if(theNumber.startsWith("3")&& (secondDigit == 4 || secondDigit == 7) && theNumber.length() == 15)
	{
		return cardType.AmericanExpress;
	}
	else if(part.equals("6011") && theNumber.length() == 16)
	{
		return cardType.Discover;
	}
	return null;
	}

}
