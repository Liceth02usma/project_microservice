package com.mssecurity.mssecurity.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class DishFoodOrder {
    @Id
    private String _id;
    
    private int amount;

    @DBRef
    private Order order;

    @DBRef
    private DishFood dishFood;

    @DBRef
    private Bill bill;

    public DishFoodOrder() {}

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String get_id() {
        return _id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DishFood getDishFood() {
        return dishFood;
    }

    public void setDishFood(DishFood dishFood) {
        this.dishFood = dishFood;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
