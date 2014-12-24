logactaesque-game
=================

A simple REST service which plays a simple football game between two teams. To build and run the REST service locally with Maven, use: 

mvn package && java -jar target/logactaesque-game-0.0.1-SNAPSHOT.jar

Games are played by POSTing a request with a JSON body at http://localhost:8080/game/play, with the request comprising two teams:

{"homeTeam": "Arsenal", "awayTeam": "WBA"}

If the supplied game is valid (i.e. both home and away teams have been supplied), then the service returns a JSON response akin to the following:

{
 "homeTeam": "Arsenal",
 "awayTeam": "WBA",
 "homeGoals": 0,
 "awayGoals": 3
}

If an invalid game is provided (for example no home team is supplied), it merely returns a HTTP 400 error code.
