package uk.org.hexsaw.logactaesque.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid Game Supplied")  
public class InvalidGameException extends RuntimeException {

}
