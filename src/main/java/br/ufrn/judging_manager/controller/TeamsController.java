package br.ufrn.judging_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.judging_manager.model.Team;
import br.ufrn.judging_manager.repository.TeamRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/teams")
public class TeamsController {

  @Autowired
  private TeamRepository teamRepository;

  @GetMapping
  public ResponseEntity<Page<Team>> index(Pageable pageable, @RequestParam(required = false) String name){
    if (name == null) {
      return ResponseEntity.status(HttpStatus.OK).body(teamRepository.findAll(pageable));
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(teamRepository.findAllByNameContaining(name, pageable));
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Team create(@RequestBody Team team) {
    return teamRepository.save(team);
  }

  @GetMapping("{id}")
  public ResponseEntity<Team> show(@PathVariable  long id){
      Team team = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not exist with id:" + id));
      return ResponseEntity.ok(team);
  }

  @PutMapping("{id}")
  public ResponseEntity<Team> update(@PathVariable long id,@RequestBody Team teamUpdated) {
    Team updateTeam = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not exist with id: " + id));

    updateTeam.setName(teamUpdated.getName());
    updateTeam.setInitials(teamUpdated.getInitials());
    // updateTeam.setJudge(teamUpdated.getJudge());

    teamRepository.save(updateTeam);

    return ResponseEntity.ok(updateTeam);  
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> destroy(@PathVariable long id){
    Team team = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not exist with id: " + id));

    teamRepository.delete(team);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
