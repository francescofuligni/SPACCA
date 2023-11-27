package application.Player;

import java.util.Collection;
import application.Card.Card;

public class EasyBot extends PlayerInGame implements Bot {
	
	public EasyBot(String botName) {
		super(botName);
	}
	
	public Card play(Collection<Card> hand) {			// il bot in difficoltà facile gioca sempre la prima carta che ha in mano
		Card[] cards = (Card[])hand.toArray();
		return cards[0];
	}
}
