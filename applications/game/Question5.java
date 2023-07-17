package com.applications.game;

import java.util.Scanner;

public class Question5 {

	private int count = Integer.MAX_VALUE;

	public static void main(String[] args) {

		new Question5().getInputs();
	}

	private void getInputs() {

		Scanner scanner = new Scanner(System.in);
		try {

			System.out.print("Dimens Of dungeon(Row * Column): ");
			String rowCol = scanner.nextLine();
			String[] splitRowCol = rowCol.split(" ");
			int row = Integer.parseInt(splitRowCol[0]);
			int col = Integer.parseInt(splitRowCol[1]);

			System.out.print("Position of Adventure: ");
			String posAd = scanner.nextLine();
			String[] splitAd = posAd.split(" ");
			int adRow = Integer.parseInt(splitAd[0]);
			int adCol = Integer.parseInt(splitAd[1]);

			System.out.print("Position of Gold: ");
			String posGold = scanner.nextLine();
			String[] splitGold = posGold.split(" ");
			int goldRow = Integer.parseInt(splitGold[0]);
			int goldCol = Integer.parseInt(splitGold[1]);

			System.out.print("Enter Number Of Pits: ");
			int pits = scanner.nextInt();
			String[] posPits = new String[pits];
			scanner.nextLine();
			for (int i = 0; i < pits; ++i) {

				System.out.print("Position Of Pits " + (i + 1) + ": ");
				posPits[i] = scanner.nextLine();
			}
			char[][] board = new char[row][col];

			String[][]content = new String[row][col];
			setDetailsInBoard(board, row, col, goldRow, goldCol, pits, posPits);
			int steps = findDistenceOneWay(board, row, col, adRow, adCol, goldRow, goldCol);
			System.out.println("Steps : " + steps);
			steps = findDistenceSecondWay(content, board, row, col, adRow, adCol, goldRow, goldCol, ' ', 0);
			System.out.println(this.count != Integer.MAX_VALUE ? "Minimum Number Of Steps : " + this.count
					: "No Possible Solutions");
			System.out.println(steps);

		} catch (Exception e) {

			System.out.println("Wrong Input!!!\n" + e.getMessage());
		} finally {

			scanner.close();
		}
	}

	private int findDistenceSecondWay(String[][] content, char[][] board, int row, int col, int rowPos, int colPos,
			int desRow, int goldCol, char s, int count) {

		try {

			if (rowPos == desRow && goldCol == colPos) {

				if (count < this.count) {

					this.count = count;
				}
				return count;
			}
			if (content[rowPos - 1][colPos - 1] == "reach" || board[rowPos - 1][colPos - 1] == 'P') {

				return count;
			}
			if (s != 'R' && colPos + 1 < col + 1) {

				content[rowPos - 1][colPos - 1] = "reach";
				count = findDistenceSecondWay(content, board, row, col, rowPos, colPos + 1, desRow, goldCol, 'L',
						count + 1);
				count -= 1;
				content[rowPos - 1][colPos - 1] = "";
			}
			if (s != 'B' && rowPos + 1 < row + 1) {

				content[rowPos - 1][colPos - 1] = "reach";
				count = findDistenceSecondWay(content, board, row, col, rowPos + 1, colPos, desRow, goldCol, 'T',
						count + 1);
				count -= 1;
				content[rowPos - 1][colPos - 1] = "";
			}
			if (s != 'L' && colPos - 1 > 0) {

				content[rowPos - 1][colPos - 1] = "reach";
				count = findDistenceSecondWay(content, board, row, col, rowPos, colPos - 1, desRow, goldCol, 'R',
						count + 1);
				count -= 1;
				content[rowPos - 1][colPos - 1] = "";
			}
			if (s != 'T' && rowPos - 1 > 0) {

				content[rowPos - 1][colPos - 1] = "reach";
				count = findDistenceSecondWay(content, board, row, col, rowPos - 1, colPos, desRow, goldCol, 'B',
						count + 1);
				count -= 1;
				content[rowPos - 1][colPos - 1] = "";
			}
			return count;
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
		return 0;
	}


	private int findDistenceOneWay(char[][] board, int row, int col, int adRow, int adCol, int goldRow, int goldCol) {

		try {

			int rowDiff = adRow - goldRow;
			int colDiff = adCol - goldCol;
			if (rowDiff < 0) {
				rowDiff = rowDiff * (-1);
			}
			if (colDiff < 0) {
				colDiff = colDiff * (-1);
			}
			return (rowDiff + colDiff);
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
		return 0;
	}

	private void setDetailsInBoard(char[][] board, int row, int col, int goldRow, int goldCol, int pits,
			String[] posPits) {

		try {

			board[goldRow - 1][goldCol - 1] = 'G';
			for (int i = 0; i < pits; ++i) {

				String[] splitPits = posPits[i].split(" ");
				board[Integer.parseInt(splitPits[0])-1][Integer.parseInt(splitPits[1])-1] = 'P';
			}
		} catch (Exception e) {

			System.out.println("Index Reached!!\n" + e.getMessage());
		}
	}
}
