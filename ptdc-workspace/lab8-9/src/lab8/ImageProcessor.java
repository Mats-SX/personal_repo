package lab8;

import se.lth.cs.ptdc.images.ImageFilter;
import se.lth.cs.ptdc.images.ImageGUI;

public class ImageProcessor {
	public static void main(String[] args) {
		ImageFilter[] filters = new ImageFilter[7];
		filters[0] = new IdentityFilter("Identity Filter");
		filters[1] = new BlueFilter("Blått");
		filters[2] = new InvertFilter("Inverterare");
		filters[3] = new GrayScaleFilter("grått");
		filters[4] = new ContrastFilter("kontrast");
		filters[5] = new GaussFilter("gauss");
		filters[6] = new SobelFilter("sobel");
		new ImageGUI(filters);
	}

}
