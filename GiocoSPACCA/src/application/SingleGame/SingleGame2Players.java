package application.SingleGame;

import java.util.ArrayList;

import application.Admin.BOTDIFF;
import application.Card.Card;
import application.Player.Player;
import application.Player.PlayerList;

public class SingleGame2Players extends SingleGame{
	
	protected int PlayerNumber = 2;
	protected BOTDIFF Difficulty;
	protected PlayerList players;
	protected String codice;
	protected int carteMazzo;
	protected ArrayList<Card> mazzo; // TODO creare metodo che simuli il mescolare il mazzo,
	protected Card[] manoPlayer1, manoPlayer2;
	
	
	public SingleGame2Players(int PlayerNumber, BOTDIFF Difficulty, PlayerList players, String codice) {
		super(PlayerNumber, Difficulty, players, codice);
		this.carteMazzo = 27; //11*2 + carte imprevisti + carte opportunità
	}

}
