/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BillBUS;
import BUS.NhanVienBUS;
import DTO.BillDTO;
import DTO.BillDetailsDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhanVienDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class hoadon extends javax.swing.JPanel {

    /**
     * Creates new form hoadon
     */
    public hoadon() {
        initComponents();
        taoBangDsHoaDon();
        layDsHd();
        hienThiDanhSach(listAllBill);
        cacChucNang();
    }
    BillBUS billBUS = new BillBUS();
    ArrayList<BillDTO> listBill = new ArrayList<>();
    ArrayList<BillDTO> listAllBill = new ArrayList<>();

    NhanVienBUS nvBUS = new NhanVienBUS();

    private void layDsHd() {
        listAllBill = billBUS.layDsHd();
    }
    private DefaultTableModel tableModelHd;

    private void cacChucNang() {
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date dateNbd = txtNbd.getDate();
                Date dateNkt = txtNkt.getDate();

                String nbd = (dateNbd != null) ? sdf.format(dateNbd) : "";
                String nkt = (dateNkt != null) ? sdf.format(dateNkt) : "";

                if (nbd.isEmpty() && nkt.isEmpty()) {
                    hienThiDanhSach(listAllBill); // Không lọc, hiển thị toàn bộ
                } else if (nbd.isEmpty() || nkt.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ngày nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    locHoaDon(nbd, nkt);
                }
            }
        });
        btnXem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jtbBill.getSelectedRow(); // Lấy dòng đang chọn

                if (selectedRow == -1) { // Kiểm tra nếu chưa chọn dòng nào
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int idBill = Integer.parseInt(jtbBill.getValueAt(selectedRow, 0).toString());

                    // Tạo panel thông tin chi tiết sản phẩm
                    xemCthd panelXem = new xemCthd();
                    ArrayList<BillDetailsDTO> listBillD = billBUS.layCthd(idBill);
                    if (listBillD.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có sản phẩm trong hóa đơn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    panelXem.hienThiHoadon(idBill);

                    // Tạo JDialog chứa panel
                    JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(jtbBill), "Chi tiết sản phẩm", true);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.getContentPane().add(panelXem);
                    dialog.setSize(767, 600); // Đặt kích thước cho hộp thoại
                    dialog.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi hiển thị phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    private void locHoaDon(String bd, String kt) {
        listBill = new ArrayList<>();
        for (BillDTO billDto : listAllBill) {
            String ngayNhap = billDto.getDayCreated().split(" ")[0];
            if (ngayNhap.compareTo(bd) >= 0 && ngayNhap.compareTo(kt) <= 0) {
                listBill.add(billDto);
            }
        }
        hienThiDanhSach(listBill);
    }

    private void taoBangDsHoaDon() {
        tableModelHd = new DefaultTableModel();
        tableModelHd.setColumnIdentifiers(new String[]{
            "ID Hóa đơn", "Người tạo", "Ngày tạo", "Tổng tiền"
        });
        jtbBill.setModel(tableModelHd);
    }

    private void hienThiDanhSach(ArrayList<BillDTO> danhSach) {
        tableModelHd.setRowCount(0); // Xóa dữ liệu cũ  

        for (BillDTO billDto : danhSach) {

            NhanVienDTO nvDto = nvBUS.layNvId(billDto.getUser());
            tableModelHd.addRow(new Object[]{
                billDto.getId(),
                nvDto.getTenNV(),
                billDto.getDayCreated(),
                billDto.getTotalPay()
            });
        }
        tableModelHd.fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        txtNbd = new com.toedter.calendar.JDateChooser();
        txtNkt = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbBill = new javax.swing.JTable();
        btnXem = new javax.swing.JButton();
        btnTimkiem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1125, 660));

        jTextField1.setText("HÓA ĐƠN");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jtbBill.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbBill);

        btnXem.setText("Xem");

        btnTimkiem.setText("Tìm kiếm");

        jLabel1.setText("Ngày:");

        jLabel2.setText("-------");

        jButton1.setText("Xuất Excel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(456, 456, 456))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNbd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1)))
                    .addComponent(txtNkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(btnXem)
                        .addGap(54, 54, 54)
                        .addComponent(jButton1)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnXem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable jtbBill;
    private com.toedter.calendar.JDateChooser txtNbd;
    private com.toedter.calendar.JDateChooser txtNkt;
    // End of variables declaration//GEN-END:variables
}
