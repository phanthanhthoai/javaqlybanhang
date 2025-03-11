/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class TaiKhoanBUS {

    private TaiKhoanDAO tkDAO = new TaiKhoanDAO();

    public boolean themTk(String tenDn, String mk, int maNv, String ngayCap) {
        TaiKhoanDTO tkDto = new TaiKhoanDTO(tenDn, mk, maNv, ngayCap);
        return tkDAO.themTk(tkDto);
    }

    public ArrayList<TaiKhoanDTO> listTk() {
        return tkDAO.listTk();
    }

    public boolean suaTk(int maTk, String tenDn, String mk, int maNv) {
        TaiKhoanDTO tkDto = new TaiKhoanDTO(maTk, tenDn, mk, maNv);
        return tkDAO.suaTk(tkDto);
    }

    public boolean chinhTt(int id,int tt) {
        return tkDAO.chinhTrangthai(id,tt);
    }
}
