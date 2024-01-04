package application.Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import application.Admin.BOTDIFF;
import application.Card.Card;
import application.Card.Deck;
import application.Player.*;

public abstract class Game {
	protected BOTDIFF difficulty;
	protected ArrayList<PlayerInGame> players;
	protected int turn;
	protected Scanner scan;
	protected File gameFile;
	public Deck deck;			// scope public per i metodi effect delle carte
	
	public Game(File game) {
		this.gameFile = game;
		try {
			scan = new Scanner(this.gameFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scan.reset();
		
		if(scan.hasNextLine()) {
			String line=scan.nextLine();
			String[] tokens = line.split(",");
			turn=Integer.parseInt(tokens[2]);
			if(tokens[1]=="FACILE")
				difficulty = BOTDIFF.FACILE;
			else
				difficulty = BOTDIFF.DIFFICILE;
			this.turn=Integer.parseInt(tokens[2]);
		}
		this.players=new ArrayList<PlayerInGame>();
		this.deck=new Deck();
		
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
	
	
	abstract public void removePlayer();			// eliminazione di un giocatore --> diverso a seconda della modalità
	
	abstract public void save();	 				// salvataggio della partita su file --> diverso a seconda della modalità
	
	protected void newGame() {					// distribuisce le carte ai giocatori se non ne hanno già in mano (se è una nuova partita)
		if(currentPlayer().getHand().size() == 0) { 
			for(PlayerInGame p : players) {
				ArrayList<Card> hand = new ArrayList<>();
				for(int i=0; i<4; i++)
					hand.add(deck.pick());
				p.setHand(hand);
			}
			Collections.shuffle(players);			// metodo built-in per mescolare una collection (rimescola i giocatori solo se è una nuova partita)
			Random rand = new Random();
			turn = rand.nextInt(players.size());	// assegna turno iniziale casualmente (solo se è una nuova partita)
		}
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
