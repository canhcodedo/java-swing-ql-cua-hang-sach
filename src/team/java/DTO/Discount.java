package team.java.DTO;

import java.util.Date;

/**
 *
 * @author ngoc canh;
 */
//Giam gia: vi du nhu ngay quoc khanh, se co chiec khau 15% cho tat ca sach
public class Discount {
    protected int idDiscount;
    protected String nameProgram;
    protected Date dateStart;
    protected Date dateEnd;
    
    public Discount(){};
    public Discount(int idDiscount, String nameProgram, Date dateStart, Date dateEnd){
        this.idDiscount = idDiscount;
        this.nameProgram = nameProgram;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public String getNameProgram() {
        return nameProgram;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public void setNameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
