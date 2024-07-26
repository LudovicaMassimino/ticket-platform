package it.ludo.ticket_platform.service;

import java.util.List;
import java.util.Optional;

import it.ludo.ticket_platform.model.Ticket;



public interface TicketService {

    public List<Ticket> findAll();

    public Optional<Ticket> findbyId(Integer id);

    public List<Ticket> findByCategoryName(String categoryName);

    public List<Ticket> findByStatus(Ticket.Status status);
}