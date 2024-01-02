package application.Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import application.Admin.BOTDIFF;
import application.Card.Card;
import application.Player.*;

public abstract class Game {
	protected BOTDIFF difficulty;
	protected ArrayList<PlayerInGame> players;
	protected int turn;
	protected Card[] deck;
	protected Scanner scan;
	private File game;
	
	public Game(File game) throws FileNotFoundException {
		this.game = game;
		scan = new Scanner(this.game);
		scan.reset();
		
		if(scan.hasNextLine()) {
			String line=scan.nextLine();
			String[] tokens = line.split(",");
			if(tokens[1]=="FACILE")
				difficulty = BOTDIFF.FACILE;
			else
				difficulty = BOTDIFF.DIFFICILE;
		}
		
		this.players=new ArrayList<PlayerInGame>();
		createDeck();
	}
	
	
	public BOTDIFF getDifficulty() {
		return difficulty;
	}
	
	public ArrayList<PlayerInGame> getPlayers() {
		return players;
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
	
	
	public String toString() {
		String s="";
		Iterator<PlayerInGame> iter = players.iterator();
		while(iter.hasNext())
			s+=(iter.next() + "\n");
		
		return s;
	}
	
	
	abstract public void removePlayer();		// metodo astratto
	
	
	public Card pickCard() {
		Random rand = new Random();
		return deck[rand.nextInt(deck.length)];
	}
	
	public void save(File game) {
		// salvataggio della partita su file
	}
	
	
	protected void shuffle() {
		// mescola i giocatori
	}
	
	private void giveCards() {
		// distribuisce le carte ai giocatori
	}
	
	private void createDeck() {
		// crea il mazzo
	}
	
	
	protected PlayerInGame createBot(String botName, int healthPoints) {
		PlayerInGame bot;
		if(difficulty == BOTDIFF.FACILE)
			bot = new EasyBot(botName, healthPoints);
		else
			bot = new HardBot(botName, healthPoints);
		return bot;
	}
}
