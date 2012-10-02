package se.lth.cs.ptdc.square;

import se.lth.cs.ptdc.window.SimpleWindow;

/**
 * Beskriver en kvadrat med ett l�ge och en sidl�ngd.
 */
public class Square {
	private int x; // x-koordinat f�r medelpunkten
	private int y; // y-koordinat
	private int side; // sidl�ngd
	private double alpha; // rotationsvinkel (i radianer)

	/**
	 * Skapar en kvadrat med medelpunkten i x,y och med en given sidl�ngd.
	 * 
	 * @param x
	 *            x-koordinat f�r medelpunkten
	 * @param y
	 *            y-koordinat f�r medelpunkten
	 * @param side
	 *            sidl�ngd
	 */
	public Square(int x, int y, int side) {
		this.x = x;
		this.y = y;
		this.side = side;
		this.alpha = 0;
	}

	/**
	 * Tar reda p� x-koordinaten.
	 * 
	 * @return x-koordinaten f�r medelpunkten
	 */
	public int getX() {
		return x;
	}

	/**
	 * Tar reda p� y-koordinaten.
	 * 
	 * @return y-koordinaten f�r medelpunkten
	 */
	public int getY() {
		return y;
	}

	/**
	 * Tar reda p� sidl�ngden.
	 * 
	 * @return sidl�ngden
	 */
	public int getSide() {
		return side;
	}

	/**
	 * Flyttar kvadraten relativt det aktuella l�get.
	 * 
	 * @param dx
	 *            relativ f�rflyttning i x-led
	 * @param dy
	 *            relativ f�rflyttning i y-led
	 */
	public void move(int dx, int dy) {
		x = x + dx;
		y = y + dy;
	}

	/**
	 * �ndrar sidl�ngden.
	 * 
	 * @param newSide
	 *            den nya sidl�ngden
	 */
	public void setSide(int newSide) {
		side = newSide;
	}

	/**
	 * Roterar kvadraten motsols kring sin medelpunkt.
	 * 
	 * @param beta
	 *            antalet grader som kvadraten roteras
	 */
	public void rotate(int beta) {
		alpha = alpha - beta * Math.PI / 180;
	}

	/**
	 * Ritar kvadraten.
	 * 
	 * @param w
	 *            F�nstret d�r kvadraten ritas.
	 */
	public void draw(SimpleWindow w) {
		double pi4 = Math.PI / 4;
		double r = side / 2 * Math.sqrt(2);
		w.moveTo(x + (int) Math.round(r * Math.cos(alpha + pi4)), y
				+ (int) Math.round(r * Math.sin(alpha + pi4)));
		w.lineTo(x + (int) Math.round(r * Math.cos(alpha + 3 * pi4)), y
				+ (int) Math.round(r * Math.sin(alpha + 3 * pi4)));
		w.lineTo(x + (int) Math.round(r * Math.cos(alpha + 5 * pi4)), y
				+ (int) Math.round(r * Math.sin(alpha + 5 * pi4)));
		w.lineTo(x + (int) Math.round(r * Math.cos(alpha + 7 * pi4)), y
				+ (int) Math.round(r * Math.sin(alpha + 7 * pi4)));
		w.lineTo(x + (int) Math.round(r * Math.cos(alpha + pi4)), y
				+ (int) Math.round(r * Math.sin(alpha + pi4)));
	}

	/**
	 * Raderar bilden av kvadraten. Kvadraten f�r inte flyttas mellan uppritning
	 * och radering. Raderingen g�rs genom att bilden ritas �ver med vit f�rg,
	 * s� �ven korsande linjer kommer att raderas.
	 * 
	 * @param w
	 *            f�nstret d�r bilden raderas och ritas.
	 */
	public void erase(SimpleWindow w) {
		java.awt.Color savedColor = w.getLineColor();
		w.setLineColor(java.awt.Color.WHITE);
		draw(w);
		w.setLineColor(savedColor);
	}
}
