package application.SingleGame;

import java.util.ArrayList;

import application.Admin.BOTDIFF;
import application.Card.Card;
import application.Player.Player;

public class SingleGame2Players extends SingleGame{
	
	protected int PlayerNumber = 2;
	protected BOTDIFF Difficulty;
	protected ArrayList<Player> Players;
	protected String Codice;
	protected int turno; //se pari è il turno del p1, altrimenti del pl2
	protected int ps1, ps2;
	protected int carteMazzo;
	protected ArrayList<Card> mazzo; // TODO creare metodo che simuli il mescolare il mazzo, TODO creare classe carta
	protected Card[] manoPlayer1, manoPlayer2;
	
	
	public SingleGame2Players(int PlayerNumber, BOTDIFF Difficulty, ArrayList<Player> Players, String Codice) {
		super(PlayerNumber, Difficulty, Players, Codice);
		turno = 0;
		ps1=30;
		ps2 = 30;
		carteMazzo = 27; //11*2 + carte imprevisti + carte opportunità;
	}
	
	
	

	

}
