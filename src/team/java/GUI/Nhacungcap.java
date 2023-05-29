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
public class Nhacungcap extends javax.swing.JFrame {

    /**
     * Creates new form Nhacungcap
     */
    public Nhacungcap() {
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

        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblNCC = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        txtMaNCC = new javax.swing.JTextField();
        txtTenNCC = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jPanel37 = new javax.swing.JPanel();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        comNCC = new javax.swing.JComboBox<>();
        txtTimKiem = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel79 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setLayout(null);

        tblNCC.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Điện thoại"
            }
        ));
        tblNCC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblNCC.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setViewportView(tblNCC);

        jPanel3.add(jScrollPane8);
        jScrollPane8.setBounds(520, 40, 370, 450);

        jPanel19.setBackground(new java.awt.Color(204, 255, 204));
        jPanel19.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel72.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel72.setText("Mã NCC");
        jLabel72.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel73.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel73.setText("Tên NCC");

        jLabel77.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel77.setText("Địa chỉ");
        jLabel77.setPreferredSize(new java.awt.Dimension(1000, 20));

        jLabel78.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel78.setText("Điện thoại");
        jLabel78.setPreferredSize(new java.awt.Dimension(1000, 20));

        txtMaNCC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMaNCC.setPreferredSize(new java.awt.Dimension(7, 40));

        txtTenNCC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTenNCC.setPreferredSize(new java.awt.Dimension(7, 40));

        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDiaChi.setPreferredSize(new java.awt.Dimension(7, 40));

        txtSDT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSDT.setPreferredSize(new java.awt.Dimension(7, 40));
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jPanel37.setBackground(new java.awt.Color(204, 255, 204));

        jButton30.setBackground(new java.awt.Color(0, 204, 204));
        jButton30.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton30.setForeground(new java.awt.Color(0, 51, 153));
        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_30px_1.png"))); // NOI18N
        jButton30.setText("THÊM");
        jButton30.setBorder(null);
        jButton30.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton30.setPreferredSize(new java.awt.Dimension(79, 42));
        jPanel37.add(jButton30);

        jButton31.setBackground(new java.awt.Color(0, 204, 204));
        jButton31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton31.setForeground(new java.awt.Color(0, 51, 153));
        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_30px.png"))); // NOI18N
        jButton31.setText("SỬA");
        jButton31.setBorder(null);
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton31.setPreferredSize(new java.awt.Dimension(79, 42));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel37.add(jButton31);

        jButton32.setBackground(new java.awt.Color(0, 204, 204));
        jButton32.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton32.setForeground(new java.awt.Color(0, 51, 153));
        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_cancel_30px_9.png"))); // NOI18N
        jButton32.setText("XÓA");
        jButton32.setBorder(null);
        jButton32.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton32.setPreferredSize(new java.awt.Dimension(79, 42));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel37.add(jButton32);

        jButton33.setBackground(new java.awt.Color(0, 204, 204));
        jButton33.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton33.setForeground(new java.awt.Color(0, 51, 153));
        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/available_updates_20px.png"))); // NOI18N
        jButton33.setText("MỚI");
        jButton33.setBorder(null);
        jButton33.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton33.setPreferredSize(new java.awt.Dimension(79, 42));
        jPanel37.add(jButton33);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addGap(23, 23, 23)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        jPanel3.add(jPanel19);
        jPanel19.setBounds(20, 90, 470, 320);

        comNCC.setEditable(true);
        comNCC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm kiếm theo mã nhà cung cấp", "Tìm kiếm theo tên nhà cung cấp" }));
        comNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comNCCActionPerformed(evt);
            }
        });
        jPanel3.add(comNCC);
        comNCC.setBounds(190, 10, 240, 30);

        txtTimKiem.setBackground(new java.awt.Color(204, 255, 204));
        txtTimKiem.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTimKiem.setBorder(null);
        txtTimKiem.setOpaque(false);
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        jPanel3.add(txtTimKiem);
        txtTimKiem.setBounds(190, 40, 240, 30);

        jSeparator1.setForeground(new java.awt.Color(0, 153, 255));
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(190, 70, 240, 10);
        jPanel3.add(jLabel79);
        jLabel79.setBounds(450, 40, 40, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void comNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comNCCActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

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
            java.util.logging.Logger.getLogger(Nhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nhacungcap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comNCC;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblNCC;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
