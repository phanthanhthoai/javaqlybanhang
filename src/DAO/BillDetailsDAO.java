/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.ChiTietPhieuNhapBUS;
import DTO.ChiTietPhieuNhapDTO;
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

    private ChiTietPhieuNhapBUS ctpnBUS = new ChiTietPhieuNhapBUS();
    
    
    
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
                int idCtpn = Integer.parseInt(model.getValueAt(i, 1).toString().trim());
            ChiTietPhieuNhapDTO ctpnDTO = ctpnBUS.layCtpnId(idCtpn);

                if (ctpnDTO == null) {
                    System.err.println("Không tìm thấy ChiTietPhieuNhapDTO với idGrnDetails: " + idCtpn);
                    continue;
                }

            int idProduct = ctpnDTO.getIdProduct();

                int quantity = Integer.parseInt(model.getValueAt(i, 3).toString().trim());
                BigDecimal price = new BigDecimal(model.getValueAt(i, 4).toString().trim());
                BigDecimal totalPrice = new BigDecimal(model.getValueAt(i, 5).toString().trim());

                stmt.setInt(1, idBill);
                stmt.setInt(2, idProduct);
                stmt.setBigDecimal(3, price);
                stmt.setInt(4, quantity);
                stmt.setBigDecimal(5, totalPrice);
                stmt.setInt(6, idCtpn);

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
