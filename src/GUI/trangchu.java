/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BillBUS;
import BUS.BillDetailsBUS;
import BUS.ChiTietPhieuNhapBUS;
import BUS.DiscountBUS;
import DAO.ChiTietPhieuNhapDAO;
import DAO.SanPhamDAO;
import DTO.BillDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.DiscountDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import com.mysql.cj.jdbc.Blob;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JPanel;
import Util.InvoicePDF;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import static org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.model;

/**
 *
 * @author Admin
 */
public class trangchu extends javax.swing.JPanel {

    /**
     * Creates new form trangchu
     */
    public trangchu() {
        initComponents();
        khoiTao();
        cacChucNang();
    }
    ArrayList<ChiTietPhieuNhapDTO> ctpn = null;
    ArrayList<SanPhamDTO> dssp = null;
    ChiTietPhieuNhapDAO ctpnDAO = new ChiTietPhieuNhapDAO();
    SanPhamDAO spDAO = new SanPhamDAO();
    BillBUS billBUS = new BillBUS();
    BillDetailsBUS billDetailsBUS = new BillDetailsBUS();

    private void cacChucNang() {

        jtbCthd.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        jtbCthd, // Đúng component
                        "Bạn có muốn xóa sản phẩm?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    // Dừng chỉnh sửa nếu đang có ô được chỉnh sửa  
                    if (jtbCthd.isEditing()) {
                        jtbCthd.getCellEditor().stopCellEditing();
                    }
                    clickChooseInCthd();
                    DefaultTableModel model = (DefaultTableModel) jtbCthd.getModel();
                    capNhatSoThuTu(model);
                }
            }
        });
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String search = txtTimKiem.getText();
                cauhinh(search);
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int confirm = JOptionPane.showConfirmDialog(
                        jtbCthd, // Đúng component
                        "Bạn có muốn xóa hóa đơn?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    // Dừng chỉnh sửa nếu đang có ô được chỉnh sửa  
                    jtbCthd.removeAll();
                    khoiTao();
                }
            }
        });
        btnTt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = now.format(formatter);
                int idBill = Integer.parseInt(txtMahoadon.getText());
                NhanVienDTO nvDTO = loggedIn.getCurrentUser();
                int idUser = nvDTO.getMaNV();
                String totalPay = txtTongTien.getText();
                if (jtbCthd.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Không có sản phẩm nào trong hóa đơn!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (billBUS.themHoaDon(idUser, formattedDateTime, totalPay) && billDetailsBUS.themChiTietHoaDon(jtbCthd, idBill) && ctpnBUS.capNhatSoLuong(jtbCthd)) {
                    btnTtActionPerformed(evt);
                    khoiTao();
                }

            }
        });

    }

    private void btnTtActionPerformed(java.awt.event.ActionEvent evt) {
        if (jtbCthd.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không có sản phẩm nào trong hóa đơn!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String filePath = "hoadon.pdf"; // Đường dẫn lưu hóa đơn
        InvoicePDF.generateInvoice(filePath, jtbCthd, txtMahoadon.getText(), txtNgaytao.getText(), txtNguoitao.getText(), txtTongTien.getText(), 1);
        InvoicePDF.openPDF(filePath);
    }

    private BillDTO billDTO = new BillDTO();

    private void capNhatSoThuTu(DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0); 
        }
    }

    private void khoiTao() {

        DefaultTableModel model = (DefaultTableModel) jtbCthd.getModel();
        model.setRowCount(0);
        NhanVienDTO nvDTO = loggedIn.getCurrentUser();
        if (nvDTO == null) {
            System.out.println("Lỗi: Người dùng chưa đăng nhập!");
            txtNguoitao.setText("Không xác định");
            return;
        }

        txtNguoitao.setText(nvDTO.getTenNV());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = now.format(formatter);
        txtNgaytao.setText(formattedDateTime);
        billDTO = billBUS.layBillCuoi();
        txtTongTien.setText("");
        if (billDTO != null) {
            txtMahoadon.setText(String.valueOf(billDTO.getId() + 1));
        } else {
            System.out.println("Không tìm thấy hóa đơn cuối.");
            txtMahoadon.setText("1");
        }
        String[] columnNames = {"Stt", "Ctpn", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"};
        modelHoaDon = new DefaultTableModel(columnNames, 0);
        jtbCthd.setModel(modelHoaDon);
        cauhinh("");

    }

    private DefaultTableModel modelHoaDon;

    private void cauhinh(String search) {
        jpnProduct.removeAll();
        jpnProduct.setPreferredSize(new Dimension(600, 600));
        jpnDssp.removeAll();
        jpnDssp.setLayout(new GridLayout(0, 3, 10, 10)); // 1 cột, gap 20px
        loadProducts(search); // Load danh sách sản phẩm

        // Đặt kích thước tối thiểu để JScrollPane có thể cuộn
        int soSp = jpnDssp.getComponentCount();
        int du = soSp % 3;
        if (du == 1) {
            soSp = soSp + 2;
        }
        if (du == 2) {
            soSp = soSp + 1;
        }
        jpnDssp.setPreferredSize(new Dimension(200, soSp * 220 / 3));

        JScrollPane scrollPane = new JScrollPane(jpnDssp);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Đảm bảo jpnProduct sử dụng BorderLayout
        jpnProduct.setLayout(new BorderLayout());
        jpnProduct.add(scrollPane, BorderLayout.CENTER);

        jpnProduct.revalidate();
        jpnProduct.repaint();

    }

    private void loadProducts(String search) {
        jpnDssp.removeAll();

//        ctpn = ctpnDAO.laySpInPn(search);
        dssp = spDAO.layDsSp(search);

        if (dssp == null || dssp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Danh sách sản phẩm trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Với mỗi sản phẩm, tạo 1 panel để hiển thị thông tin và nút thao tác
        for (SanPhamDTO spDto : dssp) {
            SanPhamDTO sppn = spDAO.laySpId(spDto.getId());
            if (sppn == null) {
                continue;
            }

            // Tạo panel sản phẩm với layout BorderLayout
            JPanel panel = new JPanel(new BorderLayout());
            panel.setPreferredSize(new Dimension(200, 220));
            panel.setMaximumSize(new Dimension(200, 220));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // --- Phần hiển thị ảnh sản phẩm (bên trái) ---
            JLabel lbAnh = new JLabel();
            try {
                Blob blob = sppn.getImage();
                byte[] blobBytes = blob.getBytes(1, (int) blob.length());
                ImageIcon icon = new ImageIcon(blobBytes);
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                lbAnh.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JPanel dImage = new JPanel();
            dImage.setBackground(Color.WHITE);
            dImage.setLayout(new BoxLayout(dImage, BoxLayout.Y_AXIS));
            dImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            dImage.add(lbAnh);
            panel.add(dImage, BorderLayout.WEST);
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BorderLayout());

            JPanel infoContainer = new JPanel();
            infoContainer.setPreferredSize(new Dimension(80, 80));
            infoContainer.setBackground(Color.WHITE);

            JLabel lblTen = new JLabel("<html>Tên: " + sppn.getName() + "</html>");
            JLabel lblGia = new JLabel(" Giá: $" + (spDto.getPrice() != null ? spDto.getPrice() : "Không có giá"), SwingConstants.LEFT);
            JLabel lblCon = new JLabel(" Còn: " + spDto.getQty(), SwingConstants.LEFT);
            lblTen.setFont(new Font("Arial", Font.BOLD, 14));
            lblGia.setFont(new Font("Arial", Font.BOLD, 14));
            lblCon.setFont(new Font("Arial", Font.BOLD, 14));
            JButton btnXem = new JButton("Xem");
            btnXem.setPreferredSize(new Dimension(10, 30));
            btnXem.setBackground(new Color(52, 152, 219)); // Màu xanh dương
            btnXem.setForeground(Color.BLACK); // Màu chữ trắng
            btnXem.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ
            btnXem.setFocusPainted(false); // Loại bỏ viền khi nhấn
            btnXem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Lề cho nút đẹp hơn
            btnXem.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(41, 128, 185), -1, true), // Viền bo góc
                    BorderFactory.createEmptyBorder(10, 20, 10, 20) // Lề bên trong
            ));
            btnXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Tạo panel thông tin chi tiết sản phẩm
                    ChiTietSanPham panelChiTiet = new ChiTietSanPham();
                    panelChiTiet.setSanPham(sppn, spDto);

                    // Tạo JDialog chứa panel
                    JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(jpnDssp), "Chi tiết sản phẩm", true);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.getContentPane().add(panelChiTiet);
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }
            });
            infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));
            infoContainer.add(Box.createVerticalStrut(10));
            infoContainer.add(lblTen);
            infoContainer.add(Box.createVerticalStrut(10));
            infoContainer.add(lblGia);
            infoContainer.add(Box.createVerticalStrut(10));
            infoContainer.add(lblCon);

// Căn giữa các nhãn thông tin
            infoContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

// Thêm infoContainer vào giữa
            infoPanel.add(infoContainer, BorderLayout.CENTER);

// Thêm btnXem xuống dưới cùng
            infoPanel.setBackground(Color.WHITE);
//            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
//            infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            infoPanel.add(btnXem, BorderLayout.SOUTH);
            panel.add(infoPanel, BorderLayout.CENTER);

            // --- Phần thao tác (ở dưới) ---
            JPanel actionPanel = new JPanel(new BorderLayout());
            actionPanel.setBackground(Color.green);
            JSpinner spnSoLuong = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            JButton btnThem = new JButton("Thêm");
            btnThem.setBackground(new Color(52, 152, 219)); // Màu xanh dương
            btnThem.setForeground(Color.BLACK); // Màu chữ trắng
            btnThem.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ
            btnThem.setFocusPainted(false); // Loại bỏ viền khi nhấn
            btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Lề cho nút đẹp hơn
            btnThem.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(41, 128, 185), -1, true), // Viền bo góc
                    BorderFactory.createEmptyBorder(10, 20, 10, 20) // Lề bên trong
            ));

            btnThem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int soLuong = (int) spnSoLuong.getValue();
                    if (soLuong <= 0 || soLuong > 100) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn số lượng hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (soLuong > spDto.getQty()) {
                        JOptionPane.showMessageDialog(null, "Số lượng vượt quá tồn kho!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    themVaoHoaDon(spDto, sppn, soLuong, Double.parseDouble(spDto.getPrice()));
                }
            });

            // Tạo một panel nhỏ để chứa spinner và nút "Thêm"
            JPanel tempPanel = new JPanel();
            tempPanel.setBackground(new Color(152, 251, 152));
            JLabel jlbsl = new JLabel("Số lượng:");
            jlbsl.setBackground(new Color(255, 255, 0));
            jlbsl.setFont(new Font("Arial", Font.BOLD, 14));

            tempPanel.add(jlbsl);
            tempPanel.add(spnSoLuong);
            tempPanel.add(btnThem, BorderLayout.EAST);
            actionPanel.add(tempPanel, BorderLayout.CENTER);
            panel.add(actionPanel, BorderLayout.SOUTH);

            // Thêm panel sản phẩm vào jpnDssp
            jpnDssp.add(panel);
        }
        jpnDssp.revalidate();
        jpnDssp.repaint();
    }

    private void themVaoHoaDon(SanPhamDTO ctpn, SanPhamDTO sp, int soLuong, Double donGia) {
        DefaultTableModel model = (DefaultTableModel) jtbCthd.getModel();
        boolean daTonTai = false;

        // ✅ Kiểm tra nếu sản phẩm đã tồn tại, chỉ cập nhật số lượng
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(ctpn.getId())) {
                int soLuongHienTai = (int) model.getValueAt(i, 3);
                int tongSoLuong = soLuongHienTai + soLuong;
                if (tongSoLuong > ctpn.getQty() || tongSoLuong > 100) {
                    JOptionPane.showMessageDialog(null, "Số lượng vượt quá tồn kho hoặc lớn hơn 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                model.setValueAt(soLuongHienTai + soLuong, i, 3);
                model.setValueAt((soLuongHienTai + soLuong) * donGia, i, 5);
                daTonTai = true;
                break;
            }
        }

        // ✅ Nếu sản phẩm chưa có, thêm mới vào bảng
        if (!daTonTai) {
            model.addRow(new Object[]{jtbCthd.getRowCount() + 1, ctpn.getId(), sp.getName(), soLuong, donGia, soLuong * donGia});
        }

        tinhTongHoaDon();
    }
    private DiscountBUS dcBUS= new DiscountBUS();
    private void tinhTongHoaDon() {
        double tongTien = 0;
        DiscountDTO gg = dcBUS.laytengg(txtDiscount.getText());
        DefaultTableModel model = (DefaultTableModel) jtbCthd.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            // Lấy giá trị từ cột thứ 4 (index 3) của dòng hiện tại
            double giaTri = Double.parseDouble(model.getValueAt(i, 5).toString());
            tongTien += giaTri;
        }
        double giamgia= Double.parseDouble(gg.getPercent()+"");
        tongTien = tongTien - tongTien*(giamgia/100);
        // Định dạng số với 3 chữ số sau dấu chấm
        DecimalFormat df = new DecimalFormat("#.###");

        // Cập nhật tổng tiền vào TextField
        txtTongTien.setText(df.format(tongTien));
    }

    private void clickChooseInCthd() {
        int row = jtbCthd.getSelectedRow();
        if (row != -1) {
            modelHoaDon.removeRow(row);
        }
    }

    private void refresh() {
        cauhinh("");
        txtTongTien.setText("");
        jtbCthd.removeAll();
    }
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Trang Chủ");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1125, 667);
//
//        trangchu panel = new trangchu();
//        frame.add(panel);  // Thêm panel vào frame
//
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnLeft = new javax.swing.JPanel();
        jpnProduct = new javax.swing.JPanel();
        jpnDssp = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimkiem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jpnHoadon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbCthd = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNgaytao = new javax.swing.JTextField();
        txtNguoitao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMahoadon = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        btnTt = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 255, 255));
        setMinimumSize(new java.awt.Dimension(1180, 750));
        setPreferredSize(new java.awt.Dimension(1125, 750));
        setLayout(new java.awt.BorderLayout());

        jpnLeft.setBackground(new java.awt.Color(255, 255, 255));
        jpnLeft.setMinimumSize(new java.awt.Dimension(745, 750));
        jpnLeft.setPreferredSize(new java.awt.Dimension(800, 750));
        jpnLeft.setLayout(new java.awt.BorderLayout());

        jpnProduct.setBackground(new java.awt.Color(255, 102, 204));
        jpnProduct.setPreferredSize(new java.awt.Dimension(800, 683));
        jpnProduct.setLayout(new java.awt.BorderLayout());

        jpnDssp.setBackground(new java.awt.Color(255, 255, 255));
        jpnDssp.setMinimumSize(new java.awt.Dimension(700, 700));
        jpnDssp.setPreferredSize(new java.awt.Dimension(800, 683));

        javax.swing.GroupLayout jpnDsspLayout = new javax.swing.GroupLayout(jpnDssp);
        jpnDssp.setLayout(jpnDsspLayout);
        jpnDsspLayout.setHorizontalGroup(
            jpnDsspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jpnDsspLayout.setVerticalGroup(
            jpnDsspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
        );

        jpnProduct.add(jpnDssp, java.awt.BorderLayout.CENTER);

        jpnLeft.add(jpnProduct, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimkiem.setBackground(new java.awt.Color(0, 204, 255));
        btnTimkiem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTimkiem.setText("Tìm kiếm");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 51, 51));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("BÁN HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimkiem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel1)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnLeft.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        add(jpnLeft, java.awt.BorderLayout.CENTER);

        jpnHoadon.setBackground(new java.awt.Color(255, 255, 255));
        jpnHoadon.setMinimumSize(new java.awt.Dimension(380, 750));
        jpnHoadon.setPreferredSize(new java.awt.Dimension(380, 750));
        jpnHoadon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtbCthd.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
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

        jpnHoadon.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 262, 370, 340));

        jLabel2.setBackground(new java.awt.Color(0, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("  Ngày tạo");
        jpnHoadon.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 368, 19));

        jLabel3.setBackground(new java.awt.Color(0, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("  Người tạo");
        jpnHoadon.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 66, 368, 20));
        jpnHoadon.add(txtNgaytao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 368, 35));

        txtNguoitao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNguoitaoActionPerformed(evt);
            }
        });
        jpnHoadon.add(txtNguoitao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 92, 368, 37));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("  Mã hóa đơn:  ");
        jpnHoadon.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 141, -1, 24));
        jpnHoadon.add(txtMahoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 142, 75, -1));

        btnXoa.setBackground(new java.awt.Color(255, 51, 51));
        btnXoa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnXoa.setMnemonic('x');
        btnXoa.setText("XÓA");
        jpnHoadon.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("  Tổng tiền:");
        jpnHoadon.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 103, -1));
        jpnHoadon.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 177, 222, 32));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Khuyến mãi:");
        jpnHoadon.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 221, 97, 23));
        jpnHoadon.add(txtDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 221, 222, 29));

        btnTt.setBackground(new java.awt.Color(51, 204, 0));
        btnTt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnTt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pay.png"))); // NOI18N
        btnTt.setText("THANH TOÁN");
        jpnHoadon.add(btnTt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 620, 160, -1));

        add(jpnHoadon, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNguoitaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNguoitaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNguoitaoActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    

    private ChiTietPhieuNhapBUS ctpnBUS = new ChiTietPhieuNhapBUS();
//    private javax.swing.JPanel jpnDssp;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnTt;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnDssp;
    private javax.swing.JPanel jpnHoadon;
    private javax.swing.JPanel jpnLeft;
    private javax.swing.JPanel jpnProduct;
    private javax.swing.JTable jtbCthd;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtMahoadon;
    private javax.swing.JTextField txtNgaytao;
    private javax.swing.JTextField txtNguoitao;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
