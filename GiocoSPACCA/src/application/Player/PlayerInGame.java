package application.Player;

public class PlayerInGame extends Player {
	
	private int healthPoints;
	
	public PlayerInGame(String username) {
		super(username);
		this.healthPoints = 30;
	}
	public PlayerInGame(Player p) {
		super(p.getUsername(), p.getScore());
		this.healthPoints = 30;
	}
	
	// getters e setters
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int lifePoints) {
		this.healthPoints = lifePoints;
	}
}
