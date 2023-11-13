package application.SingleGame;

import java.util.ArrayList;

import application.Admin.BOTDIFF;
import application.Player.Player;
import application.Player.PlayerList;

public class SingleGame {
	protected int PlayerNumber;
	protected BOTDIFF Difficulty;
	protected PlayerList players;
	protected String codice;
	
	public SingleGame (int PlayerNumber,BOTDIFF Difficulty,PlayerList players, String codice) {
		this.PlayerNumber=PlayerNumber;
		this.Difficulty=Difficulty;
		this.players=players;
		this.codice=codice;
		
	}
	
}
