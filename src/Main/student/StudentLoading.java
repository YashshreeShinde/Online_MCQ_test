
package Main.student;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StudentLoading extends JFrame implements Runnable {

	private JPanel contentPane;
	private JProgressBar progressBar;
	
        String username;
	int s;
	Thread th;

	public static void main(String[] args) {
            new StudentLoading("").setVisible(true);
	}

	public void setUploading() {
            setVisible(false);
            th.start();
	}

	public void run() {
            try {
                for (int i = 0; i < 200; i++) {
                    s = s + 1;
                    int m = progressBar.getMaximum();
                    int v = progressBar.getValue();
                    if (v < m) {
                        progressBar.setValue(progressBar.getValue() + 1);
                    } else {
                        i = 201;
                        setVisible(false);
                        new Student1(username).setVisible(true);;
                        
                    }
                    Thread.sleep(50);
                }
            } catch (Exception e) {
		e.printStackTrace();
            }
	}

	public StudentLoading(String username) {
            this.username = username;
            th = new Thread((Runnable) this);

            setBounds(300, 230, 600, 200);
            contentPane = new JPanel();
            contentPane.setBackground(new Color(42, 145, 176));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel oesManagement = new JLabel("Welcome,"+username+" !");
            oesManagement.setForeground(Color.DARK_GRAY);
            oesManagement.setFont(new Font("Tahoma", Font.PLAIN, 35));
            oesManagement.setBounds(110, 46, 700, 35);
            contentPane.add(oesManagement);
	
            progressBar = new JProgressBar();
            progressBar.setFont(new Font("Tahoma", Font.PLAIN, 12));
            progressBar.setStringPainted(true);
            progressBar.setBounds(130, 135, 300, 25);
            contentPane.add(progressBar);
            progressBar.setBackground(Color.gray);
            progressBar.setForeground(Color.black);

            JLabel lblNewLabel_2 = new JLabel("Please Wait....");
            lblNewLabel_2.setFont(new Font("Yu Gothic UI SemiPLAIN", Font.PLAIN, 20));
            lblNewLabel_2.setForeground(Color.BLACK);
            lblNewLabel_2.setBounds(220, 165, 150, 20);
            contentPane.add(lblNewLabel_2);

            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setBounds(10, 10, 580, 230);
            contentPane.add(panel);
              
            setUndecorated(true);
            setUploading();
	}
}
