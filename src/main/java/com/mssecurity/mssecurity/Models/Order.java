package com.mssecurity.mssecurity.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Order {
    @Id
    private String _id;

    @DBRef
    private Table table;

    private String datetime;

    private String status;

    public Order(String datetime, String status) {
        this.datetime = datetime;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
