package com.matroskeen.helpful;

public class Converter {
	
	public static String MilisecondsToString(long ms) {
		int SECOND = 1000;
		int MINUTE = 60 * SECOND;
		int HOUR = 60 * MINUTE;
		int DAY = 24 * HOUR;

		StringBuffer text = new StringBuffer("");
		if (ms > DAY) {
		  text.append(ms / DAY).append(" days ");
		  ms %= DAY;
		}
		if (ms > HOUR) {
		  text.append(ms / HOUR).append(" hours ");
		  ms %= HOUR;
		}
		if (ms > MINUTE) {
		  text.append(ms / MINUTE).append(" minutes ");
		  ms %= MINUTE;
		}
		if (ms > SECOND) {
		  text.append(ms / SECOND).append(" seconds ");
		  ms %= SECOND;
		}
		text.append(ms + " ms");
		
		return text.toString();
	}

}
