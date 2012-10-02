package skepp;

public class CaseConverter {
	
	public CaseConverter() {
	}
	
	/** Konverterar gemenen c till en versal upperCase, vilken returneras. Om en versal
	 * skickas in som parameter så returneras en likadan versal */
	public char convertUp(char c) {
		char lowerCase = 'a';
		char upperCase = 'A';
		while (c != upperCase && c != lowerCase) {
			lowerCase++;
			upperCase++;
		}
		return upperCase;
	}
	
	/** Konverterar versalen c till en gemen lowerCase, vilken returneras. Om en gemen
	 * skickas in som parameter så returneras en likadan gemen */
	public char convertDown(char c) {
		char lowerCase = 'a';
		char upperCase = 'A';
		while (c != lowerCase) {
			lowerCase++;
			upperCase++;
		}
		return lowerCase;
	}
}
