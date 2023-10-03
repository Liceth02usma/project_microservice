package com.mssecurity.mssecurity.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class Ingredient {

    @Id
    private String _id;

    private String name;
    private String dateInquisition;
    private int value;

    public Ingredient(String name, String dateInquisition, int value) {
        this.name = name;
        this.dateInquisition = dateInquisition;
        this.value = value;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
