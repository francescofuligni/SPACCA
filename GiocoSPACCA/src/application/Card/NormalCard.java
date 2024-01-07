package application.Card;

import application.Games.Game;
import application.Player.PlayerInGame;

public class NormalCard extends Card { 
	private int damage;
	private PlayerInGame current;
	private PlayerInGame next;

	public NormalCard(int code) { 
		super(code);
		if(code<6) 						// figure con danno positivo --> danno al prossimo
			damage=code;
		else   							// figure con danno negativo --> cura a te stesso
			damage= 5-code ;
	}
	
	public int getDamage() {
		return this.damage;
	}

	@Override
	public void effect(Game g) {
		this.current=g.currentPlayer();
		this.next=g.nextPlayerAlive();
		
		if(damage>0)
			next.setHealthPoints(next.getHealthPoints() - damage);
		else
			current.setHealthPoints(current.getHealthPoints() - damage);
	}
}
