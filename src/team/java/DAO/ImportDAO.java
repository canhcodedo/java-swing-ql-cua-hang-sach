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
public class ImportDAO {
    public static Connection conn = null;
//    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    
    public ImportDAO(){
       if(conn == null){
            try {
                conn = myconn.getConnect();
            } catch (Exception e) {
                Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        } 
        else{
            conn = null;
            try {
                conn = myconn.getConnect();
            } catch (Exception e) {
                Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public ArrayList<Import> readListImport(){
        ArrayList<Import> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM import";
            pre = myconn.getPreparedStatement(sql);
            System.out.println(pre.getResultSet());
            rs = pre.executeQuery();
            while(rs.next()){
                Import i = new Import();
                i.setIdImport(rs.getInt(1));
                i.setTotal(rs.getFloat(2));
                i.setDate(rs.getDate(3));
                i.setIdEmployee(rs.getInt(4));
                i.setIdProvider(rs.getInt(5));
                list.add(i);
            }
//            myconn.close();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        return list;
    }
    
    public ArrayList<Import> readListImport(int idEmployee){
        ArrayList<Import> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM import WHERE EmployeeID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1,idEmployee);
            System.out.println(pre.getResultSet());
            rs = pre.executeQuery();
            while(rs.next()){
                Import i = new Import();
                i.setIdImport(rs.getInt(1));
                i.setTotal(rs.getFloat(2));
                i.setDate(rs.getDate(3));
                i.setIdEmployee(rs.getInt(4));
                i.setIdProvider(rs.getInt(5));
                list.add(i);
            }
//            myconn.close();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        return list;
    }
    
    
    public boolean add(Import i){
        try {
            String sql = "INSERT INTO import (Total, Date, EmployeeID, ProviderID)  VALUES (?,?,?,?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setFloat(1, i.getTotal());
            java.sql.Date sqlDateCreateImport = new java.sql.Date(i.getDate().getTime());
            pre.setDate(2, sqlDateCreateImport);
            pre.setInt(3, i.getIdEmployee());
            pre.setInt(4, i.getIdProvider());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(Import i){
        try {
            String sql ="DELETE FROM import WHERE ImportID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, i.getIdImport());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idImport){
        try {
            String sql = "DELETE FROM import WHERE ImportID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idImport);
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(Import i){
        try {
            String sql = "UPDATE import"
                + " SET ImportID = ?, Total = ?, Date = ?, EmployeeID = ? , ProviderID = ?"
                + " WHERE ImportID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, i.getIdImport());
            pre.setFloat(2, i.getTotal());
            java.sql.Date sqlDateCreateImport = new java.sql.Date(i.getDate().getTime());
            pre.setDate(3, sqlDateCreateImport);
            pre.setInt(4, i.getIdEmployee());
            pre.setInt(5, i.getIdProvider());
            pre.setInt(6, i.getIdImport());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
