/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class SidebarExample extends javax.swing.JFrame {

    /**
     * Creates new form SidebarExample
     */
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JButton activeButton = null;
    private Map<String, Boolean> userPermissions;

    public SidebarExample() {
        setTitle("Sidebar Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Giả lập quyền của người dùng
        userPermissions = new HashMap<>();
        userPermissions.put("Quản lý hóa đơn", true);
        userPermissions.put("Quản lý sản phẩm", true);
        userPermissions.put("Quản lý nhân viên", false);
        userPermissions.put("Nhà cung cấp", true);
        userPermissions.put("Phiếu nhập", false);
        userPermissions.put("Vai trò", true);
        userPermissions.put("Tài khoản", true);
        userPermissions.put("Thống kê", true);

        // Tạo sidebar panel
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(220, getHeight()));
        sidebar.setBackground(Color.WHITE);

        // Logo
        JLabel logo = new JLabel("LOGO", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 18));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách

        // Khu vực chính với CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Tạo các JPanel cho từng giao diện
        JPanel trangchu = new JPanel();
        trangchu.setBackground(Color.LIGHT_GRAY);
        JPanel hoadon = new JPanel();
        hoadon.setBackground(Color.CYAN);
        JPanel sanpham = new JPanel();
        sanpham.setBackground(Color.ORANGE);
        JPanel nhanvien = new JPanel();
        nhanvien.setBackground(Color.PINK);
        JPanel nhacungcap = new JPanel();
        nhacungcap.setBackground(Color.YELLOW);
        JPanel phieunhap = new JPanel();
        phieunhap.setBackground(Color.GREEN);
        JPanel vaitro = new JPanel();
        vaitro.setBackground(Color.BLUE);
        JPanel taikhoan = new JPanel();
        taikhoan.setBackground(Color.MAGENTA);
        JPanel thongke = new JPanel();
        thongke.setBackground(Color.RED);

        // Thêm các JPanel vào mainPanel
        mainPanel.add(trangchu, "Trang chủ");
        mainPanel.add(hoadon, "Quản lý hóa đơn");
        mainPanel.add(sanpham, "Quản lý sản phẩm");
        mainPanel.add(nhanvien, "Quản lý nhân viên");
        mainPanel.add(nhacungcap, "Nhà cung cấp");
        mainPanel.add(phieunhap, "Phiếu nhập");
        mainPanel.add(vaitro, "Vai trò");
        mainPanel.add(taikhoan, "Tài khoản");
        mainPanel.add(thongke, "Thống kê");

        // Danh sách quyền và tên nút
        String[] buttonLabels = {
            "Trang chủ", "Quản lý hóa đơn", "Quản lý sản phẩm",
            "Quản lý nhân viên", "Nhà cung cấp", "Phiếu nhập",
            "Vai trò", "Tài khoản", "Thống kê"
        };

        // Tạo các nút sidebar dựa trên quyền
        for (String label : buttonLabels) {
            if (userPermissions.getOrDefault(label, false)) {
                JButton button = createSidebarButton(label);
                button.addActionListener(e -> switchPanel(label, button));
                sidebar.add(button);
                sidebar.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa các nút
            }
        }

        // Nút Logout
        JButton logoutButton = createSidebarButton("Đăng xuất");
        logoutButton.setBackground(new Color(200, 0, 0)); // Màu đỏ
        logoutButton.addActionListener(e -> logout());

        // Thêm khoảng cách để đẩy nút xuống dưới cùng
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách dưới nút Logout

        // Thêm sidebar và nội dung vào frame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }
        };

        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(72, 0, 255)); // Màu xanh dương đậm
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 40));
        button.setMaximumSize(new Dimension(200, 50));

        // Hiệu ứng hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != activeButton) {
                    button.setBackground(new Color(100, 50, 255)); // Màu hover
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != activeButton) {
                    button.setBackground(new Color(72, 0, 255)); // Màu bình thường
                }
            }
        });

        return button;
    }

    private void switchPanel(String panelName, JButton button) {
        cardLayout.show(mainPanel, panelName);
        if (activeButton != null) {
            activeButton.setBackground(new Color(72, 0, 255));
        }
        button.setBackground(new Color(50, 0, 200)); // Màu khi active
        activeButton = button;
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(
                null, "Bạn có chắc chắn muốn đăng xuất?",
                "Xác nhận Đăng Xuất", JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Đóng cửa sổ hiện tại
            SwingUtilities.invokeLater(() -> new Login().setVisible(true));
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(SidebarExample::new);
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SidebarExample().setVisible(true);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
//            java.util.logging.Logger.getLogger(SidebarExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SidebarExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SidebarExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SidebarExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SidebarExample().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
