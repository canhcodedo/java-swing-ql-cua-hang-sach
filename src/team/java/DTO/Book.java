package team.java.DTO;

import team.java.DAO.mySQLConnect;

/**
 *
 * @author ASUS
 */
public class Book {
    protected int idBook;
    protected String nameBook;
    protected int quantity = 0;
    protected float price;
    protected int idType;
    protected int idPublisher;
    protected int idAuthor;
    public Book(){}
    
    public Book(int idBook, String nameBook, int quantiy, float price, int idType, int idPublisher, int idAuthor) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.price = price;
        this.quantity = quantiy;
        this.idType = idType;
        this.idPublisher = idPublisher;
        this.idAuthor = idAuthor;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }
    
    
    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }
    
    public int getIdBook() {
        return idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
