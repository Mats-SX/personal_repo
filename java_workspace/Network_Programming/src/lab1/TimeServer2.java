package lab1;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class TimeServer2 {
	private Locale locale;
	
	public TimeServer2(Locale locale) {
		this.locale = locale;
	}
	
	public String getDate() {
		return DateFormat.getDateInstance(DateFormat.DEFAULT, locale).format(new Date());
	}
	
	public String getTime() {
		return DateFormat.getTimeInstance(DateFormat.DEFAULT, locale).format(new Date());
	}
	
	public String getDateAndTime() {
		return DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale).format(new Date());
	}
	
	public static void main(String[] args) {
		
		TimeServer2 ts = new TimeServer2(Locale.ENGLISH);
		
		while (true) {
			Scanner scan = new Scanner(System.in);
			int command = -1;
			command = scan.nextInt();
			switch (command) {
			case 1:
				System.out.println(ts.getDate());
				break;
			case 2:
				System.out.println(ts.getTime());
				break;
			case 3:
				System.out.println(ts.getDateAndTime());
				break;
			default:
				System.out.println("Unknown command");
				break;
			}
		}
	}

}
