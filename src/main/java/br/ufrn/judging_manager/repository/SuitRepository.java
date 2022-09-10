package br.ufrn.judging_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.judging_manager.model.Suit;

@Repository
public interface SuitRepository extends JpaRepository<Suit, Long> {
}
