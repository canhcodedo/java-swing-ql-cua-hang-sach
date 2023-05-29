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
public class CustomerDAO {
//    static Connection conn = null;
//    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    public CustomerDAO(){
//        if(conn == null){
//            try {
//                conn = myconn.getConnect();
//            } catch (Exception e) {
//                Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
    }
    
    public ArrayList<Customer> readListCustomer(){
        ArrayList<Customer> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM customer";
            pre = myconn.getPreparedStatement(sql);
            rs = pre.executeQuery();

            while(rs.next()){
                Customer customer = new Customer();       
                customer.setIdCustomer(rs.getInt(1));
                customer.setLastName(rs.getString(2));
                customer.setFirstName(rs.getString(3));
                customer.setGender(rs.getString(4));
                customer.setBirthday(rs.getDate(5));
                customer.setPhone(rs.getString(6));
                list.add(customer);
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        return list;
    }
    
    
    public boolean add(Customer cus){
        try {
//            String sql = String.format("INSERT INTO customer(CustomerID, LastName, FirstName, Gender, Birthday, PhoneNumber) VALUES (?,?,?,?,'%tF',?)";
            String sql = "INSERT INTO customer (LastName, FirstName, Gender, Birthday, PhoneNumber) VALUES (?,?,?,?,?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setString(1, cus.getLastName());
            pre.setString(2, cus.getFirstName());
            pre.setString(3, cus.getGender());
            java.sql.Date sqlDateBirhday = new java.sql.Date(cus.getBirthday().getTime());
            pre.setDate(4, sqlDateBirhday);
            pre.setString(5, cus.getPhone());
            
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(Customer cus){
        try {
            String sql = String.format("DELETE FROM customer WHERE CustomerID = ?",cus.getIdCustomer());
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, cus.getIdCustomer());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean delete(int idCustomer){
        try {
            String sql = String.format("DELETE FROM customer WHERE CustomerID = ?",idCustomer);
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idCustomer);            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(Customer cus){
        try {
            String sql = "UPDATE customer"
                + " SET CustomerID = ?, LastName = ?, FirstName = ?, Gender = ?, Birthday = ?, PhoneNumber = ?"
                + " WHERE CustomerID = ?";
            
            
            
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, cus.getIdCustomer());
            pre.setString(2, cus.getLastName());
            pre.setString(3, cus.getFirstName());
            pre.setString(4, cus.getGender());
            java.sql.Date sqlDateBirhday = new java.sql.Date(cus.getBirthday().getTime());
            pre.setDate(5, sqlDateBirhday);
            pre.setString(6, cus.getPhone());
            pre.setInt(7, cus.getIdCustomer());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
