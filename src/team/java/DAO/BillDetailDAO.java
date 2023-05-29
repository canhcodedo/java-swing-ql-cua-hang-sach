/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import team.java.DTO.*;

/**
 *
 * @author ASUS
 */
public class BillDetailDAO {
    public static Connection conn = null;
    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    
    public BillDetailDAO(){
        if(conn == null){
            try {
                conn = myconn.getConnect();
            } catch (Exception e) {
                Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        } 
    }
    
    public ArrayList<BillDetail> readListBillDetail(){
        ArrayList<BillDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM billdetail";
            
            pre = myconn.getPreparedStatement(sql);
            rs = pre.executeQuery();
            

            while(rs.next()){
                BillDetail detail = new BillDetail();
                detail.setIdBill(rs.getInt(1));
                detail.setIdBook(rs.getInt(2));
                detail.setQuantity(rs.getInt(3));
                detail.setPrice(rs.getFloat(4));
                detail.setTotalPrice(rs.getFloat(5));
                detail.setIdDiscount(rs.getInt(6));
                list.add(detail);
            }
//            myconn.close();
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách"); 
            System.err.println(e);
//            Logger.getLogger(sqlServerConnect.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    // đọc theo mã hd
    public ArrayList<BillDetail> readListBillDetail(int maHD){
        ArrayList<BillDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM billdetail WHERE BillID = ?";
            
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, maHD);
            rs = pre.executeQuery();

            while(rs.next()){
                BillDetail detail = new BillDetail();
                detail.setIdBill(rs.getInt(1));
                detail.setIdBook(rs.getInt(2));
                detail.setQuantity(rs.getInt(3));
                detail.setPrice(rs.getFloat(4));
                detail.setTotalPrice(rs.getFloat(5));
                detail.setIdDiscount(rs.getInt(6));
                list.add(detail);
            }
//            myconn.close();
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");        
//            Logger.getLogger(sqlServerConnect.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    
    public boolean add(BillDetail b){
        try {
            String sql = "INSERT INTO billdetail(BillID, BookID, Quantity, Price, TotalPrice, DiscountID)  VALUES (?,?,?,?,?,?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, b.getIdBill());
            pre.setInt(2, b.getIdBook());
            pre.setInt(3, b.getQuantity());
            pre.setFloat(4, b.getPrice());
            pre.setFloat(5, b.getTotalPrice());
            pre.setInt(6, b.getIdDiscount());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi thêm chi tiết hóa đơn");
//            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    
    
    
    
    public boolean delete(BillDetail b){
        try {
            String sql ="DELETE FROM billdetail WHERE BillID = ? AND BookID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, b.getIdBill());
            pre.setInt(2, b.getIdBook());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idBill, int idBook){
        try {
            String sql = "DELETE FROM billdetail WHERE BillID = ? AND BookID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idBill);
            pre.setInt(2, idBook);
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(BillDetail b, int idBillOld, int idBookOld){
        try {
            String sql = "UPDATE billdetail"
                + " SET BillID = ?, BookID = ?, Quantity = ?, Price = ?, TotalPrice = ?, DiscountID = ?"
                + " WHERE BillID = ? AND BookID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, b.getIdBill());
            pre.setInt(2, b.getIdBook());
            pre.setInt(3, b.getQuantity());
            pre.setFloat(4, b.getPrice());
            pre.setFloat(5, b.getTotalPrice());
            pre.setInt(6, b.getIdDiscount());
            pre.setInt(7, idBillOld);
            pre.setInt(8, idBookOld);
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
