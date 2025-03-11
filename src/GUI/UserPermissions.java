/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class UserPermissions {

    private static UserPermissions instance;
    private Set<String> permissions;

    private UserPermissions() {
        permissions = new HashSet<>();
    }

    public static UserPermissions getInstance() {
        if (instance == null) {
            instance = new UserPermissions();
        }
        return instance;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions.clear();
        this.permissions.addAll(permissions);
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    public void clearPermissions() {
        permissions.clear();
    }
}
