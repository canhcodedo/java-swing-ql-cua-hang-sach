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
import team.java.DAO.*;
import team.java.DTO.*;


/**
 *
 * @author ASUS
 */
public class BookBUS {
    public static ArrayList<Book> listBook;
    private BookDAO data;
    public BookBUS(){
         data = new BookDAO();
    }
    
    public void readListBook(){
//        if(listBook == null){
            listBook = new ArrayList<>();
            listBook = data.readListBook();
//        }
    }
    
    public boolean add(Book b){
        if(isExist(b.getNameBook())){
            return false;
        }
        if (data.add(b)){
            listBook.add(b);
            return true;
        }
        return false;
    }
    
    
    public boolean delete(Book b){
        if (data.delete(b.getIdBook())){
            listBook.remove(b);
            return true;
        }
        return false;
    }
   
    
    public boolean delete(int idBook, int index){
        if (data.delete(idBook)){
            listBook.remove(index);
            return true;
        }
        return false;
        
    }
    
    public boolean update(Book b, int index){
        if (data.update(b)){
            listBook.set(index,b);
            return true;
        }
        return false;
    }
    
//    public boolean updateID(Book b, String idOld){
//        if(isExistID(b.getIdBook(), idOld))
//            return false;
//        BookDAO data = new BookDAO();
//        data.updateID(b, idOld);
//        for(int i = 0;i < listBook.size(); i++){
//            if( idOld.equals(listBook.get(i).getIdBook()) ){
//                listBook.set(i, b);
//                return true;
//            }
//        }
//        return false;
//    }
    
    public boolean isExist(String name){
        for(Book b : listBook){
            if(b.getNameBook().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    
    public boolean isExistID(int idBook,int idOld){
        for(Book b : listBook){
            if(b.getIdBook() == (idOld))
                continue;
            if(b.getIdBook() == (idBook))
                return true;
        }
        return false;
    }
    
    public Book findBookByID(int idBook){
        for(Book b : listBook){
            if(b.getIdBook() == (idBook)){
                return b;
            }
        }
        return null;
    }
    
    public ArrayList<Book> findBooksByName(String name){
        ArrayList<Book> list = new ArrayList<>();
        for(Book b : listBook){
            if(b.getNameBook().toLowerCase().contains(name.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(b);
        }
        return list;
    }
    
    public Book findBookByName(String name){
        for(Book b : listBook){
            if(b.getNameBook().equals(name)); 
                return b;
        }
        return null;
    }
    
    public ArrayList<Author> findAuthorByName(String name){
        ArrayList<Author> list = new ArrayList<>();
        for(Author a : AuthorBUS.listAuthor){
            if(a.getFullName().toLowerCase().contains(name.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(a);
        }
        return list;
    }
    
    public ArrayList<Book> findBookByNameAuthor(String nameAuthor){
        ArrayList<Book> list = new ArrayList<>();
        ArrayList<Author> listAuthor = findAuthorByName(nameAuthor);
        for(Author au : listAuthor){
            for(Book b : listBook){
                if(au.getIdAuthor() == (b.getIdAuthor()))
                    list.add(b);
            }
        }
        return list;
    }
    
    public ArrayList<BookType> findTypeByName(String name){
        ArrayList<BookType> list = new ArrayList<>();
        for(BookType a :BookTypeBUS.listBookType){
            if(a.getNameType().toLowerCase().contains(name.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(a);
        }
        return list;
    }
    
    public ArrayList<Book> findBookByNameType(String nameType){
        ArrayList<Book> list = new ArrayList<>();
        ArrayList<BookType> listType = findTypeByName(nameType);
        for(BookType type : listType){
            for(Book b : listBook){
                if(type.getIdType() == (b.getIdType()))
                    list.add(b);
            }
        }
        return list;
    }
    
    public ArrayList<Publisher> findPublisherByName(String name){
        ArrayList<Publisher> list = new ArrayList<>();
        for(Publisher a :PublisherBUS.listPublisher){
            if(a.getNamePublisher().toLowerCase().contains(name.toLowerCase())) // kiểm tra có chứa chuỗi con
                list.add(a);
        }
        return list;
    }
    
    public ArrayList<Book> findBookByNamePublisher(String namePublisher){
        ArrayList<Book> list = new ArrayList<>();
        ArrayList<Publisher> listPublisher = findPublisherByName(namePublisher);
        for(Publisher pub : listPublisher){
            for(Book b : listBook){
                if(pub.getIdPublisher() == (b.getIdPublisher()))
                    list.add(b);
            }
        }
        return list;
    }
    
    public ArrayList<Book> findBookByIDType(int idType){
        ArrayList<Book> list = new ArrayList<>();
        for(Book b : listBook){
            if(b.getIdType() == (idType))
                list.add(b);
        }
        return list;
    }
    
    public ArrayList<Book> findBookByIDPublisher(int idPublisher){
        ArrayList<Book> list = new ArrayList<>();
        for(Book b : listBook){
            if(b.getIdPublisher() == (idPublisher))
                list.add(b);
        }
        return list;
    }
    
    public float findPriceByID(int idBook){
        for(Book b : listBook){
            if(b.getIdBook() == (idBook))
                return b.getPrice();
        }
        return -1;
    }
    
    public String findNameByID(int idBook){
        for(Book b : listBook){
            if(b.getIdBook() == (idBook))
                return b.getNameBook();
        }
        return "";
    }
    
    
}

