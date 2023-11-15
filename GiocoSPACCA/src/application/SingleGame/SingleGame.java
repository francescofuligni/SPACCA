package application.SingleGame;


import application.Admin.BOTDIFF;
import application.Player.Player;
import application.Player.PlayerList;

public class SingleGame {
	protected int playersNumber;
	protected BOTDIFF difficulty;
	protected PlayerList players;
	protected String code;
	
	public SingleGame (int playersNumber, BOTDIFF difficulty,PlayerList players, String code) {
		this.difficulty=difficulty;
		this.players=players;
		this.code=code;
		this.playersNumber = playersNumber;
		
	}
}
