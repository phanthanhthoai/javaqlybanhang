/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class BillDetailsDTO {
    private int id;
    private int idBill;
    private int idProduct;
    private String price;
    private int quantity;
    private String totalPay;
    private int idGrnDetails;
    
    public BillDetailsDTO() {
    }

    public BillDetailsDTO(int id, int idBill, int idProduct, String price, int quantity, String totalPay ,int idGrnDetails) {
        this.id = id;
        this.idBill = idBill;
        this.idProduct = idProduct;
        this.price = price;
        this.quantity = quantity;
        this.totalPay = totalPay;
        this.idGrnDetails = idGrnDetails;
    }

    public int getIdGrnDetails() {
        return idGrnDetails;
    }

    public void setIdGrnDetails(int idGrnDetails) {
        this.idGrnDetails = idGrnDetails;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }
    
    
}
