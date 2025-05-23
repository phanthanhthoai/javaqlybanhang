/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.NhaCungCapBUS;
import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class nhacungcap extends javax.swing.JPanel {

    /**
     * Creates new form nhacungcap
     */
    public nhacungcap() {
        initComponents();
        taoBangTk();
        hienthidsncc();
        cacChucNang();
    }

    private boolean validateFormNCC() {
        String idncc = txtIdncc.getText().trim();
        String name = txtName.getText().trim();
        String sdt = txtSdt.getText().trim();
        String diachi = txtDiachi.getText().trim();

        // Kiểm tra ID nhà cung cấp (phải là số)
        if (idncc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhà cung cấp không được để trống!");
            return false;
        }
        try {
            Integer.parseInt(idncc); // Kiểm tra có phải số không
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã nhà cung cấp phải là số!");
            return false;
        }

        // Kiểm tra tên nhà cung cấp
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được để trống!");
            return false;
        }
        if (name.length() < 3 || name.length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp phải từ 3 đến 50 ký tự!");
            return false;
        }
        if (!name.matches("^[a-zA-Z0-9 ]+$")) { // Chỉ cho phép chữ cái, số, và khoảng trắng
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được chứa ký tự đặc biệt!");
            return false;
        }

        // Kiểm tra số điện thoại
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
            return false;
        }
        if (!sdt.matches("^[0-9]{10,11}$")) { // Chỉ cho phép số có 10-11 chữ số
            JOptionPane.showMessageDialog(this, "Số điện thoại phải từ 10 đến 11 số và chỉ chứa số!");
            return false;
        }

        // Kiểm tra địa chỉ
        if (diachi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
            return false;
        }
        if (diachi.length() < 5 || diachi.length() > 100) {
            JOptionPane.showMessageDialog(this, "Địa chỉ phải từ 5 đến 100 ký tự!");
            return false;
        }

        return true; // Nếu tất cả đều hợp lệ
    }

    private boolean validateFormAddNCC() {
        String name = txtName.getText().trim();
        String sdt = txtSdt.getText().trim();
        String diachi = txtDiachi.getText().trim();

        // Kiểm tra ID nhà cung cấp (phải là số)
        // Kiểm tra tên nhà cung cấp
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được để trống!");
            return false;
        }
        if (name.length() < 3 || name.length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp phải từ 3 đến 50 ký tự!");
            return false;
        }
        if (!name.matches("^[a-zA-Z0-9 ]+$")) { // Chỉ cho phép chữ cái, số, và khoảng trắng
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được chứa ký tự đặc biệt!");
            return false;
        }

        // Kiểm tra số điện thoại
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
            return false;
        }
        if (!sdt.matches("^[0-9]{10,11}$")) { // Chỉ cho phép số có 10-11 chữ số
            JOptionPane.showMessageDialog(this, "Số điện thoại phải từ 10 đến 11 số và chỉ chứa số!");
            return false;
        }

        // Kiểm tra địa chỉ
        if (diachi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
            return false;
        }
        if (diachi.length() < 5 || diachi.length() > 100) {
            JOptionPane.showMessageDialog(this, "Địa chỉ phải từ 5 đến 100 ký tự!");
            return false;
        }

        return true; // Nếu tất cả đều hợp lệ
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private DefaultTableModel tableModelNcc;

    private void taoBangTk() {
        tableModelNcc = new DefaultTableModel();
        tableModelNcc.setColumnIdentifiers(new String[]{
            "Mã nhà cung cấp", "Tên Nhà cung cấp", "Số điện thoại", "Địa chỉ", "Trạng thái"
        });
        jtbNcc.setModel(tableModelNcc);
    }
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();

    private void cacChucNang() {
        jtbNcc.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                clickChooseTbTk();
            }
        });
    }
    private int row = -1;

    private void clickChooseTbTk() {

        row = jtbNcc.getSelectedRow();
        if (row != -1) {
            btnAdd.setEnabled(false);
            txtIdncc.setText(jtbNcc.getValueAt(row, 0) + "");
            txtName.setText(jtbNcc.getValueAt(row, 1) + "");
            txtSdt.setText(jtbNcc.getValueAt(row, 2) + "");
            txtDiachi.setText(jtbNcc.getValueAt(row, 3) + "");
        }
    }

    private void hienthidsncc() {
        tableModelNcc.setRowCount(0);
        ArrayList<NhaCungCapDTO> lsNcc = nccBUS.lsNcc("");
        for (NhaCungCapDTO nccDto : lsNcc) {
            String trangThai = "Ngừng hợp tác!";
            if (nccDto.getDeleted() == 0) {
                trangThai = "Đang hợp tác!";
            }
            tableModelNcc.addRow(new Object[]{
                nccDto.getId(),
                nccDto.getNameNcc(),
                nccDto.getPhone(),
                nccDto.getAddress(),
                trangThai
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbNcc = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnChinhtt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiachi = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdncc = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1125, 660));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("QUẢN LÝ NHÀ CUNG CẤP");
        jLabel5.setPreferredSize(new java.awt.Dimension(298, 60));
        jPanel1.add(jLabel5, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jtbNcc.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbNcc);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnEdit.setBackground(new java.awt.Color(255, 255, 0));
        btnEdit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btnEdit.setMnemonic('s');
        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 204, 0));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnAdd.setMnemonic('t');
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnChinhtt.setBackground(new java.awt.Color(255, 102, 102));
        btnChinhtt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnChinhtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exchange.png"))); // NOI18N
        btnChinhtt.setMnemonic('d');
        btnChinhtt.setText("Đổi trạng thái");
        btnChinhtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhttActionPerformed(evt);
            }
        });

        txtDiachi.setColumns(20);
        txtDiachi.setRows(5);
        jScrollPane2.setViewportView(txtDiachi);

        jLabel4.setText("Địa chỉ: ");

        jLabel3.setText("Số điện thoại: ");

        jLabel2.setText("Tên nhà cung cấp: ");

        jLabel1.setText("Mã nhà cung cấp:");

        txtIdncc.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdncc)
                            .addComponent(txtName)
                            .addComponent(txtSdt))))
                .addGap(48, 48, 48))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChinhtt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSdt)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnChinhtt))
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents
    private void refresh() {
        btnAdd.setEnabled(true);
        txtIdncc.setText("");
        txtName.setText("");
        txtSdt.setText("");
        txtDiachi.setText("");
    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (validateFormAddNCC()) {
//            int idncc = Integer.parseInt(txtIdncc.getText());
            String nameNcc = txtName.getText();
            String sdt = txtSdt.getText();
            String diachi = txtDiachi.getText();

            if (nccBUS.themNcc(nameNcc, sdt, diachi)) {
                refresh();
                JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                hienthidsncc();
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (validateFormNCC()) {
            int idncc = Integer.parseInt(txtIdncc.getText());
            String nameNcc = txtName.getText();
            String sdt = txtSdt.getText();
            String diachi = txtDiachi.getText();
            if (nccBUS.suaNcc(idncc, nameNcc, sdt, diachi)) {
                refresh();
                JOptionPane.showMessageDialog(null, "Cập nhật nhà cung cấp thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                hienthidsncc();
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnChinhttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhttActionPerformed
        // TODO add your handling code here:
        if (validateFormNCC()) {
            int idncc = Integer.parseInt(txtIdncc.getText());
            int tt = nccBUS.layNccid(idncc).getDeleted();
            if (nccBUS.chinhTt(idncc, tt)) {
                refresh();
                JOptionPane.showMessageDialog(null, "Chỉnh sửa trạng thái thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                hienthidsncc();
            }
        }
    }//GEN-LAST:event_btnChinhttActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChinhtt;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtbNcc;
    private javax.swing.JTextArea txtDiachi;
    private javax.swing.JTextField txtIdncc;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSdt;
    // End of variables declaration//GEN-END:variables
}
