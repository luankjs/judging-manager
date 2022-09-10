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

import br.ufrn.judging_manager.model.Judge;
import br.ufrn.judging_manager.repository.JudgeRepository;

@RestController
@RequestMapping("/judges")
public class JudgesController {

  @Autowired
  private JudgeRepository judgeRepository;

  @GetMapping
  public List<Judge> index() {
    return judgeRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Judge create(@RequestBody Judge judge) {
    return judgeRepository.save(judge);
  }

  @GetMapping("/judges/{id}")
  public ResponseEntity<Judge> getJudgeById(@PathVariable  long id){
    Judge judge = judgeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Judge not exist with id:" + id));
    return ResponseEntity.ok(judge);
  }

  @PutMapping("/judges/{id}")
  public ResponseEntity<Judge> update(@PathVariable long id,@RequestBody Judge judgeUpdated) {
    Judge updateJudge = judgeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Judge not exist with id: " + id));

    updateJudge.setName(judgeUpdated.getName());

    judgeRepository.save(updateJudge);

    return ResponseEntity.ok(updateJudge);  
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> destroy(@PathVariable long id){
    Judge judge = judgeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Judge not exist with id: " + id));

    judgeRepository.delete(judge);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
