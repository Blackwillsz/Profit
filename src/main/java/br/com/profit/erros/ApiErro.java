package br.com.profit.erros;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import antlr.TokenStreamException;

/**
 * @author willian.souza
 *
 */

@RestControllerAdvice
public class ApiErro {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 409
	public String badRequest(SQLException mensagem ) {
		return mensagem.getMessage();
	}

	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) //404
	public String notFound(ResponseStatusException mensagem) {
		return mensagem.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String internalServerError(MethodArgumentNotValidException mensagem) {
		return mensagem.getMessage();
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public String internalServerError(HttpStatus badRequest, String erro) {
//		return "usuario nao encontrado";
//	}

	@ExceptionHandler(antlr.TokenStreamException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String unauthorized(TokenStreamException mensagem) {
		return mensagem.getMessage();
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) //405
	public String methodNotAllowed(HttpRequestMethodNotSupportedException mensagem) {
		return mensagem.getMessage();
	}

}
