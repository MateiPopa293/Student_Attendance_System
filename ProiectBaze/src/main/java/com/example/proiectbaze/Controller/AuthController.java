/**
 * Controller pentru autentificare
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */


package com.example.proiectbaze.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}