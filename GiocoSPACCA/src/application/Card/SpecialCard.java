package application.Card;

import application.Player.*;

public class SpecialCard extends Card {

	private boolean flag = false;
	
	public SpecialCard(int code) {
		super(code);
	}
	
	// getters e setters
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void effect(PlayerInGame p) {
		
	}
}
