/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.imageio.ImageIO;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ProductDisplay extends javax.swing.JPanel {

    /**
     * Creates new form ProductDisplay
     */
//    public ProductDisplay() {
//        initComponents();
//    }
    private DefaultTableModel tableModel;
    private JLabel totalAmountLabel;

    public ProductDisplay() throws SQLException, IOException {
        setLayout(new BorderLayout());

        // Panel sản phẩm
        JPanel productPanel = new JPanel(new GridLayout(0, 3, 10, 10));

        // Lấy danh sách sản phẩm từ database
        SanPhamDAO productDAO = new SanPhamDAO();
        List<SanPhamDTO> products = productDAO.layDsSp("");

        for (SanPhamDTO product : products) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Hình ảnh sản phẩm
            JLabel imageLabel;
            if (product.getImage() != null) {
                byte[] imageBytes = product.getImage().getBytes(1, (int) product.getImage().length());
                ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                BufferedImage bufferedImage = ImageIO.read(bais);

                Image scaledImage = bufferedImage.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(scaledImage);

                imageLabel = new JLabel(imageIcon);
            } else {
                imageLabel = new JLabel("No Image");
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            }

            // Tên sản phẩm
            JLabel nameLabel = new JLabel(product.getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Giá sản phẩm
            JLabel priceLabel = new JLabel("$" + product.getPrice());
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Bộ chọn số lượng
            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            quantitySpinner.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Nút "Add"
            JButton addButton = new JButton("Add");
            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            addButton.addActionListener(e -> {
                int quantity = (int) quantitySpinner.getValue();
                if (quantity > 0) {
                    addToBill(product.getName(), quantity, 5);
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a valid quantity!");
                }
            });

            // Thêm các thành phần vào itemPanel
            itemPanel.add(Box.createVerticalStrut(10));
            itemPanel.add(imageLabel);
            itemPanel.add(Box.createVerticalStrut(10));
            itemPanel.add(nameLabel);
            itemPanel.add(Box.createVerticalStrut(5));
            itemPanel.add(priceLabel);
            itemPanel.add(Box.createVerticalStrut(5));
            itemPanel.add(quantitySpinner);
            itemPanel.add(Box.createVerticalStrut(5));
            itemPanel.add(addButton);

            productPanel.add(itemPanel);
        }

        // Cuộn cho danh sách sản phẩm
        JScrollPane productScrollPane = new JScrollPane(productPanel);

        // Bảng hóa đơn
        String[] columnNames = {"Product Name", "Quantity", "Price ($)", "Total ($)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable billTable = new JTable(tableModel);

        JScrollPane billScrollPane = new JScrollPane(billTable);
        billScrollPane.setPreferredSize(new Dimension(400, 300));

        // Tổng tiền
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalAmountLabel = new JLabel("Total: $0.0");
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(totalAmountLabel);

        // Panel hóa đơn
        JPanel billPanel = new JPanel(new BorderLayout());
        billPanel.add(billScrollPane, BorderLayout.CENTER);
        billPanel.add(totalPanel, BorderLayout.SOUTH);

        // Thêm productPanel và billPanel vào giao diện
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, productScrollPane, billPanel);
        splitPane.setDividerLocation(800);

        add(splitPane, BorderLayout.CENTER);
    }

    private void addToBill(String productName, int quantity, double price) {
        // Kiểm tra sản phẩm đã tồn tại trong hóa đơn chưa
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(productName)) {
                int existingQuantity = (int) tableModel.getValueAt(i, 1);
                tableModel.setValueAt(existingQuantity + quantity, i, 1);
                tableModel.setValueAt((existingQuantity + quantity) * price, i, 3);
                updateTotalAmount();
                return;
            }
        }

        // Thêm sản phẩm mới vào hóa đơn
        double total = quantity * price;
        tableModel.addRow(new Object[]{productName, quantity, price, total});
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        double totalAmount = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            totalAmount += (double) tableModel.getValueAt(i, 3);
        }
        totalAmountLabel.setText("Total: $" + totalAmount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Product Display with Bill");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            try {
                frame.add(new ProductDisplay());
            } catch (SQLException ex) {
                Logger.getLogger(ProductDisplay.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) { 
                Logger.getLogger(ProductDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }

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
