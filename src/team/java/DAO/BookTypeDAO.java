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
public class BookTypeDAO {
//    static Connection conn = null;
//    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    public BookTypeDAO(){
//        if(conn == null){
//            try {
//                conn = myconn.getConnect();
//            } catch (Exception e) {
//                Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
    }
    
    public ArrayList<BookType> readListBookType(){
        ArrayList<BookType> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM booktype";
            pre = myconn.getPreparedStatement(sql);
            rs = myconn.executeQuery();

            while(rs.next()){
                BookType type = new BookType();       
                type.setIdType(rs.getInt(1));
                type.setNameType(rs.getString(2));
                list.add(type);
            }
            myconn.close();
        } catch (SQLException e) {
        }
        return list;
    }
    
    
    public boolean add(BookType t){
        try {
            String sql = "INSERT INTO booktype (Name) VALUES (?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setString(1, t.getNameType());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(BookType t){
        try {
            String sql = "DELETE FROM booktype WHERE BooktypeID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, t.getIdType());
            
            System.out.println(pre.toString());
            
            pre.executeUpdate();
            myconn.close();
                        System.out.println("Check");

            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idBookType){
        try {
            String sql = "DELETE FROM booktype WHERE BooktypeID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idBookType);
            pre.executeUpdate();
            myconn.close();
//            System.out.println("Check");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(BookType t){
        try {
            String sql = "UPDATE booktype"
                + " SET BooktypeID = ?, Name = ?"
                + " WHERE BooktypeID = ?";
            System.out.println(sql);
                
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, t.getIdType());
            pre.setString(2, t.getNameType());
            pre.setInt(3, t.getIdType());
            
            pre.executeUpdate();
            
            myconn.close();            

            
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
