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
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbGiamgia = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtPercent = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNkt = new com.toedter.calendar.JDateChooser();
        txtNbd1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
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

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

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

        txtPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPercentActionPerformed(evt);
            }
        });

        jLabel2.setText("ID :");

        jLabel3.setText("Tên mã giảm giá:");

        jLabel4.setText("% Giảm giá:");

        jLabel5.setText("Ngày bắt đầu: ");

        jLabel6.setText("Ngày kết thúc:");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4))
                                        .addGap(53, 53, 53)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                                .addComponent(txtPercent))
                                            .addComponent(txtNkt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNbd1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addGap(70, 70, 70)
                                        .addComponent(btnEdit))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDel))))
                        .addGap(18, 109, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNbd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit)
                            .addComponent(btnAdd))
                        .addGap(39, 39, 39)
                        .addComponent(btnDel)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
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
