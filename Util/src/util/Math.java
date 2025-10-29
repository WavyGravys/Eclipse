package util;


public class Math {

	/**
	 * return float rounded to decimalPlace
	 * @param number
	 * @param decimalPlace <p>
	 * @Defaults
	 * <b>inputFloat</b> N/A <br>
	 * <b>decimalPlace</b> 2 <br>
	 */
	public static float roundFloatUp(float number, Integer decimalPlace) {
		int pow = 10;
		for (int i=1; i<decimalPlace; i++)
			pow *= 10;
		
		long wholeNum = (long)number;
		float decimals = number-wholeNum;
		if (decimals == 0f)
			return number;
		else {
			int temp = (int)(decimals*pow)+1;
			decimals = (float)temp/pow;
			return wholeNum+decimals;
		}
	}
}
