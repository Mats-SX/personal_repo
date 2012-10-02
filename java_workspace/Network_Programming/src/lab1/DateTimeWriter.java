package lab1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeWriter {
	private Date date;
	private Locale locale;
	
	public DateTimeWriter(Locale loc) {
		date = new Date();
		locale = loc;
	}
	
	public String write() {
		return DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale).format(date);
		
	}

}
