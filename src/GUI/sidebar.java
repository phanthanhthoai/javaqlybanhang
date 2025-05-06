/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import GUI.chuyenmanhinhController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;

public class sidebar extends javax.swing.JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public sidebar() throws SQLException, IOException {
        initComponents();

        setTitle("SHOP");

        chuyenmanhinhController controller = new chuyenmanhinhController(jpnView);
        controller.setView(jpnTrangchu, jlbTrangchu);

        List<DanhMucBean> listItem = new ArrayList<>();

        listItem.add(new DanhMucBean("sanpham", jpnSanpham, jlbSanpham));
        listItem.add(new DanhMucBean("nhanvien", jpnNhanvien, jlbNhanvien));
        listItem.add(new DanhMucBean("vaitro", jpnVaitro, jlbVaitro));
        listItem.add(new DanhMucBean("trangchu", jpnTrangchu, jlbTrangchu));
        listItem.add(new DanhMucBean("phieunhap", jpnPhieunhap, jlbPhieunhap));
        listItem.add(new DanhMucBean("thongke", jpnThongke, jlbThongke));
        listItem.add(new DanhMucBean("hoadon", jpnHoadon, jlbHoadon));
        listItem.add(new DanhMucBean("taikhoan", jpnTaikhoan, jlbTaikhoan));
        listItem.add(new DanhMucBean("nhacungcap", jpnNhacungcap, jlbNhacungcap));
        listItem.add(new DanhMucBean("giamgia", jpnKhuyenmai, jlbKhuyenmai));

        controller.setEvent(listItem);
//        cauhinh();
    }

    public void setView(JPanel panel) {
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(panel);
        jpnView.revalidate();
        jpnView.repaint();
    }

    private void cauhinh() {
        jpnBar.setLayout(new BorderLayout());
        jpnBar.setPreferredSize(new Dimension(250, screenSize.height));
//        labelItem.setFont(new Font("Arial", Font.BOLD, 14));
        jlbHoadon.setFont(new Font("Arial", Font.BOLD, 14));
        jlbLogout.setFont(new Font("Arial", Font.BOLD, 14));
        jlbNhacungcap.setFont(new Font("Arial", Font.BOLD, 14));
        jlbNhanvien.setFont(new Font("Arial", Font.BOLD, 14));
        jlbPhieunhap.setFont(new Font("Arial", Font.BOLD, 14));
        jlbSanpham.setFont(new Font("Arial", Font.BOLD, 14));
        jlbTaikhoan.setFont(new Font("Arial", Font.BOLD, 14));
        jlbThongke.setFont(new Font("Arial", Font.BOLD, 14));
        jlbTrangchu.setFont(new Font("Arial", Font.BOLD, 14));
        jlbVaitro.setFont(new Font("Arial", Font.BOLD, 14));
        jlbKhuyenmai.setFont(new Font("Arial", Font.BOLD, 14));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnTrangchu = new javax.swing.JPanel();
        jlbTrangchu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpnSanpham = new javax.swing.JPanel();
        jlbSanpham = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpnNhanvien = new javax.swing.JPanel();
        jlbNhanvien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jpnTaikhoan = new javax.swing.JPanel();
        jlbTaikhoan = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jpnKhuyenmai = new javax.swing.JPanel();
        jlbKhuyenmai = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jpnHoadon = new javax.swing.JPanel();
        jlbHoadon = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jpnVaitro = new javax.swing.JPanel();
        jlbVaitro = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jpnPhieunhap = new javax.swing.JPanel();
        jlbPhieunhap = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jpnNhacungcap = new javax.swing.JPanel();
        jlbNhacungcap = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jpnThongke = new javax.swing.JPanel();
        jlbThongke = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jpnDangxuat = new javax.swing.JPanel();
        jlbLogout = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1400, 750));

        jpnBar.setBackground(new java.awt.Color(0, 153, 102));
        jpnBar.setForeground(new java.awt.Color(0, 153, 102));
        jpnBar.setMinimumSize(new java.awt.Dimension(220, 750));
        jpnBar.setPreferredSize(new java.awt.Dimension(220, 750));
        jpnBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logo1.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jpnBar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 220, 90));

        jpnTrangchu.setBackground(new java.awt.Color(255, 204, 0));
        jpnTrangchu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbTrangchu.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbTrangchu.setForeground(new java.awt.Color(0, 102, 102));
        jlbTrangchu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTrangchu.setText("Trang Chủ");
        jlbTrangchu.setToolTipText("");
        jpnTrangchu.add(jlbTrangchu, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 7, 100, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.png"))); // NOI18N
        jpnTrangchu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnTrangchu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 220, 35));

        jpnSanpham.setBackground(new java.awt.Color(255, 204, 0));
        jpnSanpham.setPreferredSize(new java.awt.Dimension(52, 35));
        jpnSanpham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbSanpham.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbSanpham.setForeground(new java.awt.Color(0, 102, 102));
        jlbSanpham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbSanpham.setText("Sản Phẩm");
        jpnSanpham.add(jlbSanpham, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 7, 100, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product.png"))); // NOI18N
        jpnSanpham.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnSanpham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 155, 220, 35));

        jpnNhanvien.setBackground(new java.awt.Color(255, 204, 51));
        jpnNhanvien.setPreferredSize(new java.awt.Dimension(53, 35));
        jpnNhanvien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbNhanvien.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbNhanvien.setForeground(new java.awt.Color(0, 102, 102));
        jlbNhanvien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbNhanvien.setText("Nhân Viên");
        jpnNhanvien.add(jlbNhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 7, 104, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/staff.png"))); // NOI18N
        jpnNhanvien.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnNhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 220, 35));

        jpnTaikhoan.setBackground(new java.awt.Color(255, 204, 0));
        jpnTaikhoan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbTaikhoan.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbTaikhoan.setForeground(new java.awt.Color(0, 102, 102));
        jlbTaikhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTaikhoan.setText("Tài Khoản");
        jpnTaikhoan.add(jlbTaikhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 7, 95, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/account.png"))); // NOI18N
        jpnTaikhoan.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnTaikhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 265, 220, 35));

        jpnKhuyenmai.setBackground(new java.awt.Color(255, 204, 0));
        jpnKhuyenmai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbKhuyenmai.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbKhuyenmai.setForeground(new java.awt.Color(0, 102, 102));
        jlbKhuyenmai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbKhuyenmai.setText("Khuyến mãi");
        jpnKhuyenmai.add(jlbKhuyenmai, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 7, 109, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/discout.png"))); // NOI18N
        jpnKhuyenmai.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnKhuyenmai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 220, 35));

        jpnHoadon.setBackground(new java.awt.Color(255, 204, 0));
        jpnHoadon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbHoadon.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbHoadon.setForeground(new java.awt.Color(0, 102, 102));
        jlbHoadon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbHoadon.setText("Hóa Đơn");
        jpnHoadon.add(jlbHoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 7, 90, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/invoice.png"))); // NOI18N
        jpnHoadon.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnHoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 375, 220, 35));

        jpnVaitro.setBackground(new java.awt.Color(255, 204, 0));
        jpnVaitro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbVaitro.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbVaitro.setForeground(new java.awt.Color(0, 102, 102));
        jlbVaitro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbVaitro.setText("Vai trò");
        jpnVaitro.add(jlbVaitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 7, 93, 20));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/role.png"))); // NOI18N
        jpnVaitro.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnVaitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 220, 35));

        jpnPhieunhap.setBackground(new java.awt.Color(255, 204, 0));
        jpnPhieunhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbPhieunhap.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbPhieunhap.setForeground(new java.awt.Color(0, 102, 102));
        jlbPhieunhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPhieunhap.setText("Phiếu Nhập");
        jpnPhieunhap.add(jlbPhieunhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 7, -1, 20));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/inbound.png"))); // NOI18N
        jpnPhieunhap.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnPhieunhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 485, 220, 35));

        jpnNhacungcap.setBackground(new java.awt.Color(255, 204, 0));
        jpnNhacungcap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbNhacungcap.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbNhacungcap.setForeground(new java.awt.Color(0, 102, 102));
        jlbNhacungcap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbNhacungcap.setText("Nhà Cung Cấp");
        jpnNhacungcap.add(jlbNhacungcap, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 7, -1, 20));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/supplier.png"))); // NOI18N
        jpnNhacungcap.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnNhacungcap, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 220, 35));

        jpnThongke.setBackground(new java.awt.Color(255, 204, 0));
        jpnThongke.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbThongke.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbThongke.setForeground(new java.awt.Color(0, 102, 102));
        jlbThongke.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbThongke.setText("Thống Kê");
        jlbThongke.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpnThongke.add(jlbThongke, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 7, 86, 20));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/stats.png"))); // NOI18N
        jpnThongke.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnThongke, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 595, 220, 35));

        jpnDangxuat.setBackground(new java.awt.Color(255, 0, 0));
        jpnDangxuat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbLogout.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jlbLogout.setForeground(new java.awt.Color(255, 255, 255));
        jlbLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbLogout.setText("Đăng Xuất");
        jlbLogout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlbLogout.setMaximumSize(new java.awt.Dimension(75, 19));
        jlbLogout.setMinimumSize(new java.awt.Dimension(75, 19));
        jlbLogout.setPreferredSize(new java.awt.Dimension(75, 19));
        jpnDangxuat.add(jlbLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 7, 126, 20));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout (1).png"))); // NOI18N
        jpnDangxuat.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jpnBar.add(jpnDangxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 220, 35));

        getContentPane().add(jpnBar, java.awt.BorderLayout.WEST);

        jpnView.setBackground(new java.awt.Color(255, 255, 204));
        jpnView.setMaximumSize(new java.awt.Dimension(3000, 3000));
        jpnView.setMinimumSize(new java.awt.Dimension(1180, 750));
        jpnView.setPreferredSize(new java.awt.Dimension(1145, 700));
        jpnView.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jpnView, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

public static void main(String args[]) {
    /* Đặt Look and Feel */
    try {
        // Thử Windows Look and Feel
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        try {
            // Nếu không hỗ trợ, dùng Nimbus Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            Logger.getLogger(sidebar.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /* Tạo và hiển thị giao diện */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                new sidebar().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(sidebar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(sidebar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jlbHoadon;
    private javax.swing.JLabel jlbKhuyenmai;
    private javax.swing.JLabel jlbLogout;
    private javax.swing.JLabel jlbNhacungcap;
    private javax.swing.JLabel jlbNhanvien;
    private javax.swing.JLabel jlbPhieunhap;
    private javax.swing.JLabel jlbSanpham;
    private javax.swing.JLabel jlbTaikhoan;
    private javax.swing.JLabel jlbThongke;
    private javax.swing.JLabel jlbTrangchu;
    private javax.swing.JLabel jlbVaitro;
    private javax.swing.JPanel jpnBar;
    private javax.swing.JPanel jpnDangxuat;
    private javax.swing.JPanel jpnHoadon;
    private javax.swing.JPanel jpnKhuyenmai;
    private javax.swing.JPanel jpnNhacungcap;
    private javax.swing.JPanel jpnNhanvien;
    private javax.swing.JPanel jpnPhieunhap;
    private javax.swing.JPanel jpnSanpham;
    private javax.swing.JPanel jpnTaikhoan;
    private javax.swing.JPanel jpnThongke;
    private javax.swing.JPanel jpnTrangchu;
    private javax.swing.JPanel jpnVaitro;
    private javax.swing.JPanel jpnView;
    // End of variables declaration//GEN-END:variables
}
