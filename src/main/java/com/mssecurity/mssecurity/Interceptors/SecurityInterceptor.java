package com.mssecurity.mssecurity.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mssecurity.mssecurity.Models.Permission;
import com.mssecurity.mssecurity.Models.Role;
import com.mssecurity.mssecurity.Models.RolePermission;
import com.mssecurity.mssecurity.Models.User;
import com.mssecurity.mssecurity.Repositories.PermissionRepository;
import com.mssecurity.mssecurity.Repositories.RolePermissionRepository;
import com.mssecurity.mssecurity.Repositories.UserRepository;
import com.mssecurity.mssecurity.Services.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PermissionRepository thePermissionRepository;

    @Autowired
    private UserRepository theUserRepository;

    @Autowired
    private RolePermissionRepository theRolePermissionRepository;

    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        boolean success = true;

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {

            String token = authorizationHeader.substring(BEARER_PREFIX.length());
            System.out.println("Bearer Token: " + token);
            success = jwtService.validateToken(token);
            String url2 = request.getRequestURI();
            String method2 = request.getMethod();
            System.out.println("Antes URL " + url2 + " metodo " + method2);
            url2 = url2.replaceAll("[0-9a-fA-F]{24}", "?");
            System.out.println("URL " + url2 + " metodo " + method2);
            User theUserFromToken = jwtService.getUserFromToken(token);

            if (theUserFromToken != null) {
                System.out.println(
                        "Nombre del usuario " + theUserFromToken.getName() + " id " + theUserFromToken.get_id());
                User theUser = this.theUserRepository.findById(theUserFromToken.get_id()).orElse(null);

                Role theRole = theUser.getRole();
                String url = request.getRequestURI();
                String method = request.getMethod();
                System.out.println("Antes URL " + url + " metodo " + method);
                url = url.replaceAll("[0-9a-fA-F]{24}", "?");
                System.out.println("URL " + url + " metodo " + method);

                Permission thePermission = this.thePermissionRepository.getPermission(url, method);

                if (theRole != null && thePermission != null) {
                    System.out.println("Rol " + theRole.getname() + " Permission " + thePermission.getUrl());
                    RolePermission theRolePermission = this.theRolePermissionRepository
                            .getRolePermission(theRole.get_id(), thePermission.get_id());
                    if (theRolePermission == null) {
                        success = false;
                    }
                } else {
                    success = false;
                }
            }
        } else {
            success = false;
        }
        return success;
    }
}