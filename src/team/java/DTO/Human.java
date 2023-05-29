package team.java.DTO;

import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author ASUS
 */
public abstract class Human {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");// định dạng ngày vn
    protected String firstname;
    protected String lastname;
    protected Date birthday;
    protected String gender;
    protected String phone;
    public Human(){};
    public Human(String firstname, String lastname, Date birthday, String gender, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    
    public String getFullName(){
        return lastname + " " + firstname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
