package eu.epitech.businesscard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.epitech.businesscard.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
