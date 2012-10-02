import se.lth.cs.ptdc.window.SimpleWindow;
import java.awt.Color;

public class ColorTurtle extends Turtle {
	private Color color;
	
	/** Skapar en sk�ldpadda som ritar i ritf�nstret w med f�rgen color.
		Fr�n b�rjan befinnner sig sk�ldpaddan i punkten x,y med
		pennan lyft och huvudet pekande rakt upp�t i f�nstret, dvs i 
		negativ y-riktning */
	public ColorTurtle(SimpleWindow w, int x, int y, Color color) {
		super(w, x, y);
		this.color = color;;
	}

	/** G�r fram�t n pixlar i den riktning huvudet pekar */
	public void forward(int n) {
		Color savedColor = w.getLineColor();
		w.setLineColor(color);
		super.forward(n);
		w.setLineColor(savedColor);
	}
}