import java.awt.Color;
import se.lth.cs.ptdc.images.ImageFilter;

/** IdentityFilter beskriver en identitetstransformation */
public class Sobelfiltrering extends ImageFilter {
	private static short[][] X_SOBEL = {
		{-1, 0, 1},
		{-2, 0, 2},
		{-1, 0, 1}
	};
	private static short[][] Y_SOBEL = {
		{-1, -2, -1},
		{0, 0, 0},
		{1, 2, 1}
	};
	
	/** skapa ett filterobjekt med namnet name */
	public Sobelfiltrering(String name) {
		super(name);
	}
	
	/** filtrera bilden i matrisen inPixels och returnera resultatet i
		en ny matris. Utnyttja eventuellt värdet av parameter */
	public Color[][] apply(Color[][] inPixels, double paramValue) {
		int height = inPixels.length;
		int width = inPixels[0].length;
		Color[][] outPixels = new Color[height][width];
		
		/*short[][] red = new short[height][width];
		short[][] green = new short[height][width];
		short[][] blue = new short[height][width];
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				red[i][j] = (short)inPixels[i][j].getRed();
				green[i][j] = (short)inPixels[i][j].getGreen();
				blue[i][j] = (short)inPixels[i][j].getBlue();
			}
		}*/
		short[][] intensity = computeIntensity(inPixels);
		//short[][] newIntensity = new short[height][width];
		double treshold = paramValue;
		

		for (int i = 0;i < width;i++) {
			outPixels[0][i] = inPixels[0][i];
			outPixels[height-1][i] = inPixels[height-1][i];
		}
		for (int i = 0;i < height;i++) {
			outPixels[i][0] = inPixels[i][0];
			outPixels[i][width-1] = inPixels[i][width-1];
		}
		
		for (int i = 1; i < height-1; i++) {
			for (int j = 1; j < width-1; j++) {
				short sx = convolve(intensity, i, j, X_SOBEL, 8);
				short sy = convolve(intensity, i, j, Y_SOBEL, 8);
				int sobel = Math.abs(sx) + Math.abs(sy);
				if (sobel > treshold) {
					outPixels[i][j] = Color.BLACK;
				} else {
					outPixels[i][j] = Color.WHITE;
				}
				
				/*short newGreen = convolve(intensity, i, j, X_SOBEL, 8);
				short newBlue = convolve(intensity, i, j, X_SOBEL, 8);*/
				//outPixels[i][j] = new Color(newRed,newGreen,newBlue);
			}
		}
		/*for (int i = 1; i < height-1; i++) {
			for (int j = 1; j < width-1; j++) {
				short newRed = convolve(intensity, i, j, Y_SOBEL, 8);
				short newGreen = convolve(intensity, i, j, Y_SOBEL, 8);
				short newBlue = convolve(intensity, i, j, Y_SOBEL, 8);
				//outPixels[i][j] = new Color(newRed,newGreen,newBlue);
			}
		}*/
		
		
		/*for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				double derivative = (intensity[i][j+1] - intensity[i][j-1]) / 2;
			}
		}*/
		
		
		return outPixels;
	}
}
