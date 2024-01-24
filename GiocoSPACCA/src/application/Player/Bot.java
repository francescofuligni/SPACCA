package application.Player;

import java.util.Random;

import application.Admin.BOTDIFF;
import application.Card.*;

public class Bot extends PlayerInGame {
	
	public Bot(String username) {
		super(username);
	}
	public Bot(PlayerInGame p) {
		super(p.username, p.healthPoints, p.hand);
	}

	public int botCard(BOTDIFF difficulty) {
		// metodo per la giocata del BOT	--> diverso a seconda della difficoltà
		
		if(this.hasImprevisti())	// se ha imprevisti, gioca il primo imprevisto che ha in mano
			for(Card c: hand)
				if(c.getCode()<=16 && c.getCode()>=13)
					return hand.indexOf(c);
		
		if(difficulty.equals(BOTDIFF.DIFFICILE)) {
			// BOT DIFFICILE	-->	gioca prima le special card, poi le carte con più danno o cura a seconda dei propri HP
			int imax = -1;
			int imin = -1;
			boolean first = true;
			for(int i=0; i<hand.size(); i++) {
				Card c = hand.get(i);
				if(c.getCode()>=11)
					return i;
				else
					if(first) {
						imax = i;
						imin = i;
						first = false;
					} else {
						if(((NormalCard)c).getDamage()>0 && ((NormalCard)c).getDamage() > ((NormalCard)(hand.get(imax))).getDamage())
							imax = i;
						if(((NormalCard)c).getDamage()<0 && ((NormalCard)c).getDamage() < ((NormalCard)(hand.get(imin))).getDamage())
							imin = i;
					}
			}
			
			if(this.healthPoints<MAXHP/3)
				return imin;
			else
				return imax;
			
		} else {
			// BOT FACILE	-->	gioca una carta casualmente
			Random rand = new Random();
			return rand.nextInt(hand.size());
		}
	}
	
}
