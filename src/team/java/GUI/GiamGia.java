/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.java.GUI;

/**
 *
 * @author ngoc canh;
 */
public class GiamGia extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public GiamGia() {
        initComponents();
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
        jSeparator12 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        txtMaGG = new javax.swing.JTextField();
        txtTenCT = new javax.swing.JTextField();
        dateNgayBD = new com.toedter.calendar.JDateChooser();
        dateNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        btnThemGG = new javax.swing.JButton();
        btnSuaGG = new javax.swing.JButton();
        btnXoaGG = new javax.swing.JButton();
        btnLamMoiGG = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        btnThemCTGG1 = new javax.swing.JButton();
        btnXoaCTGG1 = new javax.swing.JButton();
        btnLamMoiCTGG1 = new javax.swing.JButton();
        btnSuaCTGG1 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setEnabled(false);

        jSeparator12.setForeground(new java.awt.Color(102, 102, 255));
        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel14.setBackground(new java.awt.Color(204, 255, 204));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setText("Mã giảm giá");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText("Tên chương trình");

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setText("Ngày bắt đầu");

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel84.setText("Ngày kết thúc");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Ngày bắt đầu");

        jLabel69.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel69.setText("Ngày kết thúc");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaGG)
                    .addComponent(txtTenCT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(dateNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(txtMaGG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
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
                .addGap(18, 18, 18)
                .addComponent(jLabel79)
                .addGap(44, 44, 44)
                .addComponent(jLabel84)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dateNgayBD.setDateFormatString("dd/MM/yyyy");
        dateNgayKT.setDateFormatString("dd/MM/yyyy");

        jPanel15.setBackground(new java.awt.Color(204, 255, 204));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel88.setText("Mã giảm giá");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setText("Mã sách");

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel91.setText("Chiếc khấu (%)");

        jTextField9.setEditable(false);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThemGG.setBackground(new java.awt.Color(0, 204, 204));
        btnThemGG.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThemGG.setForeground(new java.awt.Color(0, 51, 153));
        btnThemGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemGG.setText("THÊM");
        btnThemGG.setBorder(null);
        btnThemGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemGG.setPreferredSize(new java.awt.Dimension(79, 42));
        jPanel39.add(btnThemGG);

        btnSuaGG.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaGG.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSuaGG.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaGG.setText("SỬA");
        btnSuaGG.setBorder(null);
        btnSuaGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaGG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaGGActionPerformed(evt);
            }
        });
        jPanel39.add(btnSuaGG);

        btnXoaGG.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaGG.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXoaGG.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaGG.setText("XÓA");
        btnXoaGG.setBorder(null);
        btnXoaGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaGG.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGGActionPerformed(evt);
            }
        });
        jPanel39.add(btnXoaGG);

        btnLamMoiGG.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiGG.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLamMoiGG.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiGG.setText("MỚI");
        btnLamMoiGG.setBorder(null);
        btnLamMoiGG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiGG.setPreferredSize(new java.awt.Dimension(79, 42));
        jPanel39.add(btnLamMoiGG);

        btnThemCTGG1.setBackground(new java.awt.Color(0, 204, 204));
        btnThemCTGG1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThemCTGG1.setForeground(new java.awt.Color(0, 51, 153));
        btnThemCTGG1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        btnThemCTGG1.setText("THÊM");
        btnThemCTGG1.setBorder(null);
        btnThemCTGG1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemCTGG1.setPreferredSize(new java.awt.Dimension(79, 42));
        jPanel41.add(btnThemCTGG1);

        btnXoaCTGG1.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaCTGG1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXoaCTGG1.setForeground(new java.awt.Color(0, 51, 153));
        btnXoaCTGG1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        btnXoaCTGG1.setText("XÓA");
        btnXoaCTGG1.setBorder(null);
        btnXoaCTGG1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaCTGG1.setPreferredSize(new java.awt.Dimension(79, 42));
        btnXoaCTGG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTGG1ActionPerformed(evt);
            }
        });
        jPanel41.add(btnXoaCTGG1);

        btnLamMoiCTGG1.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiCTGG1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLamMoiCTGG1.setForeground(new java.awt.Color(0, 51, 153));
        btnLamMoiCTGG1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        btnLamMoiCTGG1.setText("MỚI");
        btnLamMoiCTGG1.setBorder(null);
        btnLamMoiCTGG1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLamMoiCTGG1.setPreferredSize(new java.awt.Dimension(79, 42));
        jPanel41.add(btnLamMoiCTGG1);

        btnSuaCTGG1.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaCTGG1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSuaCTGG1.setForeground(new java.awt.Color(0, 51, 153));
        btnSuaCTGG1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        btnSuaCTGG1.setText("SỬA");
        btnSuaCTGG1.setBorder(null);
        btnSuaCTGG1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaCTGG1.setPreferredSize(new java.awt.Dimension(79, 42));
        btnSuaCTGG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTGG1ActionPerformed(evt);
            }
        });
        jPanel41.add(btnSuaCTGG1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã giảm giá", "Tên chương trình", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ));
        jScrollPane16.setViewportView(jTable2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã giảm giá", "Mã sách", "Chiếc khấu"
            }
        ));
        jScrollPane14.setViewportView(jTable1);

        jLabel70.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 102, 255));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("GIẢM GIÁ");

        jLabel85.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 102, 255));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("CHI TIẾT GIẢM GIÁ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(482, 482, 482)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(471, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaGGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaGGActionPerformed

    private void btnXoaGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaGGActionPerformed

    private void btnSuaCTGG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTGG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaCTGG1ActionPerformed

    private void btnXoaCTGG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTGG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaCTGG1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GiamGia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiCTGG1;
    private javax.swing.JButton btnLamMoiGG;
    private javax.swing.JButton btnSuaCTGG1;
    private javax.swing.JButton btnSuaGG;
    private javax.swing.JButton btnThemCTGG1;
    private javax.swing.JButton btnThemGG;
    private javax.swing.JButton btnXoaCTGG1;
    private javax.swing.JButton btnXoaGG;
    private com.toedter.calendar.JDateChooser dateNgayBD;
    private com.toedter.calendar.JDateChooser dateNgayKT;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField txtMaGG;
    private javax.swing.JTextField txtTenCT;
    // End of variables declaration//GEN-END:variables
}