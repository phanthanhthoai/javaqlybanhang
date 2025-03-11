/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BillDetailsDAO;
import DTO.BillDetailsDTO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class BillDetailsBUS {

    private BillDetailsDAO billDetailsDAO = new BillDetailsDAO();

    public boolean themChiTietHoaDon(JTable jtb, int idBill) {
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        return billDetailsDAO.themChiTietHoaDon(model, idBill);
    }
}
