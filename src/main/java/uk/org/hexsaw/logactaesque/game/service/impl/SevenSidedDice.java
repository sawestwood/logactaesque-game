package uk.org.hexsaw.logactaesque.game.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import uk.org.hexsaw.logactaesque.game.service.Rollable;

public class SevenSidedDice implements Rollable {

    private static List<Integer> FACES = Arrays.asList(new Integer[] {
        0, 1, 2, 3, 4, 5, 6
    });

    public int roll() {
        return FACES.get(new Random().nextInt(FACES.size()));
    }

}
