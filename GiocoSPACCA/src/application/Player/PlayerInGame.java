package application.Player;

import application.Card.*;

public class PlayerInGame extends Player {
	
	private int healthPoints;
	private Card[] hand;
	
	public PlayerInGame(String username) {
		super(username);
		this.healthPoints = 30;
		hand = new Card[5];
	}
	public PlayerInGame(Player p) {
		super(p.getUsername(), p.getScore());
		this.healthPoints = 30;
		hand = new Card[5];
	}
	
	// getters e setters
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int lifePoints) {
		this.healthPoints = lifePoints;
	}
	
	public Card[] getHand() {
		return hand;
	}
	public void setHand(Card[] hand) {
		this.hand = hand;
	}
}
