package it.ludo.ticket_platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ludo.ticket_platform.model.Ticket;
import it.ludo.ticket_platform.repository.TicketRepo;



@Service
public class TicketServiceImplements implements TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Override
    public List<Ticket> findAll() {
        
        return ticketRepo.findAll();
    }

    @Override
    public Optional<Ticket> findbyId(Integer id) {
        
        return ticketRepo.findById(id)
;
    }

    @Override
    public List<Ticket> findByCategoryName(String categoryName) {
        return ticketRepo.findByCategoryName(categoryName);
    }

    @Override
    public List<Ticket> findByStatus(Ticket.Status status) {
        return ticketRepo.findByStatus(status);
    }
}  