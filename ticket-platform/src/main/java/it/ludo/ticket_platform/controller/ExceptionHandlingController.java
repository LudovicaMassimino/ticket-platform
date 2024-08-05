package it.ludo.ticket_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlingController {

    // @ExceptionHandler(AccessDeniedException.class)
    @GetMapping("/403")
    public String handleAccessDeniedException() {
        return "/common/login"; // Nome della pagina di errore personalizzata
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException() {
        return "error"; // Pagina di errore generica
    }

}