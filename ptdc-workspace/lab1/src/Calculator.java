import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		System.out.println("Skriv två tal");
		Scanner scan = new Scanner(System.in);
		double nbr1 = scan.nextDouble();
		double nbr2 = scan.nextDouble();
//		int nbr1 = scan.nextInt();
//		int nbr2 = scan.nextInt();
		System.out.println(nbr1 + " + " + nbr2 + " = " + (nbr1 + nbr2));
		System.out.println(nbr1 + " - " + nbr2 + " = " + (nbr1 - nbr2));
		System.out.println(nbr1 + " * " + nbr2 + " = " + nbr1 * nbr2);
		System.out.println(nbr1 + " / " + nbr2 + " = " + nbr1 / nbr2);
		System.out.println(nbr1 + " % " + nbr2 + " = " + nbr1 % nbr2);
	}
}
