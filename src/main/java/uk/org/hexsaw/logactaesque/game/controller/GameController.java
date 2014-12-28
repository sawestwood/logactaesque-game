package uk.org.hexsaw.logactaesque.game.controller;

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
    
	@Autowired(required=true)
	private GameValidator gameValidator;
	
	@Autowired(required=true)
	private Rollable sevenSidedDice;
	
    @RequestMapping(value="/game/play", method = RequestMethod.POST)
    @ResponseBody
	public GameResult play(@RequestBody Game game) {      	
    	BeanPropertyBindingResult result = new BeanPropertyBindingResult(game, game.toString());
		ValidationUtils.invokeValidator(gameValidator, game, result);	
		if (result.hasErrors()) {
			throw new InvalidGameException();
		}	
		return new GameResult(game.getHomeTeam(), sevenSidedDice.roll(), game.getAwayTeam(), sevenSidedDice.roll());		
	}
    
    public void setGameValidator(GameValidator gameValidator) {
        this.gameValidator = gameValidator;
    }
   
    public void setSevenSidedDice(Rollable sevenSidedDice) {
        this.sevenSidedDice = sevenSidedDice;
    }
    
}
