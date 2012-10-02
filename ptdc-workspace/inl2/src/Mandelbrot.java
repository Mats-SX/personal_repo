import se.lth.cs.ptdc.fractal.MandelbrotGUI;

public class Mandelbrot {
	public static void main(String[] args) {

		Generator gen = new Generator();
		MandelbrotGUI mG = new MandelbrotGUI();
		mG.enableInput();
		while (true) {
			switch (mG.getCommand()) {
			case MandelbrotGUI.QUIT:
				System.exit(0);
				break;
			case MandelbrotGUI.RENDER:
				gen.render(mG);
				break;
			case MandelbrotGUI.RESET:
				mG.resetPlane();
				mG.clearPlane();
				gen.resetRender();
				break;
			case MandelbrotGUI.ZOOM:
				if (gen.didRender()) {
					gen.render(mG);
				}
				break;
			default:
				break;
			}
		}
	}
}