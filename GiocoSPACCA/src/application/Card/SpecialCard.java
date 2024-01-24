package application.Card;
 
import java.util.ArrayList;
import java.util.Random;

import application.Games.Game;
import application.Player.PlayerInGame;

public class SpecialCard extends Card {
	
	private PlayerInGame next;
	private PlayerInGame current;
	
	public SpecialCard(int code) {
		super(code);
	}

	@Override
	public void effect(Game g) {		// effetto della carta
		this.current=g.currentPlayer();
		this.next=g.nextPlayerAlive();
		
		// effetto diverso carta per carta
		switch(this.getCode()) {
		case 11:
			effect1(g);
			break;
		case 12:
			effect2(g);
			break;
		case 13:
			effect3(g);
			break;
		case 14:
			effect4(g);
			break;
		case 15:
			effect5(g);
			break;
		case 16:
			effect6(g);
			break;
		case 17:
			effect7(g);
			break;
		case 18:
			effect8(g);
			break;
		case 19:
			effect9(g);
			break;
		case 20:
			effect0(g);
			break;
		default:
			break;
		}
	}
	
	// principessa eroica
	private void effect1(Game g) {
		int damage = 5;
		next.setHealthPoints(next.getHealthPoints() - damage);
		current.setHealthPoints(current.getHealthPoints() + damage);
	}
	
	// sovrano guerriero
	private void effect2(Game g) {
		int damage = 6;
		next.setHealthPoints(next.getHealthPoints() - damage);
		current.setHealthPoints(current.getHealthPoints() + damage);
	}
	
	// ammutinamento (imprevisto)
	private void effect3(Game g) {
		int damage = 5;
		current.setHealthPoints(current.getHealthPoints() - damage);
	}
	
	// scomunica (imprevisto)
	private void effect4(Game g) {
		ArrayList<Card> hand = current.getHand();
		Random rand = new Random();
		hand.remove(rand.nextInt(hand.size()));
		current.setHand(hand);
	}
	
	// baratto (imprevisto)
	private void effect5(Game g) {
		ArrayList<Card> nextHand = next.getHand();
		next.setHand(current.getHand());
		next.addCard(g.deck.pick());
		current.setHand(nextHand);
	}
	
	// ghigliottina (imprevisto)
	private void effect6(Game g) {
		int hp = current.getHealthPoints();
		if(hp%2==0)
			current.setHealthPoints(hp/2);
		else
			current.setHealthPoints((hp-1)/2);
	}
	
	// un giro in taverna (opportunità)
	private void effect7(Game g) {
		int damage = 3;
		for(PlayerInGame p : g.getPlayers()) {
			if(p.getHealthPoints()>0)				// controllo per evitare ritorno in partita di giocatori eliminati (gli effetti valgono solo su giocatori in partita)
				p.setHealthPoints(p.getHealthPoints() + damage);
		}
		current.setHealthPoints(current.getHealthPoints() + damage);
	}
	
	// veleno maleodorante (opportunità)
	private void effect8(Game g) {
		int damage = 10;
		next.setHealthPoints(next.getHealthPoints() - damage);
	}
	
	// dono del mercante (opportunità)
	private void effect9(Game g) {
		current.addCard(g.deck.pick());
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
