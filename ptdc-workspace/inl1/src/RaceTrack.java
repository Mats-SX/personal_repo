import se.lth.cs.ptdc.window.SimpleWindow;

public class RaceTrack {
	private int yStart; // Y- och X-koordinater
	private int yFinish; // för startlinje, mållinje
	private int StartX1; // och startpunkter på
	private int StartX2; // startlinjen

	/** Skapar en racingbana med startlinje på yStart och mållinje på yFinish */
	public RaceTrack(int yStart, int yFinish) {
		this.yStart = yStart;
		this.yFinish = yFinish;
	}

	/** Ritar racingbanan i fönstret w */
	public void draw(SimpleWindow w) {
		int fX = (w.getWidth()) * 1 / 10; // 1 tiondel av bredd och höjd
		int fY = (w.getHeight()) * 1 / 10; // av SimpleWindowet RaceTrack ritas
		// i.
		w.moveTo(0, 0);
		w.moveTo(fX, fY);
		w.lineTo(9 * fX, fY); // anpassar start- och mållinje
		w.moveTo(fX, 9 * fY); // samt startpunkter till att passa det
		w.lineTo(9 * fX, 9 * fY); // SimpleWindow RaceTrack är ritat i
		StartX1 = 2 * fX; // 1 tiondels marginal, 8 tiondelar
		StartX2 = 8 * fX; // långa linjer, 1 tiondel in på linjen
	} // ligger punkten

	/** Returnerar x-koordinat för startpunkt 1 */
	public int getStart1() {
		return StartX1;
	}

	/** Returnerar x-koordinat för startpunkt 2 */
	public int getStart2() {
		return StartX2;
	}

	/** Returnerar y-koordinat för mållinjen */
	public int getFinish() {
		return yFinish;
	}

	/** Returnerar y-koordinat för startlinjen */
	public int getStart() {
		return yStart;
	}

}
