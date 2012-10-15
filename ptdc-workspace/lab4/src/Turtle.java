import se.lth.cs.ptdc.window.SimpleWindow;

public class Turtle {
	protected int x;						// Turtles pos i x-led
	protected int y;						// Turtles pos i y-led
	protected boolean q;						// Om pennan är sänkt eller ej
	protected double pi = Math.PI;		// pi
	protected double dir = pi/2;			// huvudets vinkel från pos x-axeln
	protected SimpleWindow w;				// fönstret Turtle befinner sig i
	/**
	 * Skapar en sköldpadda som ritar i ritfönstret w. Från början befinner sig
	 * sköldpaddan i punkten x,y med pennan lyft och huvudet pekande rakt uppåt
	 * i fönstret (i negativ y-riktning)
	 */
	public Turtle(SimpleWindow w, int x, int y) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.q = false;
		w.moveTo(x,y);
	}
	
	/** Sänker pennan */
	public void penDown() {
		q = true;
	}
	
	/** Lyfter pennan */
	public void penUp() {
		q = false;
	}
	
	/** Går rakt framåt n pixlar i den riktning som huvudet pekar */
	public void forward(int n) {
		w.moveTo(x, y);
		double xx = n * Math.cos(dir);		//längd i x-led
		double yy = n * (-Math.sin(dir));		//längd i y-led
		x = x + (int) Math.round(xx);		//flyttar Turtle 
		y = y + (int) Math.round(yy);		//relativt
		if (q) {
			w.lineTo(x, y);
		} else {
			w.moveTo((int) Math.round(xx), (int) Math.round(yy));
		}
	}
	
	/** Vrider beta grader åt vänster runt pennan */
	public void left(int beta) {
		dir = dir + beta * (pi/180);		
	}
	
	/** Går till punkten newX,newY utan att rita. Pennans läge (sänkt 
	    eller lyft) och huvudets riktning påverkas inte */
	public void jumpTo(int newX, int newY) {
		x = newX;
		y = newY;
		w.moveTo(newX,newY);
	}
	
	/** Återställer huvudriktningen till den ursprungliga */
	public void turnNorth() {
		dir = pi/2;
	}
	
	/** Tar reda på x-koordinaten för sköldpaddans aktuella position */
	public int getX() {
		return this.x;
	}
	
	/** Tar reda på y-koordinaten för sköldpaddans aktuella position */
	public int getY() {
		return this.y;
	}
	
	/** Tar reda på sköldpaddans riktning, i grader från positiv x-led */
	public int getDirection() {
		double degree = dir * (180/pi);
		return (int) Math.round(degree);
	}
}
