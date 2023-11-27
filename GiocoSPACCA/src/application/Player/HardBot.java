package application.Player;

import java.util.Collection;
import application.Card.*;

public class HardBot extends PlayerInGame implements Bot{
	
	public HardBot(String botName) {
		super(botName);
	}
	
	public Card play(Collection<Card> hand) {
		Card[] cards = (Card[])hand.toArray();
		
		boolean flag = true;
		int imax = 0;
		for(int i=0; i<cards.length; i++) {
			if(cards[i] instanceof SpecialCard) {
				return cards[i];
			} else if(flag) {
				imax = i;
				flag = false;
			} else if(Math.abs(((NormalCard)cards[imax]).getDamage())<Math.abs(((NormalCard)cards[i]).getDamage())) {
				imax = i;
			}
		}
		
		return cards[imax];
	}
}