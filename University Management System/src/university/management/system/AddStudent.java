package university.management.system;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

class AddStudent implements ActionListener{

    JFrame f;
    JLabel id1,id2,id3,id4,id5,id6,id7,id8,id9,id15,lab;
    JTextField t,first_nametext,surnametext,student_idtext,birth_datetext,phonetext,emailtext,addresstext,entrance_yeartext;
    JButton b,b1;
    JComboBox facultytext;
    
      
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

        id8 = new JLabel("New Student Details");
        id8.setBounds(320,30,500,50);
        id8.setFont(new Font("chive",Font.ITALIC,25));
        id8.setForeground(Color.black);
        id15.add(id8);
        f.add(id15);

 
        id1 = new JLabel("First name");
        id1.setBounds(50,140,100,30);
        id1.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id1);
        first_nametext=new JTextField();
        first_nametext.setBounds(200,140,150,30);
        id15.add(first_nametext);

        id2 = new JLabel("Surname");
        id2.setBounds(450,140,200,30);
        id2.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id2);
        surnametext=new JTextField();
        surnametext.setBounds(600,140,150,30);
        id15.add(surnametext);

        id3= new JLabel("Student ID");
        id3.setBounds(50,230,100,30);
        id3.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id3);
        student_idtext=new JTextField();
        student_idtext.setBounds(200,230,150,30);
        id15.add(student_idtext);

        id4= new JLabel("Date of birth");
        id4.setBounds(450,230,200,30);
        id4.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id4);
        birth_datetext=new JTextField();
        birth_datetext.setBounds(600,230,150,30);
        id15.add(birth_datetext);

        id6= new JLabel("Phone");
        id6.setBounds(450,320,100,30);
        id6.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id6);
        phonetext=new JTextField();
        phonetext.setBounds(600,320,150,30);
        id15.add(phonetext);

        id5= new JLabel("Email");
        id5.setBounds(50,320,100,30);
        id5.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id5);
        emailtext=new JTextField();
        emailtext.setBounds(200,320,150,30);
        id15.add(emailtext);


        id7= new JLabel("Address");
        id7.setBounds(50,410,100,30);
        id7.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id7);
        addresstext=new JTextField();
        addresstext.setBounds(200,410,150,30);
        id15.add(addresstext);


        lab=new JLabel("Faculty");
        lab.setBounds(450,410,150,30);
	    lab.setFont(new Font("chive",Font.BOLD,20));
        id15.add(lab);
        String course[] = {"SOCIE (IT)","SOL (logistics)","Business"};
        facultytext = new JComboBox(course);
        facultytext.setBackground(Color.WHITE);
        facultytext.setBounds(600,410,150,30);
        id15.add(facultytext);

        id9= new JLabel("Entrance year");
        id9.setBounds(50,500,140,30);
        id9.setFont(new Font("chive",Font.BOLD,20));
        id15.add(id9);
        entrance_yeartext=new JTextField();
        entrance_yeartext.setBounds(200,500,150,30);
        id15.add(entrance_yeartext);
        
        b = new JButton("Add Student");
        b.setBackground(new Color(57, 88, 119));
        b.setForeground(Color.WHITE);
        b.setBounds(300,560,150,40);

        
        id15.add(b);

        b1=new JButton("Cancel");   
        b1.setBackground(new Color(57, 88, 119));
        b1.setForeground(Color.WHITE);
        b1.setBounds(500,560,150,40);
        
        id15.add(b1);
        
        b.addActionListener(this);
        b1.addActionListener(this);
        
        f.setVisible(true);
        f.setSize(900,700);
        f.setLocation(230,30);
    }

    public void actionPerformed(ActionEvent ae){

        String first_name = first_nametext.getText();
        String surname = surnametext.getText();
        String student_id = student_idtext.getText();
        String birth_date = birth_datetext.getText();
        String phone = phonetext.getText();
        String email = emailtext.getText();
        String address = addresstext.getText();;
        String faculty = (String)facultytext.getSelectedItem();
        String entrance_year = entrance_yeartext.getText();

        if(ae.getSource() == b){
            try{
                conn cc = new conn();
                String q = "insert into students values (\"" + first_name + "\", \"" + surname + "\", \"" + student_id + "\", " +
                        "\"" + birth_date + "\", \"" + phone + "\", \"" + email + "\", \"" + address + "\", " +
                        "\"" + faculty + "\", \"" + entrance_year + "\")";
                int count = cc.s.executeUpdate(q);
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