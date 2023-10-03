package com.mssecurity.mssecurity.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.User;
import com.mssecurity.mssecurity.Repositories.UserRepository;
import com.mssecurity.mssecurity.Services.JwtService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("public/security")
public class SecurityController {

    @Autowired
    private UserRepository theUserRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("log-in")
    public String login(@RequestBody User theUser, final HttpServletResponse response) throws IOException {
        String token = "";
        User actualUser = this.theUserRepository.getUserByID(theUser.get_id());
        if (actualUser != null && actualUser.getPassword().equals(theUser.getPassword())) {
            token = jwtService.generateToken(actualUser);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return token;
    }

}
