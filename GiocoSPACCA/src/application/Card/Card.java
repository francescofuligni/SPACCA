package application.Card;

import application.Player.Player;

public abstract class Card {


	private int code;
	
	public Card(int code) {
		this.code = code;
	}
	
	public abstract void effect(Player p);
	
}
