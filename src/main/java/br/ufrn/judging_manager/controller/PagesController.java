package br.ufrn.judging_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagesController {
  @GetMapping("/")
  public String home() {
    return "Gestor de Pautas de Sessões de Julgamento do TST";
  }
}
