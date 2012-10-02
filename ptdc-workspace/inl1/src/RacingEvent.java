import se.lth.cs.ptdc.window.SimpleWindow;
import java.util.Random;

public class RacingEvent {
	private RaceTrack track;
	private Turtle t1;
	private Turtle t2;
	private Random rand;

	/**
	 * Skapar en t�vling mellan sk�ldpaddorna t1 och t2 p� banan track, och en
	 * slumptalsgenerator
	 */
	public RacingEvent(RaceTrack track, Turtle t1, Turtle t2) {
		this.track = track;
		this.t1 = t1;
		this.t2 = t2;
		rand = new Random();
	}

	/**
	 * Startar t�vlingen och l�ter sk�ldpaddorna g� fram�t med slumpvis l�nga
	 * steg �nda tills n�gon kommit i m�l. D� skrivs vinnaren ut
	 */
	public void race() {
		t1.jumpTo(track.getStart1(), track.getStart()); // placerar Turtlarna
		t2.jumpTo(track.getStart2(), track.getStart()); // i startposition

		while (t1.getY() > track.getFinish() && t2.getY() > track.getFinish()) {
			t1.jumpTo(t1.getX(), t1.getY()); // s�tter SWs penna till t1s pos
			int p = rand.nextInt(3);
			t1.penDown();
			t1.forward(p);
			t2.jumpTo(t2.getX(), t2.getY()); // s�tter pennan till t2s pos
			p = rand.nextInt(3);
			t2.penDown();
			t2.forward(p);
			SimpleWindow.delay(10);
		}

		if (t1.getY() < t2.getY()) {
			System.out.println("Sk�ldpadda 1 vann!! Wohoohooo!!");
		} else if (t2.getY() < t1.getY()) {
			System.out.println("Sk�ldpadda 2 vann!! Booo! Uppgjort!!");
		} else {
			System.out
					.println("Zomfg, dom kom lika! Vars�god att r�kna ut oddsen!");
			System.out
					.println("Du vinner dessutom en cookie: Vi gillar Sk�ldpadda 1 b�ttre.");
		}
	}

}
