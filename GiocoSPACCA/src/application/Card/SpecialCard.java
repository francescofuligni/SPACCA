package application.Card;
 
import java.util.ArrayList;
import java.util.Random;

import application.Games.Game;
import application.Player.PlayerInGame;

public class SpecialCard extends Card {
	
	private boolean imprevisto;
	
	public SpecialCard(int code) {
		super(code);
		if(code>=13 && code<=16)
			imprevisto=true;
		else
			imprevisto=false;
	}
	
	public boolean isImprevisto() {
		return imprevisto;
	}

	@Override
	public void effect(Game g) {
		switch(this.getCode()) {
		case 11:
			effect1(g);
		case 12:
			effect2(g);
		case 13:
			effect3(g);
		case 14:
			effect4(g);
		case 15:
			effect5(g);
		case 16:
			effect6(g);
		case 17:
			effect7(g);
		case 18:
			effect8(g);
		case 19:
			effect9(g);
		case 20:
			effect0(g);
		}
	}
	
	// principessa eroica
	private void effect1(Game g) {
		int damage = 5;
		PlayerInGame current = g.currentPlayer();
		PlayerInGame next = g.nextPlayer();
		next.setHealthPoints(next.getHealthPoints() - damage);
		current.setHealthPoints(current.getHealthPoints() + damage);
	}
	
	// sovrano guerriero
	private void effect2(Game g) {
		int damage = 6;
		PlayerInGame current = g.currentPlayer();
		PlayerInGame next = g.nextPlayer();
		next.setHealthPoints(next.getHealthPoints() - damage);
		current.setHealthPoints(current.getHealthPoints() + damage);
	}
	
	// ammutinamento (imprevisto)
	private void effect3(Game g) {
		int damage = 5;
		PlayerInGame current = g.currentPlayer();
		current.setHealthPoints(current.getHealthPoints() - damage);
		g.nextTurn();
	}
	
	// scomunica (imprevisto)
	private void effect4(Game g) {
		PlayerInGame current = g.currentPlayer();
		ArrayList<Card> hand = current.getHand();
		Random rand = new Random();
		hand.remove(rand.nextInt(hand.size()));
		current.setHand(hand);
	}
	
	// baratto (imprevisto)
	private void effect5(Game g) {
		PlayerInGame current = g.currentPlayer();
		PlayerInGame next = g.nextPlayer();
		ArrayList<Card> nextHand = next.getHand();
		next.setHand(current.getHand());
		current.setHand(nextHand);
	}
	
	// ghigliottina (imprevisto)
	private void effect6(Game g) {
		PlayerInGame current = g.currentPlayer();
		int hp = current.getHealthPoints();
		if(hp%2==0)
			current.setHealthPoints(hp/2);
		else
			current.setHealthPoints((hp+1)/2);
	}
	
	// un giro in taverna (opportunità)
	private void effect7(Game g) {
		int damage = 3;
		for(PlayerInGame p : g.getPlayers()) {
			p.setHealthPoints(p.getHealthPoints() - damage);
		}
		PlayerInGame current = g.currentPlayer();
		current.setHealthPoints(current.getHealthPoints() - damage);
	}
	
	// veleno maleodorante (opportunità)
	private void effect8(Game g) {
		int damage = 10;
		PlayerInGame next = g.nextPlayer();
		next.setHealthPoints(next.getHealthPoints() - damage);
	}
	
	// dono del mercante (opportunità)
	private void effect9(Game g) {
		PlayerInGame current = g.currentPlayer();
		ArrayList<Card> hand = current.getHand();
		hand.add(g.deck.pick());
		current.setHand(hand);
	}
	
	// rissa selvaggia (opportunità)
	private void effect0(Game g) {
		Random rand = new Random();
		for(PlayerInGame p : g.getPlayers()) {
			int damage = rand.nextInt(5, 16);
			p.setHealthPoints(p.getHealthPoints() - damage);
		}
	}
}
