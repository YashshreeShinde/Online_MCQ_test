
package Main;
import javax.swing.*;
import java.awt.*;


import javax.swing.JFrame;


public class StartImage {
    public static void main(String[] args)
    {
        StartFrame sf=new StartFrame();
        sf.setUndecorated(true);

//       try{ Thread.sleep(1000);}catch(Exception e){}
       sf.setVisible(true);
       
       int x=1;
        for(int i=300;i<=506;i+=4,x++){
         sf.setLocation(600-(x+i)/2,350-(i/2));
         sf.setSize(x+i,i-x);
         try
         {
             Thread.sleep(10);
             
         }catch(Exception e){}
        }
////         sf.setVisible(true);

        
    }
    
}

 class StartFrame extends JFrame implements Runnable{
 
     StartFrame()  {
       Thread t;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("photos/online.png"));
        Image i2=i1.getImage().getScaledInstance(626, 256, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l1=new JLabel(i3);
        add(l1);
        setBackground(Color.orange);

         setBounds(200, 300,626,256);
         
         t=new Thread(this);
         t.start();
        
    }
     
     public void run()
     {
         try{
         Thread.sleep(7000);
         this.setVisible(false);
         new Asking().setVisible(true);
         
         }
         catch(Exception e){}
             
         
     }
    
    
}
