
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gudiya
 */
public class myConnection 
{
    public static Connection getConnection()
    {
        Connection con  = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost/javacontactapp","root","");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    } 
}
