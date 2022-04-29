package com.pessoa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pessoa.model.dto.PessoaDto;
import com.pessoa.model.entiy.Pessoa;
import com.pessoa.repository.PessoaRepository;

@Service
public class PessoaService {
	
	private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public ResponseEntity<List<PessoaDto>> buscaPessoas() {
		logger.info("método - buscaPessoas");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");		
		List<Pessoa> pessoas = pessoaRepository.findAll();
		List<PessoaDto> pessoasDto = new ArrayList<>();
		
		pessoas.forEach(pessoa -> pessoasDto.add(new PessoaDto(pessoa)));
		
		return ResponseEntity.ok().headers(headers).body(pessoasDto);
	}

	public ResponseEntity<PessoaDto> buscaPessoa(Long id) {
		logger.info("método - buscaPessoa");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		
		PessoaDto pessoaDto = new PessoaDto(pessoaRepository.findById(id).get());
		
		return ResponseEntity.ok().headers(headers).body(pessoaDto);
	}
	
	public ResponseEntity<PessoaDto> cadastraPessoa(Pessoa pessoa) {
		logger.info("método - cadastraPessoa");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		
		PessoaDto pessoaDto = new PessoaDto(pessoaRepository.save(pessoa));

		return ResponseEntity.ok().headers(headers).body(pessoaDto);
	}


}
