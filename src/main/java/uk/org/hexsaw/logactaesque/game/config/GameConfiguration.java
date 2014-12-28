package uk.org.hexsaw.logactaesque.game.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.org.hexsaw.logactaesque.game.model.validation.GameValidator;
import uk.org.hexsaw.logactaesque.game.service.Rollable;
import uk.org.hexsaw.logactaesque.game.service.impl.SevenSidedDiceImpl;

@Configuration
@EnableAutoConfiguration
public class GameConfiguration {
    
    @Bean
    public GameValidator gameValidator() {
        return new GameValidator();
    }
    
    @Bean(name="sevenSidedDice")
    public Rollable sevenSidedDice() {
        return new SevenSidedDiceImpl();
    }

}
