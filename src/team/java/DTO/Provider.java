package team.java.DTO;

/**
 *
 * @author ASUS
 */
public class Provider {
    protected int idProvider;
    protected String nameProvider;
    protected String address;
    protected String phone;
    
    public Provider(){}
    
    public Provider(int idProvider, String nameProvider, String address, String phone){
        this.idProvider = idProvider;
        this.nameProvider = nameProvider;
        this.address = address;
        this.phone = phone;
    }

    public int getIdProvider() {
        return idProvider;
    }

    public String getNameProvider() {
        return nameProvider;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public void setNameProvider(String nameProvider) {
        this.nameProvider = nameProvider;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
