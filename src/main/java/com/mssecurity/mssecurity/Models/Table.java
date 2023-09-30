package com.mssecurity.mssecurity.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Table {
    @Id
    private String _id;

    private int capacity;

    private boolean availability;

    public Table(int capacity, boolean availability) {
        this.capacity = capacity;
        this.availability = availability;
    }

    public String get_id() {
        return _id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
