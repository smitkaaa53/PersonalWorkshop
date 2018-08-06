package org.leetcode;

public class LongestPalindrom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String LongestPalindrom_1(String str)
	{
		if(str==null || str.length()<2)
			return str;
		
		String longestStr = str.substring(0, 1);
		for(int i=0;i<str.length();i++)
		{
			 String newStr = helper(str, i, i);
			 if(newStr.length()>longestStr.length())
			 {
				 longestStr = newStr;
			 }
			 
			 newStr = helper(str, i, i+1);
			 if(newStr.length()>longestStr.length())
			 {
				 longestStr = newStr;
			 }
		}
		
		return longestStr;
	}
	
	public String helper(String str, int start, int end)
	{
		while(start>=0 && end<=str.length()-1 && str.charAt(start)==str.charAt(end))
		{
			start--;
			end++;
		}
		return str.substring(start+1, end);
	}
	
	
	public String LongestPalindrom_2(String str)
	{
		if(str==null || str.length()<2)
			return str;
	
		int length = str.length();
		Boolean[][] counter = new Boolean[length][length];
		
		int left=0;
		int right=0;
		
		for(int j=1;j<length;j++)
		{
			for(int i=0; i<j;i++)
			{
				boolean isInnerPalin = counter[i+1][j-1] || (j-i)<=2;
				
				if(str.charAt(i)==str.charAt(j) && isInnerPalin)
				{
					counter[i][j]=true;
					if(j-i > right-left)
					{
						left=i;
						right=j;
					}
				}
			}
		}
		return str.substring(left, right+1);
	}

}
