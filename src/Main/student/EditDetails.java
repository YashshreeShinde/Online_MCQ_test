
package Main.student;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.*;



public class EditDetails extends JFrame implements ActionListener{
    
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    String username;
    private JButton b1, b2;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBox;

    Conn c;
    public static void main(String[] args) {
        new EditDetails("").setVisible(true);
    }
    public EditDetails(String username) {
    	this.username=username;
        setBounds(220, 50,800,600);
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

	JLabel lblPassword = new JLabel("CLASS  ");
	lblPassword.setForeground(Color.DARK_GRAY);
	lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblPassword.setBounds(99, 200, 150, 35);
  
	contentPane.add(lblPassword);

	JLabel lblAnswer = new JLabel("SECURITY addquestion  ");
	lblAnswer.setForeground(Color.DARK_GRAY);
	lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblAnswer.setBounds(99, 250, 200, 35);

	contentPane.add(lblAnswer);

	comboBox = new JComboBox();
	comboBox.setModel(new DefaultComboBoxModel(new String[] { "Your NickName?", "Your Lucky Number?",
			"Your child SuperHero?", "Your childhood Name ?" }));
	comboBox.setBounds(305, 250, 250, 40);
	contentPane.add(comboBox);

	JLabel lblSecurityaddquestion = new JLabel("ANSWER ");
	lblSecurityaddquestion.setForeground(Color.DARK_GRAY);
	lblSecurityaddquestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblSecurityaddquestion.setBounds(99, 300, 200, 35);
        
	contentPane.add(lblSecurityaddquestion);
        
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("photos/signup.png"));
        Image i1 = c1.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        
        JLabel l6 = new JLabel(i2);
        l6.setBounds(600, 80, 100, 100);
        add(l6);
        
    	JLabel lblyear = new JLabel("YEAR  ");
    	lblyear.setForeground(Color.DARK_GRAY);
    	lblyear.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	lblyear.setBounds(99, 350, 200, 35);

    	contentPane.add(lblyear);
    	
    	JLabel lblpass = new JLabel("PASSWORD  ");
    	lblpass.setForeground(Color.DARK_GRAY);
    	lblpass.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	lblpass.setBounds(99, 400, 200, 35);

    	contentPane.add(lblpass);

    

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

	textField_3 = new JTextField();
	textField_3.setColumns(10);
           
	textField_3.setBounds(305, 300, 250, 40);
	contentPane.add(textField_3);
	
	textField_4=new JTextField();
	textField_4.setBounds(305, 350, 250, 40);
	contentPane.add(textField_4);
	
	textField_5=new JTextField();
	textField_5.setBounds(305, 400, 250, 40);
	contentPane.add(textField_5);

	b1 = new JButton("CREATE");
	b1.addActionListener(this);
	b1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	b1.setBounds(99, 480, 160, 40);
        b1.setBackground(Color.darkGray);
        b1.setForeground(Color.white);
           
	contentPane.add(b1);

	b2 = new JButton("BACK");
	b2.addActionListener(this);
	b2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	b2.setBounds(305, 480, 160, 40);
	b2.setBackground(Color.darkGray);
        b2.setForeground(Color.white);
        
	contentPane.add(b2);

	JPanel panel = new JPanel();
	panel.setForeground(new Color(34, 139, 34));
	panel.setBorder(new TitledBorder(new LineBorder(Color.white), "Create-Account",
			TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
	panel.setBounds(31, 25, 780, 550);
        panel.setBackground(Color.white);
	contentPane.add(panel);
        
    }
    public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==b1)
            {

                
                String username=textField.getText();
                String name=textField_1.getText();
                String class1=textField_2.getText();
                String security_q= (String)comboBox.getSelectedItem();
                 String answer=textField_3.getText();
                 String year=textField_4.getText();
                 String password=textField_5.getText();
                
//                
                 String query="UPDATE student_details SET username=?,name=?,class1=?,security_q=?,answer=?,year=?,password=?"+" where username='"+username+"'";
     					
     			try {
     				Conn conn=new Conn();
     				PreparedStatement ps=conn.c.prepareStatement(query);
     				ps.setString(1, username);
     				ps.setString(2, name);
     				ps.setString(3, class1);
     				ps.setString(4, security_q);
     				ps.setString(5, answer);
     				ps.setString(6, year);
     				ps.setString(7, password);
     				
     				
     				if(ps.executeUpdate()>0)
     				{
     					System.out.println("addquestion Updated");
//     					
     					JOptionPane.showMessageDialog(null, "DETAILS UPDATED");
     					 setVisible(false);
     	                   new StudentLogin().setVisible(true);
     				}
//     		
     			} catch (Exception ae) {
     				JOptionPane.showMessageDialog(null, ae);
     				System.out.println("Error in saving addquestion  : "+ae);
     		
     			}
            
            }else{
                this.setVisible(false);
                new StudentLogin();
                
            }
            
        }

    

}
