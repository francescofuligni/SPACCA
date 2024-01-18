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
	private int score;
	
	// costruttore 1
	public Player(String username, int totalScore) {
		this.username=username;
		this.score=totalScore;
	}
	// costruttore 2
	public Player(String username) {		
		this.username=username;
		this.score=0;
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
	        FileWriter fw = new FileWriter(f.getAbsolutePath(),true);
			fw.write(this.username + "," + this.score + "\n" );
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
	
	
	public void forget() {
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
			
			while(scan.hasNextLine() ) {
				String line=scan.nextLine();			// NEXTLINE MANDA AVANTI LO SCANNER OGNI VOLTA CHE VIENE CHIAMATA ANCHE PER I CONTROLLI
				String[] tokens = line.split(",");
				if(!tokens[0].equals(this.username)) {
					memory=memory+line + "\n";
				}
			}
			scan.close();	
				
			FileWriter fw = new FileWriter(f);
			fw.write(memory);
			fw.flush();
			fw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// getters e setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return username;
	}
}
