package it.ludo.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ludo.ticket_platform.model.Note;

 public interface NoteRepo extends JpaRepository<Note, Integer> {

 }
