
package team.java.DTO;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Publisher {
    protected int idPublisher;
    protected String namePublisher;
    protected String address;
    protected String phone;
    
    public Publisher(){}
    public Publisher(int idPublisher, String namePublisher, String address, String phone){
        this.idPublisher = idPublisher;
        this.namePublisher = namePublisher;
        this.address = address;
        this.phone = phone;
    }

    public int getIdPublisher() {
        return idPublisher;
    }

    public String getNamePublisher() {
        return namePublisher;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }

    public void setNamePublisher(String namePublisher) {
        this.namePublisher = namePublisher;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}

