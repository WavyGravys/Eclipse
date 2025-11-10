package util;

public class TimeUtils {
	public static void sleep(int ms) {
		if (ms == 0) { return; }
		
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("Loading bar interrupted");
		}
		
		return;
	}
}
