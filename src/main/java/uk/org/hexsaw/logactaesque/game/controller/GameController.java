package uk.org.hexsaw.logactaesque.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.org.hexsaw.logactaesque.game.exception.InvalidGameException;
import uk.org.hexsaw.logactaesque.game.model.Game;
import uk.org.hexsaw.logactaesque.game.model.GameResult;
import uk.org.hexsaw.logactaesque.game.model.validation.GameValidator;
import uk.org.hexsaw.logactaesque.game.service.Rollable;

@RestController
public class GameController {
    
    private static final Logger  logger = LoggerFactory.getLogger(GameController.class);
    
	@Autowired(required=true)
	private GameValidator gameValidator;
	
	@Autowired(required=true)
	private Rollable sevenSidedDice;
	
    @RequestMapping(value="/game/play", method = RequestMethod.POST)
    @ResponseBody
	public GameResult play(@RequestBody Game game) {      	
    	BeanPropertyBindingResult validationResult = new BeanPropertyBindingResult(game, game.toString());
		ValidationUtils.invokeValidator(gameValidator, game, validationResult);	
		if (validationResult.hasErrors()) {
		    logger.error(validationResult.toString());
			throw new InvalidGameException();
		}	
		GameResult gameResult = new GameResult(game.getHomeTeam(), sevenSidedDice.roll(), game.getAwayTeam(), sevenSidedDice.roll());	
		logger.info(gameResult.toString());
		return gameResult;
	}
    
    public void setGameValidator(GameValidator gameValidator) {
        this.gameValidator = gameValidator;
    }
   
    public void setSevenSidedDice(Rollable sevenSidedDice) {
        this.sevenSidedDice = sevenSidedDice;
    }
    
}
