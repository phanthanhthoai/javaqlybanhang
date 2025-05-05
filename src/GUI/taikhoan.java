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

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbTaikhoan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnThemtk = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cbbIdnv = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JPasswordField();
        txtName = new javax.swing.JTextField();
        txtIdtk = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1125, 750));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1125, 80));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 102, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("QUẢN LÝ TÀI KHOẢN");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel6, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 0, 51));
        jPanel2.setLayout(new java.awt.BorderLayout());

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

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 722));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Mã tài khoản: ");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 89, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Tên đăng nhập: ");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Mật khẩu: ");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 89, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Mã nhân viên: ");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 89, -1));

        btnThemtk.setBackground(new java.awt.Color(0, 204, 51));
        btnThemtk.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThemtk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnThemtk.setText("THÊM");
        btnThemtk.setPreferredSize(new java.awt.Dimension(72, 30));
        btnThemtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemtkActionPerformed(evt);
            }
        });
        jPanel3.add(btnThemtk, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 100, 40));

        jButton2.setBackground(new java.awt.Color(255, 255, 0));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        jButton2.setText("SỬA");
        jButton2.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 100, 40));

        jPanel3.add(cbbIdnv, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 140, 25));

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        jPanel3.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 146, -1));

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        jPanel3.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 146, -1));

        txtIdtk.setEditable(false);
        txtIdtk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdtkActionPerformed(evt);
            }
        });
        jPanel3.add(txtIdtk, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 146, -1));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exchange.png"))); // NOI18N
        jButton3.setMnemonic('d');
        jButton3.setText("ĐỔI TRẠNG THÁI");
        jButton3.setPreferredSize(new java.awt.Dimension(106, 30));
        jButton3.setVerifyInputWhenFocusTarget(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 160, 40));

        add(jPanel3, java.awt.BorderLayout.WEST);
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

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtIdtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdtkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdtkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThemtk;
    private javax.swing.JComboBox<String> cbbIdnv;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbTaikhoan;
    private javax.swing.JTextField txtIdtk;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
