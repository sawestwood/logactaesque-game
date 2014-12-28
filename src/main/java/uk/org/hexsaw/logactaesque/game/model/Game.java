package uk.org.hexsaw.logactaesque.game.model;

public class Game {
	
	private String homeTeam;
	private String awayTeam;
	
	public Game() {
	    
	}
	
	public Game(String homeTeam, String awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

    @Override
    public String toString() {
        return "Game [homeTeam=" + homeTeam + " vs. awayTeam=" + awayTeam + "]";
    }
	
	
	
	

}
