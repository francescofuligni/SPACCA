package application.Player;

import application.Card.*;

public class PlayerInGame extends Player {
	
	private int healthPoints;
	protected Card[] hand;				// scope protected per permetterne la visibilità nelle sottoclassi EasyBot e HardBot
	
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
	
	public Card playCard(int pos) {
		return hand[pos];
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
	
	@Override
	public String toString() {
		String s = super.toString() + "," + healthPoints;
		for(int i=0; i<hand.length; i++) {
			if(hand[i]!=null) {
				s+=("," + hand[i]);
			}
		}
		return s;
	}
}
