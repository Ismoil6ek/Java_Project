package university.management.system;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

class AddStudent implements ActionListener{

    JFrame f;
    JLabel id,id1,id2,id3,id4,id5,id6,id7,id8,id9,id10,id11,id12,id15,id16,id17,lab,lab1,lab2;
    JTextField t,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
    JButton b,b1,b2,b3;
    JComboBox c1,c2;
    
      
    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    long first = Math.abs(first4);
    
    

    public AddStudent(){
        f = new JFrame("Add Student");
        f.setBackground(Color.white);
        f.setLayout(null);

        id15=new JLabel();
        id15.setBounds(0,0,900,700);
        id15.setLayout(null);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("university/management/system/icons/fourth.jpg"));
        Image i3 = img.getImage().getScaledInstance(1220, 700,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        id15.setIcon(icc3);

        id8 = new JLabel("New Student Details");
        id8.setBounds(320,30,500,50);
        id8.setFont(new Font("chive",Font.ITALIC,25));
        id8.setForeground(Color.black);
        id15.add(id8);
        f.add(id15);

 
        id1 = new JLabel("Full name");
        id1.setBounds(50,150,100,30);
        id1.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id1);

        t1=new JTextField();
        t1.setBounds(200,150,150,30);
        id15.add(t1);

        id2 = new JLabel("Entrance date");
        id2.setBounds(400,150,200,30);
        id2.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id2);

        t2=new JTextField();
        t2.setBounds(600,150,150,30);
        id15.add(t2);

        id3= new JLabel("Address");
        id3.setBounds(50,250,100,30);
        id3.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id3);

        t3=new JTextField();
        t3.setBounds(200,250,150,30);
        id15.add(t3);

        id4= new JLabel("Date of birth");
        id4.setBounds(400,250,200,30);
        id4.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id4);

        t4=new JTextField();
        t4.setBounds(600,250,150,30);
        id15.add(t4);

        id5= new JLabel("Email");
        id5.setBounds(50,350,100,30);
        id5.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id5);

        t5=new JTextField();
        t5.setBounds(200,350,150,30);
        id15.add(t5);

        id6= new JLabel("Phone");
        id6.setBounds(400,350,100,30);
        id6.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id6);

        t6=new JTextField();
        t6.setBounds(600,350,150,30);
        id15.add(t6);

        id7= new JLabel("ID number");
        id7.setBounds(50,450,100,30);
        id7.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id7);

        t7=new JTextField();
        t7.setBounds(200,450,150,30);
        id15.add(t7);

        lab=new JLabel("Faculty");
        lab.setBounds(400,450,150,30);
	lab.setFont(new Font("chive",Font.BOLD,20));
        id15.add(lab);
            
        String course[] = {"SOCIE","SOL","Business"};
        c1 = new JComboBox(course);
        c1.setBackground(Color.WHITE);
        c1.setBounds(600,450,150,30);
        id15.add(c1);
        
        b = new JButton("Submit");
        b.setBackground(new Color(57, 88, 119));
        b.setForeground(Color.WHITE);
        b.setBounds(300,550,150,40);

        
        id15.add(b);

        b1=new JButton("Cancel");   
        b1.setBackground(new Color(57, 88, 119));
        b1.setForeground(Color.WHITE);
        b1.setBounds(500,550,150,40);
        
        id15.add(b1);
        
        b.addActionListener(this);
        b1.addActionListener(this);
        
        f.setVisible(true);
        f.setSize(900,700);
        f.setLocation(400,150);
    }

    public void actionPerformed(ActionEvent ae){
        
        String a = t1.getText();
        String bb = t2.getText();
        String c = t3.getText();
        String d = t4.getText();
        String e = t5.getText();
        String ff = t6.getText();
        String g = t7.getText();
        String h = t8.getText();
        String i = t9.getText();
        String j = t10.getText();
        String k = t11.getText();
        String l = (String)c1.getSelectedItem();
        String m = (String)c2.getSelectedItem();
        
        if(ae.getSource() == b){
            try{
                conn cc = new conn();
                String q = "insert into student values('"+a+"','"+bb+"','"+c+"','"+d+"','"+e+"','"+ff+"','"+g+"','"+h+"','"+i+"','"+j+"','"+k+"','"+l+"','"+m+"')";
                cc.s.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Student Details Inserted Successfully");
                f.setVisible(false);
            }catch(Exception ee){
                System.out.println("The error is:"+ee);
            }
        }else if(ae.getSource() == b1){
            f.setVisible(false);
            new Project().setVisible(true);
            
        }
    }
    public static void main(String[ ] arg){
        new AddStudent().f.setVisible(true);
    }
}