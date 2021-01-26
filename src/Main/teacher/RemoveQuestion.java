package Main.teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
public class RemoveQuestion extends JPanel implements ActionListener{
	private String[] addquestionID;
	
	private JLabel RcorrectOptionLabel ;
	private JComboBox<String> RqIdCombo;
	private JTextArea RqStatement;
	private JTextField Ro1;
	private JTextField Ro2;
	private JTextField Ro4;
	private JTextField Ro3;
	
	
	private String time,subject,username;
	int correctOption=1;
	private static int eachMark;
	private static int Totaladdquestions;
	
	
	
	Conn conn=new Conn();
	private void fetch()
	{
		try{
			Conn con=new Conn();
			Statement st=con.c.createStatement();
			String query ="select count(q_id) from questions where course_name='"+subject+"'";
			ResultSet rs=st.executeQuery(query);
			rs.next();
			addquestionID=new String[rs.getInt(1)];
			query="select * from course_details where course_name='"+subject+"'";
			rs=st.executeQuery(query);
			rs.next();
			Totaladdquestions=rs.getInt("total_questions");
			
			time=rs.getString("time");
			eachMark=rs.getInt("question_mark");
			System.out.println("course : "+rs.getString("course_name")+time+rs.getInt("question_mark"));
			query="select *from questions where course_name='"+subject+"'";
			rs=st.executeQuery(query);
			int i=0;
			while(rs.next())
			{
				addquestionID[i++]=rs.getString("q_id");
			}
			System.out.println("Data is set to addquestionID");
		}
		catch(Exception e)
		{
//			System.out.println("Edit Course error in fetching data : "+e);
			JOptionPane.showMessageDialog(null, "Please set Date and time first");
		}
		
	}
	
	
	private boolean databaseExecuteQuery(String query)
	{
		try{
			Statement st=conn.c.createStatement();
			st.execute(query);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Edit Course : "+e);
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
	private ResultSet databaseResultSet(String query)
	{
		try{
			Statement st=conn.c.createStatement();
			return st.executeQuery(query);
		}
		catch(Exception e)
		{
			System.out.println("Edit Course : "+e);
			return null;
		}
	}
	
	public RemoveQuestion(String username,String subject)
	{
		
	this.username=username;
	this.subject=subject;
		
		fetch();
		setLayout(null);
		
		 setBounds(300, 0,1000,780);
		 setLayout(null);
		 setBackground(Color.LIGHT_GRAY);
		 
		RcorrectOptionLabel = new JLabel("");
		RcorrectOptionLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
		RcorrectOptionLabel.setBounds(300, 450, 250, 23);
		add(RcorrectOptionLabel);
		
		JLabel label = new JLabel("QUESTION ID : ");
		label.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 18));
		label.setBounds(45, 120, 165, 23);
		add(label);
		
		JLabel label_1 = new JLabel("QUESTION STATEMENT : ");
		label_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 18));
		label_1.setBounds(45, 180, 255, 23);
		add(label_1);
		
		RqIdCombo = new JComboBox<String>();
		RqIdCombo.setModel(new DefaultComboBoxModel<String>(addquestionID));
		RqIdCombo.setBounds(300, 120, 50, 23);
		add(RqIdCombo);
		RqIdCombo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				ResultSet rs;
				try{
					rs=databaseResultSet("select *from questions where q_id='"+(String)RqIdCombo.getSelectedItem()+"'");
					rs.next();
					RqStatement.setText(rs.getString("q_statement"));
					Ro1.setText(rs.getString("option1"));
					Ro2.setText(rs.getString("option2"));
					Ro3.setText(rs.getString("option3"));
					Ro4.setText(rs.getString("option4"));
					RcorrectOptionLabel.setText(rs.getString("correct_option"));
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
					System.out.println("Error in REMOVE action listener.");
				}
				
			}
		});
		
		
		
		RqStatement = new JTextArea();
		RqStatement.setEditable(false);
		RqStatement.setBounds(300, 160, 560, 50);
		
		RqStatement.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(RqStatement);
		
		JLabel label_2 = new JLabel("Option 1");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(50, 250, 250, 23);
		add(label_2);
		
		JLabel label_3 = new JLabel("Option 2");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_3.setBounds(50, 300, 250, 23);
		add(label_3);
		
		JLabel label_4 = new JLabel("Option 3");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_4.setBounds(50, 350, 250, 23);
		add(label_4);
		
		JLabel label_5 = new JLabel("Option 4");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(50, 400, 250, 23);
		add(label_5);
		
		JLabel label_6 = new JLabel("Correct option : ");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_6.setBounds(50, 450, 250, 23);
		add(label_6);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 18));
	     btnRemove.setBackground(Color.darkGray);
	     btnRemove.setForeground(Color.white);
	     btnRemove.setBorder(null);
		btnRemove.setBounds(200, 530, 180, 50);
		add(btnRemove);
		
		btnRemove.addActionListener(this);
			
	
		add(btnRemove);
		
		Ro1 = new JTextField();
		Ro1.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		Ro1.setBounds(300, 250, 560, 20);
		add(Ro1);
		
		Ro2 = new JTextField();
		Ro2.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		Ro2.setBounds(300, 300, 560, 20);
		add(Ro2);
		
		Ro3 = new JTextField();
		Ro3.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		Ro3.setBounds(300, 350, 560, 20);
		add(Ro3);
		
		Ro4 = new JTextField();
		Ro4.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		Ro4.setBounds(300, 400, 560, 20);
		add(Ro4);

}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String query1="DELETE FROM questions where q_id='"+(String)RqIdCombo.getSelectedItem()+"'";
		String query2="UPDATE course_details SET total_questions=total_questions-1 where course_name='"+subject+"'";
		if(databaseExecuteQuery(query1) && databaseExecuteQuery(query2))
		{
			
//			MainFrame.AddPanel(new EditCourse(CourseName));
			JOptionPane.showMessageDialog(null, "Done.");
		}		
	}
}
