/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.SanPhamDTO;
import Util.connectDB;
import com.mysql.cj.jdbc.Blob;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Admin
 */
public class SanPhamDAO {

    public ArrayList<SanPhamDTO> layDsSp(String search) {
        try {
            Connection conn = connectDB.getConnection();
            String sql = "";
            if (search == null || search.isEmpty()) {
                sql = "select * from product";
            } else {
                sql = "select * from product where (idproduct like '%" + search + "%' or nameProduct like '%" + search + "%' or description like '%" + search + "%')";

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

    public SanPhamDTO laySpId(int id) {
        SanPhamDTO sp = new SanPhamDTO();
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from product where idproduct=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                sp = new SanPhamDTO(
                        rs.getInt("idproduct"),
                        rs.getString("nameProduct"),
                        rs.getString("category"),
                        rs.getString("description"),
                        (Blob) rs.getBlob("image"),
                        rs.getInt("deleted"));
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return sp;
    }

    public boolean themSp(SanPhamDTO sp, InputStream hinhanh) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "insert into product (nameProduct,category,description,image,deleted) values(?,?,?,?,0)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sp.getName());
            pre.setString(2, sp.getCategory());
            pre.setString(3, sp.getDescription());
            pre.setBlob(4, hinhanh);
            kq = pre.executeUpdate()>0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }

    public boolean suaSp(SanPhamDTO sp, InputStream hinhanh, int checkImage) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "";
            PreparedStatement pre;
            if (checkImage == 1) {
                sql = "update product set nameProduct=?, category=?, description=?, image=? where idproduct=?";
                pre = conn.prepareStatement(sql);
                pre.setString(1, sp.getName());
                pre.setString(2, sp.getCategory());
                pre.setString(3, sp.getDescription());
                pre.setBlob(4, hinhanh);
                pre.setInt(5, sp.getId());

            } else {
                sql = "update product set nameProduct=?, category=?, description=? where idproduct=?";
                pre = conn.prepareStatement(sql);
                pre.setString(1, sp.getName());
                pre.setString(2, sp.getCategory());
                pre.setString(3, sp.getDescription());
                pre.setInt(4, sp.getId());
            }
            kq = pre.executeUpdate()>0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }

    public boolean xoaSp(int id,int del) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "UPDATE product SET deleted=? where idproduct=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            if(del==0) pre.setInt(1, 1);
            else pre.setInt(1, 0);
            pre.setInt(2, id);
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }
}
