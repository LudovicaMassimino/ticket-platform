package it.ludo.ticket_platform.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1500)
    private String name;

    @Column(nullable = false)
    private LocalDateTime note_date;

    @ManyToOne
    @JoinColumn(name = "ticket_id" , nullable = false)
    @JsonBackReference
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "author_id" , nullable = false)
    @JsonBackReference
    private User author;

    // getter e setter

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public LocalDateTime getNote_date() {
        return note_date;
    }

    public void setNote_date(LocalDateTime note_date) {
        this.note_date = note_date;
    }
}
