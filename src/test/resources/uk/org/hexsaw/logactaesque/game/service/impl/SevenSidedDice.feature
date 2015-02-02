Feature: SevenSidedDice
	Scenario: Roll a seven-sided dice
		Given a seven-sided dice
		When I roll the seven-sided dice
		Then I should get number between 0 and 6
