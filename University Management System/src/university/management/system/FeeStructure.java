/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.management.system;

import javax.swing.*;
import java.awt.*;

public class FeeStructure extends JFrame{
    
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JLabel i1;
    JLabel b1,b2,b3,b4;
    JLabel bb1,bb2,bb3,bb4,bb5,bb6,bb7,bb8;

    public FeeStructure(){
        
        setSize(1000,500);
        setLocation(180,100);
        setLayout(null);
        
        i1 = new JLabel("Tuition Fees");
        i1.setFont(new Font("chive",Font.BOLD,56));
        i1.setBounds(350,0,400,70);
        add(i1);
        
        l1 = new JLabel("Course");
        l1.setBounds(80,100,100,40);
        l1.setFont(new Font("chive",Font.BOLD,26));
        add(l1);
        
        l2 = new JLabel("SOCIE");
        l2.setBounds(270,100,100,40);
        l2.setFont(new Font("chive",Font.BOLD,26));
        add(l2);
        
        l3 = new JLabel("SOL");
        l3.setBounds(500,100,100,40);
        l3.setFont(new Font("chive",Font.BOLD,26));
        add(l3);

        l4 = new JLabel("Business");
        l4.setBounds(730,100,120,40);
        l4.setFont(new Font("chive",Font.BOLD,26));
        add(l4);

        l5 = new JLabel("Freshman");
        l5.setBounds(80,170,150,40);
        l5.setFont(new Font("chive",Font.BOLD,26));
        add(l5);
        
        l6 = new JLabel("Sophomore");
        l6.setBounds(80,240,150,40);
        l6.setFont(new Font("chive",Font.BOLD,26));
        add(l6);
        
        l7 = new JLabel("Junior");
        l7.setBounds(80,310,150,40);
        l7.setFont(new Font("chive",Font.BOLD,26));
        add(l7);
        
        l8 = new JLabel("Senior");
        l8.setBounds(80,380,150,40);
        l8.setFont(new Font("chive",Font.BOLD,26));
        add(l8);
        
        
        b1 = new JLabel("30,000,000 UZS");
        b1.setBounds(270,170,300,40);
        b1.setFont(new Font("chive",Font.PLAIN,26));
        add(b1);
        
        b2 = new JLabel("30,000,000 UZS");
        b2.setBounds(270,240,300,40);
        b2.setFont(new Font("chive",Font.PLAIN,26));
        add(b2);
        
        b3 = new JLabel("30,000,000 UZS");
        b3.setBounds(270,310,300,40);
        b3.setFont(new Font("chive",Font.PLAIN,26));
        add(b3);
        
        b4 = new JLabel("30,000,000 UZS");
        b4.setBounds(270,380,300,40);
        b4.setFont(new Font("chive",Font.PLAIN,26));
        add(b4);

        
        bb1 = new JLabel("31,500,000 UZS");
        bb1.setBounds(500,170,300,40);
        bb1.setFont(new Font("chive",Font.PLAIN,26));
        add(bb1);
        
        bb2 = new JLabel("31,500,000 UZS");
        bb2.setBounds(500,240,300,40);
        bb2.setFont(new Font("chive",Font.PLAIN,26));
        add(bb2);
        
        bb3 = new JLabel("31,500,000 UZS");
        bb3.setBounds(500,310,300,40);
        bb3.setFont(new Font("chive",Font.PLAIN,26));
        add(bb3);
        
        bb4 = new JLabel("31,500,000 UZS");
        bb4.setBounds(500,380,300,40);
        bb4.setFont(new Font("chive",Font.PLAIN,26));
        add(bb4);

        bb5 = new JLabel("33,500,000 UZS");
        bb5.setBounds(730,170,300,40);
        bb5.setFont(new Font("chive",Font.PLAIN,26));
        add(bb5);

        bb6 = new JLabel("33,500,000 UZS");
        bb6.setBounds(730,240,300,40);
        bb6.setFont(new Font("chive",Font.PLAIN,26));
        add(bb6);

        bb7 = new JLabel("33,500,000 UZS");
        bb7.setBounds(730,310,300,40);
        bb7.setFont(new Font("chive",Font.PLAIN,26));
        add(bb7);

        bb8 = new JLabel("33,500,000 UZS");
        bb8.setBounds(730,380,300,40);
        bb8.setFont(new Font("chive",Font.PLAIN,26));
        add(bb8);
        
        getContentPane().setBackground(new Color(197, 226, 247));
        setVisible(true);
    }
    
    public static void main(String[] args){
        new FeeStructure().setVisible(true);
    }
}
