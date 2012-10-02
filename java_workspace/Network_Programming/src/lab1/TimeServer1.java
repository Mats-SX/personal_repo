package lab1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServer1 {
	private Locale locale;
	
	public TimeServer1(Locale locale) {
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
	
	public String processRequest(String req) {
		if (req.equals("getDate")) {
			return getDate();
		} else if (req.equals("getTime")) {
			return getTime();
		} else if (req.equals("getDateAndTime")) {
			return getDateAndTime();
		} else {
			return "Unknown command";
		}
	}
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Submit exactly 1 argument");
			System.exit(1);
		}
		TimeServer1 ts = new TimeServer1(Locale.ENGLISH);
		if (args[0].equals("getDate")) {
			System.out.println(ts.getDate());
		} else if (args[0].equals("getTime")) {
			System.out.println(ts.getTime());
		} else if (args[0].equals("getDateAndTime")) {
			System.out.println(ts.getDateAndTime());
		} else {
			System.out.println("Unknown command");
		}
	}

}
