
package team.java.DTO;

import java.util.Date;

/**
 *
 * @author ASUS
 */
//Xem ki cho account nha
public class Employee  extends Human{
    protected int idEmployee;
    protected Account account;
    /*
        rol = 1 -> quan ly
        rol = 2 -> nhan vien ban hang
        rol = 3 -> nhan vien kho
    */
    public Employee(){
        super();
    };
    public Employee(String lastname,String firstname, Date birthday, String gender, String phone, Account account, int idEmployee) {
        super(lastname, firstname, birthday, gender, phone);
        this.idEmployee = idEmployee;
        this.account = account;
    }
    
    public Employee(String lastname,String firstname, Date birthday, String gender, String phone, int idEmployee) {
        super(lastname, firstname, birthday, gender, phone);
        this.idEmployee = idEmployee;
    }
   
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount(){
        return account;
    }  
}
