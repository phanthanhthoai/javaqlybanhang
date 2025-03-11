/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class TaiKhoanDTO {
    private int maTaiKhoan;
    private String tenDangNhap;
    private String matKhau;
    private int maPhanQuyen;
    private int maNhanVien;
    private String ngayCap;
    private int trangThaiTaiKhoan;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(int maTaiKhoan, String tenDangNhap, String matKhau, int maNhanVien) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNhanVien = maNhanVien;
    }

    public TaiKhoanDTO(int maTaiKhoan, String tenDangNhap, String matKhau, int maPhanQuyen, int maNhanVien, String ngayCap, int trangThaiTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maPhanQuyen = maPhanQuyen;
        this.maNhanVien = maNhanVien;
        this.ngayCap = ngayCap;
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
    }

    public TaiKhoanDTO(String tenDangNhap, String matKhau, int maNhanVien, String ngayCap) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNhanVien = maNhanVien;
        this.ngayCap = ngayCap;
    }

    public String getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(String ngayCap) {
        this.ngayCap = ngayCap;
    }

    public TaiKhoanDTO(int maTaiKhoan, String tenDangNhap, String matKhau, int maNhanVien, String ngayCap, int trangThaiTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNhanVien = maNhanVien;
        this.ngayCap = ngayCap;
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
    }

   
    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaPhanQuyen() {
        return maPhanQuyen;
    }

    public void setMaPhanQuyen(int maPhanQuyen) {
        this.maPhanQuyen = maPhanQuyen;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }



    public int getTrangThaiTaiKhoan() {
        return trangThaiTaiKhoan;
    }

    public void setTrangThaiTaiKhoan(int trangThaiTaiKhoan) {
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
    }

    
    
}
