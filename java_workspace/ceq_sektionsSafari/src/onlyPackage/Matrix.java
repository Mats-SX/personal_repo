package onlyPackage;

public class Matrix {
	private int[][] matrix;
	
	/*public Matrix(int rows, int columns) {
		setSize(rows, columns);
	}*/
	
	private void setSize(int rows, int columns) {
		matrix = new int[rows][columns];
	}
	
	public void setMatrix(int[][] m) {
		matrix = m;
	}
	
	/**
	 * Converts this matrix into a string that can be printed to file and then read by eg. MATLAB. Semicolon separates column values
	 * @return
	 */
	public String toCSVFile() {
		StringBuilder sb = new StringBuilder();
		sb.append("Row#;#Ans1;#Ans2;#Ans3;#Ans4;#Ans5;\n");		// hard-coded title string
		for (int i = 0; i < matrix.length; ++i) {
			sb.append(i + 1).append(";");
			for (int j = 0; j < matrix[i].length; ++j) {
				sb.append(matrix[i][j]).append(";");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String toString() {
		return String.valueOf(matrix);
	}

	public int[][] getMatrix() {
		return matrix;
	}

}
