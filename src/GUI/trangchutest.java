/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Admin
 */
class Product {
    String name;
    double price;
    int stock;
    ImageIcon image;

    public Product(String name, double price, int stock, String imagePath) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.image = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH));
    }
}
public class trangchutest extends javax.swing.JPanel {
    private JTextField searchField, txtNguoiLap, txtNgayLap, txtTongTien;
    private JTable hoaDonTable;
    private JPanel productPanel;
    private List<Product> products;
    private DefaultTableModel tableModel;

    public trangchutest() {
        setLayout(new BorderLayout());

        // 🔹 Thanh tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("🔍 Tìm kiếm");
        searchPanel.add(new JLabel("🔎 Tìm sản phẩm:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // 🔹 Danh sách sản phẩm
        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 3, 10, 10)); // 3 cột mỗi hàng
        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // 🔹 Khu vực hóa đơn bên phải
        JPanel hoaDonPanel = new JPanel();
        hoaDonPanel.setLayout(new BoxLayout(hoaDonPanel, BoxLayout.Y_AXIS));
        hoaDonPanel.setPreferredSize(new Dimension(300, 0));
        hoaDonPanel.setBackground(Color.LIGHT_GRAY);

        txtNguoiLap = new JTextField("Phan Thanh Thoai", 15);
        txtNgayLap = new JTextField("2025-02-19", 15);
        txtTongTien = new JTextField("0.0", 10);

        hoaDonPanel.add(new JLabel("📝 Người lập hóa đơn:"));
        hoaDonPanel.add(txtNguoiLap);
        hoaDonPanel.add(new JLabel("📅 Ngày lập:"));
        hoaDonPanel.add(txtNgayLap);

        // 🔹 Danh sách hóa đơn
        String[] columnNames = {"STT", "Tên SP", "SL", "Đơn giá", "Thành tiền"};
        tableModel = new DefaultTableModel(columnNames, 0);
        hoaDonTable = new JTable(tableModel);
        JScrollPane hoaDonScroll = new JScrollPane(hoaDonTable);
        hoaDonPanel.add(new JLabel("📋 Danh sách hóa đơn:"));
        hoaDonPanel.add(hoaDonScroll);

        // 🔹 Tổng tiền + nút thanh toán
        hoaDonPanel.add(new JLabel("💰 Tổng tiền:"));
        hoaDonPanel.add(txtTongTien);
        JButton btnThanhToan = new JButton("💳 Thanh toán");
        hoaDonPanel.add(btnThanhToan);

        add(hoaDonPanel, BorderLayout.EAST);

        // 🔹 Tạo danh sách sản phẩm mẫu
        products = new ArrayList<>();
        products.add(new Product("Cold Coffee", 3.0, 2, "cold_coffee.png"));
        products.add(new Product("Cappuccino", 5.0, 5, "cappuccino.png"));
        products.add(new Product("Chocolate Cake", 8.0, 3, "choco_cake.png"));
        products.add(new Product("Green Tea", 2.0, 10, "green_tea.png"));
        products.add(new Product("Pizza", 7.5, 4, "pizza.png"));
        products.add(new Product("Chicken Burger", 2.5, 6, "burger.png"));

        // Hiển thị sản phẩm ban đầu
        loadProducts("");

        // 🔹 Xử lý tìm kiếm
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();
            loadProducts(keyword);
        });

        // 🔹 Xử lý thanh toán
        btnThanhToan.addActionListener(e -> JOptionPane.showMessageDialog(null, "Thanh toán thành công!"));
    }

    private void loadProducts(String keyword) {
        productPanel.removeAll();

        for (Product product : products) {
            if (product.name.toLowerCase().contains(keyword)) {
                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                itemPanel.setPreferredSize(new Dimension(220, 250));

                JLabel imageLabel = new JLabel(product.image);
                JLabel nameLabel = new JLabel("<html><b>" + product.name + "</b></html>", SwingConstants.CENTER);
                JLabel priceLabel = new JLabel("Giá: $" + product.price, SwingConstants.CENTER);
                JLabel stockLabel = new JLabel("Còn: " + product.stock, SwingConstants.CENTER);

                JButton viewButton = new JButton("👁 Xem");
                JButton addButton = new JButton("➕ Thêm");

                // 🔹 Thêm sản phẩm vào hóa đơn
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addToBill(product);
                    }
                });

                JPanel bottomPanel = new JPanel();
                bottomPanel.add(viewButton);
                bottomPanel.add(addButton);

                itemPanel.add(imageLabel, BorderLayout.NORTH);
                itemPanel.add(nameLabel, BorderLayout.CENTER);
                itemPanel.add(priceLabel, BorderLayout.SOUTH);
                itemPanel.add(stockLabel, BorderLayout.LINE_END);
                itemPanel.add(bottomPanel, BorderLayout.PAGE_END);

                productPanel.add(itemPanel);
            }
        }

        productPanel.revalidate();
        productPanel.repaint();
    }

    private void addToBill(Product product) {
        int quantity = 1;
        double totalPrice = quantity * product.price;

        Object[] rowData = {tableModel.getRowCount() + 1, product.name, quantity, product.price, totalPrice};
        tableModel.addRow(rowData);

        // Cập nhật tổng tiền
        double currentTotal = Double.parseDouble(txtTongTien.getText());
        txtTongTien.setText(String.valueOf(currentTotal + totalPrice));
    }

    // 🔹 Test giao diện
    public static void main(String[] args) {
        JFrame frame = new JFrame("Giao diện Bán Hàng - Trang Chủ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setContentPane(new trangchutest());
        frame.setVisible(true);
    }
    /**
     * Creates new form trangchutest
     */
//    public trangchutest() {
//        initComponents();
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
