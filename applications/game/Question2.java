package com.applications.game;

import java.util.Scanner;

public class Question2 {

	public static void main(String[] args) {

		new Question2().getInputs();
	}

	private void getInputs() {

		Scanner scanner = new Scanner(System.in);
		try {

			//Input for Row * Column
			System.out.print("Dimens Of dungeon(Row * Column): ");
			String rowCol = scanner.nextLine();
			String[] splitRowCol = rowCol.split(" ");
			int row = Integer.parseInt(splitRowCol[0]);
			int col = Integer.parseInt(splitRowCol[1]);

			//Input for Adventure Position
			System.out.print("Position of Adventure: ");
			String posAd = scanner.nextLine();
			String[] splitAd = posAd.split(" ");
			int adRow = Integer.parseInt(splitAd[0]);
			int adCol = Integer.parseInt(splitAd[1]);

			//Input for Monster Position
			System.out.print("Position of Monstar: ");
			String posMonster = scanner.nextLine();
			String[] splitMonster = posMonster.split(" ");
			int monsterRow = Integer.parseInt(splitMonster[0]);
			int monsterCol = Integer.parseInt(splitMonster[1]);
			
			//Input for Gold Position
			System.out.print("Position of Gold: ");
			String posGold = scanner.nextLine();
			String[] splitGold = posGold.split(" ");
			int goldRow = Integer.parseInt(splitGold[0]);
			int goldCol = Integer.parseInt(splitGold[1]);

			//Create a Board
			char[][] board = new char[row][col];

			//find distance for One way(Subtraction)
			setDetailsInBoard(board, row, col, goldRow, goldCol, monsterRow, monsterCol);
			int steps = findDistenceOneWay(board, row, col, adRow, adCol, goldRow, goldCol);
			int monsterSteps = findDistenceOneWay(board, row, col, monsterRow, monsterCol, goldRow, goldCol);
			System.out.println("Steps : " + steps);
			System.out.println("Steps : " + monsterSteps);
			System.out.println(steps <= monsterSteps ?"Minimum Number of Steps : " + steps:"No possible Solution");
			
			//find Distance for Second Way
			steps = findDistenceSecondWay(board, row, col, adRow, adCol, goldRow, goldCol, 0);
			monsterSteps = findDistenceSecondWay(board,row,col,monsterRow,monsterCol,goldRow,goldCol,0);
			System.out.println("Steps : " + steps);
			System.out.println("Monster Steps : " + monsterSteps);
			System.out.println(steps <= monsterSteps ?"Minimum Number of Steps : " + steps:"No possible Solution");

		} catch (Exception e) {

			System.out.println("Wrong Input!!!\n" + e.getMessage());
		} finally {

			scanner.close();
		}
	}

	private int findDistenceSecondWay(char[][] board, int row, int col, int adRow, int adCol, int goldRow, int goldCol,
			int count) {

		try {

			if (adRow == goldRow && goldCol == adCol) {

				return count;
			} else if (adRow == goldRow) {

				System.out.println("Row: "+adRow+" adCol: "+adCol+" goldRow : "+goldRow+" goldCol: "+goldCol);
				if (adCol < goldCol) {

					count = findDistenceSecondWay(board, row, col, adRow, adCol + 1, goldRow, goldCol, count + 1);
				} else if(adCol > goldCol){

					count = findDistenceSecondWay(board, row, col, adRow, adCol - 1, goldRow, goldCol, count + 1);
				}
			} else {

				if (adRow < goldRow) {

					count = findDistenceSecondWay(board, row, col, adRow + 1, adCol, goldRow, goldCol, count + 1);
				} else if(adRow > goldRow){

					count = findDistenceSecondWay(board, row, col, adRow - 1, adCol, goldRow, goldCol, count + 1);
				}
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
