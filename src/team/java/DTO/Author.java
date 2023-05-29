package team.java.DTO;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Author extends Human{
    protected int idAuthor;
    public Author(){}
    public Author(int idAuthor, String lastname, String firstname) {
        this.idAuthor = idAuthor;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public void setNameAuthor(String lastname, String firstname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }
    
    public int getIdAuthor() {
        return idAuthor;
    }

    
    
}
