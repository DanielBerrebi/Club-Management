import javax.swing.*;
import java.awt.*;
/**
 *this is class that extends {@link Student} and holding details of a customer
 *and represent a form for a Student type customer
 */
public class Student extends Person
{
	protected String studentID;
	protected JTextField studentIDTextField;

	/**
	 *a 5-args constarctor that inialize the person args
	 *and creating a form with id,name,surname,tel and student ID fields
	 */
	public Student(String id,String name,String surname,String tel,String studentID)
	{
		super(id,name,surname,tel);
		this.studentID=studentID;
		JLabel studentIDJLabels=new JLabel();
		studentIDTextField=new JTextField(30);
		addToCenter(createJpanel(studentIDJLabels,studentIDTextField,"Student ID"));
		studentIDTextField.setText(studentID);
		setSize(450,250);
		setTitle("Student Clubber's Data");
	}

	/**
	 *a 0-args constarctor that call the 5-args constarctor and
	 * inilazile him with nulls
	 */
	public Student() {
		this(null,null,null,null,null);
		setVisible(true);
		cancelButton.setEnabled(false);
	}


	/**
	 *check if the key is equal to the id or to a string from the 5 char until the end of the studentID
	 * @param key is a string the enter by the manager
	 * @return true if it's equal the one of them else false
	 */
	@Override
	public boolean match(String key)
	{
		return ((id.equals(key))||((studentID.substring(4, studentID.length())).equals(key)));
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
		String check="[A-Z]{3}/[1-9]\\d{4}";
		if(!studentIDTextField.getText().matches(check))
		{
			asteriskJLabels.get(4).setVisible(true);
			return false;//and add here to put a red * after that text field
		}
		return true;
	}

	/**
	 *set the textfields text to the text that was at the opening of the student jframe
	 */
	@Override
	protected void rollBack()
	{
		super.rollBack();
		studentIDTextField.setText(studentID);
	}

	/**
	 *set the args to the textfields text and set the visiblity of the jframe to false
	 */
	@Override
	protected void commit()
	{
		super.commit();
		studentID=studentIDTextField.getText();
		setVisible(false);

	}
	/**
	 *returning a string that hold all the args of the student
	 */
	@Override
	protected String ToString()
	{
		return super.ToString()+ " " +this.studentID;
	}
}