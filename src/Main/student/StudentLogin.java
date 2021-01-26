
package Main.student;
import java.awt.Color;
import java.awt.Image;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class StudentLogin extends JFrame implements ActionListener{
        
	private JTextField textField;
	private JPasswordField passwordField;
        private JButton b1,b2,b3;
    public StudentLogin()
    {
    setLayout(null);
    getContentPane().setBackground(Color.white);
    setBounds(200, 100,800,550);
        
//    
    JPanel p1=new JPanel();
    p1.setBounds(0, 0,300,550);
    p1.setLayout(null);
    p1.setBackground(new Color(42, 145, 176));
    add(p1);
    
    ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("photos/login.png"));
    Image i2=i1.getImage().getScaledInstance(200,200,500);
    ImageIcon i3=new ImageIcon(i2);
    JLabel l1=new JLabel(i3);
    l1.setForeground(Color.red);
    l1.setBounds(50,50,200, 300);
    p1.add(l1);
    
       JLabel login = new JLabel("LOGIN ");
    login.setForeground(Color.darkGray);
	login.setBounds(90, 320, 200, 50);
        login.setFont(new Font("Tahoma", Font.PLAIN, 40));
	p1.add(login);
    
    
    
   
    JLabel username = new JLabel("USERNAME : ");
    username.setForeground(Color.darkGray);
	username.setBounds(400, 89, 200, 50);
        username.setFont(new Font("Tahoma", Font.PLAIN, 18));
	add(username);

	JLabel l2 = new JLabel("PASSWORD : ");
	l2.setBounds(400, 200, 200, 50);
        l2.setForeground(Color.darkGray);
        l2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	add(l2);

	textField = new JTextField();
//        textField.setBorder(null);
	textField.setBounds(400,140, 300, 40);
        
	add(textField);
	
	passwordField = new JPasswordField();
	passwordField.setBounds(400, 245, 300, 40);
//        passwordField.setBorder(null);
	add(passwordField);
        
        b1=new JButton("LOGIN");
        b1.addActionListener(this);
        b1.setBounds(400,320,180,50);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        b1.setBorder(null);
        add(b1);
        
	b1.setForeground(Color.white);
	b1.setBackground(Color.DARK_GRAY);
	b1.setBounds(380,350,150,40);
	add(b1);
        
        b2 = new JButton("SIGN UP");
	b2.addActionListener(this);

	
	b2.setForeground(Color.white);
	b2.setBackground(Color.DARK_GRAY);
        b2.setBorder(null);
	b2.setBounds(550, 350, 150, 40);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	add(b2);
        
        
//	b3 = new JButton("FORGET PASSWORD");
//	b3.addActionListener(this);
//
//	
//        b3.setForeground(Color.white);
//        b3.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	b3.setBackground(Color.DARK_GRAY);
//	b3.setBounds(420, 410, 250, 40);
//        b3.setBorder(null);
//	add(b3);

	
        
        
       



        
        
    
    
    }
    

	public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==b1)
            {
            	try {
            		if(textField.getText()!="" && passwordField.getText()!="")
            		{
                    Conn con = new Conn();
                    String sql = "select * from student_details where username=? and password=?";
                    PreparedStatement st = con.c.prepareStatement(sql);

                    st.setString(1, textField.getText());
                    st.setString(2, passwordField.getText());

                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        this.setVisible(false);
                        new StudentLoading(textField.getText()).setVisible(true);
                    }
                    }
                    
                    	
            		
            		
                 else
                    {
			JOptionPane.showMessageDialog(null, "Invalid Login or Password!");
                    }
                       
		} catch (Exception e2) {
                    e2.printStackTrace();
		}
//            			this.setVisible(false);
//                        new Loading(textField.getText()).setVisible(true);
//            			
           
                
            } 
            if(e.getSource()==b2)
            {
                setVisible(false);
                new StudentSignUp().setVisible(true);
            }
//            if(e.getSource()==b3)
//            {
//                
//                setVisible(false);
//                new Main.ForgotPassword().setVisible(true);
//            }
//            
        }

     
    public static void main(String[] args){
    new StudentLogin();
    }
}


