package application.Card;

import application.Player.Player;

public class Ammutinamento extends Card{


	public Ammutinamento(int c) {
		super(c);
	}
	
	@Override
	public void effect(Player p) {
	 p.setLifePoints(p.getLifePoints() - 5);  //nella classe in cui sarà chiamato questo metodo il paramentro sarò CurrentPlayer
	}

}
