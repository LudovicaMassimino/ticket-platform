package it.ludo.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ludo.ticket_platform.model.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {
}
