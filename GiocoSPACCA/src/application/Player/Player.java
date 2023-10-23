package application.Player;

public class Player {
	private String username;
	protected int lifePoints;			// perché non private?
	protected int currentScore;			// perché non private?
	
	public Player(String username,int lifePoints,int currentScore) {			// costruttore 1
		this.username=username;
		this.lifePoints=lifePoints;
		this.currentScore=currentScore;
		
	}
	public Player() {						// costruttore 2
		this.username="";
		this.lifePoints=0;
		this.currentScore=0;
		
	}
	public Player(String username) {		// costruttore 3
		this.username=username;
		this.lifePoints=0;
		this.currentScore=0;
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setLifePoints(int x) {
		this.lifePoints = x;
	}
	public int getLifePoints() {
		return this.lifePoints;
	}
	
	public void setCurrentScore(int x) {
		this.currentScore = x;
	}
	public int getCurrentScore() {
		return currentScore;
	}
}
