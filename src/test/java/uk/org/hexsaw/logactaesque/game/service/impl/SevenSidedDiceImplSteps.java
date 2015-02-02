package uk.org.hexsaw.logactaesque.game.service.impl;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import uk.org.hexsaw.logactaesque.game.service.Rollable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SevenSidedDiceImplSteps {
	
	private Rollable dice; 

	@Given("^a seven-sided dice$")
	public void aSevenSidedDice() throws Throwable {
		 dice = new SevenSidedDice();
	}

	@When("^I roll the seven-sided dice$")
	public int iRollTheSevenSidedDice() throws Throwable {
		return dice.roll();
	}

	@Then("^I should get number between 0 and 6$")
	public void iShouldGetANumberBetween0and6() throws Throwable {
		assertThat(iRollTheSevenSidedDice(), anyOf(is(0), is(1), is(2), is(3), is(4), is(5), is(6)));
	}

}
