/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.*;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import Util.dialog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhanVienBUS {

    private ArrayList<NhanVienDTO> listNv = null;
    private NhanVienDAO nvDAO = new NhanVienDAO();
    private RoleBUS roleBUS = new RoleBUS();
    
    public NhanVienDTO layNvId(int id) {
        return nvDAO.layNvId(id);
    }

    public boolean themNv(String ten, String email, String phone, String diachi, String gioitinh, Date ngaysinh, int roleId) {
        ten = ten.trim();
        email = email.trim();
        phone = phone.trim();
        NhanVienDTO nv = new NhanVienDTO();
        nv.setTenNV(ten);
        nv.setEmail(email);
        nv.setDiachi(diachi);
        nv.setSdt(phone);
        nv.setGioitinh(gioitinh);
        nv.setNgaySinh(ngaysinh);
        nv.setVaiTro(roleId);
        boolean kq = nvDAO.themNv(nv);
        if (kq) {
            new dialog("Cập nhập thành công!", dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Cập nhập thất bại!", dialog.ERROR_DIALOG);
        }
        return kq;
    }

    public boolean suaNv(int id, String ten, String email, String phone, String diachi, String gioitinh, Date ngaysinh,int roleId) {
        ten = ten.trim();
        email = email.trim();
        phone = phone.trim();
        NhanVienDTO nv = new NhanVienDTO();
        nv.setMaNV(id);
        nv.setTenNV(ten);
        nv.setEmail(email);
        nv.setDiachi(diachi);
        nv.setSdt(phone);
        nv.setGioitinh(gioitinh);
        nv.setNgaySinh(ngaysinh);
        nv.setVaiTro(roleId);

        boolean kq = nvDAO.suaNV(nv);
        if (kq) {
            new dialog("Sửa thành công!", dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Sửa thất bại!", dialog.ERROR_DIALOG);
        }
        return kq;
    }

    public boolean xoaNv(int id, String ten, String email, String phone, String diachi, String gioitinh, Date ngaysinh) {
        ten = ten.trim();
        email = email.trim();
        phone = phone.trim();
        NhanVienDTO nv = new NhanVienDTO();
        nv.setMaNV(id);
        nv.setTenNV(ten);
        nv.setEmail(email);
        nv.setDiachi(diachi);
        nv.setSdt(phone);
        nv.setGioitinh(gioitinh);
        nv.setNgaySinh(ngaysinh);
        boolean kq = nvDAO.xoaNv(nv);
        if (kq) {
            new dialog("Xóa thành công!", dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Xóa thất bại!", dialog.ERROR_DIALOG);
        }
        return kq;
    }

    public void layDsNv(JTable tbl, String search) {
        listNv = nvDAO.layDsNv(search);
        DefaultTableModel model = new DefaultTableModel(
                new String[]{
                    "Mã NV", "Tên NV", "Email", "Số điện thoại", "Địa chỉ", "Giới tính", "Ngày sinh", "Vai trò"
                },
                0
        );
        tbl.setModel(model);
        model.setRowCount(0);
        for (NhanVienDTO nv : listNv) {
            String vaitro=roleBUS.layVt(nv.getVaiTro()).getNamerole();
            model.addRow(new Object[]{nv.getMaNV(), nv.getTenNV(), nv.getEmail(), nv.getSdt(), nv.getDiachi(), nv.getGioitinh(), nv.getNgaySinh(), vaitro});
        }
    }
}
