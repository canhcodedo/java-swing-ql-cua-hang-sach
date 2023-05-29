
package team.java.DTO;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ngoc canh;
 */
public class Import {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");// định dạng ngày vn
    protected int idImport;
    protected int idEmployee;
    protected int idProvider;
    protected Date date;
    protected float total = 0;//tongtien
    
    public Import(){}
    
    public Import(int idImport,int idEmployee, int idProvider, Date date, float total ) {
        this.idImport = idImport;
        this.idEmployee = idEmployee;
        this.idProvider = idProvider;
        this.date = date;
        this.total = total;
    }

    public Import(int idImport, int idEmployee, int idProvider) {
        this.idImport = idImport;
        this.idEmployee = idEmployee;
        this.idProvider = idProvider;
        this.date = new Date();
        this.total = 0;
    }

    public void setIdImport(int idImport) {
        this.idImport = idImport;
    }

    public int getIdImport() {
        return idImport;
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

    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }
    
    public static String formatCurrency(float price){
          Locale localeVN = new Locale("vi", "VN");
          NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
          String result = currencyVN.format(price);
          return result;
    }
}