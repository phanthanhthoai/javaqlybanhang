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
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jpnDspn = new javax.swing.JPanel();
        jlbDspn = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbSp = new javax.swing.JTable();
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
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNtp = new javax.swing.JTextField();
        cbbNcc = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdpn = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbPhieunhap = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTongtien = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnNhaphang = new javax.swing.JButton();
        btnXuatexcel = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1125, 667));
        setLayout(new java.awt.BorderLayout());

        jpnChua.setPreferredSize(new java.awt.Dimension(1125, 667));
        jpnChua.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jpnDspn.setBackground(new java.awt.Color(255, 102, 102));
        jpnDspn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbDspn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlbDspn.setText("XEM DANH SÁCH PHIẾU NHẬP");
        jpnDspn.add(jlbDspn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        btnSearch.setBackground(new java.awt.Color(0, 255, 255));
        btnSearch.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search2.png"))); // NOI18N
        btnSearch.setMnemonic('t');
        btnSearch.setText("Tìm kiếm:");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jpnDspn, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(347, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch)
                        .addGap(6, 6, 6))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jpnDspn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setLayout(new java.awt.BorderLayout());

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

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 153, 0)));

        jLabel7.setText("ID Sản phẩm:");

        jLabel8.setText("Tên sản phẩm:");

        jLabel9.setText("Giá nhập:");

        jLabel11.setText("%Lãi suất:");

        jLabel14.setText("Số lượng:");

        btnThemsp.setBackground(new java.awt.Color(0, 255, 0));
        btnThemsp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnThemsp.setMnemonic('t');
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

        jPanel3.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jpnChua.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(450, 632));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Người tạo phiếu:");

        txtNtp.setEditable(false);

        cbbNcc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Nhà cung cấp:");

        jLabel2.setText("Mã Phiếu nhập:");

        txtIdpn.setEditable(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbbNcc, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNtp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdpn, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdpn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNtp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(255, 0, 204));
        jPanel9.setLayout(new java.awt.BorderLayout());

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

        jPanel9.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel9, java.awt.BorderLayout.CENTER);

        jLabel5.setText("Tổng tiền:");

        txtTongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongtienActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnDelete.setMnemonic('x');
        btnDelete.setText("Xóa");

        jLabel6.setText("Đ");

        btnNhaphang.setBackground(new java.awt.Color(255, 204, 0));
        btnNhaphang.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNhaphang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/nhaphang.png"))); // NOI18N
        btnNhaphang.setMnemonic('n');
        btnNhaphang.setText("Nhập hàng");
        btnNhaphang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaphangActionPerformed(evt);
            }
        });

        btnXuatexcel.setBackground(new java.awt.Color(0, 204, 51));
        btnXuatexcel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXuatexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnXuatexcel.setMnemonic('e');
        btnXuatexcel.setText("Xuất excel");
        btnXuatexcel.setToolTipText("");
        btnXuatexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatexcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNhaphang)
                    .addComponent(btnXuatexcel))
                .addGap(43, 43, 43))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnNhaphang))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnXuatexcel))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel10, java.awt.BorderLayout.SOUTH);

        jpnChua.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ PHIẾU NHẬP");
        jLabel1.setPreferredSize(new java.awt.Dimension(260, 35));
        jPanel5.add(jLabel1, java.awt.BorderLayout.CENTER);

        jpnChua.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        add(jpnChua, java.awt.BorderLayout.CENTER);
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
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
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
