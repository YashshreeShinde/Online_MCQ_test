package Main.teacher;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class ViewQuestion extends JPanel implements ActionListener{
	JButton b1;
	JComboBox<String> NcorrectOptionS;
	JTextField NqID;
	JTextArea NqStatement;
	private String[] addquestionID;
	JTextField No1;
	
	JTextField No2;
	JTextField No4;
	JTextField No3,No5;
	
	private String time;
	int correctOption=1,correct=1;
	private static int eachMark;
	private static int Totaladdquestions;
	
	
	String username,subject;
	

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
			JOptionPane.showMessageDialog(null, "Set Date and Time first");
		}
		
	}
	


	public ViewQuestion (String username,String subject)
	{
		this.username=username;
		this.subject=subject;
		
		fetch();
		setLayout(null);
		
		 setBounds(300, 0,1000,780);
		 setLayout(null);
		 setBackground(Color.LIGHT_GRAY);
		
	NqStatement = new JTextArea();
	NqStatement.setBounds(300, 160, 560, 50);
	NqStatement.setFont(new Font("Tahoma", Font.PLAIN, 20));
	add(NqStatement);
	
//	NqID = new JTextField();
//	NqID.setBounds(300, 120, 147, 20);
//	NqID.setFont(new Font("Rod", Font.PLAIN, 18));
//	add(NqID);
//	
	
	JLabel lblId = new JLabel(" QUESTION ID  ");
	lblId.setBounds(45, 120, 165, 23);
	lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
	add(lblId);
	
	JLabel lblStatement = new JLabel("QUESTION STATEMENT  ");
	lblStatement.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblStatement.setBounds(50, 180, 250, 23);
	add(lblStatement);
	
	JLabel lblOption = new JLabel("OPTION 1");
	lblOption.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblOption.setBounds(50, 250, 91, 23);
	add(lblOption);
	
	JLabel lblOption_1 = new JLabel("OPTION 2");
	lblOption_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblOption_1.setBounds(50, 300, 91, 23);
	add(lblOption_1);
	
	JLabel lblOption_2 = new JLabel("OPTION 3");
	lblOption_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblOption_2.setBounds(50, 350, 91, 23);
	add(lblOption_2);
	
	JLabel lblOption_3 = new JLabel("OPTION 4");
	lblOption_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblOption_3.setBounds(50, 400, 91, 23);
	add(lblOption_3);
	
	JLabel lblCorrectOption = new JLabel("CORRECT OPTION  ");
	lblCorrectOption.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblCorrectOption.setBounds(50, 450, 200, 23);
	add(lblCorrectOption);
	
	NcorrectOptionS = new JComboBox<String>();
	NcorrectOptionS.setFont(new Font("Tahoma", Font.PLAIN, 13));
	NcorrectOptionS.setModel(new DefaultComboBoxModel<String>(addquestionID));
	NcorrectOptionS.setBounds(300, 100, 70, 30);
	add(NcorrectOptionS);
	NcorrectOptionS.addActionListener(this);
		
	
	
	No1 = new JTextField();
	No1.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	No1.setBounds(300, 250, 560, 20);
	add(No1);
	
	No2 = new JTextField();
	No2.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	No2.setBounds(300, 300, 560, 20);
	add(No2);
	
	No3 = new JTextField();
	No3.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	No3.setBounds(300, 350, 560, 20);
	add(No3);
	
	No4 = new JTextField();
	No4.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	No4.setBounds(300, 400, 560, 20);
	add(No4);
	
	No5 = new JTextField();
	No5.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	No5.setBounds(300, 450, 560, 20);
	add(No5);
	
	
	 	b1=new JButton("BACK");
        b1.addActionListener(this);
        b1.setBounds(200,530,180,50);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        b1.setBackground(Color.darkGray);
        b1.setForeground(Color.white);
        
        b1.setBorder(null);
        add(b1);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==NcorrectOptionS) 	//correct option
		{
		
			
				ResultSet rs;
				try{
					Conn con=new Conn();
					Statement st=con.c.createStatement();
					
					rs=st.executeQuery("select *from questions where q_id='"+(String)NcorrectOptionS.getSelectedItem()+"'");
					rs.next();
					NqStatement.setText(rs.getString("q_statement"));
					No1.setText(rs.getString("option1"));
					No2.setText(rs.getString("option2"));
					No3.setText(rs.getString("option3"));
					No4.setText(rs.getString("option4"));
					No5.setText(rs.getString("correct_option"));
				}
				catch(SQLException e)
				{
					System.out.println("Error in view action listener.");
				}
				
			
		}
		if(ae.getSource()==b1)
		{
			this.setVisible(false);
		}
				 
				
	}     
        
	public static void main(String[] args)
	{
		new ViewQuestion("","").setVisible(true);
	}
}
