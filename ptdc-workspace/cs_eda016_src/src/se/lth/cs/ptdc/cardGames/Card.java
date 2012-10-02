package se.lth.cs.ptdc.cardGames;

/**
 * Ett spelkort med en f�rg och en val�r.
 */
public class Card {
	/** Konstant f�r kortets f�rg: Spader */
	public static final int SPADES = 1;
	/** Konstant f�r kortets f�rg: Hj�rter */
	public static final int HEARTS = SPADES + 1;
	/** Konstant f�r kortets f�rg: Ruter */
	public static final int DIAMONDS = SPADES + 2;
	/** Konstant f�r kortets f�rg: Kl�ver */
	public static final int CLUBS = SPADES + 3;

	private int suit; // f�rg
	private int rank; // val�r

	/**
	 * Skapar ett spelkort med en given f�rg och val�r.
	 * 
	 * @param suit
	 *            f�rgen (SPADES, HEARTS, DIAMONDS, eller CLUBS)
	 * @param rank
	 *            val�ren (1-13)
	 */
	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	/**
	 * Tar reda p� kortets f�rg.
	 * 
	 * @return f�rgen (SPADES, HEARTS, DIAMONDS eller CLUBS)
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * Tar reda p� kortets val�r.
	 * 
	 * @return val�ren (1-13)
	 */
	public int getRank() {
		return rank;
	}
}
