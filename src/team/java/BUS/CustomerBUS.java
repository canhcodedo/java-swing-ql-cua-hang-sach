/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package team.java.BUS;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import team.java.DAO.*;
import team.java.DTO.*;


/**
 *
 * @author ASUS
 */
public class CustomerBUS {
    public static ArrayList<Customer> listCustomer;
    private CustomerDAO data;
    public CustomerBUS(){
        data = new CustomerDAO();
    }
    
    public void readListCustomer(){
//        if(listCustomer == null){
            listCustomer = new ArrayList<>();
            listCustomer = data.readListCustomer();
//        }
    }
    
    public boolean add(Customer c){
        if(isExist(c.getFullName(), c.getPhone())){
            return false;
        }
        if (data.add(c)){
            listCustomer.add(c);
            return true;
        }
        return false;
    }
    
    public boolean delete(Customer c){
        if (data.delete(c.getIdCustomer())){
            listCustomer.remove(c);
            return true;
        }
        
        return false;
    }
    
    
    public boolean delete(int idCustomer, int index){
        if (data.delete(idCustomer)){
            listCustomer.remove(index);
            return true;
        }
        return false;
    }
    
    public boolean update(Customer c, int index){
        if (data.update(c)){
            listCustomer.set(index, c);
            return true;
        }
        return false;
    }
    
    public boolean isExist(String name, String phone){
        for(Customer c : listCustomer){
            if(c.getFullName().equalsIgnoreCase(name) && c.getPhone().equals(phone))
                return true;
        }
        return false;
    }
    
    
    public Customer findCustomerByID(int idCustomer){
        for(Customer c : listCustomer){
            if(c.getIdCustomer() == (idCustomer)){
                return c;
            }
        }
        return null;
    }
    
    public ArrayList<Customer> findCustomerByName(String name){
        ArrayList<Customer> list = new ArrayList<>();
        for(Customer c : listCustomer){
            if(c.getLastName().toLowerCase().contains(name.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(c);
        }
        return list;
    }
    
    public ArrayList<Customer> findCustomerByFullname(String Fullname){
        ArrayList<Customer> list = new ArrayList<>();
        for(Customer c : listCustomer){
            if(c.getFullName().toLowerCase().contains(Fullname.toLowerCase()))
                list.add(c);
        }
        return list;
    }
    
    public ArrayList<Customer> findCustomerByPhone(String phone){
        ArrayList<Customer> list = new ArrayList<>();
        for(Customer c : listCustomer){
            if(c.getPhone().contains(phone)) // kiểm tra có chứa chuỗi con
                list.add(c);
        }
        return list;
    }
    
    
    public String findFullnameByID(int idCustomer){
        for(Customer c : listCustomer){
            if(c.getIdCustomer() == (idCustomer))
                return c.getFullName();
        }
        return "";
    }
}

