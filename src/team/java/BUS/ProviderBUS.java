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
public class ProviderBUS {
    public static ArrayList<Provider> listProvider;
    private ProviderDAO data;
    public ProviderBUS(){
        data = new ProviderDAO();
    }
    
    public void readListProvider(){
//        if(listProvider == null){
               listProvider = new ArrayList<>();
        listProvider = data.readListProvider();
    }
    
    public boolean add(Provider p){
        if(isExist(p.getNameProvider())){
            return false;
        }
        if (data.add(p)){
            listProvider.add(p);
            return true;
        }
        return false;
    }
    
    public boolean delete(Provider p){
        if (data.delete(p.getIdProvider())){
            listProvider.remove(p);
            return true;
        }
        
        return false;
    }
    
    
    public boolean delete(int idProvider, int index){
        if (data.delete(idProvider)){
            listProvider.remove(index);
            return true;
        }
        return false;
    }
    
    public boolean update(Provider p, int index){
        if (data.update(p)){
            listProvider.set(index, p);
            return true;
        }
        
        return false;
    }
    
    public boolean isExist(String name){
        for(Provider p : listProvider){
            if(p.getNameProvider().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    
    
    public Provider findProviderByID(int idProvider){
        for(Provider p : listProvider){
            if(p.getIdProvider() == (idProvider)){
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<Provider> findProviderByName(String name){
        ArrayList<Provider> list = new ArrayList<>();
        for(Provider p : listProvider){
            if(p.getNameProvider().toLowerCase().contains(name.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(p);
        }
        return list;
    }
    
    public ArrayList<Provider> findProviderByAddress(String addresss){
        ArrayList<Provider> list = new ArrayList<>();
        for(Provider p : listProvider){
            if(p.getAddress().toLowerCase().contains(addresss.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(p);
        }
        return list;
    }
    
    public ArrayList<Provider> findProviderByPhone(String phone){
        ArrayList<Provider> list = new ArrayList<>();
        for(Provider p : listProvider){
            if(p.getPhone().contains(phone)) // kiểm tra có chứa chuỗi con
                list.add(p);
        }
        return list;
    }
    
    public String findNameByID(int idProvider){
        for(Provider p : listProvider){
            if(p.getIdProvider() == (idProvider))
                return p.getNameProvider();
        }
        return "";
    }
    
    public String findAddressByID(int idProvider){
        for(Provider p : listProvider){
            if(p.getIdProvider() == (idProvider))
                return p.getAddress();
        }
        return "";
    }
    
}
