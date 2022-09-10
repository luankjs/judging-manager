package br.ufrn.judging_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.judging_manager.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
