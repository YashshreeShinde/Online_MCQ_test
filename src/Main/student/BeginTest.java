package Main.student;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;



public class BeginTest extends JPanel {

	/**
	 * Create the panel.
	 */
	private static String username;
	private static String courseName;
	ShowQuestion addquestionPanel;
	static int eachMarks;
	private int HH=0;
	private int MM=0;
	private int SS=0;
	
	public BeginTest()
	{
		
	}
	private static String[] computeResult()
	{
		String Result[]=new String[9];
		int totaladdquestions=ShowQuestion.qInfo.length;
		int timeTaken=Clock.time;
		int CQ=0;
		for(String s[]:ShowQuestion.qInfo)
		{
			if(s[1].equals(s[2]) && !(s[1].equals("-1")))
				CQ++;
		}
		String time;
		int temp=timeTaken%60;
		time=":"+temp;
		temp=(timeTaken-temp)/60;
		time=":"+(temp%60)+time;
		temp=(temp-temp%60)/60;
		time=temp+time;
		
		Result[0]=courseName;
		Result[1]=time;//timeTaken;
		Result[2]=""+totaladdquestions;  //total addquestions
		Result[3]=""+ShowQuestion.q_attempted;//attemptedaddquestion;
		Result[4]=""+CQ;
		Result[5]=""+(ShowQuestion.q_attempted-CQ);//wrong;
		Result[6]=""+(CQ*eachMarks);//marksobtained;
		Result[7]=""+(totaladdquestions*eachMarks);//total marks;
		Result[8]=""+(((float)(CQ*eachMarks)/(totaladdquestions*eachMarks))*100);//percentage;
		return Result;
	}
	
	private void executeQuery()
	{
		Conn con=new Conn();
		try{
			Statement st=con.c.createStatement();
			String temp="select time from course_details where course_name='"+courseName+"'";
			ResultSet rs=st.executeQuery(temp);
			rs.next();
			temp=rs.getString(1);

			HH=Integer.parseInt(temp.substring(0, 2).trim());
			MM=Integer.parseInt(temp.substring(3, 5).trim());
			SS=Integer.parseInt(temp.substring(6, 8).trim());
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	
	static void endTest()
	{
		//Compute result , show result
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Student1.AddPanel(new ShowResult(computeResult(),username));
		ShowQuestion.q_attempted=0;
		Clock.time=0;
	}
	
	public BeginTest(String Username,String CourseName,String eachMarks) {
		courseName=CourseName;
		username=Username;
		BeginTest.eachMarks=Integer.parseInt(eachMarks);
		System.out.print(eachMarks);
		executeQuery();
		
		makeGUI();
	}
	private Clock c;
	public void makeGUI()
	{
		setBackground(Color.LIGHT_GRAY);
		
		JLabel lblCourseName = new JLabel("COURSE NAME : "+courseName);
		lblCourseName.setForeground(Color.DARK_GRAY);
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCourseName.setBounds(50, 26, 382, 34);
		add(lblCourseName);
		//addTimer
		
		c=new Clock(HH,MM,SS);
		c.setBounds(750,50,150,90);
		add(c);
		//add addquestionPanel
		addquestionPanel= new ShowQuestion(courseName);
//		addquestionPanel.setBackground(Color.yellow);
		addquestionPanel.setBounds(50, 71, 989, 700);
		add(addquestionPanel);
		
		//add submit button 
		setLayout(null);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				endTest();
				c.t.stop();//if submit button clicks then stop the thread of clock
			}
		});
		
		
		
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSubmit.setBorder(null);
		btnSubmit.setForeground(Color.white);
		btnSubmit.setBackground(Color.black);
		
		
		
		btnSubmit.setBounds(680, 450, 144, 35);
		btnSubmit.setFocusable(false);
		addquestionPanel.add(btnSubmit);
		
		
		
	}
}
