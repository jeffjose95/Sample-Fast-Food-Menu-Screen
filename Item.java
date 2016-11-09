package CS151.HW4;

//The item class
public class Item
{
	private String name;
	private double price;

	//Constructor
	public Item(String theName,double thePrice)
	{
		name = theName;
		price = thePrice;
	}
	
	//Returns the name
	public String getName()
	{
		return name;
	}
	
	//Returns the price
	public double getPrice()
	{
		return price;
	}
	
}