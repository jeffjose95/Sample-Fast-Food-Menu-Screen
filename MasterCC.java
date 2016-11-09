package CS151.HW4;

//Master card
public class MasterCC extends CreditCard {

	public MasterCC(String cardNumber) {
		super(cardNumber);
	}
	
	@Override
	public String accountNumberHidden()
	{
		String last = accountNumber.substring(accountNumber.length() - 4);
		String returning = "XXXXXXXXXXXX"+last+" MASTERCARD";
		return returning;
	}

}
