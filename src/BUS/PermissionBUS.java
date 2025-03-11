/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PermissionDAO;
import DTO.PermissionDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class PermissionBUS {
    ArrayList<PermissionDTO> listQuyen= null;
    PermissionDAO perDAO = new PermissionDAO();
    public ArrayList<PermissionDTO> layDsQuyen() {
        return perDAO.layDsQuyen();
    }
    public PermissionDTO layPerId(int id){
        return perDAO.layPerId(id);
    }
}
