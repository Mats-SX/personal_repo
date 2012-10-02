package onlyPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
	
	private ArrayList<Matrix> matrices;
	//private Matrix resultMatrix;
	
	public InputHandler() {
		matrices = new ArrayList<Matrix>();
	}
	
	public void addMatrix(Matrix m) {
		matrices.add(m);
	}
	
	/*public void addMatrices(int rows, int columns, int quantity) {
		for (int i = 0; i < quantity; ++i) {
			addMatrix(new Matrix(rows, columns));
		}
	}*/
	
	public void readFromStdIn(Scanner scan, int quantity, int rows, int columns) {
		for (int h = 0; h < quantity; ++h) {
			System.out.println("-- New sheet --");
			int[][] matrix = new int[rows][columns];
			for (int i = 0; i < rows; ++i) {
				System.out.println("-- New row --");
				for (int j = 0; j < columns; ++j) {
					System.out.println("Enter next number: ");
					matrix[i][j] = scan.nextInt();
				}
			}
			Matrix m = new Matrix();
			m.setMatrix(matrix);
			matrices.add(m);
		}
		
	}

	private String addMatrices() {
		int[][] resultMatrix = new int[matrices.get(0).getMatrix().length][matrices.get(0).getMatrix()[0].length];
		for (int h = 0; h < matrices.size(); ++h) {
			int[][] matrix = matrices.get(h).getMatrix();
			for (int i = 0; i < matrix.length; ++i) {
				for (int j = 0; j < matrix[i].length; ++j) {
					resultMatrix[i][j] += matrix[i][j];
				}
			}
		}
		Matrix m = new Matrix();
		m.setMatrix(resultMatrix);
		return m.toCSVFile();
	}

	public String toCSVFile() {
		return addMatrices();
	}

}
