package skepp;
import se.lth.cs.ptdc.window.SimpleWindow;
import se.lth.cs.ptdc.square.Square;
import java.awt.Color;

public class Zone {
	private Integer coordinateNbr;
	private char coordinateLetter;
	private int side;
	private boolean hasShip;
	private SimpleWindow w;
	private boolean isHit;
	
	
	public Zone(int nbr, char c, int side) {
		coordinateNbr = nbr;
		coordinateLetter = c;
		hasShip = false;
		this.side = side;
		isHit = false;
	}
	
	public void draw(SimpleWindow w) {
		this.w = w;
		int x = (coordinateLetter-'A'+1)*side;
		int y = coordinateNbr*side;
		Square sq = new Square(x,y,side);
		sq.draw(w);
		w.moveTo(x-(coordinateLetter-'A'+1)*side+5, y);
		w.writeText(coordinateNbr.toString());
		w.moveTo(x, y - coordinateNbr*side +20);
		w.writeText(coordinateLetter + "");
		//w.moveTo(w.getX()+side/2, w.getY()+side/2);
	}
	
	
	public void setShip() {
		hasShip = true;
		int x = (coordinateLetter-'A'+1)*side;
		int y = coordinateNbr*side;
		w.moveTo(x,y);
		w.lineTo(x+10,y+10);
	}
	
	public boolean isHit() {
		return isHit;
	}
	
	public void hit() {
		int x = (coordinateLetter-'A'+1)*side-side/2;
		int y = coordinateNbr*side-side/2;
		w.moveTo(x,y);
		w.setLineWidth(5);
		if (hasShip) {
			w.setLineColor(Color.RED);
		} else {
			w.setLineColor(Color.BLACK);
		}
		w.lineTo(x+side, y+side);
		isHit = true;
	}
	
	public int getSide() {
		return side;
	}
	
	/*public boolean hasShipNextTo(Zone[][] field) {
		if (field[0][0])
	}*/
	
	public boolean hasShip() {
		return hasShip;
	}
	
	public String getCoordinates() {
		return (coordinateLetter + coordinateNbr.toString());
	}
}
