package lab8;

import java.awt.Color;

import se.lth.cs.ptdc.images.ImageFilter;

public class InvertFilter extends ImageFilter {

	protected InvertFilter(String name) {
		super(name);
	}

	@Override
	public Color[][] apply(Color[][] arg0, double arg1) {
		int height = arg0.length;
		int width = arg0[0].length;
		Color[][] outPixels = new Color[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				Color pixel = arg0[i][j];
				outPixels[i][j] = new Color(255 - pixel.getRed(), 255 - pixel.getGreen(), 255 - pixel.getBlue());
			}
		}
		return outPixels;
	}
	

}
