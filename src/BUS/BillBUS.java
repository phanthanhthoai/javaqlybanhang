/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BillDAO;
import DTO.BillDTO;
import DTO.BillDetailsDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class BillBUS {
    private BillDTO billDTO = new BillDTO();
    private BillDAO billDAO = new BillDAO();
    public BillDTO layBillCuoi(){
        billDTO= billDAO.layBillCuoi();
        return billDTO;
    }
    public boolean themHoaDon(int idUser,String dayCreated, String totalPay){
        billDTO= new BillDTO(idUser,dayCreated, totalPay);
        return billDAO.themHoaDon(billDTO);
    }
    public ArrayList<BillDTO> layDsHd(){
        return billDAO.layDsHd();
    }
    public ArrayList<BillDetailsDTO> layCthd(int id){
        return billDAO.layCthd(id);
    }
}
