/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class taikhoan extends javax.swing.JPanel {

    /**
     * Creates new form taikhoan
     */
    public taikhoan() {
        initComponents();
        taoBangTk();
        hienthids();
        khoitao();
        cacChucNang();
    }

    private void cacChucNang() {
        jtbTaikhoan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                clickChooseTbTk();
            }
        });
    }
    private int row = -1;

    private void clickChooseTbTk() {

        row = jtbTaikhoan.getSelectedRow();
        if (row != -1) {
            btnThemtk.setEnabled(false);
            txtIdtk.setText(jtbTaikhoan.getValueAt(row, 0) + "");
            txtName.setText(jtbTaikhoan.getValueAt(row, 1) + "");
            txtPassword.setText(jtbTaikhoan.getValueAt(row, 2) + "");

            cbbIdnv.setSelectedItem(jtbTaikhoan.getValueAt(row, 3) + "");
        }
    }

    private void refresh() {
        btnThemtk.setEnabled(true);
        txtIdtk.setText("");
        txtName.setText("");
        txtPassword.setText("");
    }

    private boolean validateFormThem() {
        String name = txtName.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String idnv = (String) cbbIdnv.getSelectedItem();

        // Kiểm tra tên đăng nhập (6-25 ký tự, không chứa ký tự đặc biệt)
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống!");
            return false;
        }
        if (name.length() < 6 || name.length() > 25) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập phải từ 6 đến 25 ký tự!");
            return false;
        }
        if (!name.matches("^[a-zA-Z0-9]+$")) { // Chỉ cho phép chữ cái và số
            JOptionPane.showMessageDialog(this, "Tên đăng nhập chỉ được chứa chữ cái và số!");
            return false;
        }

        // Kiểm tra mật khẩu (tối thiểu 6 ký tự)
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống!");
            return false;
        }
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!");
            return false;
        }

        // Kiểm tra ID nhân viên
        if (idnv == null || idnv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã nhân viên!");
            return false;
        }

        return true; // Nếu tất cả đều hợp lệ
    }

    private boolean validateForm() {
        String idtk = txtIdtk.getText().trim();
        String name = txtName.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String idnv = (String) cbbIdnv.getSelectedItem();

        // Kiểm tra ID tài khoản (phải là số)
        if (idtk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID tài khoản không được để trống!");
            return false;
        }
        try {
            Integer.parseInt(idtk); // Kiểm tra có phải số không
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID tài khoản phải là số!");
            return false;
        }

        // Kiểm tra tên đăng nhập (6-25 ký tự, không chứa ký tự đặc biệt)
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống!");
            return false;
        }
        if (name.length() < 6 || name.length() > 25) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập phải từ 6 đến 25 ký tự!");
            return false;
        }
        if (!name.matches("^[a-zA-Z0-9]+$")) { // Chỉ cho phép chữ cái và số
            JOptionPane.showMessageDialog(this, "Tên đăng nhập chỉ được chứa chữ cái và số!");
            return false;
        }

        // Kiểm tra mật khẩu (tối thiểu 6 ký tự)
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống!");
            return false;
        }
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!");
            return false;
        }

        // Kiểm tra ID nhân viên
        if (idnv == null || idnv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã nhân viên!");
            return false;
        }

        return true; // Nếu tất cả đều hợp lệ
    }
    private NhanVienDAO nvDAO = new NhanVienDAO();
    private ArrayList<NhanVienDTO> listnv = new ArrayList<>();

    private void khoitao() {
        listnv = nvDAO.layDsNv("");
        cbbIdnv.removeAll();
        ArrayList<Integer> dsidnv = new ArrayList<>();
        for (NhanVienDTO nvDto : listnv) {
            dsidnv.add(nvDto.getMaNV());
            cbbIdnv.addItem(nvDto.getMaNV() + "");
        }
    }
    private DefaultTableModel tableModelTk;

    private void taoBangTk() {
        tableModelTk = new DefaultTableModel();
        tableModelTk.setColumnIdentifiers(new String[]{
            "Mã tài khoản", "Tên đăng nhập", "Mật khẩu", "Mã nhân viên", "Tên nhân viên", "Ngày cấp", "Trạng thái"
        });
        jtbTaikhoan.setModel(tableModelTk);
    }
    private TaiKhoanBUS tkBUS = new TaiKhoanBUS();
    private ArrayList<TaiKhoanDTO> listTk = new ArrayList<>();
    private NhanVienBUS nvBUS = new NhanVienBUS();

    private void hienthids() {
        listTk = tkBUS.listTk();
        tableModelTk.setRowCount(0);
        for (TaiKhoanDTO tkDto : listTk) {
            String trangThai = "Vô hiệu hóa!";
            if (tkDto.getTrangThaiTaiKhoan() == 0) {
                trangThai = "Mở khóa!";
            }
            NhanVienDTO nvDto = nvBUS.layNvId(tkDto.getMaNhanVien());
            tableModelTk.addRow(new Object[]{
                tkDto.getMaTaiKhoan(),
                tkDto.getTenDangNhap(),
                tkDto.getMatKhau(),
                tkDto.getMaNhanVien(),
                nvDto.getTenNV(),
                tkDto.getNgayCap(),
                trangThai
            });
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbTaikhoan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdtk = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        cbbIdnv = new javax.swing.JComboBox<>();
        btnThemtk = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();

        setPreferredSize(new java.awt.Dimension(1125, 660));

        jtbTaikhoan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbTaikhoan);

        jLabel1.setText("Mã tài khoản: ");

        jLabel2.setText("Tên đăng nhập: ");

        jLabel4.setText("Mã nhân viên: ");

        txtIdtk.setEditable(false);

        btnThemtk.setText("Thêm");
        btnThemtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemtkActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Đổi trạng thái");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Mật khẩu: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnThemtk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbIdnv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(txtIdtk)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(72, 72, 72)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtIdtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbIdnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemtk)
                    .addComponent(jButton3))
                .addGap(36, 36, 36)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemtkActionPerformed
        // TODO add your handling code here:
        if (validateFormThem()) {
            String name = txtName.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            int idnv = Integer.parseInt((String) cbbIdnv.getSelectedItem());
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            if (tkBUS.themTk(name, password, idnv, formattedDateTime)) {
                refresh();
                JOptionPane.showMessageDialog(null, "oke", "Success", JOptionPane.INFORMATION_MESSAGE);
                hienthids();
            }
        }

    }//GEN-LAST:event_btnThemtkActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            int idtk = Integer.parseInt(txtIdtk.getText().trim());
            String name = txtName.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            int idnv = Integer.parseInt((String) cbbIdnv.getSelectedItem());
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            if (tkBUS.suaTk(idtk, name, password, idnv)) {
                refresh();
                JOptionPane.showMessageDialog(null, "oke", "Success", JOptionPane.INFORMATION_MESSAGE);
                hienthids();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    private TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (validateForm()) {
            int idtk = Integer.parseInt(txtIdtk.getText().trim());
            int tt = tkDAO.layTkid(idtk).getTrangThaiTaiKhoan();
            if(tkBUS.chinhTt(idtk,tt)){
                refresh();
                JOptionPane.showMessageDialog(null, "oke", "Success", JOptionPane.INFORMATION_MESSAGE);
                hienthids();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThemtk;
    private javax.swing.JComboBox<String> cbbIdnv;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbTaikhoan;
    private javax.swing.JTextField txtIdtk;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
