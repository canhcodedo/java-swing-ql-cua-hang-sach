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
public class DiscountDetailDAO {
    static Connection conn = null;
//    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    
    public DiscountDetailDAO(){
        if(conn == null){
            try {
                conn = myconn.getConnect();
            } catch (Exception e) {
                Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public ArrayList<DiscountDetail> readListDiscountDetail(){
        ArrayList<DiscountDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM discountdetail";
            pre = myconn.getPreparedStatement(sql);
            rs = pre.executeQuery();

            while(rs.next()){
                DiscountDetail detail = new DiscountDetail();
                detail.setIdDiscount(rs.getInt(1));
                detail.setIdBook(rs.getInt(2));
                detail.setDiscount(rs.getInt(3));
                list.add(detail);
            }
//            myconn.close();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        
        }
        return list;
    }
    
    
    public boolean add(DiscountDetail d){
        try {
            String sql = "INSERT INTO discountdetail(DiscountID, BookID, Discount)  VALUES (?,?,?)";
                        pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, d.getIdDiscount());
            pre.setInt(2, d.getIdBook());
            pre.setInt(3, d.getDiscount());
            pre.executeUpdate();
//            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(DiscountDetail d){
        try {
            String sql = "DELETE FROM discountdetail WHERE DiscountID = ? AND BookID = ?";
                        pre = myconn.getPreparedStatement(sql);
                        
            pre.setInt(1, d.getIdDiscount());
            pre.setInt(2, d.getIdBook());                        
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idDiscount, int idBook){
        try {
            String sql = "DELETE FROM discountdetail WHERE DiscountID = ? AND BookID = ?";
                        pre = myconn.getPreparedStatement(sql);
                        
            pre.setInt(1, idDiscount);
            pre.setInt(2, idBook);                        
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(DiscountDetail d, int idDiscountOld, int idBookOld){
        try {
            String sql = "UPDATE discountdetail"
                + " SET DiscountID = ?, BookID = ?, Discount = ?"
                + " WHERE DiscountID = ? AND BookID = ?";
            
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, d.getIdDiscount());
            pre.setInt(2, d.getIdBook());
            pre.setInt(3, d.getDiscount());
            pre.setInt(4, idDiscountOld);
            pre.setInt(5, idBookOld);            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
