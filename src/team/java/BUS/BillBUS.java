/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.BUS;

import java.util.ArrayList;
import java.util.Date;
import team.java.DAO.BillDAO;
import team.java.DTO.Bill;
import team.java.DTO.Customer;
import team.java.DTO.Employee;

/**
 *
 * @author ngoc canh;
 */
public class BillBUS {
    public static ArrayList<Bill> listBill;
    private BillDAO data;
    public BillBUS(){
        data = new BillDAO();
    }
    
    public void readListBill(){
//        if(listBill == null){
//            listBill = new ArrayList<>();
            listBill = data.readListBill();
//        }
    }
    
    public void readListBill(int idEmployee){
//        if(listBill == null){
//            listBill = new ArrayList<>();
            listBill = data.readListBill(idEmployee);
//        }
    }
    
    public boolean add(Bill b){
        if (data.add(b)){
            listBill.add(b);
            return true;
        }
        return false;
    }
    
    public boolean delete(Bill b){
        if (data.delete(b.getIdBill())){
            listBill.remove(b);
            return true;
        }
        
        return false;
    }
    
    
    public boolean delete(int idBill){
        if (data.delete(idBill)){
            listBill.remove(idBill - 1);
            return true;
        }
        
        return false;
    }
    
    public boolean update(Bill b, int index){
        if (data.update(b)){
            listBill.set(index,b);
            return true;
        }
        
        return false;
    }
    
    public boolean isExistID(int idBill){
        for(Bill b : listBill){
            if(b.getIdBill() == (idBill))
                return true;
        }
        return false;
    }
    
    
    public Bill findBillByID(int idBill){
        for(Bill b : listBill){
            if(b.getIdBill() == (idBill)){
                return b;
            }
        }
        return null;
    }
    
    public Bill findBillByIDEmployee(int idEmployee){
        for(Bill b : listBill){
            if(b.getIdEmployee() == (idEmployee))
                return b;
        }
        return null;
    }
    
    
    
    
    public ArrayList<Bill> findBillByEmployee(String name){
        EmployeeBUS emp_bus = new EmployeeBUS();
        ArrayList<Employee> listEmp = emp_bus.findEmployeeByFullname(name);
        ArrayList<Bill> list = new ArrayList<>();
        for(Employee emp : listEmp){
            for(Bill b : listBill){
                if(emp.getIdEmployee() == (b.getIdEmployee()))
                    list.add(b);
            }
        }
        return list;
    }
    
     public ArrayList<Bill> findBillByCustomer(String name){
        CustomerBUS emp_bus = new CustomerBUS();
        ArrayList<Customer> listCus = emp_bus.findCustomerByFullname(name);
        ArrayList<Bill> list = new ArrayList<>();
        for(Customer cus : listCus){
            for(Bill b : listBill){
                if(cus.getIdCustomer() == (b.getIdCustomer()))
                    list.add(b);
            }
        }
        return list;
    }
    
    public Bill findBillByIDCustomer(int id){
        for(Bill b : listBill){
            if(b.getIdCustomer() == (id))
                return b;
        }
        return null;
    }
    
    /////
    public ArrayList<Bill> findBillByTotal(float start, float end){
        ArrayList<Bill> list = new ArrayList<>();
        for(Bill b : listBill){
            if( start <= b.getTotal() && b.getTotal() <= end)
                list.add(b);
        }
        return list;
    }
    
    public ArrayList<Bill> findBillByDate(Date date){
        ArrayList<Bill> list = new ArrayList<>();
        for(Bill b : listBill){
            if( b.getDate().equals(date))
                list.add(b);
        }
        return list;
    }
    
    
    // start<=getdate <= end 
    public ArrayList<Bill> findBillByDate(Date start, Date end){
        ArrayList<Bill> list = new ArrayList<>();
        for(Bill b : listBill){
            if( start.compareTo(b.getDate())<=0 && b.getDate().compareTo(end)<=0)
                list.add(b);
        }
        return list;
    }
}
