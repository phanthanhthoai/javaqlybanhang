/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DiscountDAO;
import DTO.DiscountDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DiscountBUS {

    private DiscountDAO dcDAO = new DiscountDAO();

    public ArrayList<DiscountDTO> laydsmgg(String search) {
        return dcDAO.laydsmgg(search);
    }
    public DiscountDTO laytengg(String search){
        return dcDAO.layTengg(search);  
    }
    
    public boolean themMgg(String ten, String nbd, String nkt, int percent) {
        DiscountDTO dcDto = new DiscountDTO(ten, nbd, nkt, percent);
        return dcDAO.themMgg(dcDto);
    }

    public boolean suaMgg(int id, String name, String nbd, String nkt, int percent) {
        DiscountDTO dcDto = new DiscountDTO(id, name, nbd, nkt, percent, 0);
        return dcDAO.suaMgg(dcDto);
    }

    public boolean chinhMgg(int id, int tt) {
        return dcDAO.chinhTtmgg(id, tt);
    }
}
