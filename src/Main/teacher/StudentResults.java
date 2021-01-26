package Main.teacher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class StudentResults{

	/**
	 * Create the panel.
	 */
	private String CourseName;
	private String[][] DATA;
	Conn con=new Conn();
	ResultSet rs;
	/**
	 * Create the panel.
	 */
	
	private void fetchData()
	{
		ResultSet temp1,temp2;
		try{
			temp1=databaseWork("select count(username) from result where course_name='"+CourseName+"'");
			temp1.next();
			int totalTest=temp1.getInt(1);
			DATA=new String[totalTest][11];
			temp1=databaseWork("select *from result where course_name='"+CourseName+"'");
			for(int i=0;i<totalTest;i++)
			{
				if(temp1.next())
				{
					DATA[i][0]=temp1.getString("username");
					DATA[i][2]=temp1.getString("total_addquestions");
					DATA[i][3]=temp1.getString("attempted_addquestion");
					DATA[i][4]=""+(temp1.getInt("attempted_addquestion")-temp1.getInt("wrong_addquestion"))+"";//correct addquestions
					DATA[i][5]=temp1.getString("wrong_addquestion");
					DATA[i][6]=temp1.getString("total_marks");
					DATA[i][7]=temp1.getString("obtained_marks");
					DATA[i][8]=temp1.getString("percentage");
					DATA[i][9]=temp1.getString("time_taken");
					DATA[i][10]=temp1.getString("test_date");
//					temp2=databaseWork("select firstname,middlename,lastname from userdetails where username='"+temp1.getString("username")+"'");
//					temp2.next();
//					DATA[i][1]=temp2.getString(1)+" "+temp2.getString(2)+" "+temp2.getString(3);
//				}
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("sdfmsdmfk"+e);
		}
	}
	private ResultSet databaseWork(String query)
	{
		ResultSet rs1;
		try{
			Statement st=con.c.createStatement();
			rs1=st.executeQuery(query);
		}
		catch(SQLException e)
		{
			System.out.println("UserResult->databaseWork(String query) Exception : "+e);
			rs1=null;
		}
		return rs1;
	}
	public StudentResults(String coursename)
	{
		CourseName=coursename;
		fetchData();
		if(DATA.length==0)
		{
			JOptionPane.showMessageDialog(null, "No result found for "+coursename+" course");
		}
		else
		{
			SettingQuestions.AddPanel(makeGUI());
		}
	}
	public JPanel makeGUI() {
		JPanel p=new JPanel();
		p.setLayout(null);

		
		
		String ColHeads[]={"Username","Name","Total Ques.","Attempted Ques.","Correct Ques.","Wrong Ques.","Total Marks","Obtained Marks","Percentage","Time taken","Test date"};
		
		JLabel lblCourseName_1 = new JLabel(CourseName);
		lblCourseName_1.setForeground(Color.black);
		lblCourseName_1.setFont(new Font("Bell MT", Font.PLAIN, 22));
		lblCourseName_1.setBounds(801, 84, 180, 23);
		p.add(lblCourseName_1);
		
		JLabel lblCourseName = new JLabel("Course Name : ");
		lblCourseName.setForeground(Color.black);
		lblCourseName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblCourseName.setBounds(704, 84, 113, 23);
		p.add(lblCourseName);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 109, 971, 570);
		p.add(panel);
		panel.setLayout(null);
		
		Result result = new Result(11,ColHeads,DATA.length,DATA);
		result.setBounds(10, 11, 951, 550);
		panel.add(result);

		
		JLabel lblOnlineExamination = new JLabel("Course Result");
		lblOnlineExamination.setForeground(Color.DARK_GRAY);
		lblOnlineExamination.setBackground(Color.GREEN);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
		lblOnlineExamination.setBounds(324, 33, 343, 71);
		p.add(lblOnlineExamination);
		
		
		
		return p;
	}
}
