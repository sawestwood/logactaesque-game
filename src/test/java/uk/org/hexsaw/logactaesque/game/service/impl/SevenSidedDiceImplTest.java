package uk.org.hexsaw.logactaesque.game.service.impl;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import uk.org.hexsaw.logactaesque.game.service.Rollable;

public class SevenSidedDiceImplTest {
    
    private Rollable dice = new SevenSidedDice();

    @Test
    public void onlyGeneratesNumberBetweenZeroAndSix() {
        for (int i = 0; i < 25; ++i) {
            assertThat(dice.roll(), anyOf(is(0), is(1), is(2), is(3), is(4), is(5), is(6)));
        }
    }    


}
