package lab7;

import se.lth.cs.ptdc.cardGames.Card;

public class Patience {
	public static void main(String[] args) {
		int count = 1;
		int timesOfFail = 0;
		double timesLaid = 1000000;
		for (int i = 0; i < timesLaid; ++i) {
			CardDeck deck = new CardDeck();
			deck.shuffle();
			while (deck.moreCards()) {			
				Card c = deck.getCard();
				if (c.getRank() == count) {
					++timesOfFail;
					break;
				} else if (count == 3) {
					count = 1;
				} else {
					++count;
				}
			}
			/*String suit = null;
			switch (c.getSuit()) {
				case Card.SPADES: suit = "Spader"; break;
				case Card.CLUBS: suit = "Klöver"; break;
				case Card.DIAMONDS: suit = "Ruter"; break;
				case Card.HEARTS: suit = "Hjärter"; break;
			}
			System.out.println(suit + " " + c.getRank());*/
		}
		System.out.println("Sannolikheten är " + ((timesLaid-timesOfFail) / timesLaid));
//		System.out.println(i);
	}

}
