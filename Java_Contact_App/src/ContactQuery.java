
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ContactQuery 
{
    public void insertContact(Contact c)
    {
        //boolean contactIsCreated = true;
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = (PreparedStatement) con.prepareStatement("INSERT INTO `mycontact`(`fname`, `lname`, `groupc`, `phone`, `email`, `address`, `pic`, `userid`) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1,c.getFname());
            ps.setString(2,c.getLname());
            ps.setString(3,c.getGroupc());
            ps.setString(4,c.getPhone());
            ps.setString(5,c.getEmail());
            ps.setString(6,c.getAddress());
            ps.setBytes(7,c.getPic());
            ps.setInt(8,c.getUid());
            
            
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null,"New Contact Added");
            //    contactIsCreated = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Something Wrong");
          //      contactIsCreated = false;
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //return contactIsCreated;
    }
    
    public void UpdateContact(Contact c, boolean withImage)
    {
        //boolean contactIsCreated = true;
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        String updateQuery= "";
        
        if(withImage == true)
        {
            updateQuery = "UPDATE `mycontact` SET `fname`= ?,`lname`= ?,`groupc`= ?,`phone`= ?,`email`= ?,`address`= ?,`pic`= ? WHERE `id` = ?";
             try {
            ps = (PreparedStatement) con.prepareStatement(updateQuery);
            ps.setString(1,c.getFname());
            ps.setString(2,c.getLname());
            ps.setString(3,c.getGroupc());
            ps.setString(4,c.getPhone());
            ps.setString(5,c.getEmail());
            ps.setString(6,c.getAddress());
            ps.setBytes(7,c.getPic());
            ps.setInt(8,c.getCid());
            
            
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null,"Contact Edited");
            //    contactIsCreated = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Something Wrong");
          //      contactIsCreated = false;
            }
        
            } catch (SQLException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
        }
        else
        {
             updateQuery = "UPDATE `mycontact` SET `fname`= ?,`lname`= ?,`groupc`= ?,`phone`= ?,`email`= ?,`address`= ? WHERE `id` = ?";
            try {
            ps = (PreparedStatement) con.prepareStatement(updateQuery);
            ps.setString(1,c.getFname());
            ps.setString(2,c.getLname());
            ps.setString(3,c.getGroupc());
            ps.setString(4,c.getPhone());
            ps.setString(5,c.getEmail());
            ps.setString(6,c.getAddress());
         //   ps.setBytes(7,c.getPic());
            ps.setInt(7,c.getCid());
            
            
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null,"Contact Edited");
            //    contactIsCreated = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Something Wrong");
          //      contactIsCreated = false;
            }
        
            } catch (SQLException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        }     
    }
    
    
   public void DeleteContact(int Cid)
    {
        //boolean contactIsCreated = true;
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = (PreparedStatement) con.prepareStatement("DELETE FROM `mycontact` WHERE `id` = ?");
            ps.setInt(1,Cid);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null,"Contact Deleted!");
            //    contactIsCreated = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Something Wrong");
          //      contactIsCreated = false;
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    // create a list of contact
    public ArrayList<Contact> contactList(int userId) throws SQLException
    {
        ArrayList<Contact>clist = new ArrayList<Contact>();
        //System.out.println(userId +"Query");
        Connection con = myConnection.getConnection();
        Statement st;
        ResultSet rs;
        
        st = con.createStatement();
        rs = st.executeQuery("SELECT `id`, `fname`, `lname`, `groupc`, `phone`, `email`, `address`, `pic` FROM `mycontact` WHERE userid ="+userId);
        Contact c;
        while(rs.next())
        {
            c = new Contact(rs.getInt("id"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("groupc"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getBytes("pic"),
                    userId);
            
            clist.add(c);
            
        }
        return clist;
    }
    
    
}
