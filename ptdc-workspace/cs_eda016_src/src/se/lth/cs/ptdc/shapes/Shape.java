package se.lth.cs.ptdc.shapes;

import se.lth.cs.ptdc.window.SimpleWindow;

/**
 * Beskriver en figur som har ett l�ge.
 */
public abstract class Shape {
	/** x-koordinat f�r figurens l�ge. */
	protected int x;
	/** y-koordinat f�r figurens l�ge. */
	protected int y;

	/**
	 * Skapar en figur med ett givet l�ge.
	 * 
	 * @param x
	 *            x-koordinaten f�r l�get
	 * @param y
	 *            y-koordinaten f�r l�get
	 */
	protected Shape(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Ritar upp figuren i ett f�nster.
	 * 
	 * @param w
	 *            f�nstret d�r figuren ritas
	 */
	public abstract void draw(SimpleWindow w);

	/**
	 * Raderar bilden av figuren, flyttar figuren till newX,newY och ritar upp
	 * den p� sin nya plats i f�nstret. Raderingen g�rs genom att figuren ritas
	 * �ver med vit f�rg, vilket medf�r att korsande linjer ocks� raderas.
	 * 
	 * @param w
	 *            f�nstret d�r bilden finns och ska ritas igen
	 * @param newX
	 *            x-koordinaten f�r det nya l�get
	 * @param newY
	 *            y-koordinaten f�r det nya l�get
	 */
	public void moveToAndDraw(SimpleWindow w, int newX, int newY) {
		java.awt.Color savedColor = w.getLineColor();
		w.setLineColor(java.awt.Color.WHITE);
		draw(w);
		x = newX;
		y = newY;
		w.setLineColor(savedColor);
		draw(w);
	}

	/**
	 * Unders�ker om punkten xc,yc ligger "n�ra" figuren. Med "n�ra" menas inom
	 * 10 pixlar fr�n den punkt som definierar punktens l�ge.
	 * 
	 * @param xc
	 *            x-koordinat
	 * @param yc
	 *            y-koordinat
	 */
	public boolean near(int xc, int yc) {
		return Math.abs(x - xc) < 10 && Math.abs(y - yc) < 10;
	}
}
