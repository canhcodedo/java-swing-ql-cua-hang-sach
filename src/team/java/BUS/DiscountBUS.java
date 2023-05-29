/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.BUS;

import java.util.ArrayList;
import team.java.DAO.*;
import team.java.DTO.*;

/**
 *
 * @author ASUS
 */
public class DiscountBUS {
    public static ArrayList<Discount> listDiscount;
    private DiscountDAO data;
    public DiscountBUS(){
        data = new DiscountDAO();
    }
    
    public void readListDiscount(){
//        if(listDiscount == null){
            listDiscount = new ArrayList<>();
            listDiscount = data.readListDiscount();
//        }
        
    }
    
    public boolean add(Discount d){
        if(isExist(d.getNameProgram())){
            return false;
        }
        if (data.add(d)){
            listDiscount.add(d);
            return true;
        }
        return false;
        
    }
    
    public boolean delete(Discount d){
        if (data.delete(d.getIdDiscount())){
            listDiscount.remove(d);
            return true;
        }
        return false;
    }
    
    
    public boolean delete(int idDiscount, int index){
        if (data.delete(idDiscount)){
            listDiscount.remove(index);
            return true;
        }
        return false;
    }
    
    public boolean update(Discount d, int index){
        if (data.update(d)){
           listDiscount.set(index, d);
           return true;
        }
        return false;
    }
    
    public boolean isExist(String name){
        for(Discount d : listDiscount){
            if(d.getNameProgram().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    
    
    public Discount findDiscountByID(int idDiscount){
        for(Discount d : listDiscount){
            if(d.getIdDiscount() == (idDiscount)){
                return d;
            }
        }
        return null;
    }
    
    public Discount findDiscountByName(String name){
        for(Discount d : listDiscount){
            if(d.getNameProgram().equals(name)){
                return d;
            }
        }
        return null;
    }
    
    
}
