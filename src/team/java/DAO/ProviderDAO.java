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
public class ProviderDAO {
//    static Connection conn = null;
//    Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    public ProviderDAO(){
//        if(conn == null){
//            try {
//                conn = myconn.getConnect();
//            } catch (Exception e) {
//                Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
    }
    
    public ArrayList<Provider> readListProvider(){
        ArrayList<Provider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM provider";
            pre = myconn.getPreparedStatement(sql);
            rs = pre.executeQuery();

            while(rs.next()){
                Provider p = new Provider();       
                p.setIdProvider(rs.getInt(1));
                p.setNameProvider(rs.getString(2));
                p.setAddress(rs.getString(3));
                p.setPhone(rs.getString(4));
                list.add(p);
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        return list;
    }
    
    
    public boolean add(Provider p){
        try {
            String sql = "INSERT INTO provider (Name, Address, PhoneNumber) VALUES (?,?,?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setString(1, p.getNameProvider());
            pre.setString(2, p.getAddress());
            pre.setString(3, p.getPhone());
            
            pre.executeUpdate();
            
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(Provider p){
        try {
            String sql  = "DELETE FROM provider WHERE ProviderID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, p.getIdProvider());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idProvider){
        try {
            String sql = "DELETE FROM provider WHERE ProviderID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idProvider);
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(Provider p){
        try {
            String sql = "UPDATE provider"
                + " SET ProviderID = ?, Name = ?, Address = ?, PhoneNumber = ?"
                + " WHERE ProviderID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, p.getIdProvider());
            pre.setString(2, p.getNameProvider());
            pre.setString(3, p.getAddress());
            pre.setString(4, p.getPhone());            
            pre.setInt(5, p.getIdProvider());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
