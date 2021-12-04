package university.management.system;

import java.sql.*;  

public class conn{
    Connection c;
    Statement s;
    public conn(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            c =DriverManager.getConnection("jdbc:mysql://localhost/inhadb","root","Ismoil*2020");
            s =c.createStatement();
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }
    }  
}  
