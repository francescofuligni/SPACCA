package application.Player;

public class Player {
	private String Username;
	protected int LifePoints;
	protected int CurrentScore;
	
	public Player(String Username,int LifePoints,int CurrentScore) {
		this.Username=Username;
		this.LifePoints=LifePoints;
		this.CurrentScore=CurrentScore;
		
	}
	public Player() {
		this.Username="";
		this.LifePoints=0;
		this.CurrentScore=0;
		
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		this.Username = username;
	}
}
