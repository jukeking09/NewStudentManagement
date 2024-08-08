package com.zach.NewStudentManagement.helper;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHelper {

    public static boolean authenticated() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
