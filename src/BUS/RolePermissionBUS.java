/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.RolePermissionDAO;
import DTO.RoleDTO;
import DTO.RolePermissionDTO;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Admin
 */
public class RolePermissionBUS {
    private RolePermissionDAO rolePerDAO = new RolePermissionDAO();
    public boolean themQuyenVaoVaiTro(int roleId){
        return rolePerDAO.themQuyenVaoVaiTro(roleId);
    }
    public boolean capNhapQuyenVaiTro(int roleId,int perId,int has){
        return rolePerDAO.capNhapQuyenVaiTro(new RolePermissionDTO(roleId,perId,has));
    }
    
    public ArrayList<RolePermissionDTO> layDsQuyen(int roleId){
        return rolePerDAO.layDsQuyen(roleId);
    }
    public List<String> layDsCoQuyen(int roleId){
        return rolePerDAO.layDsCoQuyen(roleId);
    }
}
