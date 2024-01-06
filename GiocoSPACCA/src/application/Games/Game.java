package application.Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import application.Admin.BOTDIFF;
import application.Card.Card;
import application.Card.Deck;
import application.Card.SpecialCard;
import application.Player.*;

public abstract class Game {
	protected BOTDIFF difficulty;
	protected ArrayList<PlayerInGame> players;

	protected int turn;
	protected int turnCounter;
	protected Scanner scan;
	public File gameFile;
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
			
			if(tokens[1]=="FACILE")
				difficulty = BOTDIFF.FACILE;
			else
				difficulty = BOTDIFF.DIFFICILE;
			this.turn=Integer.parseInt(tokens[2]);
			this.turnCounter=Integer.parseInt(tokens[3]);
		}
		this.players=new ArrayList<PlayerInGame>();
		this.deck=new Deck();
		
	}
	
	protected void deleteDeads(){
		//controllo per verificare se la carta speciale stia eliminando un giocatore
		for(int i=0; i<players.size();i++) {
			if(players.get(i).getHealthPoints()<=0) {
				players.remove(getPlayers().get(i));
				i--;
			}
		}
		
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
		if(turn+1>=players.size())
			return players.get(0);
		else
			return players.get(turn+1);
	}
	
	public void nextTurn() {
		Card c;
		do {
			c=deck.pick();
		}while(currentPlayer().getHand().size()==1&&c.getCode()==14);  //non puoi pescare la scomunica se hai solo una carta in mano perché autogiocandosi andresti in negativo
		
		currentPlayer().addCard(c);
		if(turn+1==players.size()) {
			turnCounter++; //conta i giri completi	
			turn=0;	
		}
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
	
	
	public void setPlayers(ArrayList<PlayerInGame> players) {
		this.players = players;
	}


	abstract public void removePlayer();			// eliminazione di un giocatore --> diverso a seconda della modalità
	
	abstract public void save();	 				// salvataggio della partita su file --> diverso a seconda della modalità
	
	protected void newGame() {						// distribuisce le carte ai giocatori se non ne hanno già in mano (se è una nuova partita)
		if(currentPlayer().getHand().size() == 0) { 
			for(PlayerInGame p : players) {
				ArrayList<Card> hand = new ArrayList<>();
				for(int i=0; i<4; i++) {
					Card c;
					do {
						c = deck.pick();
					} while(c instanceof SpecialCard && ((SpecialCard)c).isImprevisto());			// un giocatore non può avere imprevisti nella mano iniziale
					hand.add(c);
				}
				p.setHand(hand);
			}
		
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

 public int getTurnCounter() {
		return turnCounter;
	}


	//DA AGGIUNGIERE UN CONTROLLO SUI MORTI GIA RIMOSSI DA PLAYERSINGAME
	public String getInfoHP() {   //returna una stringa in cui vengono mostrati gli hp dei giocatori in gioco
		String s="";
		for	(PlayerInGame p:players) {
			s+= p.getUsername() + "- HP: "+ p.getHealthPoints() + "\n";
		}
		return s;
		
	}
}
