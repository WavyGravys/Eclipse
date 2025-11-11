package _übungen.ex01_tankbelege;

import util.ArrayUtils;

// wird noch nicht genutzt
public class RunningMean {
	
	double[] lastAverages = new double[5];
	
	public void addAverage(double avg) {
		shiftAveragesLeft();
		lastAverages[lastAverages.length - 1] = avg;
	}

	private void shiftAveragesLeft() {
		double[] newAverages = new double[lastAverages.length];
		System.arraycopy(lastAverages, 0, newAverages, 0, 0);
		for (int i = 0; i < lastAverages.length - 1; i++) {
			newAverages[i] = lastAverages[i+1];
		}
		lastAverages = newAverages;
	}
	
	public double getRunningMean() {
		return ArrayUtils.avgDouble(lastAverages);
	}
}
