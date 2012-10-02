package lab4;

import se.lth.cs.ptdc.window.SimpleWindow;

public class Turtle {
	protected SimpleWindow w;
	private boolean penDown;
	private double x;
	private double y;
	private int alpha;
	
	/**
	 * Skapar en sk�ldpadda som ritar i ritf�nstret w. Fr�n b�rjan befinner sig
	 * sk�ldpaddan i punkten x,y med pennan lyft och huvudet pekande rakt upp�t
	 * i f�nstret (i negativ y-riktning)
	 */
	public Turtle(SimpleWindow w, int x, int y) {
		this.w = w;
		this.x = x;
		this.y = y;
		alpha = 90;
		penDown = false;
	}
	
	/** S�nker pennan */
	public void penDown() {
		penDown = true;
	}
	
	/** Lyfter pennan */
	public void penUp() {
		penDown = false;
	}
	
	/** G�r rakt fram�t n pixlar i den riktning som huvudet pekar */
	public void forward(int n) {
		double xNew = x + n*Math.cos(Math.toRadians(alpha));
		double yNew = y - n*Math.sin(Math.toRadians(alpha));
		if (penDown) {
			w.moveTo((int) x, (int) y);
			w.lineTo((int) xNew, (int) yNew);
		}
		x = xNew;
		y = yNew;
	}
	
	/** Vrider beta grader �t v�nster runt pennan */
	public void left(int beta) {
		alpha += beta;
		if (alpha > 360) {
			alpha -= 360;
		}
	}
	
	/** G�r till punkten newX,newY utan att rita. Pennans l�ge (s�nkt 
	    eller lyft) och huvudets riktning p�verkas inte */
	public void jumpTo(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	/** Återst�ller huvudriktningen till den ursprungliga */
	public void turnNorth() {
		alpha = 90;
	}
	
	/** Tar reda p� x-koordinaten f�r sk�ldpaddans aktuella position */
	public int getX() {
		return (int) Math.round(x);
	}
	
	/** Tar reda p� y-koordinaten f�r sk�ldpaddans aktuella position */
	public int getY() {
		return (int) Math.round(y);
	}
	
	/** Tar reda p� sk�ldpaddans riktning, i grader fr�n positiv x-led */
	public int getDirection() {
		return alpha;
	}
}
