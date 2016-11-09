package CS151.HW4;

//Visa card
public class VisaCC extends CreditCard{

	public VisaCC(String cardNumber) {
		super(cardNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String accountNumberHidden() {
		String last = accountNumber.substring(accountNumber.length() - 4);
		if(accountNumber.length() == 13)
		{
			String returning = "XXXXXXXXX"+last+" VISA";
			return returning;
		}
		String returning = "XXXXXXXXXXXX"+last+" VISA";
		return returning;
	}

}
