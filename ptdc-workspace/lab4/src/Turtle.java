import se.lth.cs.ptdc.window.SimpleWindow;

public class Turtle {
	protected int x;						// Turtles pos i x-led
	protected int y;						// Turtles pos i y-led
	protected boolean q;						// Om pennan �r s�nkt eller ej
	protected double pi = Math.PI;		// pi
	protected double dir = pi/2;			// huvudets vinkel fr�n pos x-axeln
	protected SimpleWindow w;				// f�nstret Turtle befinner sig i
	/**
	 * Skapar en sk�ldpadda som ritar i ritf�nstret w. Fr�n b�rjan befinner sig
	 * sk�ldpaddan i punkten x,y med pennan lyft och huvudet pekande rakt upp�t
	 * i f�nstret (i negativ y-riktning)
	 */
	public Turtle(SimpleWindow w, int x, int y) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.q = false;
		w.moveTo(x,y);
	}
	
	/** S�nker pennan */
	public void penDown() {
		q = true;
	}
	
	/** Lyfter pennan */
	public void penUp() {
		q = false;
	}
	
	/** G�r rakt fram�t n pixlar i den riktning som huvudet pekar */
	public void forward(int n) {
		w.moveTo(x, y);
		double xx = n * Math.cos(dir);		//l�ngd i x-led
		double yy = n * (-Math.sin(dir));		//l�ngd i y-led
		x = x + (int) Math.round(xx);		//flyttar Turtle 
		y = y + (int) Math.round(yy);		//relativt
		if (q) {
			w.lineTo(x, y);
		} else {
			w.moveTo((int) Math.round(xx), (int) Math.round(yy));
		}
	}
	
	/** Vrider beta grader �t v�nster runt pennan */
	public void left(int beta) {
		dir = dir + beta * (pi/180);		
	}
	
	/** G�r till punkten newX,newY utan att rita. Pennans l�ge (s�nkt 
	    eller lyft) och huvudets riktning p�verkas inte */
	public void jumpTo(int newX, int newY) {
		x = newX;
		y = newY;
		w.moveTo(newX,newY);
	}
	
	/** �terst�ller huvudriktningen till den ursprungliga */
	public void turnNorth() {
		dir = pi/2;
	}
	
	/** Tar reda p� x-koordinaten f�r sk�ldpaddans aktuella position */
	public int getX() {
		return this.x;
	}
	
	/** Tar reda p� y-koordinaten f�r sk�ldpaddans aktuella position */
	public int getY() {
		return this.y;
	}
	
	/** Tar reda p� sk�ldpaddans riktning, i grader fr�n positiv x-led */
	public int getDirection() {
		double degree = dir * (180/pi);
		return (int) Math.round(degree);
	}
}
