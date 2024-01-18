package application.Player;

import java.util.Random;

import application.Admin.BOTDIFF;
import application.Card.Card;
import application.Card.NormalCard;
import application.Card.SpecialCard;

public class Bot extends PlayerInGame {
	
	public Bot(PlayerInGame p) {
		super(p.username, p.healthPoints, p.hand);
	}

	public int botCard(BOTDIFF difficulty) {
		// metodo per la giocata del BOT
		
		if(this.hasImprevisti())				// se ha imprevisti, gioca il primo imprevisto che ha in mano
			for(Card c: hand)
				if(c.getCode()<=16 && c.getCode()>=13)
					return hand.indexOf(c);
		
		if(difficulty.equals(BOTDIFF.DIFFICILE)) {
			// BOT DIFFICILE	-->	gioca prima le special card, poi le carte che infliggono più danno
			boolean flag = true;
			int imax = 0;
			for(int i=0; i<hand.size(); i++) {
				if(hand.get(i) instanceof SpecialCard) {
					return i;
				} else if(flag) {
					imax = i;
					flag = false;
				} else if(Math.abs(((NormalCard)hand.get(imax)).getDamage())<Math.abs(((NormalCard)hand.get(i)).getDamage())) {
					imax = i;
				}
			}
			return imax;
			
		} else {
			// BOT FACILE	-->	gioca una carta casualmente
			Random rand = new Random();
			return rand.nextInt(hand.size());
		}
	}
	
}
