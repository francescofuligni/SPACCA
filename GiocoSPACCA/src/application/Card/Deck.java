package application.Card;

import java.util.Random;

public class Deck {
	
	public static final int SIZE = 20;
	public Card[] cards = new Card[SIZE];		// mazzo infinito, salviamo solo i tipi di carte (20) diversi
	
	public Deck() {
		for(int i=0; i<SIZE; i++) {
			if(i<10) {
				
			 cards[i]=new NormalCard(i+1);
			} else {
				 cards[i]=new SpecialCard(i+1);
			}
		}
	}
	
	public Card pick() {						// non serve mescolare il mazzo: le carte vengono estratte casualmente dal mazzo
		Random rand = new Random();
		return cards[rand.nextInt(SIZE)];
	}
	
	public Card getCard(int i) {						// non serve mescolare il mazzo: le carte vengono estratte casualmente dal mazzo
		return cards[i];
	}
}