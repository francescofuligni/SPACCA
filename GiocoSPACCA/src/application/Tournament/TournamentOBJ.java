package application.Tournament;
import application.Admin.BOTDIFF;
import application.Admin.GAMEMODE;
import application.Player.Player;

public class TournamentOBJ {
	protected GAMEMODE mode;
	protected BOTDIFF difficulty;
	protected Player[] players;
	protected String code;
	
	public TournamentOBJ (GAMEMODE mode,BOTDIFF difficulty,Player[] players, String code) {
		this.mode=mode;
		this.difficulty=difficulty;
		this.players=players;
		this.code=code;
		
	}
}

