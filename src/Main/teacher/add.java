package Main.teacher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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

public class add extends JPanel implements ActionListener{
	JButton b1;
	JComboBox<String> NcorrectOptionS;
	JTextField NqID;
	JTextArea NqStatement;
	int correctOption=1,correct=1;
	JTextField No1;
	
	JTextField No2;
	JTextField No4;
	JTextField No3;
	
	
	String username,subject;

	public add (String username,String subject)
	{
		this.username=username;
		this.subject=subject;
		
		
		setLayout(null);
		
		 setBounds(300, 0,1000,780);
		 setLayout(null);
		 setBackground(Color.LIGHT_GRAY);
		
	NqStatement = new JTextArea();
	NqStatement.setBounds(300, 160, 560, 50);
	NqStatement.setFont(new Font("Tahoma", Font.PLAIN, 20));
	add(NqStatement);
	
	NqID = new JTextField();
	NqID.setBounds(300, 120, 147, 20);
	NqID.setFont(new Font("Rod", Font.PLAIN, 18));
	add(NqID);
	
	
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
	NcorrectOptionS.setModel(new DefaultComboBoxModel<String>(new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}));
	NcorrectOptionS.setBounds(300, 450, 560, 30);
	add(NcorrectOptionS);
	//NcorrectOptionSaddActionListener.add(this);
		
	
	
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
	
	
	 	b1=new JButton("SAVE");
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
			correctOption=NcorrectOptionS.getSelectedIndex()+1;
		}
		
		
	
		if(ae.getSource()==b1)			//insert question
		{
			
			try {
				
				 
				
//*************************INSERT QUESTION**********************************************
				Conn con = new Conn();
				ResultSet rs = con.s.executeQuery("select * from teacher_details where username='"+username+"'");
                if (rs.next()) {
                	subject=rs.getString("subject");
                	
                }
                String query="INSERT INTO questions(q_id,q_statement,option1,option2,option3,option4,correct_option,course_name)"
						+ "VALUES (?,?,?,?,?,?,?,'"+subject+"')";
                java.sql.PreparedStatement ps=con.c.prepareStatement(query);
				ps.setString(1, NqID.getText());
				ps.setString(2, NqStatement.getText());
				ps.setString(3, No1.getText());
				ps.setString(4, No2.getText());
				ps.setString(5, No3.getText());
				ps.setString(6, No4.getText());
				ps.setInt(7, correctOption);
				
				if(ps.executeUpdate()>0)
				{
					System.out.println(" Added"+NqStatement.getText());
					
					Statement st=con.c.createStatement();
					
					st.execute("UPDATE course_details SET total_questions=total_questions+1 where course_name='"+subject+"'");
					
                
				}
				JOptionPane.showMessageDialog(null,"Question added successfully");
				setVisible(true);
				NqID.setText(" ");
				NqStatement.setText(" ");
				No1.setText(" ");
				No2.setText(" ");
				No3.setText(" ");
				No4.setText(" ");
	
				
				} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
				
			}
		}
		

		
	}     
        
	public static void main(String[] args)
	{
		new add("","").setVisible(true);
	}
}
