/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PermissionDTO;
import DTO.RoleDTO;
import Util.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Admin
 */
public class RoleDAO {
    public boolean themVaiTro(RoleDTO role) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "insert into role (namerole,deleted) values (?,0)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, role.getNamerole());
            kq = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return kq;
    }
    public ArrayList<RoleDTO> layDsVaiTro() {
        ArrayList<RoleDTO> listVaiTro = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from role where deleted=0";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                RoleDTO pms = new RoleDTO(rs.getInt("idrole"), rs.getString("namerole"));
                listVaiTro.add(pms);
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
        return listVaiTro;
    }
    public RoleDTO layVt(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        RoleDTO r = new RoleDTO();
        try {
            conn = connectDB.getConnection();
            String sql = "select * from role where idrole=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                r= new RoleDTO(rs.getInt("idrole"),rs.getString("namerole"));
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }finally {
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
        return r;
    }
    public RoleDTO layVtCuoi() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        RoleDTO r = new RoleDTO();
        try {
            conn = connectDB.getConnection();
            String sql = "select * from role order by idrole desc limit 1";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                r= new RoleDTO(rs.getInt("idrole"),rs.getString("namerole"));
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }finally {
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
        return r;
    }
}
