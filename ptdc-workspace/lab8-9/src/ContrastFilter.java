import java.awt.Color;
import se.lth.cs.ptdc.images.ImageFilter;

/** IdentityFilter beskriver en identitetstransformation */
public class ContrastFilter extends ImageFilter {
	/** skapa ett filterobjekt med namnet name */
	public ContrastFilter(String name) {
		super(name);
	}

	/**
	 * filtrera bilden i matrisen inPixels och returnera resultatet i en ny
	 * matris. Utnyttja eventuellt värdet av parameter
	 */
	public Color[][] apply(Color[][] inPixels, double paramValue) {
		int height = inPixels.length;
		int width = inPixels[0].length;

		Color[] grayLevels = new Color[256];
		for (int l = 0; l < grayLevels.length; l++) {
			grayLevels[l] = new Color(l, l, l);
		}

		Color[][] outPixels = new Color[height][width];
		short[][] intensity = computeIntensity(inPixels);
		
		int[] histogram = new int[256];
		for (int k = 0;k < height;k++) {
			for (int q = 0;q < width;q++) {
				histogram[intensity[k][q]]++;
			}
		}
		
		double cutOff = paramValue/100;
		int lowCut = 0;
		int pixels = 0;
		while (pixels <= cutOff*height*width) {
			pixels += histogram[lowCut];
			lowCut++;
		}
		
		pixels = 0;
		int highCut = 255;
		while (pixels <= cutOff*height*width) {
			pixels += histogram[highCut];
			highCut--;
		}
		
		double[][] newIntensity = new double[height][width];
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {	
				if (intensity[i][j] < lowCut) {
					newIntensity[i][j] = 0;
				} else if (intensity[i][j] > highCut) {
					newIntensity[i][j] = 255;
				} else {
					newIntensity[i][j] = (double)(255 * (((double)intensity[i][j] - (double)lowCut) / ((double)highCut - (double)lowCut)));
				}
			}
		}
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				outPixels[i][j] = grayLevels[(int)newIntensity[i][j]];
			}
		}
		return outPixels;
	}
}