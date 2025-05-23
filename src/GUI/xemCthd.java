/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BillBUS;
import BUS.SanPhamBUS;
import DTO.BillDetailsDTO;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class xemCthd extends javax.swing.JPanel {

    /**
     * Creates new form xemCthd
     */
    public xemCthd() {
        initComponents();
    }
    private BillBUS billBUS = new BillBUS();
    private SanPhamBUS spBUS = new SanPhamBUS();
    private DefaultTableModel model;

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   
    public void hienThiHoadon(int id) {
        ArrayList<BillDetailsDTO> dsCthd = billBUS.layCthd(id);

        // Kiểm tra nếu danh sách rỗng hoặc null
        if (dsCthd == null || dsCthd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu hóa đơn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Tạo model mới
        model = new DefaultTableModel(
                new String[]{"Mã hóa đơn", "Tên SP", "Số lượng", "Giá", "Tổng tiền"},
                0
        );

        for (BillDetailsDTO cthd : dsCthd) {
            // Lấy thông tin sản phẩm
            SanPhamDTO spDto = spBUS.laySpId(cthd.getIdProduct());
            String tenSanPham = (spDto != null) ? spDto.getName() : "Không tìm thấy";

            // Thêm dữ liệu vào model
            Object[] row = {
                cthd.getIdBill(),
                tenSanPham,
                cthd.getQuantity(),
                cthd.getPrice(),
                cthd.getTotalPay()
            };
            model.addRow(row);
        }

        jtbCthd.setModel(model); // Gán model mới cho bảng
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
        jtbCthd = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnXuatexcel = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1125, 660));
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);

        jtbCthd.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbCthd);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnXuatexcel.setBackground(new java.awt.Color(0, 204, 51));
        btnXuatexcel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXuatexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnXuatexcel.setMnemonic('x');
        btnXuatexcel.setText("Xuất Excel ");
        jPanel1.add(btnXuatexcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, -1));

        add(jPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatexcel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbCthd;
    // End of variables declaration//GEN-END:variables
}
