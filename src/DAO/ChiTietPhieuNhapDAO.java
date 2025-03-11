/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.ChiTietPhieuNhapBUS;
import DTO.ChiTietPhieuNhapDTO;
import Util.connectDB;
import static java.lang.Integer.parseInt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhapDAO {

//    private ArrayList<ChiTietPhieuNhapDTO> spInPn;
//    private ChiTietPhieuNhapDTO ctpnDTO = new ChiTietPhieuNhapDTO();
//
    // Cập nhật số lượng sản phẩm tồn kho
    public boolean capNhatSoLuong(DefaultTableModel model) {
        boolean kq = false;
        String sql = "UPDATE grndetails SET qtyExist = ? WHERE idgrndetails = ?";
        ChiTietPhieuNhapDAO ctpnDAO = new ChiTietPhieuNhapDAO();
        try (Connection conn = connectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < model.getRowCount(); i++) {
                try {
                    int idgrndetails = Integer.parseInt(model.getValueAt(i, 1).toString().trim());
                    ChiTietPhieuNhapDTO ctpnDTO1 = ctpnDAO.layCtpnId(idgrndetails);
                    int qty = Integer.parseInt(model.getValueAt(i, 3).toString().trim());
                    int qtyExist = ctpnDTO1.getQtyExist() - qty;

                    stmt.setInt(1, qtyExist);  // ✅ Đặt đúng vị trí
                    stmt.setInt(2, idgrndetails);
                    stmt.addBatch();
                    kq = true;
                } catch (NumberFormatException e) {
                    System.err.println(" Dữ liệu không hợp lệ ở hàng " + i + ": " + e.getMessage());
                }
            }
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ChiTietPhieuNhapDTO layCtpnId(int id) {
        ChiTietPhieuNhapDTO ctpnDTO = null;
        String sql = "SELECT * FROM grndetails WHERE idgrndetails = ?";

        Connection conn = null;
        PreparedStatement stmtCtpn = null;
        ResultSet rs = null;

        try {
            conn = connectDB.getConnection();
            stmtCtpn = conn.prepareStatement(sql);
            stmtCtpn.setInt(1, id);
            rs = stmtCtpn.executeQuery();

            if (rs.next()) {
                ctpnDTO = new ChiTietPhieuNhapDTO(
                        rs.getInt("idgrndetails"),
                        rs.getInt("idGRN"),
                        rs.getInt("idProduct"),
                        rs.getInt("quantity"),
                        rs.getString("iPrice"),
                        rs.getString("interestRate"),
                        rs.getString("ePrice"),
                        rs.getInt("qtyExist")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmtCtpn != null) {
                    stmtCtpn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ctpnDTO;
    }

    // Thêm chi tiết phiếu nhập
    public boolean themChiTietPhieuNhap(DefaultTableModel model, int idPn) {
        boolean kq = false;
        String sql = "INSERT INTO grndetails (idGRN, idProduct, quantity, iPrice, interestRate, ePrice, qtyExist) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < model.getRowCount(); i++) {
                try {
                    int idProduct = Integer.parseInt(model.getValueAt(i, 1).toString().trim());
                    int quantity = Integer.parseInt(model.getValueAt(i, 6).toString().trim());
                    String iPrice = model.getValueAt(i, 3).toString().trim();
                    float interestRate = Float.parseFloat(model.getValueAt(i, 4).toString().trim());
                    String ePrice = model.getValueAt(i, 5).toString().trim();

                    stmt.setInt(1, idPn);
                    stmt.setInt(2, idProduct);
                    stmt.setInt(3, quantity);
                    stmt.setString(4, iPrice);
                    stmt.setFloat(5, interestRate);
                    stmt.setString(6, ePrice);
                    stmt.setInt(7, quantity);
                    stmt.addBatch();
                } catch (NumberFormatException e) {
                    System.err.println("⚠ Dữ liệu không hợp lệ ở hàng " + i + ": " + e.getMessage());
                }
            }
            stmt.executeBatch();
            kq = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    // Lấy danh sách sản phẩm có trong phiếu nhập

    public ArrayList<ChiTietPhieuNhapDTO> laySpInPn(String tuKhoa) {
        ArrayList<ChiTietPhieuNhapDTO> spInPn = new ArrayList<>();

        // Tách từ khóa thành các phần: tên - mô tả - giá
        String[] parts = tuKhoa.split("-");
        String tenSp = (parts.length > 0) ? parts[0].trim() : "";
        String moTa = (parts.length > 1) ? parts[1].trim() : "";
        String gia = (parts.length > 2) ? parts[2].trim() : "";

        // Xây dựng câu truy vấn SQL động
        String sql = """
    WITH ranked AS (
        SELECT gd.*, ROW_NUMBER() OVER (PARTITION BY gd.idProduct ORDER BY gd.idGRN ASC) AS rn
        FROM grndetails gd
        JOIN product p ON gd.idProduct = p.idproduct
        JOIN goodsreceiptnote grn ON gd.idGRN = grn.idGRN
        WHERE gd.qtyExist > 0 AND grn.deleted = 0
    """;

        List<String> conditions = new ArrayList<>();
        if (!tenSp.isEmpty()) {
            conditions.add("p.nameProduct LIKE ?");
        }
        if (!moTa.isEmpty()) {
            conditions.add("p.description LIKE ?");
        }
        if (!gia.isEmpty()) {
            conditions.add("gd.ePrice = ?");
        }

        if (!conditions.isEmpty()) {
            sql += " AND " + String.join(" AND ", conditions);
        }

        sql += " ) SELECT * FROM ranked WHERE rn = 1";

        try (Connection conn = connectDB.getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (!tenSp.isEmpty()) {
                pre.setString(paramIndex++, "%" + tenSp + "%");
            }
            if (!moTa.isEmpty()) {
                pre.setString(paramIndex++, "%" + moTa + "%");
            }
            if (!gia.isEmpty()) {
                pre.setString(paramIndex++, gia);
            }

            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    ChiTietPhieuNhapDTO spInCtpn = new ChiTietPhieuNhapDTO(
                            rs.getInt("idgrndetails"),
                            rs.getInt("idGRN"),
                            rs.getInt("idProduct"),
                            rs.getInt("quantity"),
                            rs.getString("iPrice"),
                            rs.getString("interestRate"),
                            rs.getString("ePrice"),
                            rs.getInt("qtyExist")
                    );
                    spInPn.add(spInCtpn);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy sản phẩm trong chi tiết phiếu nhập: " + e.getMessage());
        }
        return spInPn;
    }

}
