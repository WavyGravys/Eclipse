package math;

public class Graph {
	
	String[] axisNames;
	int[] size;
	boolean[][] dataPoints;
	
	public Graph() {
		int sizeX = 10;
		int sizeY = 6;
		axisNames = new String[] {"x", "y"};
		size = new int[] {sizeX, sizeY};
		dataPoints = new boolean[sizeX][sizeY];
	}
	
	public Graph size(int x, int y) {
		dataPoints = new boolean[x][y];
		size = new int[] {x, y};
		return this;
	}
	
	public Graph axisNames(String x, String y) {
		axisNames = new String[] {x, y};
		return this;
	}
	
	public Graph addDataPoints(Point... points) {
		for (Point point : points) {
			dataPoints[point.x][point.y] = true;
		}
		return this;
	}
	
	public void display() {
		System.out.println(" " + axisNames[1]);
		System.out.println();
		for (int y = size[1] - 1; y >= 0; y--) {
			System.out.print(y + "|");
			for (int x = 0; x < size[0]; x++) {
				if (dataPoints[x][y]) {
					System.out.print("* ");
				}
				else {
					System.out.print("  ");
				}
			}
			
			System.out.print("\n");
		}
		StringBuilder sb = new StringBuilder("  ");
		sb.repeat("-", size[0] * 2);
		sb.append("  ");
		sb.append(axisNames[0]);
		sb.append("\n  ");
		
		System.out.print(sb.toString());
		for (int i = 0; i < size[0]; i++) {
			System.out.print(i + " ");
		}
	}
}


