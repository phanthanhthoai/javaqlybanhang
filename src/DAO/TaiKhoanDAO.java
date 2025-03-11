/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.TaiKhoanDTO;
import Util.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class TaiKhoanDAO {

    public ArrayList<TaiKhoanDTO> listTk(){
        ArrayList<TaiKhoanDTO> listtk= new ArrayList<>();
        try{
            Connection conn = connectDB.getConnection();
            String sql="select * from accountlogin";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                TaiKhoanDTO tkDto = new TaiKhoanDTO(rs.getInt("MaTaiKhoan"),rs.getString("TenDangNhap"),rs.getString("MatKhau"),rs.getInt("MaPhanQuyen"),rs.getInt("MaNhanVien"),rs.getString("NgayCap"),rs.getInt("TrangThaiTaiKhoan"));
                listtk.add(tkDto);
            }
        }catch(Exception e){
            System.out.print(e);
        }
        return listtk;
    }
    public boolean themTk(TaiKhoanDTO tkDto) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "insert into accountlogin (TenDangNhap,MatKhau,MaNhanVien,NgayCap,TrangThaiTaiKhoan) values(?,?,?,?,0)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, tkDto.getTenDangNhap());
            pre.setString(2, tkDto.getMatKhau());
            pre.setInt(3, tkDto.getMaNhanVien());
            pre.setString(4, tkDto.getNgayCap());
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.print(e);
        }
        return kq;
    }

    public boolean suaTk(TaiKhoanDTO tkDto) {
        boolean kq = false;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "";
            PreparedStatement pre;
            if (tkDto.getMatKhau().isEmpty() || tkDto.getMatKhau().equals(null)) {
                sql = "update accountlogin set TenDangNhap=?,MaNhanVien=? where MaTaiKhoan=?";
                pre = conn.prepareStatement(sql);
                pre.setString(1, tkDto.getTenDangNhap());
                pre.setInt(2, tkDto.getMaNhanVien());
                pre.setInt(3, tkDto.getMaTaiKhoan());

            } else {
                sql = "update accountlogin set TenDangNhap=?,MatKhau=?,MaNhanVien=? where MaTaiKhoan=?";
                pre = conn.prepareStatement(sql);
                pre.setString(1, tkDto.getTenDangNhap());
                pre.setString(2, tkDto.getMatKhau());
                pre.setInt(3, tkDto.getMaNhanVien());
                pre.setInt(4, tkDto.getMaTaiKhoan());
            }

            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }
    public TaiKhoanDTO layTkid(int id){
        TaiKhoanDTO tkDto = new TaiKhoanDTO();
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from accountlogin where MaTaiKhoan=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                tkDto = new TaiKhoanDTO(rs.getInt("MaTaiKhoan"),rs.getString("TenDangNhap"),rs.getString("MatKhau"),rs.getInt("MaNhanVien"),rs.getString("NgayCap"),rs.getInt("TrangThaiTaiKhoan"));
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return tkDto;
    }
    public boolean chinhTrangthai(int id,int tt) {
        boolean kq = false;
        
        try {
            int thaydoi = 0;
            if(tt == 0) thaydoi =1;
            if(tt == 1) thaydoi =0;
            Connection conn = connectDB.getConnection();
            String sql = "update accountlogin set TrangThaiTaiKhoan=? where MaTaiKhoan=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, thaydoi);
            pre.setInt(2, id);
            kq = pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return kq;
    }
}
