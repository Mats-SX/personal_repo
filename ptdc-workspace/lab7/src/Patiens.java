import se.lth.cs.ptdc.cardGames.Card;

public class Patiens {
	public static void main(String[] args) {

		CardDeck deck = new CardDeck();
		Card c;

		int one23 = 0;
		boolean passed = true;
		int pass = 0;
		double measureLength = 10000.0;

		for (int i = 0; i < measureLength; i++) {

			deck.shuffle();

			while (deck.moreCards() && passed) {
				one23++;
				if (one23 == 4) {
					one23 = 1;
				}
				c = deck.getCard();
					
				if (c.getRank() == one23) {
					passed = false;
				}
			}

			if (passed) {
				pass++;
			}
			passed = true;
		}

		double ratio = pass / measureLength;

		System.out.println("Sannolikheten för att patiensen ska gå ut är "
				+ ratio);

	}

}
