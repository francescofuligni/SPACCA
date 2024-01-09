package application.Player;
import application.Admin.BOTDIFF;
import application.Card.*;

public class HardBot extends PlayerInGame implements IBot{
	
	private final BOTDIFF difficulty = BOTDIFF.DIFFICILE;
	
	public HardBot(String botName) {
		super(botName);
	}
	public HardBot(String botName, int healthPoints) {
		super(botName, healthPoints);
	}
	
	@Override
	public Card playCard() {		// restituisce la carta da giocare
		if(hasImprevisti())
			for(Card c: hand)
				if(c.getCode()<=16 && c.getCode()>=13)
					return c;
		
		boolean flag = true;
		int imax = 0;
		for(int i=0; i<hand.size(); i++) {
			if(hand.get(i) instanceof SpecialCard) {
				return hand.get(i);
			} else if(flag) {
				imax = i;
				flag = false;
			} else if(Math.abs(((NormalCard)hand.get(imax)).getDamage())<Math.abs(((NormalCard)hand.get(i)).getDamage())) {
				imax = i;
			}
		}
		return hand.get(imax);
	}
	
	public BOTDIFF getDifficulty() {
		return difficulty;
	}
}