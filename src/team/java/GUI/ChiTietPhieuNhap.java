/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.GUI;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
public class ChiTietPhieuNhap extends javax.swing.JFrame {

    /**
     * Creates new form ChiTietPhieuNhap
     */
    private int maNV;
    public int idImport;
    
    DefaultTableModel modelDetailImport = new DefaultTableModel();

    public ChiTietPhieuNhap(int idImport,int maNV) {
        this.idImport = idImport;
        this.maNV = maNV;
        
        initComponents();
        
        setLocationRelativeTo(null);
        modelDetailImport = (DefaultTableModel) tblCTPN.getModel();
        setComboBoxBook();
        
        readTableDetailImport(this.idImport);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
    }

    //đọc dữ liệu table
    private void readTableDetailImport(int maPN) {
        
        lblTitle.setText("CHI TIẾT PHIẾU NHẬP " + idImport);
        
        if(ImportDAO.conn!=null){
            ImportDAO.conn = null;
        }
        
        ImportDetailBUS bus = new ImportDetailBUS();
        
        if (ImportDetailBUS.listImportDetail == null) {
            bus.readListImportDetail(maPN);
        } else {
            ImportDetailBUS.listImportDetail = null;
            ImportDetailDAO.conn = null;
            bus.readListImportDetail(maPN);
        }
        
        BookBUS book_bus = new BookBUS();
        
        modelDetailImport.setRowCount(0);
        int i = 1;
        for (ImportDetail detail : ImportDetailBUS.listImportDetail) {
            // set giá và thành tiền
            detail = bus.tinhThanhTien(detail);  
            modelDetailImport.addRow(new Object[]{
                i++, book_bus.findBookByID(detail.getIdBook()).getNameBook(), detail.getQuantity(), detail.getPrice(),detail.getTotalPrice()
            });
        }
        
        tblCTPN.setModel(modelDetailImport);
 
    }

    //set combo box
    private void setComboBoxBook() {
        BookBUS bus = new BookBUS();
        if (BookBUS.listBook == null) {
            bus.readListBook();;
        }
        Vector<String> rowdata = new Vector<>();
        for (Book book : BookBUS.listBook) {
            rowdata.add(book.getNameBook());
        }
        cbSach_CTPN.setModel(new DefaultComboBoxModel<>(rowdata));

    }


    //tính tổng số lượng
    public int sumAmount() {
        int total = 0;
        for (int i = 0; i < tblCTPN.getRowCount(); i++) {
            float amount = Integer.parseInt(modelDetailImport.getValueAt(i, 2).toString());
            total += amount;
        }
        return total;
    }
    
    

    public void enterInPDF(Document d, int index) throws DocumentException {
        for (int i = 0; i < index; i++) {
            d.add(new Paragraph(" "));
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

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTPN = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSLNhap = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        cbSach_CTPN = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        btnThemCTPN = new javax.swing.JButton();
        btnSuaCTPN = new javax.swing.JButton();
        btnXoaCTPN = new javax.swing.JButton();
        btnLamMoiCTPN = new javax.swing.JButton();
        lblExit = new javax.swing.JLabel();
        lblMinimize = new javax.swing.JLabel();
        btnInChiTietPhieuNhap = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(240, 242, 245));
        jPanel2.setLayout(null);

        tblCTPN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "<html><b>STT</b></html>","<html><b>Sách</b></html>", "<html><b>Số lượng</b></html>", "<html><b>Giá nhập</b></html>", "<html><b>Thành tiền</b></html>"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTPN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblCTPN.setFocusable(false);
        tblCTPN.setRowHeight(23);
        tblCTPN.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPNMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTPN);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(380, 50, 520, 350);

        jPanel5.setBackground(new java.awt.Color(240, 242, 245));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Sách");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Số lượng");
        jLabel11.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtSLNhap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtGiaNhap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cbSach_CTPN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Giá nhập (VND)");
        jLabel12.setPreferredSize(new java.awt.Dimension(1000, 20));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSLNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbSach_CTPN, 0, 222, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbSach_CTPN, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSLNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(10, 120, 370, 200);

        jPanel34.setBackground(new java.awt.Color(240, 242, 245));

        btnThemCTPN.setBackground(new java.awt.Color(0, 204, 204));
        btnThemCTPN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemCTPN.setForeground(new java.awt.Color(0, 51, 153));
        btnThemCTPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemCTPN.setText("THÊM");
        btnThemCTPN.setBorder(null);
        btnThemCTPN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemCTPN.setPreferredSize(new java.awt.Dimension(80, 40));
        btnThemCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTPNActionPerformed(evt);
            }
        });
        jPanel34.add(btnThemCTPN);

        btnSuaCTPN.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaCTPN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaCTPN.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaCTPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaCTPN.setText("SỬA");
        btnSuaCTPN.setBorder(null);
        btnSuaCTPN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaCTPN.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSuaCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTPNActionPerformed(evt);
            }
        });
        jPanel34.add(btnSuaCTPN);

        btnXoaCTPN.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaCTPN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaCTPN.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaCTPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaCTPN.setText("XÓA");
        btnXoaCTPN.setBorder(null);
        btnXoaCTPN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaCTPN.setPreferredSize(new java.awt.Dimension(80, 40));
        btnXoaCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTPNActionPerformed(evt);
            }
        });
        jPanel34.add(btnXoaCTPN);

        btnLamMoiCTPN.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiCTPN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiCTPN.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiCTPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiCTPN.setText("MỚI");
        btnLamMoiCTPN.setBorder(null);
        btnLamMoiCTPN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiCTPN.setPreferredSize(new java.awt.Dimension(80, 40));
        btnLamMoiCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiCTPNActionPerformed(evt);
            }
        });
        jPanel34.add(btnLamMoiCTPN);

        jPanel2.add(jPanel34);
        jPanel34.setBounds(20, 340, 340, 50);

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
        btnInChiTietPhieuNhap.setBounds(780, 420, 110, 29);

        lblTitle.setBackground(new java.awt.Color(240, 242, 245));
        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 102, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("canh code do");
        lblTitle.setOpaque(true);
        jPanel2.add(lblTitle);
        lblTitle.setBounds(50, 60, 290, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTPNActionPerformed
        // TODO add your handling code here:
        
        String sach = cbSach_CTPN.getSelectedItem().toString();
        String soluong = txtSLNhap.getText();
        String gianhap = txtGiaNhap.getText();
        
        if (sach.equals("") || soluong.equals("") || gianhap.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        
        int amount = 0;
        float price = 0;
       
        
        try
        {
            amount = Integer.parseInt(txtSLNhap.getText());
        } catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên");
            return;
        }
        
        if(amount <=0){
            JOptionPane.showMessageDialog(null, "Số lượng nhập phải lớn hơn 0");
            return;
        }
        
        try
        {
            price = Float.parseFloat(txtGiaNhap.getText());
        } catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "Giá nhập phải là số nguyên");
            return;
        }
        
        if(price <= 0){
            JOptionPane.showMessageDialog(null, "Giá nhập nhập phải lớn hơn 0");
            return;
        }
        
        
        ImportDetailBUS bus = new ImportDetailBUS();
        
        int idBook = BookBUS.listBook.get(cbSach_CTPN.getSelectedIndex()).getIdBook();
        
        
        ImportDetail detail = new ImportDetail(this.idImport, idBook, amount, price );
        BookBUS bus_book = new BookBUS();
        Book book = bus_book.findBookByID(detail.getIdBook());
        
        /// xử lí chi tiết phiếu nhập hợp lệ và tính thành tiền
   
        detail = bus.tinhThanhTien(detail);
        if(!bus.add(detail)){
            JOptionPane.showMessageDialog(null, "Chi tiết phiếu nhập đã tồn tại, thêm thất bại");
        } else{           
            // cập nhật lại tổng tiền trong import(imp): cộng
            ImportBUS bus_import = new ImportBUS();
//            bus_import.readListImport(maNV);
            
            Import imp = new Import(); 
                    
            imp = bus_import.findImportByID(detail.getIdImport());
            
            imp.setTotal(imp.getTotal() + detail.getTotalPrice());
            
            
            bus_import.update(imp, ImportBUS.listImport.indexOf(imp));   
            
            //  cập nhật lại số lượng sách trong kho
            book.setQuantity(book.getQuantity() + detail.getQuantity());
            bus_book.update(book, BookBUS.listBook.indexOf(book));
            
            /// cập nhật GUI
            Vector row = new Vector();
            row.add(tblCTPN.getRowCount() + 1);
            row.add(bus_book.findBookByID(detail.getIdBook()).getNameBook());
            row.add(detail.getQuantity());
            row.add(detail.getPrice());
            row.add(detail.getTotalPrice());
            modelDetailImport.addRow(row);
            tblCTPN.setModel(modelDetailImport);
            JOptionPane.showMessageDialog(null, "Thêm thành công");    
        }
    }//GEN-LAST:event_btnThemCTPNActionPerformed

    private void btnInChiTietPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInChiTietPhieuNhapMouseClicked
        // TODO add your handling code here:
        exportToExcel(modelDetailImport);
    }//GEN-LAST:event_btnInChiTietPhieuNhapMouseClicked

    private void tblCTPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPNMouseClicked
        // TODO add your handling code here:
        int i = tblCTPN.getSelectedRow();
        int column = 1;
        if (i >= 0) {
            cbSach_CTPN.getModel().setSelectedItem(tblCTPN.getValueAt(i, column++).toString());
            txtSLNhap.setText(tblCTPN.getValueAt(i, column++).toString());
            txtGiaNhap.setText(tblCTPN.getValueAt(i, column++).toString());
        }
    }//GEN-LAST:event_tblCTPNMouseClicked

    private void btnSuaCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTPNActionPerformed
        // TODO add your handling code here:
        
        String sach = cbSach_CTPN.getSelectedItem().toString();
        String soluong = txtSLNhap.getText();
        String gianhap = txtGiaNhap.getText();
        
        if (sach.equals("") || soluong.equals("") || gianhap.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        
        int i = tblCTPN.getSelectedRow();
        if(i==-1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn chi tiết cần sửa");
        }else{
            int amount = 0;
            float price =0;
           
            
            try
            {
                amount = Integer.parseInt(txtSLNhap.getText());
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên");
                return;
            }
            
            if(amount <=0){
                JOptionPane.showMessageDialog(null, "Số lượng nhập phải lớn hơn 0");
                return;
            }
            
            try
            {
                price = Float.parseFloat(txtGiaNhap.getText());
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Giá nhập phải là số nguyên");
                return;
            }
            if(price <= 0){
                JOptionPane.showMessageDialog(null, "Giá nhập nhập phải lớn hơn 0");
                return;
            }
            
            ImportDetailBUS bus = new ImportDetailBUS();
            int idBook =  BookBUS.listBook.get(cbSach_CTPN.getSelectedIndex()).getIdBook();
            
            
            ImportDetail detail = new ImportDetail(this.idImport, idBook, amount, price);
            BookBUS bus_book = new BookBUS();
            Book book = bus_book.findBookByID(detail.getIdBook());
            
            /// xử lí chi tiết hoá đơn hợp lệ và tính thành tiền
            int oldBookID = bus.listImportDetail.get(i).getIdBook();
            
            
            
            
            detail = bus.tinhThanhTien(detail);
            
            
//            String idBook_current = (String) modelDetailImport.getValueAt(i,1);
//            if(!idBook_current.equals(detail.getIdBook())){
//                JOptionPane.showMessageDialog(null, "Mã sách không được thay đổi khi sửa");
//                cbSach_CTPN.getModel().setSelectedItem(idBook_current);
//                return;
//            }


            if(!bus.update(detail, this.idImport, oldBookID)){
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
            } else{           
                // cập nhật lại tổng tiền trong import
                ImportBUS bus_import = new ImportBUS();
//                bus_import.readListImport(maNV);
                Import imp = bus_import.findImportByID(detail.getIdImport());   
                float total_current = (Float) modelDetailImport.getValueAt(i,4);
                if(total_current - detail.getTotalPrice() <=0){
                    float delta = detail.getTotalPrice() - total_current;
                    imp.setTotal(imp.getTotal() + delta);
                    bus_import.update(imp, ImportBUS.listImport.indexOf(imp));
                } else{
                    float delta =  total_current - detail.getTotalPrice();
                    imp.setTotal(imp.getTotal() - delta);
                    bus_import.update(imp, ImportBUS.listImport.indexOf(imp));
                }
                

                //  cập nhật lại số lượng sách trong kho
                int amount_current =(Integer) modelDetailImport.getValueAt(i,2);
                if(amount_current - amount <= 0){ // nhập nhiều hơn
                    int delta = amount-amount_current;
                    book.setQuantity(book.getQuantity() + delta);
                    bus_book.update(book, BookBUS.listBook.indexOf(book));
                } else{
                    int delta = amount_current - amount;  // nhập ít hơn
                    book.setQuantity(book.getQuantity() - delta);
                    bus_book.update(book, BookBUS.listBook.indexOf(book));
                }
                /// cập nhật GUI
                int column = 1;
                modelDetailImport.setValueAt(bus_book.findBookByID(detail.getIdBook()).getNameBook(), i, column++);
                modelDetailImport.setValueAt(detail.getQuantity(), i, column++);
                modelDetailImport.setValueAt(detail.getPrice(), i, column++);
                modelDetailImport.setValueAt(detail.getTotalPrice(), i, column++);
                tblCTPN.setModel(modelDetailImport);
                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            }
        }
    }//GEN-LAST:event_btnSuaCTPNActionPerformed

    private void btnLamMoiCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiCTPNActionPerformed
        txtGiaNhap.setText("");
        txtSLNhap.setText("");
        readTableDetailImport(this.idImport);
    }//GEN-LAST:event_btnLamMoiCTPNActionPerformed

    private void btnXoaCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTPNActionPerformed
        // TODO add your handling code here:
        int i = tblCTPN.getSelectedRow();
        if(i==-1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn chi tiết phiếu nhập cần xóa");
        }else{
            int result = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn xóa chi tiết này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                ImportDetailBUS bus_detail = new ImportDetailBUS();
                
                
                
                int idBook = ImportDetailBUS.listImportDetail.get(i).getIdBook();
                
                
                float totalprice = (Float) modelDetailImport.getValueAt(i,4);
                int amount = (Integer) modelDetailImport.getValueAt(i,2);
                
                if(!bus_detail.delete(this.idImport, idBook)){
                    JOptionPane.showMessageDialog(rootPane, "Xoá thất bại");
                    return;
                }
                
                // cập nhật lại tổng tiền trong import: trừ
                ImportBUS bus_import = new ImportBUS();
//                bus_import.readListImport(maNV);
               
                Import imp = bus_import.findImportByID(this.idImport);
                imp.setTotal(imp.getTotal() - totalprice);         
                
                bus_import.update(imp, ImportBUS.listImport.indexOf(imp));

                //  cập nhật lại số lượng sách trong kho: trừ
                BookBUS bus_book = new BookBUS();
                Book book = bus_book.findBookByID(idBook);
                book.setQuantity(book.getQuantity() - amount );
                bus_book.update(book, BookBUS.listBook.indexOf(book));    
                modelDetailImport.removeRow(i);
                tblCTPN.setModel(modelDetailImport);
                JOptionPane.showMessageDialog(rootPane, "Xoá thành công");
                
            }
        }
    }//GEN-LAST:event_btnXoaCTPNActionPerformed

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
//            java.util.logging.Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ChiTietPhieuNhap(this., maNV).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInChiTietPhieuNhap;
    private javax.swing.JButton btnLamMoiCTPN;
    private javax.swing.JButton btnSuaCTPN;
    private javax.swing.JButton btnThemCTPN;
    private javax.swing.JButton btnXoaCTPN;
    private javax.swing.JComboBox<String> cbSach_CTPN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblCTPN;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtSLNhap;
    // End of variables declaration//GEN-END:variables
}
