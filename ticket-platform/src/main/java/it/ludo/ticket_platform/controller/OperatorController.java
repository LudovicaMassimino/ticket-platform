package it.ludo.ticket_platform.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ludo.ticket_platform.model.User;
import it.ludo.ticket_platform.repository.UserRepo;

@Controller
@RequestMapping ("/operator")
public class OperatorController {

    @Autowired 
    UserRepo userRepo;

    @GetMapping("/edit")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepo.findByUsername(username);

        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/edit")
    public String profile(@ModelAttribute User userForm, BindingResult bindingResult, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isPresent()) {
            User existingUser = user.get();

            // Aggiornamento dei dati dell'utente
            existingUser.setName(userForm.getName());
            existingUser.setUsername(userForm.getUsername());
            existingUser.setStatus(userForm.isStatus());

            // Controllo se ci sono errori di binding
            if (bindingResult.hasErrors()) {
                model.addAttribute("user", existingUser);
                return "user/profile";
            }

            // Salvataggio delle modifiche
            userRepo.save(existingUser);
        }

        return "redirect:/ticket/user";
    }
}
