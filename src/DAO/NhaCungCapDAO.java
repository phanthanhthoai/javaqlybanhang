/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;
import java.sql.*;
import Util.connectDB;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhaCungCapDAO {

    public ArrayList<NhaCungCapDTO> layDsNcc(String search) {
        try {
            Connection conn = (Connection) connectDB.getConnection();
            String sql = "";
            if (search == null || search.isEmpty()) {
                sql = "select * from supplier where deleted=0";
            } else {
                sql = "select * from supplier where (idsupplier like '%" + search + "%' or nameSupplier like '%" + search + "%' or address like '%" + search + "%' or phone like '%" + search + "%') and deleted=0";

            }
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<NhaCungCapDTO> listNcc = new ArrayList<>();
            while (rs.next()) {
                NhaCungCapDTO nv = new NhaCungCapDTO(
                        rs.getInt("idsupplier"),
                        rs.getString("nameSupplier"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getInt("deleted")
                );
                listNcc.add(nv);
            }
            return listNcc;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return null;
    }
    public NhaCungCapDTO layNccid(int id){
        NhaCungCapDTO nccDto = new NhaCungCapDTO();
         try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from supplier where idsupplier=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                nccDto = new NhaCungCapDTO(rs.getInt("idsupplier"),rs.getString("nameSupplier"),rs.getString("address"),rs.getString("phone"),rs.getInt("deleted"));
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
         return nccDto;
    }
    public boolean themNcc(NhaCungCapDTO nccDto) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "insert into supplier (nameSupplier,address,phone,deleted) values(?,?,?,0)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nccDto.getNameNcc());
            pre.setString(2, nccDto.getAddress());
            pre.setString(3, nccDto.getPhone());
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.print(e);
        }
        return kq;
    }

    public boolean suaNcc(NhaCungCapDTO nccDto) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "update supplier set nameSupplier = ?,address=?,phone=? where idsupplier=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nccDto.getNameNcc());
            pre.setString(2, nccDto.getAddress());
            pre.setString(3, nccDto.getPhone());
            pre.setInt(4, nccDto.getId());
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.print(e);
        }
        return kq;
    }

    public boolean chinhTt(int id,int tt) {
        boolean kq = false;
        try {
            int thaydoi = 0;
            if(tt == 0) thaydoi =1;
            if(tt == 1) thaydoi =0;
            Connection conn = connectDB.getConnection();
            String sql = "update supplier set deleted=? where idsupplier=?";
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
