package application.Card;

import application.Player.Player;

public class NormalCard extends Card { //ABSTRACT PERCHE DOBBIAMO DIFFERENZIARE LE CARTE NORMALI DA QUELLE SPECIALI
	private int damage;

	public NormalCard(int damage, int code) { 
		super(code);
		this.damage = damage;
	
	}
	
	public int getDamage() {
		return this.damage;
	}

	@Override
	public void effect(Player p) {
		p.setLifePoints(p.getLifePoints() - damage); 
	
	}
	
}
