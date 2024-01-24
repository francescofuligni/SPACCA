package application.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import application.Main;

public class Player {		// superclasse di PlayerInGame
	
	protected String username;
	
	public Player(String username) {		
		this.username=username;
	}
	public String getUsername() {
		return username;
	}
	
	
	public void memorize() {
		// scrive il giocatore nel Players Register
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		if(!f.exists())
			Main.fileCheck();		// se il file non esiste, lo crea
	        	
		try {
	        FileWriter fw = new FileWriter(f.getAbsolutePath(), true);
			fw.write(this.username + ",0,0\n" );
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean exists() {
		// controlla se il giocatore è già memorizzato nel Players Register
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		if(!f.exists()) {
			Main.fileCheck();		// se il file non esiste, lo crea
			return false;			// se il file non esiste, anche il giocatore non è memorizzato
		}
		
		try {
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()) {	
				if(scan.nextLine().split(",")[0].equals(this.username)) {
					scan.close();
					return true;
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void delete() {
		// elimina il giocatore dal Players Register
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		if(f.exists()) {		// se il file esiste, cerca il giocatore e lo elimina
			try {
				Scanner scan = new Scanner(f);
				scan.reset();
				String memory="";
				
				while(scan.hasNextLine()) {
					String line=scan.nextLine();
					String[] tokens = line.split(",");
					if(!tokens[0].equals(this.username)) {
						memory=memory+line + "\n";
					}
				}
				scan.close();	
					
				FileWriter fw = new FileWriter(f.getAbsolutePath(), false);
				fw.write(memory);
				fw.flush();
				fw.close();
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else {
			Main.fileCheck();	// se il file non esiste, lo crea (è certo che il giocatore non ci sia)
		}
	}
	
	
	public void addGame() {
		// viene richiamato quando il giocatore viene aggiunto a una partita	-->	incrementa di 1 contatore di partite del giocatore
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		// no controllo esistenza file	--> questo metodo viene richiamato dopo exists()
		
		try {
			Scanner scan = new Scanner(f);
			scan.reset();
			String memory="";
			
			while(scan.hasNextLine()) {
				String line=scan.nextLine();
				String[] tokens = line.split(",");
				if(tokens[0].equals(this.username)) {
					int gamesCounter = Integer.parseInt(tokens[2])+1;
					memory = memory + tokens[0] + "," + tokens[1] + "," + gamesCounter + "\n";
				} else {
					memory = memory+line+"\n";
				}
			}
			scan.close();	
				
			FileWriter fw = new FileWriter(f.getAbsolutePath(), false);
			fw.write(memory);
			fw.flush();
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void subtractGame() {
		// viene richiamato quando una partita termina o viene eliminata	-->	decrementa di 1 il contatore delle partite del giocatore
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		// no controllo esistenza file	--> questo metodo viene richiamato dopo exists()
		
		try {
			Scanner scan = new Scanner(f);
			scan.reset();
			String memory="";
			
			while(scan.hasNextLine()) {
				String line=scan.nextLine();
				String[] tokens = line.split(",");
				if(tokens[0].equals(this.username)) {
					int gamesCounter = Integer.parseInt(tokens[2])-1;
					if(gamesCounter<=0)
						gamesCounter=0;
					memory = memory + tokens[0] + "," + tokens[1] + "," + gamesCounter + "\n";
				} else {
					memory = memory+line+"\n";
				}
			}
			scan.close();	
				
			FileWriter fw = new FileWriter(f.getAbsolutePath(), false);
			fw.write(memory);
			fw.flush();
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean isInGame() {
		// ritorna true se il giocatore si trova in una partita esistente, altrimenti ritorna false
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		// no controllo esistenza file	--> questo metodo viene richiamato dopo exists()
		
		try {
			Scanner scan = new Scanner(f);
			scan.reset();
			
			while(scan.hasNextLine()) {
				String line=scan.nextLine();
				String[] tokens = line.split(",");
				if(tokens[0].equals(this.username)) {
					int gamesCounter = Integer.parseInt(tokens[2]);
					if(gamesCounter<=0) {
						scan.close();
						return false;
					} else {
						scan.close();
						return true;
					}
				}
			}
			scan.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;		// se non trova il giocatore, ritorna false
	}
	
	
	@Override
	public String toString() {
		return username;
	}
}
