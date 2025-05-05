/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.DiscountBUS;
import DTO.DiscountDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

/**
 *
 * @author Admin
 */
public class giamgia extends javax.swing.JPanel {

    public giamgia() {
        initComponents();
        loadTable("");
        cacChucNang();
    }

    private void refresh() {
        txtId.setText("");
        txtName.setText("");
        txtPercent.setText("");
        LocalDate today = LocalDate.now();
        Date currentDate = java.sql.Date.valueOf(today);
        txtNbd1.setDate(currentDate);
        txtNkt.setDate(currentDate);
        loadTable("");
        btnAdd.setEnabled(true);
    }

    private DiscountBUS dcBUS = new DiscountBUS();
    DefaultTableModel tbmodel;

    private void loadTable(String search) {
        tbmodel = new DefaultTableModel();
        tbmodel.setRowCount(0); // Xóa dữ liệu cũ
        tbmodel.setColumnIdentifiers(new String[]{
            "ID Giảm giá", "Tên mã giảm giá", "% Giảm giá", "Ngày bắt đầu", "Ngày kết thúc"
        });
        ArrayList<DiscountDTO> list = dcBUS.laydsmgg(search);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (DiscountDTO d : list) {
            tbmodel.addRow(new Object[]{
                d.getIdDiscount(),
                d.getNameDiscount(),
                d.getPercent(),
                d.getDayStart(),
                d.getDayEnd()
            });
        }
        jtbGiamgia.setModel(tbmodel);
    }

    private boolean validateAddForm() {
        String name = txtName.getText().trim();
        String percentStr = txtPercent.getText().trim();
        Date startDate = txtNbd1.getDate();
        Date endDate = txtNkt.getDate();

        // Kiểm tra tên
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên giảm giá không được để trống.");
            txtName.requestFocus();
            return false;
        }

        // Kiểm tra phần trăm giảm giá
        int percent;
        try {
            percent = Integer.parseInt(percentStr);
            if (percent <= 0 || percent > 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm giá phải từ 1 đến 100.");
                txtPercent.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm giá phải là số nguyên.");
            txtPercent.requestFocus();
            return false;
        }

        // Kiểm tra ngày bắt đầu và kết thúc
        if (startDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu.");
            txtNbd1.requestFocus();
            return false;
        }

        if (endDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc.");
            txtNkt.requestFocus();
            return false;
        }

        // Ngày bắt đầu phải từ ngày mai
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        today.add(Calendar.DATE, 1); // Ngày mai

        if (startDate.before(today.getTime())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải từ ngày mai trở đi.");
            txtNbd1.requestFocus();
            return false;
        }

        // Ngày kết thúc phải sau ngày bắt đầu
        if (endDate.before(startDate)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu.");
            txtNkt.requestFocus();
            return false;
        }

        return true; // Hợp lệ
    }

    private boolean validateUpdateForm() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String percentStr = txtPercent.getText().trim();
        Date startDate = txtNbd1.getDate();
        Date endDate = txtNkt.getDate();

        // Kiểm tra mã giảm giá
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã giảm giá không được để trống.");
            txtId.requestFocus();
            return false;
        }

        // Kiểm tra tên
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên giảm giá không được để trống.");
            txtName.requestFocus();
            return false;
        }

        // Kiểm tra phần trăm giảm giá
        int percent;
        try {
            percent = Integer.parseInt(percentStr);
            if (percent <= 0 || percent > 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm giá phải từ 1 đến 100.");
                txtPercent.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm giá phải là số nguyên.");
            txtPercent.requestFocus();
            return false;
        }

        // Kiểm tra ngày bắt đầu và kết thúc
        if (startDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu.");
            txtNbd1.requestFocus();
            return false;
        }

        if (endDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc.");
            txtNkt.requestFocus();
            return false;
        }

        // Ngày bắt đầu phải từ ngày mai
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        today.add(Calendar.DATE, 1); // Ngày mai

        if (startDate.before(today.getTime())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải từ ngày mai trở đi.");
            txtNbd1.requestFocus();
            return false;
        }

        // Ngày kết thúc phải sau ngày bắt đầu
        if (endDate.before(startDate)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu.");
            txtNkt.requestFocus();
            return false;
        }

        return true; // Hợp lệ
    }
    private void cacChucNang() {
        jtbGiamgia.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                clickChooseTbgg();
            }
        });
    }
    private int row = -1;

    private void clickChooseTbgg() {
        row = jtbGiamgia.getSelectedRow();
        if (row != -1) {
            btnAdd.setEnabled(false);
            txtId.setText(jtbGiamgia.getValueAt(row, 0).toString());
            txtName.setText(jtbGiamgia.getValueAt(row, 1).toString());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date nbd = sdf.parse(jtbGiamgia.getValueAt(row, 3).toString());
                Date nkt = sdf.parse(jtbGiamgia.getValueAt(row, 4).toString());

                txtNbd1.setDate(nbd);
                txtNkt.setDate(nkt);
            } catch (Exception e) {
                e.printStackTrace(); // hoặc JOptionPane.showMessageDialog nếu bạn muốn thông báo lỗi
            }

            txtPercent.setText(jtbGiamgia.getValueAt(row, 2).toString());
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPercent = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNbd1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtNkt = new com.toedter.calendar.JDateChooser();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbGiamgia = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1125, 667));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ KHUYẾN MÃI");
        jLabel1.setPreferredSize(new java.awt.Dimension(264, 40));
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("ID :");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên mã giảm giá:");

        jLabel4.setText("% Giảm giá:");

        txtPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPercentActionPerformed(evt);
            }
        });

        jLabel5.setText("Ngày bắt đầu: ");

        jLabel6.setText("Ngày kết thúc:");

        btnAdd.setBackground(new java.awt.Color(0, 204, 51));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnAdd.setMnemonic('t');
        btnAdd.setText("THÊM");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(255, 255, 0));
        btnEdit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btnEdit.setMnemonic('s');
        btnEdit.setText("SỬA");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(255, 0, 0));
        btnDel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnDel.setMnemonic('x');
        btnDel.setText("XÓA");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(btnAdd))
                            .addComponent(jLabel2))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btnDel)
                                .addGap(26, 26, 26)
                                .addComponent(btnEdit)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNbd1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNkt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNbd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDel)
                    .addComponent(btnEdit))
                .addContainerGap(303, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jtbGiamgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbGiamgia);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(770, 45));

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 255, 255));
        btnSearch.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnSearch.setMnemonic('t');
        btnSearch.setText("TÌM KIẾM");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(btnSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPercentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPercentActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText().trim();
        int percent = Integer.parseInt(txtPercent.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = sdf.format(txtNbd1.getDate());
        String endDate = sdf.format(txtNkt.getDate());
        if (dcBUS.suaMgg(id, name, startDate, endDate, percent)) {
            refresh();
            JOptionPane.showMessageDialog(null, "Thêm mã giảm giá thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (validateAddForm()) {
            String name = txtName.getText().trim();
            int percent = Integer.parseInt(txtPercent.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String startDate = sdf.format(txtNbd1.getDate());
            String endDate = sdf.format(txtNkt.getDate());
            if (dcBUS.themMgg(name, startDate, endDate, percent)) {
                refresh();
                JOptionPane.showMessageDialog(null, "Thêm mã giảm giá thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(txtId.getText());
        if (!txtId.getText().equals("")) {
            if (dcBUS.chinhMgg(id, 0)) {
                refresh();
                JOptionPane.showMessageDialog(null, "Xóa mã giảm giá thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        loadTable(txtSearch.getText().trim());
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbGiamgia;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private com.toedter.calendar.JDateChooser txtNbd1;
    private com.toedter.calendar.JDateChooser txtNkt;
    private javax.swing.JTextField txtPercent;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
