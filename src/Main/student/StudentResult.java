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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;



public class StudentResult {

	private String USERNAME;
	private String[][] DATA;
	Conn conn=new Conn();
	ResultSet rs;
	private String Handler;
	
	private void fetchData()
	{
		ResultSet temp1;
		try{
			temp1=databaseWork("select count(course_name) from result where username='"+USERNAME+"'");
			temp1.next();
			int totalTest=temp1.getInt(1);
			DATA=new String[totalTest][10];
			temp1=databaseWork("select *from result where username='"+USERNAME+"' order by test_date desc");
			for(int i=0;i<totalTest;i++)
			{
				if(temp1.next())
				{
					DATA[i][0]=temp1.getString("course_name");
					DATA[i][1]=temp1.getString("total_addquestions");
					DATA[i][2]=temp1.getString("attempted_addquestion");
					DATA[i][3]=""+(temp1.getInt("attempted_addquestion")-temp1.getInt("wrong_addquestion"))+"";//correct addquestions
					DATA[i][4]=temp1.getString("wrong_addquestion");
					DATA[i][5]=temp1.getString("total_marks");
					DATA[i][6]=temp1.getString("obtained_marks");
					DATA[i][7]=temp1.getString("percentage");
					DATA[i][8]=temp1.getString("time_taken");
					DATA[i][9]=temp1.getString("test_date");
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	private ResultSet databaseWork(String query)
	{
		ResultSet rs1;
		try{
			Statement st=conn.c.createStatement();
			rs1=st.executeQuery(query);
		}
		catch(SQLException e)
		{
			System.out.println("UserResult->databaseWork(String query) Exception : "+e);
			rs1=null;
		}
		return rs1;
	}
	public StudentResult(String Username,String handler)
	{
		USERNAME=Username;
		Handler=handler;
		fetchData();
		if(DATA.length==0)
		{
			JOptionPane.showMessageDialog(null, "No test given by this user.");
		}
		else
		{
			Student1.AddPanel(makeGUI());
		}
	}
	private JPanel makeGUI() {
		JPanel p=new JPanel();
//		p.setBounds(10, 109, 1000, 700);
		p.setLayout(null);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(Handler.equals("user"))
					new Student1(USERNAME).setVisible(true);
//				else if(Handler.equals("admin"))
//					new UserData(USERNAME,true,"UsersData");
			}
		});
		
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.white);
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		btnNewButton.setBounds(30, 33, 89, 36);
		p.add(btnNewButton);
		
		
		
		String ColHeads[]={"Course","Total Ques.","Attempted Ques.","Correct Ques.","Wrong Ques.","Total Marks","Marks Obtained","Percentage","Time taken","Test date"};
		
		JLabel label = new JLabel(USERNAME);
		label.setForeground(Color.black);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label.setBounds(1121, 70, 180, 28);
		p.add(label);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblUsername.setBounds(1001, 70, 92, 28);
		p.add(lblUsername);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 109, 971, 440);
		p.add(panel);
		panel.setLayout(null);
				
		
		
		JLabel lblOnlineExamination = new JLabel("RESULT");
		lblOnlineExamination.setForeground(Color.DARK_GRAY);
		lblOnlineExamination.setBackground(Color.BLACK);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
		lblOnlineExamination.setBounds(501, 43, 270, 72);
		p.add(lblOnlineExamination);
		
		
		
		Result result = new Result(10,ColHeads,DATA.length,DATA);
		result.setOpaque(false);
		result.setBounds(10, 11, 951, 418);
		panel.add(result);		
		return p;
	}
}
