package lab10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import se.lth.cs.ptdc.window.SimpleWindow;

public class ShapeTest {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "lol");
		ShapeList shapes = new ShapeList();
		Scanner scan = null;
		try { 
			scan = new Scanner(new File("shapedata.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNext()) {
			String s = scan.next();
			if (s.equals("S")) {
				
			} else if (s.equals("T")) {
				
			} else if (s.equals("C")) {
				
			}
		}
		shapes.draw(w);
		CommandDispatcher cmd = new CommandDispatcher(w, shapes);
		cmd.mainLoop();
	}

}
