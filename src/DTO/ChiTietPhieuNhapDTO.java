/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhapDTO {
    private int id;
    private int idGRN;
    private int idProduct;
    private int quantity;
    private String iprice;
    private String interestRate;
    private String eprice;
    private int qtyExist;

    public ChiTietPhieuNhapDTO() {
    }

    public int getQtyExist() {
        return qtyExist;
    }

    public void setQtyExist(int qtyExist) {
        this.qtyExist = qtyExist;
    }

    public ChiTietPhieuNhapDTO(int id, int idGRN, int idProduct, int quantity, String iprice,String interestRate, String eprice, int qtyExist) {
        this.id = id;
        this.idGRN = idGRN;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.iprice = iprice;
        this.interestRate = interestRate;
        this.eprice = eprice;
        this.qtyExist = qtyExist;
    }

    public ChiTietPhieuNhapDTO(int id, int idGRN, int idProduct, int quantity, String iprice, String interestRate, String eprice) {
        this.id = id;
        this.idGRN = idGRN;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.iprice = iprice;
        this.interestRate = interestRate;
        this.eprice = eprice;
    }

    public ChiTietPhieuNhapDTO(int idGRN, int idProduct, int quantity, String iprice, String interestRate, String eprice) {
        this.idGRN = idGRN;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.iprice = iprice;
        this.interestRate = interestRate;
        this.eprice = eprice;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGRN() {
        return idGRN;
    }

    public void setIdGRN(int idGRN) {
        this.idGRN = idGRN;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIprice() {
        return iprice;
    }

    public void setIprice(String iprice) {
        this.iprice = iprice;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getEprice() {
        return eprice;
    }

    public void setEprice(String eprice) {
        this.eprice = eprice;
    }
    
}
