
public class Complex {
	private double re;
	private double im;
	
	/** Skapar en komplex variabel med realdelen re och imaginärdelen im */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	/** Tar reda på realdelen */
	public double getRe() {
		return re;
	}
	
	/** Tar reda på imgaginärdelen */
	public double getIm() {
		return im;
	}
	
	/** Tar reda på absolutbeloppet i kvadrat */
	public double getAbs2() {
		return re*re + im*im;
	}
	
	/** Adderar det komplexa talet c till detta tal */
	public void add(Complex c) {
		re = re + c.re;
		im = im + c.im;
	}
	
	/** Multiplicerar detta tal med det komplexa talet c */
	public void mul(Complex c) {
		double tempRe = re*c.re - im*c.im;
		double tempIm = re*c.im + c.re*im;
		re = tempRe;
		im = tempIm;
	}
}
