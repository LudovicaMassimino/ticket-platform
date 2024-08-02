package it.ludo.ticket_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginForm() {
        return "/common/login";
    }

    @PostMapping("/authentication")
    public ModelAndView loginSubmit(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            if (auth.isAuthenticated()) {
                request.getSession().setAttribute("user", auth.getPrincipal());
                return new ModelAndView("redirect:/home"); // Reindirizza alla pagina principale
            }
        } catch (AuthenticationException e) {
            // Gestisci l'errore di autenticazione
            return new ModelAndView("login", "error", "Invalid username or password");
        }

        return new ModelAndView("login", "error", "Unknown error");
    }

}
