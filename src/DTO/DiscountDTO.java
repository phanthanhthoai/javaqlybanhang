/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class DiscountDTO {
    private int idDiscount;
    private String nameDiscount;
    private String dayStart;
    private String dayEnd;
    private int percent;
    private int deleted;

    public DiscountDTO() {
    }

    public DiscountDTO(String nameDiscount, String dayStart, String dayEnd, int percent) {
        this.nameDiscount = nameDiscount;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.percent = percent;
    }

    public DiscountDTO(int idDiscount, String nameDiscount, String dayStart, String dayEnd, int percent,int del) {
        this.idDiscount = idDiscount;
        this.nameDiscount = nameDiscount;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.percent = percent;
        this.deleted =del;
    }

    
    
    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public String getNameDiscount() {
        return nameDiscount;
    }

    public void setNameDiscount(String nameDiscount) {
        this.nameDiscount = nameDiscount;
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
    
    
}
