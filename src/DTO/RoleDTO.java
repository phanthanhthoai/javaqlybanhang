/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class RoleDTO {
    private int idrole;
    private String namerole;
    private int deleted;

    public RoleDTO() {
    }

    public RoleDTO(String namerole) {
        this.namerole = namerole;
    }

    public RoleDTO(int idrole, String namerole) {
        this.idrole = idrole;
        this.namerole = namerole;
    }

    public int getIdrole() {
        return idrole;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    public String getNamerole() {
        return namerole;
    }

    public void setNamerole(String namerole) {
        this.namerole = namerole;
    }

    public RoleDTO(int idrole, String namerole, int deleted) {
        this.idrole = idrole;
        this.namerole = namerole;
        this.deleted = deleted;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
}
