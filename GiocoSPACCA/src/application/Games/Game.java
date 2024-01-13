package application.Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Admin.BOTDIFF;
import application.Card.Card;
import application.Card.Deck;
import application.Player.*;

public abstract class Game {
	protected BOTDIFF difficulty;
	protected ArrayList<PlayerInGame> players;
	protected ArrayList<PlayerInGame> eliminated;

	protected int turn;
	protected Scanner scan;
	public File gameFile;
	public String code;
	public Deck deck;			// scope public per i metodi effect delle carte
	
	public Game(File game) {
		this.gameFile = game;
		code = gameFile.getName().split("\\.")[0];
		try {
			scan = new Scanner(this.gameFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scan.reset();
		
		if(scan.hasNextLine()) {
			String line=scan.nextLine();
			String[] tokens = line.split(",");
			
			if(tokens[1]=="DIFFICILE")
				difficulty = BOTDIFF.DIFFICILE;
			else
				difficulty = BOTDIFF.FACILE;
			this.turn=Integer.parseInt(tokens[2]);
		}
		this.players=new ArrayList<PlayerInGame>();
		this.eliminated=new ArrayList<PlayerInGame>();
		this.deck=new Deck();	
	}
	
	public BOTDIFF getDifficulty() {
		return difficulty;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public ArrayList<PlayerInGame> getPlayers() {
		return players;
	}
	
	public ArrayList<PlayerInGame> getEliminated() {
		return eliminated;
	}
	
	public PlayerInGame currentPlayer() {
		return players.get(turn);
	}
	
	public PlayerInGame previousPlayer() {
		if(turn-1<0)
			return players.get(players.size()-1);
		else
			return players.get(turn-1);
	}
	
	public PlayerInGame nextPlayer() {
		if(turn+1>=players.size())
			return players.get(0);
		else
			return players.get(turn+1);
	}
	
	public PlayerInGame nextPlayerAlive() {			// restitusce il primo giocatore successivo a currentPlayer ancora in partita (con HP>0)
		PlayerInGame p = nextPlayer();
		for(int i=players.indexOf(p)+1; p.getHealthPoints()<=0 && !p.equals(currentPlayer()); i++) {
			if(i>=players.size())
				i=0;
			p=players.get(i);
		}
		return p;		// se non trova un giocatore vivo, ritorna il current
	}
	
	public void nextTurn() {
		Card c;
		do {
			c=deck.pick();
			if(currentPlayer().getHand().size()==1 && c.getCode()==14)		// non puoi pescare la scomunica se hai solo una carta in mano perché autogiocandosi andresti in negativo
				currentPlayer().getHand().remove(c);
		}while(currentPlayer().getHand().size()==0);		
		
		currentPlayer().addCard(c);
		if(turn+1==players.size())
			turn=0;
		else
			turn++;
	}
	
	public void setPlayers(ArrayList<PlayerInGame> players) {
		this.players = players;
	}

	public String printPlayers() {			// restituisce una stringa con i giocatori in partita e i loro HP
		String s="";
		for	(PlayerInGame p:players) {
			if(p.getHealthPoints()>0)
				s += (p.getUsername() + " ["+ p.getHealthPoints() + "]\n");
			else 
				s += (p.getUsername() + " [eliminato]\n");
		}
		return s;
	}
	
	public String printEliminated() {		// restituisce una stringa con i giocatori eliminati
		String s="";
		for	(PlayerInGame p:eliminated)
			s += (p.getUsername() + " [eliminato]\n");
		return s;
	}
	
	abstract public void removePlayer();		// eliminazione di un giocatore --> diverso a seconda della modalità
	
	abstract public void save();	 			// salvataggio della partita su file --> diverso a seconda della modalità
	
	protected boolean newGame() {
		if(currentPlayer().getHealthPoints()>0 && currentPlayer().getHand().size() == 0) {			// se i giocatori non hanno carte in mano, vengono distribuite le carte
			for(PlayerInGame p : players) {
				ArrayList<Card> hand = new ArrayList<>();
				for(int i=0; i<4; i++) {
					Card c;
					do {
						c = deck.pick();
					} while(c.getCode()<=16 && c.getCode()>=13);			// un giocatore non può avere imprevisti nella mano iniziale
					hand.add(c);
				}
				p.setHand(hand);
			}
			save();
			return true;
		} else {
			save();
			return false;
		}
	}
}
