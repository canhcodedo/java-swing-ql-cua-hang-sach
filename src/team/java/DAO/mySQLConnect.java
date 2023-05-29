/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.DAO;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class mySQLConnect {
    String host = "";
    String database = "";
    String url = "jdbc:mysql://localhost:3306/bookstore";
    String user = "root";
    String password ="";
    
    
    ResultSet rs;    
    Statement st;
    PreparedStatement pre;
    Connection conn;

    public mySQLConnect() {
        if(conn == null){
            try {
                getConnect();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public mySQLConnect(String url, String user, String password){
        this.password = password;
        this.user = user;
        this.url = url;
    }
    
    public Connection getConnect() {
        if(this.conn==null){
            try{
                this.conn = DriverManager.getConnection(url, user, password);
            }
            catch(SQLException ex){
                Logger.getLogger(mySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.conn;
    }
    
    public Statement getStatement() throws SQLException{
        if(this.st==null ? true: this.st.isClosed()){
            try{
                this.st = this.conn.createStatement();     
            }
            catch(SQLException ex){
                Logger.getLogger(mySQLConnect.class.getName()).log(Level.SEVERE, null, ex);              
            }
        }
        return this.st;
    }
    
    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
        if(this.pre == null ? true : this.pre.isClosed()){
            try{
                this.pre = this.conn.prepareStatement(sql);
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return this.pre;
    }
    
    
     /// thực hiện lệnh select
    public ResultSet executeQuery(){
            try{
                this.rs = pre.executeQuery();     
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
        return  this.rs;
    }
    
    // thực hiện lệnh insert, update, delete
    public int executeUpdate(String query) throws SQLException{
            int i = Integer.MIN_VALUE;
            try{
                i = getStatement().executeUpdate(query);
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            finally{
                this.close();
            }
        return  i;
    }
    
    public void close() throws SQLException{
        if(this.rs!= null && !this.rs.isClosed()){
            this.rs.close();
            this.rs = null;
        }
        
        if(this.st!= null && !this.st.isClosed()){
            this.st.close();
            this.st = null;
        }
        
        if(this.conn!= null && !this.conn.isClosed()){
            this.conn.close();
            this.conn = null;
        }
    }
    
    /// thực hiện lệnh select
//    public ResultSet executeQuery(String query){
//            try{
//                this.rs = getStatement().executeQuery(query);     
//            }
//            catch(SQLException ex){
//                Logger.getLogger(mySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        return  this.rs;
//    }
//    
//    // thực hiện lệnh insert, update, delete
//    public int executeUpdate(String query) throws SQLException{
//            int i = Integer.MIN_VALUE;
//            try{
//                i = getStatement().executeUpdate(query);
//            }
//            catch(SQLException ex){
//                Logger.getLogger(mySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            finally{
//                this.close();
//            }
//        return  i;
//    }
//    
//    public void close() throws SQLException{
//        if(this.rs!= null && !this.rs.isClosed()){
//            this.rs.close();
//            this.rs = null;
//        }
//        
//        if(this.st!= null && !this.st.isClosed()){
//            this.st.close();
//            this.st = null;
//        }
//        
//        if(this.conn!= null && !this.conn.isClosed()){
//            this.conn.close();
//            this.conn = null;
//        }
//    }
}
