/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.RoleDAO;
import DTO.RoleDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class RoleBUS {
    private RoleDAO roleDAO= new RoleDAO(); 
    private ArrayList<RoleDTO> listRole= roleDAO.layDsVaiTro();
    public boolean themVaiTro(String name) {
        return roleDAO.themVaiTro(new RoleDTO(name));
    }
    public RoleDTO layVt(int id){
        return roleDAO.layVt(id);
    }
    public ArrayList<RoleDTO> layDsVaiTro(){
        return roleDAO.layDsVaiTro();
    }
    public boolean ktTonTai(String name){
        boolean kq= false;
        for(RoleDTO r : listRole){
            if(r.getNamerole().equals(name)){
                kq=true;
            }
        }
        return kq;
    }
    public RoleDTO layVtCuoi() {
        return roleDAO.layVtCuoi();
    }
}
