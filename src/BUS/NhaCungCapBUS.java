/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhaCungCapBUS {

    NhaCungCapDAO nccDAO = new NhaCungCapDAO();

    public ArrayList<NhaCungCapDTO> lsNcc(String search) {
        return nccDAO.layDsNcc(search);
    }

    public boolean themNcc(String nameNcc, String sdt, String diachi) {
        NhaCungCapDTO nccDto = new NhaCungCapDTO(nameNcc, diachi, sdt);
        return nccDAO.themNcc(nccDto);
    }
    public NhaCungCapDTO layNccid(int id){
        return nccDAO.layNccid(id);
    }
    public boolean chinhTt(int id,int tt){
        return nccDAO.chinhTt(id, tt);
    }
    public boolean suaNcc(int idncc, String nameNcc, String sdt, String diachi) {
        NhaCungCapDTO nccDto = new NhaCungCapDTO(idncc, nameNcc, diachi, sdt, 0);
        return nccDAO.suaNcc(nccDto);
    }
}
