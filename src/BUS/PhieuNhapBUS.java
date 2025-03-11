/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhieuNhapDAO;
import DAO.SanPhamDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import com.mysql.cj.jdbc.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class PhieuNhapBUS {
    ArrayList<SanPhamDTO> listSp = null;
    ArrayList<PhieuNhapDTO> listPn= new ArrayList<>();
    public PhieuNhapDAO spinPnDAO = new PhieuNhapDAO();
    public PhieuNhapDAO pnDAO=new PhieuNhapDAO();
    public PhieuNhapDTO layPncuoi(){
        return pnDAO.layPncuoi();
    }
    public boolean xacNhan(int id){
        return pnDAO.xacNhan(id);
    }
    public ArrayList<ChiTietPhieuNhapDTO> layDsCtpnId(int id){
        return pnDAO.layDsPhieuNhapId(id);
    }
    public ArrayList<PhieuNhapDTO> layDspn(String nbd,String nkt){
        return listPn = pnDAO.layDsPn(nbd,nkt);        
    }       
    public void layDsSp(JTable tbl, String search) {
        listSp = spinPnDAO.layDsSp(search);
        DefaultTableModel model = new DefaultTableModel(
                new String[]{
                    "Mã SP", "Tên SP", "Mô tả"
                },
                0
        );
        tbl.setModel(model);
        model.setRowCount(0);

        for (SanPhamDTO sp : listSp) {
            model.addRow(new Object[]{
                sp.getId(),
                sp.getName(),
                sp.getDescription(),
            });
        }
    }
    public boolean themPn(int idSupplier,int idGRN,int idNguoinhap,String totalMoney){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        PhieuNhapDTO pnNew= new PhieuNhapDTO(idGRN,idSupplier,formattedDateTime,0,idNguoinhap,totalMoney);
        return pnDAO.themPn(pnNew);
    }
}
