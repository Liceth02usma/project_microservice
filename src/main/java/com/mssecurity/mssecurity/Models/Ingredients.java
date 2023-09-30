package com.mssecurity.mssecurity.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class Ingredients {

    @Id
    private String _id;

    private String name;
    private String dateInquisition;
    private int cantidad;
    private String category;

    public Ingredients(String name, String dateInquisition, int cantidad, String category) {
        this.name = name;
        this.dateInquisition = dateInquisition;
        this.cantidad = cantidad;
        this.category = category;
    }

    public String get_id() {
        return _id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateInquisition() {
        return dateInquisition;
    }

    public void setDateInquisition(String dateInquisition) {
        this.dateInquisition = dateInquisition;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
