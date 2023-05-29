/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.DTO;

/**
 *
 * @author ASUS
 */
public class DiscountDetail {
    protected int idBook;
    protected int idDiscount;
    protected int discount = 0;
    
    public DiscountDetail(){}
    
    public DiscountDetail(int idBook, int idDiscount, int discount) {
        this.idBook = idBook;
        this.idDiscount = idDiscount;
        this.discount = discount;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    
    
    
}
