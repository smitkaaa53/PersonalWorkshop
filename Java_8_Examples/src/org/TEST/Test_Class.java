package org.TEST;

public class Test_Class 
{
	
	public static int findAllWay(int[][] board, int currRow, int currCol)
	{
		//System.out.println("Iteration row/col: " + currRow + " / " + currCol);
		if(board[0].length<=currCol || board.length<=currRow)
		{
			System.out.println("row/col - OutOfIndex: " + currRow + " / " + currCol);
			return 0;
		}
		if(board[currRow][currCol] == -1)
		{
			System.out.println("Find Blockage: " + currRow + " / " + currCol);
			return 0;
		}
		if(board[currRow][currCol] == 100)
		{
			System.out.println("Find Destination: " + currRow + " / " + currCol);
			return 1;
		}
		return findAllWay(board, currRow, currCol+1) + findAllWay(board, currRow+1, currCol) ;
	}
	
	public static void main(String[] args) 
	{
		/*int[][] board = new int[4][4];
		
		board[3][3]=100;
		board[1][1]=-1;
		board[2][2]=-1;*/
		
		int[][] board = new int[3][3];
		
		board[2][2]=100;
		board[0][1]=-1;
		board[1][1]=-1;
		
		
		
		System.out.println("No Of Ways: " + findAllWay(board, 0, 0));
		
	}
	

}
