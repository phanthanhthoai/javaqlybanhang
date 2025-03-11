/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuNhapDTO {
    private int id;
    private int idSupplier;
    private String dayReceive;
    private int deleted;
    private int idUser;
    private String totalMoney;

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public PhieuNhapDTO(int id, int idSupplier, String dayReceive, int deleted, int idUser, String totalMoney) {
        this.id = id;
        this.idSupplier = idSupplier;
        this.dayReceive = dayReceive;
        this.deleted = deleted;
        this.idUser = idUser;
        this.totalMoney = totalMoney;
    }

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(int id, int idSupplier, String dayReceive, int deleted, int idUser) {
        this.id = id;
        this.idSupplier = idSupplier;
        this.dayReceive = dayReceive;
        this.deleted = deleted;
        this.idUser = idUser;
    }

    public PhieuNhapDTO(int idSupplier, String dayReceive, int idUser) {
        this.idSupplier = idSupplier;
        this.dayReceive = dayReceive;
        this.idUser = idUser;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getDayReceive() {
        return dayReceive;
    }

    public void setDayReceive(String dayReceive) {
        this.dayReceive = dayReceive;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
}
