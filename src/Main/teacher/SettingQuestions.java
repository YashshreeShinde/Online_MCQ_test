package Main.teacher;


import java.awt.*;
import java.sql.*;

import java.awt.event.*;

import javax.crypto.AEADBadTagException;
import javax.swing.*;


import java.sql.*;
public class SettingQuestions extends JFrame implements ActionListener{
		String username,subject;
		JButton b2,b3,b4,b5,b6,b7;
		static JPanel MainPanel;
				
	SettingQuestions(String username,String subject)
	{
		
		MainPanel = new JPanel();
		MainPanel.setBounds(new Rectangle(300, 0, 1000, 700));
		MainPanel.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		add(MainPanel);
		MainPanel.setLayout(null);
		
	
	this.username=username;
	this.subject=subject;
	setTitle("Online Examination System");
	
	
	setLayout(null);
    getContentPane().setBackground(Color.gray);
    setBounds(0, 0,1950,780);
    
    
    
    JLabel l1 = new JLabel("MENU");
	l1.setForeground(Color.BLACK);
    l1.setFont(new Font("Tahoma", Font.PLAIN, 30));
	l1.setBounds(90, 35, 500, 50);
	add(l1);
	
    JLabel l2 = new JLabel("COURSE: "+subject);
	l2.setForeground(Color.BLACK);
    l2.setFont(new Font("Tahoma", Font.PLAIN, 30));
	l2.setBounds(500, 30, 500, 50);
	add(l2);
	
	
	
//**************************PANELS********************************************
	JPanel menuPanel=new JPanel();
	menuPanel.setBounds(0, 0,300,780);
    menuPanel.setLayout(null);
    menuPanel.setBackground(new Color(42, 145, 176));
    add(menuPanel);
   
	
	 
	
	 
	
	 
    b4=new JButton("ADD");
    b4.addActionListener(this);
    b4.setBounds(50,200,180,50);
    b4.setFont(new Font("Tahoma", Font.PLAIN, 18));
    b4.setBorder(null);
    b4.setForeground(Color.white);
    b4.setBackground(Color.DARK_GRAY);
    menuPanel.add(b4);
    
    b2 = new JButton("VIEW");
    b2.addActionListener(this);
	b2.setForeground(Color.white);
	b2.setBackground(Color.DARK_GRAY);
    b2.setBorder(null);
    b2.setBounds(50, 260, 180, 50);
    b2.setFont(new Font("Tahoma", Font.PLAIN, 18));
    menuPanel.add(b2);
    
    
    b3 = new JButton("UPDATE");
    b3.addActionListener(this);
    b3.setForeground(Color.white);
    b3.setFont(new Font("Tahoma", Font.PLAIN, 18));
    b3.setBackground(Color.darkGray);
    b3.setBounds(50, 320, 180, 50);
    b3.setBorder(null);
    menuPanel.add(b3);
    
      b5 = new JButton("REMOVE");
      b5.addActionListener(this);
      b5.setForeground(Color.white);
      b5.setFont(new Font("Tahoma", Font.PLAIN, 18));
      b5.setBackground(Color.darkGray);
      b5.setBounds(50, 380, 180, 50);
      b5.setBorder(null);
      menuPanel.add(b5);
      
      
      b6 = new JButton("SET TIME & MARKS");
      b6.addActionListener(this);
      b6.setForeground(Color.white);
      b6.setFont(new Font("Tahoma", Font.PLAIN, 18));
      b6.setBackground(Color.darkGray);
      b6.setBounds(50, 440, 180, 50);
      b6.setBorder(null);
      menuPanel.add(b6);
      
      b7 = new JButton("RESULTS");
      b7.addActionListener(this);
      b7.setForeground(Color.white);
      b7.setFont(new Font("Tahoma", Font.PLAIN, 18));
      b7.setBackground(Color.darkGray);
      b7.setBounds(50, 500, 180, 50);
      b7.setBorder(null);
      menuPanel.add(b7);
      
//      
//      add(new add(username, subject));
//      add(new ViewQuestion(username, subject));
//      add(new UpdateQuestion(username,subject));
      
    
	}
	public static void main(String[] args)
	{
		new SettingQuestions("","").setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource()==b4)
		{
		
				
			  AddPanel(new add(username,subject));
			 
		}
		else if(e.getSource()==b2)
		{
		
			
			AddPanel(new ViewQuestion(username,subject));
			
		}
		else if(e.getSource()==b3)
		{
			
			AddPanel(new UpdateQuestion(username,subject));
			
		}
		else if(e.getSource()==b5) {
			AddPanel(new RemoveQuestion(username, subject));
		}
		else if(e.getSource()==b6){
			AddPanel(new Course_details(username,subject));
		}
		else
		{
			new StudentResults(subject);
		}
	}
	
	public static void AddPanel(JPanel p)
	{
		SettingQuestions.RemoveAllPanel();
		p.setBounds(new Rectangle(0, 0, 1000, 700));
		MainPanel.add(p);
	}
	public static void RemoveAllPanel()
	{
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
	}
	
	
	

}
