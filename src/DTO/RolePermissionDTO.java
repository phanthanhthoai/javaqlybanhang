/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class RolePermissionDTO {

    private int idRolePermission;
    private int roleId;
    private int permissionId;
    private int has;

    public int getHas() {
        return has;
    }

    public void setHas(int has) {
        this.has = has;
    }

    public RolePermissionDTO(int idRolePermission, int roleId, int permissionId, int has) {
        this.idRolePermission = idRolePermission;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.has = has;
    }
    public RolePermissionDTO(int roleId, int permissionId, int has) {
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.has = has;
    }

    public RolePermissionDTO() {
    }


    public int getIdRolePermission() {
        return idRolePermission;
    }

    public void setIdRolePermission(int idRolePermission) {
        this.idRolePermission = idRolePermission;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

}
