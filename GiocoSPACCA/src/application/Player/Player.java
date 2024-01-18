package application.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Player {
	
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
	        try {
	            Files.createDirectories(pathToFile.getParent());
	            Files.createFile(pathToFile);
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	        	
		try {
	        FileWriter fw = new FileWriter(f.getAbsolutePath(), true);
			fw.write(this.username + ",0,0\n" );
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean exists() throws FileNotFoundException {
		// controlla se il giocatore è già stato memorizzato nel Players Register
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		if(!f.exists())
			try {
				Files.createDirectories(pathToFile.getParent());
				Files.createFile(pathToFile);
			} catch (IOException e) {
				System.out.println(e);
			}	
		
		Scanner scan = new Scanner(f);	
		while(scan.hasNextLine()) {	
			if(scan.nextLine().split(",")[0].equals(this.username)) {
				scan.close();
				return true;
			}
		}
		scan.close();
		return false;
	}
	
	
	public void delete() {
		// elimina il giocatore dal Players Register
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		if(!f.exists())
	        try {
	            Files.createDirectories(pathToFile.getParent());
	            Files.createFile(pathToFile);
	        } catch (IOException e) {
	            System.out.println(e);
	        }
		
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
	}
	
	
	public void addGame() {
		// viene richiamato quando il giocatore viene aggiunto a una partita	-->	aggiunge una partita al giocatore
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
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
		// viene richiamato quando il giocatore temrina una partita	-->	toglie una partita al giocatore
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
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
