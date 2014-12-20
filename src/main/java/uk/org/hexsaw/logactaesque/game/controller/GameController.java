package uk.org.hexsaw.logactaesque.game.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.org.hexsaw.logactaesque.game.model.Game;
import uk.org.hexsaw.logactaesque.game.model.GameResult;

@RestController
public class GameController {
	
    @RequestMapping(value="/game/play", method = RequestMethod.POST)
    @ResponseBody
	public GameResult play(@RequestBody Game game) {     
		return new GameResult(game.getHomeTeam(), 0, game.getAwayTeam(), 3);		
	}
	

}
