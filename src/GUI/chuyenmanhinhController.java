/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import GUI.DanhMucBean;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class chuyenmanhinhController {

    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = new ArrayList<DanhMucBean>();

    public chuyenmanhinhController(JPanel root) {
        this.root = root;
    }

//    public void setView(JPanel jpnItem, JLabel jlbItem) throws SQLException, IOException {
//        kindSelected = "trangchu";
//        jpnItem.setBackground(new Color(96, 100, 191));
//        jlbItem.setBackground(new Color(96, 100, 191));
//
//        root.removeAll();
//        root.setLayout(new BorderLayout());
//        root.add(new trangchu());
//        root.validate();
//        root.repaint();
//    }
    public void setView(JPanel jpnItem, JLabel jlbItem) throws SQLException, IOException {
        kindSelected = "trangchu";
        setChangeBackground(kindSelected);

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new trangchu());
        root.revalidate(); // Cập nhật giao diện
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        if (listItem == null || listItem.isEmpty()) {
            return;
        }
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }
    public void setPanel(DanhMucBean dm){
        dm.getJlb().addMouseListener(new LabelEvent(dm.getKind(), dm.getJpn(), dm.getJlb()));
    }

    private JPanel createPillShapedPanel(JLabel jlb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    class LabelEvent implements MouseListener {

        private JPanel node;

        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent() {
        }

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        private JPanel createPillShapedPanel(JLabel jlb) {
            PillShapedPanel panel = new PillShapedPanel(new Color(96, 100, 191));
            panel.setPreferredSize(new Dimension(230, 50));
            panel.setLayout(new BorderLayout());
            panel.add(jlb, BorderLayout.CENTER);
            jlb.setHorizontalAlignment(SwingConstants.CENTER);
            panel.setOpaque(false); // Đảm bảo nền trong suốt

            return panel;
        }

        public class PillShapedPanel extends JPanel {

            private Color backgroundColor;

            public PillShapedPanel(Color backgroundColor) {
                this.backgroundColor = backgroundColor;
                setOpaque(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int arcSize = height; // Để hình viên thuốc

                g2.setColor(backgroundColor);
                g2.fillRoundRect(0, 0, width, height, arcSize, arcSize);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            String code = "view_" + kind;

            if (!UserPermissions.getInstance().hasPermission(code)) {
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào mục này!");
                return;
            }

            switch (kind) {
                case "trangchu":
                    node = new trangchu();
                    break;
                case "nhanvien":
                    node = new nhanvien();
                    break;
                case "sanpham":
                    node = new sanpham();
                    break;
                case "phieunhap":
                    node = new phieunhap();
                    break;
                case "danhsachphieunhap":
                    node = new danhsachphieunhap();
                    break;
                case "thongke":
                    node = new thongke(); // Sửa lại đúng class cần hiển thị
                    break;
                case "vaitro":
                    node = new vaitro();
                    break;
                case "taikhoan":
                    node = new taikhoan();
                    break;
                case "hoadon":
                    node = new hoadon();
                    break;
                case "nhacungcap":
                    node = new nhacungcap();
                    break;
                default:
                    return;
            }

            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.revalidate();
            root.repaint();

            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(76, 175, 80));
                jlbItem.setBackground(new Color(76, 175, 80));
            }
        }
    }

    void setChangeBackground(String kind) {
        for (DanhMucBean item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                JPanel newPanel = createPillShapedPanel(item.getJlb());
                item.setJpn(newPanel); // Cập nhật JPanel mới
                newPanel.setBackground(new Color(96, 100, 191));
                item.getJlb().setBackground(new Color(255, 255, 255)); // Chữ màu trắng

                // Remove panel cũ và add lại panel mới
                root.remove(item.getJpn());
                root.add(newPanel);
                root.revalidate();
                root.repaint();
            } else {
                item.getJpn().setBackground(new Color(76, 175, 80));
                item.getJlb().setBackground(new Color(0, 0, 0));
            }
        }
    }
//    void setChangeBackground(String kind) {
//        for (DanhMucBean item : listItem) {
//            if (item.getKind().equalsIgnoreCase(kind)) 
//                JPanel newPanel = createPillShapedPanel(item.getJlb());
//                item.setJpn(newPanel); // Cập nhật JPanel mới
//                newPanel.setBackground(new Color(96, 100, 191));
//                item.getJlb().setBackground(new Color(96, 100, 191));
//            } else {
//                item.getJpn().setBackground(new Color(76, 175, 80));
//                item.getJlb().setBackground(new Color(76, 175, 80));
//            }
//        }
//    }
}
