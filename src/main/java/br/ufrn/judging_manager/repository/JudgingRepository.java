package br.ufrn.judging_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.judging_manager.model.Judging;

@Repository
public interface JudgingRepository extends JpaRepository<Judging, Long> {
}
