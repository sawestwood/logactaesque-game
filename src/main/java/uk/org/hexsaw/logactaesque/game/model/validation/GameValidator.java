package uk.org.hexsaw.logactaesque.game.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import uk.org.hexsaw.logactaesque.game.model.Game;

public class GameValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Game.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "homeTeam", "homeTeam.empty");
		ValidationUtils.rejectIfEmpty(e, "awayTeam", "awayTeam.empty");
	}

}
