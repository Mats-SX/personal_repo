package lab4;

import se.lth.cs.ptdc.window.SimpleWindow;

public class Turtle {
	protected SimpleWindow w;
	private boolean penDown;
	private double x;
	private double y;
	private int alpha;
	
	/**
	 * Skapar en sköldpadda som ritar i ritfönstret w. Från början befinner sig
	 * sköldpaddan i punkten x,y med pennan lyft och huvudet pekande rakt uppåt
	 * i fönstret (i negativ y-riktning)
	 */
	public Turtle(SimpleWindow w, int x, int y) {
		this.w = w;
		this.x = x;
		this.y = y;
		alpha = 90;
		penDown = false;
	}
	
	/** Sänker pennan */
	public void penDown() {
		penDown = true;
	}
	
	/** Lyfter pennan */
	public void penUp() {
		penDown = false;
	}
	
	/** Går rakt framåt n pixlar i den riktning som huvudet pekar */
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
	
	/** Vrider beta grader åt vänster runt pennan */
	public void left(int beta) {
		alpha += beta;
		if (alpha > 360) {
			alpha -= 360;
		}
	}
	
	/** Går till punkten newX,newY utan att rita. Pennans läge (sänkt 
	    eller lyft) och huvudets riktning påverkas inte */
	public void jumpTo(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	/** Ã…terställer huvudriktningen till den ursprungliga */
	public void turnNorth() {
		alpha = 90;
	}
	
	/** Tar reda på x-koordinaten för sköldpaddans aktuella position */
	public int getX() {
		return (int) Math.round(x);
	}
	
	/** Tar reda på y-koordinaten för sköldpaddans aktuella position */
	public int getY() {
		return (int) Math.round(y);
	}
	
	/** Tar reda på sköldpaddans riktning, i grader från positiv x-led */
	public int getDirection() {
		return alpha;
	}
}
