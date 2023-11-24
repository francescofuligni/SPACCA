package application.Card;

import application.Player.*;

public class NormalCard extends Card { 
	private int damage;

	public NormalCard(int damage, int code) { 
		super(code);
		this.damage = damage;
	
	}
	
	public int getDamage() {
		return this.damage;
	}

	@Override
	public void effect(PlayerInGame p) {
		p.setLifePoints(p.getLifePoints() - damage);
	}
	
}
