package Main.student;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;



import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;

import java.sql.*;



import javax.swing.JTextArea;




public class Student1 extends JFrame implements ActionListener{
	
	/**
	 * Create the panel.
	 */
	Conn conn=new Conn();
	private String[] Courses;
	private String Name;
	private String Username;
	private JComboBox<String> comboBox;
	private String Selected_course="";
	private int totaladdquestions;
	private String eachMark;
	private String time;
	JPanel panel;
	static JPanel MainPanel;
	JButton btnLogout;
	JButton btnstart,btnEditDetails;
	
	//initialises the Selected_course and fill values in courses array this function will run one time only
	private void dataBaseWork()
	{
		String course_query="select *from course_details ";
		try{
			Statement stmt=conn.c.createStatement();
			ResultSet rs=stmt.executeQuery("select count(course_name) from course_details ");
			rs.next();
			int i=rs.getInt(1);
			rs=stmt.executeQuery(course_query);
			Courses=new String[i];
			i=0;
			while(rs.next())
				Courses[i++]=rs.getString("course_name");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	
	// this function helps the start button to add beginTest panel or not.
	private int courseDetail(String Course)
	{
		try {
			Statement stmt = conn.c.createStatement();
			ResultSet rs=stmt.executeQuery("select *from course_details where course_name='"+Course+"'");
			rs.next();
			
			Statement stmt1 = conn.c.createStatement();
			
			ResultSet r=stmt1.executeQuery("select count(q_id) from questions where course_name='"+Course+"'");
			r.next();
			int i=(int)r.getInt(1);
			time=rs.getString("time");
//			totaladdquestions=rs.getString("total_questions");
			totaladdquestions=(int)r.getInt(1);
			eachMark=rs.getString("question_mark");
			System.out.println("addquestions are available : "+i);
			return i;
		} catch (SQLException e) {
			System.out.println("UserPanel->availableaddquestions : "+e);
		}
		System.out.println("No addquestions are available ");
		return -1;
	}
	
	// this function gives the details of current user who is login
	private void dataBaseWork(String username)
	{
		String query="select * from student_details where username='"+username+"'";
		try{
			Statement stmt=conn.c.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			Name=rs.getString("name");
			System.out.println(Name);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	JTextArea instructions;
	
	JButton btnResult;
	
	
	public Student1(String username) {
		
		
		MainPanel = new JPanel();
		MainPanel.setBounds(new Rectangle(0, 0, 1950,780));
		MainPanel.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		add(MainPanel);
		MainPanel.setLayout(null);
		
		setLayout(null);
		Username=username;
		dataBaseWork();
		dataBaseWork(Username);
		
		setBackground(Color.lightGray);
		

		setBounds(0, 0,1950,780);
	
		
		
		
		 btnstart = new JButton("START TEST");
		btnstart.setBounds(500,300,180,50);
	    btnstart.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    btnstart.setBorder(null);
	    btnstart.setForeground(Color.white);
	    btnstart.setBackground(Color.DARK_GRAY);
	    btnstart.addActionListener(this);
		
	    MainPanel.add(btnstart);
		
	    
	     btnEditDetails = new JButton("EDIT DETAILS");
		btnEditDetails.addActionListener(this);
			
		btnEditDetails.setBounds(50,320,180,50);
		btnEditDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEditDetails.setBorder(null);
		btnEditDetails.setForeground(Color.white);
		btnEditDetails.setBackground(Color.DARK_GRAY);

		MainPanel.add(btnEditDetails);
		
		 btnResult = new JButton("RESULT");
		btnResult.addActionListener(this);
			
		btnResult.setBounds(50,180,180,50);
	    btnResult.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    btnResult.setBorder(null);
	    btnResult.setForeground(Color.white);
	    btnResult.setBackground(Color.DARK_GRAY);
		
	    MainPanel.add(btnResult);
		
		
		
		instructions = new JTextArea();
		instructions.setToolTipText("INSTRUCTIONS");
		instructions.setEditable(false);
		instructions.setDisabledTextColor(new Color(221, 160, 221));
		instructions.setBackground(new Color(192, 192, 192));
		instructions.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 18));
		instructions.setForeground(new Color(70, 130, 180));
		instructions.setBounds(10, 299, 971, 248);
		instructions.setVisible(false);
		MainPanel.add(instructions);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(Courses));
		comboBox.setFont(new Font("Perpetua Titling MT", Font.PLAIN, 21));
		comboBox.setBounds(600, 180, 171, 36);
		comboBox.setSelectedIndex(-1);
		MainPanel.add(comboBox);
		comboBox.addActionListener(this);
			
							
		JLabel lblSelectCourse = new JLabel("SELECT COURSE");
		lblSelectCourse.setForeground(UIManager.getColor("CheckBoxMenuItem.foreground"));
		lblSelectCourse.setFont(new Font("Rod", Font.PLAIN, 20));
		lblSelectCourse.setBounds(380, 184, 200, 28);
		MainPanel.add(lblSelectCourse);
		
		
		
		
		
		 btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(this);
			
		btnLogout.setBounds(50,250,180,50);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogout.setBorder(null);
		btnLogout.setForeground(Color.white);
		btnLogout.setBackground(Color.DARK_GRAY);
		
		MainPanel.add(btnLogout);
		
		JLabel WlecomeLabel = new JLabel("MENU ");
		WlecomeLabel.setForeground(new Color(47, 79, 79));
		WlecomeLabel.setFont(new Font("Raavi", Font.PLAIN, 30));
		WlecomeLabel.setBounds(80, 80, 445, 28);
		MainPanel.add(WlecomeLabel);
	}
				
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource()==btnstart)
			{
			if(!Selected_course.equals(""))
			{
				//Start test of selected course if no of addquestions available are greater than 1.
				if(courseDetail(Selected_course)>0)	
				{
				
					AddPanel(new BeginTest(Username,Selected_course,eachMark));
				}
				else
					JOptionPane.showMessageDialog(null, "addquestions are not available.\nPlease contact to admin.");
		
			}
			else
			{
				JOptionPane.showMessageDialog(null, "2 addquestions are not available.\nPlease contact to admin.");
						}
			
		}
			if(e.getSource()==comboBox)
			{
				Selected_course=(String)comboBox.getSelectedItem();
				courseDetail(Selected_course);
				
//				instructions.setText("////////////////////////////////////\r\n              INSTRUCTIONS   \r\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\r\n\r\n1. There are total "+totaladdquestions+" addquestions each with "+eachMark+" marks in this course.\r\n2. Maximum time is "+time+" .\r\n3. There is no negative marking for any addquestion.\r\n4. After time up you will be automatically logged out.\r\n5. You can see the time left on the upper right corner.\r\n6.Wish You All The Best.");
//				instructions.setVisible(true);
				revalidate();
			}
			if(e.getSource()==btnLogout)
			{
				
				JOptionPane.showMessageDialog(null, "Logout Successfully");
				setVisible(false);
				new StudentLogin().setVisible(true);;
			}
			else if(e.getSource()==btnEditDetails)
			{
				
			
			new EditDetails(Username).setVisible(true);
			}
			else if(e.getSource()==btnResult)
			{
			
				new StudentResult(Username,"user");		
			}
		}
	
		public static void AddPanel(JPanel p)
		{
			Student1.RemoveAllPanel();
			p.setBounds(new Rectangle(0, 0, 1950, 780));
			MainPanel.add(p);
		}
		public static void RemoveAllPanel()
		{
			MainPanel.removeAll();
			MainPanel.repaint();
			MainPanel.revalidate();
		}
		public static Rectangle Dimensions()
		{
			return MainPanel.getBounds();
		}
		
		
	
	public static void main(String args[])
	{
		new Student1("").setVisible(true);
	}

}

	
