package CS151.HW4;

import java.text.DecimalFormat;
import java.util.ArrayList;

class MenuModel {
	
	private ArrayList<Item> MenuItems;		//The list of menu items

	//Constructor
	public MenuModel()
	{
		MenuItems = new ArrayList<Item>();

	}
	
	//Adds the items to menuItems
	public void add(Item menuItem)
	{
		MenuItems.add(menuItem);
	}
	
	//Returns the menuItems
	public ArrayList<Item> getMenuItems()
	{
		return MenuItems;
	}
}