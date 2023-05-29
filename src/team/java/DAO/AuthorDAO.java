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
public class AuthorDAO {
//    static Connection conn = null;
    private PreparedStatement pre = null;
    private Statement st = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    public AuthorDAO(){
//        if(conn == null){
//            try {
//                conn = myconn.getConnect();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
    }
    
    public ArrayList<Author> readListAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM author";
            
            pre = myconn.getPreparedStatement(sql);
            rs = myconn.executeQuery();
           
            while(rs.next()){
                Author auth = new Author();       
                auth.setIdAuthor(rs.getInt(1));
                auth.setLastName(rs.getString(2));
                auth.setFirstName(rs.getString(3));
                list.add(auth);
            }
            myconn.close();
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi đọc ds thể loại");
             Logger.getLogger(mySQLConnect.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    
    public boolean add(Author a){
        try {
            String sql ="INSERT INTO author (LastName, FirstName) VALUES (?,?)";
            pre = myconn.getPreparedStatement(sql);
            pre.setString(1, a.getLastName());
            pre.setString(2, a.getFirstName());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Thêm tác giả không thành công.");
//            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean delete(Author a){
        try {
            String sql = "DELETE FROM author WHERE AuthorID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, a.getIdAuthor());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi xoá tài khoản");
//            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    public boolean delete(int idAuthor){
        try {
            String sql = "DELETE FROM author WHERE AuthorID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idAuthor);
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi xoá tài khoản");
//            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean update(Author a){
        try {
            String sql = "UPDATE author"
                + " SET AuthorID = ?, LastName = ?, FirstName = ?"
                + " WHERE AuthorID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, a.getIdAuthor());
            pre.setString(2, a.getLastName());
            pre.setString(3, a.getFirstName());
            pre.setInt(4, a.getIdAuthor());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi sửa tài khoản");
//            System.out.println(e.getMessage());
            return false;
        }
    }
}
