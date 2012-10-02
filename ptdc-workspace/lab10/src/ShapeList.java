import se.lth.cs.ptdc.shapes.Shape;
import se.lth.cs.ptdc.window.SimpleWindow;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class ShapeList {
	private ArrayList<Shape> shapes;
	
	/** Skapar en tom lista */
	public ShapeList() {
		shapes = new ArrayList<Shape>();
	}
	
	public void read(Scanner scan) {
		while (scan.hasNext()) {
			String s = scan.next();
			if (s.equals("S")) {
				int p = scan.nextInt();
				int q = scan.nextInt();
				int r = scan.nextInt();
				shapes.add(new Square(p,q,r));
			} else if (s.equals("T")) {
				int p = scan.nextInt();
				int q = scan.nextInt();
				int r = scan.nextInt();
				shapes.add(new Triangle(p,q,r));
			} else {
				int p = scan.nextInt();
				int q = scan.nextInt();
				int r = scan.nextInt();
				shapes.add(new Circle(p,q,r));			
			}
		}
	}
	
	/** Lägger in figuren s i listan */
	public void insert(Shape s) {
		shapes.add(s);
	}
	
	/** Ritar upp figurerna i listan i fönstret w */
	public void draw(SimpleWindow w) {
		for (int i = 0;i < shapes.size();i++) {
			Shape s = shapes.get(i);
			s.draw(w);
		}
	}
	
	/** Tar reda på en figur som ligger nära punkten xc,yc (null om ingen
		sådan figur finns i listan */
	public Shape findHit(int xc, int yc) {
		for (int i = 0;i < shapes.size();i++) {
			Shape s = shapes.get(i);
			if (s.near(xc,yc)) {
				return s;
			}
		}
		return null;
	}
	
	public void print() {
		try {
			PrintWriter out = new PrintWriter(new File("shapedata.txt"));
			for (int i = 0;i < shapes.size();i++) {
				if (shapes.get(i) instanceof Triangle) {
					Triangle t = (Triangle)shapes.get(i);
					out.println(t.print());
				} else if (shapes.get(i) instanceof Square) {
					Square q = (Square)shapes.get(i);
					out.println(q.print());
				} else {
					Circle c = (Circle)shapes.get(i);
					out.println(c.print());
				}
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
