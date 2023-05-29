/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class sqlServerConnect {
    String host = "";
    String database = "";
    // Using SQL authentication
    String url = "jdbc:sqlserver://ngoccanh\\serverpt;databaseName=bookstore;;encrypt=true;trustServerCertificate=true;";
    // Using windows authentication ;integratedSecurity=true;
    String url1 = "jdbc:sqlserver://ngoccanh\\serverpt;databaseName=bookstore;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    String user = "sa";
    String password ="canh";
    
    
    ResultSet rs = null;    
    PreparedStatement pre = null;
    Connection conn = null;

    public sqlServerConnect() {
        if(conn == null){
            try {
                getConnect();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public sqlServerConnect(String url, String user, String password){
        this.password = password;
        this.user = user;
        this.url = url;
    }   
    
    public Connection getConnect(){
        try{
//                Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            this.conn = DriverManager.getConnection(url,user, password);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return this.conn;
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
                System.out.println(ex.getMessage());
            }
        return  this.rs;
    }
    
    // thực hiện lệnh insert, update, delete
    public int executeUpdate() throws SQLException{
            int i = Integer.MIN_VALUE;
            try{
                i = pre.executeUpdate();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
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
        
        if(this.pre!= null && !this.pre.isClosed()){
            this.pre.close();
            this.pre = null;
        }
        
        if(this.conn!= null && !this.conn.isClosed()){
            this.conn.close();
            this.conn = null;
        }
    }
}
