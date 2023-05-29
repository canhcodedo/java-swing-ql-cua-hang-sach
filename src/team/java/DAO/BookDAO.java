/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package team.java.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import team.java.DAO.sqlServerConnect;
import team.java.DTO.*;


/**
 *
 * @author ASUS
 */
public class BookDAO {
    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    public BookDAO(){
    }
    
    public ArrayList<Book> readListBook(){
        ArrayList<Book> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM book";
            pre = myconn.getPreparedStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                Book book = new Book();       
                book.setIdBook(rs.getInt(1));
                book.setNameBook(rs.getString(2));
                book.setPrice(rs.getInt(3));
                book.setQuantity(rs.getInt(4));
                book.setIdType(rs.getInt(5));
                book.setIdAuthor(rs.getInt(6));
                book.setIdPublisher(rs.getInt(7));
                list.add(book);
            }
            myconn.close();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        return list;
    }
    
    
    public boolean add(Book b){
        try {
            String sql = "INSERT INTO book (Name, Price, Quantity, BooktypeID, AuthorID, PublisherID) VALUES (?,?,?,?,?,?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setString(1, b.getNameBook());
            pre.setFloat(2, b.getPrice());
            pre.setInt(3, b.getQuantity());
            pre.setInt(4, b.getIdType());
            pre.setInt(5, b.getIdPublisher());
            pre.setInt(6, b.getIdAuthor());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(Book b){
        try {
            String sql = "DELETE FROM book WHERE BookID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, b.getIdBook());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idBook){
        try {
            String sql = "DELETE FROM book WHERE BookID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idBook);
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(Book b){
        try {
            String sql = "UPDATE book"
                + " SET BookID = ?, Name = ?, Price = ?, Quantity = ?, BookTypeID = ?, PublisherID = ?, AuthorID = ?"
                + " WHERE BookID = ?";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, b.getIdBook());
            pre.setString(2, b.getNameBook());
            pre.setFloat(3, b.getPrice());
            pre.setInt(4, b.getQuantity());
            pre.setInt(5, b.getIdType());
            pre.setInt(6, b.getIdPublisher());
            pre.setInt(7, b.getIdAuthor());
            pre.setInt(8, b.getIdBook());
            
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
 
}
