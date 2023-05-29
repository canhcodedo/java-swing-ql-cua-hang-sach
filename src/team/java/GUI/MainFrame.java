/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import javax.swing.*;
import team.java.BUS.*;
import team.java.DTO.*;
import team.java.DAO.*;
import team.java.DTO.*;
import team.java.BUS.Error;
import javax.swing.WindowConstants;
import java.util.Date;
import java.util.ArrayList;
import static team.java.BUS.BookBUS.listBook;
import java.util.Vector;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ngoc canh;
 */
public class MainFrame extends javax.swing.JFrame {
    int idCurrent;
    SimpleDateFormat eng = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat vn = new SimpleDateFormat("dd/MM/yyyy");
    
    DefaultTableModel modelBook = new DefaultTableModel();
    DefaultTableModel modelTypeBook = new DefaultTableModel();
    DefaultTableModel modelAuthor = new DefaultTableModel();
    DefaultTableModel modelProvider = new DefaultTableModel();
    DefaultTableModel modelPublisher = new DefaultTableModel();
    DefaultTableModel modelAccount = new DefaultTableModel();
    DefaultTableModel modelCustomer = new DefaultTableModel();
    DefaultTableModel modelEmployee = new DefaultTableModel();
    DefaultTableModel modelDiscount = new DefaultTableModel();
    DefaultTableModel modelDetailDiscount = new DefaultTableModel();
    DefaultTableModel modelBill = new DefaultTableModel();
    DefaultTableModel modelImport = new DefaultTableModel();
    DefaultTableModel modelThongKe = new DefaultTableModel();
    DefaultTableModel modelSachBan = new DefaultTableModel();
    

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        modelBook = (DefaultTableModel) tblSach.getModel();
        modelTypeBook = (DefaultTableModel) tblTheLoai.getModel();
        modelAuthor = (DefaultTableModel) tblTacGia.getModel();
        modelProvider = (DefaultTableModel) tblNCC.getModel();
        modelPublisher = (DefaultTableModel) tblNXB.getModel();
        modelAccount = (DefaultTableModel) tblAccount.getModel();
        modelCustomer = (DefaultTableModel) tblKhachHang.getModel();
        modelEmployee = (DefaultTableModel) tblNhanVien.getModel();
        modelDiscount = (DefaultTableModel) tblGiamGia.getModel();
        modelDetailDiscount = (DefaultTableModel) tblChiTietGiamGia.getModel();
        modelBill = (DefaultTableModel) tblHoaDon.getModel();
        modelImport = (DefaultTableModel) tblPhieuNhap.getModel();
        modelThongKe = (DefaultTableModel) tblTK_DT.getModel();
        modelSachBan = (DefaultTableModel) tblTK_SL.getModel();
        
        readTableTypeBook();
        readTableAuthor();
        readTableProvider();
        readTablePublisher();
        
        readTableEmployee();
        readTableCustomer();
        
        readTableBook();
        readTableAccount();
        readTableDiscount();
        readTableDetailDiscount();
        
        
        
        setCbType();
        setCbAuthor();
        setCbNXB();
        setCbDiscount();
        setCbBook();
        setCbRegister();
        setCbCustomer();
        setCbEmployee();
        setCbProvider();
        
        
        
        setFindingBook();
        setFindingPublisher();
        setFindingProvider();
    }
    
    
    public void setMenu(Account a){
        int roll = a.getRoll();
        idCurrent = a.getIdEmployee();
        EmployeeBUS bus = new EmployeeBUS();
        if(EmployeeBUS.listEmployee==null)
            bus.readListEmployee();
        Employee user = bus.findEmployeeByID(idCurrent);  
        lbIDUser.setText("Mã nhân viên: " + idCurrent);
        if (roll == 0) lbUsername.setText("Xin chào Admin");
        else lbUsername.setText("Xin chào "+ user.getFullName());
        if(roll == 1){
            tabbedMain.removeAll();
            tabbedMain.addTab("SÁCH", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_book_40px_1.png")), pnlBook);
            tabbedMain.addTab("HÓA ĐƠN", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_bill_40px_4.png")), pnlBill);
            readTableBill(idCurrent);
            tabbedMain.addTab("GIẢM GIÁ", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_discount_40px.png")), pnlDiscount, "");
            tabbedMain.addTab("KHÁCH HÀNG", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_customer_40px_1.png")), pnlCustomer);       
        } 
        else if(roll == 2){
            tabbedMain.removeAll();
            readTableImport(idCurrent);
            tabbedMain.addTab("SÁCH", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_book_40px_1.png")), pnlBook);
            tabbedMain.addTab("NHÀ CUNG CẤP", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_factory_40px_1.png")), pnlProvider, "");
            tabbedMain.addTab("NHÀ XUẤT BẢN", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_apartment_40px_1.png")), pnlPublisher);
            tabbedMain.addTab("NHẬP SÁCH", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_40px_6.png")), pnlImport, "");
        }
        else{
            readTableBill();
            readTableImport();
            comHoaDon.addItem("Tìm kiếm theo nhân viên");
            comPhieuNhap.addItem("Tìm kiếm theo nhân viên");
        }
    }
    
    private void exportToExcel(DefaultTableModel model) {
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        Workbook excelExporter = null;
        JFileChooser excelFileChoose = new JFileChooser(getClass().getResource("/report/table/").getPath());
        excelFileChoose.setDialogTitle("Save as");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel File", "xls", "xlsx", "csv");
        excelFileChoose.setFileFilter(fnef);
        int excelChooser = excelFileChoose.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelExporter = new XSSFWorkbook();
                Sheet excelSheet = excelExporter.createSheet("Bảng mới");
                System.out.println(model.getRowCount());
                
                for (int i = 0; i < model.getRowCount(); i++) {
                    Row excelRow = excelSheet.createRow(i);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Cell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(model.getValueAt(i, j).toString());    
                    }
                }
                System.out.println(excelFileChoose.getSelectedFile());
                excelFOU = new FileOutputStream(excelFileChoose.getSelectedFile()+ ".xlsx");
                excelExporter.write(excelFOU);
                JOptionPane.showMessageDialog(null, "xuất thành công");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (excelFOU != null) {
                        excelFOU.close();
                    }
                    if (excelBOU != null) {
                        excelBOU.close();
                    }
                    if (excelExporter != null) {
                        excelExporter.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    private void setCbRegister() {
//        EmployeeBUS bus = new EmployeeBUS();
//        if (EmployeeBUS.listEmployee == null) {
//            bus.readListEmployee();
//        }
        Vector<String> rowdata = new Vector<>();
        for (Employee nv : EmployeeBUS.listEmployee) {
            rowdata.add(nv.getFullName());
        }
        cbEmployee.setModel(new DefaultComboBoxModel<>(rowdata));
    }

    // tạo cb thể loại mặc định
    private void setCbType() {
//        BookTypeBUS bus = new BookTypeBUS();
//        if (BookTypeBUS.listBookType == null) {
//            bus.readListBookType();
//        }
        Vector<String> rowdata = new Vector<>();
        for (BookType type : BookTypeBUS.listBookType) {
            rowdata.add(type.getNameType());
        }
        cbTheLoai.setModel(new DefaultComboBoxModel<>(rowdata));
    }

    // tạo cb tác giả mặc định
    private void setCbAuthor() {
//        AuthorBUS bus = new AuthorBUS();
//        if (AuthorBUS.listAuthor == null) {
//            bus.readListAuthor();
//        }
        Vector<String> rowdata = new Vector<>();
        for (Author auth : AuthorBUS.listAuthor) {
            rowdata.add(auth.getFullName());
        }
        cbTacGia.setModel(new DefaultComboBoxModel<>(rowdata));
    }

    // tạo cb NXB mặc định
    private void setCbNXB() {
//        PublisherBUS bus = new PublisherBUS();
//        if (PublisherBUS.listPublisher == null) {
//            bus.readListPublisher();
//        }
        Vector<String> rowdata = new Vector<>();
        for (Publisher pub : PublisherBUS.listPublisher) {
            rowdata.add(pub.getNamePublisher());
        }
        cbNXB.setModel(new DefaultComboBoxModel<>(rowdata));
    }
    
    
    private void setCbProvider() {
//        ProviderBUS bus = new ProviderBUS();
//        if (ProviderBUS.listProvider == null) {
//            bus.readListProvider();
//        }
        Vector<String> rowdata = new Vector<>();
        for (Provider p : ProviderBUS.listProvider) {
            rowdata.add(p.getNameProvider());
        }
        cbNCC.setModel(new DefaultComboBoxModel<>(rowdata));
    }
    
    private void setCbCustomer() {
//        CustomerBUS bus = new CustomerBUS();
//        if (CustomerBUS.listCustomer == null) {
//            bus.readListCustomer();
//        }
        Vector<String> rowdata = new Vector<>();
        for (Customer cus : CustomerBUS.listCustomer) {
            rowdata.add(cus.getFullName());
        }
        cbKhachHang.setModel(new DefaultComboBoxModel<>(rowdata));
    }
    
    
    private void setCbEmployee() {
//        EmployeeBUS bus = new EmployeeBUS();
//        if (bus.listEmployee == null) {
//            bus.readListEmployee();
//        }
        Vector<String> rowdata = new Vector<>();
        for (Employee emp : EmployeeBUS.listEmployee) {
            rowdata.add(emp.getFullName());
        }
        cbEmployee.setModel(new DefaultComboBoxModel<>(rowdata));
    }
    
    private void setCbDiscount() {
//        DiscountBUS bus = new DiscountBUS();
//        if (DiscountBUS.listDiscount == null) {
//            bus.readListDiscount();
//        }
        Vector<String> rowdata = new Vector<>();
        for (Discount d : DiscountBUS.listDiscount) {
            rowdata.add(d.getNameProgram());
        }
        cbGG.setModel(new DefaultComboBoxModel<>(rowdata));
    }
    
    private void setCbBook() {
//        BookBUS bus = new BookBUS();
//        if (BookBUS.listBook == null) {
//            bus.readListBook();
//        }
        Vector<String> rowdata = new Vector<>();
        for (Book b : BookBUS.listBook) {
            rowdata.add(b.getNameBook());
        }
        cbSach_GG.setModel(new DefaultComboBoxModel<>(rowdata));
    }

    //tạo cb tìm kiếm sách
    private void setFindingBook() {
        String[] finding = {"Tìm kiếm theo tên sách","Tìm kiếm theo tác giả", "Tìm kiếm theo thể loại",
            "Tìm kiếm theo nhà xuất bản"};
        Vector<String> row = new Vector<>();
        for (int i = 0; i < finding.length; i++) {
            row.add(finding[i]);
        }
        comSach.setModel(new DefaultComboBoxModel<>(row));
    }
    
    private void setFindingPublisher() {
        String[] finding = {"Tìm kiếm theo tên", "Tìm kiếm theo điện thoại"};
        Vector<String> row = new Vector<>();
        for (int i = 0; i < finding.length; i++) {
            row.add(finding[i]);
        }
        comNXB.setModel(new DefaultComboBoxModel<>(row));
    }
    
    private void setFindingProvider() {
        String[] finding = {"Tìm kiếm theo tên", "Tìm kiếm theo điện thoại"};
        Vector<String> row = new Vector<>();
        for (int i = 0; i < finding.length; i++) {
            row.add(finding[i]);
        }
        comNCC.setModel(new DefaultComboBoxModel<>(row));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel22 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jToggleButton1 = new javax.swing.JToggleButton();
        pnlMain = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        pnlTieuDe = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        lblMinimize = new javax.swing.JLabel();
        lblLogOut = new javax.swing.JLabel();
        lbIDUser = new javax.swing.JLabel();
        lbUsername = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tabbedMain = new javax.swing.JTabbedPane();
        pnlBook = new javax.swing.JPanel();
        panel_sach_tg_tl = new javax.swing.JTabbedPane();
        pnlBook1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();
        pnlContentSach = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSLSach = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        cbNXB = new javax.swing.JComboBox<>();
        cbTacGia = new javax.swing.JComboBox<>();
        txtTenSach = new javax.swing.JTextField();
        cbTheLoai = new javax.swing.JComboBox<>();
        lblFindBook = new javax.swing.JLabel();
        txtFindBook = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        comSach = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        btnThemSach = new javax.swing.JButton();
        btnSuaSach = new javax.swing.JButton();
        btnXoaSach = new javax.swing.JButton();
        btnLamMoiSach = new javax.swing.JButton();
        btnRSSach = new javax.swing.JButton();
        pnlType = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblTheLoai = new javax.swing.JTable();
        jPanel36 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        txtTenTL = new javax.swing.JTextField();
        txtFindType = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        lblFindType = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        btnThemTL = new javax.swing.JButton();
        btnSuaTL = new javax.swing.JButton();
        btnXoaTL = new javax.swing.JButton();
        btnLamMoiTL = new javax.swing.JButton();
        btnRSSach12 = new javax.swing.JButton();
        pnlAuthor = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblTacGia = new javax.swing.JTable();
        jPanel33 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        txtHoTacGia = new javax.swing.JTextField();
        txtTenTacGia = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtFindAuthor = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        lblFindAuthor = new javax.swing.JLabel();
        btnRSSach13 = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        btnThemTG = new javax.swing.JButton();
        btnSuaTG = new javax.swing.JButton();
        btnXoaTG = new javax.swing.JButton();
        btnLamMoiTG = new javax.swing.JButton();
        pnlProvider = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblNCC = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        txtDiaChiNCC = new javax.swing.JTextField();
        txtSDTNCC = new javax.swing.JTextField();
        txtFindProvider = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lblFindProvider = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        btnThemNCC = new javax.swing.JButton();
        btnSuaNCC = new javax.swing.JButton();
        btnXoaNCC = new javax.swing.JButton();
        btnLamMoiNCC = new javax.swing.JButton();
        btnRSSach1 = new javax.swing.JButton();
        comNCC = new javax.swing.JComboBox<>();
        pnlPublisher = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblNXB = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        txtTenNXB = new javax.swing.JTextField();
        txtDiaChiNXB = new javax.swing.JTextField();
        txtSDTNXB = new javax.swing.JTextField();
        txtFindPublisher = new javax.swing.JTextField();
        comNXB = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jlblFindPublisher = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        btnThemNXB = new javax.swing.JButton();
        btnSuaNXB = new javax.swing.JButton();
        btnXoaNXB = new javax.swing.JButton();
        btnLamMoiNXB = new javax.swing.JButton();
        btnRSSach2 = new javax.swing.JButton();
        pnlBill = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtTongTienHD = new javax.swing.JTextField();
        dateNgayLapHD = new com.toedter.calendar.JDateChooser();
        cbKhachHang = new javax.swing.JComboBox<>();
        lblFindBill = new javax.swing.JLabel();
        comHoaDon = new javax.swing.JComboBox<>();
        jSeparator13 = new javax.swing.JSeparator();
        txtFindBill = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        btnThemHD1 = new javax.swing.JButton();
        btnLamMoiHD = new javax.swing.JButton();
        btnInHD = new javax.swing.JButton();
        btnCTHD = new javax.swing.JButton();
        btnRSSach3 = new javax.swing.JButton();
        pnlImport = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblPhieuNhap = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        txtMaPhieuNhap = new javax.swing.JTextField();
        txtTongTienPN = new javax.swing.JTextField();
        dateNgayNhap = new com.toedter.calendar.JDateChooser();
        cbNCC = new javax.swing.JComboBox<>();
        comPhieuNhap = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        txtFindImport = new javax.swing.JTextField();
        lblFindImport = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        btnThemPN = new javax.swing.JButton();
        btnLamMoiPN = new javax.swing.JButton();
        btnInPN = new javax.swing.JButton();
        btnCTPN = new javax.swing.JButton();
        btnRSSach4 = new javax.swing.JButton();
        pnlCustomer = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtHOKH = new javax.swing.JTextField();
        txtSDTKH = new javax.swing.JTextField();
        txtGioiTinhKH = new javax.swing.JComboBox<>();
        txtTenKH = new javax.swing.JTextField();
        dateNgaySinhKH = new com.toedter.calendar.JDateChooser();
        jSeparator6 = new javax.swing.JSeparator();
        comKH = new javax.swing.JComboBox<>();
        txtFindCustomer = new javax.swing.JTextField();
        lblFindCustomer = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        btnThemKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        btnLamMoiKH = new javax.swing.JButton();
        btnRSSach5 = new javax.swing.JButton();
        pnlEmployee = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtHONV = new javax.swing.JTextField();
        txtSDTNV = new javax.swing.JTextField();
        txtGioiTinhNV = new javax.swing.JComboBox<>();
        txtTenNV = new javax.swing.JTextField();
        dateNgaySinhNV = new com.toedter.calendar.JDateChooser();
        comNV = new javax.swing.JComboBox<>();
        txtFindEmployee = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        lblFindEmployee = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        btnLamMoiNV = new javax.swing.JButton();
        btnRSSach6 = new javax.swing.JButton();
        pnlAccount = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        cbRoll = new javax.swing.JComboBox<>();
        cbEmployee = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JPasswordField();
        txtUser = new javax.swing.JTextField();
        btnTaoTK = new javax.swing.JButton();
        txtMsg = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();
        btnLock = new javax.swing.JButton();
        btnDeleteAccount = new javax.swing.JButton();
        btnUnLock = new javax.swing.JButton();
        btnRSSach9 = new javax.swing.JButton();
        pnlStatistics = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTK_DT = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txtTK_DT = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        dateTuNgayDT = new com.toedter.calendar.JDateChooser();
        jLabel65 = new javax.swing.JLabel();
        dateDenNgayDT = new com.toedter.calendar.JDateChooser();
        btnTKDT = new javax.swing.JButton();
        btnRSSach8 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblTK_SL = new javax.swing.JTable();
        lblCheckSL = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        dateTuNgaySL = new com.toedter.calendar.JDateChooser();
        jLabel67 = new javax.swing.JLabel();
        dateDenNgaySL = new com.toedter.calendar.JDateChooser();
        jLabel48 = new javax.swing.JLabel();
        txtTK_SL = new javax.swing.JTextField();
        btnTKSL = new javax.swing.JButton();
        btnRSSach7 = new javax.swing.JButton();
        pnlDiscount = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        txtTenCT = new javax.swing.JTextField();
        dateNgayBD = new com.toedter.calendar.JDateChooser();
        dateNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txtChietKhau = new javax.swing.JTextField();
        cbSach_GG = new javax.swing.JComboBox<>();
        cbGG = new javax.swing.JComboBox<>();
        jPanel39 = new javax.swing.JPanel();
        btnThemGG = new javax.swing.JButton();
        btnSuaGG = new javax.swing.JButton();
        btnXoaGG = new javax.swing.JButton();
        btnLamMoiGG = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        btnThemCTGG = new javax.swing.JButton();
        btnSuaCTGG = new javax.swing.JButton();
        btnXoaCTGG = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblGiamGia = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblChiTietGiamGia = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        btnRSSach10 = new javax.swing.JButton();
        btnRSSach11 = new javax.swing.JButton();

        jLabel10.setText("jLabel10");

        jLabel13.setText("jLabel13");

        jLabel15.setText("jLabel15");

        jLabel16.setText("jLabel16");

        jLabel5.setText("jLabel5");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jLabel22.setText("jLabel22");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản Lý Cửa Hàng Sách");
        setBackground(new java.awt.Color(204, 255, 204));
        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setMinimumSize(null);
        setUndecorated(true);

        pnlMain.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(0, 255, 204));
        jPanel6.setPreferredSize(new java.awt.Dimension(938, 53));

        pnlTieuDe.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_books_emoji_48px.png"))); // NOI18N
        jLabel1.setText(" QUẢN LÝ CỬA HÀNG SÁCH");
        jLabel1.setToolTipText("");

        lblExit.setBackground(new java.awt.Color(204, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete_26px.png"))); // NOI18N
        lblExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblExit.setOpaque(true);
        lblExit.setPreferredSize(new java.awt.Dimension(30, 30));
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExitMouseExited(evt);
            }
        });

        lblMinimize.setBackground(new java.awt.Color(204, 255, 255));
        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/subtract_26px.png"))); // NOI18N
        lblMinimize.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMinimize.setOpaque(true);
        lblMinimize.setPreferredSize(new java.awt.Dimension(30, 30));
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseExited(evt);
            }
        });

        lblLogOut.setBackground(new java.awt.Color(204, 255, 255));
        lblLogOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/export_26px.png"))); // NOI18N
        lblLogOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLogOut.setOpaque(true);
        lblLogOut.setPreferredSize(new java.awt.Dimension(30, 30));
        lblLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLogOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogOutMouseExited(evt);
            }
        });

        lbIDUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lbUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/businessman_100px.png"))); // NOI18N

        javax.swing.GroupLayout pnlTieuDeLayout = new javax.swing.GroupLayout(pnlTieuDe);
        pnlTieuDe.setLayout(pnlTieuDeLayout);
        pnlTieuDeLayout.setHorizontalGroup(
            pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieuDeLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIDUser, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
                .addComponent(lblLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTieuDeLayout.setVerticalGroup(
            pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieuDeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbIDUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTieuDeLayout.createSequentialGroup()
                .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTieuDeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlTieuDeLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(pnlTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMain.add(jPanel6);
        jPanel6.setBounds(0, 0, 1120, 80);

        tabbedMain.setBackground(new java.awt.Color(204, 255, 255));
        tabbedMain.setForeground(new java.awt.Color(0, 153, 51));
        tabbedMain.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabbedMain.setFocusTraversalPolicyProvider(true);
        tabbedMain.setOpaque(true);

        pnlBook.setBackground(new java.awt.Color(240, 242, 245));
        pnlBook.setEnabled(false);
        pnlBook.setPreferredSize(new java.awt.Dimension(1500, 1000));

        panel_sach_tg_tl.setBackground(new java.awt.Color(240, 242, 245));
        panel_sach_tg_tl.setOpaque(true);

        pnlBook1.setBackground(new java.awt.Color(240, 242, 245));
        pnlBook1.setLayout(null);

        tblSach.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>", "<html><b>Tên sách</b></html>", "<html><b>Số lượng</b></html>", "<html><b>Đơn giá</b></html>", "<html><b>Tác giả</b></html>", "<html><b>Thể loại</b></html>", "<html><b>Nhà xuất bản</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSach.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSach.setFocusable(false);
        tblSach.setRowHeight(23);
        tblSach.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblSach.getTableHeader().setResizingAllowed(false);
        tblSach.getTableHeader().setReorderingAllowed(false);
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSach);

        pnlBook1.add(jScrollPane1);
        jScrollPane1.setBounds(370, 70, 570, 410);

        pnlContentSach.setBackground(new java.awt.Color(240, 242, 245));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Tên sách");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Tác giả");
        jLabel8.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Thể loại");
        jLabel9.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nhà xuất bản");
        jLabel11.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Số lượng");
        jLabel17.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Đơn giá (VND)");
        jLabel18.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtSLSach.setEditable(false);
        txtSLSach.setEnabled(false);

        cbNXB.setMaximumRowCount(9);

        cbTheLoai.setMaximumRowCount(9);

        javax.swing.GroupLayout pnlContentSachLayout = new javax.swing.GroupLayout(pnlContentSach);
        pnlContentSach.setLayout(pnlContentSachLayout);
        pnlContentSachLayout.setHorizontalGroup(
            pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentSachLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addGroup(pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSLSach, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbNXB, javax.swing.GroupLayout.Alignment.LEADING, 0, 177, Short.MAX_VALUE)
                    .addComponent(cbTacGia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDonGia)
                    .addComponent(txtTenSach)
                    .addComponent(cbTheLoai, javax.swing.GroupLayout.Alignment.LEADING, 0, 177, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnlContentSachLayout.setVerticalGroup(
            pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentSachLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSLSach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContentSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pnlBook1.add(pnlContentSach);
        pnlContentSach.setBounds(10, 100, 340, 320);

        lblFindBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFindBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_search_20px_1.png"))); // NOI18N
        lblFindBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFindBookMouseClicked(evt);
            }
        });
        pnlBook1.add(lblFindBook);
        lblFindBook.setBounds(300, 60, 30, 30);

        txtFindBook.setBackground(new java.awt.Color(240, 242, 245));
        txtFindBook.setBorder(null);
        pnlBook1.add(txtFindBook);
        txtFindBook.setBounds(50, 60, 240, 30);

        jSeparator1.setForeground(new java.awt.Color(0, 153, 255));
        pnlBook1.add(jSeparator1);
        jSeparator1.setBounds(50, 90, 240, 10);

        pnlBook1.add(comSach);
        comSach.setBounds(50, 30, 240, 30);

        jPanel22.setBackground(new java.awt.Color(240, 242, 245));

        btnThemSach.setBackground(new java.awt.Color(0, 204, 204));
        btnThemSach.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemSach.setForeground(new java.awt.Color(0, 51, 153));
        btnThemSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemSach.setText("THÊM");
        btnThemSach.setBorder(null);
        btnThemSach.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemSach.setPreferredSize(new java.awt.Dimension(79, 42));
        btnThemSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemSachMouseClicked(evt);
            }
        });
        jPanel22.add(btnThemSach);

        btnSuaSach.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaSach.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaSach.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaSach.setText("SỬA");
        btnSuaSach.setBorder(null);
        btnSuaSach.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaSach.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaSachMouseClicked(evt);
            }
        });
        jPanel22.add(btnSuaSach);

        btnXoaSach.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaSach.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaSach.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaSach.setText("XÓA");
        btnXoaSach.setBorder(null);
        btnXoaSach.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaSach.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSachMouseClicked(evt);
            }
        });
        jPanel22.add(btnXoaSach);

        btnLamMoiSach.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiSach.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiSach.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiSach.setText("MỚI");
        btnLamMoiSach.setBorder(null);
        btnLamMoiSach.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiSach.setPreferredSize(new java.awt.Dimension(79, 42));
        btnLamMoiSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiSachMouseClicked(evt);
            }
        });
        jPanel22.add(btnLamMoiSach);

        pnlBook1.add(jPanel22);
        jPanel22.setBounds(10, 430, 340, 60);

        btnRSSach.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach.setText("Xuất bản in");
        btnRSSach.setBorder(null);
        btnRSSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSachActionPerformed(evt);
            }
        });
        pnlBook1.add(btnRSSach);
        btnRSSach.setBounds(830, 30, 110, 29);

        panel_sach_tg_tl.addTab("SÁCH", null, pnlBook1, "");

        pnlType.setBackground(new java.awt.Color(240, 242, 245));
        pnlType.setLayout(null);

        tblTheLoai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "<html><b>STT</b></html>", "<html><b>Tên thể loại</b></html>"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTheLoai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblTheLoai.setFocusable(false);
        tblTheLoai.setRowHeight(23);
        tblTheLoai.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblTheLoai.getTableHeader().setResizingAllowed(false);
        tblTheLoai.getTableHeader().setReorderingAllowed(false);
        tblTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTheLoaiMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblTheLoai);

        pnlType.add(jScrollPane12);
        jScrollPane12.setBounds(460, 50, 480, 290);

        jPanel36.setBackground(new java.awt.Color(240, 242, 245));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("Tên thể loại");

        txtTenTL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenTL, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtTenTL, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pnlType.add(jPanel36);
        jPanel36.setBounds(40, 140, 360, 130);

        txtFindType.setBackground(new java.awt.Color(240, 242, 245));
        txtFindType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFindType.setBorder(null);
        pnlType.add(txtFindType);
        txtFindType.setBounds(90, 100, 240, 30);

        jSeparator10.setForeground(new java.awt.Color(0, 153, 255));
        pnlType.add(jSeparator10);
        jSeparator10.setBounds(90, 130, 240, 10);

        lblFindType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFindType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_search_20px_1.png"))); // NOI18N
        lblFindType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFindTypeMouseClicked(evt);
            }
        });
        pnlType.add(lblFindType);
        lblFindType.setBounds(340, 100, 30, 30);

        jPanel32.setBackground(new java.awt.Color(240, 242, 245));

        btnThemTL.setBackground(new java.awt.Color(0, 204, 204));
        btnThemTL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemTL.setForeground(new java.awt.Color(0, 51, 153));
        btnThemTL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemTL.setText("THÊM");
        btnThemTL.setBorder(null);
        btnThemTL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemTL.setPreferredSize(new java.awt.Dimension(79, 42));
        btnThemTL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemTLMouseClicked(evt);
            }
        });
        jPanel32.add(btnThemTL);

        btnSuaTL.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaTL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaTL.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaTL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaTL.setText("SỬA");
        btnSuaTL.setBorder(null);
        btnSuaTL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaTL.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaTL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaTLMouseClicked(evt);
            }
        });
        jPanel32.add(btnSuaTL);

        btnXoaTL.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaTL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaTL.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaTL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaTL.setText("XÓA");
        btnXoaTL.setBorder(null);
        btnXoaTL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaTL.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaTL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaTLMouseClicked(evt);
            }
        });
        jPanel32.add(btnXoaTL);

        btnLamMoiTL.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiTL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiTL.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiTL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiTL.setText("MỚI");
        btnLamMoiTL.setBorder(null);
        btnLamMoiTL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiTL.setPreferredSize(new java.awt.Dimension(79, 42));
        btnLamMoiTL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiTLMouseClicked(evt);
            }
        });
        jPanel32.add(btnLamMoiTL);

        pnlType.add(jPanel32);
        jPanel32.setBounds(40, 290, 350, 50);

        btnRSSach12.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach12.setText("Xuất bản in");
        btnRSSach12.setBorder(null);
        btnRSSach12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach12ActionPerformed(evt);
            }
        });
        pnlType.add(btnRSSach12);
        btnRSSach12.setBounds(830, 10, 110, 29);

        panel_sach_tg_tl.addTab("THỂ LOẠI", pnlType);

        pnlAuthor.setBackground(new java.awt.Color(240, 242, 245));
        pnlAuthor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlAuthor.setLayout(null);

        tblTacGia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "<html><b>STT</b></html>", "<html><b>Tên tác giả</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTacGia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblTacGia.setFocusable(false);
        tblTacGia.setRowHeight(23);
        tblTacGia.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblTacGia.getTableHeader().setResizingAllowed(false);
        tblTacGia.getTableHeader().setReorderingAllowed(false);
        tblTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTacGiaMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblTacGia);

        pnlAuthor.add(jScrollPane11);
        jScrollPane11.setBounds(450, 50, 490, 330);

        jPanel33.setBackground(new java.awt.Color(240, 242, 245));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Họ và tên lót");

        txtHoTacGia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtTenTacGia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Tên tác giả");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(txtHoTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(29, 29, 29))
        );

        pnlAuthor.add(jPanel33);
        jPanel33.setBounds(30, 130, 350, 180);

        txtFindAuthor.setBackground(new java.awt.Color(240, 242, 245));
        txtFindAuthor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFindAuthor.setBorder(null);
        txtFindAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindAuthorActionPerformed(evt);
            }
        });
        pnlAuthor.add(txtFindAuthor);
        txtFindAuthor.setBounds(80, 90, 240, 30);

        jSeparator9.setForeground(new java.awt.Color(0, 153, 255));
        pnlAuthor.add(jSeparator9);
        jSeparator9.setBounds(80, 120, 240, 10);

        lblFindAuthor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFindAuthor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_search_20px_1.png"))); // NOI18N
        lblFindAuthor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFindAuthorMouseClicked(evt);
            }
        });
        pnlAuthor.add(lblFindAuthor);
        lblFindAuthor.setBounds(330, 90, 30, 30);

        btnRSSach13.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach13.setText("Xuất bản in");
        btnRSSach13.setBorder(null);
        btnRSSach13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach13ActionPerformed(evt);
            }
        });
        pnlAuthor.add(btnRSSach13);
        btnRSSach13.setBounds(830, 10, 110, 29);

        jPanel35.setBackground(new java.awt.Color(240, 242, 245));

        btnThemTG.setBackground(new java.awt.Color(0, 204, 204));
        btnThemTG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemTG.setForeground(new java.awt.Color(0, 51, 153));
        btnThemTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemTG.setText("THÊM");
        btnThemTG.setBorder(null);
        btnThemTG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemTG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnThemTG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemTGMouseClicked(evt);
            }
        });

        btnSuaTG.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaTG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaTG.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaTG.setText("SỬA");
        btnSuaTG.setBorder(null);
        btnSuaTG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaTG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaTG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaTGMouseClicked(evt);
            }
        });

        btnXoaTG.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaTG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaTG.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaTG.setText("XÓA");
        btnXoaTG.setBorder(null);
        btnXoaTG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaTG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaTG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaTGMouseClicked(evt);
            }
        });

        btnLamMoiTG.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiTG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiTG.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiTG.setText("MỚI");
        btnLamMoiTG.setBorder(null);
        btnLamMoiTG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiTG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnLamMoiTG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiTGMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(btnThemTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnSuaTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnXoaTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnLamMoiTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlAuthor.add(jPanel35);
        jPanel35.setBounds(30, 330, 340, 47);

        panel_sach_tg_tl.addTab("TÁC GIẢ", pnlAuthor);

        javax.swing.GroupLayout pnlBookLayout = new javax.swing.GroupLayout(pnlBook);
        pnlBook.setLayout(pnlBookLayout);
        pnlBookLayout.setHorizontalGroup(
            pnlBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookLayout.createSequentialGroup()
                .addComponent(panel_sach_tg_tl, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlBookLayout.setVerticalGroup(
            pnlBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_sach_tg_tl, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        tabbedMain.addTab("SÁCH", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_book_40px_1.png")), pnlBook); // NOI18N

        pnlProvider.setBackground(new java.awt.Color(240, 242, 245));
        pnlProvider.setForeground(new java.awt.Color(102, 255, 51));
        pnlProvider.setEnabled(false);
        pnlProvider.setLayout(null);

        tblNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>", "<html><b>Tên nhà cung cấp</b></html>", "<html><b>Địa chỉ</b></html>", "<html><b>Điện thoại</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNCC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblNCC.setFocusable(false);
        tblNCC.setRowHeight(23);
        tblNCC.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblNCC.getTableHeader().setResizingAllowed(false);
        tblNCC.getTableHeader().setReorderingAllowed(false);
        tblNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNCCMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblNCC);

        pnlProvider.add(jScrollPane8);
        jScrollPane8.setBounds(430, 50, 470, 370);

        jPanel19.setBackground(new java.awt.Color(240, 242, 245));
        jPanel19.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setText("Tên NCC");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setText("Địa chỉ");
        jLabel77.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel78.setText("Điện thoại");
        jLabel78.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtTenNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenNCC.setPreferredSize(new java.awt.Dimension(7, 40));

        txtDiaChiNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDiaChiNCC.setPreferredSize(new java.awt.Dimension(7, 40));

        txtSDTNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSDTNCC.setPreferredSize(new java.awt.Dimension(7, 40));
        txtSDTNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(txtDiaChiNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSDTNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addGap(23, 23, 23)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pnlProvider.add(jPanel19);
        jPanel19.setBounds(40, 150, 350, 200);

        txtFindProvider.setBackground(new java.awt.Color(240, 242, 245));
        txtFindProvider.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFindProvider.setBorder(null);
        pnlProvider.add(txtFindProvider);
        txtFindProvider.setBounds(90, 90, 240, 30);

        jSeparator2.setForeground(new java.awt.Color(0, 153, 255));
        pnlProvider.add(jSeparator2);
        jSeparator2.setBounds(90, 120, 240, 10);

        lblFindProvider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFindProvider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_search_20px_1.png"))); // NOI18N
        lblFindProvider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFindProviderMouseClicked(evt);
            }
        });
        pnlProvider.add(lblFindProvider);
        lblFindProvider.setBounds(340, 90, 30, 30);

        jPanel24.setBackground(new java.awt.Color(240, 242, 245));

        btnThemNCC.setBackground(new java.awt.Color(0, 204, 204));
        btnThemNCC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemNCC.setForeground(new java.awt.Color(0, 51, 153));
        btnThemNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemNCC.setText("THÊM");
        btnThemNCC.setBorder(null);
        btnThemNCC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemNCC.setPreferredSize(new java.awt.Dimension(79, 42));
        btnThemNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNCCMouseClicked(evt);
            }
        });
        jPanel24.add(btnThemNCC);

        btnSuaNCC.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaNCC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaNCC.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaNCC.setText("SỬA");
        btnSuaNCC.setBorder(null);
        btnSuaNCC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaNCC.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaNCCMouseClicked(evt);
            }
        });
        jPanel24.add(btnSuaNCC);

        btnXoaNCC.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaNCC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaNCC.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaNCC.setText("XÓA");
        btnXoaNCC.setBorder(null);
        btnXoaNCC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaNCC.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaNCCMouseClicked(evt);
            }
        });
        jPanel24.add(btnXoaNCC);

        btnLamMoiNCC.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiNCC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiNCC.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiNCC.setText("MỚI");
        btnLamMoiNCC.setBorder(null);
        btnLamMoiNCC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiNCC.setPreferredSize(new java.awt.Dimension(79, 42));
        btnLamMoiNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiNCCMouseClicked(evt);
            }
        });
        jPanel24.add(btnLamMoiNCC);

        pnlProvider.add(jPanel24);
        jPanel24.setBounds(40, 370, 350, 52);

        btnRSSach1.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach1.setText("Xuất bản in");
        btnRSSach1.setBorder(null);
        btnRSSach1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach1ActionPerformed(evt);
            }
        });
        pnlProvider.add(btnRSSach1);
        btnRSSach1.setBounds(790, 10, 110, 29);

        comNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pnlProvider.add(comNCC);
        comNCC.setBounds(90, 50, 240, 30);

        tabbedMain.addTab("NHÀ CUNG CẤP", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_factory_40px_1.png")), pnlProvider, ""); // NOI18N

        pnlPublisher.setBackground(new java.awt.Color(240, 242, 245));
        pnlPublisher.setEnabled(false);
        pnlPublisher.setLayout(null);

        tblNXB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNXB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>", "<html><b>Tên nhà xuất bản</b></html>", "<html><b>Địa chỉ</b></html>", "<html><b>Điện thoại</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNXB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblNXB.setFocusable(false);
        tblNXB.setRowHeight(23);
        tblNXB.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblNXB.getTableHeader().setResizingAllowed(false);
        tblNXB.getTableHeader().setReorderingAllowed(false);
        tblNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNXBMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblNXB);

        pnlPublisher.add(jScrollPane9);
        jScrollPane9.setBounds(400, 50, 480, 350);

        jPanel21.setBackground(new java.awt.Color(240, 242, 245));
        jPanel21.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setText("Tên NXB");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel81.setText("Địa chỉ");
        jLabel81.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel82.setText("Điện thoại");
        jLabel82.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtTenNXB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenNXB.setPreferredSize(new java.awt.Dimension(7, 40));

        txtDiaChiNXB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDiaChiNXB.setPreferredSize(new java.awt.Dimension(7, 40));

        txtSDTNXB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSDTNXB.setPreferredSize(new java.awt.Dimension(7, 40));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenNXB, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(txtDiaChiNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSDTNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addGap(23, 23, 23)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChiNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlPublisher.add(jPanel21);
        jPanel21.setBounds(50, 140, 320, 190);

        txtFindPublisher.setBackground(new java.awt.Color(240, 242, 245));
        txtFindPublisher.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFindPublisher.setBorder(null);
        pnlPublisher.add(txtFindPublisher);
        txtFindPublisher.setBounds(90, 80, 240, 30);

        comNXB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pnlPublisher.add(comNXB);
        comNXB.setBounds(90, 50, 240, 30);

        jSeparator3.setForeground(new java.awt.Color(0, 153, 255));
        pnlPublisher.add(jSeparator3);
        jSeparator3.setBounds(90, 110, 240, 10);

        jlblFindPublisher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblFindPublisher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_search_20px_1.png"))); // NOI18N
        jlblFindPublisher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblFindPublisherMouseClicked(evt);
            }
        });
        pnlPublisher.add(jlblFindPublisher);
        jlblFindPublisher.setBounds(340, 80, 30, 30);
        pnlPublisher.add(jLabel87);
        jLabel87.setBounds(310, 70, 40, 30);

        jPanel26.setBackground(new java.awt.Color(240, 242, 245));

        btnThemNXB.setBackground(new java.awt.Color(0, 204, 204));
        btnThemNXB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemNXB.setForeground(new java.awt.Color(0, 51, 153));
        btnThemNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemNXB.setText("THÊM");
        btnThemNXB.setBorder(null);
        btnThemNXB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemNXB.setPreferredSize(new java.awt.Dimension(79, 42));
        btnThemNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNXBMouseClicked(evt);
            }
        });
        jPanel26.add(btnThemNXB);

        btnSuaNXB.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaNXB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaNXB.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaNXB.setText("SỬA");
        btnSuaNXB.setBorder(null);
        btnSuaNXB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaNXB.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaNXBMouseClicked(evt);
            }
        });
        jPanel26.add(btnSuaNXB);

        btnXoaNXB.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaNXB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaNXB.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaNXB.setText("XÓA");
        btnXoaNXB.setBorder(null);
        btnXoaNXB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaNXB.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaNXBMouseClicked(evt);
            }
        });
        jPanel26.add(btnXoaNXB);

        btnLamMoiNXB.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiNXB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiNXB.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiNXB.setText("MỚI");
        btnLamMoiNXB.setBorder(null);
        btnLamMoiNXB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiNXB.setPreferredSize(new java.awt.Dimension(79, 42));
        btnLamMoiNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiNXBMouseClicked(evt);
            }
        });
        jPanel26.add(btnLamMoiNXB);

        pnlPublisher.add(jPanel26);
        jPanel26.setBounds(40, 350, 340, 50);

        btnRSSach2.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach2.setText("Xuất bản in");
        btnRSSach2.setBorder(null);
        btnRSSach2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach2ActionPerformed(evt);
            }
        });
        pnlPublisher.add(btnRSSach2);
        btnRSSach2.setBounds(770, 10, 110, 29);

        tabbedMain.addTab("NHÀ XUẤT BẢN", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_apartment_40px_1.png")), pnlPublisher); // NOI18N

        pnlBill.setEnabled(false);
        pnlBill.setLayout(null);

        jPanel10.setBackground(new java.awt.Color(240, 242, 245));
        jPanel10.setLayout(null);

        tblHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>","<html><b>Mã hóa đơn</b></html>", "<html><b>Khách hàng</b></html>", "<html><b>Ngày lập</b></html>", "<html><b>Tổng tiền</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblHoaDon.setFocusable(false);
        tblHoaDon.setRowHeight(23);
        tblHoaDon.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        jPanel10.add(jScrollPane3);
        jScrollPane3.setBounds(380, 60, 530, 380);

        jPanel16.setBackground(new java.awt.Color(240, 242, 245));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Mã hóa đơn");
        jLabel12.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Khách hàng");
        jLabel19.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Ngày lập");
        jLabel20.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Tổng tiền (VNĐ)");
        jLabel21.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtMaHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMaHD.setEnabled(false);

        txtTongTienHD.setEditable(false);
        txtTongTienHD.setBackground(new java.awt.Color(240, 242, 245));
        txtTongTienHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTongTienHD.setEnabled(false);

        dateNgayLapHD.setDateFormatString("dd/MM/yyyy");

        cbKhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongTienHD, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateNgayLapHD, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(cbKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateNgayLapHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTienHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel10.add(jPanel16);
        jPanel16.setBounds(20, 110, 350, 240);

        lblFindBill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFindBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_search_20px_1.png"))); // NOI18N
        lblFindBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFindBillMouseClicked(evt);
            }
        });
        jPanel10.add(lblFindBill);
        lblFindBill.setBounds(310, 70, 30, 30);

        comHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm kiếm theo khách hàng", "Tìm kiếm theo ngày lập" }));
        comHoaDon.setBorder(null);
        jPanel10.add(comHoaDon);
        comHoaDon.setBounds(60, 40, 240, 30);

        jSeparator13.setForeground(new java.awt.Color(0, 153, 255));
        jPanel10.add(jSeparator13);
        jSeparator13.setBounds(60, 100, 240, 10);

        txtFindBill.setBackground(new java.awt.Color(240, 242, 245));
        txtFindBill.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFindBill.setBorder(null);
        jPanel10.add(txtFindBill);
        txtFindBill.setBounds(60, 70, 240, 30);

        jPanel27.setBackground(new java.awt.Color(240, 242, 245));

        btnThemHD1.setBackground(new java.awt.Color(0, 204, 204));
        btnThemHD1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemHD1.setForeground(new java.awt.Color(0, 51, 153));
        btnThemHD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemHD1.setText("THÊM");
        btnThemHD1.setBorder(null);
        btnThemHD1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemHD1.setPreferredSize(new java.awt.Dimension(80, 40));
        btnThemHD1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemHD1MouseClicked(evt);
            }
        });
        jPanel27.add(btnThemHD1);

        btnLamMoiHD.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiHD.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiHD.setText("MỚI");
        btnLamMoiHD.setBorder(null);
        btnLamMoiHD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiHD.setPreferredSize(new java.awt.Dimension(80, 40));
        btnLamMoiHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiHDMouseClicked(evt);
            }
        });
        jPanel27.add(btnLamMoiHD);

        btnInHD.setBackground(new java.awt.Color(0, 204, 204));
        btnInHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnInHD.setForeground(new java.awt.Color(0, 51, 153));
        btnInHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnInHD.setText("IN");
        btnInHD.setBorder(null);
        btnInHD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInHD.setPreferredSize(new java.awt.Dimension(80, 40));
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });
        jPanel27.add(btnInHD);

        btnCTHD.setBackground(new java.awt.Color(0, 204, 204));
        btnCTHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCTHD.setForeground(new java.awt.Color(0, 51, 153));
        btnCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_more_details_20px_2.png"))); // NOI18N
        btnCTHD.setText("CHI TIẾT");
        btnCTHD.setBorder(null);
        btnCTHD.setPreferredSize(new java.awt.Dimension(80, 40));
        btnCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCTHDActionPerformed(evt);
            }
        });
        jPanel27.add(btnCTHD);

        jPanel10.add(jPanel27);
        jPanel27.setBounds(20, 390, 350, 50);

        btnRSSach3.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach3.setText("Xuất bản in");
        btnRSSach3.setBorder(null);
        btnRSSach3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach3ActionPerformed(evt);
            }
        });
        jPanel10.add(btnRSSach3);
        btnRSSach3.setBounds(800, 20, 110, 29);

        pnlBill.add(jPanel10);
        jPanel10.setBounds(0, 0, 970, 530);

        tabbedMain.addTab("HÓA ĐƠN", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_bill_40px_4.png")), pnlBill); // NOI18N

        pnlImport.setEnabled(false);
        pnlImport.setLayout(null);

        jPanel9.setBackground(new java.awt.Color(240, 242, 245));
        jPanel9.setLayout(null);

        tblPhieuNhap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>","<html><b>Mã phiếu nhập</b></html>", "<html><b>Nhà cung cấp</b></html>", "<html><b>Ngày nhập</b></html>", "<html><b>Tổng tiền</b></html>"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblPhieuNhap.setFocusable(false);
        tblPhieuNhap.setRowHeight(23);
        tblPhieuNhap.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblPhieuNhap.getTableHeader().setResizingAllowed(false);
        tblPhieuNhap.getTableHeader().setReorderingAllowed(false);
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblPhieuNhap);

        jPanel9.add(jScrollPane15);
        jScrollPane15.setBounds(370, 50, 530, 400);

        jPanel17.setBackground(new java.awt.Color(240, 242, 245));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Mã phiếu nhập");
        jLabel50.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel95.setText("Nhà cung cấp");
        jLabel95.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel96.setText("Ngày nhập");
        jLabel96.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel97.setText("Tổng tiền (VNĐ)");
        jLabel97.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtMaPhieuNhap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTongTienPN.setEditable(false);
        txtTongTienPN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTongTienPN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtTongTienPN.setEnabled(false);

        dateNgayNhap.setDateFormatString("dd/MM/yyyy");
        dateNgayNhap.setEnabled(false);

        cbNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongTienPN, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(cbNCC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTienPN, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        JTextFieldDateEditor editor5 = (JTextFieldDateEditor) dateNgayNhap.getDateEditor();
        editor5.setEditable(false);

        jPanel9.add(jPanel17);
        jPanel17.setBounds(20, 150, 340, 230);

        comPhieuNhap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comPhieuNhap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm kiếm theo nhà cung cấp", "Tìm kiếm theo ngày lập" }));
        jPanel9.add(comPhieuNhap);
        comPhieuNhap.setBounds(60, 40, 240, 30);

        jSeparator4.setForeground(new java.awt.Color(0, 153, 255));
        jPanel9.add(jSeparator4);
        jSeparator4.setBounds(60, 100, 240, 10);

        txtFindImport.setBackground(new java.awt.Color(240, 242, 245));
        txtFindImport.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFindImport.setBorder(null);
        jPanel9.add(txtFindImport);
        txtFindImport.setBounds(60, 70, 240, 30);

        lblFindImport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFindImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_search_20px_1.png"))); // NOI18N
        lblFindImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFindImportMouseClicked(evt);
            }
        });
        jPanel9.add(lblFindImport);
        lblFindImport.setBounds(310, 70, 30, 30);

        jPanel34.setBackground(new java.awt.Color(240, 242, 245));

        btnThemPN.setBackground(new java.awt.Color(0, 204, 204));
        btnThemPN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemPN.setForeground(new java.awt.Color(0, 51, 153));
        btnThemPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemPN.setText("THÊM");
        btnThemPN.setBorder(null);
        btnThemPN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemPN.setPreferredSize(new java.awt.Dimension(80, 40));
        btnThemPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemPNMouseClicked(evt);
            }
        });
        jPanel34.add(btnThemPN);

        btnLamMoiPN.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiPN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiPN.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiPN.setText("MỚI");
        btnLamMoiPN.setBorder(null);
        btnLamMoiPN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiPN.setPreferredSize(new java.awt.Dimension(80, 40));
        btnLamMoiPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiPNMouseClicked(evt);
            }
        });
        jPanel34.add(btnLamMoiPN);

        btnInPN.setBackground(new java.awt.Color(0, 204, 204));
        btnInPN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnInPN.setForeground(new java.awt.Color(0, 51, 153));
        btnInPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnInPN.setText("IN");
        btnInPN.setBorder(null);
        btnInPN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInPN.setPreferredSize(new java.awt.Dimension(80, 40));
        btnInPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPNActionPerformed(evt);
            }
        });
        jPanel34.add(btnInPN);

        btnCTPN.setBackground(new java.awt.Color(0, 204, 204));
        btnCTPN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCTPN.setForeground(new java.awt.Color(0, 51, 153));
        btnCTPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_more_details_20px_2.png"))); // NOI18N
        btnCTPN.setText("CHI TIẾT");
        btnCTPN.setBorder(null);
        btnCTPN.setPreferredSize(new java.awt.Dimension(80, 40));
        btnCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCTPNActionPerformed(evt);
            }
        });
        jPanel34.add(btnCTPN);

        jPanel9.add(jPanel34);
        jPanel34.setBounds(20, 400, 340, 50);

        btnRSSach4.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach4.setText("Xuất bản in");
        btnRSSach4.setBorder(null);
        btnRSSach4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach4ActionPerformed(evt);
            }
        });
        jPanel9.add(btnRSSach4);
        btnRSSach4.setBounds(790, 10, 110, 29);

        pnlImport.add(jPanel9);
        jPanel9.setBounds(0, 0, 970, 530);

        tabbedMain.addTab("NHẬP SÁCH", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_40px_6.png")), pnlImport, ""); // NOI18N

        pnlCustomer.setBackground(new java.awt.Color(240, 242, 245));
        pnlCustomer.setEnabled(false);
        pnlCustomer.setLayout(null);

        tblKhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>", "<html><b>Họ và tên lót</b></html>", "<html><b>Tên khách hàng</b></html>", "<html><b>Giới tính</b></html>", "<html><b>Ngày sinh</b></html>", "<html><b>Số điện thoại</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblKhachHang.setFocusable(false);
        tblKhachHang.setRowHeight(23);
        tblKhachHang.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblKhachHang.getTableHeader().setResizingAllowed(false);
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblKhachHang);

        pnlCustomer.add(jScrollPane5);
        jScrollPane5.setBounds(380, 50, 540, 410);

        jPanel18.setBackground(new java.awt.Color(240, 242, 245));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Họ và tên lót");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Tên khách hàng");
        jLabel30.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Giới tính");
        jLabel31.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Ngày sinh");
        jLabel32.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Số điện thoại");
        jLabel33.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtHOKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtSDTKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtGioiTinhKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGioiTinhKH.setMaximumRowCount(9);
        txtGioiTinhKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        txtGioiTinhKH.setSelectedIndex(-1);

        txtTenKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenKH.setPreferredSize(new java.awt.Dimension(7, 20));

        dateNgaySinhKH.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(txtHOKH, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDTKH)
                    .addComponent(txtGioiTinhKH, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateNgaySinhKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtHOKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGioiTinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(dateNgaySinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

        pnlCustomer.add(jPanel18);
        jPanel18.setBounds(10, 100, 360, 290);

        jSeparator6.setForeground(new java.awt.Color(0, 153, 255));
        pnlCustomer.add(jSeparator6);
        jSeparator6.setBounds(70, 80, 240, 10);

        comKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm kiếm theo tên khách hàng", "Tìm kiếm theo số điện thoại" }));
        pnlCustomer.add(comKH);
        comKH.setBounds(70, 20, 240, 30);

        txtFindCustomer.setBackground(new java.awt.Color(240, 242, 245));
        txtFindCustomer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFindCustomer.setBorder(null);
        pnlCustomer.add(txtFindCustomer);
        txtFindCustomer.setBounds(70, 50, 240, 30);

        lblFindCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFindCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_search_20px_1.png"))); // NOI18N
        lblFindCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFindCustomerMouseClicked(evt);
            }
        });
        pnlCustomer.add(lblFindCustomer);
        lblFindCustomer.setBounds(320, 50, 30, 30);

        jPanel37.setBackground(new java.awt.Color(240, 242, 245));

        btnThemKH.setBackground(new java.awt.Color(0, 204, 204));
        btnThemKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemKH.setForeground(new java.awt.Color(0, 51, 153));
        btnThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemKH.setText("THÊM");
        btnThemKH.setBorder(null);
        btnThemKH.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemKH.setPreferredSize(new java.awt.Dimension(79, 42));
        btnThemKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemKHMouseClicked(evt);
            }
        });
        jPanel37.add(btnThemKH);

        btnSuaKH.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaKH.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaKH.setText("SỬA");
        btnSuaKH.setBorder(null);
        btnSuaKH.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaKH.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaKHMouseClicked(evt);
            }
        });
        jPanel37.add(btnSuaKH);

        btnXoaKH.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaKH.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaKH.setText("XÓA");
        btnXoaKH.setBorder(null);
        btnXoaKH.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaKH.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaKHMouseClicked(evt);
            }
        });
        jPanel37.add(btnXoaKH);

        btnLamMoiKH.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiKH.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiKH.setText("MỚI");
        btnLamMoiKH.setBorder(null);
        btnLamMoiKH.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiKH.setPreferredSize(new java.awt.Dimension(79, 42));
        btnLamMoiKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiKHMouseClicked(evt);
            }
        });
        jPanel37.add(btnLamMoiKH);

        pnlCustomer.add(jPanel37);
        jPanel37.setBounds(20, 410, 340, 50);

        btnRSSach5.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach5.setText("Xuất bản in");
        btnRSSach5.setBorder(null);
        btnRSSach5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach5ActionPerformed(evt);
            }
        });
        pnlCustomer.add(btnRSSach5);
        btnRSSach5.setBounds(810, 10, 110, 29);

        tabbedMain.addTab("KHÁCH HÀNG", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_customer_40px_1.png")), pnlCustomer); // NOI18N

        pnlEmployee.setBackground(new java.awt.Color(240, 242, 245));
        pnlEmployee.setEnabled(false);
        pnlEmployee.setLayout(null);

        tblNhanVien.setAutoCreateRowSorter(true);
        tblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>", "<html><b>Họ và tên lót</b></html>", "<html><b>Tên nhân viên</b></html>", "<html><b>Giới tính</b></html>", "<html><b>Ngày sinh</b></html>", "<html><b>Số điện thoại</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblNhanVien.setFocusable(false);
        tblNhanVien.setRowHeight(23);
        tblNhanVien.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblNhanVien.getTableHeader().setResizingAllowed(false);
        tblNhanVien.getTableHeader().setReorderingAllowed(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblNhanVien);

        pnlEmployee.add(jScrollPane6);
        jScrollPane6.setBounds(380, 60, 550, 400);

        jPanel25.setBackground(new java.awt.Color(240, 242, 245));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Họ và tên lót");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Tên nhân viên");
        jLabel36.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Giới tính");
        jLabel37.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Ngày sinh");
        jLabel38.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Số điện thoại");
        jLabel39.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtHONV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtSDTNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtGioiTinhNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGioiTinhNV.setMaximumRowCount(9);
        txtGioiTinhNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        txtGioiTinhNV.setSelectedIndex(-1);

        txtTenNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenNV.setPreferredSize(new java.awt.Dimension(7, 20));

        dateNgaySinhNV.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDTNV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateNgaySinhNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHONV, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGioiTinhNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtHONV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txtGioiTinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateNgaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

        dateNgaySinhNV.setDateFormatString("dd/MM/yyyy");
        JTextFieldDateEditor editor7 = (JTextFieldDateEditor) dateNgayNhap.getDateEditor();
        editor7.setEditable(false);

        pnlEmployee.add(jPanel25);
        jPanel25.setBounds(10, 100, 350, 290);

        comNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm kiếm theo tên nhân viên", "Tìm kiếm theo số điện thoại" }));
        pnlEmployee.add(comNV);
        comNV.setBounds(70, 20, 240, 30);

        txtFindEmployee.setBackground(new java.awt.Color(240, 242, 245));
        txtFindEmployee.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFindEmployee.setBorder(null);
        pnlEmployee.add(txtFindEmployee);
        txtFindEmployee.setBounds(70, 50, 240, 30);

        jSeparator7.setForeground(new java.awt.Color(0, 153, 255));
        pnlEmployee.add(jSeparator7);
        jSeparator7.setBounds(70, 80, 240, 10);

        lblFindEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFindEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_search_20px_1.png"))); // NOI18N
        lblFindEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFindEmployeeMouseClicked(evt);
            }
        });
        pnlEmployee.add(lblFindEmployee);
        lblFindEmployee.setBounds(320, 50, 30, 30);

        jPanel38.setBackground(new java.awt.Color(240, 242, 245));

        btnThemNV.setBackground(new java.awt.Color(0, 204, 204));
        btnThemNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemNV.setForeground(new java.awt.Color(0, 51, 153));
        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemNV.setText("THÊM");
        btnThemNV.setBorder(null);
        btnThemNV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemNV.setPreferredSize(new java.awt.Dimension(79, 42));
        btnThemNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNVMouseClicked(evt);
            }
        });
        jPanel38.add(btnThemNV);

        btnSuaNV.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaNV.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaNV.setText("SỬA");
        btnSuaNV.setBorder(null);
        btnSuaNV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaNV.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaNVMouseClicked(evt);
            }
        });
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });
        jPanel38.add(btnSuaNV);

        btnXoaNV.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaNV.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaNV.setText("XÓA");
        btnXoaNV.setBorder(null);
        btnXoaNV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaNV.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaNVMouseClicked(evt);
            }
        });
        jPanel38.add(btnXoaNV);

        btnLamMoiNV.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiNV.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiNV.setText("MỚI");
        btnLamMoiNV.setBorder(null);
        btnLamMoiNV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiNV.setPreferredSize(new java.awt.Dimension(79, 42));
        btnLamMoiNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiNVMouseClicked(evt);
            }
        });
        jPanel38.add(btnLamMoiNV);

        pnlEmployee.add(jPanel38);
        jPanel38.setBounds(20, 410, 340, 70);

        btnRSSach6.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach6.setText("Xuất bản in");
        btnRSSach6.setBorder(null);
        btnRSSach6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach6ActionPerformed(evt);
            }
        });
        pnlEmployee.add(btnRSSach6);
        btnRSSach6.setBounds(820, 20, 110, 29);

        tabbedMain.addTab("NHÂN VIÊN", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_worker_40px_1.png")), pnlEmployee); // NOI18N

        pnlAccount.setBackground(new java.awt.Color(240, 242, 245));
        pnlAccount.setEnabled(false);
        pnlAccount.setLayout(null);

        jLabel40.setBackground(new java.awt.Color(240, 242, 245));
        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("KHOÁ TÀI KHOẢN");
        pnlAccount.add(jLabel40);
        jLabel40.setBounds(520, 40, 430, 40);

        jPanel1.setOpaque(false);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 204, 102));
        jLabel41.setText("Nhân viên");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 204, 102));
        jLabel42.setText("Mật khẩu");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 204, 102));
        jLabel43.setText("Tên tài khoản");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 204, 102));
        jLabel44.setText("Quyền");

        cbRoll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbRoll.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QUẢN LÝ BÁN SÁCH", "QUẢN LÝ NHẬP SÁCH" }));

        cbEmployee.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbEmployee, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword)
                    .addComponent(txtUser)
                    .addComponent(cbRoll, 0, 222, Short.MAX_VALUE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(cbRoll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pnlAccount.add(jPanel1);
        jPanel1.setBounds(20, 80, 470, 250);

        btnTaoTK.setBackground(new java.awt.Color(51, 255, 255));
        btnTaoTK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTaoTK.setForeground(new java.awt.Color(0, 0, 255));
        btnTaoTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_create_30px_3.png"))); // NOI18N
        btnTaoTK.setText("TẠO");
        btnTaoTK.setBorder(null);
        btnTaoTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoTKMouseClicked(evt);
            }
        });
        pnlAccount.add(btnTaoTK);
        btnTaoTK.setBounds(200, 370, 94, 46);

        txtMsg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMsg.setForeground(new java.awt.Color(255, 0, 0));
        txtMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlAccount.add(txtMsg);
        txtMsg.setBounds(100, 320, 300, 40);

        jLabel49.setBackground(new java.awt.Color(240, 242, 245));
        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("TẠO TÀI KHOẢN");
        pnlAccount.add(jLabel49);
        jLabel49.setBounds(100, 40, 300, 40);

        jSeparator5.setForeground(new java.awt.Color(0, 102, 255));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlAccount.add(jSeparator5);
        jSeparator5.setBounds(490, 0, 10, 530);

        tblAccount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><b>STT</b></html>","<html><b>Nhân viên</b></html>", "<html><b>Tên tài khoản</b></html>",  "<html><b>Quyền</b></html>"

            }
        ));
        tblAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblAccount.setFocusable(false);
        tblAccount.setRowHeight(23);
        tblAccount.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblAccount.getTableHeader().setResizingAllowed(false);
        tblAccount.getTableHeader().setReorderingAllowed(false);
        tblAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAccountMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblAccount);

        pnlAccount.add(jScrollPane13);
        jScrollPane13.setBounds(530, 90, 410, 310);

        btnLock.setBackground(new java.awt.Color(0, 204, 204));
        btnLock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLock.setForeground(new java.awt.Color(0, 51, 153));
        btnLock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_lock_error_30px_1.png"))); // NOI18N
        btnLock.setText("KHOÁ");
        btnLock.setBorder(null);
        btnLock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLock.setPreferredSize(new java.awt.Dimension(79, 42));
        btnLock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLockMouseClicked(evt);
            }
        });
        pnlAccount.add(btnLock);
        btnLock.setBounds(740, 470, 110, 42);

        btnDeleteAccount.setBackground(new java.awt.Color(0, 204, 204));
        btnDeleteAccount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteAccount.setForeground(new java.awt.Color(0, 51, 153));
        btnDeleteAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnDeleteAccount.setText("XÓA");
        btnDeleteAccount.setBorder(null);
        btnDeleteAccount.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDeleteAccount.setPreferredSize(new java.awt.Dimension(79, 42));
        btnDeleteAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteAccountMouseClicked(evt);
            }
        });
        pnlAccount.add(btnDeleteAccount);
        btnDeleteAccount.setBounds(620, 420, 110, 42);

        btnUnLock.setBackground(new java.awt.Color(0, 204, 204));
        btnUnLock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUnLock.setForeground(new java.awt.Color(0, 51, 153));
        btnUnLock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_unlock_30px.png"))); // NOI18N
        btnUnLock.setText("MỞ KHOÁ");
        btnUnLock.setBorder(null);
        btnUnLock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUnLock.setPreferredSize(new java.awt.Dimension(79, 42));
        btnUnLock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUnLockMouseClicked(evt);
            }
        });
        pnlAccount.add(btnUnLock);
        btnUnLock.setBounds(620, 470, 110, 42);

        btnRSSach9.setBackground(new java.awt.Color(0, 204, 204));
        btnRSSach9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRSSach9.setForeground(new java.awt.Color(0, 51, 153));
        btnRSSach9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach9.setText("XUẤT BẢN IN");
        btnRSSach9.setBorder(null);
        btnRSSach9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach9ActionPerformed(evt);
            }
        });
        pnlAccount.add(btnRSSach9);
        btnRSSach9.setBounds(740, 420, 110, 40);

        tabbedMain.addTab("TÀI KHOẢN", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_test_account_40px_1.png")), pnlAccount); // NOI18N

        pnlStatistics.setBackground(new java.awt.Color(240, 242, 245));
        pnlStatistics.setEnabled(false);
        pnlStatistics.setLayout(null);

        jSeparator8.setForeground(new java.awt.Color(0, 102, 255));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jSeparator8.setPreferredSize(new java.awt.Dimension(500, 500));
        pnlStatistics.add(jSeparator8);
        jSeparator8.setBounds(490, 0, 10, 520);

        jPanel29.setBackground(new java.awt.Color(240, 242, 245));

        tblTK_DT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTK_DT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>","<html><b>Mã hóa đơn</b></html>", "<html><b>Nhân viên</b></html>", "<html><b>Khách hàng</b></html>", "<html><b>Tổng tiền</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTK_DT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblTK_DT.setFocusable(false);
        tblTK_DT.setRowHeight(23);
        tblTK_DT.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblTK_DT.getTableHeader().setResizingAllowed(false);
        tblTK_DT.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tblTK_DT);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Tổng doanh thu (VNĐ):");

        txtTK_DT.setEnabled(false);

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 102, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("DOANH THU");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText("Từ ngày");

        dateTuNgayDT.setDateFormatString("dd/MM/yyyy");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setText("Đến ngày");

        dateDenNgayDT.setDateFormatString("dd/MM/yyyy");

        btnTKDT.setBackground(new java.awt.Color(0, 255, 102));
        btnTKDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pencil_20px.png"))); // NOI18N
        btnTKDT.setBorder(null);
        btnTKDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKDTActionPerformed(evt);
            }
        });

        btnRSSach8.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach8.setText("Xuất bản in");
        btnRSSach8.setBorder(null);
        btnRSSach8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateTuNgayDT, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateDenNgayDT, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTKDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTK_DT, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGap(25, 25, 25))))
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRSSach8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dateDenNgayDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateTuNgayDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTKDT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRSSach8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTK_DT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlStatistics.add(jPanel29);
        jPanel29.setBounds(500, 40, 460, 430);

        jPanel30.setBackground(new java.awt.Color(240, 242, 245));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 102, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("SỐ LƯỢNG SÁCH ĐÃ BÁN");

        tblTK_SL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTK_SL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>","<html><b>Sách</b></html>", "<html><b>Số lượng bán</b></html>", "<html><b>Thành tiền</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTK_SL.setFocusable(false);
        tblTK_SL.setRowHeight(23);
        tblTK_SL.getTableHeader().setResizingAllowed(false);
        tblTK_SL.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(tblTK_SL);

        lblCheckSL.setBackground(new java.awt.Color(51, 255, 51));
        lblCheckSL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCheckSL.setOpaque(true);

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("Từ ngày");

        dateTuNgaySL.setDateFormatString("dd/MM/yyyy");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("Đến ngày");

        dateDenNgaySL.setDateFormatString("dd/MM/yyyy");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Tổng số lượng:");

        txtTK_SL.setEnabled(false);

        btnTKSL.setBackground(new java.awt.Color(0, 255, 102));
        btnTKSL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pencil_20px.png"))); // NOI18N
        btnTKSL.setBorder(null);
        btnTKSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKSLActionPerformed(evt);
            }
        });

        btnRSSach7.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach7.setText("Xuất bản in");
        btnRSSach7.setBorder(null);
        btnRSSach7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(btnRSSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(txtTK_SL, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateTuNgaySL, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateDenNgaySL, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTKSL, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)))
                .addComponent(lblCheckSL)
                .addGap(0, 0, 0))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(lblCheckSL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateDenNgaySL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTKSL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateTuNgaySL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTK_SL, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRSSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pnlStatistics.add(jPanel30);
        jPanel30.setBounds(10, 40, 470, 430);

        tabbedMain.addTab("THỐNG KÊ", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_account_40px_2.png")), pnlStatistics); // NOI18N

        pnlDiscount.setBackground(new java.awt.Color(240, 242, 245));
        pnlDiscount.setEnabled(false);

        jSeparator12.setForeground(new java.awt.Color(102, 102, 255));
        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel14.setBackground(new java.awt.Color(240, 242, 245));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText("Tên chương trình");

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel84.setText("Ngày kết thúc");

        txtTenCT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        dateNgayBD.setDateFormatString("dd/MM/yyyy");

        dateNgayKT.setDateFormatString("dd/MM/yyyy");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Ngày bắt đầu");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setText("Ngày kết thúc");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenCT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(dateNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76)
                            .addComponent(txtTenCT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(dateNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69))
                .addGap(77, 77, 77)
                .addComponent(jLabel84)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dateNgayBD.setDateFormatString("dd/MM/yyyy");
        dateNgayKT.setDateFormatString("dd/MM/yyyy");

        jPanel15.setBackground(new java.awt.Color(240, 242, 245));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel88.setText("Tên chương trình");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setText("Tên sách");

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel91.setText("Chiết khấu (%)");

        txtChietKhau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cbSach_GG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cbGG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtChietKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(cbSach_GG, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbGG, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(cbGG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(cbSach_GG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel39.setBackground(new java.awt.Color(240, 242, 245));

        btnThemGG.setBackground(new java.awt.Color(0, 204, 204));
        btnThemGG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemGG.setForeground(new java.awt.Color(0, 51, 153));
        btnThemGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemGG.setText("THÊM");
        btnThemGG.setBorder(null);
        btnThemGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemGG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnThemGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemGGMouseClicked(evt);
            }
        });
        jPanel39.add(btnThemGG);

        btnSuaGG.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaGG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaGG.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaGG.setText("SỬA");
        btnSuaGG.setBorder(null);
        btnSuaGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaGG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaGGMouseClicked(evt);
            }
        });
        jPanel39.add(btnSuaGG);

        btnXoaGG.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaGG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaGG.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaGG.setText("XÓA");
        btnXoaGG.setBorder(null);
        btnXoaGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaGG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaGGMouseClicked(evt);
            }
        });
        jPanel39.add(btnXoaGG);

        btnLamMoiGG.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiGG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiGG.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiGG.setText("MỚI");
        btnLamMoiGG.setBorder(null);
        btnLamMoiGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiGG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnLamMoiGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiGGMouseClicked(evt);
            }
        });
        jPanel39.add(btnLamMoiGG);

        jPanel41.setBackground(new java.awt.Color(240, 242, 245));

        btnThemCTGG.setBackground(new java.awt.Color(0, 204, 204));
        btnThemCTGG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemCTGG.setForeground(new java.awt.Color(0, 51, 153));
        btnThemCTGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemCTGG.setText("THÊM");
        btnThemCTGG.setBorder(null);
        btnThemCTGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemCTGG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnThemCTGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemCTGGMouseClicked(evt);
            }
        });
        jPanel41.add(btnThemCTGG);

        btnSuaCTGG.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaCTGG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaCTGG.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaCTGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaCTGG.setText("SỬA");
        btnSuaCTGG.setBorder(null);
        btnSuaCTGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaCTGG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaCTGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaCTGGMouseClicked(evt);
            }
        });
        jPanel41.add(btnSuaCTGG);

        btnXoaCTGG.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaCTGG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaCTGG.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaCTGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaCTGG.setText("XÓA");
        btnXoaCTGG.setBorder(null);
        btnXoaCTGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaCTGG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaCTGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaCTGGMouseClicked(evt);
            }
        });
        jPanel41.add(btnXoaCTGG);

        tblGiamGia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>","<html><b>Tên chương trình</b></html>", "<html><b>Ngày bắt đầu</b></html>", "<html><b>Ngày kết thúc</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGiamGia.setFocusable(false);
        tblGiamGia.setRowHeight(23);
        tblGiamGia.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tblGiamGia);

        tblChiTietGiamGia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblChiTietGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>","<html><b>Chương trình</b></html>", "<html><b>Sách</b></html>", "<html><b>Chiết khấu</b></html>"

            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietGiamGia.setFocusable(false);
        tblChiTietGiamGia.setRowHeight(23);
        tblChiTietGiamGia.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblChiTietGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblChiTietGiamGia);

        jLabel70.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 102, 255));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("GIẢM GIÁ");

        jLabel85.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 102, 255));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("CHI TIẾT GIẢM GIÁ");

        btnRSSach10.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach10.setText("Xuất bản in");
        btnRSSach10.setBorder(null);
        btnRSSach10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach10ActionPerformed(evt);
            }
        });

        btnRSSach11.setBackground(new java.awt.Color(102, 153, 255));
        btnRSSach11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnRSSach11.setText("Xuất bản in");
        btnRSSach11.setBorder(null);
        btnRSSach11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSSach11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDiscountLayout = new javax.swing.GroupLayout(pnlDiscount);
        pnlDiscount.setLayout(pnlDiscountLayout);
        pnlDiscountLayout.setHorizontalGroup(
            pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiscountLayout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
            .addGroup(pnlDiscountLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnRSSach10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlDiscountLayout.createSequentialGroup()
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDiscountLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDiscountLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRSSach11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))))
            .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDiscountLayout.createSequentialGroup()
                    .addGap(473, 473, 473)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(501, Short.MAX_VALUE)))
        );
        pnlDiscountLayout.setVerticalGroup(
            pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiscountLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDiscountLayout.createSequentialGroup()
                        .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDiscountLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDiscountLayout.createSequentialGroup()
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRSSach10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRSSach11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(pnlDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDiscountLayout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tabbedMain.addTab("GIẢM GIÁ", new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_discount_40px.png")), pnlDiscount, ""); // NOI18N

        pnlMain.add(tabbedMain);
        tabbedMain.setBounds(0, 80, 1120, 530);
        //

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void readTableCustomer(){
        CustomerBUS bus = new CustomerBUS();
//        if(CustomerBUS.listCustomer == null)
               bus.readListCustomer();    
        modelCustomer.setRowCount(0);
        int i = 1;
        for(Customer c : CustomerBUS.listCustomer){
            String birthDay = vn.format(c.getBirthday());
            modelCustomer.addRow(new Object[]{
                i++,c.getLastName(),c.getFirstName(),c.getGender(),birthDay,c.getPhone()
            });
        }
    }
    
     private void readTableImport() {
        ImportBUS bus = new ImportBUS();
        ProviderBUS pro_bus = new ProviderBUS();
//        if (ImportBUS.listImport == null) {
            bus.readListImport();
//        }

        modelImport.setRowCount(0);
        int i = 1, j = 0;
        for (Import imp : ImportBUS.listImport) {

            String dateImport = vn.format(imp.getDate());
            modelImport.addRow(new Object[]{
                i++, imp.getIdImport(),  pro_bus.findProviderByID(imp.getIdProvider()).getNameProvider(), dateImport, imp.getTotal()
            });
        }
    }
    
    private void readTableImport(int idEmployee) {
        ImportBUS bus = new ImportBUS();
        ProviderBUS pro_bus = new ProviderBUS();
//        if (ImportBUS.listImport == null) {
            bus.readListImport(idEmployee);
//        }

        modelImport.setRowCount(0);
        int i = 1, j = 0;
        for (Import imp : ImportBUS.listImport) {

            String dateImport = vn.format(imp.getDate());
            modelImport.addRow(new Object[]{
                i++, imp.getIdImport(),  pro_bus.findProviderByID(imp.getIdProvider()).getNameProvider(), dateImport, imp.getTotal()
            });
        }
    }
    
    private void readTableEmployee(){
        EmployeeBUS bus = new EmployeeBUS();
//        if(EmployeeBUS.listEmployee == null)
            bus.readListEmployee();
        modelEmployee.setRowCount(0);
        int i = 1;
        for(Employee c : EmployeeBUS.listEmployee){
            String birthDay = vn.format(c.getBirthday());
            modelEmployee.addRow(new Object[]{
                i++,c.getLastName(),c.getFirstName(),c.getGender(),birthDay,c.getPhone()
            });
        }
    }
    
    private void readTableDiscount() {
        DiscountBUS bus = new DiscountBUS();
//        if (DiscountBUS.listDiscount == null) {
            bus.readListDiscount();
//        }

        modelDiscount.setRowCount(0);
        int i = 1;
        for (Discount a : DiscountBUS.listDiscount) {
            String dateStart = vn.format(a.getDateStart());
            String dateEnd = vn.format(a.getDateEnd());
            modelDiscount.addRow(new Object[]{
                i++, a.getNameProgram(), dateStart, dateEnd
            });
        }
    }
    
    private void readTableBill() {
        BillBUS bus = new BillBUS();
        CustomerBUS cus_bus = new CustomerBUS();
//        if (BillBUS.listBill == null) {
            bus.readListBill();
//        }

        modelBill.setRowCount(0);
        int i = 1;
        
        int j  = 0;
        for (Bill b : BillBUS.listBill) {
            String date = vn.format(b.getDate());
            modelBill.addRow(new Object[]{
                i++, b.getIdBill(), cus_bus.findCustomerByID(b.getIdCustomer()).getFullName(), date, b.getTotal()
            });
        }
    }
    
    private void readTableBill(int idEmployee) {
        BillBUS bus = new BillBUS();
        CustomerBUS cus_bus = new CustomerBUS();
//        if (BillBUS.listBill == null) {
            bus.readListBill(idEmployee);
//        }

        modelBill.setRowCount(0);
        int i = 1;
        
        int j  = 0;
        for (Bill b : BillBUS.listBill) {
            String date = vn.format(b.getDate());
            modelBill.addRow(new Object[]{
                i++, b.getIdBill(), cus_bus.findCustomerByID(b.getIdCustomer()).getFullName(), date, b.getTotal()
            });
        }
    }
    
    private void readTableDetailDiscount() {
        DiscountDetailBUS bus = new DiscountDetailBUS();
//        if (DiscountDetailBUS.listDiscountDetail == null) {
            bus.readListDiscountDetail();
//        }

        
        BookBUS book_bus = new BookBUS();
        DiscountBUS dis_bus = new DiscountBUS();
        
        modelDetailDiscount.setRowCount(0);
        int i = 1;
        for (DiscountDetail a : DiscountDetailBUS.listDiscountDetail) {
            modelDetailDiscount.addRow(new Object[]{
                i++, dis_bus.findDiscountByID(a.getIdDiscount()).getNameProgram(), book_bus.findBookByID(a.getIdBook()).getNameBook(), a.getDiscount()
            });
            
        }
    }
    
    private void readTableAccount() {
        AccountBUS bus = new AccountBUS();
//        if (AccountBUS.listAccount == null) {
            bus.readListAccount();
//        }

        modelAccount.setRowCount(0);
        int j = 1;
        Account a = new Account();
        EmployeeBUS emp_bus = new EmployeeBUS();
        for(int i = 1; i < bus.listAccount.size(); ++i){
            try {
                a = bus.listAccount.get(i);
                modelAccount.addRow(new Object[]{
                    j++, emp_bus.findEmployeeByID(a.getIdEmployee()).getFullName(), a.getUsername(), a.getRollToString()
                });
            } catch (Exception ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void readTableBook() {
        BookBUS bus = new BookBUS();
//        if (BookBUS.listBook == null) {
            bus.readListBook();
//        }
        
        AuthorBUS author_bus = new AuthorBUS();
        PublisherBUS pub_bus = new PublisherBUS();
        BookTypeBUS type_bus = new BookTypeBUS();
        
        modelBook.setRowCount(0);
        int i = 1;
        for (Book b : BookBUS.listBook) {
            modelBook.addRow(new Object[]{
                i++, b.getNameBook(), b.getQuantity(), Math.round(b.getPrice()), author_bus.findAuthorByID(b.getIdAuthor()).getFullName()
                    , type_bus.findBookTypeByID(b.getIdType()).getNameType() , pub_bus.findPublisherByID(b.getIdPublisher()).getNamePublisher()
            });
        }
    }

    private void readTableTypeBook() {
        BookTypeBUS bus = new BookTypeBUS();
//        if (BookTypeBUS.listBookType == null) {
          bus.readListBookType();
//        }

        modelTypeBook.setRowCount(0);
        int i = 1;
        for (BookType bt : BookTypeBUS.listBookType) {
            modelTypeBook.addRow(new Object[]{
                i++, bt.getNameType()
            });
        }
    }

    private void readTableProvider() {
        ProviderBUS bus = new ProviderBUS();
//        if (ProviderBUS.listProvider == null) {
            bus.readListProvider();
//        }
        modelProvider.setRowCount(0);
        int i = 1;
        for (Provider pro : ProviderBUS.listProvider) {
            modelProvider.addRow(new Object[]{
                i++, pro.getNameProvider(), pro.getAddress(), pro.getPhone()
            });
        }
    }

    private void readTablePublisher() {
        PublisherBUS bus = new PublisherBUS();
//        if (PublisherBUS.listPublisher == null) {
            bus.readListPublisher();
//        }
        modelPublisher.setRowCount(0);
        int i = 1;
        for (Publisher pub : PublisherBUS.listPublisher) {
            modelPublisher.addRow(new Object[]{
                i++, pub.getNamePublisher(), pub.getAddress(), pub.getPhone()
            });
        }
    }

    private void readTableAuthor() {
        AuthorBUS bus = new AuthorBUS();
//        if (AuthorBUS.listAuthor == null) {
            bus.readListAuthor();
//        }

        modelAuthor.setRowCount(0);
        int i = 1;
        for (Author auth : AuthorBUS.listAuthor) {
            modelAuthor.addRow(new Object[]{
                i++, auth.getFullName()
            });
        }
    }
    
    public String parseString(Date d) {
        return vn.format(d);
    }

    public void enterInPDF(Document d, int index) throws DocumentException {
        for (int i = 0; i < index; i++) {
            d.add(new Paragraph(" "));
        }
    }
    
    // đinh dạng ngày
    public Date parseDate(String date) {
        Date result = null;
        try {
            result = vn.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    private void deleteAllBookTable() {
        int curRow = modelBook.getRowCount();
        for (int i = curRow - 1; i >= 0; i--) {
            modelBook.removeRow(i);
        }
        tblSach.setModel(modelBook);
    }

    
   
    
    private void txtSDTNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTNCCActionPerformed

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void lblExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseEntered
        lblExit.setBackground(Color.RED);
        lblExit.setOpaque(true);
    }//GEN-LAST:event_lblExitMouseEntered

    private void lblExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseExited
        lblExit.setBackground(pnlTieuDe.getBackground());
    }//GEN-LAST:event_lblExitMouseExited

    private void lblMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseEntered
        lblMinimize.setBackground(new Color(183, 252, 252));
    }//GEN-LAST:event_lblMinimizeMouseEntered

    private void lblMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseExited
        lblMinimize.setBackground(pnlTieuDe.getBackground());
    }//GEN-LAST:event_lblMinimizeMouseExited

    private void lblLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseClicked
        Login login = new Login();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblLogOutMouseClicked

    private void lblLogOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseEntered
        lblLogOut.setBackground(new Color(183, 252, 252));
    }//GEN-LAST:event_lblLogOutMouseEntered

    private void lblLogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseExited
        lblLogOut.setBackground(pnlTieuDe.getBackground());
    }//GEN-LAST:event_lblLogOutMouseExited

    private void btnThemSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSachMouseClicked
        // TODO add your handling code here:
        String ten = txtTenSach.getText();
        String tacgia = cbTacGia.getSelectedItem().toString();
        String theloai = cbTheLoai.getSelectedItem().toString();
        String nxb = cbNXB.getSelectedItem().toString();
        String dongia = txtDonGia.getText();
        
        
        if (ten.equals("") || tacgia.equals("") || theloai.equals("") ||
            nxb.equals("") || dongia.equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin");  
            return;
        }
        
        
        if (Error.containsSpecialChar(ten) || Error.containsNumber(ten)){
            JOptionPane.showMessageDialog(null,"Tên sách không được chứa ký tự đặc biệt và số");
            return;
        }
        
        if (!Error.isInteger(dongia) || Error.isNagativeNumber(Integer.parseInt(dongia))){
            JOptionPane.showMessageDialog(null,"Giá sách phải là số nguyên dương");
            return;
        }
        
        
        
        Book book = new Book();

//         đổi từ tên sang id để thêm vào database
        String nameAuth = cbTacGia.getSelectedItem().toString();
        String nameType = cbTheLoai.getSelectedItem().toString();
        String namePub = cbNXB.getSelectedItem().toString();

        BookTypeBUS typebus = new BookTypeBUS();
        BookBUS bus_book = new BookBUS();
        
        if (BookTypeBUS.listBookType == null) {
            typebus.readListBookType();
        }
        int idtype = typebus.findIDbyName(nameType); // tìm id thể loại theo tên

        AuthorBUS authbus = new AuthorBUS();
        if (AuthorBUS.listAuthor == null) {
            authbus.readListAuthor();
        }
        int idauth = authbus.findIDbyName(nameAuth); // tìm id tác giả theo tên

        PublisherBUS pubbus = new PublisherBUS();
        if (PublisherBUS.listPublisher == null) {
            pubbus.readListPublisher();
        }
        int idpub = pubbus.findIDbyName(namePub); // tìm id nhà xuất bản theo tên
        
        book.setNameBook(txtTenSach.getText());
        book.setPrice(Float.parseFloat(txtDonGia.getText()));
        book.setIdType(idtype);
        book.setIdAuthor(idauth);
        book.setIdPublisher(idpub);
        
        
        // 15/11/2022: Lỗi xảy ra nếu vừa mới thêm thì nó sẽ ko có được mã vừa thêm. Đã tìm được các fix hehe

//        Vector row = new Vector();
//        
//        row.add(tblSach.getRowCount() + 1);
//        row.add(book.getNameBook());
//        row.add(book.getQuantity());
//        row.add(Math.round(book.getPrice()));
//        row.add(book.getIdAuthor());
//        row.add(book.getIdType());
//        row.add(book.getIdPublisher());
        
        if (!bus_book.add(book)){
            JOptionPane.showMessageDialog(null, "Sách này đã tồn tại");       
            txtTenSach.requestFocus();
        }
        else{
//            modelBook.addRow(row);
//            tblSach.setModel(modelBook);
            readTableBook();
            setCbBook();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }

    }//GEN-LAST:event_btnThemSachMouseClicked

    private void btnLamMoiSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiSachMouseClicked
        // TODO add your handling code here:
        txtTenSach.setText("");
        txtTenSach.requestFocus();
        txtFindBook.setText("");
        txtSLSach.setText("");
        txtDonGia.setText("");
        setCbAuthor();
        setCbType();
        setCbNXB(); 
        readTableBook();
        
    }//GEN-LAST:event_btnLamMoiSachMouseClicked

    private void btnXoaSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSachMouseClicked
        // TODO add your handling code here:
        int i = tblSach.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn sách cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự muốn xoá sách này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                
                BookBUS bus_book = new BookBUS();
                
                
                Book book = BookBUS.listBook.get(i);
                System.out.println(book.getIdBook());
                
                if (bus_book.delete(book)){
                    modelBook.removeRow(i);
                    tblSach.setModel(modelBook);
                    setCbBook();
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                }
                else JOptionPane.showMessageDialog(null, "Xóa thất bại");
            }
        }

    }//GEN-LAST:event_btnXoaSachMouseClicked

    private void btnSuaSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaSachMouseClicked
        int i = tblSach.getSelectedRow();
        System.out.println("hàng " + i);
        Book old = BookBUS.listBook.get(i);
        System.out.println("Mã sách cũ " + old.getIdBook());
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn sách cần sửa");
        } else {
            
            String ten = txtTenSach.getText();
            String tacgia = cbTacGia.getSelectedItem().toString();
            String theloai = cbTheLoai.getSelectedItem().toString();
            String nxb = cbNXB.getSelectedItem().toString();
            String dongia = txtDonGia.getText();
        
        
            if (ten.equals("") || tacgia.equals("") || theloai.equals("") ||
                nxb.equals("") || dongia.equals("")){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin");  
                return;
            }


            if (Error.containsSpecialChar(ten) || Error.containsNumber(ten)){
                JOptionPane.showMessageDialog(null,"Tên sách không được chứa ký tự đặc biệt và số");
                return;
            }

            if (!Error.isInteger(dongia) || Error.isNagativeNumber(Integer.parseInt(dongia))){
                JOptionPane.showMessageDialog(null,"Giá sách phải là số nguyên dương");
                return;
            }
        

            
            
            Book b = new Book();
            
            b.setIdBook(BookBUS.listBook.get(i).getIdBook());
            b.setPrice(Float.parseFloat(txtDonGia.getText()));
            b.setQuantity(BookBUS.listBook.get(i).getQuantity());
            b.setNameBook(txtTenSach.getText());

//                   đổi từ tên sang id để thực hiện việc thay đổi thông tin sách vào database
            String nameAuth = cbTacGia.getSelectedItem().toString();
            String nameType = cbTheLoai.getSelectedItem().toString();
            String namePub = cbNXB.getSelectedItem().toString();

            BookTypeBUS typebus = new BookTypeBUS();
            
            if (BookTypeBUS.listBookType == null) {
                typebus.readListBookType();
            }
            int idtype = typebus.findIDbyName(nameType); // tìm id theo tên

            AuthorBUS authbus = new AuthorBUS();
            if (AuthorBUS.listAuthor == null) {
                authbus.readListAuthor();
            }
            int idauth = authbus.findIDbyName(nameAuth); // tìm id theo tên

            PublisherBUS pubbus = new PublisherBUS();
            if (PublisherBUS.listPublisher == null) {
                pubbus.readListPublisher();
            }
            int idpub = pubbus.findIDbyName(namePub); // tìm id theo tên

            b.setIdAuthor(idauth);
            b.setIdPublisher(idpub);
            b.setIdType(idtype);

            BookBUS bookbus = new BookBUS();
            if (BookBUS.listBook == null) {
                bookbus.readListBook();
            }


            if (bookbus.update(b, i)){
                int column = 1;
                modelBook.setValueAt(b.getNameBook(), i, column++);
                modelBook.setValueAt(b.getQuantity(), i, column++);
                modelBook.setValueAt(Math.round(b.getPrice()), i, column++);
                modelBook.setValueAt(nameAuth, i, column++);
                modelBook.setValueAt(nameType, i, column++);
                modelBook.setValueAt(namePub, i, column++);
                tblSach.setModel(modelBook);
                
                    // Cập nhật lại combox box                
                setCbBook();
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
            }
            else JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");

        }
    }//GEN-LAST:event_btnSuaSachMouseClicked

    private void btnThemTLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemTLMouseClicked
        // TODO add your handling code here:
          if (txtTenTL.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập vào tên thể loại");
            return;
        }
        if (Error.containsNumber(txtTenTL.getText()) || Error.containsSpecialChar(txtTenTL.getText()))
            JOptionPane.showMessageDialog(null, "Tên thể loại không được chứa ký tự đặc biệt và số");
        else{
            BookType booktype = new BookType();
            BookTypeBUS typebus = new BookTypeBUS();

            booktype.setNameType(txtTenTL.getText());
            
            

            
             
            
            if (!typebus.add(booktype)) {
                JOptionPane.showMessageDialog(null, "Thể loại này đã tồn tại");
                txtTenTL.requestFocus();
            } else {
//                Vector row = new Vector();
//                
//                row.add(tblTheLoai.getRowCount() + 1);
//                row.add(booktype.getNameType());
//
//                modelTypeBook.addRow(row);
//                tblTheLoai.setModel(modelTypeBook);  
                  readTableTypeBook();
                  setCbType();
                JOptionPane.showMessageDialog(null, "Thêm thành công");
            }
        }
    }//GEN-LAST:event_btnThemTLMouseClicked

    private void btnSuaTLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaTLMouseClicked
        // TODO add your handling code here:
        
        int i = tblTheLoai.getSelectedRow();
//        System.out.println("hàng " + i);
//        BookType old = BookTypeBUS.listBookType.get(i);
//        System.out.println("Mã sách cũ " + old.getIdType());

        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thể loại cần sửa");
        } else {
            
            if (txtTenTL.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập vào tên thể loại");
                return;
            }

            if (Error.containsNumber(txtTenTL.getText()) || Error.containsSpecialChar(txtTenTL.getText())) {
                JOptionPane.showMessageDialog(null, "Tên thể loại không được chứa ký tự đặc biệt và số");
            } else {

                BookType booktype = new BookType();
                BookTypeBUS typebus = new BookTypeBUS();
//
//                if (BookTypeBUS.listBookType == null) {
//                    typebus.readListBookType();
//                }
                
                booktype.setIdType(BookTypeBUS.listBookType.get(i).getIdType());
                booktype.setNameType(txtTenTL.getText());
                
                
                if (typebus.update(booktype, i)){
                    modelTypeBook.setValueAt(booktype.getNameType(), i, 1);
                    tblTheLoai.setModel(modelTypeBook);
                    
                    // Cập nhật lại combox box
                    setCbType();
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
                }
                else JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");
            }
        }
        


    }//GEN-LAST:event_btnSuaTLMouseClicked

    private void btnXoaTLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaTLMouseClicked
        // TODO add your handling code here:
        int i = tblTheLoai.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn thể loại cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự muốn xoá thể loại này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                
                BookTypeBUS typebus = new BookTypeBUS();
                
                BookType booktype = typebus.listBookType.get(i);
                
                // Case just add new item
//                if (i >= typebus.listBookType.size()){
//                    booktype = typebus.listBookType.get(typebus.listBookType.size() - 1);
//                }
                
                
                System.out.println(booktype.getNameType());
                System.out.println("Mã: " + booktype.getIdType());
//                System.out.println(idType);   

                if(typebus.delete(booktype) == true){
                    modelTypeBook.removeRow(i);
                    tblTheLoai.setModel(modelTypeBook);
                    setCbType();
                    JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                }
                else  JOptionPane.showMessageDialog(rootPane, "Xóa thất bại");
                
               
            }
        }
    }//GEN-LAST:event_btnXoaTLMouseClicked

    private void btnThemNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNCCMouseClicked
        // TODO add your handling code here:
        String name = txtTenNCC.getText();
        String address = txtDiaChiNCC.getText();
        String phone = txtSDTNCC.getText();
        
        
        if (name.equals("") || address.equals("") || phone.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        if (Error.containsNumber(name) || Error.containsSpecialChar(name)){
            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được chứa ký tự đặc biệt và số");
            return;
        }
        
        if (!Error.isNumberPhone(phone)){
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
            return;
        }
        
        Provider pro = new Provider();
        ProviderBUS probus = new ProviderBUS();

        pro.setNameProvider(txtTenNCC.getText());
        pro.setAddress(txtDiaChiNCC.getText());
        pro.setPhone(phone);
        
        
//        Vector row = new Vector();
//        row.add(tblNCC.getRowCount() + 1);
//        row.add(pro.getNameProvider());
//        row.add(pro.getAddress());
//        row.add(pro.getPhone());

        if (!probus.add(pro)) {
            JOptionPane.showMessageDialog(null, "Nhà cung cấp này đã tồn tại");
            txtTenNCC.requestFocus();
        } else {
//            modelProvider.addRow(row);
//            tblNCC.setModel(modelProvider);
            readTableProvider();
            setCbProvider();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btnThemNCCMouseClicked

    private void btnXoaNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNCCMouseClicked
        // TODO add your handling code here:
      int i = tblNCC.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn nhà cung cấp cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự muốn xoá nhà cung cấp này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                
                ProviderBUS probus = new ProviderBUS();
                int idProvider = ProviderBUS.listProvider.get(i).getIdProvider();
                
                if(!probus.delete(idProvider, i)){
                    JOptionPane.showMessageDialog(rootPane, "Xoá thất bại");
                    return;
                }
                
                modelProvider.removeRow(i);
                tblNCC.setModel(modelProvider);
                setCbProvider();
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            }
        }
    }//GEN-LAST:event_btnXoaNCCMouseClicked

    private void btnThemNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNXBMouseClicked
        // TODO add your handling code here:
        String name = txtTenNXB.getText();
        String address = txtDiaChiNXB.getText();
        String phone = txtSDTNXB.getText();
        
        
        if (name.equals("") || address.equals("") || phone.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        if (Error.containsNumber(name) || Error.containsSpecialChar(name)){
            JOptionPane.showMessageDialog(null, "Tên nhà xuất bản không được chứa ký tự đặc biệt và số");
            return;
        }
        
        if (!Error.isNumberPhone(phone)){
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
            return;
        }

        Publisher pub = new Publisher();
        PublisherBUS pubbus = new PublisherBUS();

        pub.setNamePublisher(txtTenNXB.getText());
        pub.setAddress(txtDiaChiNXB.getText());
        pub.setPhone(txtSDTNXB.getText());

//        Vector row = new Vector();
//        row.add(tblNXB.getRowCount() + 1);
//        row.add(pub.getNamePublisher());
//        row.add(pub.getAddress());
//        row.add(pub.getPhone());

        if (!pubbus.add(pub)) {
            JOptionPane.showMessageDialog(null, "Nhà xuất bản này đã tồn tại");
        } else {
//            modelPublisher.addRow(row);
//            tblNXB.setModel(modelPublisher);  
            readTablePublisher();
            setCbNXB();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btnThemNXBMouseClicked

    private void btnXoaNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNXBMouseClicked
        // TODO add your handling code here:
       int i = tblNXB.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn nhà xuất bản cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự muốn xoá nhà xuất bản này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                PublisherBUS pubbus = new PublisherBUS();
                int idPublisher = PublisherBUS.listPublisher.get(i).getIdPublisher();
                if(!pubbus.delete(idPublisher, i)){
                    JOptionPane.showMessageDialog(rootPane, "Xoá thất bại");
                    return;
                }
                modelPublisher.removeRow(i);
                tblNXB.setModel(modelPublisher);
                setCbNXB();
                JOptionPane.showMessageDialog(null, "Xóa thành công");
               
            }
        }
    }//GEN-LAST:event_btnXoaNXBMouseClicked

    private void btnSuaNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNXBMouseClicked
        // TODO add your handling code here:
        int i = tblNXB.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn nhà xuất bản cần sửa");
        } else {
            String name = txtTenNXB.getText();
            String address = txtDiaChiNXB.getText();
            String phone = txtSDTNXB.getText();

            if (name.equals("") || address.equals("") || phone.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            if (Error.containsNumber(name) || Error.containsSpecialChar(name)) {
                JOptionPane.showMessageDialog(null, "Tên nhà xuất bản không được chứa ký tự đặc biệt và số");
                return;
            }

            if (!Error.isNumberPhone(phone)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
                return;
            }

            Publisher pub = new Publisher();
            PublisherBUS pubbus = new PublisherBUS();

            System.out.println("hàng " + i);
//            Publisher old = PublisherBUS.listPublisher.get(i);
            
//            if (PublisherBUS.listPublisher == null) {
//                pubbus.readListPublisher();
//            }

            pub.setIdPublisher(pubbus.listPublisher.get(i).getIdPublisher());
            pub.setNamePublisher(txtTenNXB.getText());
            pub.setAddress(txtDiaChiNXB.getText());
            pub.setPhone(txtSDTNXB.getText());
            
            
            if (pubbus.update(pub, i)) {
                int column = 1;
                modelPublisher.setValueAt(pub.getNamePublisher(), i, column++);
                modelPublisher.setValueAt(pub.getAddress(), i, column++);
                modelPublisher.setValueAt(pub.getPhone(), i, column++);
                tblNXB.setModel(modelPublisher);
                setCbNXB();
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");
            }
        }
        
    }//GEN-LAST:event_btnSuaNXBMouseClicked

    private void btnSuaNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNCCMouseClicked
        // TODO add your handling code here:
        int i = tblNCC.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn nhà cung cấp cần sửa");
        } else {
            String name = txtTenNCC.getText();
            String address = txtDiaChiNCC.getText();
            String phone = txtSDTNCC.getText();

            if (name.equals("") || address.equals("") || phone.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            if (Error.containsNumber(name) || Error.containsSpecialChar(name)) {
                JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được chứa ký tự đặc biệt và số");
                return;
            }

            if (!Error.isNumberPhone(phone)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
                return;
            }

            Provider pro = new Provider();
            ProviderBUS probus = new ProviderBUS();

            System.out.println("hàng " + i);
            Provider old = ProviderBUS.listProvider.get(i);
            
            if (ProviderBUS.listProvider == null) {
                probus.readListProvider();
            }

            pro.setIdProvider(probus.listProvider.get(i).getIdProvider());
            pro.setNameProvider(txtTenNCC.getText());
            pro.setAddress(txtDiaChiNCC.getText());
            pro.setPhone(txtSDTNCC.getText());

            if (probus.update(pro, i)){
                int column = 1;
                modelProvider.setValueAt(pro.getNameProvider(), i, column++);
                modelProvider.setValueAt(pro.getAddress(), i, column++);
                modelProvider.setValueAt(pro.getPhone(), i, column++);
                tblNCC.setModel(modelProvider);
                setCbProvider();
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
            }
            else  JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");

            
        }
       
    }//GEN-LAST:event_btnSuaNCCMouseClicked

    private void lblFindBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFindBookMouseClicked
        // TODO add your handling code here:       
        BookBUS bookbus = new BookBUS();
        String timkiem = txtFindBook.getText();
        if (timkiem.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập thông tin tìm kiếm");
            txtFindBook.requestFocus();
        } else {
            
            if (Error.containsNumber(timkiem) || Error.containsSpecialChar(timkiem)){
                JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự đặc biệt và số");
                txtFindBook.requestFocus();
            }
            else{
                int selected = comSach.getSelectedIndex();
                ArrayList<Book> findBook = new ArrayList<>();
                    switch (selected) {
                        case 0:
                            findBook = bookbus.findBooksByName(timkiem);
                            break;
                        case 1:
                            findBook = bookbus.findBookByNameAuthor(timkiem);
                            break;
                        case 2:
                            findBook = bookbus.findBookByNameType(timkiem);
                            break;
                        case 3:
                            findBook = bookbus.findBookByNamePublisher(timkiem);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Bạn hãy chọn mục cần tìm kiếm");
                    }
                    if (findBook.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
                        txtFindBook.requestFocus();
                    } 
                    else {
                        modelBook.setRowCount(0);
                        int i = 1;
                        
                        AuthorBUS author_bus = new AuthorBUS();
                        PublisherBUS pub_bus = new PublisherBUS();
                        BookTypeBUS type_bus = new BookTypeBUS();
                        
                        for (Book book : findBook) {
                            Vector row = new Vector();
                            row.add(i++);
                            row.add(book.getNameBook());
                            row.add(book.getQuantity());
                            row.add(Math.round(book.getPrice()));
                            row.add(author_bus.findAuthorByID(book.getIdAuthor()).getFullName());
                            row.add(type_bus.findBookTypeByID(book.getIdType()).getNameType());
                            row.add(pub_bus.findPublisherByID(book.getIdPublisher()).getNamePublisher());
                            modelBook.addRow(row);
                        }
                        tblSach.setModel(modelBook);
                    }
                }
            
        }

    }//GEN-LAST:event_lblFindBookMouseClicked

    private void lblFindTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFindTypeMouseClicked
        // TODO add your handling code here:
        String timkiem = txtFindType.getText();
        if (timkiem.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập thông tin tìm kiếm");
            txtFindType.requestFocus();
        } else {
            
            if (Error.containsNumber(timkiem) || Error.containsSpecialChar(timkiem)){
                JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự và số");
                txtFindType.requestFocus();
            }
            else{
                BookTypeBUS typebus = new BookTypeBUS();
                ArrayList<BookType> findTypeName = new ArrayList<BookType>();
                findTypeName = typebus.findBookTypeByName(timkiem);
                if (findTypeName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả tìm kiếm");
                    txtFindType.requestFocus();
                } 
                else {
                    modelTypeBook.setRowCount(0);
                    int i = 1;
                    for (BookType tName : findTypeName) {
                        Vector row = new Vector();
                        row.add(i++);
                        row.add(tName.getNameType());
                        modelTypeBook.addRow(row);
                    }
                    tblTheLoai.setModel(modelTypeBook);
                }
            }
        }
    }//GEN-LAST:event_lblFindTypeMouseClicked

    private void lblFindProviderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFindProviderMouseClicked
        // TODO add your handling code here:
        String timkiem = txtFindProvider.getText();
        if (timkiem.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập thông tin tìm kiếm");
            txtFindProvider.requestFocus();
        } else {
            
            if (Error.containsSpecialChar(timkiem)){
                JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự đặc biệt");
                txtFindProvider.requestFocus();
            }
            else{
                ProviderBUS probus = new ProviderBUS();
                ArrayList<Provider> findProvider = new ArrayList<>();
                int selected = comNCC.getSelectedIndex();
                switch (selected) {
                    case 0:
                        findProvider = probus.findProviderByName(timkiem);
                        break;
                    case 1:
                        findProvider = probus.findProviderByPhone(timkiem);
                        break; 
                    default:
                        JOptionPane.showMessageDialog(null, "Bạn hãy chọn mục cần tìm kiếm");
                }
                if (findProvider.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
                    txtFindProvider.requestFocus();
                } else {
                    modelProvider.setRowCount(0);
                    int i = 1;
                    for (Provider pName : findProvider) {
                        Vector row = new Vector();
                        row.add(i++);
                        row.add(pName.getNameProvider());
                        row.add(pName.getAddress());
                        row.add(pName.getPhone());
                        modelProvider.addRow(row);
                    }
                    tblNCC.setModel(modelProvider);
                }
            }
            
        }
    }//GEN-LAST:event_lblFindProviderMouseClicked

    private void jlblFindPublisherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblFindPublisherMouseClicked
        // TODO add your handling code here:
        String timkiem = txtFindPublisher.getText();
        if (timkiem.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập thông tin tìm kiếm");
            txtFindPublisher.requestFocus();
        } else {
            
            if (Error.containsSpecialChar(timkiem)){
                JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự đặc biệt");
                txtFindPublisher.requestFocus();
            }
            else{
                PublisherBUS probus = new PublisherBUS();
                ArrayList<Publisher> findPublisher = new ArrayList<>();
                int selected = comNXB.getSelectedIndex();
                switch (selected) {
                    case 0:
                        findPublisher = probus.findPublisherByName(timkiem);
                        break;
                    case 1:
                        findPublisher = probus.findPublisherByPhone(timkiem);
                        break; 
                    default:
                        JOptionPane.showMessageDialog(null, "Bạn hãy chọn mục cần tìm kiếm");
                }
                if (findPublisher.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
                    txtFindPublisher.requestFocus();
                } else {
                    modelPublisher.setRowCount(0);
                    int i = 1;
                    for (Publisher pName : findPublisher) {
                        Vector row = new Vector();
                        row.add(i++);
                        row.add(pName.getNamePublisher());
                        row.add(pName.getAddress());
                        row.add(pName.getPhone());
                        modelPublisher.addRow(row);
                    }
                    tblNXB.setModel(modelPublisher);
                }
            }
            
        }
    }//GEN-LAST:event_jlblFindPublisherMouseClicked

    private void btnLamMoiNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiNXBMouseClicked
        // TODO add your handling code here:
        txtFindPublisher.setText("");
        txtTenNXB.setText("");
        txtDiaChiNXB.setText("");
        txtSDTNXB.setText("");
        txtTenNXB.requestFocus();
        readTablePublisher();
    }//GEN-LAST:event_btnLamMoiNXBMouseClicked

    private void btnTaoTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoTKMouseClicked

        txtMsg.setText("");
        
        String nameEmployee = (String)cbEmployee.getSelectedItem();
        String pass = txtPassword.getText();
        String nameUser = txtUser.getText();
        int roll = cbRoll.getSelectedIndex() + 1;
        
        int idEmployee = EmployeeBUS.listEmployee.get(cbEmployee.getSelectedIndex()).getIdEmployee();
        
        System.out.println(idEmployee);
        AccountBUS bus = new AccountBUS();
        
        
//        if(AccountBUS.listAccount == null)
//            bus.readListAccount();
        // 1-bán sách; 2- nhập sách;   
        /// check đăng kí
        
        String Msg = bus.checkRegister(nameUser, pass, roll, idEmployee);
        if(Msg.equals("")){
                
                String encodePass = "";
                
                try {
                encodePass = bus.encrypt(pass);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                Account a = new Account(idEmployee, nameUser, encodePass , roll,1);
                
                if (bus.add(a)){
                    
                    
                    readTableAccount();
                    JOptionPane.showMessageDialog(rootPane, "Thêm tài khoản thành công");
                    
//                    Vector row = new Vector();
//                    row.add(tblAccount.getRowCount() + 1);
//                    row.add(nameEmployee);
//                    row.add(a.getUsername());
//                    try {
//                        row.add(bus.encrypt(a.getPassword()));
//                    } catch (NoSuchAlgorithmException ex) {
//                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    row.add(a.getRoll());
//                    modelAccount.addRow(row);
//                    tblAccount.setModel(modelAccount);
                        
                   
                }
                else JOptionPane.showMessageDialog(rootPane, "Thêm tài khoản thất bại");
                
        } else {  /// xuất thông báo lỗi
           txtMsg.setText(Msg); 
        }
    }//GEN-LAST:event_btnTaoTKMouseClicked

    private void btnLockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLockMouseClicked
//         TODO add your handling code here:   
        AccountBUS accbus = new AccountBUS();
        int i = tblAccount.getSelectedRow();
        
//        int roll = (int) modelAccount.getValueAt(i, 3);
//        if(roll == 0){
//            JOptionPane.showMessageDialog(rootPane, "Tài khoản admin không thể khoá");
//            return;
//        }
        
        System.out.println("hàng " + i);
    
        if (AccountBUS.listAccount == null) {
            accbus.readListAccount();
        }

        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn tài khoản cần khóa");
        } else {
//            int idEmployee = AccountBUS.listAccount.get(i + 1).getIdEmployee();

                String userName = tblAccount.getValueAt(i, 2).toString();
                 
                 int idEmployee = accbus.findAccountByUserName(userName).getIdEmployee();

            Account acc = accbus.findAccountByID(idEmployee);
            if(acc.getStatus()==0){
                JOptionPane.showMessageDialog(rootPane, "Tài khoản hiện đang khoá");
                return;
            } 
            acc.setIdEmployee(idEmployee);
            acc.setStatus(0);
            if (accbus.update(acc, i)){
                JOptionPane.showMessageDialog(rootPane, "Khoá thành công");    
            }
            else JOptionPane.showMessageDialog(rootPane, "Khoá thất bại");  
        }
    }//GEN-LAST:event_btnLockMouseClicked

    private void btnDeleteAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAccountMouseClicked
       int i = tblAccount.getSelectedRow();   
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn tài khoản cần xoá");
        } else {
            
//            int roll = (int) modelAccount.getValueAt(i, 3);
//            if(roll == 0){
//                JOptionPane.showMessageDialog(rootPane, "Tài khoản admin không thể xoá");
//                return;
//            }

            int result = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự muốn xoá tài khoản này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                
                AccountBUS accbus = new AccountBUS();
                
//                // i+1 vì table chỉ nap từ arraylist từ vị trí thứ hai (i = 1)
//                int idEmployee = AccountBUS.listAccount.get(i + 1).getIdEmployee();
//                System.out.println(idEmployee);

                 String userName = tblAccount.getValueAt(i, 2).toString();
                 
                 int idEmployee = accbus.findAccountByUserName(userName).getIdEmployee();
                
                if(!accbus.delete(idEmployee, i)){
                    JOptionPane.showMessageDialog(rootPane, "Xoá thất bại ");
                    return;
                }
                modelAccount.removeRow(i);
                tblAccount.setModel(modelAccount);
                JOptionPane.showMessageDialog(rootPane, "Xoá thành công ");
            }
        }
    }//GEN-LAST:event_btnDeleteAccountMouseClicked

    private void btnUnLockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUnLockMouseClicked
        AccountBUS accbus = new AccountBUS();
        int i = tblAccount.getSelectedRow();
        
//        int roll = (int) modelAccount.getValueAt(i, 3);
//        if(roll == 0){
//            JOptionPane.showMessageDialog(rootPane, "Tài khoản admin không bị khoá");
//            return;
//        }
    
        if (AccountBUS.listAccount == null) {
            accbus.readListAccount();
        }

        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn tài khoản cần mở khóa");
        } else {
//            int idEmployee = AccountBUS.listAccount.get(i + 1).getIdEmployee();

                 String userName = tblAccount.getValueAt(i, 2).toString();
                 int idEmployee = accbus.findAccountByUserName(userName).getIdEmployee();
                 
            Account acc = accbus.findAccountByID(idEmployee);
            if(acc.getStatus()==1){
                JOptionPane.showMessageDialog(rootPane, "Tài khoản hiện không bị khoá");
                return;
            } 
            
            acc.setIdEmployee(idEmployee);
            acc.setStatus(1);
            if (accbus.update(acc, i)){
                JOptionPane.showMessageDialog(rootPane, "Mở khoá thành công");    
            }
            else JOptionPane.showMessageDialog(rootPane, "Mở khoá thất bại");
        }
    }//GEN-LAST:event_btnUnLockMouseClicked

    private void btnThemKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKHMouseClicked
        // TODO add your handling code here:
        String lastname = txtHOKH.getText();
        String firstname = txtTenKH.getText();
        String gender = txtGioiTinhKH.getSelectedItem().toString();
        String phone = txtSDTKH.getText();
        
        
        if (lastname.equals("") || firstname.equals("") || gender.equals("") || phone.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        if (Error.containsNumber(lastname) || Error.containsSpecialChar(lastname)){
            JOptionPane.showMessageDialog(null, "Họ và tên lót không được chứa ký tự đặc biệt và số");
            return;
        }
        
        if (Error.containsNumber(firstname) || Error.containsSpecialChar(firstname)){
            JOptionPane.showMessageDialog(null, "Tên không được chứa ký tự đặc biệt và số");
            return;
        }
        
        if (!Error.isNumberPhone(phone)){
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
            return;
        }
        
        Customer customer = new Customer();
        
        CustomerBUS cus_BUS = new CustomerBUS();
        
        
        customer.setLastName(txtHOKH.getText());
        customer.setFirstName(txtTenKH.getText());
        customer.setGender(txtGioiTinhKH.getModel().getSelectedItem().toString());
        String dateEng  = eng.format(dateNgaySinhKH.getDate());
        String dateVN  = vn.format(dateNgaySinhKH.getDate());
        try {
            Date date = eng.parse(dateEng);
            customer.setBirthday(date);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (Error.isNumberPhone(txtSDTKH.getText())) customer.setPhone(txtSDTKH.getText());
        else{
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
            return;
        }
//        Vector row = new Vector();
//        row.add(tblKhachHang.getRowCount() + 1);
//        row.add(customer.getLastName());
//        row.add(customer.getFirstName());
//        row.add(customer.getGender());
//        row.add(dateVN);
//        row.add(customer.getPhone());  
        
        if (!cus_BUS.add(customer)){
            JOptionPane.showMessageDialog(null, "Khách hàng này đã có trong hệ thống");
            txtHOKH.requestFocus();
        }
        else{
//            modelCustomer.addRow(row);
//            tblKhachHang.setModel(modelCustomer);
            readTableCustomer();
            setCbCustomer();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btnThemKHMouseClicked

    private void btnXoaKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKHMouseClicked
       int i = tblKhachHang.getSelectedRow();
        if(i==-1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn khách hàng cần xóa");
        }else{
            int result = JOptionPane.showConfirmDialog(rootPane,"Bạn có thực sự muốn xóa khách hàng này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                CustomerBUS bus_customer = new CustomerBUS();
                int idCustomer = CustomerBUS.listCustomer.get(i).getIdCustomer();
                if(!bus_customer.delete(idCustomer, i)){
                    JOptionPane.showMessageDialog(rootPane, "Xoá thất bại");
                    return;
                }
                else{
                    modelCustomer.removeRow(i);
                    tblKhachHang.setModel(modelCustomer);
                    setCbCustomer();
                    JOptionPane.showMessageDialog(rootPane, "Xoá thành công");
                }
            }
        }
    }//GEN-LAST:event_btnXoaKHMouseClicked

    private void btnSuaKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaKHMouseClicked
        
        int i = tblKhachHang.getSelectedRow();
        
        if(i==-1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn khách hàng cần sửa");
        } else{
            
                String lastname = txtHOKH.getText();
                String firstname = txtTenKH.getText();
                String gender = txtGioiTinhKH.getSelectedItem().toString();
                String phone = txtSDTKH.getText();


                if (lastname.equals("") || firstname.equals("") || gender.equals("") || phone.equals("")){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                if (Error.containsNumber(lastname) || Error.containsSpecialChar(lastname)){
                    JOptionPane.showMessageDialog(null, "Họ và tên lót không được chứa ký tự đặc biệt và số");
                    return;
                }

                if (Error.containsNumber(firstname) || Error.containsSpecialChar(firstname)){
                    JOptionPane.showMessageDialog(null, "Tên không được chứa ký tự đặc biệt và số");
                    return;
                }

                if (!Error.isNumberPhone(phone)){
                    JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
                    return;
                }
                
                Customer c = new Customer();
                
                c.setIdCustomer(CustomerBUS.listCustomer.get(i).getIdCustomer());
                c.setLastName(lastname);
                c.setFirstName(firstname);
                c.setGender(gender);
                c.setPhone(phone);
                
                String dateEng  = eng.format(dateNgaySinhKH.getDate());
                String dateVN  = vn.format(dateNgaySinhKH.getDate());
               
                try{
                   Date date = eng.parse(dateEng);
                   c.setBirthday(date);
                }catch(ParseException ex){
                   Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE,null,ex);
                }
                
                CustomerBUS bus = new CustomerBUS();
                
                
                
                if (bus.update(c, i) == true){
                    int column = 1;
                    modelCustomer.setValueAt(c.getLastName(), i, column++);
                    modelCustomer.setValueAt(c.getFirstName(), i, column++);
                    modelCustomer.setValueAt(c.getGender(), i, column++);
                    modelCustomer.setValueAt(dateVN, i, column++);
                    modelCustomer.setValueAt(c.getPhone(), i, column++);
                    tblKhachHang.setModel(modelCustomer);
                    setCbCustomer();
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
                }
                else JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");

        }
    }//GEN-LAST:event_btnSuaKHMouseClicked

    private void lblFindCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFindCustomerMouseClicked
        // TODO add your handling code here:
        String timkiem = txtFindCustomer.getText();
        
        if (timkiem.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập thông tin tìm kiếm");
            txtFindCustomer.requestFocus();
        } else {
            ArrayList<Customer> findCustomer = new ArrayList<>();
            CustomerBUS cusbus = new CustomerBUS();
            int selected = comKH.getSelectedIndex();
            switch (selected) {
                case 0:
                    
                    if (Error.containsNumber(timkiem) || Error.containsSpecialChar(timkiem)){
                        JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự đặc biệt và số");
                        txtFindCustomer.requestFocus();
                    }
                    else
                        findCustomer = cusbus.findCustomerByFullname(timkiem);
                    break;
                case 1:
                    if (!Error.isNumberPhone(timkiem)){
                        JOptionPane.showMessageDialog(null, "Số điện thoại tìm kiếm không đúng định dạng");
                        txtFindCustomer.requestFocus();
                    }
                    else
                         findCustomer = cusbus.findCustomerByPhone(timkiem);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Bạn hãy chọn mục cần tìm kiếm");
            }
            modelCustomer.setRowCount(0);
            int i = 1;
            for (Customer cName : findCustomer) {
                Vector row = new Vector();
                row.add(i++);
                row.add(cName.getLastName());
                row.add(cName.getFirstName());
                row.add(cName.getGender());
                row.add(cName.getBirthday());
                row.add(cName.getPhone());
                modelCustomer.addRow(row);
            }
            tblKhachHang.setModel(modelCustomer);
        }
    }//GEN-LAST:event_lblFindCustomerMouseClicked

    private void btnThemNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNVMouseClicked
        String lastname = txtHONV.getText();
        String firstname = txtTenNV.getText();
        String gender = txtGioiTinhNV.getSelectedItem().toString();
        String phone = txtSDTNV.getText();
        
        
        if (lastname.equals("") || firstname.equals("") || gender.equals("") || phone.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        if (Error.containsNumber(lastname) || Error.containsSpecialChar(lastname)){
            JOptionPane.showMessageDialog(null, "Họ và tên lót không được chứa ký tự đặc biệt và số");
            return;
        }
        
        if (Error.containsNumber(firstname) || Error.containsSpecialChar(firstname)){
            JOptionPane.showMessageDialog(null, "Tên không được chứa ký tự đặc biệt và số");
            return;
        }
        
        if (!Error.isNumberPhone(phone)){
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
            return;
        }
        
        Employee employee = new Employee();
        EmployeeBUS employee_BUS = new EmployeeBUS();
       
        employee.setLastName(txtHONV.getText());
        employee.setFirstName(txtTenNV.getText());
        employee.setGender(txtGioiTinhNV.getModel().getSelectedItem().toString());
        employee.setPhone(phone);
        
        String dateEng  = eng.format(dateNgaySinhNV.getDate());
        String dateVN  = vn.format(dateNgaySinhNV.getDate());
        try {
            Date date = eng.parse(dateEng);
            employee.setBirthday(date);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        Vector row = new Vector();
//        row.add(tblNhanVien.getRowCount() + 1);
//        row.add(employee.getLastName());
//        row.add(employee.getFirstName());
//        row.add(employee.getGender());
//        row.add(dateVN);
//        row.add(employee.getPhone());  
        
        if (!employee_BUS.add(employee)){
            JOptionPane.showMessageDialog(null, "Nhân viên này đã có trong hệ thống");
            txtHONV.requestFocus();
        }
        else{
//            modelEmployee.addRow(row);
//            tblNhanVien.setModel(modelEmployee);
            readTableEmployee();
            setCbEmployee();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btnThemNVMouseClicked

    private void btnSuaNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNVMouseClicked
        
        int i = tblNhanVien.getSelectedRow();
        System.out.println("hàng " + i);
        
        if(i == -1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn nhân viên cần sửa");
        } else{
            
                String lastname = txtHONV.getText();
                String firstname = txtTenNV.getText();
                String gender = txtGioiTinhNV.getSelectedItem().toString();
                String phone = txtSDTNV.getText();


                if (lastname.equals("") || firstname.equals("") || gender.equals("") || phone.equals("")){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                if (Error.containsNumber(lastname) || Error.containsSpecialChar(lastname)){
                    JOptionPane.showMessageDialog(null, "Họ và tên lót không được chứa ký tự đặc biệt và số");
                    return;
                }

                if (Error.containsNumber(firstname) || Error.containsSpecialChar(firstname)){
                    JOptionPane.showMessageDialog(null, "Tên không được chứa ký tự đặc biệt và số");
                    return;
                }

                if (!Error.isNumberPhone(phone)){
                    JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
                    return;
                }
        
            
                Employee e = new Employee();
                
                e.setIdEmployee(EmployeeBUS.listEmployee.get(i).getIdEmployee());
                e.setLastName(lastname);
                e.setFirstName(firstname);
                e.setGender(gender);
                e.setPhone(phone);
                String dateEng  = eng.format(dateNgaySinhNV.getDate());
                String dateVN  = vn.format(dateNgaySinhNV.getDate());
                
                try{
                   Date date = eng.parse(dateEng);
                   e.setBirthday(date);
                }catch(ParseException ex){
                   Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE,null,ex);
                }
                
                EmployeeBUS employeebus = new EmployeeBUS();
                
                if (employeebus.update(e, i) == true){
                    int column = 1;
                    modelEmployee.setValueAt(e.getLastName(), i, column++);
                    modelEmployee.setValueAt(e.getFirstName(), i, column++);
                    modelEmployee.setValueAt(e.getGender(), i, column++);
                    modelEmployee.setValueAt(dateVN, i, column++);
                    modelEmployee.setValueAt(e.getPhone(), i, column++);
                    tblNhanVien.setModel(modelEmployee);
                    setCbEmployee();
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
                }
                else JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");
        }
    }//GEN-LAST:event_btnSuaNVMouseClicked

    private void lblFindEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFindEmployeeMouseClicked
       
        String timkiem = txtFindEmployee.getText();
        
        if (timkiem.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập thông tin tìm kiếm");
            lblFindEmployee.requestFocus();
        } else {
            ArrayList<Employee> findEmployee = new ArrayList<>();
            EmployeeBUS empbus = new EmployeeBUS();
            int selected = comNV.getSelectedIndex();
            switch (selected) {
                case 0:
                    if (Error.containsNumber(timkiem) || Error.containsSpecialChar(timkiem)){
                        JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự đặc biệt và số");
                        lblFindEmployee.requestFocus();
                    }
                    else
                        findEmployee = empbus.findEmployeeByFullname(timkiem);
                    break;
                case 1:
                    if (!Error.isNumberPhone(timkiem)){
                        JOptionPane.showMessageDialog(null, "Số điện thoại tìm kiếm không đúng định dạng");
                        lblFindEmployee.requestFocus();
                    }
                    else
                         findEmployee = empbus.findEmployeeByPhone(timkiem);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Bạn hãy chọn mục cần tìm kiếm");
            }
            modelEmployee.setRowCount(0);
            int i = 1;
            for (Employee cName : findEmployee) {
                Vector row = new Vector();
                row.add(i++);
                row.add(cName.getLastName());
                row.add(cName.getFirstName());
                row.add(cName.getGender());
                row.add(cName.getBirthday());
                row.add(cName.getPhone());
                modelEmployee.addRow(row);
            }
            tblNhanVien.setModel(modelEmployee);
        }
    }//GEN-LAST:event_lblFindEmployeeMouseClicked

    private void btnCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCTPNActionPerformed
        int i = tblPhieuNhap.getSelectedRow();
        if (i >= 0) {
            int maPN = (int )tblPhieuNhap.getModel().getValueAt(i, 1);
            new ChiTietPhieuNhap(maPN,idCurrent).setVisible(true);
        }
        else JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập cần xem chi tiết");
    }//GEN-LAST:event_btnCTPNActionPerformed

    private void btnRSSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSachActionPerformed
        exportToExcel(modelBook);
    }//GEN-LAST:event_btnRSSachActionPerformed

    private void btnRSSach1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach1ActionPerformed
        exportToExcel(modelProvider);
    }//GEN-LAST:event_btnRSSach1ActionPerformed

    private void btnRSSach2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach2ActionPerformed
        exportToExcel(modelPublisher);
    }//GEN-LAST:event_btnRSSach2ActionPerformed

    private void btnRSSach12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach12ActionPerformed
        exportToExcel(modelTypeBook);
    }//GEN-LAST:event_btnRSSach12ActionPerformed

    private void btnRSSach5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach5ActionPerformed
        exportToExcel(modelCustomer);
    }//GEN-LAST:event_btnRSSach5ActionPerformed

    private void btnRSSach6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach6ActionPerformed
        exportToExcel(modelEmployee);
    }//GEN-LAST:event_btnRSSach6ActionPerformed

    private void btnRSSach9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach9ActionPerformed
        exportToExcel(modelAccount);
    }//GEN-LAST:event_btnRSSach9ActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int i = tblHoaDon.getSelectedRow();
        System.out.println("hàng: "+i);
        if(i>=0){
            txtMaHD.setText(tblHoaDon.getModel().getValueAt(i, 1).toString());
            cbKhachHang.getModel().setSelectedItem(tblHoaDon.getModel().getValueAt(i, 2).toString());
            try {
                Date ngayLap = vn.parse(tblHoaDon.getModel().getValueAt(i, 3).toString());
                dateNgayLapHD.setDate(ngayLap);
            } catch (ParseException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtTongTienHD.setText(tblHoaDon.getModel().getValueAt(i, 4).toString());
            
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void lblFindBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFindBillMouseClicked
        
        String timkiem = txtFindBill.getText();
        if (timkiem.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập thông tin tìm kiếm");
            txtFindBill.requestFocus();
        } else {
            BillBUS bill_bus = new BillBUS();
            ArrayList<Bill> findBill = new ArrayList<>();
            int selected = comHoaDon.getSelectedIndex();
            switch (selected) {
                case 0:
                    
                    if (Error.containsNumber(timkiem) || Error.containsSpecialChar(timkiem)){
                        JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự đặc biệt và số");
                        txtFindBill.requestFocus();
                    }
                    else
                        findBill = bill_bus.findBillByCustomer(timkiem);
                    break;
                case 1:
                    Date ngayLap = parseDate(timkiem);
                    findBill = bill_bus.findBillByDate(ngayLap);
                    break;
                case 2:
                   if (Error.containsNumber(timkiem) || Error.containsSpecialChar(timkiem)){
                        JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự đặc biệt và số");
                        txtFindBill.requestFocus();
                    }
                    else
//                        findBill = bill_bus.findBillByEmployee(timkiem);
                        bill_bus.readListBill(2);
                        findBill = bill_bus.listBill;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Bạn hãy chọn mục cần tìm kiếm");
            }
            if (findBill.isEmpty()){
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả tìm kiếm");
                txtFindBill.requestFocus();
            }
            else{
                CustomerBUS cus_bus = new CustomerBUS();
                modelBill.setRowCount(0);
                int i = 1;
                for (Bill bill : findBill) {
                    Vector row = new Vector();
                    row.add(i++);
                    row.add(bill.getIdBill());
                    row.add(cus_bus.findCustomerByID(bill.getIdCustomer()).getFullName());
                    String dateVN  = vn.format(bill.getDate());
                    row.add(dateVN);
                    row.add(Math.round(bill.getTotal()));
                    modelBill.addRow(row);
                }
                tblHoaDon.setModel(modelBill);
            }
        }
    }//GEN-LAST:event_lblFindBillMouseClicked

    private void btnRSSach3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach3ActionPerformed
        exportToExcel(modelBill);
    }//GEN-LAST:event_btnRSSach3ActionPerformed

    private void btnRSSach4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach4ActionPerformed
        exportToExcel(modelImport);
    }//GEN-LAST:event_btnRSSach4ActionPerformed

    private void btnInPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPNActionPerformed
        int select = tblPhieuNhap.getSelectedRow();
        if(select<0){
            JOptionPane.showMessageDialog(null, "Hãy chọn phiếu nhập cần in");
            return;
        }
        try {
            // xử lí tìm thông tin
            int idNCC = ImportBUS.listImport.get(select).getIdProvider();
            EmployeeBUS bus_emp = new EmployeeBUS();
            ProviderBUS bus_pro = new ProviderBUS();
            
            String tenNCC = bus_pro.findNameByID(idNCC);
            String diaChi = bus_pro.findAddressByID(idNCC);
            String tenNhanVien = bus_emp.findFullnameByID(idCurrent);
            String ngayNhap =  tblPhieuNhap.getValueAt(select, 3).toString();
            
            
            int maPN = (int)tblPhieuNhap.getValueAt(select, 1);
            
            
            BaseFont bf = BaseFont.createFont("/Font/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
            
            URL url = getClass().getResource("/report/import/");
            
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array.toString());
            
            
//            String file_dict = "C:\\Users\\ngoccanh\\Desktop\\" + random + ".pdf";
            
            
            String file_dict = url.getPath() + generatedString +".pdf";
            
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(file_dict));
            doc.open();
            
            Paragraph nameStore = new Paragraph("DCT BOOKSTORE", new Font(bf, 20));
            nameStore.setAlignment(1);
            doc.add(nameStore);
            
            Paragraph address = new Paragraph("273 An Dương Vương, P.3, Q.5, TP.HCM (0366476592)", new Font(bf, 17));
            address.setAlignment(1);
            doc.add(address);
            
            
            Paragraph title = new Paragraph("PHIẾU NHẬP SÁCH", new Font(bf, 22));
            title.setAlignment(1);
            doc.add(title);
            enterInPDF(doc, 2);
            
            //xuất mã hoá đơn, tên nhân viên, ngày lập hoá đơn  
            Paragraph idBill = new Paragraph("Mã phiếu nhập: " + maPN, new Font(bf, 18));
            Paragraph employee = new Paragraph("Nhân viên: " + tenNhanVien, new Font(bf, 18));
            Paragraph curDate = new Paragraph("Ngày nhập: " +ngayNhap , new Font(bf, 18));
            Paragraph nameProvider = new Paragraph("Nhà cung cấp: " + tenNCC, new Font(bf, 18));
            Paragraph addressProvider = new Paragraph("Địa chỉ: " + diaChi, new Font(bf, 18));
            doc.add(idBill);
            enterInPDF(doc, 1);
            doc.add(nameProvider);
            enterInPDF(doc, 1);
            doc.add(addressProvider);
            enterInPDF(doc, 1);
            doc.add(employee);
            enterInPDF(doc, 1);
            doc.add(curDate);
            enterInPDF(doc, 2);
            //xuất dữ liệu
            PdfPTable table = new PdfPTable(5);
            
            // tạo header
            String content[]={
                "STT", "Tên sách", "Số lượng","Đơn giá","Thành tiền"};
            for (String name : content) {
                PdfPCell cHeader = new PdfPCell(new Paragraph(name, new Font(bf, 18)));
                cHeader.setHorizontalAlignment(1);
                table.addCell(cHeader);
            }
   
           
            //quá trình thêm data vào table
            BookBUS book_bus = new BookBUS();
            if(BookBUS.listBook==null)
                book_bus.readListBook();
            ImportDetailBUS detail_bus = new ImportDetailBUS();
            if(ImportDetailBUS.listImportDetail==null)
                detail_bus.readListImportDetail();
            ArrayList<ImportDetail> list = detail_bus.findByImportID(maPN);
            String[] data = new String[list.size()*5];
            int index = 0, stt=1;
            for(ImportDetail items : list ){
                data[index++] = Integer.toString(stt++); 
                data[index++] = book_bus.findNameByID(items.getIdBook());// tìm tên sách bằng id sách 
                data[index++] = Integer.toString(items.getQuantity()); // số lượng lượng
                data[index++] = Float.toString(items.getPrice());
                data[index++] = Float.toString(items.getTotalPrice());// thành tiền
            }
            
        
            for (String cellData : data) {
                System.out.println(cellData);
                PdfPCell cell = new PdfPCell(new Paragraph(cellData, new Font(bf, 18)));
                table.addCell(cell);
            }
            doc.add(table);
            
            Paragraph total = new Paragraph("Tổng tiền: " +tblPhieuNhap.getValueAt(select, 4).toString() +" VND" , new Font(bf, 18));
            doc.add(total);
            //đóng file

            doc.close();
            JOptionPane.showMessageDialog(null, "In phiếu nhập thành công");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi in phiếu nhập");
            Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi in phiếu nhập");
            Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi in phiếu nhập");
            Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInPNActionPerformed

    private void lblFindImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFindImportMouseClicked
        String timkiem = txtFindImport.getText();
        if (timkiem.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập thông tin tìm kiếm");
            txtFindImport.requestFocus();
        } else {
            ImportBUS import_bus = new ImportBUS();
            ArrayList<Import> findImport = new ArrayList<>();
            int selected = comPhieuNhap.getSelectedIndex();
            switch (selected) {
                case 0:
                    
                    if (Error.containsNumber(timkiem) || Error.containsSpecialChar(timkiem)){
                        JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự đặc biệt và số");
                        txtFindImport.requestFocus();
                    }
                    else
                        findImport = import_bus.findImportByProvider(timkiem);
                    break;
                case 1:
                    Date ngayLap = parseDate(timkiem);
                    findImport = import_bus.findImportByDate(ngayLap);
                    break;
                case 2:
                   if (Error.containsNumber(timkiem) || Error.containsSpecialChar(timkiem)){
                        JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự đặc biệt và số");
                        txtFindImport.requestFocus();
                    }
                    else
//                        findImport = import_bus.findImportByEmployee(timkiem);
                       import_bus.readListImport(3);
                       findImport = import_bus.listImport;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Bạn hãy chọn mục cần tìm kiếm");
            }
            if (findImport.isEmpty()){
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả tìm kiếm");
                txtFindImport.requestFocus();
            }
            else{
                modelImport.setRowCount(0);
                int i = 1;
                
                ProviderBUS pro_bus = new ProviderBUS();
                for (Import imp : findImport) {
                    Vector row = new Vector();
                    row.add(i++);
                    row.add(imp.getIdImport());
                    String nameProvider = pro_bus.findProviderByID(imp.getIdProvider()).getNameProvider();
                    row.add(nameProvider);
                    String dateVN  = vn.format(imp.getDate());
                    row.add(dateVN);
                    row.add(Math.round(imp.getTotal()));
                    modelImport.addRow(row);
                }
                tblPhieuNhap.setModel(modelImport);
            }
        }       
    }//GEN-LAST:event_lblFindImportMouseClicked

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed
        int select = tblHoaDon.getSelectedRow();
        if(select<0){
            JOptionPane.showMessageDialog(null, "Hãy chọn hoá đơn cần in");
            return;
        }
        try {
            // xử lí tìm thông tin
            int idCustomer =  BillBUS.listBill.get(select).getIdCustomer();
            EmployeeBUS bus_emp = new EmployeeBUS();
            CustomerBUS bus_cus = new CustomerBUS();
            
            String tenKhachHang = bus_cus.findFullnameByID(idCustomer);
            
            String tenNhanVien = bus_emp.findFullnameByID(idCurrent);
            String ngayLap =  tblHoaDon.getValueAt(select, 3).toString();
            int maHD = (int)tblHoaDon.getValueAt(select, 1);
            
            
            BaseFont bf = BaseFont.createFont("/Font/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
            URL url = getClass().getResource("/report/bill/");

            
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array.toString());
            
            System.out.println(generatedString);
            
//            String file_dict = "C:\\Users\\ngoccanh\\Desktop\\" + random + ".pdf";
            
            String file_dict = url.getPath() + generatedString + ".pdf";
            
            
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(file_dict));
            doc.open();
            
            
            Paragraph nameStore = new Paragraph("DCT BOOKSTORE", new Font(bf, 20));
            nameStore.setAlignment(1);
            doc.add(nameStore);
            
            Paragraph address = new Paragraph("273 An Dương Vương, P.3, Q.5, TP.HCM (0366476592)", new Font(bf, 17));
            address.setAlignment(1);
            doc.add(address);
            
            
            
            Paragraph title = new Paragraph("HOÁ ĐƠN THANH TOÁN", new Font(bf, 19));
            title.setAlignment(1);
            doc.add(title);
            enterInPDF(doc, 2);
            
            //xuất mã hoá đơn, tên nhân viên, ngày lập hoá đơn  
            Paragraph idBill = new Paragraph("Mã hoá đơn: " + maHD, new Font(bf, 18));
            Paragraph employee = new Paragraph("Nhân viên: " + tenNhanVien, new Font(bf, 18));
            Paragraph curDate = new Paragraph("Ngày: " +ngayLap , new Font(bf, 18));
            Paragraph customer = new Paragraph("Khách hàng: " + tenKhachHang, new Font(bf, 18));
            doc.add(idBill);
            enterInPDF(doc, 1);
            doc.add(customer);
            enterInPDF(doc, 1);
            doc.add(employee);
            enterInPDF(doc, 1);
            doc.add(curDate);
            enterInPDF(doc, 2);
            //xuất dữ liệu
            PdfPTable table = new PdfPTable(5);
            
            // tạo header
            String content[]={
                "STT", "Sách", "Số lượng","Đơn giá","Thành tiền"};
            for (String name : content) {
                PdfPCell cHeader = new PdfPCell(new Paragraph(name, new Font(bf, 18)));
                cHeader.setHorizontalAlignment(1);
                table.addCell(cHeader);
            }
   
           
            //quá trình thêm data vào table
            BookBUS book_bus = new BookBUS();
            if(BookBUS.listBook==null)
                book_bus.readListBook();
            BillDetailBUS detail_bus = new BillDetailBUS();
            if(BillDetailBUS.listBillDetail==null)
                detail_bus.readListBillDetail();
            
            ArrayList<BillDetail> list = detail_bus.findByBillID(maHD);
            String[] data = new String[list.size()*5];
            int index = 0, stt=1;
            for(BillDetail items : list ){
                data[index++] = Integer.toString(stt++); 
                data[index++] = book_bus.findNameByID(items.getIdBook());// tìm tên sách bằng id sách 
                data[index++] = Integer.toString(items.getQuantity()); // số lượng
                data[index++] = Float.toString(book_bus.findPriceByID(items.getIdBook()));// tìm giá sách bằng id sách 
                data[index++] = Float.toString(items.getTotalPrice());// thành tiền
            }
            
        
            for (String cellData : data) {
                System.out.println(cellData);
                PdfPCell cell = new PdfPCell(new Paragraph(cellData, new Font(bf, 18)));
                table.addCell(cell);
            }
            doc.add(table);
            
            Paragraph total = new Paragraph("Tổng tiền: " +tblHoaDon.getValueAt(select, 4).toString() +" VND" , new Font(bf, 18));
            total.setAlignment(0);
            doc.add(total);
            
            Paragraph thankyou = new Paragraph("Xin cảm ơn và hẹn gặp lại!", new Font(bf, 16));
            thankyou.setAlignment(1);
            doc.add(thankyou);
            
            //đóng file

            doc.close();
            JOptionPane.showMessageDialog(null, "In hóa đơn thành công");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi in hóa đơn");
            Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi in hóa đơn");
            Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi in hóa đơn");
            Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInHDActionPerformed

    private void btnTKSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKSLActionPerformed
        Date ngayBD = dateTuNgaySL.getDate();
        Date ngayKT = dateDenNgaySL.getDate();
        BillBUS bus = new BillBUS();
        ArrayList<Bill> listBill = bus.findBillByDate(ngayBD, ngayKT);
        if(listBill.isEmpty()){
            JOptionPane.showMessageDialog(null, "Thời gian này chưa bán được sách");
        } else{
                int tongSL = 0;
                modelSachBan.setRowCount(0);
                modelSachBan = (DefaultTableModel) tblTK_SL.getModel();
                BillDetailBUS detail_bus = new BillDetailBUS();
                if(BillDetailBUS.listBillDetail==null)
                    detail_bus.readListBillDetail();
                int i = 1;
                BookBUS book_bus = new BookBUS();
                for (Bill b : listBill) {
                    ArrayList<BillDetail> listdetail = detail_bus.findByBillID(b.getIdBill());
                    for(BillDetail detail : listdetail){
                        Vector row = new Vector();
                        row.add(i++);
                        row.add(book_bus.findBookByID(detail.getIdBook()).getNameBook());
                        row.add(detail.getQuantity());
                        row.add(Math.round(detail.getTotalPrice()));

                        modelSachBan.addRow(row);
                        tongSL += detail.getQuantity();
                    }
                }
                tblTK_SL.setModel(modelSachBan);
                txtTK_SL.setText(Integer.toString(tongSL));
                 
        }
        
    }//GEN-LAST:event_btnTKSLActionPerformed

    private void btnTKDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKDTActionPerformed
        Date ngayBD = dateTuNgayDT.getDate();
        Date ngayKT = dateDenNgayDT.getDate();
        BillBUS bus = new BillBUS();
        ArrayList<Bill> find = bus.findBillByDate(ngayBD, ngayKT);
        if(find.isEmpty()){
            JOptionPane.showMessageDialog(null, "Không tìm thấy hoá đơn");
        } else{
                float doanhThu = 0;
                modelThongKe.setRowCount(0);
                modelThongKe = (DefaultTableModel) tblTK_DT.getModel();
                
                EmployeeBUS emp_bus = new EmployeeBUS();
                CustomerBUS cus_bus = new CustomerBUS();
                
                int i = 1;
                for (Bill b : find) {
                    Vector row = new Vector();
                    row.add(i++);
                    row.add(b.getIdBill());
                    row.add(emp_bus.findEmployeeByID(b.getIdEmployee()).getFullName());
                    row.add(cus_bus.findCustomerByID(b.getIdCustomer()).getFullName());
                    row.add(Math.round(b.getTotal()));
                    modelThongKe.addRow(row);
                    doanhThu += b.getTotal();
                }
                tblTK_DT.setModel(modelThongKe);
                txtTK_DT.setText(Float.toString(doanhThu));
        }
    }//GEN-LAST:event_btnTKDTActionPerformed

    private void btnRSSach8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach8ActionPerformed
        exportToExcel(modelThongKe);
    }//GEN-LAST:event_btnRSSach8ActionPerformed

    private void btnRSSach11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach11ActionPerformed
        exportToExcel(modelDetailDiscount);
    }//GEN-LAST:event_btnRSSach11ActionPerformed

    private void btnRSSach10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach10ActionPerformed
        exportToExcel(modelDiscount);
    }//GEN-LAST:event_btnRSSach10ActionPerformed

    private void tblChiTietGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietGiamGiaMouseClicked
        int i = tblChiTietGiamGia.getSelectedRow();
        System.out.println("hàng: " + i);
        int column = 1;
        if (i >= 0) {
            String Discount = tblChiTietGiamGia.getModel().getValueAt(i, column++).toString();
            String Book = tblChiTietGiamGia.getModel().getValueAt(i, column++).toString();
            cbGG.getModel().setSelectedItem(Discount);
            cbSach_GG.getModel().setSelectedItem(Book);
            txtChietKhau.setText(tblChiTietGiamGia.getModel().getValueAt(i, column++).toString());
        }
    }//GEN-LAST:event_tblChiTietGiamGiaMouseClicked

    private void tblGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamGiaMouseClicked
        int i = tblGiamGia.getSelectedRow();
        System.out.println("hàng: "+i);
        int column = 1;
        if(i>=0){
            txtTenCT.setText(tblGiamGia.getModel().getValueAt(i, column++ ).toString());
            try {
                Date dateStart = vn.parse(tblGiamGia.getModel().getValueAt(i, column++ ).toString());
                dateNgayBD.setDate(dateStart);
                Date dateEnd = vn.parse(tblGiamGia.getModel().getValueAt(i, column++ ).toString());
                dateNgayKT.setDate(dateEnd);
            } catch (ParseException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblGiamGiaMouseClicked

    private void btnXoaCTGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaCTGGMouseClicked
        // TODO add your handling code here:
         // TODO add your handling code here:
        int i = tblChiTietGiamGia.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn chi tiết cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự  muốn xoá chi tiết này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                DiscountDetailBUS bus_detail = new DiscountDetailBUS();
                
//                // Loi
//                int idDiscount = DiscountDetailBUS.listDiscountDetail.get(i).getIdDiscount();
//                int idBook = BookBUS.listBook.get(i).getIdBook();
                
                int idDiscount = DiscountDetailBUS.listDiscountDetail.get(i).getIdDiscount();
                int idBook = DiscountDetailBUS.listDiscountDetail.get(i).getIdBook();
                
                if (bus_detail.delete(idDiscount, idBook)){
                    modelDetailDiscount.removeRow(i);
                    tblChiTietGiamGia.setModel(modelDetailDiscount);
                    JOptionPane.showMessageDialog(rootPane, "Xoá thành công");
                }
                else JOptionPane.showMessageDialog(rootPane, "Xoá thất bại");
                
            }
        }
    }//GEN-LAST:event_btnXoaCTGGMouseClicked

    private void btnSuaCTGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaCTGGMouseClicked
        int i = tblChiTietGiamGia.getSelectedRow();
        System.out.println("hàng " + i);
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn chi tiết cần sửa");
        } else {
            
            String newProgram = cbGG.getSelectedItem().toString(); 
            String newBook = cbSach_GG.getSelectedItem().toString();
            String chietkhau = txtChietKhau.getText();


            if (newProgram.equals("") || newBook.equals("") || chietkhau.equals("")){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thong tin");
                cbGG.requestFocus();
            }

            if (Error.containsSpecialChar(chietkhau) || !Error.isInteger(chietkhau) || Error.isNagativeNumber(Integer.parseInt(chietkhau))){
                JOptionPane.showMessageDialog(null, "Chiết khấu phải là số nguyên dương");
                txtChietKhau.requestFocus();
            }
            
            if (Integer.parseInt(chietkhau) < 1 || Integer.parseInt(chietkhau) > 100) 
                JOptionPane.showMessageDialog(null, "Phần trăm chiết khấu phải từ 1 đến 100%");
            else{
                
                // Lấy mã sách cũ và mã giảm giá cũ để cập nhật
                DiscountDetailBUS bus_detail = new DiscountDetailBUS();
                
//                int oldProgram = bus_detail.listDiscountDetail.get(i).getIdDiscount();
//                int oldBook = bus_detail.listDiscountDetail.get(i).getIdBook();


                 String oldNameProgram = tblChiTietGiamGia.getValueAt(i, 1).toString();
                 String oldNameBook = tblChiTietGiamGia.getValueAt(i, 2).toString();
                 
                 
                 DiscountBUS discountBUS = new DiscountBUS();
                 BookBUS bookBUS = new BookBUS();
                 
                 int oldProgram = DiscountDetailBUS.listDiscountDetail.get(i).getIdDiscount();
                 int oldBook = DiscountDetailBUS.listDiscountDetail.get(i).getIdBook();
                 
                 
                DiscountDetail detail = new DiscountDetail();
                
                detail.setDiscount(Integer.parseInt(txtChietKhau.getText()));
                detail.setIdDiscount(oldProgram);
                detail.setIdBook(oldBook);

//                if (DiscountDetailBUS.listDiscountDetail == null) {
//                    bus_detail.readListDiscountDetail();
//                }
                
                
                if (bus_detail.update(detail, oldProgram, oldBook)){
                    int column = 1;
                    modelDetailDiscount.setValueAt(newProgram, i, column++);
                    modelDetailDiscount.setValueAt(newBook, i, column++);
                    modelDetailDiscount.setValueAt(chietkhau, i, column++);
                    tblChiTietGiamGia.setModel(modelDetailDiscount);
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
                }
                else JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");
                    
//                

                
//                DiscountDetail old = DiscountDetailBUS.listDiscountDetail.get(i);
//                System.out.println(old.getIdBook() + " " + old.getIdDiscount());
//                // kiểm tra không cho thay đổi mã chi tiết
//                if (!detail.getIdBook().equals(old.getIdBook()) || !detail.getIdDiscount().equals(old.getIdDiscount()) ) {
//                    JOptionPane.showMessageDialog(rootPane, "Mã giảm và mã sách không thể thay đổi, cập nhật thất bại");
//                } else {
                    
//                }
            }
        }
    }//GEN-LAST:event_btnSuaCTGGMouseClicked

    private void btnThemCTGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemCTGGMouseClicked

        String nameProgram =  cbGG.getSelectedItem().toString();
        String nameBook = cbSach_GG.getSelectedItem().toString();
        String chietkhau = txtChietKhau.getText();
        
        
        if (nameProgram.equals("") || nameBook.equals("") || chietkhau.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thong tin");
        }
        
        if (Error.containsSpecialChar(chietkhau) || !Error.isInteger(chietkhau) || Error.isNagativeNumber(Integer.parseInt(chietkhau))){
            JOptionPane.showMessageDialog(null, "Chiết khấu phải là số nguyên dương");
            txtChietKhau.requestFocus();
        }
        
        if (Integer.parseInt(chietkhau) < 1 || Integer.parseInt(chietkhau) > 100){
            JOptionPane.showMessageDialog(null, "Chiết khấu phải là số từ 1 đến 100%");
        }
        else{
            DiscountDetail detail = new DiscountDetail();
            detail.setDiscount(Integer.parseInt(txtChietKhau.getText()));
            detail.setIdDiscount(DiscountBUS.listDiscount.get(cbGG.getSelectedIndex()).getIdDiscount());
            detail.setIdBook(BookBUS.listBook.get(cbSach_GG.getSelectedIndex()).getIdBook());

//            Vector row = new Vector();
//            row.add(tblChiTietGiamGia.getRowCount() + 1);
//            row.add(nameProgram);
//            row.add(nameBook);
//            row.add(detail.getDiscount());
            DiscountDetailBUS bus_detail = new DiscountDetailBUS();
            if (!bus_detail.add(detail)) {
                JOptionPane.showMessageDialog(null, "Chi tiết giảm giá đã tồn tại");
                txtChietKhau.requestFocus();
            } else {
//                modelDetailDiscount.addRow(row);
//                tblChiTietGiamGia.setModel(modelDetailDiscount);
                readTableDetailDiscount();
                JOptionPane.showMessageDialog(null, "Thêm thành công");
            }
        }
        
    }//GEN-LAST:event_btnThemCTGGMouseClicked

    private void btnLamMoiGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiGGMouseClicked
        setCbDiscount();
        setCbBook();
        dateNgayBD.setDate(null);
        dateNgayKT.setDate(null);
        txtTenCT.setText("");
        txtTenCT.requestFocus();
        readTableDiscount();
    }//GEN-LAST:event_btnLamMoiGGMouseClicked

    private void btnXoaGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaGGMouseClicked
        int i = tblGiamGia.getSelectedRow();
        if(i==-1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn giảm giá cần xóa");
        }else{
            int result = JOptionPane.showConfirmDialog(rootPane,"Bạn có thực sự muốn xóa giảm giá này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                DiscountBUS bus_discount = new DiscountBUS();
                int idDiscount = DiscountBUS.listDiscount.get(i).getIdDiscount();
                System.out.println(idDiscount);
                if(bus_discount.delete(idDiscount, i)){
                    modelDiscount.removeRow(i);
                    tblGiamGia.setModel(modelDiscount);
                    setCbDiscount();
                    JOptionPane.showMessageDialog(rootPane, "Xoá thành công");
                }
                else 
                    JOptionPane.showMessageDialog(rootPane, "Xoá thất bại");
            }
        }
    }//GEN-LAST:event_btnXoaGGMouseClicked

    private void btnSuaGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaGGMouseClicked
       int i = tblGiamGia.getSelectedRow();
        System.out.println("hàng " + i);
        if(i==-1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn giảm giá cần sửa");
        } else{
            
            if (txtTenCT.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên chương trình");
                txtTenCT.requestFocus();
            }

            if (Error.containsSpecialChar(txtTenCT.getText())){
                JOptionPane.showMessageDialog(null, "Tên chương trình không được chứa ký tự đặc biệt");
                txtTenCT.requestFocus();
            }
            
            Discount discount = new Discount();
            discount.setIdDiscount(DiscountBUS.listDiscount.get(i).getIdDiscount());
            discount.setNameProgram(txtTenCT.getText());
            String StartVN  = vn.format(dateNgayBD.getDate());
            String EndVN  = vn.format(dateNgayKT.getDate());
            try{
                String Start  = eng.format(dateNgayBD.getDate());
                String End  = eng.format(dateNgayKT.getDate());

                Date dateStart = eng.parse(Start);
                discount.setDateStart(dateStart);
                Date dateEnd = eng.parse(End);
                discount.setDateEnd(dateEnd);
            }catch(ParseException ex){
                Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE,null,ex);
            }
            DiscountBUS discountbus = new DiscountBUS();
//            if(DiscountBUS.listDiscount ==null){
//                discountbus.readListDiscount();
//            }

            if (discountbus.update(discount, i)){
                int column = 1;
                modelDiscount.setValueAt(discount.getNameProgram(), i, column++);
                modelDiscount.setValueAt(StartVN, i, column++);
                modelDiscount.setValueAt(EndVN, i, column++);
                tblGiamGia.setModel(modelDiscount);
                setCbDiscount();
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
            }
            else JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");
            
        }
    }//GEN-LAST:event_btnSuaGGMouseClicked

    private void btnThemGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemGGMouseClicked
        
        if (txtTenCT.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên chương trình");
            txtTenCT.requestFocus();
        }
        
        if (Error.containsSpecialChar(txtTenCT.getText())){
            JOptionPane.showMessageDialog(null, "Tên chương trình không được chứa ký tự đặc biệt");
            txtTenCT.requestFocus();
        }
        
        Discount discount = new Discount();
        DiscountBUS discount_bus = new DiscountBUS();
        
        discount.setNameProgram(txtTenCT.getText());
        String StartVN  = vn.format(dateNgayBD.getDate());
        String EndVN  = vn.format(dateNgayKT.getDate());
        try {
            String Start  = eng.format(dateNgayBD.getDate());
            String End  = eng.format(dateNgayKT.getDate());

            Date dateStart = eng.parse(Start);
            discount.setDateStart(dateStart);
            Date dateEnd = eng.parse(End);
            discount.setDateEnd(dateEnd);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

//        Vector row = new Vector();
//        row.add(tblGiamGia.getRowCount() + 1);
//        row.add(discount.getNameProgram());
//        row.add(StartVN);
//        row.add(EndVN);
        DiscountBUS bus_discount = new DiscountBUS();
        if (!bus_discount.add(discount)) {
            JOptionPane.showMessageDialog(null, "Chương trình này đã tồn tại");
            txtTenCT.requestFocus();
        } else {
//            modelDiscount.addRow(row);
//            tblGiamGia.setModel(modelDiscount);
            readTableDiscount();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btnThemGGMouseClicked

    private void btnXoaTGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaTGMouseClicked
        // TODO add your handling code here:
        int i = tblTacGia.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn tác giả cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự muốn xoá tác giả này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                AuthorBUS authbus = new AuthorBUS();
                int idAuthor = AuthorBUS.listAuthor.get(i).getIdAuthor();
                System.out.println(idAuthor);
                if(!authbus.delete(idAuthor, i)){
                    JOptionPane.showMessageDialog(rootPane, "Xoá thất bại");
                    return;
                }
                modelAuthor.removeRow(i);
                tblTacGia.setModel(modelAuthor);
                setCbAuthor();
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
            }
        }
    }//GEN-LAST:event_btnXoaTGMouseClicked

    private void btnSuaTGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaTGMouseClicked
        // TODO add your handling code here:
        int i = tblTacGia.getSelectedRow();
        
        
        if (i == -1){
             JOptionPane.showMessageDialog(rootPane, "Hãy chọn tác giả cần sửa");
        }
            else{
                String lastname = txtHoTacGia.getText();
                String firstname = txtTenTacGia.getText();

                if (lastname.equals("") || firstname.equals("")){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                if (Error.containsNumber(lastname) || Error.containsSpecialChar(lastname)){
                    JOptionPane.showMessageDialog(null, "Họ và tên lót không được chứa ký tự đặc biệt và số");
                    return;
                }

                if (Error.containsNumber(firstname) || Error.containsSpecialChar(firstname)){
                    JOptionPane.showMessageDialog(null, "Tên không được chứa ký tự đặc biệt và số");
                    return;
                }


                Author auth = new Author();
                AuthorBUS authbus = new AuthorBUS();

                System.out.println("hàng " + i);
                Author old = AuthorBUS.listAuthor.get(i);
                System.out.println("Mã sách cũ " + old.getIdAuthor());
                if (AuthorBUS.listAuthor == null) {
                    authbus.readListAuthor();
                }

                int idTacGia = AuthorBUS.listAuthor.get(i).getIdAuthor();
                auth.setIdAuthor(idTacGia);
                auth.setNameAuthor(lastname, firstname);


                if (authbus.update(auth , i)){
                     modelAuthor.setValueAt(auth.getFullName(), i, 1);
                    tblTacGia.setModel(modelAuthor);
                    setCbAuthor();
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
                }
                else JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");
            }
        
    }//GEN-LAST:event_btnSuaTGMouseClicked

    private void btnThemTGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemTGMouseClicked
        // TODO add your handling code here:
        String lastname = txtHoTacGia.getText();
        String firstname = txtTenTacGia.getText();
        
        if (lastname.equals("") || firstname.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        if (Error.containsNumber(lastname) || Error.containsSpecialChar(lastname)){
            JOptionPane.showMessageDialog(null, "Họ và tên lót không được chứa ký tự đặc biệt và số");
            return;
        }

        if (Error.containsNumber(firstname) || Error.containsSpecialChar(firstname)){
            JOptionPane.showMessageDialog(null, "Tên không được chứa ký tự đặc biệt và số");
            return;
        }
        
        Author auth = new Author();
        AuthorBUS authbus = new AuthorBUS();

        auth.setNameAuthor(txtHoTacGia.getText(), txtTenTacGia.getText());
        
//        Vector row = new Vector();
//        row.add(tblTacGia.getRowCount() + 1);
//        row.add(auth.getFullName());

        if (!authbus.add(auth)) {
            JOptionPane.showMessageDialog(null, "Tác giả này đã tồn tại");
            txtHoTacGia.requestFocus();
        } else {
            
//            modelAuthor.addRow(row);
//            tblTacGia.setModel(modelAuthor);
            readTableAuthor();
            setCbAuthor();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btnThemTGMouseClicked

    private void btnRSSach13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach13ActionPerformed
        exportToExcel(modelAuthor);
    }//GEN-LAST:event_btnRSSach13ActionPerformed

    private void lblFindAuthorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFindAuthorMouseClicked
        // TODO add your handling code here:
        String timkiem = txtFindAuthor.getText();
        if (timkiem.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập thông tin tìm kiếm");
            txtFindAuthor.requestFocus();
        } else {
            
            if (Error.containsNumber(timkiem) || Error.containsSpecialChar(timkiem)){
                JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được chứa ký tự và số");
                txtFindAuthor.requestFocus();
            }
            else{
                AuthorBUS authorbus = new AuthorBUS();
                ArrayList<Author> findAuthor = new ArrayList<Author>();
                
                findAuthor = authorbus.findAuthorByFullname(timkiem);
                if (findAuthor.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả tìm kiếm");
                    txtFindAuthor.requestFocus();
                } 
                else {
                    modelAuthor.setRowCount(0);
                    int i = 1;
                    for (Author tName : findAuthor) {
                        Vector row = new Vector();
                        row.add(i++);
                        row.add(tName.getFullName());
                        modelAuthor.addRow(row);
                    }
                    tblTacGia.setModel(modelAuthor);
                }
            }
        }
    }//GEN-LAST:event_lblFindAuthorMouseClicked

    private void txtFindAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindAuthorActionPerformed

    private void btnLamMoiTGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiTGMouseClicked
        // TODO add your handling code here:
        txtFindAuthor.setText("");
        txtHoTacGia.setText("");
        txtTenTacGia.setText("");
        txtHoTacGia.requestFocus();
        readTableAuthor();
    }//GEN-LAST:event_btnLamMoiTGMouseClicked

    private void btnThemHD1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemHD1MouseClicked
        // TODO add your handling code here:
        Bill bill = new Bill();
        BillBUS bill_bus = new BillBUS();

        CustomerBUS cus_bus = new CustomerBUS();
        

        
        // Lấy mã khách hàng dựa vào phương thức get của list với tham số là selectedindex trong combobox 
        int idCustomer = cus_bus.listCustomer.get(cbKhachHang.getSelectedIndex()).getIdCustomer();
        
        System.out.println(idCustomer);
        
        bill.setIdEmployee(idCurrent);
        bill.setIdCustomer(idCustomer);
        LocalDateTime now = LocalDateTime.now();
        Date dateNow = new Date();
        String dateEng  = eng.format(dateNow);
        String dateVN  = vn.format(dateNow);
        try {
            Date date = eng.parse(dateEng);
            bill.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        if (!bill_bus.add(bill)) {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
            txtMaHD.requestFocus();
        }
        else {
//            Vector row = new Vector();
//            row.add(tblHoaDon.getRowCount() + 1);
//            row.add(bill.getIdBill());
//            row.add(cus_bus.findCustomerByID(bill.getIdCustomer()).getFullName());
//            row.add(dateVN);
//            row.add(bill.getTotal());
//            modelBill.addRow(row);
//            tblHoaDon.setModel(modelBill);
            readTableBill(idCurrent);
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btnThemHD1MouseClicked

    private void btnLamMoiHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiHDMouseClicked
        // TODO add your handling code here:
        txtMaHD.setText("");
        txtFindBill.setText("");
        txtMaHD.requestFocus();
        dateNgayLapHD.setDate(null);
        txtTongTienHD.setText("");
        setCbCustomer();
        if (idCurrent == 1) readTableBill();
        else readTableBill(idCurrent);
    }//GEN-LAST:event_btnLamMoiHDMouseClicked

    private void btnThemPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemPNMouseClicked
        // TODO add your handling code here:
        Import imp = new Import();
        ImportBUS imp_bus = new ImportBUS();
        EmployeeBUS emp_bus = new EmployeeBUS();
        
        imp.setIdEmployee(idCurrent);
        imp.setIdProvider(ProviderBUS.listProvider.get(cbNCC.getSelectedIndex()).getIdProvider());

        
        // Get current date
        LocalDateTime now = LocalDateTime.now();
        Date dateNow = new Date();
        String dateEng  = eng.format(dateNow);
        String dateVN  = vn.format(dateNow);
        try {
            Date date = eng.parse(dateEng);
            imp.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        

//        Vector row = new Vector();
//        row.add(tblPhieuNhap.getRowCount() + 1);
//        row.add(imp.getIdImport());
//        row.add(new ProviderBUS().findProviderByID(imp.getIdProvider()).getNameProvider());
//        row.add(dateVN);
//        row.add(imp.getTotal());
//           System.out.println(imp.getTotal());
        if (!imp_bus.add(imp)) {
            JOptionPane.showMessageDialog(null, "Mã phiếu nhập đã tồn tại");
            txtMaPhieuNhap.requestFocus();
        } else {
//            modelImport.addRow(row);
//            tblPhieuNhap.setModel(modelImport);
            readTableImport(idCurrent);
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btnThemPNMouseClicked

    private void btnLamMoiPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiPNMouseClicked
        // TODO add your handling code here:
        txtMaPhieuNhap.setText("");
        txtFindImport.setText("");
        txtMaPhieuNhap.requestFocus();
        dateNgayNhap.setDate(null);
        txtTongTienPN.setText("");
        setCbProvider();
        if (idCurrent == 1) readTableImport();
        else readTableImport(idCurrent);
    }//GEN-LAST:event_btnLamMoiPNMouseClicked

    private void btnLamMoiKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiKHMouseClicked
        // TODO add your handling code here:
        txtHOKH.setText("");
        txtHOKH.requestFocus();
        txtTenKH.setText("");
        txtSDTKH.setText("");
        dateNgaySinhKH.setDate(null);
        readTableCustomer();
    }//GEN-LAST:event_btnLamMoiKHMouseClicked

    private void btnXoaNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNVMouseClicked
        // TODO add your handling code here:
         int i = tblNhanVien.getSelectedRow();
        if(i==-1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn nhân viên cần xóa");
        }else{
            int result = JOptionPane.showConfirmDialog(rootPane,"Bạn có thực sự muốn xóa nhân viên này không?","Confirm", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                EmployeeBUS bus_emp = new EmployeeBUS();
                int idEmployee = EmployeeBUS.listEmployee.get(i).getIdEmployee();
                if(!bus_emp.delete(idEmployee, i)){
                    JOptionPane.showMessageDialog(rootPane, "Xoá thất bại");
                    return;
                }
                else{
                     modelEmployee.removeRow(i);
                    tblNhanVien.setModel(modelEmployee);
                    setCbEmployee();
                    JOptionPane.showMessageDialog(rootPane, "Xoá thành công");
                }
            }
        }
    }//GEN-LAST:event_btnXoaNVMouseClicked

    private void btnLamMoiNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiNVMouseClicked
        // TODO add your handling code here:
        txtHONV.setText("");
        txtHONV.requestFocus();
        txtTenNV.setText("");
        txtSDTNV.setText("");
        dateNgaySinhNV.setDate(null);
        readTableEmployee();
    }//GEN-LAST:event_btnLamMoiNVMouseClicked

    private void btnRSSach7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSSach7ActionPerformed
        // TODO add your handling code here:
        exportToExcel(modelSachBan);
    }//GEN-LAST:event_btnRSSach7ActionPerformed

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseClicked
        // TODO add your handling code here:
        int i = tblSach.getSelectedRow();
        System.out.println("hàng: " + i);
        int column = 1;
        if (i >= 0) {
            txtTenSach.setText(tblSach.getModel().getValueAt(i, column++).toString());
            txtSLSach.setText(tblSach.getModel().getValueAt(i, column++).toString());
            txtDonGia.setText(tblSach.getModel().getValueAt(i, column++).toString());
           
            cbTacGia.getModel().setSelectedItem(tblSach.getModel().getValueAt(i, column++).toString());
            cbTheLoai.getModel().setSelectedItem(tblSach.getModel().getValueAt(i, column++).toString());
            cbNXB.getModel().setSelectedItem(tblSach.getModel().getValueAt(i, column++).toString());
        }
    }//GEN-LAST:event_tblSachMouseClicked

    private void tblTheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTheLoaiMouseClicked
        // TODO add your handling code here:
        int i = tblTheLoai.getSelectedRow();
        System.out.println("hàng: " + i);
        if (i >= 0) {
            txtTenTL.setText(tblTheLoai.getModel().getValueAt(i, 1).toString());
        }
        System.out.println("Mã: "+BookTypeBUS.listBookType.get(i).getIdType());
    }//GEN-LAST:event_tblTheLoaiMouseClicked

    private void tblTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTacGiaMouseClicked
        // TODO add your handling code here:
        int i = tblTacGia.getSelectedRow();
        System.out.println("hàng: " + i);
        if (i >= 0) {
            AuthorBUS authbus = new AuthorBUS();
            int idTacGia = AuthorBUS.listAuthor.get(i).getIdAuthor();
            String lastname = authbus.findLastNameByID(idTacGia);
            txtHoTacGia.setText(lastname);
            String firstname = authbus.findFirstNameByID(idTacGia);
            txtTenTacGia.setText(firstname);
        }
    }//GEN-LAST:event_tblTacGiaMouseClicked

    private void tblNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNCCMouseClicked
        // TODO add your handling code here:
        int i = tblNCC.getSelectedRow();
        System.out.println("hàng" + i);
        int column = 1;
        if (i >= 0) {
            txtTenNCC.setText(tblNCC.getModel().getValueAt(i, column++).toString());
            txtDiaChiNCC.setText(tblNCC.getModel().getValueAt(i, column++).toString());
            txtSDTNCC.setText(tblNCC.getModel().getValueAt(i, column++).toString());
        }
    }//GEN-LAST:event_tblNCCMouseClicked

    private void tblNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNXBMouseClicked
        // TODO add your handling code here:
         int i = tblNXB.getSelectedRow();
        System.out.println("hàng" + i);
        int column = 1;
        if (i >= 0) {
            txtTenNXB.setText(tblNXB.getModel().getValueAt(i, column++).toString());
            txtDiaChiNXB.setText(tblNXB.getModel().getValueAt(i, column++).toString());
            txtSDTNXB.setText(tblNXB.getModel().getValueAt(i, column++).toString());
        }
    }//GEN-LAST:event_tblNXBMouseClicked

    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseClicked
        // TODO add your handling code here:
        int i = tblPhieuNhap.getSelectedRow();
        if (i >= 0) {
            txtMaPhieuNhap.setText(modelImport.getValueAt(i, 1).toString());
            cbNCC.getModel().setSelectedItem(modelImport.getValueAt(i, 2).toString());
            dateNgayNhap.setDate(parseDate(modelImport.getValueAt(i, 3).toString()));
            txtTongTienPN.setText(modelImport.getValueAt(i, 4).toString());
        }
    }//GEN-LAST:event_tblPhieuNhapMouseClicked

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        int i = tblKhachHang.getSelectedRow();
        System.out.println("hàng: "+i);
        int column = 1;
        if(i>=0){
            txtHOKH.setText(tblKhachHang.getModel().getValueAt(i, column++ ).toString());
            txtTenKH.setText(tblKhachHang.getModel().getValueAt(i, column++ ).toString());
            txtGioiTinhKH.getModel().setSelectedItem(tblKhachHang.getModel().getValueAt(i, column++ ).toString());
            try {
                Date BirthDay = vn.parse(tblKhachHang.getModel().getValueAt(i, column++ ).toString());
                dateNgaySinhKH.setDate(BirthDay);
            } catch (ParseException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtSDTKH.setText(tblKhachHang.getModel().getValueAt(i, column++ ).toString());
            
        }
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
         int i = tblNhanVien.getSelectedRow();
        System.out.println("hàng: "+i);
        int column = 1;
        if(i>=0){
            txtHONV.setText(tblNhanVien.getModel().getValueAt(i, column++ ).toString());
            txtTenNV.setText(tblNhanVien.getModel().getValueAt(i, column++ ).toString());
            txtGioiTinhNV.getModel().setSelectedItem(tblNhanVien.getModel().getValueAt(i, column++ ).toString());
            try {
                Date BirthDay = vn.parse(tblNhanVien.getModel().getValueAt(i, column++ ).toString());
                dateNgaySinhNV.setDate(BirthDay);
            } catch (ParseException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtSDTNV.setText(tblNhanVien.getModel().getValueAt(i, column++ ).toString());
            
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void tblAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblAccountMouseClicked

    private void tblTK_SLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTK_SLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTK_SLMouseClicked

    private void tblDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDoanhThuMouseClicked

    private void btnLamMoiNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiNCCMouseClicked
        // TODO add your handling code here:
        txtTenNCC.setText("");
        txtDiaChiNCC.setText("");
        txtSDTNCC.setText("");
        txtTenNCC.requestFocus();
        readTableProvider();
    }//GEN-LAST:event_btnLamMoiNCCMouseClicked

    private void btnCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCTHDActionPerformed
        // TODO add your handling code here:
        int i = tblHoaDon.getSelectedRow();
        if (i >= 0) {
            int maHD = (int)tblHoaDon.getModel().getValueAt(i, 1);
            new ChiTietHoaDon(maHD,idCurrent).setVisible(true);
        }
        else JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xem chi tiết");
    }//GEN-LAST:event_btnCTHDActionPerformed

    private void btnLamMoiTLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiTLMouseClicked
        // TODO add your handling code here:
        txtTenTL.setText("");
        txtTenTL.requestFocus();
        txtFindType.setText("");
        readTableTypeBook();
    }//GEN-LAST:event_btnLamMoiTLMouseClicked

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaNVActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //main-----
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NhapSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NhapSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NhapSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NhapSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCTHD;
    private javax.swing.JButton btnCTPN;
    private javax.swing.JButton btnDeleteAccount;
    private javax.swing.JButton btnInHD;
    private javax.swing.JButton btnInPN;
    private javax.swing.JButton btnLamMoiGG;
    private javax.swing.JButton btnLamMoiHD;
    private javax.swing.JButton btnLamMoiKH;
    private javax.swing.JButton btnLamMoiNCC;
    private javax.swing.JButton btnLamMoiNV;
    private javax.swing.JButton btnLamMoiNXB;
    private javax.swing.JButton btnLamMoiPN;
    private javax.swing.JButton btnLamMoiSach;
    private javax.swing.JButton btnLamMoiTG;
    private javax.swing.JButton btnLamMoiTL;
    private javax.swing.JButton btnLock;
    private javax.swing.JButton btnRSSach;
    private javax.swing.JButton btnRSSach1;
    private javax.swing.JButton btnRSSach10;
    private javax.swing.JButton btnRSSach11;
    private javax.swing.JButton btnRSSach12;
    private javax.swing.JButton btnRSSach13;
    private javax.swing.JButton btnRSSach2;
    private javax.swing.JButton btnRSSach3;
    private javax.swing.JButton btnRSSach4;
    private javax.swing.JButton btnRSSach5;
    private javax.swing.JButton btnRSSach6;
    private javax.swing.JButton btnRSSach7;
    private javax.swing.JButton btnRSSach8;
    private javax.swing.JButton btnRSSach9;
    private javax.swing.JButton btnSuaCTGG;
    private javax.swing.JButton btnSuaGG;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnSuaNCC;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnSuaNXB;
    private javax.swing.JButton btnSuaSach;
    private javax.swing.JButton btnSuaTG;
    private javax.swing.JButton btnSuaTL;
    private javax.swing.JButton btnTKDT;
    private javax.swing.JButton btnTKSL;
    private javax.swing.JButton btnTaoTK;
    private javax.swing.JButton btnThemCTGG;
    private javax.swing.JButton btnThemGG;
    private javax.swing.JButton btnThemHD1;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnThemNXB;
    private javax.swing.JButton btnThemPN;
    private javax.swing.JButton btnThemSach;
    private javax.swing.JButton btnThemTG;
    private javax.swing.JButton btnThemTL;
    private javax.swing.JButton btnUnLock;
    private javax.swing.JButton btnXoaCTGG;
    private javax.swing.JButton btnXoaGG;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JButton btnXoaNCC;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton btnXoaNXB;
    private javax.swing.JButton btnXoaSach;
    private javax.swing.JButton btnXoaTG;
    private javax.swing.JButton btnXoaTL;
    private javax.swing.JComboBox<String> cbEmployee;
    private javax.swing.JComboBox<String> cbGG;
    private javax.swing.JComboBox<String> cbKhachHang;
    private javax.swing.JComboBox<String> cbNCC;
    private javax.swing.JComboBox<String> cbNXB;
    private javax.swing.JComboBox<String> cbRoll;
    private javax.swing.JComboBox<String> cbSach_GG;
    private javax.swing.JComboBox<String> cbTacGia;
    private javax.swing.JComboBox<String> cbTheLoai;
    private javax.swing.JComboBox<String> comHoaDon;
    private javax.swing.JComboBox<String> comKH;
    private javax.swing.JComboBox<String> comNCC;
    private javax.swing.JComboBox<String> comNV;
    private javax.swing.JComboBox<String> comNXB;
    private javax.swing.JComboBox<String> comPhieuNhap;
    private javax.swing.JComboBox<String> comSach;
    private com.toedter.calendar.JDateChooser dateDenNgayDT;
    private com.toedter.calendar.JDateChooser dateDenNgaySL;
    private com.toedter.calendar.JDateChooser dateNgayBD;
    private com.toedter.calendar.JDateChooser dateNgayKT;
    private com.toedter.calendar.JDateChooser dateNgayLapHD;
    private com.toedter.calendar.JDateChooser dateNgayNhap;
    private com.toedter.calendar.JDateChooser dateNgaySinhKH;
    private com.toedter.calendar.JDateChooser dateNgaySinhNV;
    private com.toedter.calendar.JDateChooser dateTuNgayDT;
    private com.toedter.calendar.JDateChooser dateTuNgaySL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel jlblFindPublisher;
    private javax.swing.JLabel lbIDUser;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JLabel lblCheckSL;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblFindAuthor;
    private javax.swing.JLabel lblFindBill;
    private javax.swing.JLabel lblFindBook;
    private javax.swing.JLabel lblFindCustomer;
    private javax.swing.JLabel lblFindEmployee;
    private javax.swing.JLabel lblFindImport;
    private javax.swing.JLabel lblFindProvider;
    private javax.swing.JLabel lblFindType;
    private javax.swing.JLabel lblLogOut;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JTabbedPane panel_sach_tg_tl;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlAuthor;
    private javax.swing.JPanel pnlBill;
    private javax.swing.JPanel pnlBook;
    private javax.swing.JPanel pnlBook1;
    private javax.swing.JPanel pnlContentSach;
    private javax.swing.JPanel pnlCustomer;
    private javax.swing.JPanel pnlDiscount;
    private javax.swing.JPanel pnlEmployee;
    private javax.swing.JPanel pnlImport;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlProvider;
    private javax.swing.JPanel pnlPublisher;
    private javax.swing.JPanel pnlStatistics;
    private javax.swing.JPanel pnlTieuDe;
    private javax.swing.JPanel pnlType;
    private javax.swing.JTabbedPane tabbedMain;
    private javax.swing.JTable tblAccount;
    private javax.swing.JTable tblChiTietGiamGia;
    private javax.swing.JTable tblGiamGia;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblNCC;
    private javax.swing.JTable tblNXB;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblPhieuNhap;
    private javax.swing.JTable tblSach;
    private javax.swing.JTable tblTK_DT;
    private javax.swing.JTable tblTK_SL;
    private javax.swing.JTable tblTacGia;
    private javax.swing.JTable tblTheLoai;
    private javax.swing.JTextField txtChietKhau;
    private javax.swing.JTextField txtDiaChiNCC;
    private javax.swing.JTextField txtDiaChiNXB;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtFindAuthor;
    private javax.swing.JTextField txtFindBill;
    private javax.swing.JTextField txtFindBook;
    private javax.swing.JTextField txtFindCustomer;
    private javax.swing.JTextField txtFindEmployee;
    private javax.swing.JTextField txtFindImport;
    private javax.swing.JTextField txtFindProvider;
    private javax.swing.JTextField txtFindPublisher;
    private javax.swing.JTextField txtFindType;
    private javax.swing.JComboBox<String> txtGioiTinhKH;
    private javax.swing.JComboBox<String> txtGioiTinhNV;
    private javax.swing.JTextField txtHOKH;
    private javax.swing.JTextField txtHONV;
    private javax.swing.JTextField txtHoTacGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaPhieuNhap;
    private javax.swing.JLabel txtMsg;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtSDTNCC;
    private javax.swing.JTextField txtSDTNV;
    private javax.swing.JTextField txtSDTNXB;
    private javax.swing.JTextField txtSLSach;
    private javax.swing.JTextField txtTK_DT;
    private javax.swing.JTextField txtTK_SL;
    private javax.swing.JTextField txtTenCT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTenNXB;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTenTL;
    private javax.swing.JTextField txtTenTacGia;
    private javax.swing.JTextField txtTongTienHD;
    private javax.swing.JTextField txtTongTienPN;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

}