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

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDsphieunhap = new javax.swing.JTable();
        btnXacnhan = new javax.swing.JToggleButton();
        btnXem = new javax.swing.JToggleButton();
        btnXuatexcel = new javax.swing.JButton();
        txtNbd = new com.toedter.calendar.JDateChooser();
        txtNkt = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTimkiem = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1125, 667));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel2.setText("DANH SÁCH PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

        btnXacnhan.setText("Xác nhân");

        btnXem.setText("Xem");

        btnXuatexcel.setText("Xuất excel");

        jLabel1.setText("Ngày bắt đầu:");

        jLabel3.setText("Ngày kết thúc:");

        btnTimkiem.setText("Tìm kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnTimkiem))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimkiem)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnXacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnXuatexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JToggleButton btnXacnhan;
    private javax.swing.JToggleButton btnXem;
    private javax.swing.JButton btnXuatexcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbDsphieunhap;
    private com.toedter.calendar.JDateChooser txtNbd;
    private com.toedter.calendar.JDateChooser txtNkt;
    // End of variables declaration//GEN-END:variables
}
