/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.BUS;

import java.util.ArrayList;
import team.java.DAO.*;
import team.java.DTO.*;

/**
 *
 * @author ASUS
 */
public class DiscountDetailBUS {
    public static ArrayList<DiscountDetail> listDiscountDetail;
    private DiscountDetailDAO data;
    public DiscountDetailBUS(){
        data = new DiscountDetailDAO();
    }
    
    public void readListDiscountDetail(){
        listDiscountDetail = new ArrayList<>();
        listDiscountDetail = data.readListDiscountDetail();
    }
    
    public boolean add(DiscountDetail d){
        if(isExist(d.getIdDiscount(), d.getIdBook())){
            return false;
        }
        if (data.add(d)){
            listDiscountDetail.add(d);
            return true;
        }
        return false;
    }
    
    public boolean delete(DiscountDetail d){
        if (data.delete(d.getIdDiscount(), d.getIdBook())){
            for(DiscountDetail detail : listDiscountDetail){
                if(detail.getIdDiscount() == (d.getIdDiscount()) && detail.getIdBook() == (d.getIdBook())){
                    listDiscountDetail.remove(detail);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    public boolean delete(int idDiscount, int idBook){
        if (data.delete(idDiscount, idBook)){
            for(DiscountDetail detail : listDiscountDetail){
                if(detail.getIdDiscount() == idDiscount && detail.getIdBook() == idBook){
                    listDiscountDetail.remove(detail);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean update(DiscountDetail d, int idDiscountOld, int idBookOld){
        if (data.update(d, idDiscountOld, idBookOld)){
            for(int i = 0;i < listDiscountDetail.size(); i++){
                if(listDiscountDetail.get(i).getIdDiscount() == idDiscountOld && listDiscountDetail.get(i).getIdBook() == idBookOld){
                    listDiscountDetail.set(i, d);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean isExist(int idDiscount, int idBook){
        for(DiscountDetail b : listDiscountDetail){
            if(b.getIdDiscount() == (idDiscount) && b.getIdBook() == (idBook))
                return true;
        }
        return false;
    }
    
    
    public DiscountDetail findDiscountDetail(int idDiscount, int idBook ){
        for(DiscountDetail detail : listDiscountDetail){
            if(detail.getIdDiscount() == (idDiscount) && detail.getIdBook() == (idBook)){
                return detail;
            }
        }
        return null;
    }
     
    public ArrayList<DiscountDetail> findByBookID(int idBook){
        ArrayList<DiscountDetail> list = new ArrayList<>();
        for(DiscountDetail detail : listDiscountDetail){
            if(detail.getIdBook() == (idBook))
                list.add(detail);
        }
        return list;
    }
    
    public ArrayList<DiscountDetail> findbyDiscountID(int idDiscount){
        ArrayList<DiscountDetail> list = new ArrayList<>();
        for(DiscountDetail detail : listDiscountDetail){
            if(detail.getIdDiscount() == (idDiscount))
                list.add(detail);
        }
        return list;
    }
    
    
}
