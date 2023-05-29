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
public class BookTypeBUS {
    public static ArrayList<BookType> listBookType;
    private BookTypeDAO data;
    public BookTypeBUS(){
        data = new BookTypeDAO();
    }
    
    public void readListBookType(){
//        if(listBookType == null){
//            listBookType = new ArrayList<>();
//            listBookType = data.readListBookType();
//        }
        listBookType = new ArrayList<>();
        listBookType = data.readListBookType();
        
    }
    
    public boolean add(BookType t){
        if(isExist(t.getNameType())){
            return false;
        }
        
        if (data.add(t)){
            listBookType.add(t);
            return true;
        }
        return false;
    }
    
    public boolean delete(BookType t){
        if (data.delete(t) == true){
            listBookType.remove(t);
            return true;
        }
        return false;
    }
    
    
    public boolean delete(int  idBookType, int index){
        if (data.delete(idBookType)){
            listBookType.remove(index);
            return true;
        }
        return false;
    }
    
    public boolean update(BookType t, int index){
        if (data.update(t)){
            listBookType.set(index, t);
            return true;
        }
        
        return false;
    }
    
    public boolean isExist(String nameType){
        for(BookType t : listBookType){
            if(t.getNameType().equalsIgnoreCase(nameType))
                return true;
        }
        return false;
    }
    
    
    public BookType findBookTypeByID(int idBookType){
        for(BookType t : listBookType){
            if(t.getIdType() == (idBookType)){
                return t;
            }
        }
        return null;
    }
    
    public ArrayList<BookType> findBookTypeByName(String name){
        ArrayList<BookType> list = new ArrayList<>();
        for(BookType t : listBookType){
            if(t.getNameType().toLowerCase().contains(name.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(t);
        }
        return list;
    }
    
    public int findIDbyName(String NameType){
        for(BookType t : listBookType){
            if(t.getNameType().equalsIgnoreCase(NameType)){
                return t.getIdType();
            }
        }
        return -1;
    }
    
}
