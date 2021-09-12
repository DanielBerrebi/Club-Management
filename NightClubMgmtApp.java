// File: NightClubMgmtApp.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
/**
 *this class is displaying a GUI JPanel that allow the manager to manage his club by the follow options(read the customers list from BKCustomers.dat):
 *search a customer by key (show the customer details and able change them).
 *add a {@link Person} type customer.
 *add a {@link Soldier} type customer.
 *add a {@link Student} type customer.
 *exit and save the all customers in the BKCustomers.dat file
 */
public class NightClubMgmtApp
{
	//Night-Club Regular Customers Repository
	private ArrayList<ClubAbstractEntity> clubbers;
	private JTextField searchTextField;
	private JButton[] buttons;
	private MenuButtonsHandler handler;
	private JFrame frame;


	/**
	 *a 0-args constarctor that create an ArrayList of clubbers ({@link ClubAbstractEntity})
	 * inserting to the ArrayList the existing customers from the BKCustomers.dat file.
	 * and creating a GUI JPanel that allow the manager to manage his club.
	 */
	public NightClubMgmtApp()
	{
		clubbers = new ArrayList<>();
		loadClubbersDBFromFile();
		createGui();


	}
	/**
	 *creating a JFrame and adding to him 3 JPanels and displying him
	 *initialize all the buttons and apply a handler for them by the  {@link #initButtons} method.
	 *and create a listner to the exit click
	 */
	private void createGui() {
		searchTextField=new JTextField(25);
		initButtons();
		frame = new JFrame("Club Management");
		frame.getContentPane().add(buttons[0], BorderLayout.NORTH);
		JPanel jPanel=new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.add(buttons[1], BorderLayout.WEST);
		jPanel.add(buttons[2], BorderLayout.CENTER);
		jPanel.add(buttons[3], BorderLayout.EAST);
		frame.getContentPane().add(jPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(350,150);
		frame.setVisible(true);
		frame.setResizable(false);

		frame.addWindowListener(new WindowAdapter()

		{
			/**
			 *a listner to the exit button pressed
			 */
			@Override
			public void windowClosing(WindowEvent e)
			{
				writeClubbersDBtoFile();
				System.exit(0);
			}
		});
	}
	/**
	 *initialize all the buttons and apply an handler for them
	 */
	private void initButtons(){
		String [] buttonsText={"Search by key","Add Person","Add Student","Add Soldier"};
		buttons=new JButton[buttonsText.length];
		handler=new MenuButtonsHandler();
		for (int i=0;i< buttons.length;i++)
		{
			buttons[i] = new JButton(buttonsText[i]);
			buttons[i].addActionListener(handler);
		}
	}
	/**
	 *this method read the customers list from BKCustomers.dat and adding them to the the {@link ClubAbstractEntity} arraylist
	 */
	private void loadClubbersDBFromFile() {

		try {
			FileInputStream readData = new FileInputStream("BKCustomers.dat");
			ObjectInputStream readStream = new ObjectInputStream(readData);

			clubbers = (ArrayList<ClubAbstractEntity>) readStream.readObject();
			readStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		/**
		 *this method write the {@link ClubAbstractEntity} arraylist to BKCustomers.dat
		 * checking if a {@link Person} is valid by the {@link Person#isValidate} method
		 * if it's valid adding to the BKCustomers.dat a string that hold all of the {@link Person} attributes by the {@link Person#ToString} method.
		 */
		private void writeClubbersDBtoFile()
		{
			for(int i=clubbers.size()-1;i>=0;i--)
			{
				if(!clubbers.get(i).isValidate())
					clubbers.remove(i);
			}
			try{
				FileOutputStream writeData = new FileOutputStream("BKCustomers.dat");
				ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

				writeStream.writeObject(clubbers);
				writeStream.flush();
				writeStream.close();

			}catch (IOException e) {
				e.printStackTrace();
			}
	}
	/**
	 *create an NightClubMgmtApp that start the program
	 */
	public static void main(String[] args)
	{
		NightClubMgmtApp appliction = new NightClubMgmtApp();
	}

	/**
	 *the class MenuButtonsHandler implements ActionListener
	 */
	private class MenuButtonsHandler implements ActionListener
	{
		/**
		 *on ActionEvent check which button was pressed and acting correspondingly
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == buttons[0])
			{
				boolean found=false;
				String key = JOptionPane.showInputDialog(frame,"Please Enter The Clubber's Key");
				for(ClubAbstractEntity clubber : clubbers)
					if(clubber.isValidate()) {
						if (clubber.match(key)) {
							clubber.setVisible(true);
							found = true;
							break;
						}
					}
				if(!found)
					JOptionPane.showMessageDialog(frame, String.format("Clubber with key %s does not exist", key),"Clubber wasn't found!",JOptionPane.INFORMATION_MESSAGE);

			}
			else if (e.getSource() == buttons[1])
				clubbers.add(new Person());
			else if (e.getSource() == buttons[2])
				clubbers.add(new Student());
			else if (e.getSource() == buttons[3])
				clubbers.add(new Soldier());

		}

	}
}