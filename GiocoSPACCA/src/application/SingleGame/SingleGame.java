package application.SingleGame;

import java.util.ArrayList;

import application.Admin.BOTDIFF;
import application.Player.Player;

public class SingleGame {
	protected int PlayerNumber;
	protected BOTDIFF Difficulty;
	protected ArrayList<Player> Players;
	protected String Codice;
	
	public SingleGame (int PlayerNumber,BOTDIFF Difficulty,ArrayList<Player> Players, String Codice) {
		this.PlayerNumber=PlayerNumber;
		this.Difficulty=Difficulty;
		this.Players=Players;
		this.Codice=Codice;
		
	}
	
}
