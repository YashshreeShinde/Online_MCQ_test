package Main.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;



import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowQuestion extends JPanel implements ActionListener{

	/**
	 * This panel is smaller than the Main BeginTest Panel
	 */

	private Conn con=new Conn();
	private String CourseName;
	private ResultSet rs;
	private int q_number=1;
	private JPanel panel = new JPanel();
	private JRadioButton o_1; 
	private JRadioButton o_2;
	private JRadioButton o_3 ;
	private JRadioButton o_4 ;
	
	//if bug occurs then make String non static .
	static String[][] qInfo;
	static int q_attempted;
	/**
	 * qInfo reference variable contains the whole information of each addquestion in the course
	 * four coloumns : 
	 * 1 : addquestion number
	 * 2 : selected option
	 * 3 : correct option
	 * 4 : 0 if addquestion is viewed and not attempted
	 *     1 if addquestion is viewed and attempted
	 *     -1 if addquestion is not viewed
	 *  
	 * **/
	
	
	private void databaseWork()
	{
		try{
			Statement s=con.c.createStatement();
			String query="select count(q_id) from questions where course_name='"+CourseName+"'";
			rs=s.executeQuery(query);
			rs.next();
			int totaladdquestions=rs.getInt(1);
			qInfo=new String[totaladdquestions][4];
			for(int i=0;i<totaladdquestions;i++)
				for(int j=0;j<4;j++)
					qInfo[i][j]="-1";
			query="select *from questions where course_name='"+CourseName+"'";
			rs=s.executeQuery(query);
		}
		catch(SQLException e)
		{
			System.out.println("Error in SQL : "+e);
		}
	}
	
	public ShowQuestion(String courseName) {
		CourseName=courseName;
		databaseWork();
		
		setLayout(null);
		setBackground(Color.lightGray);
		ResultManagement();
	}

	JLabel addquestionAttempted;
	JLabel addquestionRemaining;
	JButton Next;
	JButton Previous;
	private void ResultManagement()
	{
			setVisible(true);
			
//			addquestionAttempted=new JLabel();
//			addquestionAttempted.setBounds(10, 350, 144, 35);
//			addquestionAttempted.setVisible(true);
//			
//			addquestionRemaining=new JLabel();
//			addquestionRemaining.setBounds(10, 380, 144, 35);
//			addquestionRemaining.setVisible(true);
//			add(addquestionAttempted);
//			add(addquestionRemaining);
			
			try {
				if(rs.next())
				{
					makeaddquestionVisible(rs.getString("q_id"));
					
					Previous = new JButton("LAST");
					Previous.setFont(new Font("Tahoma", Font.PLAIN, 18));
					Previous.setBorder(null);
					Previous.setForeground(Color.black);
					Previous.setBackground(Color.white);
					Previous.setBounds(80, 450, 144, 35);
					Previous.setFocusable(false);
					add(Previous);
					Previous.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent ae) {
							try {
								if(!rs.previous())
								{
									rs.afterLast();
									q_number=qInfo.length+1;
									rs.previous();
								}
								q_number--;
								makeaddquestionVisible(rs.getString("q_id"));
								if(q_number==1)
									Previous.setText("LAST");
								else
									Previous.setText("PREVIOUS");
								if(q_number==qInfo.length)
									Next.setText("FIRST");
								else
									Next.setText("NEXT");
								
							} catch (SQLException e) {
								e.printStackTrace();
							}
							
						}
					});
					
					Next = new JButton("NEXT");
					Next.setFont(new Font("Tahoma", Font.PLAIN, 18));
					Next.setBorder(null);
					Next.setForeground(Color.black);
					Next.setBackground(Color.white);
					Next.setBounds(300, 450, 144, 35);
					Next.setFocusable(false);
					add(Next);
					Next.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent ae) {
							try {
								if(!rs.next())
								{
//									JOptionPane.showMessageDialog(null, "This is the last addquestion.");
									q_number=0;
									rs.beforeFirst();
									rs.next();
//									Next.setText("FIRST");
								}
								q_number++;
								makeaddquestionVisible(rs.getString("q_id"));
								if(q_number==1)
									Previous.setText("LAST");
								else
									Previous.setText("PREVIOUS");
								if(q_number==qInfo.length)
									Next.setText("FIRST");
								else
									Next.setText("NEXT");
				
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					});
					repaint();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No addquestions available.");
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
	}
	
	private void makeaddquestionVisible(String q_id)
	{
		//add addquestions
		
		//add four options
		
		//add next and previos button
		
		if(qInfo[q_number-1][3].equals("-1"))
		{
			qInfo[q_number-1][0]=""+q_number+"";
			try{
				qInfo[q_number-1][2]=rs.getString("correct_option");
			}
			catch(SQLException e)
			{
				System.out.println(e);
			}
			qInfo[q_number-1][3]="0";
		}
		
//		addquestionAttempted.setText("addquestion attempted : "+q_attempted);
//		addquestionRemaining.setText("addquestion remaining : "+(qInfo.length-q_attempted));
//		add(panel);
		
		add(panel);
		
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(20,10,900,400);
		panel.setLayout(null);
		
		panel.removeAll();
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65,20, 450, 120);
		panel.add(scrollPane);
		
		JLabel lblQXx = new JLabel("Q."+q_number);
		lblQXx.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblQXx.setBounds(10, 12, 49, 35);
		panel.add(lblQXx);
				
		JTextArea addquestion_statement = new JTextArea();
		
		try {
			addquestion_statement.setText(rs.getString("q_statement"));
		} catch (SQLException e) {
			System.out.println(e);
		}
		addquestion_statement.setFont(new Font("Gisha", Font.PLAIN, 18));
		addquestion_statement.setBackground(Color.white);
		addquestion_statement.setEditable(false);
		panel.add(addquestion_statement);
		
		scrollPane.setViewportView(addquestion_statement);
		
		
		o_1 = new JRadioButton();
		try {
			o_1.setText(rs.getString("option1"));
		} catch (SQLException e) {
			System.out.println(e);
		}
		o_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		o_1.setBounds(57, 200, 462, 23);
		panel.add(o_1);
		o_1.addActionListener(this);
		
		o_2 = new JRadioButton();
		try {
			o_2.setText(rs.getString("option2"));
		} catch (SQLException e) {
			System.out.println(e);
		}
		o_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		o_2.setBounds(57, 250, 462, 23);
		panel.add(o_2);
		o_2.addActionListener(this);
					
		o_3 = new JRadioButton();
		try {
			o_3.setText(rs.getString("option3"));
		} catch (SQLException e) {
			System.out.println(e);
		}
		o_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		o_3.setBounds(57, 300, 462, 23);
		panel.add(o_3);
		o_3.addActionListener(this);
		
		o_4 = new JRadioButton();
		try {
			o_4.setText(rs.getString("option4"));
		} catch (SQLException e) {
			System.out.println(e);
		}
		o_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		o_4.setBounds(57, 350, 462, 23);
		panel.add(o_4);
		o_4.addActionListener(this);
		
		switch(qInfo[q_number-1][1].charAt(0))
		{
			case '1':o_1.setSelected(true);break;
			case '2':o_2.setSelected(true);break;
			case '3':o_3.setSelected(true);break;
			case '4':o_4.setSelected(true);break;
		}
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(o_1);
		bg.add(o_2);
		bg.add(o_3);
		bg.add(o_4);
		
		
		panel.repaint();
		panel.revalidate();
		}
	public void actionPerformed(ActionEvent ae) {
		if((qInfo[q_number-1][3].equals("0")))
			q_attempted++;
//		addquestionAttempted.setText("addquestion attempted : "+q_attempted);
//		addquestionRemaining.setText("addquestion remaining : "+(qInfo.length-q_attempted));
		if(ae.getSource()==o_1)
		{
			qInfo[q_number-1][1]="1";
		}
		if(ae.getSource()==o_2)
		{
			qInfo[q_number-1][1]="2";
		}
		if(ae.getSource()==o_3)
		{
			qInfo[q_number-1][1]="3";
		}
		if(ae.getSource()==o_4)
		{
			qInfo[q_number-1][1]="4";
		}
		qInfo[q_number-1][3]="1";
	}
}
