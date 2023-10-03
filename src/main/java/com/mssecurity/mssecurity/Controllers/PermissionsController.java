package com.mssecurity.mssecurity.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.Permission;
import com.mssecurity.mssecurity.Repositories.PermissionRepository;

@CrossOrigin
@RestController
@RequestMapping("private/permissions")
public class PermissionsController {
    @Autowired
    private PermissionRepository thePermissionRepository;

    @GetMapping("")
    public List<Permission> index() {
        return this.thePermissionRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permission store(@RequestBody Permission newPermission) {
        return this.thePermissionRepository.save(newPermission);
    }

    @GetMapping("{id}")
    public Permission show(@PathVariable String id) {
        Permission thePermission = this.thePermissionRepository.findById(id).orElse(null);
        return thePermission;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("list")
    public List<Permission> storeList(@RequestBody List<Permission> ListPermission) {
        List<Permission> savedPermissions = new ArrayList<>();
        for (Permission Permission : ListPermission) {
            Permission savedPermission = this.thePermissionRepository.save(Permission);
            savedPermissions.add(savedPermission);
        }
        return savedPermissions;
    }

    @PutMapping("{id}")
    public Permission update(@PathVariable String id, @RequestBody Permission theNewPermission) {
        Permission theActualPermission = this.thePermissionRepository.findById(id).orElse(null);
        if (theActualPermission != null) {
            theActualPermission.setUrl(theNewPermission.getUrl());
            theActualPermission.setMethod(theNewPermission.getMethod());
            theActualPermission.setMenuItem(theNewPermission.getMenuItem());
            return this.thePermissionRepository.save(theActualPermission);
        } else {
            return null;
        }
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Permission thePermission = this.thePermissionRepository.findById(id).orElse(null);
        if (thePermission != null) {
            this.thePermissionRepository.delete(thePermission);
        }
    }

}
