/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PermissionDTO;
import Util.connectDB;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class PermissionDAO {

    public ArrayList<PermissionDTO> layDsQuyen() {
        ArrayList<PermissionDTO> listQuyen = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from permissions";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                PermissionDTO pms = new PermissionDTO(rs.getInt("idpermissions"), rs.getString("code"), rs.getString("description"));
                listQuyen.add(pms);
                System.out.print("oke ");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listQuyen;
    }

    public PermissionDTO layPerId(int idper) {
        PermissionDTO per = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from permissions where idpermissions=?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, idper);
            rs = pre.executeQuery();
            if (rs.next()) {
                per = new PermissionDTO(rs.getInt("idpermissions"), rs.getString("code"), rs.getString("description"));
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pre != null) {
                    pre.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return per;
    }
}
