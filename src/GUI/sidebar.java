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
        jPanel3 = new javax.swing.JPanel();
        jpnSanpham = new javax.swing.JPanel();
        jlbSanpham = new javax.swing.JLabel();
        jpnNhanvien = new javax.swing.JPanel();
        jlbNhanvien = new javax.swing.JLabel();
        jpnTaikhoan = new javax.swing.JPanel();
        jlbTaikhoan = new javax.swing.JLabel();
        jpnTrangchu = new javax.swing.JPanel();
        jlbTrangchu = new javax.swing.JLabel();
        jpnHoadon = new javax.swing.JPanel();
        jlbHoadon = new javax.swing.JLabel();
        jpnVaitro = new javax.swing.JPanel();
        jlbVaitro = new javax.swing.JLabel();
        jpnPhieunhap = new javax.swing.JPanel();
        jlbPhieunhap = new javax.swing.JLabel();
        jpnNhacungcap = new javax.swing.JPanel();
        jlbNhacungcap = new javax.swing.JLabel();
        jpnThongke = new javax.swing.JPanel();
        jlbThongke = new javax.swing.JLabel();
        jpnDangxuat = new javax.swing.JPanel();
        jlbLogout = new javax.swing.JLabel();
        jpnKhuyenmai = new javax.swing.JPanel();
        jlbKhuyenmai = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnBar.setBackground(new java.awt.Color(0, 204, 102));
        jpnBar.setPreferredSize(new java.awt.Dimension(219, 667));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jpnSanpham.setPreferredSize(new java.awt.Dimension(52, 35));

        jlbSanpham.setText("Sản Phẩm");

        javax.swing.GroupLayout jpnSanphamLayout = new javax.swing.GroupLayout(jpnSanpham);
        jpnSanpham.setLayout(jpnSanphamLayout);
        jpnSanphamLayout.setHorizontalGroup(
            jpnSanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbSanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnSanphamLayout.setVerticalGroup(
            jpnSanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbSanpham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jpnNhanvien.setPreferredSize(new java.awt.Dimension(53, 35));

        jlbNhanvien.setText("Nhân Viên");

        javax.swing.GroupLayout jpnNhanvienLayout = new javax.swing.GroupLayout(jpnNhanvien);
        jpnNhanvien.setLayout(jpnNhanvienLayout);
        jpnNhanvienLayout.setHorizontalGroup(
            jpnNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbNhanvien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnNhanvienLayout.setVerticalGroup(
            jpnNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbNhanvien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jlbTaikhoan.setText("Tài Khoản");

        javax.swing.GroupLayout jpnTaikhoanLayout = new javax.swing.GroupLayout(jpnTaikhoan);
        jpnTaikhoan.setLayout(jpnTaikhoanLayout);
        jpnTaikhoanLayout.setHorizontalGroup(
            jpnTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTaikhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnTaikhoanLayout.setVerticalGroup(
            jpnTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jlbTrangchu.setText("Trang Chủ");

        javax.swing.GroupLayout jpnTrangchuLayout = new javax.swing.GroupLayout(jpnTrangchu);
        jpnTrangchu.setLayout(jpnTrangchuLayout);
        jpnTrangchuLayout.setHorizontalGroup(
            jpnTrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTrangchu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnTrangchuLayout.setVerticalGroup(
            jpnTrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jlbHoadon.setText("Hóa Đơn");

        javax.swing.GroupLayout jpnHoadonLayout = new javax.swing.GroupLayout(jpnHoadon);
        jpnHoadon.setLayout(jpnHoadonLayout);
        jpnHoadonLayout.setHorizontalGroup(
            jpnHoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbHoadon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnHoadonLayout.setVerticalGroup(
            jpnHoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbHoadon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        jlbVaitro.setText("Vai trò");

        javax.swing.GroupLayout jpnVaitroLayout = new javax.swing.GroupLayout(jpnVaitro);
        jpnVaitro.setLayout(jpnVaitroLayout);
        jpnVaitroLayout.setHorizontalGroup(
            jpnVaitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
            .addGroup(jpnVaitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jlbVaitro, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
        );
        jpnVaitroLayout.setVerticalGroup(
            jpnVaitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
            .addGroup(jpnVaitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnVaitroLayout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(jlbVaitro, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addGap(2, 2, 2)))
        );

        jlbPhieunhap.setText("Phiếu Nhập");

        javax.swing.GroupLayout jpnPhieunhapLayout = new javax.swing.GroupLayout(jpnPhieunhap);
        jpnPhieunhap.setLayout(jpnPhieunhapLayout);
        jpnPhieunhapLayout.setHorizontalGroup(
            jpnPhieunhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbPhieunhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnPhieunhapLayout.setVerticalGroup(
            jpnPhieunhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbPhieunhap, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jlbNhacungcap.setText("Nhà Cung Cấp");

        javax.swing.GroupLayout jpnNhacungcapLayout = new javax.swing.GroupLayout(jpnNhacungcap);
        jpnNhacungcap.setLayout(jpnNhacungcapLayout);
        jpnNhacungcapLayout.setHorizontalGroup(
            jpnNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbNhacungcap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnNhacungcapLayout.setVerticalGroup(
            jpnNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbNhacungcap, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jlbThongke.setText("Thống Kê");

        javax.swing.GroupLayout jpnThongkeLayout = new javax.swing.GroupLayout(jpnThongke);
        jpnThongke.setLayout(jpnThongkeLayout);
        jpnThongkeLayout.setHorizontalGroup(
            jpnThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbThongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnThongkeLayout.setVerticalGroup(
            jpnThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbThongke, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jpnDangxuat.setBackground(new java.awt.Color(255, 0, 0));

        jlbLogout.setText("                       LOG OUT");

        javax.swing.GroupLayout jpnDangxuatLayout = new javax.swing.GroupLayout(jpnDangxuat);
        jpnDangxuat.setLayout(jpnDangxuatLayout);
        jpnDangxuatLayout.setHorizontalGroup(
            jpnDangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDangxuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnDangxuatLayout.setVerticalGroup(
            jpnDangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDangxuatLayout.createSequentialGroup()
                .addComponent(jlbLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jlbKhuyenmai.setText("Khuyến mãi");

        javax.swing.GroupLayout jpnKhuyenmaiLayout = new javax.swing.GroupLayout(jpnKhuyenmai);
        jpnKhuyenmai.setLayout(jpnKhuyenmaiLayout);
        jpnKhuyenmaiLayout.setHorizontalGroup(
            jpnKhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbKhuyenmai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnKhuyenmaiLayout.setVerticalGroup(
            jpnKhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbKhuyenmai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnBarLayout = new javax.swing.GroupLayout(jpnBar);
        jpnBar.setLayout(jpnBarLayout);
        jpnBarLayout.setHorizontalGroup(
            jpnBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnDangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnBarLayout.createSequentialGroup()
                .addGroup(jpnBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnBarLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnBarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpnTrangchu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnVaitro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnPhieunhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnNhacungcap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnThongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnTaikhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnHoadon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnSanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(jpnNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
                    .addGroup(jpnBarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpnKhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnBarLayout.setVerticalGroup(
            jpnBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnTrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jpnSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jpnNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jpnTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnKhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jpnHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jpnVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jpnPhieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jpnNhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jpnThongke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jpnDangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnView.setMaximumSize(new java.awt.Dimension(1125, 667));
        jpnView.setMinimumSize(new java.awt.Dimension(1125, 667));
        jpnView.setPreferredSize(new java.awt.Dimension(1125, 667));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1173, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, 1173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
            .addComponent(jpnBar, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

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
    private javax.swing.JPanel jPanel3;
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
