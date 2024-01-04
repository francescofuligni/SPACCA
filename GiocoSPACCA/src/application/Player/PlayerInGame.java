package application.Player;

import java.util.ArrayList;
import java.util.Collection;

import application.Card.*;

public class PlayerInGame extends Player {
	
	private int healthPoints;
	protected ArrayList<Card> hand;				// scope protected per permetterne la visibilità nelle sottoclassi EasyBot e HardBot
	public final int MAXHP = 30;				// valore massimo di punti salute
	
	public PlayerInGame(String username) {
		super(username);
		this.healthPoints = MAXHP;
		this.hand = new ArrayList<>();
	}
	public PlayerInGame(Player p) {
		super(p.getUsername(), p.getScore());
		this.healthPoints = MAXHP;
		this.hand = new ArrayList<>();
	}
	public PlayerInGame(String username, int healthPoints) {
		super(username);
		this.healthPoints = healthPoints;
		this.hand = new ArrayList<>();
	}
	
	public Card playCard(int i) {
		return hand.remove(i);
	}
	public void addCard(Card c) {
		hand.add(c);
	}
	
	
	// getters e setters
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int hp) {
		if(hp>MAXHP)
			this.healthPoints = MAXHP;
		else
			this.healthPoints = hp;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	public void setHand(Collection<Card> hand) {
		this.hand = (ArrayList<Card>)hand;
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
