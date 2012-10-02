import se.lth.cs.ptdc.fractal.MandelbrotGUI;
import java.awt.Color;
import java.util.Random;

public class Generator {
	private boolean didRender;
	private int pixelSize;
	private final int COLOURS = 500;
	private Color[] colorSet;

	/** Skapar en Generator som inte har renderat än */
	public Generator() {
		didRender = false;
		pixelSize = 1;
		colorSet = new Color[COLOURS];
		Random rand = new Random();
		for (int i = 0; i < COLOURS; i++) {
			colorSet[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand
					.nextInt(256));
		}
	}

	/** Returnerar true om en bild har renderats */
	public boolean didRender() {
		return didRender;
	}

	/** Sätter didRender till default */
	public void resetRender() {
		didRender = false;
	}

	/**
	 * Renderar en bild i fönstret i användargränssnittet gui. I färgläge finns
	 * 500 intervall och varje intervall ges en slumpmässig RGB-färg.
	 */
	public void render(MandelbrotGUI gui) {
		gui.disableInput();
		int startPos = 0;
		boolean color = false;

		switch (gui.getResolution()) {
		case MandelbrotGUI.RESOLUTION_VERY_HIGH:
			pixelSize = 1;
			break;
		case MandelbrotGUI.RESOLUTION_HIGH:
			pixelSize = 3;
			startPos = 1;
			break;
		case MandelbrotGUI.RESOLUTION_MEDIUM:
			pixelSize = 5;
			startPos = 2;
			break;
		case MandelbrotGUI.RESOLUTION_LOW:
			pixelSize = 7;
			startPos = 3;
			break;
		case MandelbrotGUI.RESOLUTION_VERY_LOW:
			pixelSize = 9;
			startPos = 4;
			break;
		default:
			break;
		}

		if (gui.getMode() == MandelbrotGUI.MODE_COLOR) {
			color = true;
		} else {
			color = false;
		}

		Complex[][] complex = mesh(gui.getMinimumReal(), gui.getMaximumReal(),
				gui.getMinimumImag(), gui.getMaximumImag(), gui.getWidth(), gui
						.getHeight());
		Color[][] picture = new Color[1][gui.getWidth() / pixelSize];

		int resChangeW = 0;
		int resChangeH = 0;

		for (int i = 0; i < gui.getHeight() / pixelSize; i++) {
			resChangeH = i * pixelSize;
			for (int j = 0; j < picture[0].length - 1; j++) {
				resChangeW = j * pixelSize;

				Complex z = new Complex(0, 0);
				int k = 0;
				while (k < COLOURS && z.getAbs2() < 4) {
					z.mul(z);
					z.add(complex[startPos + resChangeH][startPos
									+ resChangeW]);
					k++;
				}

				if (color) {
					picture[0][j] = colorSet[k-1];
				} else {
					if (k < COLOURS) {
						picture[0][j] = Color.WHITE;
					} else {
						picture[0][j] = Color.BLACK;
					}
				}
			}
			gui.putData(0, pixelSize * i, picture, pixelSize, pixelSize);
		}
		didRender = true;
		gui.enableInput();
	}

	/**
	 * Skapar en matris där varje element är ett komplext tal i intervallen
	 * [minRe, maxRe] och [minIm, maxIm]. Matrisen har width kolonner och height
	 * rader.
	 */
	private Complex[][] mesh(double minRe, double maxRe, double minIm,
			double maxIm, int width, int height) {

		Complex[][] mesh = new Complex[height][width];
		double reChange = (maxRe - minRe) / (width - 1);
		double imChange = (minIm - maxIm) / (height - 1);
		for (int i = 0; i < height; i++) {
			double imValue = maxIm + i * (imChange);
			for (int j = 0; j < width; j++) {
				mesh[i][j] = new Complex(minRe + j * (reChange), imValue);
			}
		}
		return mesh;
	}
}
