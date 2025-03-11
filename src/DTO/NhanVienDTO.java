/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class NhanVienDTO {
    private int MaNV;
    private String TenNV;
    private String Email;
    private String sdt;
    private String Diachi;
    private String Gioitinh;
    private Date NgaySinh;
    private int VaiTro;
    public NhanVienDTO() {
    }

    public NhanVienDTO(int MaNV, String TenNV, String Email, String sdt, String Diachi,String Gioitinh,Date NgaySinh) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.Email = Email;
        this.sdt = sdt;
        this.Diachi = Diachi;
        this.Gioitinh = Gioitinh;
        this.NgaySinh = NgaySinh;
    }

    public NhanVienDTO(int MaNV, String TenNV, String Email, String sdt, String Diachi, String Gioitinh, Date NgaySinh, int VaiTro) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.Email = Email;
        this.sdt = sdt;
        this.Diachi = Diachi;
        this.Gioitinh = Gioitinh;
        this.NgaySinh = NgaySinh;
        this.VaiTro = VaiTro;
    }

    public int getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(int VaiTro) {
        this.VaiTro = VaiTro;
    }
    

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    
    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String Gioitinh) {
        this.Gioitinh = Gioitinh;
    }
    
}
