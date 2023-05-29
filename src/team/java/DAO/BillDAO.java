package team.java.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.NumberFormat;
/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import team.java.DTO.*;
public class BillDAO{
    public static Connection conn = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    
    public BillDAO(){
         if(conn == null){
            try {
                conn = myconn.getConnect();
            } catch (Exception e) {
                Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, e);
            }
         
        }
            else{
            conn = null;
            try {
                conn = myconn.getConnect();
            } catch (Exception e) {
                Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public ArrayList<Bill> readListBill(int idEmployee){
        ArrayList<Bill> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bill WHERE EmployeeID = ?";
//            st = myconn.getStatement();
//            rs = st.executeQuery(sql);
            
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1,idEmployee);

            rs = pre.executeQuery();
            
            while(rs.next()){
                Bill bill = new Bill();
                bill.setIdBill(rs.getInt(1));
                bill.setTotal(rs.getFloat(2));
                bill.setDate(rs.getDate(3));
                bill.setIdEmployee(rs.getInt(4));
                bill.setIdCustomer(rs.getInt(5));
                list.add(bill);
//                System.out.println(bill.getTotal());
            }
//            myconn.close();
        } catch (SQLException e) {
//            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public ArrayList<Bill> readListBill(){
        ArrayList<Bill> list = new ArrayList<>();
        try {
            String sql ="SELECT * FROM bill";
            
            pre = myconn.getPreparedStatement(sql);
            rs = myconn.executeQuery();
            
            while(rs.next()){
                Bill bill = new Bill();
                bill.setIdBill(rs.getInt(1));
                bill.setTotal(rs.getFloat(2));
                bill.setDate(rs.getDate(3));
                bill.setIdEmployee(rs.getInt(4));
                bill.setIdCustomer(rs.getInt(5));
                list.add(bill);
            }
//            myconn.close();
        } catch (SQLException e) {
        
//            Logger.getLogger(sqlServerConnect.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    public boolean add(Bill b){
        try {
            String sql = "INSERT INTO bill (Total, Date, EmployeeID, CustomerID) VALUES (?,?,?,?)";
            
            pre = myconn.getPreparedStatement(sql);
            
            pre.setFloat(1, b.getTotal());
            java.sql.Date sqlDateCreateBill = new java.sql.Date(b.getDate().getTime());
            pre.setDate(2, sqlDateCreateBill);
            pre.setInt(3, b.getIdEmployee());
            pre.setInt(4, b.getIdCustomer());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi thêm hóa đơn");
//            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public boolean delete(Bill b){
        try {
            String sql = "DELETE FROM bill WHERE BillID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, b.getIdBill());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi xoá hóa đơn");
//            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    
    public boolean delete(int idBill){
        try {
           String sql = "DELETE FROM bill WHERE BillID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idBill);
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi xoá hóa đơn");
//            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public boolean update(Bill b){
        try {
//            String sql = "UPDATE bill"
//                + "SET BillID = '%, Total='%f', Date='%tF', EmployeeID = '%s' , CustomerID = '%s'"
//                + "WHERE BillID='%s'";

            String sql = "UPDATE bill"
                + " SET BillID = ?, Total = ?, Date = ?, EmployeeID = ? , CustomerID = ?"
                + " WHERE BillID = ?";
            
            
            pre = myconn.getPreparedStatement(sql);
            System.out.println(pre.toString());
            
            
            pre.setInt(1, b.getIdBill());
            pre.setFloat(2, b.getTotal());
            java.sql.Date sqlDateCreateBill = new java.sql.Date(b.getDate().getTime());
            pre.setDate(3, sqlDateCreateBill);
            pre.setInt(4, b.getIdEmployee());
            pre.setInt(5, b.getIdCustomer());
            pre.setInt(6, b.getIdBill());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    
}
