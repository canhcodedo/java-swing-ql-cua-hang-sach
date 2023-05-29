/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.BUS;

/**
 *
 * @author ngoc canh;
 */
import java.util.regex.*;
public class Error{
	public static boolean isInteger(String data) {
            try {
                    data = data.replaceAll("\\s", "");
                    Integer.parseInt(data);
                    return true;
            }catch(Exception e) {
                    return false;
            }
	}
        
        public static boolean isNagativeNumber(int data){
            return data < 0 ? true : false;
        }
	
        public static boolean containsSpecialChar(String data){
            boolean result = true;
            Pattern p = Pattern.compile("[^àáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝa-z0-9\\s]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(data);
            return result = m.find();
        }
        
        public static boolean containsNumber(String data){
            boolean result = true;
            Pattern p=Pattern.compile("[0-9]");
            Matcher m = p.matcher(data);
            return result = m.find();
        }
        
	public static boolean isNumberPhone(String data) {
            boolean result = true;
            Pattern p = Pattern.compile("^03[2-9]\\d{7}$");
            Pattern p1 = Pattern.compile("^07[767890]\\d{7}$");
            Pattern p2 = Pattern.compile("^08[1-5]\\d{7}$");
            Pattern p3 = Pattern.compile("^05[689]\\d{7}$");

            Matcher m = p.matcher(data);
            Matcher m1 = p1.matcher(data);
            Matcher m2 = p2.matcher(data);
            Matcher m3 = p3.matcher(data);

            if (m.find() || m1.find() || m2.find() || m3.find()) return true;
            return false;
	}
}
