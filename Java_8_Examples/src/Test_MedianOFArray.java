import java.util.Arrays;

public class Test_MedianOFArray 
{
	
	public static int medianOfTwoSortedArrSameSIZE(int[] arr1, int[] arr2)
	{
		if(arr1==null || arr2==null || arr1.length<=0 || arr1.length<=0)
			return 0;
		
		return medianOfTwoSortedArrSameSIZE(arr1, arr2, 0, arr1.length-1, 0,arr2.length-1);
	}
	
	public static int medianOfTwoSortedArrSameSIZE(int[] arr1, int[] arr2, int st1, int ed1, int st2, int ed2)
	{
		System.out.println(Arrays.toString(arr1)+" - "+Arrays.toString(arr2));
		System.out.println(st1+"-" +ed1+" : "+st2+"-" +ed2);
		
		if(ed1-st1==1 && ed2-st2==1)
		{
			return (Math.max(arr1[st1], arr2[st2]) + Math.min(arr1[ed1], arr2[ed2]))/2; 
		}
		
		int m1 = getMedian(arr1, st1, ed1);
		int m2 = getMedian(arr2, st2, ed2);
		
		if(m1<m2)
		{
			return medianOfTwoSortedArrSameSIZE(arr1, arr2, (ed1+st1)/2, ed1, st2, (ed2+st2)/2);
		}
		else if(m1>m2)
		{
			return medianOfTwoSortedArrSameSIZE(arr1, arr2, st1, (ed1+st1)/2,(ed2+st2)/2,ed2);
		}
		return m1;
		
	}
	
	static int getMedian(int[] arr1, int st1, int ed1)
	{
		return arr1[(ed1+st1)/2];
	}
	
	public static void main(String[] args) {
		 int[] arr1 = new int[]{1, 12, 15, 26, 38};
		 int[]  arr2 =new int[]{2, 13, 17, 30, 45};
		 
		//System.out.println(medianOfTwoSortedArrSameSIZE(arr1, arr2)); 
		
		arr1 = new int[]{1, 2, 3, 6};
		arr2 =new int[]{4, 6, 8, 10};
		 
		System.out.println(medianOfTwoSortedArrSameSIZE(arr1, arr2));
		
		 
	}
}
