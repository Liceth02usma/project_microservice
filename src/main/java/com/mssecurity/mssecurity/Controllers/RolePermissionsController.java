package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.Permission;
import com.mssecurity.mssecurity.Models.Role;
import com.mssecurity.mssecurity.Models.RolePermission;
import com.mssecurity.mssecurity.Repositories.PermissionRepository;
import com.mssecurity.mssecurity.Repositories.RolePermissionRepository;
import com.mssecurity.mssecurity.Repositories.RoleRepository;

@CrossOrigin
@RestController
@RequestMapping("private/role-permission")
public class RolePermissionsController {
    @Autowired
    private RolePermissionRepository theRolePermissionRepository;
    @Autowired
    private RoleRepository theRoleRepository;
    @Autowired
    private PermissionRepository thePermissionRepository;

    @GetMapping("")
    public List<RolePermission> index() {
        return this.theRolePermissionRepository.findAll();
    }

    // @ResponseStatus(HttpStatus.CREATED)
    // @PostMapping("role/{role_id}/permissions")
    // public RolePermission storeListPermission(@PathVariable String role_id,
    // @RequestBody List<Permission> listPermission) {
    // Role theRole = this.theRoleRepository.findById(role_id).orElse(null);
    // if (theRole != null) {
    // List<RolePermission> savedRolePermission = new ArrayList<>();
    // for (Permission permission : listPermission) {
    // RolePermission newRolePermission = new RolePermission();
    // newRolePermission.setRole(theRole);
    // newRolePermission.setPermission(permission);
    // savedRolePermission.add(newRolePermission).

    // }
    // return this.theRolePermissionRepository.save(newR);
    // } else {
    // return null;
    // }
    // }

    @GetMapping("{id}")
    public RolePermission show(@PathVariable String id) {
        RolePermission theRolePermission = this.theRolePermissionRepository.findById(id).orElse(null);
        return theRolePermission;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("role/{role_id}/permission/{permission_id}")
    public RolePermission store(@PathVariable String role_id, @PathVariable String permission_id) {
        Role theRole = this.theRoleRepository.findById(role_id).orElse(null);
        Permission thePermission = this.thePermissionRepository.findById(permission_id).orElse(null);
        if (theRole != null && thePermission != null) {
            RolePermission newRolePermission = new RolePermission();
            newRolePermission.setRole(theRole);
            newRolePermission.setPermission(thePermission);
            return this.theRolePermissionRepository.save(newRolePermission);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("role/{role_id}/permissions")
    public void storeList(@RequestBody List<Permission> ListPermission, @PathVariable String role_id) {
        // List<RolePermission> savedRolePermissions = new ArrayList<>();
        for (Permission permission : ListPermission) {
            this.store(role_id, permission.get_id());
            // savedRolePermissions.add(savedPermission);
        }
        // return savedRolePermissions;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        RolePermission theRolePermission = this.theRolePermissionRepository.findById(id).orElse(null);
        if (theRolePermission != null) {
            this.theRolePermissionRepository.delete(theRolePermission);
        }
    }
}
