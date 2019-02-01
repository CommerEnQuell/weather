package nl.commerquell.weather.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateUtils {
	
	private DateUtils() {}

	public static String format(Date date, String pattern) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(date.getTime());
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = 1 + cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		StringBuffer buf = new StringBuffer(pattern);
		replace(buf, "D", day);
		replace(buf, "M", month);
		replace(buf, "Y", year);
		replace(buf, "h", hour);
		replace(buf, "m", min);
		replace(buf, "s", sec);
		
		return buf.toString();
	}
	
	private static void replace(StringBuffer buf, String s, int i) {
		int firstIdx = buf.indexOf(s);
		if (firstIdx < 0) {
			return;
		}
		int lastIdx = buf.lastIndexOf(s);
		if (firstIdx == lastIdx) {
			buf.replace(firstIdx, firstIdx + 1, Integer.valueOf(i).toString());
			return;
		}
		String ns = Integer.valueOf(i).toString();
		int idx = lastIdx;
		int idy = ns.length();
		while (idx >= firstIdx) {
			idy--;
			if (idy < 0) {
				buf.replace(idx, idx + 1, "0");
			} else {
				buf.replace(idx, idx + 1, ns.substring(idy, idy + 1));
			}
			
			idx--;
		}
	}
	

}
