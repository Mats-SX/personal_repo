package lab8;

import java.awt.Color;

import se.lth.cs.ptdc.images.ImageFilter;

public class BlueFilter extends ImageFilter {

	protected BlueFilter(String name) {
		super(name);
	}

	@Override
	public Color[][] apply(Color[][] inPixels, double paramValue) {
		int height = inPixels.length;
		int width = inPixels[0].length;
		Color[][] outPixels = new Color[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				Color pixel = inPixels[i][j];
				outPixels[i][j] = new Color(0, 0, pixel.getBlue());
			}
		}
		return outPixels;
	}

}
