package application.Player;

public class PlayerInGame extends Player {
	
	private int lifePoints;
	// private int currentScore;						// non necessario
	
	public PlayerInGame(String username, int totalScore, int lifePoints) {
		super(username, totalScore);
		this.lifePoints = lifePoints;
	}
	
	// getters e setters
	public int getLifePoints() {
		return lifePoints;
	}
	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
}
