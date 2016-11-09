package CS151.HW4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class McPatternsPresenter 
{
    //This is the class that will handle the model <-> UI communication.
    MenuModel model;
    McPatternsGUI view;
    ArrayList<Item> menuItems;
    String fileName;
    void loadMenuItems() throws FileNotFoundException 
    {
    	model = new MenuModel();

     	Scanner fileLocation = new Scanner(System.in);
    	System.out.println("What is the name of your file?");
    	
    	File folder = new File("");
    	File[] filesInFolder =  folder.listFiles();
    	
    	
    	fileName = fileLocation.next() + ".txt";
		fileLocation.close();
		
    	Scanner fileReading = new Scanner(new File(fileName));

    	
    	while(fileReading.hasNextLine())
    	{
    		String theNextLine = fileReading.nextLine();
    		int breakPosition = theNextLine.indexOf("|");
    		String[] Itemparts = new String[2];
    		Itemparts[0] = theNextLine.substring(0, breakPosition);
    		Itemparts[1] = theNextLine.substring(breakPosition+1);
    		double value = Double.parseDouble(Itemparts[1]);
    		Item nextItem = new Item(Itemparts[0],value);
    		model.add(nextItem);
    	}        	
    }
    
    

    void attachView(McPatternsGUI view) {
        this.view = view;
    }
    
    // Add functions to return the menu items. 
    public ArrayList<Item> getMenuItems()
    {
    	return model.getMenuItems();
    }
}