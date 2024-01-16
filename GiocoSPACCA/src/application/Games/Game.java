package application.Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
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
	protected boolean allDead = true;
	protected int turn;
	
	public File gameFile;
	public String code;
	public Deck deck;			// scope public per i metodi effect delle carte
	
	public Game(Path path) {
		this.gameFile = new File(path.toString());
		this.code = gameFile.getName().split("\\.")[0];
		
		this.players=new ArrayList<PlayerInGame>();
		this.eliminated=new ArrayList<PlayerInGame>();
		this.deck=new Deck();
		
		try {
			Scanner scan = new Scanner(this.gameFile);
			scan.reset();
			
			if(scan.hasNextLine()) {				// prende le informazioni dall'intestazione
				String line=scan.nextLine();
				String[] tokens = line.split(",");
				
				if(tokens[1]=="DIFFICILE")
					difficulty = BOTDIFF.DIFFICILE;
				else
					difficulty = BOTDIFF.FACILE;
				this.turn=Integer.parseInt(tokens[2]);
			}
			
			while(scan.hasNextLine()) {				// aggiunge i giocatori all'arraylist allPlayers
				String line=scan.nextLine();
				String[] tokens = line.split(",");
				
				String username = tokens[1];
				int healthPoints = Integer.parseInt(tokens[2]);
				PlayerInGame p = new PlayerInGame(username, healthPoints);
				
				if(tokens[0].equals("in")) {			// giocatori in partita
					ArrayList<Card> hand = new ArrayList<>();
					for(int i=3; i<tokens.length; i++)
						hand.add(deck.getCard(Integer.parseInt(tokens[i])));
					p.setHand(hand);
					players.add(p);
					
					if(p.getHealthPoints()>0)
						this.allDead = false;
				} else {								// giocatori eliminati
					eliminated.add(p);
				}
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
	
	protected boolean newGame() {
		if(eliminated.size() == 0 && (currentPlayer().getHealthPoints()>0 && currentPlayer().getHand().size() == 0)) {			// se i giocatori non hanno carte in mano, vengono distribuite le carte
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
		}
		return false;
	}
	
	
	// metodi astratti
	abstract public void removePlayer();		// eliminazione di un giocatore --> diverso a seconda della modalità
	
	abstract public void save();	 			// salvataggio della partita su file --> diverso a seconda della modalità
}
