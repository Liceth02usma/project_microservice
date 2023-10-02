package com.mssecurity.mssecurity.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User {
    @Id
    private String _id;
    private String name;
    private String password;


    @DBRef
    private Role role;

    public User() {}

    public Role getRole() {
        return role;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
