
package team.java.DTO;

/**
 *
 * @author ngoc canh;
 */
import java.util.Scanner;
public class Account {
    protected int idEmployee;
    protected String username;	
    protected String password;
    protected int roll;
    protected int status;
    
    public Scanner sc = new Scanner(System.in);
    public Account(){};
    public Account(int idEmployee, String username, String password, int roll, int status) {
        this.idEmployee = idEmployee;
        this.username = username;
        this.password = password;
        this.roll = roll;
        this.status = status;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
    public void setRoll(int roll) {
        this.roll = roll;
    }
    
    public void setUsername(String username){
       this.username = username;
    };
    public void setPassword(String password){
        this.password = password;
    };

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRoll() {
        return roll;
    }
    
    public String getRollToString(){
        return roll == 1 ? "Quản lý bán hàng" : "Quản lý nhập hàng";
    }
}
