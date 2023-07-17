package com.applications.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question4 {

	public static void main(String[] args) {

		new Question4().getInputs();
	}

	private void getInputs() {

		Scanner scanner = new Scanner(System.in);
		try {

			System.out.print("Dimens Of dungeon(Row * Column): ");
			String rowCol = scanner.nextLine();
			String[] splitRowCol = rowCol.split(" ");
			int row = Integer.parseInt(splitRowCol[0]);
			int col = Integer.parseInt(splitRowCol[1]);

			// Input for Adventure
			System.out.print("Position of Adventure: ");
			String posAd = scanner.nextLine();
			String[] splitAd = posAd.split(" ");
			int adRow = Integer.parseInt(splitAd[0]);
			int adCol = Integer.parseInt(splitAd[1]);

			// Input for Monster
			System.out.print("Position of Monstar: ");
			String posMonster = scanner.nextLine();
			String[] splitMonster = posMonster.split(" ");
			int monsterRow = Integer.parseInt(splitMonster[0]);
			int monsterCol = Integer.parseInt(splitMonster[1]);

			// Input for Trigger
			System.out.print("Position of Trigger : ");
			String posTrigger = scanner.nextLine();
			String[] splitTrigger = posTrigger.split(" ");
			int triggerRow = Integer.parseInt(splitTrigger[0]);
			int triggerCol = Integer.parseInt(splitTrigger[1]);

			// Input for Gold
			System.out.print("Position of Gold: ");
			String posGold = scanner.nextLine();
			String[] splitGold = posGold.split(" ");
			int goldRow = Integer.parseInt(splitGold[0]);
			int goldCol = Integer.parseInt(splitGold[1]);

			// Process
			process(row, col, adRow, adCol, monsterRow, monsterCol, goldRow, goldCol, triggerRow, triggerCol);
		} catch (Exception e) {

			System.out.println("Wrong Input!!!\n" + e.getMessage());
		} finally {

			scanner.close();
		}
	}

	private void process(int row, int col, int adRow, int adCol, int monsterRow, int monsterCol, int goldRow,
			int goldCol, int triggerRow, int triggerCol) {

		try {

			// Create Board
			char[][] board = new char[row][col];

			// Set Details In Board
			setDetailsInBoard(board, row, col, goldRow, goldCol, monsterRow, monsterCol);

			// Find Distance for One Way(Subtraction)
			int steps = findDistanceOneWay(board, row, col, adRow, adCol, goldRow, goldCol);
			int monsterSteps = findDistanceOneWay(board, row, col, monsterRow, monsterCol, goldRow, goldCol);
			System.out.println("Steps : " + steps);
			System.out.println("Monster Steps : " + monsterSteps);

			System.out.println(steps <= monsterSteps ? "Minimum Number of Steps : " + steps : "No possible Solution");

			// Print Path
			if (steps <= monsterSteps) {

				movingSecondWay(board, row, col, adRow, adCol, goldRow, goldCol, monsterRow, monsterCol, triggerRow,
						triggerCol);
			} else {
				List<String> triggerPath = new ArrayList<>();
				List<String> adventurePath = new ArrayList<>();
				steps = findDistanceSecondWay(board, row, col, adRow, adCol, triggerRow, triggerCol, 0,
						adventurePath);
				int triggerSteps = findDistanceSecondWay(board, row, col, triggerRow, triggerCol, goldRow, goldCol, 0,
						triggerPath);
				System.out.println("Minimum number Of Steps : " + (steps + triggerSteps));
//				System.out.println(steps + " "+ triggerSteps);
				System.out.println("Adventure Path : " + adventurePath);
				System.out.println("Trigger Path : " + triggerPath);
			}

		} catch (Exception e) {

			System.out.println("Index Reached!!\n" + e.getMessage());
		}
	}

	private void movingSecondWay(char[][] board, int row, int col, int adRow, int adCol, int goldRow, int goldCol,
			int monsterRow, int monsterCol, int triggerRow, int triggerCol) {

		try {

			List<String> adventurePath = new ArrayList<>();
			List<String> monsterPath = new ArrayList<>();

			// Find Distance for Second Way(Recursion)
			int steps = findDistanceSecondWay(board, row, col, adRow, adCol, goldRow, goldCol, 0, adventurePath);
			int monsterSteps = findDistanceSecondWay(board, row, col, monsterRow, monsterCol, goldRow, goldCol, 0,
					monsterPath);

			System.out.println("Adventurer Path : " + adventurePath);
			System.out.println("Monster Path : " + monsterPath);
			System.out.println("Steps : " + steps);
			System.out.println("Monster Steps : " + monsterSteps);
			System.out.println(steps <= monsterSteps ? "Minimum Number of Steps : " + steps : "No possible Solution");
		} catch (Exception e) {

			System.out.println("Wrong Output!!!\n" + e.getMessage());
		}
	}

	private int findDistanceSecondWay(char[][] board, int row, int col, int rowPos, int colPos, int desRow, int desCol,
			int count, List<String> path) {

		try {

			path.add(rowPos + "," + colPos);
			if (rowPos == desRow && desCol == colPos) {

				return count;
			} else if (rowPos == desRow) {

				if (colPos < desCol) {

					count = findDistanceSecondWay(board, row, col, rowPos, colPos + 1, desRow, desCol, count + 1, path);
				} else if (colPos > desCol) {

					count = findDistanceSecondWay(board, row, col, rowPos, colPos - 1, desRow, desCol, count + 1, path);
				}
			} else {

				if (rowPos < desRow) {

					count = findDistanceSecondWay(board, row, col, rowPos + 1, colPos, desRow, desCol, count + 1, path);
				} else if (rowPos > desRow) {

					count = findDistanceSecondWay(board, row, col, rowPos - 1, colPos, desRow, desCol, count + 1, path);
				}
			}
			return count;
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
		return 0;
	}

	private int findDistanceOneWay(char[][] board, int row, int col, int adRow, int adCol, int goldRow, int goldCol) {

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

	private void setDetailsInBoard(char[][] board, int row, int col, int goldRow, int goldCol, int monsterRow,
			int monsterCol) {

		try {

			board[goldRow - 1][goldCol - 1] = 'G';
			board[monsterRow - 1][monsterCol - 1] = 'M';
		} catch (Exception e) {

			System.out.println("Index Reached!!\n" + e.getMessage());
		}
	}
}
