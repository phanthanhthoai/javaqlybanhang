/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.NhanVienDTO;

/**
 *
 * @author Admin
 */
public class loggedIn {
    private static NhanVienDTO currentUser;

    public static NhanVienDTO getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(NhanVienDTO user) {
        currentUser = user;
    }
}
