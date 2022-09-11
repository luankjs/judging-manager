package br.ufrn.judging_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JudgingManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(JudgingManagerApplication.class, args);
	}
}
