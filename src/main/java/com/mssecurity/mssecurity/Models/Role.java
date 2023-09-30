package com.mssecurity.mssecurity.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class Role {
    @Id
    private String _id_role;
    private String role_name;
    private String description;

    public Role(String name, String description) {
        this.role_name = name;
        this.description = description;
    }

    public String get_id() {
        return _id_role;
    }


    public String getRole_Name() {
        return role_name;
    }

    public void setName(String role_name) {
        this.role_name = role_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
