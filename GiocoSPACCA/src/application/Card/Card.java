package application.Card;

import application.Player.*;

public abstract class Card {

	private int code;
	
	public Card(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public abstract void effect(PlayerInGame p);
	
}
