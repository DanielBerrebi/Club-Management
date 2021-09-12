import javax.swing.*;
import java.awt.*;
/**
 *this is class that extends {@link Person} and holding details of a customer
 *and represent a form for a Soldier type customer
 */
public class Soldier extends Person
{
	protected String personalNum;
	protected JTextField personalNumTextField;

	/**
	 *a 5-args constarctor that inialize the person args
	 *and creating a form with id,name,surname,tel and personal num fields
	 */
	public Soldier(String id,String name,String surname,String tel,String personalNum)
	{
		super(id,name,surname,tel);
		this.personalNum=personalNum;
		JLabel personalNumJLabels=new JLabel();
		personalNumTextField=new JTextField(30);
		addToCenter(createJpanel(personalNumJLabels,personalNumTextField,"Personal No."));
		personalNumTextField.setText(personalNum);
		setSize(450,250);
		setTitle("Soldier Clubber's Data");
	}
	/**
	 *a 0-args constarctor that call the 5-args constarctor and
	 * inilazile him with nulls
	 */
	public Soldier() {
		this(null,null,null,null,null);
		setVisible(true);
		cancelButton.setEnabled(false);
	}

	/**
	 *check if the key is equal to the id or the personalNum
	 * @param key is a string the enter by the manager
	 * @return true if it's equal the one of them else false
	 */
	@Override
	public boolean match(String key)
	{
		return ((id.equals(key))||(personalNum.equals(key)));
	}


	/**
	 *check if the text in the textfields are in the right pattern
	 * @return true if they are else false
	 */
	@Override
	protected boolean validateData()
	{
		if(!super.validateData())
			return false;
		String check="[ROC]/[1-9]\\d{6}";
		if(!personalNumTextField.getText().matches(check))
		{
			asteriskJLabels.get(4).setVisible(true);
			return false;
		}
		return true;
	}

	/**
	 *set the textfields text to the text that was at the opening of the soldier jframe
	 */
	@Override
	protected void rollBack()
	{
		super.rollBack();
		personalNumTextField.setText(personalNum);
	}

	/**
	 *set the args to the textfields text and set the visiblity of the jframe to false
	 */
	@Override
	protected void commit()
	{
		super.commit();
		personalNum=personalNumTextField.getText();
		setVisible(false);
	}

	/**
	 *returning a string that hold all the args of the soldier
	 */
	@Override
	protected String ToString()
	{
		return super.ToString()+" "+ this.personalNum;
	}
}