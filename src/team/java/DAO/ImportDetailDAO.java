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
public class ImportDetailDAO {
    public static Connection conn = null;
//    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    
    public ImportDetailDAO(){
        if(conn == null){
            try {
                conn = myconn.getConnect();
            } catch (Exception e) {
                Logger.getLogger(ImportDetailDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public ArrayList<ImportDetail> readListImportDetail(){
        ArrayList<ImportDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM importdetail";
            pre = myconn.getPreparedStatement(sql);
            rs = pre.executeQuery();

            while(rs.next()){
                ImportDetail detail = new ImportDetail();
                detail.setIdImport(rs.getInt(1));
                detail.setIdBook(rs.getInt(2));
                detail.setQuantiy(rs.getInt(3));
                detail.setPrice(rs.getFloat(4));
                detail.setTotalPrice(rs.getFloat(5));
                list.add(detail);
            }
//            myconn.close();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        
        }
        return list;
    }
    
    // đọc theo mã pn
    public ArrayList<ImportDetail> readListImportDetail(int maPN){
        ArrayList<ImportDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM importdetail WHERE ImportID = ?";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, maPN);
            rs = pre.executeQuery();

            while(rs.next()){
                ImportDetail detail = new ImportDetail();
                detail.setIdImport(rs.getInt(1));
                detail.setIdBook(rs.getInt(2));
                detail.setQuantiy(rs.getInt(3));
                detail.setPrice(rs.getFloat(4));
                detail.setTotalPrice(rs.getFloat(5));
                list.add(detail);
            }
//            myconn.close();
        } catch (SQLException e) {
        
        }
        return list;
    }
    
    public boolean add(ImportDetail i){
        try {
            String sql = "INSERT INTO importdetail(ImportID, BookID, Quantity,Price, TotalPrice)  VALUES (?,?,?,?,?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, i.getIdImport());
            pre.setInt(2, i.getIdBook());
            pre.setInt(3, i.getQuantity());
            pre.setFloat(4, i.getPrice());
            pre.setFloat(5, i.getTotalPrice());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(ImportDetail i){
        try {
            String sql = "DELETE FROM importdetail WHERE ImportID = ? AND BookID = ?";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, i.getIdImport());
            pre.setInt(2, i.getIdBook());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idImport, int idBook){
        try {
            String sql ="DELETE FROM importdetail WHERE ImportID = ? AND BookID = ?";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, idImport);
            pre.setInt(2, idBook);
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(ImportDetail i, int idImportOld, int idBookOld){
        try {
            String sql = "UPDATE importdetail"
                + " SET ImportID = ?, BookID = ?, Quantity = ?, Price = ?,TotalPrice = ?"
                + " WHERE ImportID = ? AND BookID = ?";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, i.getIdImport());
            pre.setInt(2, i.getIdBook());
            pre.setInt(3, i.getQuantity());
            pre.setFloat(4, i.getPrice());
            pre.setFloat(5, i.getTotalPrice());   
            pre.setInt(6, idImportOld);
            pre.setInt(7, idBookOld);  
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
