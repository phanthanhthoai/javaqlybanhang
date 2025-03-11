/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Util.connectDB;
import DTO.TaiKhoanDTO;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class loginDAO {

    public TaiKhoanDTO checkLogin(String userName, String password) {
        TaiKhoanDTO tk = null;
        try {
            Connection con = connectDB.getConnection();
            String sql = "select * from accountlogin where TenDangNhap=? and MatKhau=?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int maTK = rs.getInt("MaTaiKhoan");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                int maQuyen = rs.getInt("MaPhanQuyen");
                int maNV = rs.getInt("MaNhanVien");
                String ngayCap = rs.getString("NgayCap");
                int trangThai = rs.getInt("TrangThaiTaiKhoan");
                tk = new TaiKhoanDTO(maTK, tenDangNhap, matKhau, maQuyen, maNV, ngayCap, trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        };
        return tk;
    }

}
