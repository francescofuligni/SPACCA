package application.SingleGame;


import application.Admin.BOTDIFF;
import application.Admin.CreateSingleGameController;
import application.Player.Player;

public class SingleGame {
	protected int playersNumber;
	protected BOTDIFF difficulty;
	protected Player[] players;
	protected String code;
	
	public SingleGame (int playersNumber, BOTDIFF difficulty,Player[] players, String code) {
		this.difficulty=difficulty;
		this.players=players;
		this.code=code;
		this.playersNumber = playersNumber;
		
	}
}
