package application.Player;

import application.Admin.BOTDIFF;
import application.Card.Card;

public interface IBot {
	
	public Card playCard();		// restituisce la carta da giocare
	
	public BOTDIFF getDifficulty();
}
