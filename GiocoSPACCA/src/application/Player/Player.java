package application.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Player {
	
	private String username;
	private boolean status; 
	private int lifePoints;			
	private int currentScore;	
	private int totalScore;
	// costruttore 1
	public Player(String username, boolean status ,int lifePoints,int currentScore) {		
		this.status=status;
		this.username=username;
		this.lifePoints=lifePoints;
		this.currentScore=currentScore;
		this.totalScore=totalScore;
		
	}
	// costruttore 2
	public Player(String username) {		
		this.username=username;
		this.status=false;
		this.lifePoints=0;
		this.currentScore=0;
		this.totalScore=0;
		
	}
	
	
	public void memorize() {
		
		Path pathToFile = Paths.get("GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
			File f=new File(pathToFile.toString());
			if(!f.exists())
	        try {
	            Files.createDirectories(pathToFile.getParent());
	            Files.createFile(pathToFile);
	        } 
	        catch( IOException e ) {
	            System.out.println(e);
	        }
	        	
		try {
			
	        FileWriter fw = new FileWriter(f.getAbsolutePath(),true);
	       
			fw.write(this.username + "," + this.status+ "," +this.lifePoints+ "," +this.currentScore+ "," +this.totalScore+ "," + "\n");
			fw.flush();
			fw.close();
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public boolean exists() {
		Path pathToFile = Paths.get("GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		boolean exists=false;
		if(!f.exists())
        try {
            Files.createDirectories(pathToFile.getParent());
            Files.createFile(pathToFile);
        } 
        catch( IOException e ) {
            System.out.println(e);
        }
			
			Scanner scan = new Scanner(f.getAbsolutePath());
			while(scan.hasNextLine() ) {
				if(scan.next()==username) {
					exists=true;
				}
			}
			
			return exists;
		
		
	}
	
	
	public void forget() {
		Path pathToFile = Paths.get("GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		if(!f.exists())
        try {
            Files.createDirectories(pathToFile.getParent());
            Files.createFile(pathToFile);
        } 
        catch( IOException e ) {
            System.out.println(e);
        }
        	
		try {
			
			Scanner scan = new Scanner(f.getAbsolutePath());
			FileWriter fw = new FileWriter(f.getAbsolutePath(),false);
			String memory="";
			
			while(scan.hasNextLine() ) {
				if(scan.next()!=username) {
					memory+=scan.nextLine() +"\n";
				}
			}
				fw.write(memory);
				fw.close();
				scan.close();
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	//getter e setter per ogni attributo
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status=status;
	}
	
	public void setLifePoints(int x) {
		this.lifePoints = x;
	}
	public int getLifePoints() {
		return this.lifePoints;
	}
	
	public void setCurrentScore(int x) {
		this.currentScore = x;
	}
	
	public int getCurrentScore() {
		return currentScore;
	}
}
