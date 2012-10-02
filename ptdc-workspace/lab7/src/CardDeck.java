import se.lth.cs.ptdc.cardGames.Card;
import java.util.Random;

/**
 * En kortlek med Card-objekt.
 * OBSERVERA: bara skelett, du ska själv skriva denna klass.
 */
public class CardDeck {
	
	private Card[] kort;
	private int current;
	private static Random r = new Random();
	
	public CardDeck() {
		kort = new Card[52];
		
		int i = 0;
		for (int suit = 1;suit < 5;suit++){
			for (int rank = 1;rank < 14;rank++) {
				kort[i] = new Card(suit,rank);
				i++;
			}
		}
		current = 0;		
	}

	/**
	 * Blandar kortleken.
	 */
	public void shuffle() {
		Card extra;
		
		for (int i = 0;i < 52;i++) {
			int nbr = r.nextInt(51);
			extra = kort[i];
			kort[i] = kort[nbr];
			kort[nbr] = extra;			
		}
		current = 0;
	}

	/**
	 * Undersöker om det finns fler kort i kortleken.
	 * 
	 * @return true om det finns fler kort, false annars
	 */
	public boolean moreCards() {
		if (current < 52) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Drar det översta kortet i leken. Fungerar bara om moreCards är true.
	 * 
	 * @return det översta kortet i leken
	 */
	public Card getCard() {
		if (moreCards()) {
			current++;
			return kort[current-1];
		} else {
			System.out.println("Inga fler kort i leken!");
			return null;
		}
		
	}
}
