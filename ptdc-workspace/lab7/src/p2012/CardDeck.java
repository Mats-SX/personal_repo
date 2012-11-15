package p2012;

import java.util.Random;

import se.lth.cs.ptdc.cardGames.Card;

public class CardDeck {
	private Card[] cards;
	private int current;
	private Random rand = new Random();
	
	public CardDeck() {
		cards = new Card[52];
		for (int rank = 1; rank < 14; rank++) {
			for (int suit = Card.SPADES; suit <= Card.CLUBS; suit++) {
				cards[current++] = new Card(suit, rank);
			}
		}
		current = 0;
	}

	public void shuffle() {
		current = 0;
		for (int i = 51; i > 0; i--) {
			int nbr = rand.nextInt(i);
			Card temp = cards[i];
			cards[i] = cards[nbr];
			cards[nbr] = temp;
		}
	}

	public boolean moreCards() {
		return current < 52;
	}

	public Card getCard() {
		if (!moreCards()) {
			return null;
		}
		return cards[current++];
	}
}
