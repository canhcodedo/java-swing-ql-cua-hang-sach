/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.BUS;

import java.util.ArrayList;
import java.util.Date;
import team.java.DAO.*;
import team.java.DTO.*;

/**
 *
 * @author ASUS
 */
public class ImportDetailBUS {
    public static ArrayList<ImportDetail> listImportDetail;
    private ImportDetailDAO data;
    public ImportDetailBUS(){
         data = new ImportDetailDAO();
    }
    
    public void readListImportDetail(){
        listImportDetail = new ArrayList<>();
        listImportDetail = data.readListImportDetail();
    }
    
    public void readListImportDetail(int maPN){
        listImportDetail = new ArrayList<>();
        listImportDetail = data.readListImportDetail(maPN);
    }
    
    public boolean add(ImportDetail i){
        if(isExist(i.getIdImport(), i.getIdBook())){
            return false;
        }
        if (data.add(i)){
            listImportDetail.add(i);
            return true;
        }
        return false;
        
    }
    
    public boolean delete(ImportDetail i){
        if (data.delete(i.getIdImport(), i.getIdBook())){
            for(ImportDetail detail : listImportDetail){
                if(detail.getIdImport() == (i.getIdBook()) && detail.getIdImport() == (i.getIdBook())){
                    listImportDetail.remove(detail);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    public boolean delete(int idImport, int idBook){
        if (data.delete(idImport, idBook)){
            for(ImportDetail detail : listImportDetail){
                if(detail.getIdImport() == (idImport) && detail.getIdBook() == (idBook)){
                    listImportDetail.remove(detail);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean update(ImportDetail detail, int idImportOld, int idBookOld){
        if (data.update(detail, idImportOld, idBookOld)){
            for(int i = 0;i < listImportDetail.size(); i++){
                if(listImportDetail.get(i).getIdImport() == idImportOld && listImportDetail.get(i).getIdBook() == idBookOld){
                    listImportDetail.set(i, detail);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public ImportDetail tinhThanhTien(ImportDetail detail){
        float gia = detail.getPrice();
        float soluong = detail.getQuantity();
        detail.setTotalPrice(gia*soluong);
        System.out.println("Thành tiền pn : " + detail.getTotalPrice());
        return detail;
    }
    
    public boolean isExist(int idImport, int idBook){
        for(ImportDetail i : listImportDetail){
            if(i.getIdImport() == (idImport) && i.getIdBook() == (idBook))
                return true;
        }
        return false;
    }
    
    
    public ImportDetail findImportDetail(int idImport, int idBook ){
        for(ImportDetail detail : listImportDetail){
            if(detail.getIdImport() == (idImport) && detail.getIdBook() == (idBook)){
                return detail;
            }
        }
        return null;
    }
    
    
    public ArrayList<ImportDetail> findByImportID(int idImport){
        ArrayList<ImportDetail> list = new ArrayList<>();
        for(ImportDetail detail : listImportDetail){
            if(detail.getIdImport() == (idImport))
                list.add(detail);
        }
        return list;
    }
}
