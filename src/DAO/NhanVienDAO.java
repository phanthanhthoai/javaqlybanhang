/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
import java.sql.*;
import Util.connectDB;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhanVienDAO {

    public ArrayList<NhanVienDTO> layDsNv(String search) {
        try {
            Connection conn = connectDB.getConnection();
            String sql = "";
            if (search == null || search.isEmpty()) {
                sql = "select * from staff where deleted=0";
            } else {
                sql = "select * from staff where (idstaff like '%" + search + "%' or nameStaff like '%" + search + "%' or email like '%" + search + "%' or phone like '%" + search + "%' or address like '%" + search + "%') and deleted=0";

            }
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<NhanVienDTO> listNV = new ArrayList<>();
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(
                        rs.getInt("idstaff"),
                        rs.getString("nameStaff"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("sex"),
                        rs.getDate("birthday"),
                        rs.getInt("roleId")
                );
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return null;
    }

    public NhanVienDTO layNvId(int id) {
        NhanVienDTO nv = null;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from staff where idStaff=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                nv = new NhanVienDTO(rs.getInt("idstaff"), rs.getString("nameStaff"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("sex"), rs.getDate("birthday"), rs.getInt("roleId"));
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return nv;
    }

    public boolean themNv(NhanVienDTO nv) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "insert into staff (namestaff,email,phone,address,sex,birthday,deleted,roleId) values(?,?,?,?,?,?,0)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nv.getTenNV());
            pre.setString(2, nv.getEmail());
            pre.setString(3, nv.getSdt());
            pre.setString(4, nv.getDiachi());
            pre.setString(5, nv.getGioitinh());
            pre.setDate(6, (Date) nv.getNgaySinh());
            pre.setInt(7, nv.getVaiTro());
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }

    public boolean suaNV(NhanVienDTO nv) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "update staff set nameStaff=?, email=?, phone=?, address=?, sex=?, birthday=?,roleId=? where idstaff=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nv.getTenNV());
            pre.setString(2, nv.getEmail());
            pre.setString(3, nv.getSdt());
            pre.setString(4, nv.getDiachi());
            pre.setString(5, nv.getGioitinh());
            pre.setDate(6, new java.sql.Date(nv.getNgaySinh().getTime()));
            pre.setInt(7, nv.getVaiTro());
            pre.setInt(8, nv.getMaNV());
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }

    public boolean xoaNv(NhanVienDTO nv) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "update staff set deleted=1 where idstaff=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, nv.getMaNV());
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }
;

}
