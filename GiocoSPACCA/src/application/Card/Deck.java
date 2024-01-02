package application.Card;

import java.util.Random;

public class Deck {
	
	public static final int SIZE = 20;
	public Card[] cards = new Card[SIZE];		// mazzo infinito, salviamo solo i tipi di carte (22) diverse
	
	public Deck() {
		for(int i=0; i<SIZE; i++) {
			if(i<10) {
				
			 cards[i]=new NormalCard(i+1);
			} else {
				 cards[i]=new SpecialCard(i+1);
			}
		}
	}
	
	public Card draw() {
		Random rand = new Random();
		int i = rand.nextInt(0, SIZE);
		Card card = cards[i];
		return card;
	}
	
	// non serve mescolare il mazzo: le carte vengono estratte casualmente dal mazzo
}
