package application.Tournament;
import java.util.ArrayList;

import application.Admin.BOTDIFF;
import application.Admin.GAMEMODE;
import application.Player.*;

public class TournamentOBJ {
	protected GAMEMODE mode;
	protected BOTDIFF difficulty;
	protected ArrayList<PlayerInGame> players;
	protected String code;
	
	public TournamentOBJ (GAMEMODE mode,BOTDIFF difficulty, ArrayList<PlayerInGame> players, String code) {
		this.mode=mode;
		this.difficulty=difficulty;
		this.players=players;
		this.code=code;
	}
}

