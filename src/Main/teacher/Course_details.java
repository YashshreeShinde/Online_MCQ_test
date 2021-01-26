package Main.teacher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Course_details extends JPanel implements ActionListener,FocusListener{
	private JTextField HH;
	private JTextField MM;
	private JTextField SS;
	private int addquestionMark=0;
	int t;
	JLabel lblEachMark,lbltime;
	String time;
	JButton b1;
	JComboBox<Integer> eachMark;
	String username,subject;
	 
	public Course_details(String user,String subject)
	{
		
		JPanel menuPanel=new JPanel();
		menuPanel.setBounds(0, 0,800,100);
	   menuPanel.setLayout(null);
	   menuPanel.setBackground(new Color(42, 145, 176));
	   add(menuPanel);
	   
		this.username=user;
		this.subject=subject;
		setLayout(null);
//	    getContentPane().setBackground(Color.white);
	    setBounds(300, 150,800,400);
	    
	   
	    lbltime = new JLabel("COURSE:  "+subject);
		lbltime.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbltime.setForeground(Color.DARK_GRAY);
		lbltime.setBounds(280, 30, 200, 33);
		menuPanel.add(lbltime);
	    
	    
	lbltime = new JLabel("SET DURATION:  ");
	lbltime.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lbltime.setForeground(Color.DARK_GRAY);
	lbltime.setBounds(100, 150, 200, 33);
	add(lbltime);
	
	HH = new JTextField();
	HH.setForeground(Color.LIGHT_GRAY);
	HH.setText("H");
	HH.setFont(new Font("Mangal", Font.PLAIN, 20));
	HH.setColumns(10);
	HH.setBounds(300, 160, 30, 21);
	add(HH);
	HH.addFocusListener(this);
	
	MM = new JTextField();
	MM.setForeground(Color.LIGHT_GRAY);
	MM.setText("M");
	MM.setFont(new Font("Mangal", Font.PLAIN, 20));
	MM.setColumns(10);
	MM.setBounds(350,  160, 30, 21);
	add(MM);
	MM.addFocusListener(this);
	
	
  SS = new JTextField();
	SS.setForeground(Color.LIGHT_GRAY);
	SS.setText("S");
	SS.setFont(new Font("Mangal", Font.PLAIN, 20));
	SS.setColumns(10);
	SS.setBounds(400,  160, 30, 21);
	add(SS);
	SS.addFocusListener(this);
	
	
	lblEachMark = new JLabel("EACH MARK:");
	lblEachMark.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblEachMark.setForeground(Color.DARK_GRAY);
	lblEachMark.setBounds(100, 200, 200, 33);
	add(lblEachMark);
//	
	eachMark= new JComboBox<Integer>();
	eachMark.setFont(new Font("SimSun", Font.PLAIN, 20));
	eachMark.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1,2,3,4,5,6,7,8}));
	eachMark.setBounds(300, 210, 57, 21);
	add(eachMark);
	eachMark.addActionListener(this);

	
	
	
	b1=new JButton("SET");
    b1.addActionListener(this);
    b1.setBounds(300,270,180,50);
    b1.setFont(new Font("Tahoma", Font.PLAIN, 18));
    b1.setBackground(Color.darkGray);
    b1.setForeground(Color.white);
    
    b1.setBorder(null);
   add(b1);
	
	}
	
	public void focusGained(FocusEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==HH && HH.getForeground()==Color.LIGHT_GRAY)
		{
			HH.setText("");
			HH.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
			HH.setForeground(Color.BLACK);
		}
		if(ae.getSource()==MM && MM.getForeground()==Color.LIGHT_GRAY)
		{
			MM.setText("");
			MM.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
			MM.setForeground(Color.BLACK);
		}
		if(ae.getSource()==SS && SS.getForeground()==Color.LIGHT_GRAY)
		{
			SS.setText("");
			SS.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
			SS.setForeground(Color.BLACK);
		}
//		
	}

	public void focusLost(FocusEvent ae) {
		// TODO Auto-generated method stub
		if(HH.getText().equals(""))
		{
			HH.setForeground(Color.LIGHT_GRAY);
			HH.setFont(new Font("Mangal", Font.PLAIN, 18));
			HH.setText("H");
		}
		if(MM.getText().equals(""))
		{
			MM.setForeground(Color.LIGHT_GRAY);
			MM.setFont(new Font("Mangal", Font.PLAIN, 18));
			MM.setText("M");
		}
		if(SS.getText().equals(""))
		{
			SS.setForeground(Color.LIGHT_GRAY);
			SS.setFont(new Font("Mangal", Font.PLAIN, 18));
			SS.setText("S");
		}
		time=""+HH.getText()+":"+MM.getText()+":"+SS.getText()+"";
	}
		
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==eachMark)			//marks
			{
				addquestionMark=(Integer)eachMark.getSelectedItem();
				
			
			    
			}
			if(ae.getSource()==b1)
			{
				try {
				Conn con=new Conn();
				String query1="INSERT INTO course_details (course_name,time,date_of_creation,question_mark) VALUES(?,?,current_timestamp,?)";
				PreparedStatement ps1=(PreparedStatement)con.c.prepareStatement(query1);
				ps1.setString(1, subject);
				ps1.setString(2, time);
				ps1.setInt(3, addquestionMark);
				int i=ps1.executeUpdate();
				if(i>0)
					JOptionPane.showMessageDialog(null,"Time and marks of each question are set");
				this.setVisible(false);
				
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			
		}
					
					
					
				}
			
		}

		

	
	public static void main() {
		new Course_details("","").setVisible(true);
	}
	
	

}
