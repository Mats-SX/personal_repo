package onlyPackage;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter nbr of sheets: ");
		int sheets = scan.nextInt();
		
		System.out.println("Enter nbr of rows on each sheet: ");
		int rows = scan.nextInt();
		
		System.out.println("Enter nbr of columns on each sheet: ");
		int columns = scan.nextInt();
		
		InputHandler in = new InputHandler();
		//in.addMatrices(rows, columns, sheets);
		in.readFromStdIn(scan, sheets, rows, columns);
		
		
		System.out.println(in.toCSVFile());
		
	}

}
