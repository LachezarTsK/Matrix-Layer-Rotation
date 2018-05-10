import java.util.*;

public class VarForTest {

		public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int rows = reader.nextInt();
		int columns = reader.nextInt();
		int rotations = reader.nextInt();

		TreeMap<Integer, LinkedList<Integer>> grid = new TreeMap<Integer, LinkedList<Integer>>();
		int halfRows = 0;
		if (rows <= columns) {
			halfRows = (rows / 2) + (rows % 2);
		} else if (rows > columns) {
			halfRows = (columns / 2) + (columns % 2);
		}
		for (int i = 0; i < halfRows; i++) {
			LinkedList<Integer> ring = new LinkedList<Integer>();

			grid.put(i, ring);
			for (int j = 0; j < columns; j++) {
				int input = reader.nextInt();
				if (j < i) {

					grid.get(j).add(grid.get(j).size() - (i - 1 - j), input);

				} else if (j >= columns - i) {
					grid.get(columns - 1 - j).add(grid.get(columns - 1 - j).size() - (i - (columns - 1 - j)), input);
				} else if (j >= i && j <= columns - 1 - i) {
					grid.get(i).add(input);
				}
			}
		}
		for (int i = halfRows; i < halfRows + rows - columns; i++) {

			for (int j = 0; j < columns; j++) {
				int input = reader.nextInt();
				if (j < halfRows - 1) {
					grid.get(j).add(grid.get(j).size() - (i - 1 - j), input);

				} else if (j > halfRows) {

					grid.get(columns - 1 - j).add(grid.get(columns - 1 - j).size() - (i - (columns - 1 - j)), input);
				} else {
					grid.get(halfRows - 1).add(grid.get(halfRows - 1).size() - (i - halfRows) - (j - (halfRows - 1)),
							input);
				}
			}
		}
		int additionWhenRowsAreGreaterThanColumns = 0;
		if (rows - columns >= 0) {
			additionWhenRowsAreGreaterThanColumns = rows - columns;
		}
		for (int i = halfRows + additionWhenRowsAreGreaterThanColumns; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int input = reader.nextInt();
				int reverse = rows - i;
				if (j < reverse && i < rows - 1 - j) {

					grid.get(j).add(grid.get(j).size() - (i - 1 - j), input);
				} else if (j >= reverse - 1 && j <= columns - reverse) {
					grid.get(reverse - 1).add(grid.get(reverse - 1).size() - (i - (reverse)) - (j - (reverse - 1)),
							input);

				} else if (j > columns - reverse) {

					grid.get(columns - 1 - j).add(columns - 2 * (columns - 1 - j) + i - (columns - 1 - j) - 1, input);
				}
			}
		}

		getResults(rows, columns, rotations, grid);
	}

	public static void getResults(int rows, int columns, int rotations, TreeMap<Integer, LinkedList<Integer>> grid) {
		int[][] rotatedMatrix = new int[rows][columns];

		for (Integer n : grid.keySet()) {
			int	rotaionWithoutRepeat = rotations % (grid.get(n).size());
			grid.get(n).addAll(grid.get(n).subList(0, rotaionWithoutRepeat));
			grid.get(n).subList(0, rotaionWithoutRepeat).clear();

			int counterColumnsOne = 0;
			int counterRowsOne = n + 1;
			int counterColumnsTwo = columns - 1 - n;
			int counterRowsTwo = rows - 1 - (n + 1);
			for (int i = 0; i < grid.get(n).size(); i++) {
				if (i < columns - 2 * n) {
					rotatedMatrix[n][n + counterColumnsOne] = grid.get(n).get(i);
					counterColumnsOne++;

				} else if (i >= columns - 2 * n
						&& i < columns - 2 * n - 1 + rows - 2 * n - 1) {
					rotatedMatrix[counterRowsOne][columns - 1 - n] = grid.get(n).get(i);
					counterRowsOne++;
				} else if (i >= columns - 2 * n - 1 + rows - 2 * n - 1
						&& i < columns - 2 * n - 1 + rows - 2 * n - 1 + columns - 2 * n) {
					rotatedMatrix[rows - 1 - n][counterColumnsTwo] = grid.get(n).get(i);
					counterColumnsTwo--;
				} else if (i>= columns - 2 * n - 1 + rows - 2 * n - 1 + columns - 2 * n
						&& i < columns - 2 * n - 1 + rows - 2 * n - 1 + columns - 2 * n - 1
								+ rows - 2 * n - 1) {
					rotatedMatrix[counterRowsTwo][n] = grid.get(n).get(i);
					counterRowsTwo--;
				}

			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (j < columns - 1) {
					System.out.print(rotatedMatrix[i][j] + " ");
				} else {
					System.out.print(rotatedMatrix[i][j]);
				}
			}
			if (i < rows - 1) {
				System.out.println();
			}
		}
	}
}
