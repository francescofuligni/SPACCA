package application.SingleGame;

import java.util.ArrayList;

import application.Admin.BOTDIFF;
import application.Player.Player;

public class SingleGame {
	protected int PlayerNumber;
	protected BOTDIFF Difficulty;
	protected ArrayList<Player> Giocatori;
	protected String Codice;
	
	public SingleGame (int PlayerNumber,BOTDIFF Difficulty,ArrayList<Player> giocatori, String Codice) {
		this.PlayerNumber=PlayerNumber;
		this.Difficulty=Difficulty;
		this.Giocatori=giocatori;
		this.Codice=Codice;
		
	}
}
