import java.awt.Color;
import se.lth.cs.ptdc.images.ImageFilter;

/** IdentityFilter beskriver en identitetstransformation */
public class GaussFilter extends ImageFilter {
	private short[][] GAUSS_KERNEL =
	{	{0, 1, 0},
		{1, 4, 1},
		{0, 1, 0}
	};
	
	/** skapa ett filterobjekt med namnet name */
	public GaussFilter(String name) {
		super(name);
	}
	
	/** filtrera bilden i matrisen inPixels och returnera resultatet i
		en ny matris. Utnyttja eventuellt värdet av parameter */
	public Color[][] apply(Color[][] inPixels, double paramValue) {
		int height = inPixels.length;
		int width = inPixels[0].length;
		Color[][] outPixels = new Color[height][width];
		GAUSS_KERNEL[1][1] = (short)paramValue;
		int sum = 4+GAUSS_KERNEL[1][1];
		
		short[][] red = new short[height][width];
		short[][] green = new short[height][width];
		short[][] blue = new short[height][width];
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				red[i][j] = (short)inPixels[i][j].getRed();
				green[i][j] = (short)inPixels[i][j].getGreen();
				blue[i][j] = (short)inPixels[i][j].getBlue();
			}
		}
		
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

				short newRed = convolve(red, i, j, GAUSS_KERNEL, sum);
				short newGreen = convolve(green, i, j, GAUSS_KERNEL, sum);
				short newBlue = convolve(blue, i, j, GAUSS_KERNEL, sum);
				outPixels[i][j] = new Color(newRed,newGreen,newBlue);
			}
		}
		return outPixels;
	}
}
