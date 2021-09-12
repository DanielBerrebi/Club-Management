import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

/**
 *this is an abstract class that extends JFrame and represent a customer
 *and holding an ok and cancel button and a handler for them,also holding a JPanel that in center of the jframe
 */
public abstract class ClubAbstractEntity extends JFrame implements Serializable
{
	protected final JButton okButton;
	protected final JButton cancelButton;
	protected ButtonsHandler handler;
	protected final JPanel centerPanel;

	/**
	 *a 0-args constarctor that create the buttons and handler and set them in the JPanel,
	 * also create JPanel that he is in the middle of the JFrame
	 */
	public ClubAbstractEntity()
	{
		setResizable(false);//block resize
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//X don't close
		JPanel buttonsPanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		add(buttonsPanel,BorderLayout.SOUTH);
		handler=new ButtonsHandler();
		okButton.addActionListener(handler);
		cancelButton.addActionListener(handler);
		centerPanel=new JPanel(new GridLayout(5, 1));
 	}

	/**
	 *this method is adding a Component to the center of the Jframe
	 * @param guiComponent
	 */
 	protected void addToCenter(Component guiComponent)
 	{
		centerPanel.add(guiComponent);
		JPanel myComponent = new JPanel(new FlowLayout(FlowLayout.CENTER));
		myComponent.add(centerPanel, new GridLayout());
		add(myComponent);
 	}

	/**
	 *check if the key is equal to the id or other field the is uniqe for the {@link ClubAbstractEntity}
	 */
 	public abstract boolean match(String key);

	/**
	 *check if the text in the textfields are in the right pattern
	 * @return true if they are else false
	 */
	protected abstract boolean validateData();

	/**
	 *set the args to the textfields text and set the visiblity of the jframe to false
	 */
	protected abstract void commit();

	/**
	 *set the textfields text to the text that was at the opening of the person jframe
	 */
	protected abstract void rollBack();

	/**
	 *returning a string that hold all the
	 */
	protected abstract String ToString();

	/**
	 *@return true if id is not null else return false
	 */
	protected abstract boolean isValidate();

	/**
	 *the inner class ButtonsHandler implements ActionListener
	 */
 	private class ButtonsHandler implements ActionListener,Serializable
 	{
		/**
		 *on ActionEvent check if ok Button was click if true check if the data is valid with the {@link #validateData} if true save the data
		 * with the method {@link #commit}
		 * if cancel Button was click use the {@link #rollBack()} method
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == okButton)
			{
				if(validateData())
					commit();

			}
			else if (e.getSource() == cancelButton)
			{
				rollBack();
			}

		}
 	}
}