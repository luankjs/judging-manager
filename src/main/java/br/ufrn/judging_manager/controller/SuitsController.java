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

import br.ufrn.judging_manager.model.Suit;
import br.ufrn.judging_manager.repository.SuitRepository;

@RestController
@RequestMapping("/suits")
public class SuitsController {

  @Autowired
  private SuitRepository suitRepository;

  @GetMapping
  public List<Suit> index() {
    return suitRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Suit create(@RequestBody Suit suit) {
    return suitRepository.save(suit);
  }

  @GetMapping("/suits/{id}")
  public ResponseEntity<Suit> getSuitById(@PathVariable  long id){
      Suit suit = suitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Suit not exist with id:" + id));
      return ResponseEntity.ok(suit);
  }

  @PutMapping("/suits/{id}")
  public ResponseEntity<Suit> update(@PathVariable long id,@RequestBody Suit suitUpdated) {
    Suit updateSuit = suitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Suit not exist with id: " + id));

    updateSuit.setNumber(suitUpdated.getNumber());
    updateSuit.setExcerpt(suitUpdated.getExcerpt());
    updateSuit.setJudge(suitUpdated.getJudge());
    updateSuit.setParts(suitUpdated.getParts());

    suitRepository.save(updateSuit);

    return ResponseEntity.ok(updateSuit);  
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> destroy(@PathVariable long id){
    Suit suit = suitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Suit not exist with id: " + id));

    suitRepository.delete(suit);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
