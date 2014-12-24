package uk.org.hexsaw.logactaesque.game.model.validation;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import uk.org.hexsaw.logactaesque.game.model.Game;

public class GameValidatorTest {

	private Validator validator;

	@Before
	public void setUp() {
		validator = new GameValidator();
	}

	@Test
	public void canCreateGameValidator() {
		assertThat(validator, notNullValue());
	}

	@Test
	public void canValidateAValidGame() {
		Game game = new Game("WBA", "Arsenal");
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(game, game.toString());
		ValidationUtils.invokeValidator(validator, game, result);
		assertThat(result.getErrorCount(), equalTo(0));
	}
	
	@Test
	public void thatErrorRaisedWhenNoHomeTeamSpecified() {
		Game game = new Game("", "Arsenal");
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(game, game.toString());
		ValidationUtils.invokeValidator(validator, game, result);
		assertThat(result.getErrorCount(), equalTo(1));
	}
	
	@Test
	public void thatErrorRaisedWhenNoAwayTeamSpecified() {
		Game game = new Game("WBA", "");
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(game, game.toString());
		ValidationUtils.invokeValidator(validator, game, result);
		assertThat(result.getErrorCount(), equalTo(1));
	}

}
