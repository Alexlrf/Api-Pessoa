package com.pessoa.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pessoa.model.dto.ErrorMessage;
import com.pessoa.model.dto.PessoaDto;
import com.pessoa.model.entiy.Pessoa;
import com.pessoa.service.PessoaService;

//@Validated
@RestController
@RequestMapping(value = "/apiPessoa/")
@CrossOrigin(origins = "*")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	Validator validator;

	private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

	@GetMapping(value = "/teste")
	public String getTestMessages() {
		logger.info("método - teste");

		return "TESTE OK!";
	}

	@GetMapping(value = "/buscaPessoas")
	public ResponseEntity<List<PessoaDto>> buscaPessoas() {
		logger.info("método - buscaPessoas");

		return pessoaService.buscaPessoas();
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<PessoaDto> buscaPessoa(@PathVariable Long id) {
		logger.info("método - buscaPessoa");

		return pessoaService.buscaPessoa(id);
	}

	@PostMapping(value = "/cadastraPessoa")
	public ResponseEntity<?> cadastraPessoa(@RequestBody Pessoa pessoa) {
		logger.info("método - cadastraPessoa");

		List<String> erros = validator.validate(pessoa).stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());

		if (erros.size() > 0) {
			return ResponseEntity.badRequest()
					.body(new ErrorMessage(HttpStatus.BAD_REQUEST.name(), erros, HttpStatus.BAD_REQUEST.value()));

		} else {
			return pessoaService.cadastraPessoa(pessoa);

		}

	}

	@PutMapping(value = "/id/{id}")
	public Pessoa alteraPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		logger.info("método - alteraPessoa");

		return null;

	}

}
