package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.User;
// import com.mssecurity.mssecurity.Repositories.RoleRepository;
import com.mssecurity.mssecurity.Repositories.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("private/users")
public class UsersController {
    @Autowired
    private UserRepository theUserRepository;
    // @Autowired
    // private RoleRepository theRoleRepository;

    @GetMapping("")
    public List<User> index() {return this.theUserRepository.findAll();}

    @GetMapping("{id}")
    public User show(@PathVariable String id) {
        User theUser = this.theUserRepository
                .findById(id)
                .orElse(null);
        return theUser;
    }

    @PutMapping("{id}")
    public User update(@PathVariable String id, @RequestBody User theNewUser) {
        User theActualUser = this.theUserRepository
                .findById(id)
                .orElse(null);
        if (theActualUser != null) {
            theActualUser.setName(theNewUser.getName());
            theActualUser.setEmail(theNewUser.getEmail());
            return this.theUserRepository.save(theActualUser);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        User theUser = this.theUserRepository
                .findById(id)
                .orElse(null);
        if (theUser != null) {
            this.theUserRepository.delete(theUser);
        }
    }


}
