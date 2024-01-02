package application.Games;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import application.Admin.BOTDIFF;
import application.Card.Card;
import application.Player.PlayerInGame;

public abstract class Game {
	protected BOTDIFF difficulty;
	protected ArrayList<PlayerInGame> players;
	protected int turn;
	
	public Game(File game) {
		// differente popola da file a seconda del primo campo del file
		
		this.players=new ArrayList<PlayerInGame>();
		shuffle();
	}
	
	public PlayerInGame currentPlayer() {
		return players.get(turn);
	}
	
	public PlayerInGame nextPlayer() {
		if(turn+1==players.size())
			return players.get(0);
		else
			return players.get(turn+1);
	}
	
	public void nextTurn() {
		if(turn+1==players.size())
			turn=0;
		else
			turn++;
	}

	public ArrayList<PlayerInGame> getPlayers() {
		return players;
	}
	
	abstract public void removePlayer();
	
	private void shuffle() {
		// mescola i giocatori
	}
	
	public String toString() {
		String s="";
		
		Iterator<PlayerInGame> iter = players.iterator();
		while(iter.hasNext())
			s+=(iter.next() + "\n");
		
		return s;
	}
	
	private void giveCards() {
		// metodo per distribuire le carte
	}
	
	public Card pickCard() {
		// metodo per pescare una carta casualmente
		return null;
	}
	
	public void save(File game) {
		// salvataggio della partita su file
	}
}
