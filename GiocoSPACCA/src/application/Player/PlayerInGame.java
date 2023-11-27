package application.Player;

public class PlayerInGame extends Player {
	
	private int lifePoints;
	
	public PlayerInGame(String username, int totalScore) {
		super(username, totalScore);
		this.lifePoints = 30;
	}
	
	public PlayerInGame(Player p) {
		super(p.getUsername(), p.getScore());
		this.lifePoints = 30;
	}
	
	// getters e setters
	public int getLifePoints() {
		return lifePoints;
	}
	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
}
