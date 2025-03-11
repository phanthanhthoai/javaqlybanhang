/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class NhaCungCapDTO {
    private int id;
    private String nameNcc;
    private String address;
    private String phone;
    private int deleted;

    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(String nameNcc, String address, String phone) {
        this.nameNcc = nameNcc;
        this.address = address;
        this.phone = phone;
    }

    public NhaCungCapDTO(int id, String nameNcc, String address, String phone, int deleted) {
        this.id = id;
        this.nameNcc = nameNcc;
        this.address = address;
        this.phone = phone;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameNcc() {
        return nameNcc;
    }

    public void setNameNcc(String nameNcc) {
        this.nameNcc = nameNcc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    
}
