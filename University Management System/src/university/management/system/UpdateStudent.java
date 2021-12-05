/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.management.system;


import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

class UpdateStudent implements ActionListener{

    JFrame f;
    JLabel id1,id2,id3,id4,id5,id6,id7,id8,id9,id10;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15;
    JButton b,b1,b2;

    UpdateStudent(){;
        f=new JFrame("Update Student details");
        f.setSize(900,650);
        f.setLocation(230,60);
        f.setBackground(Color.white);
        f.setLayout(null);

        JLabel l1 = new JLabel("Enter ID number to update the data of student");
        l1.setBounds(50,100,500,30);
        l1.setFont(new Font("chivo",Font.ITALIC,20));
        f.add(l1);
        
        t12 = new JTextField();
        t12.setBounds(500,100,200,30);
        f.add(t12);
        
        b2 = new JButton("Search");
        b2.setBackground(new Color(57, 88, 119));
        b2.setForeground(Color.WHITE);
        b2.setBounds(720,100,100,30);
        f.add(b2);
        b2.addActionListener(this);

        
        id8 = new JLabel("Update Student Details:");
        id8.setBounds(50,10,500,50);
        id8.setFont(new Font("chivo",Font.ITALIC,40));
        id8.setForeground(Color.black);
        f.add(id8);


        id1 = new JLabel("First name");
        id1.setBounds(50,180,100,30);
        id1.setFont(new Font("chivo",Font.BOLD,20));
        f.add(id1);

        t1=new JTextField();
        t1.setBounds(200,180,150,30);
        f.add(t1);

        id2 = new JLabel("Surname");
        id2.setBounds(400,180,200,30);
        id2.setFont(new Font("chivo",Font.BOLD,20));
        f.add(id2);

        t2=new JTextField();
        t2.setBounds(600,180,150,30);
        f.add(t2);

        id3= new JLabel("Student ID");
        id3.setBounds(50,240,100,30);
        id3.setFont(new Font("chivo",Font.BOLD,20));
        f.add(id3);

        t3=new JTextField();
        t3.setBounds(200,240,150,30);
        f.add(t3);

        id4= new JLabel("Date of birth");
        id4.setBounds(400,240,200,30);
        id4.setFont(new Font("chivo",Font.BOLD,20));
        f.add(id4);

        t4=new JTextField();
        t4.setBounds(600,240,150,30);
        f.add(t4);

        id5= new JLabel("Email");
        id5.setBounds(50,300,100,30);
        id5.setFont(new Font("chivo",Font.BOLD,20));
        f.add(id5);

        t5=new JTextField();
        t5.setBounds(200,300,150,30);
        f.add(t5);

        id6= new JLabel("Phone");
        id6.setBounds(400,300,100,30);
        id6.setFont(new Font("chivo",Font.BOLD,20));
        f.add(id6);

        t6=new JTextField();
        t6.setBounds(600,300,150,30);
        f.add(t6);

        id7= new JLabel("Address");
        id7.setBounds(50,360,100,30);
        id7.setFont(new Font("chivo",Font.BOLD,20));
        f.add(id7);

        t7=new JTextField();
        t7.setBounds(200,360,150,30);
        f.add(t7);

        id9= new JLabel("Faculty");
        id9.setBounds(400,360,130,30);
        id9.setFont(new Font("chivo",Font.BOLD,20));
        f.add(id9);

        t8=new JTextField();
        t8.setBounds(600,360,150,30);
        f.add(t8);

        id10= new JLabel("Entrance year");
        id10.setBounds(50,420,150,30);
        id10.setFont(new Font("chivo",Font.BOLD,20));
        f.add(id10);

        t15=new JTextField();
        t15.setBounds(200,420,150,30);
        f.add(t15);
        
        b = new JButton("Submit");
        b.setBackground(new Color(57, 88, 119));
        b.setForeground(Color.WHITE);
        b.setBounds(250,500,150,40);
        
        f.add(b);

        b1=new JButton("Cancel");   
        b1.setBackground(new Color(57, 88, 119));
        b1.setForeground(Color.WHITE);
        b1.setBounds(450,500,150,40);
        
        f.add(b1);
        
        b.addActionListener(this);
        b1.addActionListener(this);
        
        f.setVisible(true);
    }



    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b){
            try{
                conn con = new conn();
                String str = "update student set name='"+t1.getText()+"',fathers_name='"+t2.getText()+"',age='"+t3.getText()+"', dob='"+t4.getText()+"',address='"+t5.getText()+"',phone='"+t6.getText()+"',email='"+t7.getText()+"',class_x='"+t8.getText()+"',class_xii='"+t9.getText()+"',aadhar='"+t10.getText()+"',course='"+t13.getText()+"',branch='"+t14.getText()+"' where rollno='"+t12.getText()+"'";
                con.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null,"successfully updated");
                f.setVisible(false);
                new StudentDetails();
            }catch(Exception e){
                System.out.println("The error is:"+e);
            }
        }
        if(ae.getSource()==b1){
            f.setVisible(false);
            new Project().setVisible(true);
        }
        if(ae.getSource() == b2){
            try{
                conn con = new conn();
                String str = "select * from student where rollno = '"+t12.getText()+"'";
                ResultSet rs = con.s.executeQuery(str);

                if(rs.next()){
                    f.setVisible(true);
             

                    t1.setText(rs.getString(1));
                    t2.setText(rs.getString(2));
                    t3.setText(rs.getString(3));
                    t4.setText(rs.getString(4));
                    t5.setText(rs.getString(5));
                    t6.setText(rs.getString(6));
                    t7.setText(rs.getString(7));
                    t8.setText(rs.getString(8));
                    t9.setText(rs.getString(9));
                    t10.setText(rs.getString(10));
                    t11.setText(rs.getString(11));
                    t13.setText(rs.getString(12));
                    t14.setText(rs.getString(13));
                }

                
            }catch(Exception ex){}
        
            f.setVisible(true);
            f.setSize(900,650);
            f.setLocation(450,250);
        }
    }

    public static void main(String[] arg){
        new UpdateStudent().f.setVisible(true);
    }
}