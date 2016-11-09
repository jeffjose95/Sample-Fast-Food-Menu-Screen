package CS151.HW4;

//American Express
public class AmExCC extends CreditCard{	

	public AmExCC(String cardNumber) {
		super(cardNumber);
	}

	@Override
	public String accountNumberHidden() {
		String last = accountNumber.substring(accountNumber.length() - 4);
		String returning = "XXXXXXXXXXX"+last+" AMERICAN EXPRESS";
		return returning;
		}

}
