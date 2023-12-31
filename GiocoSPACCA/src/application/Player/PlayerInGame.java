package application.Player;

import java.util.ArrayList;
import application.Card.*;

public class PlayerInGame extends Player {
	
	private int healthPoints;
	protected ArrayList<Card> hand;				// scope protected per permetterne la visibilità nelle sottoclassi EasyBot e HardBot
	
	public PlayerInGame(String username) {
		super(username);
		this.healthPoints = 30;
		hand = new ArrayList<>();
	}
	public PlayerInGame(Player p) {
		super(p.getUsername(), p.getScore());
		this.healthPoints = 30;
		hand = new ArrayList<>();
	}
	
	public Card playCard(int pos) {
		return hand.get(pos);
	}
	
	// getters e setters
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int lifePoints) {
		this.healthPoints = lifePoints;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "," + healthPoints;
		for(int i=0; i<hand.size(); i++) {
			if(hand.get(i)!=null) {
				s+=("," + hand.get(i));
			}
		}
		return s;
	}
}
