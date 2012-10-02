import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import se.lth.cs.ptdc.window.SimpleWindow;

public class ShapeTest {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "ShapeTest");
		Scanner scan;
		ShapeList shapes = new ShapeList();
		try {
			scan = new Scanner(new File("shapedata.txt"));
			shapes.read(scan);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		CommandDispatcher cmd = new CommandDispatcher(w, shapes);
		
		shapes.draw(w);
		cmd.mainLoop();
	}
}
