package application.Games;

import java.io.File;

//flow ST:  inserisci codice> gioca e finisci partita1> gioca e finisci partita2>gioca e finisci finale> fine torneo con dispay posizioni/punteggi

public class SimpleTournament extends Game { //in costruzione
	
	SingleGame partita1;
	SingleGame partita2;
	SingleGame finale;
	int counter=0;

	public SimpleTournament(File game) {
		super(game);
				
		
		
		if(players.size()==0) {		// nel caso in cui i giocatori sono morti tutti contemporaneamente, l'ultimo a morire (in base all'ordine) è il vincitore
			players.add(eliminated.remove(0));
			players.get(0).setHealthPoints(1);
		}
		scan.close();
		newGame();
	}
	
//TODO forse serve nuovo fxml apposito con label che indichi che partita si sta giocando oppure aggiungere label alla nostra fxml che in SingleGame normali
	//mostra codice partita mentre in singleGame derivato da tournament mostri "partita1", "partita2" oppure "finale"
	
	
	@Override
	public void removePlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

}
