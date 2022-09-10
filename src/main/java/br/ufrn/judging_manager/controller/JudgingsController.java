package br.ufrn.judging_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.judging_manager.model.Judging;
import br.ufrn.judging_manager.repository.JudgingRepository;

@RestController
@RequestMapping("/judgings")
public class JudgingsController {

  @Autowired
  private JudgingRepository judgingRepository;

  @GetMapping
  public List<Judging> index() {
    return judgingRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Judging create(@RequestBody Judging judging) {
    return judgingRepository.save(judging);
  }

  @GetMapping("/judgings/{id}")
  public ResponseEntity<Judging> getJudgingById(@PathVariable  long id){
      Judging judging = judgingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Judging not exist with id:" + id));
      return ResponseEntity.ok(judging);
  }

  @PutMapping("/judgings/{id}")
  public ResponseEntity<Judging> update(@PathVariable long id,@RequestBody Judging judgingUpdated) {
    Judging updateJudging = judgingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Judging not exist with id: " + id));

    updateJudging.setDate(judgingUpdated.getDate());
    updateJudging.setTime(judgingUpdated.getTime());
    updateJudging.setTeam(judgingUpdated.getTeam());

    judgingRepository.save(updateJudging);

    return ResponseEntity.ok(updateJudging);  
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> destroy(@PathVariable long id){
    Judging judging = judgingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Judging not exist with id: " + id));

    judgingRepository.delete(judging);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
