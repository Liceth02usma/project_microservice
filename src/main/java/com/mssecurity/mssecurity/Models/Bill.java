package com.mssecurity.mssecurity.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Bill {
    @Id
    private String _id;

    private String datetime;
    private String total;
    
    @DBRef
    private DishFoodOrder dishFoodOrder;

    public Bill(String datetime, String total) {
        this.datetime = datetime;
        this.total = total;
    }

    public String get_id() {
        return _id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public DishFoodOrder getDishFoodOrder() {
        return dishFoodOrder;
    }

    public void setDishFoodOrder(DishFoodOrder dishFoodOrder) {
        this.dishFoodOrder = dishFoodOrder;
    }
}
