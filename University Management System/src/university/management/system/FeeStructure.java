/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.management.system;

import javax.swing.*;
import java.awt.*;

public class FeeStructure extends JFrame{
    
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
    JLabel i1;
    JLabel b1,b2,b3,b4,b5,b6,b7,b8;
    JLabel bb1,bb2,bb3,bb4,bb5,bb6,bb7,bb8;

    public FeeStructure(){
        
        setSize(1700,800);
        setLocation(100,100);
        setLayout(null);
        
        i1 = new JLabel("Fee Structure");
        i1.setFont(new Font("serif",Font.ITALIC,56));
        i1.setBounds(400,10,400,70);
        add(i1);
        
        l1 = new JLabel("Course");
        l1.setBounds(80,100,100,40);
        l1.setFont(new Font("serif",Font.BOLD,26));
        add(l1);
        
        l2 = new JLabel("SOCIE");
        l2.setBounds(480,100,100,40);
        l2.setFont(new Font("serif",Font.BOLD,26));
        add(l2);
        
        l3 = new JLabel("SOL");
        l3.setBounds(880,100,100,40);
        l3.setFont(new Font("serif",Font.BOLD,26));
        add(l3);

        l4 = new JLabel("Semester 1");
        l4.setBounds(80,170,150,40);
        l4.setFont(new Font("serif",Font.BOLD,26));
        add(l4);
        
        l5 = new JLabel("Semester 2");
        l5.setBounds(80,240,150,40);
        l5.setFont(new Font("serif",Font.BOLD,26));
        add(l5);
        
        l6 = new JLabel("Semester 3");
        l6.setBounds(80,310,150,40);
        l6.setFont(new Font("serif",Font.BOLD,26));
        add(l6);
        
        l7 = new JLabel("Semester 4");
        l7.setBounds(80,380,150,40);
        l7.setFont(new Font("serif",Font.BOLD,26));
        add(l7);
        
        l8 = new JLabel("Semester 5");
        l8.setBounds(80,450,150,40);
        l8.setFont(new Font("serif",Font.BOLD,26));
        add(l8);
        
        l9 = new JLabel("Semester 6");
        l9.setBounds(80,520,150,40);
        l9.setFont(new Font("serif",Font.BOLD,26));
        add(l9);
        
        l10 = new JLabel("Semester 7");
        l10.setBounds(80,590,150,40);
        l10.setFont(new Font("serif",Font.BOLD,26));
        add(l10);
        
        l11 = new JLabel("Semester 8");
        l11.setBounds(80,660,150,40);
        l11.setFont(new Font("serif",Font.BOLD,26));
        add(l11);
        
        
        b1 = new JLabel("15,000,000 UZS");
        b1.setBounds(480,170,300,40);
        b1.setFont(new Font("serif",Font.PLAIN,26));
        add(b1);
        
        b2 = new JLabel("15,000,000 UZS");
        b2.setBounds(480,240,300,40);
        b2.setFont(new Font("serif",Font.PLAIN,26));
        add(b2);
        
        b3 = new JLabel("15,000,000 UZS");
        b3.setBounds(480,310,300,40);
        b3.setFont(new Font("serif",Font.PLAIN,26));
        add(b3);
        
        b4 = new JLabel("15,000,000 UZS");
        b4.setBounds(480,380,300,40);
        b4.setFont(new Font("serif",Font.PLAIN,26));
        add(b4);
        
        b5 = new JLabel("15,000,000 UZS");
        b5.setBounds(480,450,300,40);
        b5.setFont(new Font("serif",Font.PLAIN,26));
        add(b5);
        
        b6 = new JLabel("15,000,000 UZS");
        b6.setBounds(480,520,300,40);
        b6.setFont(new Font("serif",Font.PLAIN,26));
        add(b6);
        
        b7 = new JLabel("15,000,000 UZS");
        b7.setBounds(480,590,300,40);
        b7.setFont(new Font("serif",Font.PLAIN,26));
        add(b7);
        
        b8 = new JLabel("15,000,000 UZS");
        b8.setBounds(480,660,300,40);
        b8.setFont(new Font("serif",Font.PLAIN,26));
        add(b8);
        
        
        bb1 = new JLabel("15,750,000 UZS");
        bb1.setBounds(880,170,300,40);
        bb1.setFont(new Font("serif",Font.PLAIN,26));
        add(bb1);
        
        bb2 = new JLabel("15,750,000 UZS");
        bb2.setBounds(880,240,300,40);
        bb2.setFont(new Font("serif",Font.PLAIN,26));
        add(bb2);
        
        bb3 = new JLabel("15,750,000 UZS");
        bb3.setBounds(880,310,300,40);
        bb3.setFont(new Font("serif",Font.PLAIN,26));
        add(bb3);
        
        bb4 = new JLabel("15,750,000 UZS");
        bb4.setBounds(880,380,300,40);
        bb4.setFont(new Font("serif",Font.PLAIN,26));
        add(bb4);
        
        bb5 = new JLabel("15,750,000 UZS");
        bb5.setBounds(880,450,300,40);
        bb5.setFont(new Font("serif",Font.PLAIN,26));
        add(bb5);
        
        bb6 = new JLabel("15,750,000 UZS");
        bb6.setBounds(880,520,300,40);
        bb6.setFont(new Font("serif",Font.PLAIN,26));
        add(bb6);

        bb7 = new JLabel("15,750,000 UZS");
        bb7.setBounds(880,590,300,40);
        bb7.setFont(new Font("serif",Font.PLAIN,26));
        add(bb7);

        bb8 = new JLabel("15,750,000 UZS");
        bb8.setBounds(880,660,300,40);
        bb8.setFont(new Font("serif",Font.PLAIN,26));
        add(bb8);
        
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    
    public static void main(String[] args){
        new FeeStructure().setVisible(true);
    }
}
