package application.Card;

import application.Games.Game;
import application.Player.PlayerInGame;

public class NormalCard extends Card { 
	private int damage;
	private PlayerInGame current;
	private PlayerInGame next;

	public NormalCard(int code) { 
		super(code);
		if(code<6) 						// figure con danno positivo --> danno al giocatore successivo
			damage = code;
		else   							// figure con danno negativo --> cura al giocatore corrente
			damage = 5-code ;
	}
	
	public int getDamage() {
		return this.damage;
	}

	@Override
	public void effect(Game g) {		// effetto della carta
		this.current=g.currentPlayer();
		this.next=g.nextPlayerAlive();
		
		if(damage>0)
			next.setHealthPoints(next.getHealthPoints() - damage);
		else
			current.setHealthPoints(current.getHealthPoints() - damage);
	}
}
