package it.ludo.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ludo.ticket_platform.model.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

    public List<Ticket> findByTitleContainingIgnoreCase(String title);

    public List<Ticket> findByBodyContainingIgnoreCase(String body);
    
}
