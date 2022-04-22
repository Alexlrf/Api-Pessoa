package com.pessoa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/apiPessoa")
public class PessoaController {
	
	private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);
	
	@GetMapping(value = "/teste")
	public String getTestMessages() {
		logger.info("método - teste");
		
		return "OK!";
	}
	
	

}
