package Main.teacher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
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

public class UpdateQuestion extends JPanel implements ActionListener{

	//UPDATE
			private String[] addquestionID;
			private JTextArea UqStatement;
			private JTextField Uo1;
			private JTextField Uo2;
			private JTextField Uo3;
			private JTextField Uo4;
			private JComboBox<String> UqIdCombo;
			private JComboBox<String> UcorrectOptionS;
			private JButton btnUpdate;
			JPanel updatequestion;
			
			private String time;
			int correctOption=1;
			private static int eachMark;
			private static int Totaladdquestions;
			
			
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
//					System.out.println("Edit Course error in fetching data : "+e);
					JOptionPane.showMessageDialog(null, "Please Set Date and Time first");
				}
				
			}
			
			String username,subject;		
	public UpdateQuestion(String username,String subject)
	{
		
		this.username=username;
		this.subject=subject;
		
		setLayout(null);
		fetch();
		
		 setBounds(300, 0,1000,780);
		 setLayout(null);
		 setBackground(Color.LIGHT_GRAY);
		 
		 
	
	UqStatement = new JTextArea();
	UqStatement.setBounds(300, 160, 560, 50);
	UqStatement.setFont(new Font("Tahoma", Font.PLAIN, 20));
	add(UqStatement);
	
	
	
	JLabel qid = new JLabel(" QUESTION ID: ");
	qid.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 18));
	qid.setBounds(50, 120, 200, 23);
	add(qid);
	
	JLabel label_11 = new JLabel("QUESTION STATEMENT : ");
	label_11.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 18));
	label_11.setBounds(50, 180, 250, 23);
	add(label_11);
	
	JLabel label_21 = new JLabel("OPTION 1");
	label_21.setFont(new Font("Tahoma", Font.PLAIN, 18));
	label_21.setBounds(50, 250, 250, 23);
	add(label_21);
	
	Uo1 = new JTextField();
	Uo1.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	Uo1.setBounds(300, 250, 560, 20);
	add(Uo1);
	
	JLabel label_31 = new JLabel("OPTION 2");
	label_31.setFont(new Font("Tahoma", Font.PLAIN, 18));
	label_31.setBounds(50, 300, 250, 23);
	add(label_31);
	
	Uo2 = new JTextField();
	Uo2.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	Uo2.setBounds(300, 300, 560, 20);
	add(Uo2);
	
	JLabel label_41 = new JLabel("OPTION 3");
	label_41.setFont(new Font("Tahoma", Font.PLAIN, 18));
	label_41.setBounds(50, 350, 250, 23);
	add(label_41);
	
	Uo3 = new JTextField();
	Uo3.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	Uo3.setBounds(300, 350, 560, 20);
	add(Uo3);
	
	JLabel label_51 = new JLabel("OPTION 4");
	label_51.setFont(new Font("Tahoma", Font.PLAIN, 18));
	label_51.setBounds(50, 400, 250, 23);
	add(label_51);
	
	Uo4 = new JTextField();
	Uo4.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	Uo4.setBounds(300, 400, 560, 20);
	add(Uo4);
	
	JLabel label_61 = new JLabel("CORRECT OPTION : ");
	label_61.setFont(new Font("Tahoma", Font.PLAIN, 18));
	label_61.setBounds(50, 450, 250, 23);
	add(label_61);
	
	UqIdCombo = new JComboBox<String>();
	UqIdCombo.setModel(new DefaultComboBoxModel<String>(addquestionID));
	UqIdCombo.setBounds(300, 120, 150, 23);
	add(UqIdCombo);
	UqIdCombo.addActionListener(this);
	
	UcorrectOptionS = new JComboBox<String>();
	UcorrectOptionS.setModel(new DefaultComboBoxModel<String>(new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}));
	UcorrectOptionS.setFont(new Font("Tahoma", Font.PLAIN, 13));
	UcorrectOptionS.setBounds(300, 450, 250, 23);
	add(UcorrectOptionS);
	UcorrectOptionS.addActionListener(this); 
	
	 
	btnUpdate = new JButton("UPDATE");
	btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
     btnUpdate.setBackground(Color.darkGray);
     btnUpdate.setForeground(Color.white);
     btnUpdate.setBorder(null);
	btnUpdate.setBounds(200, 530, 180, 50);
	add(btnUpdate);
	btnUpdate.addActionListener(this);
	
}


	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource()==UqIdCombo)
		{
			
			
			try{
				
				Conn con=new Conn();
				Statement st=con.c.createStatement();
				ResultSet rs;
				rs=st.executeQuery("select *from questions where q_id='"+(String)UqIdCombo.getSelectedItem()+"'");
				if(rs.next())
				System.out.println("Done");
				System.out.println(rs.getString("q_statement"));
				UqStatement.setText(rs.getString("q_statement"));
				Uo1.setText(rs.getString("option1"));
				Uo2.setText(rs.getString("option2"));
				Uo3.setText(rs.getString("option3"));
				Uo4.setText(rs.getString("option4"));
				UcorrectOptionS.setSelectedIndex(((int)rs.getInt("correct_option")-1));
			
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null, "i");
				System.out.println("Error in view action listener.");
			}
			
			
		}
		if(ae.getSource()==UcorrectOptionS)
		{
			
			correctOption=UcorrectOptionS.getSelectedIndex()+1;
			
		}
		
		if(ae.getSource()==btnUpdate)
		{
			Conn conn=new Conn();
			
			String query="UPDATE questions SET q_statement=?,option1=?,option2=?,option3=?,option4=?,correct_option=?"
					+ " WHERE q_id='"+(String)UqIdCombo.getSelectedItem()+"'";
			try {
				
				PreparedStatement ps=conn.c.prepareStatement(query);
				ps.setString(1, UqStatement.getText());
				ps.setString(2, Uo1.getText());
				ps.setString(3, Uo2.getText());
				ps.setString(4, Uo3.getText());
				ps.setString(5, Uo4.getText());
				ps.setInt(6, correctOption);
				
				
				if(ps.executeUpdate()>0)
				{
					System.out.println("addquestion Updated");
//					MainFrame.AddPanel(new EditCourse(CourseName));
					JOptionPane.showMessageDialog(null, "QUESTION UPDATED");
				}
//		
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				System.out.println("Error in saving addquestion  : "+e);
				
			}
		}	
		
	}
	public static void main(String[] args)
	{
		new UpdateQuestion("","").setVisible(true);
	}
}
