import java.awt.Color;
import se.lth.cs.ptdc.images.ImageFilter;

/** IdentityFilter beskriver en identitetstransformation */
public class GrayScaleFilter extends ImageFilter {
	/** skapa ett filterobjekt med namnet name */
	public GrayScaleFilter(String name) {
		super(name);
	}
	
	/** filtrera bilden i matrisen inPixels och returnera resultatet i
		en ny matris. Utnyttja eventuellt värdet av parameter */
	public Color[][] apply(Color[][] inPixels, double paramValue) {
		int height = inPixels.length;
		int width = inPixels[0].length;
		
		Color[] grayLevels = new Color[256];
		for (int l = 0;l < grayLevels.length;l++) {
			grayLevels[l] = new Color(l,l,l);
		}
		
		Color[][] outPixels = new Color[height][width];
		short[][] intensity = computeIntensity(inPixels);
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				outPixels[i][j] = grayLevels[intensity[i][j]];
			}
		}
		return outPixels;
	}
}
