/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.GUI;

import com.itextpdf.text.pdf.PdfName;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import team.java.BUS.*;
import team.java.DTO.*;
import team.java.DAO.*;

/**
 *
 * @author ngoc canh;
 */
public class ChiTietHoaDon extends javax.swing.JFrame {
    DefaultTableModel modelDetail = new DefaultTableModel();
    private int maNV;
    private int maHD;
    /**
     * Creates new form ChiTietHoaDon
     * @param maHD
     */
    
    
    public ChiTietHoaDon(int maHD,int maNV) {
        this.maHD = maHD;
        this.maNV = maNV;
        initComponents();
        modelDetail = (DefaultTableModel) tblCTHD.getModel();
        setCbBook();
        setLocationRelativeTo(null);
        readTableDetail();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void setCbDiscount(ArrayList<DiscountDetail> list) {
        Vector<String> rowdata = new Vector<>();
        DiscountBUS discountBUS = new DiscountBUS();
        for(DiscountDetail detail : list){
            rowdata.add(discountBUS.findDiscountByID(detail.getIdDiscount()).getNameProgram());
        }
        cbGG.setModel(new DefaultComboBoxModel<>(rowdata));
    }
    
    private void setCbBook() {
       

        BookBUS bus = new BookBUS();
//        if (BookBUS.listBook == null) {
            bus.readListBook();
//        }
        Vector<String> rowdata = new Vector<>();
        for (Book b : BookBUS.listBook) {
            rowdata.add(b.getNameBook());
        }
        cbSach_CTHD.setModel(new DefaultComboBoxModel<>(rowdata));
    }
    
    private void readTableDetail() {
        
        lblTitle.setText("CHI TIẾT HÓA ĐƠN " + maHD);

        if(BillDAO.conn!=null){
            BillDAO.conn = null;
        }
        
        BillDetailBUS bus = new BillDetailBUS();
        
        if (BillDetailBUS.listBillDetail == null) {
            bus.readListBillDetail(maHD);
        } else {
            BillDetailBUS.listBillDetail = null;
            BillDetailDAO.conn = null;
            bus.readListBillDetail(maHD);
        }
        
        BookBUS book_bus = new BookBUS();

        modelDetail.setRowCount(0);
        
        DiscountDetailBUS discountdetail_bus = new DiscountDetailBUS();
        
        int i = 1;
        
        for (BillDetail detail : BillDetailBUS.listBillDetail) {
//             set giá và thành tiền
            detail = bus.tinhThanhTien(detail); 
            
            DiscountDetail discountDetail = discountdetail_bus.findDiscountDetail(detail.getIdDiscount(), detail.getIdBook());
            
            int discount = 0;
            if (discountDetail != null){
                discount = discountDetail.getDiscount();
            }
            
            Float priceBook = book_bus.findBookByID(detail.getIdBook()).getPrice();
            
//            System.out.println("Gia sach " + priceBook);
            
            Float soTienGiam = 0f;
            soTienGiam = discount * priceBook * 0.01f;
            
            modelDetail.addRow(new Object[]{
                //tìm giá sách theo id        
                i++, book_bus.findBookByID(detail.getIdBook()).getNameBook(), detail.getQuantity(), 
                detail.getPrice() , soTienGiam ,detail.getTotalPrice()});
        }
    }
    
    private void exportToExcel(DefaultTableModel model) {
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        Workbook excelExporter = null;
        JFileChooser excelFileChoose = new JFileChooser("C:\\Users\\pc\\Desktop");
        excelFileChoose.setDialogTitle("Save as");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel File", "xls", "xlsx", "csv");
        excelFileChoose.setFileFilter(fnef);
        int excelChooser = excelFileChoose.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelExporter = new XSSFWorkbook();
                Sheet excelSheet = excelExporter.createSheet("Bảng sách");
                System.out.println(model.getRowCount());

                for (int i = 0; i < model.getRowCount(); i++) {
                    Row excelRow = excelSheet.createRow(i);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Cell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(model.getValueAt(i, j).toString());
                    }
                }
                System.out.println(excelFileChoose.getSelectedFile());
                excelFOU = new FileOutputStream(excelFileChoose.getSelectedFile() + ".xlsx");
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTHD = new javax.swing.JTable();
        txtMaHD = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbGG = new javax.swing.JComboBox<>();
        cbSach_CTHD = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        btnThemCTHD = new javax.swing.JButton();
        btnSuaCTHD = new javax.swing.JButton();
        btnXoaCTHD = new javax.swing.JButton();
        btnLamMoiCTHD = new javax.swing.JButton();
        btnInChiTietPhieuNhap = new javax.swing.JButton();
        lblExit = new javax.swing.JLabel();
        lblMinimize = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(240, 242, 245));
        jPanel2.setLayout(null);

        tblCTHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>", "<html><b>Sách</b></html>", "<html><b>Số lượng</b></html>","<html><b>Đơn giá</b></html>", "<html><b>Tiền giảm giá</b></html>", "<html><b>Thành tiền</b></html>"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTHD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblCTHD.setFocusable(false);
        tblCTHD.setRowHeight(23);
        tblCTHD.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTHD);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(370, 50, 530, 370);

        txtMaHD.setBackground(new java.awt.Color(240, 242, 245));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Sách");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Số lượng");
        jLabel11.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Đơn giá (VND)");
        jLabel17.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtSL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtDonGia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDonGia.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Giảm giá");
        jLabel18.setPreferredSize(new java.awt.Dimension(1000, 20));

        cbGG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cbSach_CTHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbSach_CTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSach_CTHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout txtMaHDLayout = new javax.swing.GroupLayout(txtMaHD);
        txtMaHD.setLayout(txtMaHDLayout);
        txtMaHDLayout.setHorizontalGroup(
            txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtMaHDLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbGG, 0, 202, Short.MAX_VALUE)
                    .addComponent(cbSach_CTHD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        txtMaHDLayout.setVerticalGroup(
            txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtMaHDLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbSach_CTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(txtMaHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGG, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.add(txtMaHD);
        txtMaHD.setBounds(10, 150, 350, 210);

        jPanel31.setBackground(new java.awt.Color(240, 242, 245));
        jPanel31.setOpaque(false);

        btnThemCTHD.setBackground(new java.awt.Color(0, 204, 204));
        btnThemCTHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemCTHD.setForeground(new java.awt.Color(0, 51, 153));
        btnThemCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemCTHD.setText("THÊM");
        btnThemCTHD.setBorder(null);
        btnThemCTHD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemCTHD.setPreferredSize(new java.awt.Dimension(80, 40));
        btnThemCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTHDActionPerformed(evt);
            }
        });
        jPanel31.add(btnThemCTHD);

        btnSuaCTHD.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaCTHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaCTHD.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaCTHD.setText("SỬA");
        btnSuaCTHD.setBorder(null);
        btnSuaCTHD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaCTHD.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSuaCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTHDActionPerformed(evt);
            }
        });
        jPanel31.add(btnSuaCTHD);

        btnXoaCTHD.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaCTHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaCTHD.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaCTHD.setText("XÓA");
        btnXoaCTHD.setBorder(null);
        btnXoaCTHD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaCTHD.setPreferredSize(new java.awt.Dimension(80, 40));
        btnXoaCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTHDActionPerformed(evt);
            }
        });
        jPanel31.add(btnXoaCTHD);

        btnLamMoiCTHD.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiCTHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiCTHD.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiCTHD.setText("MỚI");
        btnLamMoiCTHD.setBorder(null);
        btnLamMoiCTHD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiCTHD.setPreferredSize(new java.awt.Dimension(80, 40));
        btnLamMoiCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiCTHDActionPerformed(evt);
            }
        });
        jPanel31.add(btnLamMoiCTHD);

        jPanel2.add(jPanel31);
        jPanel31.setBounds(10, 370, 350, 50);

        btnInChiTietPhieuNhap.setBackground(new java.awt.Color(102, 153, 255));
        btnInChiTietPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_printer_20px.png"))); // NOI18N
        btnInChiTietPhieuNhap.setText("Xuất bản in");
        btnInChiTietPhieuNhap.setBorder(null);
        btnInChiTietPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInChiTietPhieuNhapMouseClicked(evt);
            }
        });
        jPanel2.add(btnInChiTietPhieuNhap);
        btnInChiTietPhieuNhap.setBounds(750, 440, 110, 29);

        lblExit.setBackground(new java.awt.Color(240, 242, 245));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete_26px.png"))); // NOI18N
        lblExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        jPanel2.add(lblExit);
        lblExit.setBounds(880, 0, 30, 30);

        lblMinimize.setBackground(new java.awt.Color(240, 242, 245));
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
        jPanel2.add(lblMinimize);
        lblMinimize.setBounds(850, 0, 30, 30);

        lblTitle.setBackground(new java.awt.Color(240, 242, 245));
        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 102, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("canh code do");
        lblTitle.setOpaque(true);
        jPanel2.add(lblTitle);
        lblTitle.setBounds(50, 60, 280, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbSach_CTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSach_CTHDActionPerformed
        int index = cbSach_CTHD.getSelectedIndex();
        int idBook = BookBUS.listBook.get(index).getIdBook();
        Book b = new BookBUS().findBookByID(idBook);
        
        DiscountDetailBUS discountdetailBUS = new DiscountDetailBUS();
        
        ArrayList<DiscountDetail> discountdetailList = discountdetailBUS.findByBookID(idBook);
        
        setCbDiscount(discountdetailList);
        
        txtDonGia.setText(Float.toString(b.getPrice()));
    }//GEN-LAST:event_cbSach_CTHDActionPerformed

    private void tblCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHDMouseClicked
        
        int i = tblCTHD.getSelectedRow();

        if (i >= 0) {
            
            int column = 1;

            BookBUS bus = new BookBUS();
            
            
            Book book = new Book();

            
            cbSach_CTHD.getModel().setSelectedItem(tblCTHD.getModel().getValueAt(i, column++).toString());
            txtSL.setText(tblCTHD.getModel().getValueAt(i, column++).toString());
            
            
//            book = bus.findBookByName(tblCTHD.getModel().getValueAt(i, 1).toString());
            
            int idBook = BillDetailBUS.listBillDetail.get(i).getIdBook();
            book = bus.findBookByID(idBook);

//            System.out.println(tblCTHD.getModel().getValueAt(i, 1).toString());
//            System.out.println(book.getIdBook());
            
            
//             FIX LOI O DAY , VE NHA LAM GAP
//            int idDiscount = billdetail.getIdDiscount();
//            System.out.println(idDiscount);
//
//
//            Discount discount = new DiscountBUS().findDiscountByID(idDiscount);
//
//            String nameProgram = "";
//            if (discount != null){
//                nameProgram = discount.getNameProgram();
//            }
//            else nameProgram = "Không có";

            String nameProgram = "";
            String soTienGiam = tblCTHD.getModel().getValueAt(i, 4).toString();
            if (soTienGiam.equals("0.0")){
                nameProgram = "Không có";
            }
            else{
                BillDetail billdetail = new BillDetail();
                billdetail = new BillDetailBUS().findBillDetail(this.maHD, idBook);
                
                int idDiscount = billdetail.getIdDiscount();
                Discount discount = new DiscountBUS().findDiscountByID(idDiscount);
                nameProgram = discount.getNameProgram();
                
            }
            cbGG.getModel().setSelectedItem(nameProgram);
            txtDonGia.setText(String.valueOf(book.getPrice()));
        }
    }//GEN-LAST:event_tblCTHDMouseClicked
   
    private void btnThemCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTHDActionPerformed

        int idSach = BookBUS.listBook.get(cbSach_CTHD.getSelectedIndex()).getIdBook();
        System.out.println("Mã sách " + idSach);
        String soluong = txtSL.getText();
        DiscountBUS discountBUS = new DiscountBUS();
//        int idGiamgia = DiscountBUS.listDiscount.get(cbGG.getSelectedIndex()).getIdDiscount(); // Chay sai ket qua
      
        int indexGiamGia = cbGG.getSelectedIndex();
        
        
        if (idSach == -1 || soluong.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        int amount = 0;
        try
        {
            amount = Integer.parseInt(txtSL.getText());
        } catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số");
            return;
        }
        
        BillDetailBUS bus = new BillDetailBUS();

        
        if(amount <=0){
            JOptionPane.showMessageDialog(null, "Số lượng mua phải lớn hơn 0");
            return;
        }
        
        BillDetail detail = new BillDetail();
        int idGiamGia = 0;
        if (indexGiamGia != -1){
            idGiamGia = discountBUS.findDiscountByName(cbGG.getSelectedItem().toString()).getIdDiscount();
            detail = new BillDetail(this.maHD, idSach, idGiamGia,amount );
        }
        else detail = new BillDetail(this.maHD, idSach, 0,amount );

        System.out.println("Mã giảm giá " + idGiamGia);
        
        
        
        
        Float price = Float.parseFloat(txtDonGia.getText());
        
        
//        detail.setPrice(price);
        
        BookBUS bus_book = new BookBUS();
        
        Book book = bus_book.findBookByID(detail.getIdBook());
        
        if(book.getQuantity() < detail.getQuantity()){
            JOptionPane.showMessageDialog(null, "Số lượng sách khả dụng là "+ book.getQuantity() +"\nVui lòng nhập ít hơn hoặc bằng "+book.getQuantity());
            return;
        }
        
        
        /// xử lí chi tiết hoá đơn hợp lệ và tính thành tiền
   
        detail = bus.tinhThanhTien(detail);
        if(!bus.add(detail)){
            JOptionPane.showMessageDialog(null, "Chi tiết hoá đơn đã tồn tại, thêm thất bại");
        } 
        else{           
            // cập nhật lại tổng tiền trong bill: cộng
            BillBUS bus_bill = new BillBUS();
//            bus_bill.readListBill(maNV);
            
            // Check
            Bill bill = new Bill();
            bill = bus_bill.findBillByID(detail.getIdBill());
           
            // CHECK Bat buoc khoi tao doi tuong new
            
//            System.out.println("Ma hoa don cap nhat: " + bill.getIdBill());
            
            System.out.println("Tong tien khi chua cap nhat: " + bill.getTotal());

            bill.setTotal((bill.getTotal() + detail.getTotalPrice()));
            
            System.out.println(detail.getTotalPrice());
            
            System.out.println(bill.getTotal());
            
            // CHECK 
            if (bus_bill.update(bill, BillBUS.listBill.indexOf(bill))){
                System.out.println("Cap nhat thanh cong");
            }
            
            
            else System.out.println("Cap nhat that bai");
            //  cập nhật lại số lượng sách trong kho
            book.setQuantity(book.getQuantity() - detail.getQuantity());
            
            bus_book.update(book, BookBUS.listBook.indexOf(book));
            
            /// cập nhật GUI
//            Vector row = new Vector();
//            row.add(tblCTHD.getRowCount() + 1);
//            row.add(bus_book.findBookByID(detail.getIdBook()).getNameBook());
//            row.add(detail.getQuantity());
//            
//            Float priceBook = new BookBUS().findBookByID(idSach).getPrice();
//            int discount = new DiscountDetailBUS().findDiscountDetail(idGiamgia, idSach).getDiscount();
//            row.add(price);
//            row.add(price*discount);
//            row.add(detail.getTotalPrice());
//            modelDetail.addRow(row);
//            tblCTHD.setModel(modelDetail);
            readTableDetail();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }

    }//GEN-LAST:event_btnThemCTHDActionPerformed

    private void btnXoaCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTHDActionPerformed
        int i = tblCTHD.getSelectedRow();
        if(i==-1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn chi tiết cần xóa");
        }else{
            int result = JOptionPane.showConfirmDialog(rootPane,"Bạn có thực sự muốn xóa chi tiết này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                
                BillDetailBUS bus_detail = new BillDetailBUS();
                
//                Bi loi => BAO CAO XONG CHECK LAI
//                int idBook = new BookBUS().findBookByName(tblCTHD.getModel().getValueAt(i, 2).toString()).getIdBook();
                

                int idBook = BillDetailBUS.listBillDetail.get(i).getIdBook();
                
                float totalprice = (Float) modelDetail.getValueAt(i,5);
                int amount = (Integer) modelDetail.getValueAt(i,2);
                
                if(!bus_detail.delete(this.maHD, idBook)){
                    JOptionPane.showMessageDialog(rootPane, "Xoá thất bại");
                    return;
                }
                
                
                // cập nhật lại tổng tiền trong bill: trừ
                BillBUS bus_bill = new BillBUS();
//                bus_bill.readListBill(maNV);
                Bill bill = bus_bill.findBillByID(this.maHD);
                bill.setTotal(bill.getTotal() - totalprice);
                bus_bill.update(bill, BillBUS.listBill.indexOf(bill));

                //  cập nhật lại số lượng sách trong kho: cộng
                BookBUS bus_book = new BookBUS();
                Book book = bus_book.findBookByID(idBook);
                book.setQuantity(book.getQuantity() + amount );
                bus_book.update(book, BookBUS.listBook.indexOf(book));    
                modelDetail.removeRow(i);
                tblCTHD.setModel(modelDetail);
                JOptionPane.showMessageDialog(rootPane, "Xoá thành công");
                
            }
        }
    }//GEN-LAST:event_btnXoaCTHDActionPerformed

    private void btnSuaCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTHDActionPerformed

        int i = tblCTHD.getSelectedRow();
        
        if(i==-1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn chi tiết cần sửa");
        }else{
            
            int maSach = BookBUS.listBook.get(cbSach_CTHD.getSelectedIndex()).getIdBook();
            int indexGiamGia = cbGG.getSelectedIndex();
            String soluong = txtSL.getText();




            if (maSach == -1 || soluong.equals("")){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            DiscountBUS discountBUS = new DiscountBUS();
            int maGiamGia = discountBUS.findDiscountByName(cbGG.getSelectedItem().toString()).getIdDiscount();
            
            int soLuong = 0;
            try
            {
                soLuong = Integer.parseInt(txtSL.getText());
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên");
                return;
            }
            BillDetailBUS bus = new BillDetailBUS();
            Float price = Float.parseFloat(txtDonGia.getText());

            
            if(soLuong <=0){
                JOptionPane.showMessageDialog(null, "Số lượng mua phải lớn hơn 0");
                return;
            }
            
            
            BillDetail detail = new BillDetail();
            int idGiamGia = 0;
            if (indexGiamGia != -1){
                idGiamGia = discountBUS.findDiscountByName(cbGG.getSelectedItem().toString()).getIdDiscount();
                detail = new BillDetail(this.maHD, maSach, idGiamGia,soLuong );
            }
            else detail = new BillDetail(this.maHD, maSach, 0,soLuong );
            
            
            
            BookBUS bus_book = new BookBUS();
            Book book = bus_book.findBookByID(detail.getIdBook());
            if(book.getQuantity() < detail.getQuantity()){
                JOptionPane.showMessageDialog(null, "Số lượng sách tồn kho là "+ book.getQuantity() +"\nVui lòng nhập ít hơn hoặc bằng "+book.getQuantity());
                return;
            }
            /// xử lí chi tiết hoá đơn hợp lệ và tính thành tiền
//            String idBook_current = (String) modelDetail.getValueAt(i,2);
//            if(!idBook_current.equals(detail.getIdBook())){
//                JOptionPane.showMessageDialog(null, "Mã sách không được thay đổi khi sửa");
//                cbSach_CTHD.getModel().setSelectedItem(idBook_current);
//                return;
//            }
            
            
            detail = bus.tinhThanhTien(detail);
            
            
            // Lấy mã sách, mã giảm giá cũ để cập nhật chi tiết 
            int oldBookID = bus.listBillDetail.get(i).getIdBook();
            int oldBillID = bus.listBillDetail.get(i).getIdBill();
            
            
            if(!bus.update(detail, oldBillID,oldBookID)){
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
            } else{           
                // cập nhật lại tổng tiền trong bill
                BillBUS bus_bill = new BillBUS();
//                bus_bill.readListBill(maNV);
                Bill bill = bus_bill.findBillByID(detail.getIdBill());   
                float total_current = (Float) modelDetail.getValueAt(i,4);
                if(total_current - detail.getTotalPrice() <=0){
                    float delta = detail.getTotalPrice() - total_current;
                    bill.setTotal(bill.getTotal() + delta);
                    bus_bill.update(bill, BillBUS.listBill.indexOf(bill));
                } else{
                    float delta =  total_current - detail.getTotalPrice();
                    bill.setTotal(bill.getTotal() - delta);
                    bus_bill.update(bill, BillBUS.listBill.indexOf(bill));
                }
                

                //  cập nhật lại số lượng sách trong kho
                int amount_current =(Integer) modelDetail.getValueAt(i,2);
                if(amount_current - soLuong <= 0){
                    int delta = soLuong-amount_current;
                    book.setQuantity(book.getQuantity() - delta);
                    bus_book.update(book, BookBUS.listBook.indexOf(book));
                } else{
                    int delta = amount_current - soLuong;
                    book.setQuantity(book.getQuantity() + delta);
                    bus_book.update(book,BookBUS.listBook.indexOf(book));
                }
                

                /// cập nhật GUI
                int column = 1;
                
//                modelDetail.setValueAt(bus_book.findBookByID(detail.getIdBook()).getNameBook(), i, column++);
//                modelDetail.setValueAt(detail.getQuantity(), i, column++);
//                modelDetail.setValueAt(detail.getIdDiscount(), i, column++);
//                modelDetail.setValueAt(detail.getTotalPrice(), i, column++);
//                tblCTHD.setModel(modelDetail);
                readTableDetail();
                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            }
        }
    }//GEN-LAST:event_btnSuaCTHDActionPerformed

    private void btnLamMoiCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiCTHDActionPerformed
        txtSL.setText("");
        txtDonGia.setText("");
        readTableDetail();
    }//GEN-LAST:event_btnLamMoiCTHDActionPerformed

    private void btnInChiTietPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInChiTietPhieuNhapMouseClicked
        // TODO add your handling code here:
        exportToExcel(modelDetail);
    }//GEN-LAST:event_btnInChiTietPhieuNhapMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        dispose();
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseEntered
        lblExit.setBackground(Color.RED);
        lblExit.setOpaque(true);        
    }//GEN-LAST:event_lblExitMouseEntered

    private void lblExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseExited
        lblExit.setBackground(jPanel2.getBackground());
    }//GEN-LAST:event_lblExitMouseExited

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void lblMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseEntered
        lblMinimize.setBackground(new Color(183, 252, 252));
    }//GEN-LAST:event_lblMinimizeMouseEntered

    private void lblMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseExited
        lblMinimize.setBackground(jPanel2.getBackground());
    }//GEN-LAST:event_lblMinimizeMouseExited

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
////       new ChiTietHoaDon("HD002","").setVisible(true);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInChiTietPhieuNhap;
    private javax.swing.JButton btnLamMoiCTHD;
    private javax.swing.JButton btnSuaCTHD;
    private javax.swing.JButton btnThemCTHD;
    private javax.swing.JButton btnXoaCTHD;
    private javax.swing.JComboBox<String> cbGG;
    private javax.swing.JComboBox<String> cbSach_CTHD;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblCTHD;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JPanel txtMaHD;
    private javax.swing.JTextField txtSL;
    // End of variables declaration//GEN-END:variables
}
