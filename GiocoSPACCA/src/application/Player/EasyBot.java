package application.Player;

import application.Admin.BOTDIFF;
import application.Card.Card;

public class EasyBot extends PlayerInGame implements IBot {
	
	private final BOTDIFF difficulty = BOTDIFF.FACILE;
	
	public EasyBot(String botName) {
		super(botName);
	}
	
	@Override
	public Card playCard() {			// il bot in difficoltà facile gioca sempre la prima carta che ha in mano
		return hand[0];
	}

	public BOTDIFF getDifficulty() {
		return difficulty;
	}
}
