package lab8;

import java.awt.Color;

import se.lth.cs.ptdc.images.ImageFilter;

public class ContrastFilter extends ImageFilter {

	protected ContrastFilter(String name) {
		super(name);
	}

	public Color[][] apply(Color[][] inPixels, double paramValue) {
		int height = inPixels.length;
		int length = inPixels[0].length;
		short[][] intensities = computeIntensity(inPixels);
		short[] histogram = new short[256];
		for (int i = 0; i < intensities.length; ++i) {
			for (int j = 0; j < intensities[0].length; ++j) {
				int lol = intensities[i][j];
				histogram[lol] += 1;
			}
		}
		
		double nbrOfPixels = height*length*paramValue;
		
		int count = 0;
		int lowCut = 0;
		while (count < nbrOfPixels) {
			count += histogram[lowCut];
			lowCut++; 
		}
		
		count = 0;
		int highCut = 255;
		while (count < nbrOfPixels) {
			count += histogram[highCut];
			highCut--;
		}
		
		Color[][] outPixels = new Color[height][length];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < length; ++j) {
				int intensity;
				if (intensities[i][j] < lowCut) {
					intensity = 0;
				} else if (intensities[i][j] > highCut) {
					intensity = 255;
				} else {
					intensity = (255 * (intensities[i][j] - lowCut)) / (highCut - lowCut);
				}
				outPixels[i][j] = new Color(intensity, intensity, intensity);
			}
		}
		return outPixels;
	}

}
