package br.ufrn.judging_manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.judging_manager.model.Judge;

@Repository
public interface JudgeRepository extends JpaRepository<Judge, Long> {
  Page<Judge> findAllByNameContaining(String name, Pageable pageable);
}
