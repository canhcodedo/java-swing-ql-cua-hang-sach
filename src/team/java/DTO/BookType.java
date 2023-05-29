
package team.java.DTO;
/**
 *
 * @author ASUS
 */
public class BookType {
    protected int idType;
    protected String nameType;
    
    public BookType(){}
            
    public BookType(int idType, String nameType) {
        this.idType = idType;
        this.nameType = nameType;
    }
    
    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public int getIdType() {
        return idType;
    }

    public String getNameType() {
        return nameType;
    }
}
