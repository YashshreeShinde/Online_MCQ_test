/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.awt.event.*;

public class ForgotPassword extends JFrame{

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3,textField_4;
    private JButton b1, b2,b3;

    public static void main(String[] args) {
	new ForgotPassword().setVisible(true);
    }

    public ForgotPassword() {

        setBounds(200, 100,800,550);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
        contentPane.setBackground(new Color(42, 145, 176));
	contentPane.setLayout(null);

	JLabel lblUsername = new JLabel("USERNAME ");
	lblUsername.setForeground(Color.darkGray);
	lblUsername.setFont(new Font("Verdana", Font.PLAIN, 18));
	lblUsername.setBounds(99, 100, 150, 35);
        
	contentPane.add(lblUsername);

	JLabel lblName = new JLabel("NAME  ");
	lblName.setForeground(Color.DARK_GRAY);
	lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblName.setBounds(99, 150, 150, 35);
       
	contentPane.add(lblName);

	JLabel lblPassword = new JLabel("SECURITY addquestion  ");
	lblPassword.setForeground(Color.DARK_GRAY);
	lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblPassword.setBounds(99, 200, 200, 35);
  
	contentPane.add(lblPassword);

	JLabel lblAnswer = new JLabel("ANSWER  ");
	lblAnswer.setForeground(Color.DARK_GRAY);
	lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblAnswer.setBounds(99, 250, 200, 35);

	contentPane.add(lblAnswer);

	

	JLabel lblSecurityaddquestion = new JLabel("PASSWORD ");
	lblSecurityaddquestion.setForeground(Color.DARK_GRAY);
	lblSecurityaddquestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblSecurityaddquestion.setBounds(99, 300, 200, 35);
        
	contentPane.add(lblSecurityaddquestion);
        
        

        textField = new JTextField();
           
	textField.setBounds(305, 90, 250, 40);
	contentPane.add(textField);
	textField.setColumns(10);

	textField_1 = new JTextField();
	textField_1.setColumns(10);
           
	textField_1.setBounds(305, 150,250, 40);
	contentPane.add(textField_1);

        textField_2 = new JTextField();
	textField_2.setColumns(10);
           
	textField_2.setBounds(305, 200, 250, 40);
	contentPane.add(textField_2);

	        
        textField_4 = new JTextField();
	textField_4.setColumns(10);
           
	textField_4.setBounds(305, 250, 250, 40);
	contentPane.add(textField_4);
        
        textField_3 = new JTextField();
	textField_3.setColumns(10);
           
	textField_3.setBounds(305, 300, 250, 40);
	contentPane.add(textField_3);


//        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/forgotpassword.jpg"));
//        Image i1 = c1.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
//        ImageIcon i2 = new ImageIcon(i1);
//        
//        JLabel l6 = new JLabel(i2);
//        l6.setBounds(560, 70, 200, 200);
//        add(l6);

	b1 = new JButton("Search");
//	b1.addActionListener(this);
	b1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	b1.setBounds(580, 90, 100, 35);
	b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        contentPane.add(b1);
        
        

	b2 = new JButton("Retrieve");
//	b2.addActionListener(this);
	b2.setFont(new Font("Tahoma", Font.PLAIN, 12));
	b2.setBounds(580, 250, 100, 35);
	b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
	contentPane.add(b2);

	b3 = new JButton("Back");
//	b3.addActionListener(this);
	b3.setFont(new Font("Tahoma", Font.PLAIN, 13));
	b3.setBounds(305, 370,150, 35);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
	contentPane.add(b3);

	JPanel panel = new JPanel();
	panel.setBounds(31, 25, 720, 450);
        panel.setBackground(Color.WHITE);
	contentPane.add(panel);
    }
    
    
}
