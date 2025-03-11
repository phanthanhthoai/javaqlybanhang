/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DTO.TaiKhoanDTO;
import DAO.loginDAO;
/**
 *
 * @author Admin
 */
public class loginBUS {
    private loginDAO loginDAO;
    public loginBUS(){
        this.loginDAO = new loginDAO();
    }
    public TaiKhoanDTO checkLogin(String userName,String password)
    {
        return loginDAO.checkLogin(userName, password);
    }
}
