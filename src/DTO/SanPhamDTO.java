/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import com.mysql.cj.jdbc.Blob;

/**
 *
 * @author Admin
 */
public class SanPhamDTO {

    private int id;
    private String name;
    private String category;
    private String description;
    private Blob image;
    private String price;
    private int deleted;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int id, String name, String category, String description, Blob image, String price, int deleted) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
        this.deleted = deleted;
    }

    public SanPhamDTO(int id, String name, String category, String description, Blob image, int deleted) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
        this.deleted = deleted;
    }
    public SanPhamDTO(int id, String name, String category, String description, Blob image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    public SanPhamDTO(String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
