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
    ArrayList<PhieuNhapDTO> listPn = new ArrayList<>();
    private PhieuNhapDAO spinPnDAO = new PhieuNhapDAO();
    private PhieuNhapDAO pnDAO = new PhieuNhapDAO();
    private SanPhamDAO spDAO = new SanPhamDAO();

    public PhieuNhapDTO layPncuoi() {
        return pnDAO.layPncuoi();
    }

//    public boolean xacNhan(int id) {
//        ArrayList<ChiTietPhieuNhapDTO> dssp = pnDAO.layDsPhieuNhapId(id);
//        for (ChiTietPhieuNhapDTO sp : dssp) {
//            SanPhamDTO spu = spDAO.laySpId(sp.getId());
//            int qty = spu.getQty() + sp.getQuantity();
//            double price = (Double.parseDouble(sp.getEprice()) + Double.parseDouble(spu.getPrice())) / 2;
//            String uprice = price + "";
//            if (spDAO.capnhat(sp.getId(), qty, uprice)) {
//                continue;
//            }else{
//                return false;
//            }
//        }
//        return pnDAO.xacNhan(id);
//    }
    public boolean xacNhan(int id) {
        ArrayList<ChiTietPhieuNhapDTO> dssp = pnDAO.layDsPhieuNhapId(id);

        for (ChiTietPhieuNhapDTO sp : dssp) {
            SanPhamDTO spu = spDAO.laytheoSpIdcapnhat(sp.getIdProduct());

            if (spu == null) {
                System.err.println("Không tìm thấy sản phẩm có ID: " + sp.getId());
                return false;
            }

            int qty = spu.getQty() + sp.getQuantity();

            // Lấy giá nhập và giá hiện tại
            String epriceStr = sp.getEprice();
            String currentPriceStr = spu.getPrice();

            if (epriceStr == null || currentPriceStr == null) {
                System.err.println("Giá sản phẩm bị null tại sản phẩm ID: " + sp.getIdProduct());
                return false;
            }

            double price;
            try {
                price = (Double.parseDouble(epriceStr.trim()) + Double.parseDouble(currentPriceStr.trim())) / 2;
            } catch (NumberFormatException e) {
                System.err.println("Giá sản phẩm không hợp lệ tại ID: " + sp.getId());
                e.printStackTrace();
                return false;
            }

            String uprice = String.valueOf(price);

            if (!spDAO.capnhat(sp.getIdProduct(), qty, uprice)) {
                System.err.println("Cập nhật sản phẩm thất bại tại ID: " + sp.getId());
                return false;
            }
        }

        // Nếu cập nhật hết thành công thì xác nhận phiếu nhập
        return pnDAO.xacNhan(id);
    }

    public ArrayList<ChiTietPhieuNhapDTO> layDsCtpnId(int id) {
        return pnDAO.layDsPhieuNhapId(id);
    }

    public ArrayList<PhieuNhapDTO> layDspn(String nbd, String nkt) {
        return listPn = pnDAO.layDsPn(nbd, nkt);
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
                sp.getDescription(),});
        }
    }

    public boolean themPn(int idSupplier, int idGRN, int idNguoinhap, String totalMoney) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        PhieuNhapDTO pnNew = new PhieuNhapDTO(idGRN, idSupplier, formattedDateTime, 0, idNguoinhap, totalMoney);
        return pnDAO.themPn(pnNew);
    }
}
