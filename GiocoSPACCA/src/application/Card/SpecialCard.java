package application.Card;

import application.Player.Player;
import application.Player.PlayerInGame;

public class SpecialCard extends Card{

	private boolean flag = false;
	
	public SpecialCard(int code) {
		super(code);
	}
	
	@Override
	public void effect(PlayerInGame p) {
		
	}
}
