package p2012;

import se.lth.cs.ptdc.cardGames.Card;
//import se.lth.cs.ptdc.cardGames.CardDeck;

public class OneTwoThree {

	public static void main(String[] args) {
		int nbrOfTries = 2000000;
		int timesPassed = 0;
		CardDeck deck = new CardDeck();

		deck.shuffle();

		boolean passed = true;
		int numberSpoken = 1;

		for (int i = 0; i < nbrOfTries; i++) {
			while (deck.moreCards()) {
				Card c = deck.getCard();
				if (c.getRank() == numberSpoken) {
					passed = false;
					break;
				}
				if (++numberSpoken > 3) numberSpoken = 1;
			}
			if (passed) timesPassed++;
			deck.shuffle();
			passed = true;
		}

		double p = ((double) timesPassed) / nbrOfTries;
		System.out.println("Probability of success is: " + p);
	}

}
