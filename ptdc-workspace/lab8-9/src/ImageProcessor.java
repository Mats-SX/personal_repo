import se.lth.cs.ptdc.images.ImageFilter;
import se.lth.cs.ptdc.images.ImageGUI;


public class ImageProcessor {
	public static void main(String[] args) {
		ImageFilter[] filters = new ImageFilter[7];
		filters[0] = new IdentityFilter("Identity Filter");
		filters[1] = new BlueFilter("Blue Filter");
		filters[2] = new InvertFilter("Invert Filter");
		filters[3] = new GrayScaleFilter("Gråskalefilter");
		filters[4] = new ContrastFilter("Kontrastfilter");
		filters[5] = new GaussFilter("Gaussfilter");
		filters[6] = new Sobelfiltrering("Konturfilter");
		new ImageGUI(filters);
	}
}
