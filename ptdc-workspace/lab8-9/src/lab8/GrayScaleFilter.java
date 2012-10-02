package lab8;

import java.awt.Color;
import se.lth.cs.ptdc.images.ImageFilter;

public class GrayScaleFilter extends ImageFilter {

	protected GrayScaleFilter(String name) {
		super(name);
	}

	@Override
	public Color[][] apply(Color[][] arg0, double arg1) {
		int height = arg0.length;
		int width = arg0[0].length;
		Color[][] outPixels = new Color[height][width];
		short[][] gray = computeIntensity(arg0);
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				outPixels[i][j] = new Color(gray[i][j], gray[i][j], gray[i][j]);
			}
		}
		return outPixels;
	}
	
	

}
