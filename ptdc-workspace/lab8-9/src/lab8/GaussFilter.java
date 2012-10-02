package lab8;

import java.awt.Color;

import se.lth.cs.ptdc.images.ImageFilter;

public class GaussFilter extends ImageFilter {
	private static short[][] GAUSS_KERNEL = {	{ 0, 1, 0 },
												{ 1, 4, 1 },
												{ 0, 1, 0 }	};
	private int kernelSum = 8;

	protected GaussFilter(String name) {
		super(name);
	}

	
	public Color[][] apply(Color[][] inPixels, double paramValue) {
		GAUSS_KERNEL[1][1] = (short)paramValue;
		kernelSum = (int) (4 + paramValue);
		int height = inPixels.length;
		int length = inPixels[0].length;
		Color[][] outPixels = new Color[height][length];
		
		short[][] redMatrix = new short[height][length];
		short[][] greenMatrix = new short[height][length];
		short[][] blueMatrix = new short[height][length];
		
		for (int i = 0; i < height; ++i) {
			outPixels[i][0] = inPixels[i][0];
			outPixels[i][length - 1] = inPixels[i][length - 1];
		}
		for (int i = 0; i < length; ++i) {
			outPixels[0][i] = inPixels[0][i];
			outPixels[height - 1][i] = inPixels[height - 1][i];
		}
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < length; ++j) {
				redMatrix[i][j] = (short) inPixels[i][j].getRed();
				greenMatrix[i][j] = (short) inPixels[i][j].getGreen();
				blueMatrix[i][j] = (short) inPixels[i][j].getBlue();
			}
		}
		for (int i = 1; i < height - 1; ++i) {
			for (int j = 1; j < length - 1; ++j) {
				outPixels[i][j] = new Color(
						convolve(redMatrix, i, j, GAUSS_KERNEL, kernelSum),
						convolve(greenMatrix, i, j, GAUSS_KERNEL, kernelSum),
						convolve(blueMatrix, i, j, GAUSS_KERNEL, kernelSum));
			}
		}
		
		return outPixels;
	}

}
