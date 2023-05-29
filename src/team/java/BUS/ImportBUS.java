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
public class ImportBUS {
    public static ArrayList<Import> listImport;
    private ImportDAO data;
    public ImportBUS(){
        data = new ImportDAO();
    }
    
    public void readListImport(){
        listImport = new ArrayList<>();
        listImport = data.readListImport();
    }
    
    public void readListImport(int idEmployee){
        listImport = new ArrayList<>();
        listImport = data.readListImport(idEmployee);
    }
    
    public boolean add(Import i){
        if (data.add(i)){
            listImport.add(i);
            return true;
        }
        return false;
    }
    
    public boolean delete(Import i){
        if (data.delete(i.getIdImport())){
            listImport.remove(i);
            return true;
        }
        return false;
    }
    
    
    public boolean delete(int idImport, int index){
        if (data.delete(idImport)){
            listImport.remove(index);
            return true;
        }
        return false;
    }
    
    public boolean update(Import imp, int index){
        if (data.update(imp)){
            listImport.set(index, imp);
            return true;
        }
        
        return false;
    }
    
    public boolean isExistID(int idImport){
        for(Import i : listImport){
            if(i.getIdImport() == (idImport))
                return true;
        }
        return false;
    }
    
    
    public Import findImportByID(int idImport){
        for(Import i : listImport){
            if(i.getIdImport() == (idImport)){
                return i;
            }
        }
        return null;
    }
    
    
    public ArrayList<Import> findImportByIDEmployee(int idEmployee){
        ArrayList<Import> list = new ArrayList<>();
        for(Import i : listImport){
            if(i.getIdEmployee() == (idEmployee))
                list.add(i);
        }
        return list;
    }
    
    public ArrayList<Import> findImportByProvider(String name){
        ProviderBUS pro_bus = new ProviderBUS();
        ArrayList<Provider> listPro = pro_bus.findProviderByName(name);
        ArrayList<Import> list = new ArrayList<>();
        for(Provider pro : listPro){
            for(Import i : listImport){
                if(pro.getIdProvider() == (i.getIdProvider()))
                    list.add(i);
            }
        }
        
        return list;
    }
    
    public ArrayList<Import> findImportByEmployee(String name){
        EmployeeBUS emp_bus = new EmployeeBUS();
        ArrayList<Employee> listEmp = emp_bus.findEmployeeByFullname(name);
        ArrayList<Import> list = new ArrayList<>();
        for(Employee emp : listEmp){
            for(Import i : listImport){
                if(emp.getIdEmployee() == (i.getIdEmployee()))
                    list.add(i);
            }
        }
        
        return list;
    }
    
    public ArrayList<Import> findImportByTotal(float start, float end){
        ArrayList<Import> list = new ArrayList<>();
        for(Import i : listImport){
            if( start < i.getTotal() && i.getTotal() < end)
                list.add(i);
        }
        return list;
    }
    
    public ArrayList<Import> findImportByDate(Date date){
        ArrayList<Import> list = new ArrayList<>();
        for(Import i : listImport){
            if( i.getDate().equals(date))
                list.add(i);
        }
        return list;
    }
}
