package se.lth.cs.ptdc.images;

import java.awt.Color;

/**
 * Superklasseb till alla filterklasser.
 */
public abstract class ImageFilter {
	private String name; // filtrets namn

	/**
	 * Skapar ett filterobjekt med ett givet namn.
	 * 
	 * @param name
	 *            filtrets namn
	 */
	protected ImageFilter(String name) {
		this.name = name;
	}

	/**
	 * Tar reda p� filtrets namn.
	 * 
	 * @return filtrets namn
	 */
	public String getName() {
		return name;
	}

	/**
	 * Filtrerar bilden i matrisen inPixels och returnerar resultatet i en ny
	 * matris. Utnyttjar eventuellt v�rdet av paramValue
	 * 
	 * @param inPixels
	 *            den ursprungliga bilden
	 * @param paramValue
	 *            parameterv�rde
	 * @return den filtrerade bilden
	 */
	public abstract Color[][] apply(Color[][] inPixels, double paramValue);

	/**
	 * Ber�knar intensiteten hos alla pixlarna i pixels, returnerar resultatet i
	 * en ny matris.
	 * 
	 * @param pixels
	 *            matris med pixlar
	 * @return intensiteten i varje pixel
	 */
	protected short[][] computeIntensity(Color[][] pixels) {
		int height = pixels.length;
		int width = pixels[0].length;
		short[][] intensity = new short[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Color c = pixels[i][j];
				intensity[i][j] = (short) ((c.getRed() + c.getGreen() + c
						.getBlue()) / 3);
			}
		}
		return intensity;
	}

	/**
	 * Faltar punkten p[i][j] med faltningsk�rnan kernel.
	 * 
	 * @param p
	 *            matris med talv�rden
	 * @param i
	 *            radindex f�r den aktuella punkten
	 * @param j
	 *            kolonnindex f�r den aktuella punkten
	 * @param kernel
	 *            faltningsk�rnan, en 3x3-matris
	 * @param weight
	 *            summan av elementen i kernel
	 * @return resultatet av faltningen
	 */
	protected short convolve(short[][] p, int i, int j, short[][] kernel,
			int weight) {
		short sum = 0;
		for (int ii = -1; ii <= 1; ii++) {
			for (int jj = -1; jj <= 1; jj++) {
				sum += p[i + ii][j + jj] * kernel[ii + 1][jj + 1];
			}
		}
		return (short) Math.round((double) sum / weight);
	}
}