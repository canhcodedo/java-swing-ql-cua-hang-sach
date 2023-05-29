package team.java.DTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
public class Bill {
    SimpleDateFormat vn = new SimpleDateFormat("dd/MM/yyyy");// định dạng ngày vn
    protected int idBill;
    protected int idCustomer;
    protected int idEmployee;
    protected Date date;
    protected float total = 0; //tongtien  
    
    public Bill(){};
    public Bill(int idBill, Date date, float total, int idCustomer, int idEmployee) {
        this.idBill = idBill;
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.date = date; 
    }
    
    public Bill(int idBill, int idCustomer, int idEmployee) {
        this.idBill = idBill;
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.date = new Date();
        this.total = 0;      
    }
    
    
    public int getIdBill() {
       return idBill;    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotal() {
        return total;
    }
    
    
    public static String formatCurrency(float price){
          Locale localeVN = new Locale("vi", "VN");
          NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
          String result = currencyVN.format(price);
          return result;
    }
    
    
}
