package lab8;

import java.awt.Color;

import se.lth.cs.ptdc.images.ImageFilter;

public class SobelFilter extends ImageFilter {
	private static short[][] X_SOBEL = { {-1, 0, 1},
										 {-2, 0, 2},
										 {-1, 0, 1} };
	private static short[][] Y_SOBEL = { {-1, -2, -1},
										 {0, 0, 0},
										 {1, 2, 1} };

	protected SobelFilter(String name) {
		super(name);
	}

	@Override
	public Color[][] apply(Color[][] inPixels, double paramValue) {
		int height = inPixels.length;
		int width = inPixels[0].length;
		Color[][] outPixels = new Color[height][width];
		short[][] intensities = computeIntensity(inPixels);

		for (int i = 0; i < height; ++i) {
			outPixels[i][0] = inPixels[i][0];
			outPixels[i][width - 1] = inPixels[i][width - 1];
		}
		for (int i = 0; i < width; ++i) {
			outPixels[0][i] = inPixels[0][i];
			outPixels[height - 1][i] = inPixels[height - 1][i];
		}
		
		for (int i = 1; i < height - 1; ++i) {
			for (int j = 1; j < width - 1; ++j) {
				short sx = convolve(intensities, i, j, X_SOBEL, 1);
				short sy = convolve(intensities, i, j, Y_SOBEL, 1);
				if (Math.abs(sx) + Math.abs(sy) > paramValue) {
					outPixels[i][j] = Color.BLACK;
				} else {
					outPixels[i][j] = Color.WHITE;
				}
			}
		}
		return outPixels;
	}
}