package application.Card;

import application.Games.Game;
import application.Player.PlayerInGame;

public class NormalCard extends Card { 
	private int damage;

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
		PlayerInGame current = g.currentPlayer();
		PlayerInGame next = g.nextPlayer();
		if(damage>0)
			next.setHealthPoints(next.getHealthPoints() - damage);
		else
			current.setHealthPoints(current.getHealthPoints() - damage);
	}
}
