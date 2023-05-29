/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.BUS;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import team.java.DAO.AccountDAO;
import team.java.DTO.Account;
import java.util.regex.*;

/**
 *
 * @author ngoc canh;
 */
public class AccountBUS {
  public static ArrayList<Account> listAccount;
  private AccountDAO data;
    public AccountBUS(){
        data = new AccountDAO();
    }
    
    public void readListAccount(){
//        if(listAccount == null){
            listAccount = new ArrayList<>();
            listAccount = data.readListAccount();
//        }
    }
    
    public String checkRegister(String idUser, String password, int roll, int idEmployee ){
        String msg="";
        Pattern pattern ;
        String regex;
        if("".equals(idUser) || "".equals(password)){
            msg = "Hãy nhập đầy đủ mật khẩu và tài khoản";
        } else {
            /// check username
            //(min: 6 và max:12 ký tự,không có dấu tiếng việt,không có khoảng trống)
            regex = "[a-z0-9_-]{3,12}$";
            pattern = Pattern.compile(regex);
            if(!pattern.matcher(idUser).matches())
                msg = "<html>Tên tài khoản phải có từ 3 - 12 kí tự thường,<br>không có dấu tiếng việt và khoảng trống<br></html>";
            else {
                //check pass 
//                + Phải chứa ít nhất 1 ký tự số từ 0 – 9
//                + Phải chứa ít nhất 1 ký tự chữ thường
//                + Phải chứa ít nhất 1 ký tự chữ hoa
//                + Phải ít nhất là 03 ký tự và tối đa là 20 ký tự
                regex ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{3,20})";
                pattern = Pattern.compile(regex);
                if(!pattern.matcher(password).matches()){
                    msg = "<html>Mật khẩu phải có từ 3 - 20 kí tự,<br>gồm chữ thường, chữ hoa và số<br></html>";
                } else if(this.findAccountByID(idEmployee)!=null){
                    msg = "Nhân viên này đã có tài khoản";
                } else if(this.findAccountByUserName(idUser)!=null){
                    msg = "Tên tài khoản đã tồn tại";
                }
                
            }
        } 
        
       
        return msg;
    }
    
    public String checkLogin(String idUser, String password){
        String msg="";
        if("".equals(idUser) || "".equals(password)){
            msg = "Hãy nhập đầy đủ mật khẩu và tài khoản";
            return msg;
        } 
        Account user = this.findAccountByUserName(idUser);
        if(user==null){
            msg = "Tên tài khoản không đúng";
        } else if(!user.getPassword().equals(password)){
            msg = "Bạn đã nhập sai mật khẩu";
        } else if(user.getStatus()==0){
            msg = "Tài khoản hiện đang bị khoá";
        }
        return msg;
    }
    
    public static String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
 
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
 
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
 
            // Convert message digest into hex value
            String hashtext = no.toString(16);
 
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
 
            // return the HashText
            return hashtext;
        }
 
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean add(Account a){
        
        if (data.add(a)){
            listAccount.add(a);
            return true;
        }     
        
        return false;
    }
    
    public boolean delete(Account acc){
        
        if (data.delete(acc)){
            listAccount.remove(acc);
            return true;
        }
        
        return false;
    }
    
    
    public boolean delete(int idEmployee, int index){
        
        if (data.delete(idEmployee)){
            listAccount.remove(index);
            return true;
        }
        
        return false;
    }
    
    public boolean update(Account a, int index){
        
        if (data.update(a)){
            listAccount.set(index, a);
            return true;
        }
        
        return false;
    }
    
    public boolean isExist(String Username, int idEmployee){
        for(Account b : listAccount){
            if(b.getUsername().equalsIgnoreCase(Username) || b.getIdEmployee() == (idEmployee))
                return true;
        }
        return false;
    }
    
    
    
    public Account findAccountByID(int idEmployee){
        for(Account b : listAccount){
            if(b.getIdEmployee() == idEmployee){
                return b;
            }
        }
        return null;
    }
    
    public Account findAccountByUserName(String username){
        for(Account b : listAccount){
            if(b.getUsername().equals(username)){
                return b;
            }
        }
        return null;
    }
    
    
    public ArrayList<Account> findAccountByRoll(int roll){
        ArrayList<Account> list = new ArrayList<>();
        for(Account b : listAccount){
            if(b.getRoll()==roll)
                list.add(b);
        }
        return list;
    }
   public static String encrypt(String textinput) throws NoSuchAlgorithmException{
       String result = "";
       MessageDigest md = MessageDigest.getInstance("SHA1");
       byte[] srcTextBytes = textinput.getBytes();
       byte[] enrTextBytes= md.digest(srcTextBytes);
       BigInteger bigInt =  new BigInteger(1,enrTextBytes);
       result = bigInt.toString(16);
       return result;
    }
}
