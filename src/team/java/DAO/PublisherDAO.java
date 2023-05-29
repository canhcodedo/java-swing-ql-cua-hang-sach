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
import team.java.DTO.Customer;
import team.java.DTO.*;

/**
 *
 * @author ASUS
 */
public class PublisherDAO {
//    static Connection conn = null;
//    Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    public PublisherDAO(){
//        if(conn == null){
//            try {
//                conn = myconn.getConnect();
//            } catch (Exception e) {
//                Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
    }
    
    public ArrayList<Publisher> readListPublisher(){
        ArrayList<Publisher> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM publisher";
            pre = myconn.getPreparedStatement(sql);
            rs = pre.executeQuery();

            while(rs.next()){
                Publisher publisher = new Publisher();       
                publisher.setIdPublisher(rs.getInt(1));
                publisher.setNamePublisher(rs.getString(2));
                publisher.setAddress(rs.getString(3));
                publisher.setPhone(rs.getString(4));
                list.add(publisher);
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        
        return list;
    }
    
    
    public boolean add(Publisher p){
        try {
            String sql ="INSERT INTO publisher (Name, Address, PhoneNumber) VALUES (?,?,?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setString(1, p.getNamePublisher());
            pre.setString(2, p.getAddress());
            pre.setString(3, p.getPhone());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(Publisher p){
        try {
            String sql = "DELETE FROM publisher WHERE PublisherID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, p.getIdPublisher());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idPublisher){
        try {
            String sql ="DELETE FROM publisher WHERE PublisherID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idPublisher);
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(Publisher p){
        try {
            String sql ="UPDATE publisher"
                + " SET PublisherID = ?, Name = ?, Address = ?, PhoneNumber = ?"
                + " WHERE PublisherID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, p.getIdPublisher());
            pre.setString(2, p.getNamePublisher());
            pre.setString(3, p.getAddress());
            pre.setString(4, p.getPhone()); 
            pre.setInt(5, p.getIdPublisher());

            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
}
