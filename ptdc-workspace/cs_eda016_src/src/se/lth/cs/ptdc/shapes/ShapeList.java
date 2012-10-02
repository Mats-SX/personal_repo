package se.lth.cs.ptdc.shapes;

import se.lth.cs.ptdc.shapes.Shape;
import se.lth.cs.ptdc.window.SimpleWindow;

/**
 * Beskriver en lista av figurobjekt (objekt av klassen Shape).
 * OBSERVERA: bara skelett, du ska sj�lv skriva denna klass.
 */
public class ShapeList {
	/**
	 * Skapar en tom lista.
	 */
	public ShapeList() {
		// ...
	}

	/**
	 * L�gger in en figur i listan.
	 * 
	 * @param s
	 *            figuren som ska l�ggas in i listan
	 */
	public void insert(Shape s) {
		// ...
	}

	/**
	 * Ritar upp figurerna i listan.
	 * 
	 * @param w
	 *            f�nstret d�r figurerna ritas
	 */
	public void draw(SimpleWindow w) {
		// ...
	}

	/**
	 * Tar reda p� en figur som ligger n�ra punkten xc,yc. Om flera figurer
	 * ligger n�ra s� returneras den f�rsta som hittas, om ingen figur ligger
	 * n�ra returneras null.
	 * 
	 * @param xc
	 *            x-koordinaten
	 * @param yc
	 *            y-koordinaten
	 */
	public Shape findHit(int xc, int yc) {
		return null;
	}
}
