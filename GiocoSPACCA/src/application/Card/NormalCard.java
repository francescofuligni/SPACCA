package application.Card;

import application.Player.Player;

public class NormalCard extends Card {

	private int damage;
	public NormalCard(int c, int d) {
		super(c);
		this.damage=d;
	}
	
	@Override
	public void effect(Player p) {
		//TODO danni (se a playerInGame oppure su Player lo decideremo)
	}
}
