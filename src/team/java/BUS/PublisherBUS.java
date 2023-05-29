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
public class PublisherBUS {
    public static ArrayList<Publisher> listPublisher;
    private PublisherDAO data;
    public PublisherBUS(){
        data = new PublisherDAO();
    }
    
    public void readListPublisher(){
//        if(listPublisher == null){
        listPublisher = new ArrayList<>();
        listPublisher = data.readListPublisher();
    }
    
    public boolean add(Publisher p){
        if(isExist(p.getNamePublisher())){
            return false;
        }
        if (data.add(p)){
            listPublisher.add(p);
            return true;
        }
        return false;
    }
    
    public boolean delete(Publisher p){
        if (data.delete(p.getIdPublisher())){
            listPublisher.remove(p);
            return true;
        }
        return false;
    }
    
    
    public boolean delete(int idPublisher, int index){
        if (data.delete(idPublisher)){
            listPublisher.remove(index);
            return true;
        }
        return false;
    }
    
    public boolean update(Publisher p, int index){
        if (data.update(p)){
            listPublisher.set(index, p);
            return true;
        }
        return false;
    }
    
    public boolean isExist(String name){
        for(Publisher p : listPublisher){
            if(p.getNamePublisher().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    
    
    public Publisher findPublisherByID(int idPublisher){
        for(Publisher p : listPublisher){
            if(p.getIdPublisher() == (idPublisher)){
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<Publisher> findPublisherByName(String name){
        ArrayList<Publisher> list = new ArrayList<>();
        for(Publisher p : listPublisher){
            if(p.getNamePublisher().toLowerCase().contains(name.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(p);
        }
        return list;
    }
    
    public ArrayList<Publisher> findPublisherByAddress(String addresss){
        ArrayList<Publisher> list = new ArrayList<>();
        for(Publisher p : listPublisher){
            if(p.getAddress().toLowerCase().contains(addresss.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(p);
        }
        return list;
    }
    
    public ArrayList<Publisher> findPublisherByPhone(String phone){
        ArrayList<Publisher> list = new ArrayList<>();
        for(Publisher p : listPublisher){
            if(p.getPhone().contains(phone)) // kiểm tra có chứa chuỗi con
                list.add(p);
        }
        return list;
    }
    
    public int findIDbyName(String NamePub){
        for(Publisher pub : listPublisher){
            if(pub.getNamePublisher().equals(NamePub)){
                return pub.getIdPublisher();
            }
        }
        return -1;
    }
}

