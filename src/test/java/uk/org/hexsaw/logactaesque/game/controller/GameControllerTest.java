package uk.org.hexsaw.logactaesque.game.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import uk.org.hexsaw.logactaesque.game.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class GameControllerTest  {

    private MockMvc mockMvc;
    
    private static final String GAME_CONTENT_HOME_TEAM_MISSING = " {\"awayTeam\": \"WBA\"} ";
    private static final String GAME_CONTENT_ARSENAL_VS_WBA = " {\"homeTeam\": \"Arsenal\", \"awayTeam\": \"WBA\"} ";

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new GameController()).build();
    }

    @Test
    public void thatGameControllerReturnsCorrectInformation() throws Exception {
        final String expectedResult = "{\"homeTeam\":\"Arsenal\",\"awayTeam\":\"WBA\",\"homeGoals\":0,\"awayGoals\":3}";
        mockMvc.perform(post("/game/play")
                        .content(GAME_CONTENT_ARSENAL_VS_WBA)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string(expectedResult));                    
    }
    
    @Test
    public void thatGameControllerReturns400IfIncorrectInformation() throws Exception {
        mockMvc.perform(post("/game/play")
                        .content(GAME_CONTENT_HOME_TEAM_MISSING)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is4xxClientError());
    }
}
