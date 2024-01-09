package application.Player;

import java.util.Random;

import application.Admin.BOTDIFF;
import application.Card.Card;

public class EasyBot extends PlayerInGame implements IBot {
	
	private final BOTDIFF difficulty = BOTDIFF.FACILE;
	
	public EasyBot(String botName) {
		super(botName);
	}
	public EasyBot(String botName, int healthPoints) {
		super(botName, healthPoints);
	}
	
	@Override
	public Card playCard() {				// il bot in difficoltà facile gioca casualmente una carta
		if(hasImprevisti())
			for(Card c: hand)
				if(c.getCode()<=16 && c.getCode()>=13)
					return hand.remove(hand.indexOf(c));
		
		Random rand = new Random();
		return hand.remove(rand.nextInt(5));
	}
	
	public BOTDIFF getDifficulty() {
		return difficulty;
	}
}


