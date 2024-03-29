package lab8;

import java.awt.Color;

import se.lth.cs.ptdc.images.ImageFilter;

public class IdentityFilter extends ImageFilter {
	
	public IdentityFilter(String name) {
		super(name);
	}

	public Color[][] apply(Color[][] inPixels, double paramValue) {
		int height = inPixels.length;
		int width = inPixels[0].length;
		Color[][] outPixels = new Color[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				Color pixel = inPixels[i][j];
				outPixels[i][j] = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
			}
		}
		return outPixels;
	}

}
