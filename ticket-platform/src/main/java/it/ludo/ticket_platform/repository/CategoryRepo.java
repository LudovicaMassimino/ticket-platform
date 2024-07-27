package it.ludo.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ludo.ticket_platform.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

