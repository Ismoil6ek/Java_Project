/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.management.system;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class StudentDetails extends JFrame implements ActionListener{
 
    JLabel l1,l2,l3;
    JTable t1;
    JButton b1,b2,b3;
    JTextField t2;
    String x[] = {"First name","Surname","Student ID","Date of Birth","Phone","Email","Address","Faculty","Entrance year"};
    String y[][] = new String[20][13];
    int i=0, j=0;
    StudentDetails(){
        super("Student Details");
        setSize(1260,650);
        setLocation(50,55);
        setLayout(null);
        
        l1 = new JLabel("Enter ID number to search Student : ");
        l1.setBounds(50,360,400,30);
        l1.setFont(new Font("chive",Font.BOLD,20));
        add(l1);
        
        t2 = new JTextField();
        t2.setBounds(400,360,200,30);
        add(t2);
        
        b1 = new JButton("Search");
        b1.setBackground(new Color(57, 88, 119));
        b1.setForeground(Color.WHITE);
        b1.setBounds(620, 360, 100 ,30);
        add(b1);
            
        l2 = new JLabel("Add New Student");
        l2.setBounds(50,450,400,30);
        l2.setFont(new Font("chive",Font.BOLD,20));
        add(l2);
        
        b2 = new JButton("Add Student");
        b2.setBackground(new Color(57, 88, 119));
        b2.setForeground(Color.WHITE);
        b2.setBounds(300, 450, 150 ,30);
        add(b2);

        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        
        try{
            conn c1  = new conn();
            String s1 = "select * from students";
            ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("first_name");
                y[i][j++]=rs.getString("surname");
                y[i][j++]=rs.getString("student_id");
                y[i][j++]=rs.getString("birth_date");
                y[i][j++]=rs.getString("phone");
                y[i][j++]=rs.getString("email");
                y[i][j++]=rs.getString("address");
                y[i][j++]=rs.getString("faculty");
                y[i][j++]=rs.getString("entrance_year");
                i++;
                j=0;
            }
            t1 = new JTable(y,x);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(20,20,1200,330);
        add(sp);
        
        getContentPane().setBackground(Color.WHITE);
        
        b1.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        
        conn c1 = new conn();
    
        if(ae.getSource() == b1){
            try{
                String a = t2.getText();
                String q = "delete from student where rollno = '"+a+"'";
                c1.s.executeUpdate(q);
                this.setVisible(false);
                new StudentDetails().setVisible(true);
            }catch(Exception e){}
    
        }else if(ae.getSource() == b2){
            new AddStudent().f.setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource() == b3){
            this.setVisible(false);
        }
    }
    public static void main(String[] args){
        new StudentDetails().setVisible(true);
    }
    
}
