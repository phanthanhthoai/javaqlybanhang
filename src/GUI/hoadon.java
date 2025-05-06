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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbBill = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnTimkiem = new javax.swing.JButton();
        txtNkt = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtNbd = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnXem = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1125, 660));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("HÓA ĐƠN");
        jLabel3.setPreferredSize(new java.awt.Dimension(113, 35));
        jPanel1.add(jLabel3, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(51, 255, 51));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 153, 102));
        jPanel5.setLayout(new java.awt.BorderLayout());

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

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(956, 40));

        btnTimkiem.setBackground(new java.awt.Color(0, 255, 255));
        btnTimkiem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search2.png"))); // NOI18N
        btnTimkiem.setMnemonic('t');
        btnTimkiem.setText("Tìm kiếm");
        jPanel6.add(btnTimkiem);
        jPanel6.add(txtNkt);

        jLabel2.setText("-------");
        jPanel6.add(jLabel2);
        jPanel6.add(txtNbd);

        jLabel1.setText("Ngày:");
        jPanel6.add(jLabel1);

        jPanel3.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(0, 204, 51));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        jButton1.setMnemonic('e');
        jButton1.setText("Xuất Excel");

        btnXem.setBackground(new java.awt.Color(255, 255, 0));
        btnXem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/see.png"))); // NOI18N
        btnXem.setMnemonic('x');
        btnXem.setText("Xem");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnXem)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(495, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.WEST);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnXem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbBill;
    private com.toedter.calendar.JDateChooser txtNbd;
    private com.toedter.calendar.JDateChooser txtNkt;
    // End of variables declaration//GEN-END:variables
}
