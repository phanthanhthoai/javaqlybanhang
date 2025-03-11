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
public class BillDTO {
    private int id;
    private int user;
    private String dayCreated;
    private String totalPay;

    public BillDTO() {
    }

    public BillDTO(int user, String dayCreated, String totalPay) {
        this.user = user;
        this.dayCreated = dayCreated;
        this.totalPay = totalPay;
    }

    public BillDTO(int id, int user, String dayCreated, String totalPay) {
        this.id = id;
        this.user = user;
        this.dayCreated = dayCreated;
        this.totalPay = totalPay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getDayCreated() {
        return dayCreated;
    }

    public void setDayCreated(String dayCreated) {
        this.dayCreated = dayCreated;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }
    
    
}
