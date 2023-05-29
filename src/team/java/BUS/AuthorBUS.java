/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.BUS;

import java.util.ArrayList;
import static team.java.BUS.AccountBUS.listAccount;
import team.java.DAO.*;
import team.java.DTO.*;

/**
 *
 * @author ASUS
 */
public class AuthorBUS {
    public static ArrayList<Author> listAuthor;
    private AuthorDAO data;
    public AuthorBUS(){
        data = new AuthorDAO();
    }
    
    public void readListAuthor(){
//        if(listAuthor == null){
            listAuthor = new ArrayList<>();
            listAuthor = data.readListAuthor();
//        }
    }
    
    
    public boolean add(Author a){
        if(isExist(a.getFullName())){
            return false;
        }
        if (data.add(a)){
            listAuthor.add(a);
            return true;
        }
        return false;
    }
    
    public boolean delete(Author a){
        
        if (data.delete(a.getIdAuthor())){
            listAuthor.remove(a);
            return true;
        }
        
        return false;
    }
    
    
    public boolean delete(int idAuthor, int index){
        if(data.delete(idAuthor)){
            listAuthor.remove(index);
            return true;
        }
        return false;
    }
    
    public boolean update(Author a, int index){
        if (data.update(a)){
            listAuthor.set(index, a);
            return true;
        }
        
        return false;
    }
    
    public boolean isExist(String name){
        for(Author a : listAuthor){
            if(a.getFullName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    
    
    public Author findAuthorByID(int idAuthor){
        for(Author a : listAuthor){
            if(a.getIdAuthor() == (idAuthor)){
                return a;
            }
        }
        return null;
    }
    
    public ArrayList<Author> findAuthorByName(String name){
        ArrayList<Author> list = new ArrayList<>();
        for(Author a : listAuthor){
            if(a.getFirstName().contains(name)) // kiểm tra có chứa chuỗi con
                list.add(a);
        }
        return list;
    }
    
    public ArrayList<Author> findAuthorByFullname(String Fullname){
        ArrayList<Author> list = new ArrayList<>();
        for(Author a : listAuthor){
            if(a.getFullName().toLowerCase().contains(Fullname.toLowerCase()))
                list.add(a);
        }
        return list;
    }
    
    public String findLastNameByID(int idAuthor) {
        for(Author a: listAuthor) {
            if(a.getIdAuthor() == (idAuthor)){
                return a.getLastName();
            }
        }
        return "";
    }
    
    public String findFirstNameByID(int idAuthor) {
        for(Author a: listAuthor) {
            if(a.getIdAuthor() == (idAuthor)){
                return a.getFirstName();
            }
        }
        return "";
    }
    
    public int findIDbyName(String NameAuth){
        for(Author auth : listAuthor){
            if(auth.getFullName().equals(NameAuth)){
                return auth.getIdAuthor();
            }
        }
        return -1;
    }
    
}
