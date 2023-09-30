package com.mssecurity.mssecurity.Controllers;

import com.mssecurity.mssecurity.Models.Permission;
import com.mssecurity.mssecurity.Models.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class RolePermission {
    @Id
    private String _id;
    @DBRef
    private Role role;
    @DBRef
    private Permission permission;

    public RolePermission() {
    }

    public String get_id() {
        return _id;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}