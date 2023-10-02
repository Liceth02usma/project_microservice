package com.mssecurity.mssecurity.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.Role;
import com.mssecurity.mssecurity.Repositories.RoleRepository;

@CrossOrigin
@RestController
@RequestMapping("private/roles")
public class RoleController {
    @Autowired
    private RoleRepository theRoleRepository;

    @GetMapping("")
    public List<Role> index() {
        return this.theRoleRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Role store(@RequestBody Role newRole) {
        return this.theRoleRepository.save(newRole);
    }

    @GetMapping("{id}")
    public Role show(@PathVariable String id) {
        Role theRole = this.theRoleRepository.findById(id).orElse(null);
        return theRole;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("list")
    public List<Role> storeList(@RequestBody List<Role> ListRole) {
        List<Role> savedRoles = new ArrayList<>();
        for (Role Role : ListRole) {
            Role savedRole = this.theRoleRepository.save(Role);
            savedRoles.add(savedRole);
        }
        return savedRoles;
    }

    @PutMapping("{id}")
    public Role update(@PathVariable String id, @RequestBody Role theNewRole) {
        Role theActualRole = this.theRoleRepository.findById(id).orElse(null);
        if (theActualRole != null) {
            theActualRole.setName(theNewRole.getname());
            theActualRole.setdescription(theNewRole.getdescription());
            return this.theRoleRepository.save(theActualRole);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Role theRole = this.theRoleRepository
                .findById(id)
                .orElse(null);
        if (theRole != null) {
            this.theRoleRepository.delete(theRole);
        }
    }
}
