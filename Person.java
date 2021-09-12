import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 *this is  class that extends {@link ClubAbstractEntity} and holding details of a customer
 *and represent a form for a person type customer
 */
public class Person extends ClubAbstractEntity
{
	protected String id;
	protected String name;
	protected String surname;
	protected String tel;
	protected JTextField[] personJTextField;
	protected ArrayList<JLabel> asteriskJLabels;

	/**
	 *a 4-args constarctor that inialize the person args
	 *and creating a form with id,name,surname and tel fields
	 */
	public Person(String id,String name,String surname,String tel)
	{
		this.id=id;
		this.name=name;
		this.surname=surname;
		this.tel=tel;
		String []labelsName={"ID","Name","Surname","Tel"};
		JLabel[] personJLabels=new JLabel[labelsName.length];
		asteriskJLabels=new ArrayList<>();
		personJTextField=new JTextField[personJLabels.length];
		for(int i=0;i<personJLabels.length;i++)
		{
			personJTextField[i]=new JTextField(30);
			addToCenter(createJpanel(personJLabels[i],personJTextField[i],labelsName[i]));
		}
		setPersonJTextFields();
		setSize(450,220);
		setTitle("Person Clubber's Data");
	}
	/**
	 *a 0-args constarctor that call the 4-args constarctor and
	 * inilazile him with nulls
	 */
	public Person() {
		this(null,null,null,null);
		setVisible(true);
		cancelButton.setEnabled(false);
	}
	/**
	 *check if the key is equal to the id
	 * @param key is a string the enter by the manager
	 * @return true if it's equal else false
	 * implementaion of the {@link ClubAbstractEntity#match}
	 */
	public boolean match(String key)
	{
		return (id.equals(key));
	}
	/**
	 *check if the text in the textfields are in the right pattern
	 * @return true if they are else false
	 *implementaion of the {@link ClubAbstractEntity#validateData}
	 */
	protected boolean validateData()
	{
		String[] check={"\\d-\\d{7}\\|[1-9]","[A-Z][a-z]+","([A-Z][a-z]*[-']?)+","\\+\\([1-9]\\d{0,2}\\)[1-9]\\d{0,2}-[1-9]\\d{6}"};
		for (int j=0;j<asteriskJLabels.size();j++){
			asteriskJLabels.get(j).setVisible(false);
		}
		for(int i=0;i<personJTextField.length;i++)
		{

			String text=personJTextField[i].getText();
			if(!text.matches(check[i]))
			{
				asteriskJLabels.get(i).setVisible(true);
				return false;
			}

		}
		return true;
	}
	/**
	 *set the textfields text to the text that was at the opening of the person jframe
	 * implementaion of the {@link ClubAbstractEntity#rollBack}
	 */
	protected void rollBack()
	{
		setPersonJTextFields();
		for (int j=0;j<asteriskJLabels.size();j++){
			asteriskJLabels.get(j).setVisible(false);
		}
		setVisible(false);
	}
	/**
	 *set the args to the textfields text and set the visiblity of the jframe to false
	 *implementaion of the {@link ClubAbstractEntity#commit}
	 */
	protected void commit()
	{
		id = personJTextField[0].getText();
		name = personJTextField[1].getText();
		surname = personJTextField[2].getText();
		tel = personJTextField[3].getText();
		cancelButton.setEnabled(true);
		setVisible(false);

	}
	/**
	 *set all the textfields text to be equal to person args
	 */
	private void setPersonJTextFields(){
		personJTextField[0].setText(id);
		personJTextField[1].setText(name);
		personJTextField[2].setText(surname);
		personJTextField[3].setText(tel);
	}
	/**
	 *returning a string that hold all the args of the person
	 */
	@Override
	protected String ToString()
	{
		return this.id+" "+ this.name+" "+ this.surname+" " +this.tel;
	}
	/**
	 *@return true if id is not null else return false
	 */
	protected boolean isValidate()
	{
		return(id!=null);
	}
	/**
	 *create a Jpanel with a JLabel ,a JTextField and another JLabel that hold the asteriskJLabel
	 * @return  JPanel that was created
	 */
	protected JPanel createJpanel(JLabel personJLabel,JTextField textFieldJlabel ,String labelName){
		JPanel jPanel=new JPanel();
		jPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0, 2));
		asteriskJLabels.add(new JLabel("*", JLabel.LEFT));
		asteriskJLabels.get(asteriskJLabels.size()-1).setVisible(false);
		asteriskJLabels.get(asteriskJLabels.size()-1).setForeground(Color.RED);
		personJLabel=new JLabel(labelName, JLabel.RIGHT);
		personJLabel.setLabelFor(textFieldJlabel);
		personJLabel.setPreferredSize(new Dimension(75, 25));
		addToCenter(jPanel);
		JPanel labelsOnLeft=new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelsOnLeft.add(textFieldJlabel);
		jPanel.add(personJLabel, BorderLayout.WEST);
		jPanel.add(labelsOnLeft, BorderLayout.CENTER);
		jPanel.add(asteriskJLabels.get(asteriskJLabels.size()-1), BorderLayout.EAST);
		return jPanel;
	}
}