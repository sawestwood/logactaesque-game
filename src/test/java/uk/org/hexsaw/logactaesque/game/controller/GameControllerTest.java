package uk.org.hexsaw.logactaesque.game.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import uk.org.hexsaw.logactaesque.game.Application;
import uk.org.hexsaw.logactaesque.game.model.validation.GameValidator;
import uk.org.hexsaw.logactaesque.game.service.Rollable;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class )
public class GameControllerTest  {

    private MockMvc mockMvc;
    
    private static final String GAME_CONTENT_HOME_TEAM_MISSING = " {\"awayTeam\": \"WBA\"} ";
    private static final String GAME_CONTENT_AWAY_TEAM_MISSING = " {\"homeTeam\": \"Stockport County\"} ";
    private static final String GAME_CONTENT_ARSENAL_VS_WBA = " {\"homeTeam\": \"Arsenal\", \"awayTeam\": \"WBA\"} ";
 

    @Before
    public void setup() {
        
        GameController gameController = new GameController();
        gameController.setGameValidator(new GameValidator());
        
        Rollable sevenSidedDice = Mockito.mock(Rollable.class);
        gameController.setSevenSidedDice(sevenSidedDice);
        // Always return the result 0-2
        when(sevenSidedDice.roll()).thenReturn(0,2);
             
        this.mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
        
    }

    @Test
    public void thatGameControllerReturnsCorrectInformation() throws Exception {
  
        final String expectedResult = "{\"homeTeam\":\"Arsenal\",\"awayTeam\":\"WBA\",\"homeGoals\":0,\"awayGoals\":2}";
        mockMvc.perform(post("/game/play")
                        .content(GAME_CONTENT_ARSENAL_VS_WBA)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string(expectedResult));                    
    }
    
    @Test
    public void thatGameControllerReturns400IfNoHomeTeamSupplied() throws Exception {
        mockMvc.perform(post("/game/play")
                        .content(GAME_CONTENT_HOME_TEAM_MISSING)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());
    }
    
    @Test
    public void thatGameControllerReturns400IfNoAwayTeamSupplied() throws Exception {
        mockMvc.perform(post("/game/play")
                        .content(GAME_CONTENT_AWAY_TEAM_MISSING)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());
    }
}
