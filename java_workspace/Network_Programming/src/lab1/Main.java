package lab1;

import java.util.Locale;

public class Main {
	
	public static void main(String[] args) {
		
		// Printing Date and Time
		DateTimeWriter dtw = new DateTimeWriter(Locale.ENGLISH);
		System.out.println(dtw.write());
		
	}

}
