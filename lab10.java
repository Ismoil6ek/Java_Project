/*
StudentID: U2010090
FName: Ilxomov Ismoilbek
Section: 005
Group: 20-12
*/

import java.sql.*;   
// Step 1 :Use 'Connection', 'Statement' and 'ResultSet' classes in java.sql package

public class lab10
{
	public static void main(String[] args) throws SQLException
	{
	// Step 2: Allocate a database 'Connection' object
    Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/BMI_DB?autoReconnect=true&useSSL=false", "root", "ibrohim12345");
    // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"
    			
	// Step 3: Allocate a 'Statement' object in the Connection
   	Statement stmt = conn.createStatement();

    // Step 4: Execute a SQL SELECT query, the query result
    //  is returned in a 'ResultSet' object. INSERT two rows
    int id = 1;
 	String name = "Jhon";
    int weight = 102;
    int height = 181;
    String strSelect = "insert into BMI values ( " + id + " , " + "'" + name + " , " + "'" + weight+ " , " + "'" + height+"')";
    System.out.println("The SQL query is: " + strSelect); // Echo For debugging
    System.out.println();
    int count= stmt.executeUpdate(strSelect);

    id = 2;
    name = "Peter";
    weight = 80;
    height = 170;
    String strSelect = "insert into BMI values ( " + id + " , " + "'" + name + " , " + "'" + weight+ " , " + "'" + height+"')";
    System.out.println("The SQL query is: " + strSelect); // Echo For debugging
    System.out.println();
    count= stmt.executeUpdate(strSelect);

    id = 3;
    name = "Ismoil";
    weight = 72;
    height = 180;
    String strSelect = "insert into BMI values ( " + id + " , " + "'" + name + " , " + "'" + weight+ " , " + "'" + height+"')";
    System.out.println("The SQL query is: " + strSelect); // Echo For debugging
    System.out.println();
    count= stmt.executeUpdate(strSelect);

    // Step 5: Execute a SQL SELECT query, the query result
    //  is returned in a 'ResultSet' object. UPDATE row
	id = 2;
	String strSelect = "Update from student where PersonID =" + id + ";";
    System.out.println("The SQL query is: " + strSelect); // Echo For debugging
    System.out.println();
    int new_weight = 90;
    ResultSet rset = stmt.executeQuery(strSelect);
    rset.setInt(id ,new_weight);
    count= stmt.executeUpdate(strSelect);

    // Step 6: Execute a SQL SELECT query, the query result
    //  is returned in a 'ResultSet' object. DELETE row
	id = 1;
	String strSelect = "Delete from student where PersonID =" + id + ";";
    System.out.println("The SQL query is: " + strSelect); // Echo For debugging
    System.out.println();
    count= stmt.executeUpdate(strSelect);

 	// Step 7: Execute a SQL SELECT query, the query result
    //  is returned in a 'ResultSet' object.    SELECT
	String strSelect = "select PersonID, PersonName, Weight, Height from BMI";
    System.out.println("The SQL query is: " + strSelect); // Echo For debugging
    System.out.println();
    ResultSet rset = stmt.executeQuery(strSelect);
    // Step 8: Process the ResultSet by scrolling the cursor forward via next().
    //  For each row, retrieve the contents of the cells with getXxx(columnName).
    System.out.println("The records selected are:");
  	int rowCount = 0;
    while(rset.next()) 
	{
	// Move the cursor to the next row, return false if no more row
	int g_id= rset.getInt("PersonID");   
    String g_name = rset.getString("PersonName");
    int g_w= rset.getInt("Weight");   
    int g_h= rset.getInt("Height");   
    System.out.println(g_id + " " + g_name + " " + g_w + " " + g_h);
	++rowCount;
	}
	System.out.println("Total number of records = " + rowCount);
    conn.close();
    // Step 9: Close the resources - Done automatically by try-with-resources
    }
}