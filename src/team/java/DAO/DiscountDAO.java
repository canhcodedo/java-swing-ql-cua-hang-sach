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
public class DiscountDAO {
    static Connection conn = null;
//    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    
    public DiscountDAO(){
        if(conn == null){
            try {
                conn = myconn.getConnect();
            } catch (Exception e) {
                Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public ArrayList<Discount> readListDiscount(){
        ArrayList<Discount> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM discount";
            pre = myconn.getPreparedStatement(sql);
            rs = pre.executeQuery();

            while(rs.next()){
                Discount d = new Discount();
                d.setIdDiscount(rs.getInt(1));
                d.setNameProgram(rs.getString(2));
                d.setDateStart(rs.getDate(3));
                d.setDateEnd(rs.getDate(4));
                list.add(d);
            }
//            myconn.close();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        
        }
        return list;
    }
    
    
    public boolean add(Discount d){
        try {
            String sql = "INSERT INTO discount (NameProgram, DateStart, DateEnd) VALUES (?,?,?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setString(1, d.getNameProgram());
            java.sql.Date sqlDateStart = new java.sql.Date(d.getDateStart().getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(d.getDateEnd().getTime());
            pre.setDate(2, sqlDateStart);
            pre.setDate(3, sqlDateEnd);
            pre.executeUpdate();
//            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(Discount d){
        try {
            String sql = "DELETE FROM discount WHERE DiscountID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, d.getIdDiscount());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idDiscount){
        try {
            String sql = "DELETE FROM discount WHERE DiscountID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idDiscount);            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean update(Discount d){
        try {
            String sql = "UPDATE discount"
                + " SET DiscountID = ?, NameProgram = ?, DateStart = ? , DateEnd = ?"
                + " WHERE DiscountID = ?";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, d.getIdDiscount());
            pre.setString(2, d.getNameProgram());
            java.sql.Date sqlDateStart = new java.sql.Date(d.getDateStart().getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(d.getDateEnd().getTime());
            pre.setDate(3, sqlDateStart);
            pre.setDate(4, sqlDateEnd);
            pre.setInt(5, d.getIdDiscount());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean updateID(int idCurrent, Discount d){
        try {
            String sql = "UPDATE discount"
                + " SET DiscountID = ?, NameProgram = ?, DateStart = ?, DateEnd = ?"
                + " WHERE DiscountID = ?";
            
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1,idCurrent);
            pre.setString(2, d.getNameProgram());
            pre.setDate(3,(java.sql.Date)d.getDateStart());
            pre.setDate(4, (java.sql.Date)d.getDateEnd());
            pre.setInt(5, d.getIdDiscount());
            pre.executeUpdate();
            myconn.close();
            
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
