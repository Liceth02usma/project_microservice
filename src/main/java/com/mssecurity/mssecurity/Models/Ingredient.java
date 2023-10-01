package com.mssecurity.mssecurity.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document()
public class Ingredient {

    @Id
    private String _id;

    private String name;
    private LocalDateTime dateInquisition;
    private int amount;
    private String category;

    public Ingredient(String name, LocalDateTime dateInquisition, int amount, String category) {
        this.name = name;
        this.dateInquisition = dateInquisition;
        this.amount = amount;
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

    public LocalDateTime getDateInquisition() {
        return dateInquisition;
    }

    public void setDateInquisition(LocalDateTime dateInquisition) {
        this.dateInquisition = dateInquisition;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
