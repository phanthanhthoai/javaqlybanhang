package DAO;

import Util.connectDB;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class BillDetailsDAO {

    public boolean themChiTietHoaDon(DefaultTableModel model, int idBill) {
        boolean kq = false;
        String sql = "INSERT INTO billdetails (idBill, idProduct, price, quantity, totalPay, idGrnDetails) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connectDB.getConnection();
            stmt = conn.prepareStatement(sql);

            int count = 0; // Đếm số dòng hợp lệ để insert

            for (int i = 0; i < model.getRowCount(); i++) {
                int idGrnDetails = Integer.parseInt(model.getValueAt(i, 1).toString().trim()); // Cột idGrnDetails
                int idProduct = Integer.parseInt(model.getValueAt(i, 2).toString().trim()); // Cột idProduct
                int quantity = Integer.parseInt(model.getValueAt(i, 3).toString().trim()); // Cột Quantity
                BigDecimal price = new BigDecimal(model.getValueAt(i, 4).toString().trim()); // Cột Price
                BigDecimal totalPrice = new BigDecimal(model.getValueAt(i, 5).toString().trim()); // Cột Total

                stmt.setInt(1, idBill);
                stmt.setInt(2, idProduct);
                stmt.setString(3, price.toString());
                stmt.setInt(4, quantity);
                stmt.setString(5, totalPrice.toString());
                stmt.setInt(6, idGrnDetails);

                stmt.addBatch();
                count++;
            }

            if (count > 0) {
                stmt.executeBatch();
                kq = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return kq;
    }
}