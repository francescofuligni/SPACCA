package application.Card;

import application.Player.Player;

public abstract class Card {


	private int code;
	
	public Card(int c) {
		this.code = c;
	}
	
	public abstract void effect(Player p);
	
}
