package application.Card;

import application.Player.Player;
import application.Player.PlayerInGame;

public abstract class Card {


	private int code;
	
	public Card(int code) {
		this.code = code;
	}
	
	public abstract void effect(PlayerInGame p);
	
}
