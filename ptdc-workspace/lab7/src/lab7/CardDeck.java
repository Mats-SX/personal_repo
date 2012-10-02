package lab7;

import java.util.Random;

import se.lth.cs.ptdc.cardGames.Card;

public class CardDeck {
	private Card[] cards;
	private int current;
	private static Random rand = new Random();
	
	public CardDeck() {
		cards = new Card[52];
		int i = 0;
		for (int suit = Card.SPADES; suit <= Card.CLUBS; ++suit) {
			for (int rank = 1; rank < 14; ++rank) {
				cards[i] = new Card(suit, rank);				
				++i;
			}
		}
		current = 0;
	}

	public void shuffle() {
		for (int i = 51; i > -1; --i) {
			int nbr = rand.nextInt(i+1);
			Card temp = cards[i];
			cards[i] = cards[nbr];
			cards[nbr] = temp;
		}
		current = 0;
	}

	public boolean moreCards() {
		return current != 52;
	}

	public Card getCard() {
		++current;
		return cards[current - 1];
	}

}
