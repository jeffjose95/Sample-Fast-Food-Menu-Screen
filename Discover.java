package CS151.HW4;

//Discover card
public class Discover extends CreditCard {

	public Discover(String cardNumber) {
		super(cardNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String accountNumberHidden() {
		String last = accountNumber.substring(accountNumber.length() - 4);
		String returning = "XXXXXXXXXXXX"+last+" DISCOVER";
		return returning;
	}

}
