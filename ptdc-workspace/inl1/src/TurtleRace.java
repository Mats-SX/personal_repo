import se.lth.cs.ptdc.window.SimpleWindow;
import java.util.Scanner;

public class TurtleRace {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Ange önskad fönsterstorlek i x- och y-led. ");
		System.out.println("Främst anpassat för heltal delbara med 10.");
		int x = scan.nextInt();
		int y = scan.nextInt();
		SimpleWindow w = new SimpleWindow(x, y, "Turtle Race");
		Turtle t1 = new Turtle(w, 10, 10);
		Turtle t2 = new Turtle(w, 20, 20);

		RaceTrack bana = new RaceTrack(9 * y / 10, y / 10); // anpassar start- och
															// mållinje till SWs
		bana.draw(w); 										// storlek
															
		RacingEvent prix = new RacingEvent(bana, t1, t2);
		w.waitForMouseClick();
		prix.race();
	}
}
