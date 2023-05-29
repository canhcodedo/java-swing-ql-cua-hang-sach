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
public class EmployeeDAO {
//    static Connection conn = null;
//    private Statement st = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
    private mySQLConnect myconn = new mySQLConnect();
    
    public EmployeeDAO(){
//        if(conn == null){
//            try {
//                conn = myconn.getConnect();
//            } catch (Exception e) {
//                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
    }
    
    public ArrayList<Employee> readListEmployee(){
        ArrayList<Employee> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM employee";
            pre = myconn.getPreparedStatement(sql);
            rs = pre.executeQuery();

            while(rs.next()){
                Employee employee = new Employee();       
                employee.setIdEmployee(rs.getInt(1));
                employee.setLastName(rs.getString(2));
                employee.setFirstName(rs.getString(3));
                employee.setGender(rs.getString(4));
                employee.setBirthday(rs.getDate(5));
                employee.setPhone(rs.getString(6));
                list.add(employee);
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        return list;
    }
    
    
    public boolean add(Employee b){
        try {
            String sql = "INSERT INTO employee (LastName, FirstName, Gender, Birthday, PhoneNumber)"
                    + " VALUES (?,?,?,?,?)";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setString(1, b.getLastName());
            pre.setString(2, b.getFirstName());
            pre.setString(3, b.getGender());
            java.sql.Date sqlDateBirhday = new java.sql.Date(b.getBirthday().getTime());
            pre.setDate(4, sqlDateBirhday);
            pre.setString(5, b.getPhone());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(Employee b){
        try {
            String sql = "DELETE FROM employee WHERE EmployeeID = ?";
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, b.getIdEmployee());
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean delete(int idEmployee){
        try {
            String sql = String.format("DELETE FROM employee WHERE EmployeeID = ?",idEmployee);
            pre = myconn.getPreparedStatement(sql);
            pre.setInt(1, idEmployee);
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(Employee b){
        try {
            String sql = "UPDATE employee"
                + " SET EmployeeID = ?, LastName = ?, FirstName = ?, Gender = ?, Birthday = ?, PhoneNumber = ?"
                + " WHERE EmployeeID = ?";
            pre = myconn.getPreparedStatement(sql);
            
            pre.setInt(1, b.getIdEmployee());
            pre.setString(2, b.getLastName());
            pre.setString(3, b.getFirstName());
            pre.setString(4, b.getGender());
            java.sql.Date sqlDateBirhday = new java.sql.Date(b.getBirthday().getTime());
            pre.setDate(5, sqlDateBirhday);
            pre.setString(6, b.getPhone());        
            pre.setInt(7, b.getIdEmployee());
            
            pre.executeUpdate();
            myconn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
//    public static void main(String[] args) {
//        EmployeeDAO data = new EmployeeDAO();
//        ArrayList<Employee> list = new ArrayList<>();
//        list = data.readListEmployee();
//        for(Employee b : list){
//            String chuoi = String.format("(%s, %s, %s, %s, %tF, %s)"
//                ,b.getIdEmployee(),b.getLastName(),b.getFirstName(),b.getGender(),b.getBirthday(),b.getPhone());
//            System.out.println(chuoi);
//        }
//        
//    }
}
