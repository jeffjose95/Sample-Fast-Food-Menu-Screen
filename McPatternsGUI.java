package CS151.HW4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;

class McPatternsGUI extends JFrame{

	McPatternsPresenter presenter;									// The presenter
	ArrayList<Item> menu;											//List of all menu items
	ArrayList<Item> order;											//Stores the customers order
	private static DecimalFormat df =  new DecimalFormat("#.00");	// The decimal format
	private static CreditCardFactory cardReader;					//Used to create credit card

	public McPatternsGUI(McPatternsPresenter presenter) throws FileNotFoundException {
		
		this.presenter = presenter;
		presenter.attachView(this);
		showGUI();
		cardReader = new CreditCardFactory();

	}
	
	private void showGUI() throws FileNotFoundException {
	
		presenter.loadMenuItems();							//Loads menu data
		menu = presenter.getMenuItems();					//To store menu items
		order = new ArrayList<Item>();						//To store purchasing items
		
		JFrame theFrame = new JFrame("McPatterns");			//Name of frame
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setLayout(new BorderLayout());
		
		JPanel title = new JPanel(new FlowLayout());		//Title panel
		title.add(new JLabel("Welcome to McPatterns"));		//Heading
		title.setBackground(Color.orange);					//Sets the title background
		
		
		JPanel orderPane = new JPanel();					//Order panel used to display order
		orderPane.setLayout(new BoxLayout(orderPane, BoxLayout.Y_AXIS));
		orderPane.setBorder(BorderFactory.createBevelBorder(NORMAL));

		JTextArea cc = new JTextArea();						//Text field for credit card input
		
		JLabel orderDetails = new JLabel("Your order");		//Order panel title
		orderPane.add(orderDetails);						//Adds the title to the panel

		JTextArea total = new JTextArea("total = $0.00");	//Text field displaying total to pay
		total.setEditable(false);
		
		JTextArea receipt = new JTextArea(30,20);			//Text field displaying receipt		
		receipt.setEditable(false);
		receipt.setBorder(BorderFactory.createBevelBorder(NORMAL));
		
		//Scroll panel to hold the receipt text field inside
		JScrollPane receiptEntryHolder = new JScrollPane(receipt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		
		JButton confirm = new JButton("Place Order");		//Button that confirms order
		confirm.addActionListener(new ActionListener() {
			//On pressing
			public void actionPerformed(ActionEvent e) 
			{
				//Think about where you will store order and who should manipulate.
				//Handle the Payment validation before confirming order. Who should validate?
				boolean hasLetters = cc.getText().matches(".*[a-zA-Z]+.*");
				if(!cc.getText().isEmpty() && !hasLetters)
				{
					String creditCardNumber = cc.getText();
					CreditCard myCard = cardReader.CreditCardFactory(creditCardNumber);
					
					
					if(myCard != null && myCard.toPay(Double.parseDouble(total.getText().substring(9))))
					{
						orderDetails.setText("Order confrimed for: "+myCard.accountNumberHidden());
						String theOrder ="";
						for(int i = 0;i< order.size();i++)
						{
							theOrder += order.get(i).getName()+"...........$"+ df.format(order.get(i).getPrice())+"\n";
						}
						System.out.println(theOrder);
						order.clear();
						receipt.setText("");
						
						System.out.println(total.getText());
						total.setText("total = $0.00");
						System.out.println("ACCT: "+ myCard.accountNumberHidden());
						cc.setText("");						
					}
					else
					{
							orderDetails.setText("ERROR");
					}

				}
				else
				{
						orderDetails.setText("Enter a proper credit card number");
				}

			}


		});


		JButton cancel = new JButton("Cancel Order");		//Cancel order
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderDetails.setText("Order cancelled");	//Clears all previously stored info
				order.clear();
				receipt.setText(null);
				total.setText("total = $0.00");
			}

		});		
		
		//Adding all the parts of the order pane to the order pane
		orderPane.add(cc);
		orderPane.add(receiptEntryHolder);
		orderPane.add(total);
		orderPane.add(confirm);
		orderPane.add(cancel);

		
		JPanel buttonPanel = new JPanel();				//Menu Item Panel
		int gridSize = (int)Math.sqrt(menu.size());
		buttonPanel.setLayout(new GridLayout(gridSize, gridSize, 5, 5));
		buttonPanel.setBackground(Color.orange);
		
		//for each menu item button
		for(int i=0;i<menu.size();i++)
		{

			JButton confirmItem = new JButton(menu.get(i).getName()+": $"+df.format(menu.get(i).getPrice()));
			confirmItem.setToolTipText(""+i);
			confirmItem.addActionListener(new ActionListener() {
				
				//on clicking the button
				public void actionPerformed(ActionEvent e) {
					JButton reference = (JButton) e.getSource();
					orderDetails.setText("added " + menu.get(Integer.parseInt(confirmItem.getToolTipText())).getName());
					
					order.add(menu.get(Integer.parseInt(confirmItem.getToolTipText())));
			
					String theOrder ="";
					double totalPrice = 0;
					for(int i = 0;i< order.size();i++)
					{
						theOrder += order.get(i).getName()+"...........$"+ df.format(order.get(i).getPrice())+"\n";
						totalPrice += order.get(i).getPrice();
					}
					receipt.setText(theOrder);
					total.setText("total = $"+df.format(totalPrice));
				}

			});
			buttonPanel.add(confirmItem);
		}
		
		//adding the different panels to the frame
		theFrame.add(title,BorderLayout.NORTH);
		theFrame.add(buttonPanel, BorderLayout.CENTER);
		theFrame.add(orderPane, BorderLayout.LINE_START);
		theFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		theFrame.setVisible(true);
		
	}
}
