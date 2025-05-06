/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class danhsachphieunhap extends javax.swing.JPanel {

    /**
     * Creates new form danhsachphieunhap
     */
    public danhsachphieunhap() {
        initComponents();
        taoBangDsPhieuNhap();
        taiTatCaPhieuNhap(); // Tải tất cả phiếu nhập từ database
        hienThiDanhSach(listPnAll);
        cacChucNang();
    }
    private ArrayList<PhieuNhapDTO> listPnAll = new ArrayList<>();
    private ArrayList<PhieuNhapDTO> listPn = new ArrayList<>();
    private PhieuNhapBUS pnBUS = new PhieuNhapBUS();
    private DefaultTableModel tableModelPn;

    private void taiTatCaPhieuNhap() {
        listPnAll = pnBUS.layDspn("", ""); // Lấy tất cả phiếu nhập từ database
    }

    private void cacChucNang() {
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date dateNbd = txtNbd.getDate();
                Date dateNkt = txtNkt.getDate();

                String nbd = (dateNbd != null) ? sdf.format(dateNbd) : "";
                String nkt = (dateNkt != null) ? sdf.format(dateNkt) : "";

                if (nbd.isEmpty() && nkt.isEmpty()) {
                    hienThiDanhSach(listPnAll); // Không lọc, hiển thị toàn bộ
                } else if (nbd.isEmpty() || nkt.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ngày nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    locPhieuNhap(nbd, nkt);
                }
            }
        });
        btnXacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int selectedRow = jtbDsphieunhap.getSelectedRow(); // Lấy dòng đang chọn

                if (selectedRow == -1) { // Kiểm tra nếu chưa chọn dòng nào
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu nhập!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int idPhieunhap = Integer.parseInt(jtbDsphieunhap.getValueAt(selectedRow, 0).toString());
                    if (pnBUS.xacNhan(idPhieunhap)) {
                        JOptionPane.showMessageDialog(null, "Xác nhận thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        hienThiDanhSach(listPnAll);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi hiển thị phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        btnXem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jtbDsphieunhap.getSelectedRow(); // Lấy dòng đang chọn

                if (selectedRow == -1) { // Kiểm tra nếu chưa chọn dòng nào
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu nhập!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int idPhieunhap = Integer.parseInt(jtbDsphieunhap.getValueAt(selectedRow, 0).toString());

                    // Tạo panel thông tin chi tiết sản phẩm
                    xemphieunhap panelXem = new xemphieunhap();
                    ArrayList<ChiTietPhieuNhapDTO> listCtpnDTO = pnBUS.layDsCtpnId(idPhieunhap);
                    if (listCtpnDTO.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có sản phẩm trong phiếu nhập!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    panelXem.hienThiPhieuNhap(idPhieunhap);

                    // Tạo JDialog chứa panel
                    JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(jtbDsphieunhap), "Chi tiết sản phẩm", true);
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

    private void locPhieuNhap(String bd, String kt) {
        listPn = new ArrayList<>();
        for (PhieuNhapDTO pn : listPnAll) {
            String ngayNhap = pn.getDayReceive().split(" ")[0];
            if (ngayNhap.compareTo(bd) >= 0 && ngayNhap.compareTo(kt) <= 0) {
                listPn.add(pn);
            }
        }
        hienThiDanhSach(listPn);
    }
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();

    private void hienThiDanhSach(ArrayList<PhieuNhapDTO> danhSach) {
        tableModelPn.setRowCount(0); // Xóa dữ liệu cũ  

        for (PhieuNhapDTO pnDto : danhSach) {
            String trangThai = "Chưa xác nhận!";
            if (pnDto.getDeleted() == 0) {
                trangThai = "Đã nhập!";
            }
//            NhaCungCapDTO nccDto=nccBUS.;
            NhanVienDTO nvDto = nvBUS.layNvId(pnDto.getIdUser());
            tableModelPn.addRow(new Object[]{
                pnDto.getId(),
                pnDto.getIdSupplier(),
                pnDto.getDayReceive(),
                nvDto.getTenNV(),
                pnDto.getTotalMoney(),
                trangThai
            });
        }

        tableModelPn.fireTableDataChanged();
    }

    private void taoBangDsPhieuNhap() {
        tableModelPn = new DefaultTableModel();
        tableModelPn.setColumnIdentifiers(new String[]{
            "ID Phiếu nhập", "Nhà cung cấp", "Ngày nhập", "Người nhập", "Tổng tiền (VNĐ)", "Trạng thái"
        });
        jtbDsphieunhap.setModel(tableModelPn);
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
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNbd = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtNkt = new com.toedter.calendar.JDateChooser();
        btnTimkiem = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDsphieunhap = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnXuatexcel = new javax.swing.JButton();
        btnXem = new javax.swing.JToggleButton();
        btnXacnhan = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1125, 667));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(925, 40));

        jLabel1.setText("Ngày bắt đầu:");

        jLabel3.setText("Ngày kết thúc:");

        btnTimkiem.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnTimkiem)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jtbDsphieunhap.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbDsphieunhap);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 652));

        btnXuatexcel.setBackground(new java.awt.Color(0, 204, 204));
        btnXuatexcel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXuatexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnXuatexcel.setMnemonic('u');
        btnXuatexcel.setText("XUẤT EXCEL");

        btnXem.setBackground(new java.awt.Color(255, 255, 0));
        btnXem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/see.png"))); // NOI18N
        btnXem.setMnemonic('e');
        btnXem.setText("XEM");

        btnXacnhan.setBackground(new java.awt.Color(0, 204, 51));
        btnXacnhan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXacnhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/checked.png"))); // NOI18N
        btnXacnhan.setMnemonic('x');
        btnXacnhan.setText("XÁC NHẬN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnXem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXacnhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXuatexcel))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnXacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(457, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.WEST);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(new java.awt.Color(51, 102, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DANH SÁCH PHIẾU NHẬP");
        jLabel2.setPreferredSize(new java.awt.Dimension(296, 35));
        jPanel2.add(jLabel2, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JToggleButton btnXacnhan;
    private javax.swing.JToggleButton btnXem;
    private javax.swing.JButton btnXuatexcel;
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
    private javax.swing.JTable jtbDsphieunhap;
    private com.toedter.calendar.JDateChooser txtNbd;
    private com.toedter.calendar.JDateChooser txtNkt;
    // End of variables declaration//GEN-END:variables
}
