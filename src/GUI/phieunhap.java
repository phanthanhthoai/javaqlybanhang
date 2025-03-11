/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.ChiTietPhieuNhapBUS;
import BUS.PhieuNhapBUS;
import DAO.NhaCungCapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import Util.ExcelExporter;
import Util.dialog;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Integer.parseInt;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import GUI.chuyenmanhinhController;
import GUI.sidebar;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class phieunhap extends javax.swing.JPanel {

    /**
     * Creates new form phieunhap
     */
    public phieunhap() {

        initComponents();
        taoBangPhieuNhap();
        displayProduct();
        NhanVienDTO nv = loggedIn.getCurrentUser();
        txtNtp.setText(nv.getTenNV());
        phieuNhapCuoi();
        loadNhaCungCap();
        cacChucNang();

        clickChooseTblPhieuNhap();
    }
    private JTable table;

    private void cacChucNang() {
        jtbSp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                clickProductInTbl();

            }
        });
        jtbPhieunhap.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        jtbSp, // Đúng component
                        "Bạn có muốn thay đổi sản phẩm?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    // Dừng chỉnh sửa nếu đang có ô được chỉnh sửa  
                    if (jtbSp.isEditing()) {
                        jtbSp.getCellEditor().stopCellEditing();
                    }
                    clickChooseTblPhieuNhap();
                    tinhTongHoaDon();
                }
            }
        });

        DanhMucBean dmb = new DanhMucBean("danhsachphieunhap", jpnDspn, jlbDspn);
        chuyenmanhinhController controller = new chuyenmanhinhController(jpnChua);
        controller.setPanel(dmb);

        btnThemsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (validateAddPr()) {
                    xuliThemSpVaoPn();
                }
                tinhTongHoaDon();
            }
        });
        btnXuatexcel.addActionListener(e -> ExcelExporter.exportToExcel(jtbPhieunhap));
    }

    private PhieuNhapBUS pnBUS = new PhieuNhapBUS();

    private void displayProduct() {
        pnBUS.layDsSp(jtbSp, "");
    }

    private void phieuNhapCuoi() {
        PhieuNhapDTO pnc = pnBUS.layPncuoi();
        if (pnc != null) {
            txtIdpn.setText(String.valueOf(pnc.getId() + 1));
        } else {
            System.out.println("Không tìm thấy phiếu nhập cuối.");
            txtIdpn.setText("1");
        }
    }
    NhaCungCapDAO nccDAO = new NhaCungCapDAO();
    private Map<String, NhaCungCapDTO> nccMap = new HashMap<>();

    private void loadNhaCungCap() {
        List<NhaCungCapDTO> listNcc = nccDAO.layDsNcc("");
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel();
        for (NhaCungCapDTO ncc : listNcc) {
            model.addElement(ncc.getNameNcc());
            nccMap.put(ncc.getNameNcc(), ncc);
        }
        cbbNcc.setModel(model);
        jtbPhieunhap.setName("PhieuNhap");
    }
    private int nccId = -1;

    private void onNccSelected() {
        String selectedName = (String) cbbNcc.getSelectedItem();
        if (selectedName != null && nccMap.containsKey(selectedName)) {
            NhaCungCapDTO selectedNCC = nccMap.get(selectedName);
            nccId = selectedNCC.getId();
        }
    }

    private int rowSp = -1;

    private void clickProductInTbl() {
        rowSp = jtbSp.getSelectedRow();
        if (rowSp != -1) {
            txtIdsp.setText(jtbSp.getValueAt(rowSp, 0) + "");
            txtNamesp.setText(jtbSp.getValueAt(rowSp, 1) + "");
        }
    }

    private void tinhTongHoaDon() {
        double tongTien = 0;
        DefaultTableModel model = (DefaultTableModel) jtbPhieunhap.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            double giaTri = Double.parseDouble(model.getValueAt(i, 3).toString()) * Double.parseDouble(model.getValueAt(i, 6).toString());
            tongTien += giaTri;
        }

        // Định dạng số với 3 chữ số sau dấu chấm
        DecimalFormat df = new DecimalFormat("#.###");

        // Cập nhật tổng tiền vào TextField
        txtTongtien.setText(df.format(tongTien));
    }

    private boolean validateAddPr() {
        if (txtIdsp.getText() == "" || txtIdsp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Kt giá nhập
        String priceInput = txtIprice.getText().trim();
        if (priceInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!priceInput.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(null, "Giá nhập phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        double price = Double.parseDouble(priceInput); // Chuyển thành số thực
        if (price <= 0) {
            JOptionPane.showMessageDialog(null, "Giá nhập phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Kt lãi suất
        String rateInput = txtRate.getText().trim();
        if (rateInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập lãi suất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!rateInput.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(null, "Lãi suất phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        double rate = Double.parseDouble(rateInput); // Chuyển thành số thực
        if (rate <= 0) {
            JOptionPane.showMessageDialog(null, "Lãi suất phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Kt số lượng
        String quantityInput = txtQuantity.getText().trim();
        if (quantityInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!quantityInput.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int quantity = Integer.parseInt(quantityInput); // Chuyển thành số nguyên
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    private DefaultTableModel tableModelPn;

    private void taoBangPhieuNhap() {
        tableModelPn = new DefaultTableModel();
        tableModelPn.setColumnIdentifiers(new String[]{
            "STT", "ID SP", "Tên SP", "Giá nhập", "Lãi suất(%)", "Giá bán", "Số lượng"
        });
        jtbPhieunhap.setModel(tableModelPn);
    }

    private void xuliThemSpVaoPn() {

        String id = txtIdsp.getText().trim();
        String name = txtNamesp.getText().trim();
        String priceInput = txtIprice.getText().trim();
        String rateInput = txtRate.getText().trim();
        String quantityInput = txtQuantity.getText().trim();
        double Iprice = Double.parseDouble(priceInput);
        double rate = Double.parseDouble(rateInput);
        double Eprice = (1 + rate / 100) * Iprice;
        int quantity = Integer.parseInt(quantityInput);
        int stt = jtbPhieunhap.getRowCount() + 1;
        // Thêm dữ liệu vào mô hình bảng
        tableModelPn.addRow(new Object[]{stt, id, name, Iprice, rate, Eprice, quantity});
        refresh();
    }

    private void refresh() {
        txtIdsp.setText("");
        txtNamesp.setText("");
        txtIprice.setText("");
        txtRate.setText("");
        txtQuantity.setText("");
    }
    private int rowPn = -1;

    private void clickChooseTblPhieuNhap() {
        rowPn = jtbPhieunhap.getSelectedRow();
        if (rowPn != -1) {
            txtIdsp.setText(jtbPhieunhap.getValueAt(rowPn, 1) + "");
            txtNamesp.setText(jtbPhieunhap.getValueAt(rowPn, 2) + "");
            txtIprice.setText(jtbPhieunhap.getValueAt(rowPn, 3) + "");
            txtRate.setText(jtbPhieunhap.getValueAt(rowPn, 4) + "");
            txtQuantity.setText(jtbPhieunhap.getValueAt(rowPn, 6) + "");
            tableModelPn.removeRow(rowPn);
        }
        System.out.println(rowPn);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnChua = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnThemsp = new javax.swing.JButton();
        txtIdsp = new javax.swing.JTextField();
        txtIprice = new javax.swing.JTextField();
        txtRate = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtNamesp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbSp = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jpnDspn = new javax.swing.JPanel();
        jlbDspn = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdpn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbbNcc = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNtp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbPhieunhap = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTongtien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnNhaphang = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnXuatexcel = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1125, 667));

        jpnChua.setPreferredSize(new java.awt.Dimension(1125, 667));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 153, 0)));

        jLabel7.setText("ID Sản phẩm:");

        jLabel8.setText("Tên sản phẩm:");

        jLabel9.setText("Giá nhập:");

        jLabel11.setText("%Lãi suất:");

        jLabel14.setText("Số lượng:");

        btnThemsp.setBackground(new java.awt.Color(0, 255, 0));
        btnThemsp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemsp.setText("Thêm");
        btnThemsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemspActionPerformed(evt);
            }
        });

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIprice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRate, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemsp))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdsp, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamesp)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtIdsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamesp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14)
                    .addComponent(txtRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtIprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemsp))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jtbSp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbSp);

        btnSearch.setText("Tìm kiếm:");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jpnDspn.setBackground(new java.awt.Color(255, 102, 102));

        jlbDspn.setText("Danh sách phiếu nhập ");

        javax.swing.GroupLayout jpnDspnLayout = new javax.swing.GroupLayout(jpnDspn);
        jpnDspn.setLayout(jpnDspnLayout);
        jpnDspnLayout.setHorizontalGroup(
            jpnDspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDspnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbDspn, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
        );
        jpnDspnLayout.setVerticalGroup(
            jpnDspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbDspn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel2.setText("Mã Phiếu nhập:");

        txtIdpn.setEditable(false);

        jLabel3.setText("Nhà cung cấp:");

        cbbNcc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Người tạo phiếu:");

        txtNtp.setEditable(false);

        jtbPhieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Giá nhập", "% Lãi suất", "Giá bán"
            }
        ));
        jScrollPane2.setViewportView(jtbPhieunhap);

        jLabel5.setText("Tổng tiền:");

        txtTongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongtienActionPerformed(evt);
            }
        });

        jLabel6.setText("Đ");

        btnNhaphang.setText("Nhập hàng");
        btnNhaphang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaphangActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");

        btnXuatexcel.setText("Xuất excel");
        btnXuatexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatexcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnChuaLayout = new javax.swing.GroupLayout(jpnChua);
        jpnChua.setLayout(jpnChuaLayout);
        jpnChuaLayout.setHorizontalGroup(
            jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnChuaLayout.createSequentialGroup()
                .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnChuaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpnDspn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpnChuaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpnChuaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnChuaLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnChuaLayout.createSequentialGroup()
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnChuaLayout.createSequentialGroup()
                                .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(23, 23, 23)
                                .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNtp, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdpn, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(jpnChuaLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDelete)
                            .addGroup(jpnChuaLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNhaphang)
                            .addComponent(btnXuatexcel))
                        .addGap(79, 79, 79))))
        );
        jpnChuaLayout.setVerticalGroup(
            jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnChuaLayout.createSequentialGroup()
                .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnChuaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpnDspn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnChuaLayout.createSequentialGroup()
                        .addContainerGap(25, Short.MAX_VALUE)
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdpn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnChuaLayout.createSequentialGroup()
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbbNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnChuaLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(jpnChuaLayout.createSequentialGroup()
                                .addComponent(txtNtp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(411, 411, 411)))
                        .addGap(10, 10, 10)
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(btnNhaphang))
                        .addGap(18, 18, 18)
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnXuatexcel)))
                    .addGroup(jpnChuaLayout.createSequentialGroup()
                        .addGroup(jpnChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnChua, javax.swing.GroupLayout.PREFERRED_SIZE, 1186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnChua, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private ChiTietPhieuNhapBUS ctpnBUS = new ChiTietPhieuNhapBUS();
    private void btnNhaphangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaphangActionPerformed
        // TODO add your handling code here:
        onNccSelected();
        int idPn = parseInt(txtIdpn.getText());
        String tMoney = txtTongtien.getText();
        if (pnBUS.themPn(nccId, idPn, loggedIn.getCurrentUser().getMaNV(), tMoney) && ctpnBUS.themCtpn(jtbPhieunhap, idPn)) {
            JOptionPane.showMessageDialog(null, "oke", "Success", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnNhaphangActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtTongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongtienActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void btnXuatexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatexcelActionPerformed


    }//GEN-LAST:event_btnXuatexcelActionPerformed

    private void btnThemspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemspActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNhaphang;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThemsp;
    private javax.swing.JButton btnXuatexcel;
    private javax.swing.JComboBox<String> cbbNcc;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlbDspn;
    private javax.swing.JPanel jpnChua;
    private javax.swing.JPanel jpnDspn;
    private javax.swing.JTable jtbPhieunhap;
    private javax.swing.JTable jtbSp;
    private javax.swing.JTextField txtIdpn;
    private javax.swing.JTextField txtIdsp;
    private javax.swing.JTextField txtIprice;
    private javax.swing.JTextField txtNamesp;
    private javax.swing.JTextField txtNtp;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtRate;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTongtien;
    // End of variables declaration//GEN-END:variables
}
