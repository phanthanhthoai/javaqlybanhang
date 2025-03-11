/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.PermissionBUS;
import DTO.PermissionDTO;
import DTO.RoleDTO;
import DTO.RolePermissionDTO;
import Util.connectDB;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RolePermissionDAO {

    public boolean themQuyenVaoVaiTro(int roleId) {
        boolean kq = false;
        String sql = "INSERT INTO rolepermissions (roleId, permissionId, has) VALUES (?, ?, 0)";

        Connection conn = null;
        PreparedStatement stmt = null;
        PermissionBUS perBUS = new PermissionBUS();
        try {
            conn = connectDB.getConnection();
            stmt = conn.prepareStatement(sql);
            ArrayList<PermissionDTO> listQuyen = perBUS.layDsQuyen();
            int count = 0;

            for (PermissionDTO quyen : listQuyen) {
                stmt.setInt(1, roleId);
                stmt.setInt(2, quyen.getIdPermission());
                System.out.print(quyen.getCode() + " ");
                count++;
                stmt.addBatch();

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

    public ArrayList<RolePermissionDTO> layDsQuyen(int roleId) {
        ArrayList<RolePermissionDTO> listQ = new ArrayList<>();
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from rolepermissions where roleId=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, roleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RolePermissionDTO rp = new RolePermissionDTO(rs.getInt("idrolepermissions"), rs.getInt("roleId"), rs.getInt("permissionId"), rs.getInt("has"));
                listQ.add(rp);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return listQ;
    }

    public List<String> layDsCoQuyen(int roleId) {
        List<String> listQ = new ArrayList<>();
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from rolepermissions where (roleId=? and has=1)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, roleId);
            ResultSet rs = stmt.executeQuery();
            PermissionBUS perBUS = new PermissionBUS();
            while (rs.next()) {
                listQ.add(perBUS.layPerId(rs.getInt("permissionId")).getCode());
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return listQ;
    }

    public boolean capNhapQuyenVaiTro(RolePermissionDTO rpDTO) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "update rolepermissions set has=? where (roleId=? and permissionId=?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rpDTO.getHas());
            stmt.setInt(2, rpDTO.getRoleId());
            stmt.setInt(3, rpDTO.getPermissionId());
            kq = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return kq;
    }
}
