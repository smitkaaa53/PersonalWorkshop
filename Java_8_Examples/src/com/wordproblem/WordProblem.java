package com.wordproblem;

public class WordProblem 
{
	public static void main(String[] args) 
	{
		int[][] matrix = getMatrix();
		findWords("", matrix, 0,  matrix.length-1);
	}
	
	public static void findWords(String prefix, int[][] matrix, int start, int end)
	{
		for(int i=start; i<=matrix.length-1;i++)
		{
			for(int j = i+1; j<end;j++)
			{
				String word = prefix + matrix[i][j];
				findWords(word, matrix, i, j);
				/*if(isWord(word))
				{
					
				}*/
				System.out.println(word);
			}
		}
	}
	
	

	public static boolean isWord(String str)
	{
		return true;
	}
	
	public static int[][] getMatrix()
	{
		return new int[][] 
				{{'a','b'},
				{'t','o'}
			};
	}
}
