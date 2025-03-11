/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import Util.connectDB;
import com.mysql.cj.jdbc.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class PhieuNhapDAO {
    public boolean xacNhan(int id){
        boolean kq= false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "update goodsreceiptnote set deleted=0 where idGRN=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }
    
    public ArrayList<PhieuNhapDTO> layDsPn(String nBd, String nKt) {
        ArrayList<PhieuNhapDTO> listPN = new ArrayList<>();
        String sql = "SELECT * FROM goodsreceiptnote";

        if (!nBd.isEmpty() && !nKt.isEmpty()) {
            sql += " WHERE STR_TO_DATE(dayReceive, '%Y-%m-%d') BETWEEN STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d')";
        }

        sql += " ORDER BY STR_TO_DATE(dayReceive, '%Y-%m-%d') ASC"; // Sắp xếp theo ngày tăng dần

        try (Connection conn = connectDB.getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {

            if (!nBd.isEmpty() && !nKt.isEmpty()) {
                pre.setString(1, nBd);
                pre.setString(2, nKt);
            }

            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    PhieuNhapDTO pn = new PhieuNhapDTO(
                            rs.getInt("idGRN"),
                            rs.getInt("idSupplier"),
                            rs.getString("dayReceive"), // Giữ nguyên dưới dạng chuỗi
                            rs.getInt("deleted"),
                            rs.getInt("idUserImport"),
                            rs.getString("totalMoney")
                    );
                    listPN.add(pn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listPN;
    }

    public ArrayList<ChiTietPhieuNhapDTO> layDsPhieuNhapId(int id) {
        ArrayList<ChiTietPhieuNhapDTO> listCtpnId = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;

        try {
            conn = connectDB.getConnection(); // Kết nối CSDL
            String sql = "SELECT * FROM grndetails WHERE idGRN = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();

            while (rs.next()) { // Lặp qua tất cả kết quả
                ChiTietPhieuNhapDTO pn = new ChiTietPhieuNhapDTO(
                        rs.getInt("idgrndetails"),
                        rs.getInt("idGRN"),
                        rs.getInt("idProduct"),
                        rs.getInt("quantity"),
                        rs.getString("iPrice"),
                        rs.getString("interestRate"),
                        rs.getString("ePrice"),
                        rs.getInt("qtyExist")
                );
                listCtpnId.add(pn);
            }

            // Kiểm tra nếu danh sách vẫn rỗng
            if (listCtpnId.isEmpty()) {
                System.out.println("Không tìm thấy dữ liệu cho idGRN = " + id);
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return listCtpnId;
    }

    public ArrayList<SanPhamDTO> layDsSp(String search) {
        try {
            Connection conn = connectDB.getConnection();
            String sql = "";
            if (search == null || search.isEmpty()) {
                sql = "select * from product where deleted=0";
            } else {
                sql = "select * from product where (idproduct like '%" + search + "%' or nameProduct like '%" + search + "%' or description like '%" + search + "%')and deleted=0";

            }
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPhamDTO> listSp = new ArrayList<>();
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO(
                        rs.getInt("idproduct"),
                        rs.getString("nameProduct"),
                        rs.getString("category"),
                        rs.getString("description"),
                        (Blob) rs.getBlob("image"),
                        rs.getInt("deleted"));
                listSp.add(sp);
            }
            return listSp;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return null;
    }

    public PhieuNhapDTO layPncuoi() {
        PhieuNhapDTO pn = null;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from goodsreceiptnote order by idGRN desc limit 1";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                pn = new PhieuNhapDTO(rs.getInt("idGRN"), rs.getInt("idSupplier"), rs.getString("dayReceive"), 0, rs.getInt("idUserImport"));
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return pn;
    }

    public boolean themPn(PhieuNhapDTO pn) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "insert into goodsreceiptnote (idGRN,idSupplier,dayReceive,deleted,idUserImport,accept,totalMoney) values(?,?,?,1,?,0,?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pn.getId());
            pre.setInt(2, pn.getIdSupplier());
            pre.setString(3, pn.getDayReceive());
            pre.setInt(4, pn.getIdUser());
            pre.setString(5, pn.getTotalMoney());
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }
}
