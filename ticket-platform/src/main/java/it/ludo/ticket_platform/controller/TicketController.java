package it.ludo.ticket_platform.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.ludo.ticket_platform.model.Note;
import it.ludo.ticket_platform.model.Ticket;
import it.ludo.ticket_platform.model.User;
import it.ludo.ticket_platform.repository.CategoryRepo;
import it.ludo.ticket_platform.repository.NoteRepo;
import it.ludo.ticket_platform.repository.TicketRepo;
import it.ludo.ticket_platform.repository.UserRepo;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketRepo ticketRepo;

    @Autowired
    NoteRepo noteRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/admin")
    public String index(Model model, @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "body", required = false) String body) {

        List<Ticket> ticketList = new ArrayList<>();

        if (title == null && body == null) {

            ticketList = ticketRepo.findAll();

        } else if (title == null) {
            ticketList = ticketRepo.findByBodyContainingIgnoreCase(body);
        } else {

            ticketList = ticketRepo.findByTitleContainingIgnoreCase(title);
        }

        model.addAttribute("list", ticketList);

        return "/admin/home_admin";
    }

    @GetMapping("/user")
    public String getMyTickets(Model model, @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "body", required = false) String body) {

        List<Ticket> ticketList = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (title == null && body == null) {

            ticketList = ticketRepo.findByUserUsername(username);

        } else if (title == null) {
            ticketList = ticketRepo.findByUserUsernameAndBodyContainingIgnoreCase(username , body);
        } else {

            ticketList = ticketRepo.findByUserUsernameAndTitleContainingIgnoreCase(username , title);
        }
        model.addAttribute("list", ticketList);

        return "/user/home_user";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("ticket", ticketRepo.getReferenceById(id));

        return "/common/info";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ticket", ticketRepo.getReferenceById(id));
        model.addAttribute("status", Ticket.Status.values());
        return "/common/edit";
    }

    @PostMapping("/{id}/edit")
    public String Update(@PathVariable("id") Integer id, @Valid @ModelAttribute("ticket") Ticket ticketUpdate,
            BindingResult bindingresult,
            Model model) {

        if (bindingresult.hasErrors()) {

            return "/common/edit";
        }
        Ticket existingTicket = ticketRepo.getReferenceById(id);

        existingTicket.setTitle(ticketUpdate.getTitle());
        existingTicket.setBody(ticketUpdate.getBody());
        existingTicket.setStatus(ticketUpdate.getStatus());

        ticketRepo.save(existingTicket);

        return "redirect:/ticket/{id}";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {

        ticketRepo.deleteById(id);

        return "redirect:/ticket/admin";
    }

    @GetMapping("/{id}/createNote")
    public String getCreateNote(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("ticketId", id);
        return "/common/createNote";
    }

    @PostMapping("/{id}/createNote")
    public String createNote(@PathVariable("id") Integer id, @Valid Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ticketId", id);
            return "/common/createNote";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepo.findByUsername(username);
        User loggedUser = user.get();

        Ticket ticket = ticketRepo.getReferenceById(id)
;
        note.setTicket(ticket)
;
        note.setNote_date(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        note.setAuthor(loggedUser);
        noteRepo.save(note);

        return "redirect:/ticket/" + id;
    }

    @GetMapping("/admin/create")
    public String create(Model model) {

        model.addAttribute("ticket", new Ticket());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("category", categoryRepo.findAll());

        return "/admin/create";
    }

    @PostMapping("/admin/create")
    public String store(@Valid @ModelAttribute("ticket") Ticket ticketForm, BindingResult bindingresult, Model model) {

        if (bindingresult.hasErrors()) {

            return "/admin/create";
        }

        ticketForm.setTicket_date(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        ticketForm.setStatus(Ticket.Status.DA_FARE);

        ticketRepo.save(ticketForm);

        return "redirect:/ticket/admin";

    }
}