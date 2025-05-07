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

    public void setPanel(DanhMucBean dm) {
        dm.getJlb().addMouseListener(new LabelEvent(dm.getKind(), dm.getJpn(), dm.getJlb()));
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

        @Override
        public void mouseClicked(MouseEvent e) {
            String code = "view_" + kind;
   
            if (!kind.equals("danhsachphieunhap")) {
                if (!UserPermissions.getInstance().hasPermission(code)) {
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào mục này!");
                    return;
                }
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
                    node = new thongke();
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
                case "giamgia":
                    node = new giamgia();
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
            jlbItem.setForeground(new Color(255, 255, 255));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setForeground(new Color(255, 255, 255));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(255, 204, 0)); // Restore original yellow color
                jlbItem.setForeground(new Color(0, 102, 102)); // Restore original foreground color
            }
        }
    }

    void setChangeBackground(String kind) {
        for (DanhMucBean item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(96, 100, 191));
                item.getJlb().setForeground(new Color(255, 255, 255));
            } else {
                item.getJpn().setBackground(new Color(255, 204, 0)); // Restore original yellow color
                item.getJlb().setForeground(new Color(0, 102, 102)); // Restore original foreground color
            }
        }
    }
}