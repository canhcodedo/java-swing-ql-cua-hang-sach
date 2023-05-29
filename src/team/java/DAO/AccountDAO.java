package team.java.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.NumberFormat;
/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import team.java.DTO.*;
public class AccountDAO{
//    static Connection conn = null;
//    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    public AccountDAO(){
//        if(conn == null){
//            try {
//                conn = myconn.getConnect();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
    }
    
    public ArrayList<Account> readListAccount(){
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account";
            pre = myconn.getPreparedStatement(sql);
//            st = myconn.getStatement();s
            rs = myconn.executeQuery();
            
            while(rs.next()){
                Account account = new Account();
                account.setIdEmployee(rs.getInt(1));
                account.setUsername(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setRoll(rs.getInt(4));
                account.setStatus(rs.getInt(5));
                list.add(account);
            }
        } catch (SQLException e) {
                System.out.println(e.getMessage());

        }
        return list;
    }
    
    
    public boolean add(Account a){
        try {
//            String sql = String.format("INSERT INTO account(EmployeeID, Username, Password, Roll, Status) VALUES ('%,'%s','%s','%d','%d')"
//                ,a.getIdEmployee(),a.getUsername(), a.getPassword(), a.getRoll(), a.getStatus());
//            st = conn.createStatement();
//            st.executeUpdate(sql);

            String sql = "INSERT INTO account (EmployeeID, UserName, Password, Roll, Status) VALUES (?,?,?,?,?)";
            
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, a.getIdEmployee());
            pre.setString(2, a.getUsername());
            pre.setString(3, a.getPassword());
            pre.setInt(4, a.getRoll());
            pre.setInt(5, a.getStatus());
            
            pre.executeUpdate();
            
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(Account a){
        try {
            String sql = "DELETE FROM account WHERE EmployeeID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, a.getIdEmployee());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int EmployeeID){
        try {
            String sql = "DELETE FROM account WHERE EmployeeID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, EmployeeID);
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Xóa tài khoản không thành công.");
//            System.out.println(e);
            return false;
        }
    }
    
    public boolean update(Account a){
        try {
//            String sql = String.format("UPDATE account"
//                + "SET EmployeeID='%s',Username = '%s', Password = '%s', Roll = '%d', Status='%d'"
//                + "WHERE EmployeeID='%s'",
//                a.getIdEmployee(),a.getUsername(),a.getPassword(),a.getRoll(),a.getStatus(),a.getIdEmployee());
            
//
//            st = conn.createStatement();
//            st.executeUpdate(sql);
             String sql = "UPDATE account"
                + " SET EmployeeID = ?,Username = ?, Password = ?, Roll = ?, Status = ?"
                + " WHERE EmployeeID = ?";
            
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, a.getIdEmployee());
            pre.setString(2, a.getUsername());
            pre.setString(3, a.getPassword());
            pre.setInt(4, a.getRoll());
            pre.setInt(5, a.getStatus());
            pre.setInt(6, a.getIdEmployee());
            
            pre.executeUpdate();
             
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Sửa tài khoản không thành công.");
//            System.out.println(e);
            return false;
        }
    }
    
}
