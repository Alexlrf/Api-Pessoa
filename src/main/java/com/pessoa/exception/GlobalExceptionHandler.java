package com.pessoa.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pessoa.model.dto.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<ErrorMessage> handleOrderNotFoundException(NoSuchElementException exception) {
		List<String> lista = new ArrayList<>();
		String erro = new String("Busca não encontrou resultado");
		lista.add(erro);
		
		return new ResponseEntity<>(new ErrorMessage(HttpStatus.NOT_FOUND.name(), lista,
				HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorMessage> handleOrderBadRequestException(MethodArgumentTypeMismatchException exception) {
		List<String> lista = new ArrayList<>();
		String erro = new String("Houve algum erro na informação dos parâmetros");
		lista.add(erro);
		
		return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.name(), lista,
				HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> handleAnyException(Exception exception) {
		List<String> lista = new ArrayList<>();
		String erro = new String(exception.getMessage());
		lista.add(erro);
		
		return new ResponseEntity<>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name(), lista,
						HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}