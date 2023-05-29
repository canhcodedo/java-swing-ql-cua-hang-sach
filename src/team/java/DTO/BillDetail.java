package team.java.DTO;
import java.text.NumberFormat;
import java.util.Locale;
/**
 *
 * @author ASUS
 */

public class BillDetail {
    protected int idBill;
    protected int idBook;
    protected int idDiscount;
    protected int quantity;
    protected float price;
    protected float totalPrice; //thanhtien
    
    public BillDetail(){};
    public BillDetail(int idBill, int idBook,int idDiscount,int quantity) {
        this.idBill = idBill;
        this.idBook = idBook;
        this.idDiscount = idDiscount; //ma giam gia
        this.quantity = quantity;
        this.totalPrice = 0;
    }
    
    public BillDetail(int idBill, int idBook, int quantity) {
        this.idBill = idBill;
        this.idBook = idBook;
        this.quantity = quantity;
        this.totalPrice = 0;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float total) {
        this.totalPrice = total;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
    //Dinh dang tien te
    public static String formatCurrency(float price){
          Locale localeVN = new Locale("vi", "VN");
          NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
          String result = currencyVN.format(price);
          return result;
    }
}
