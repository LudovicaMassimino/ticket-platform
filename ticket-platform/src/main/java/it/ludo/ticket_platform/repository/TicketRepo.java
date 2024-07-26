package it.ludo.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.ludo.ticket_platform.model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {

    public List<Ticket> findByTitleContainingIgnoreCase(String title);

    public List<Ticket> findByBodyContainingIgnoreCase(String body);

    @Query("SELECT t FROM Ticket t WHERE t.category.name = :categoryName")
    List<Ticket> findByCategoryName(@Param("categoryName") String categoryName);

    List<Ticket> findByStatus(Ticket.Status status);

}