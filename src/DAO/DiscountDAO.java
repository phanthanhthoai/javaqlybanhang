/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DiscountDTO;
import Util.connectDB;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class DiscountDAO {

    public ArrayList<DiscountDTO> laydsmgg(String search) {
        try {
            Connection conn = (Connection) connectDB.getConnection();
            String sql = "";
            if (search == null || search.isEmpty()) {
                sql = "select * from discount where deleted=0";
            } else {
                sql = "select * from discount where namediscount like '%" + search + "%' and deleted=0";

            }
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<DiscountDTO> listMgg = new ArrayList<>();
            while (rs.next()) {
                DiscountDTO mgg = new DiscountDTO(
                        rs.getInt("iddiscount"),
                        rs.getString("namediscount"),
                        rs.getString("daystart"),
                        rs.getString("dayend"),
                        rs.getInt("percent"),
                        rs.getInt("deleted")
                );
                listMgg.add(mgg);
            }
            return listMgg;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return null;
    }
    public DiscountDTO layTengg(String ten){
        DiscountDTO nccDto = new DiscountDTO();
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from discount where namediscount=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,ten);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                nccDto = new DiscountDTO(
                        rs.getInt("iddiscount"),
                        rs.getString("namediscount"),
                        rs.getString("daystart"),
                        rs.getString("dayend"),
                        rs.getInt("percent"),
                        rs.getInt("deleted")
                );
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return nccDto;
    }
    public DiscountDTO layMggid(int id) {
        DiscountDTO nccDto = new DiscountDTO();
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from discount where iddiscount=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                nccDto = new DiscountDTO(
                        rs.getInt("iddiscount"),
                        rs.getString("namediscount"),
                        rs.getString("daystart"),
                        rs.getString("dayend"),
                        rs.getInt("percent"),
                        rs.getInt("deleted")
                );
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return nccDto;
    }

    public boolean themMgg(DiscountDTO nccDto) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "insert into discount (namediscount,daystart,dayend,percent,deleted) values(?,?,?,?,0)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nccDto.getNameDiscount());
            pre.setString(2, nccDto.getDayStart());
            pre.setString(3, nccDto.getDayEnd());
            pre.setInt(4, nccDto.getPercent());
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.print(e);
        }
        return kq;
    }

    public boolean suaMgg(DiscountDTO nccDto) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "update discount set namediscount = ?,daystart=?,dayend=?, percent=? where iddiscount=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nccDto.getNameDiscount());
            pre.setString(2, nccDto.getDayStart());
            pre.setString(3, nccDto.getDayEnd());
            pre.setInt(4, nccDto.getPercent());
            pre.setInt(5,nccDto.getIdDiscount());
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.print(e);
        }
        return kq;
    }

    public boolean chinhTtmgg(int id, int tt) {
        boolean kq = false;
        try {
            int thaydoi = 0;
            if (tt == 0) {
                thaydoi = 1;
            }
            if (tt == 1) {
                thaydoi = 0;
            }
            Connection conn = connectDB.getConnection();
            String sql = "update discount set deleted=? where iddiscount=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, thaydoi);
            pre.setInt(2, id);
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.print(e);
        }
        return kq;
    }
}
