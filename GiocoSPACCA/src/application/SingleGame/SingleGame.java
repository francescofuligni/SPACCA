package application.SingleGame;


import java.util.ArrayList;

import application.Admin.BOTDIFF;
import application.Player.PlayerInGame;

public class SingleGame {
	protected int playersNumber;
	protected BOTDIFF difficulty;
	protected ArrayList<PlayerInGame> players;
	protected String code;
	
	public SingleGame (int playersNumber, BOTDIFF difficulty, ArrayList<PlayerInGame> players, String code) {
		this.difficulty=difficulty;
		this.players=players;
		this.code=code;
		this.playersNumber = playersNumber;
		
	}
}
