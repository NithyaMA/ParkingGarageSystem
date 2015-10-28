package cs414.a4.nithya;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dummy {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
		Date date=null;
		try {
			date = sdf.parse("12-1990");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		System.out.println(cal.getTime());
	}

}
