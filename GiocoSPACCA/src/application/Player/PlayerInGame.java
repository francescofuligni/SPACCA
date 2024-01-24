package application.Player;

import java.util.ArrayList;

import application.Card.*;

public class PlayerInGame extends Player {		// superclasse di Bot
	
	protected int healthPoints;
	protected ArrayList<Card> hand;
	public final int MAXHP = 20;				// valore massimo di punti salute
	
	public PlayerInGame(String username) {
		super(username);
		this.healthPoints = MAXHP;
		this.hand = new ArrayList<>();
	}
	public PlayerInGame(String username, int healthPoints) {
		super(username);
		this.healthPoints = healthPoints;
		this.hand = new ArrayList<>();
	}
	public PlayerInGame(String username, int healthPoints, ArrayList<Card> hand) {
		super(username);
		this.healthPoints = healthPoints;
		this.hand = hand;
	}
	
	public Card getCard(int i) {
		// restituisce la carta in posizione i e la rimuove dalla mano
		return hand.remove(i);
	}
	public void addCard(Card c) {
		// aggiunge una carta alla mano, nella prima posizione
		hand.add(0,c);
	}
	
	public boolean hasImprevisti() {
		// controlla se il giocatore ha imprevisti in mano
		for(Card c: hand)
			if(c.getCode()<=16 && c.getCode()>=13)
				return true;
		
		return false;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int hp) {
		if(hp>MAXHP+5)
			this.healthPoints = MAXHP+5;
		else
			this.healthPoints = hp;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	@Override
	public String toString() {
		// restituisce una stringa con le informazioni del giocatore
		// --> coerente con il formato (.csv per il salvataggio su file)
		
		String s =  super.toString() + "," + healthPoints;
		for(int i=0; i<hand.size(); i++)
			if(hand.get(i)!=null)
				s+=("," + hand.get(i));
				
		return s;
	}
}
