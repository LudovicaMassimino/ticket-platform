package it.ludo.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ludo.ticket_platform.model.Note;
import it.ludo.ticket_platform.model.Ticket;

 public interface NoteRepo extends JpaRepository<Note, Integer> {

    // List<Note> findByTicketIdOrderByNoteDateDesc(Integer ticketId);
 }
