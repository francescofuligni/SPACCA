package application.Tournament;
import application.Admin.BOTDIFF;
import application.Admin.GAMEMODE;
import application.Player.Player;

public class TournamentOBJ {
	protected GAMEMODE Mode;
	protected BOTDIFF Difficulty;
	protected Player[] Giocatori;
	protected String Codice;
	
	public TournamentOBJ (GAMEMODE Mode,BOTDIFF Difficulty,Player[] Giocatori, String Codice) {
		this.Mode=Mode;
		this.Difficulty=Difficulty;
		this.Giocatori=Giocatori;
		this.Codice=Codice;
		
	}
}

