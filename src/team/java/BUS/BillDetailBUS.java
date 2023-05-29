/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.BUS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import team.java.DAO.*;
import team.java.DTO.*;

/**
 *
 * @author ASUS
 */
public class BillDetailBUS {
    SimpleDateFormat eng = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat vn = new SimpleDateFormat("dd/MM/yyyy");
    public static ArrayList<BillDetail> listBillDetail;
    private BillDetailDAO data;
    public BillDetailBUS(){
        data = new BillDetailDAO();
    }
    
    public void readListBillDetail(){
//        if(listBillDetail == null){
            listBillDetail = new ArrayList<>();
            listBillDetail = data.readListBillDetail();
//        }
        
    }
    
    public void readListBillDetail(int maHD){
//        if(listBillDetail == null){
            listBillDetail = new ArrayList<>();
//        }
        listBillDetail = data.readListBillDetail(maHD);
    }
    
    public boolean add(BillDetail b){
        if(isExist(b.getIdBill(), b.getIdBook())){
            return false;
        }
        
        if (data.add(b)){
            listBillDetail.add(b);
            return true;
        }
        return false;
    }
    
    public boolean delete(BillDetail b){
        if (data.delete(b.getIdBill(), b.getIdBook())){
            for(BillDetail detail : listBillDetail){
                if(detail.getIdBill() == (b.getIdBill()) && detail.getIdBook() == (b.getIdBook())){
                    listBillDetail.remove(detail);
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public BillDetail tinhThanhTien(BillDetail detail){
            // tìm sách theo id
            BookBUS bus_book = new BookBUS();
            
            if(BookBUS.listBook==null)
               bus_book.readListBook();
            
            
            Book book = bus_book.findBookByID(detail.getIdBook());
            detail.setPrice(book.getPrice());
           
            
            //Tìm giảm giá theo id
            BillBUS billbus = new BillBUS();
            Bill bill = billbus.findBillByID(detail.getIdBill());
            
            // tìm phiếu giảm giá
            DiscountDetailBUS bus_dis = new DiscountDetailBUS();
            if(DiscountDetailBUS.listDiscountDetail==null)
                bus_dis.readListDiscountDetail();
            
            
            if (detail.getIdDiscount() == 0){
                // trường hợp mã giảm không hợp lệ
                float thanhtien = detail.getPrice()* detail.getQuantity();
                detail.setTotalPrice(thanhtien);
                System.out.println("Thành tiền(không có giảm giá):" + detail.getTotalPrice());
            }
            
            DiscountDetail discount = bus_dis.findDiscountDetail(detail.getIdDiscount(), book.getIdBook() );
            
            if(discount!=null){
                // trường hợp có mã giảm giá hợp lệ
                // check thời gian còn giảm giá
                
                DiscountBUS tmp = new DiscountBUS();
                Discount a = tmp.findDiscountByID(detail.getIdDiscount());
                
                Date current = bill.getDate();
                
                
                if(a.getDateStart().compareTo(current)<=0 && a.getDateEnd().compareTo(current)>=0){ 
                    float gia = detail.getPrice()*(100 - discount.getDiscount())/100;
                    float thanhtien = gia * detail.getQuantity();
                    detail.setTotalPrice(thanhtien);
                } else {
                    discount.setDiscount(0);
                    detail.setTotalPrice(detail.getPrice()*detail.getQuantity());
//                    System.out.println("Mã giảm đã hết hạn");
                }
                
            //  set giá và thành tiền, có giảm giá
            //  giá hiện tại = giá * ( 100 - phần trăm giảm) /100
                
//                if(discount.getDiscount()==0)
//                    System.out.println("Thành tiền(không có giảm giá):" + detail.getTotalPrice());
//                else
//                    System.out.println("Thành tiền(có giảm giá):" + detail.getTotalPrice());
            }
//            else {
//                float thanhtien = detail.getPrice()* detail.getQuantity();
//                detail.setTotalPrice(thanhtien);
//                System.out.println("Thành tiền(không có giảm giá):" + detail.getTotalPrice());
//            }
        return detail;
    }
    
    
    
    public boolean delete(int idBill, int idBook){
        
        if (data.delete(idBill, idBook)){
            for(BillDetail detail : listBillDetail){
                if(detail.getIdBill() == (idBill) && detail.getIdBook() == (idBook)){
                    listBillDetail.remove(detail);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean update(BillDetail b, int idBillOld, int idBookOld){
        if (data.update(b, idBillOld, idBookOld)){
            for(int i = 0;i < listBillDetail.size(); i++){
                if(listBillDetail.get(i).getIdBill() == idBillOld && listBillDetail.get(i).getIdBook() == idBookOld){
                    listBillDetail.set(i, b);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean isExist(int idBill, int idBook){
        for(BillDetail b : listBillDetail){
            if(b.getIdBill() == (idBill) && b.getIdBook() == (idBook))
                return true;
        }
        return false;
    }
    
    
    public BillDetail findBillDetail(int idBill, int idBook ){
        for(BillDetail detail : listBillDetail){
            if(detail.getIdBill() == (idBill) && detail.getIdBook() == (idBook)){
                return detail;
            }
        }
        return null;
    }
    
    
    public ArrayList<BillDetail> findByBillID(int idBill){
        ArrayList<BillDetail> list = new ArrayList<>();
        for(BillDetail detail : listBillDetail){
            if(detail.getIdBill() == (idBill))
                list.add(detail);
        }
        return list;
    }
}
