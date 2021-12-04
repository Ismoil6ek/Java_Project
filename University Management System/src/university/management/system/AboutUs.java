package university.management.system;

import java.awt.*;
import javax.swing.*;

public class AboutUs extends JFrame{

	private JPanel contentPane;

        public static void main(String[] args) {
            new AboutUs().setVisible(true);			
	}
    
        public AboutUs() {
            
            super("About Us - IUT");
            setBackground(new Color(173, 216, 230));
            setBounds(500, 250, 700, 500);
		
            contentPane = new JPanel();
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel l1 = new JLabel();
            ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("university/management/system/icons/logo.jpg"));
            Image i2 = i1.getImage().getScaledInstance(300, 200,Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            l1 = new JLabel(i3);
            l1.setBounds(350, 50, 300, 200);
            contentPane.add(l1);


            JLabel l3 = new JLabel("INHA University");
            l3.setForeground(new Color(0,0,255));
            l3.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 34));
            l3.setBounds(50, 90, 300, 55);
            contentPane.add(l3);

            JLabel l4 = new JLabel("in Tashkent");
            l4.setForeground(new Color(0,0,255));
            l4.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 34));
            l4.setBounds(80, 140, 405, 40);
            contentPane.add(l4);

            JLabel l8 = new JLabel("Email : info@inha.uz");
            l8.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
            l8.setBounds(70, 280, 600, 34);
            contentPane.add(l8);

            JLabel l9 = new JLabel("Address : 9, Ziyolilar str., M.Ulugbek district");
            l9.setFont(new Font("Trebuchet MS", Font.BOLD , 20));
            l9.setBounds(70, 310, 600, 34);
            contentPane.add(l9);


            JLabel l10 = new JLabel("Phone :  +998 71 2899999");
            l10.setForeground(new Color(47, 79, 79));
            l10.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
            l10.setBounds(70, 400, 600, 34);
            contentPane.add(l10);
                
                
            contentPane.setBackground(Color.WHITE);
	}
        

}


