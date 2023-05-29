
package team.java.DTO;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author ngoc canh;
 */
public class ImportDetail {
    protected int idImport;
    protected int idBook;
    protected int quantity;
    protected float price;
    protected float totalPrice; //thanhtien
    
    public ImportDetail(){};
    
    public ImportDetail(int idImport, int idBook, int quantiy, float price) {
        this.idImport = idImport;
        this.idBook = idBook;
        this.quantity = quantiy;
        this.totalPrice = 0;
        this.price = price;
    }

    public int getIdImport() {
        return idImport;
    }

    public void setIdImport(int idImport) {
        this.idImport = idImport;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantiy(int quantiy) {
        this.quantity = quantiy;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
    
    public static String formatCurrency(float price){
          Locale localeVN = new Locale("vi", "VN");
          NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
          String result = currencyVN.format(price);
          return result;
    }
}
