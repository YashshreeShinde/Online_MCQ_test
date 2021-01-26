package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

import Main.student.StudentLogin;
import Main.teacher.TeacherLoading;
import Main.teacher.TeacherLogin;

public class Asking extends JFrame implements ActionListener{
	 private JButton b1, b2;
	public static void main(String[] args) {
		new Asking().setVisible(true);
	    }

	    public Asking() {
	    	JPanel contentPane;
	    	setBounds(200, 100,800,550);
	 		contentPane = new JPanel();
	 		
	 		setContentPane(contentPane);
	 	    contentPane.setBackground(new Color(42, 145, 176));
	 		contentPane.setLayout(null);
	 		
	 		b1 = new JButton("TEACHER");
	 		b1.addActionListener(this);
	 		b1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	 		b1.setBounds(300, 180, 200, 55);
	 		b1.setBackground(Color.darkGray);
	 	        b1.setForeground(Color.WHITE);
	 	        contentPane.add(b1);
	 	        
	 	        

	 		b2 = new JButton("STUDENT");
	 		b2.addActionListener(this);
	 		b2.setFont(new Font("Tahoma", Font.PLAIN, 20));
	 		b2.setBounds(300, 250, 200, 55);
	 		b2.setBackground(Color.darkGray);
	 	    b2.setForeground(Color.WHITE);
	 		contentPane.add(b2);

	 		
	 		
	    }
	    
	    public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==b1)
            {
                setVisible(false);
                new TeacherLogin().setVisible(true);
            }else if(e.getSource()==b2)
            {
                setVisible(false);
                new StudentLogin().setVisible(true);
            }
            
        }

	   
}
