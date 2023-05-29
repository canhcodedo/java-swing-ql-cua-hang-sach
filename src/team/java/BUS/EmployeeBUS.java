/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.BUS;

import java.util.ArrayList;
import team.java.DAO.EmployeeDAO;
import team.java.DTO.Employee;

/**
 *
 * @author ngoc canh;
 */
public class EmployeeBUS {
    public static ArrayList<Employee> listEmployee;
    private EmployeeDAO data;
    public EmployeeBUS(){
        data = new EmployeeDAO();
    }
    
    public void readListEmployee(){
        listEmployee = new ArrayList<>();
        listEmployee = data.readListEmployee();
    }
    
    public boolean add(Employee b){
        if(isExist(b.getFullName(), b.getPhone())){
            return false;
        }
        if ( data.add(b)){
            listEmployee.add(b);
            return true;
        }
        return false;
    }
    
    public boolean delete(Employee b){
        if (data.delete(b.getIdEmployee())){
            listEmployee.remove(b);
            return true;
        }
        
        return false;
    }
    
    
    public boolean delete(int idEmployee, int index){
        if (data.delete(idEmployee)){
            for(Employee e : listEmployee){
                listEmployee.remove(index);
                return true;
            }
        }
        return false;
    }
    
    public boolean update(Employee b, int index){
        if (data.update(b)){
            listEmployee.set(index, b);
            return true;
        }
        
        return false;
    }
    
    public boolean isExist(String name, String phone){
        for(Employee b : listEmployee){
            if(b.getFullName().equalsIgnoreCase(name) && b.getPhone().equals(phone))
                return true;
        }
        return false;
    }
    
    
    public Employee findEmployeeByID(int idEmployee){
        for(Employee b : listEmployee){
            if(b.getIdEmployee() == (idEmployee)){
                return b;
            }
        }
        return null;
    }
    
    
    public ArrayList<Employee> findEmployeeByName(String name){
        ArrayList<Employee> list = new ArrayList<>();
        for(Employee b : listEmployee){
            if(b.getFullName().contains(name))
                list.add(b);
        }
        return list;
    }
    
    public ArrayList<Employee> findEmployeeByFullname(String Fullname){
        ArrayList<Employee> list = new ArrayList<>();
        for(Employee c : listEmployee){
            if(c.getFullName().toLowerCase().contains(Fullname.toLowerCase()))
                list.add(c);
        }
        return list;
    }
    
     public ArrayList<Employee> findEmployeeByPhone(String phone){
        ArrayList<Employee> list = new ArrayList<>();
        for(Employee c : listEmployee){
            if(c.getPhone().contains(phone)) // kiểm tra có chứa chuỗi con
                list.add(c);
        }
        return list;
    }
    
    
    public String findFullnameByID(int idEmployee){
        for(Employee c : listEmployee){
            if(c.getIdEmployee() == (idEmployee))
                return c.getFullName();
        }
        return "";
    }
}
