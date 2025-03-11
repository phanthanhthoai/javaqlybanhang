/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhapBUS {
    private ChiTietPhieuNhapDAO ctpnDAO= new ChiTietPhieuNhapDAO();
    
    public boolean themCtpn(JTable jtb,int idPn){
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        return ctpnDAO.themChiTietPhieuNhap(model, idPn);
    }
    public ChiTietPhieuNhapDTO layCtpnId(int id){
        return ctpnDAO.layCtpnId(id);
    }
    public boolean capNhatSoLuong(JTable jtb){
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        return ctpnDAO.capNhatSoLuong(model);
    }
}
