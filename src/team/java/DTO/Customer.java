package team.java.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Customer extends Human {
    public int idCustomer;

    public Customer() {
        
    }

    public Customer(int idCustomer, String firstname, String lastname, Date birthday, String gender, String phone) {
        super(firstname, lastname, birthday, gender, phone);
        this.idCustomer = idCustomer;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
    
    
    
}
