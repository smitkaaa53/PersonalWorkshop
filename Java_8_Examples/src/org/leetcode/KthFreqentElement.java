package org.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class KthFreqentElement
{
	
	public static void main(String... args){
        Map<Integer, String> random = new HashMap<Integer, String>();
        for (int i = 0; i < 10; i++) {
            random.put((int)(Math.random() * 100), String.valueOf((int) (Math.random() * 100)));
        }

        System.out.println("Initial Map: " + Arrays.toString(random.entrySet().toArray()));

        Map<Integer, String> sortedMap =
                random.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println("Sorted Map: " + Arrays.toString(sortedMap.entrySet().toArray()));
    }
	
	/*public static void main(String[] args) {
		System.out.println(kthFrequentElement(new int[]{1,2,3,4,2,3,3,4,4,4,5,6,6,6,6}, 4));
	}*/
	
	public static int kthFrequentElement(int[] arr, int k)
	{
		if(arr==null || arr.length<0 || arr.length<k)
			return -1;
		
		Map<Integer, Integer> countMap = new TreeMap<>((t1,t2)->(t1-t2));
		
		for(int i=0; i<arr.length;i++)
		{
			if(countMap.containsKey(arr[i]))
			{
				countMap.put(arr[i], countMap.get(arr[i])+1);
			}
			else
			{
				countMap.put(arr[i], 1);
			}
		}
		
		System.out.println("CountMap: " + countMap);
		
		/*if(countMap.size()<k)
		{
			return -1;
		}*/

		return new ArrayList<>(countMap.keySet()).get(k-1);
		
	
	}

	
	/*public static int kthFrequentElement(int[] arr, int k)
	{
		if(arr==null || arr.length<0 || arr.length<k)
			return -1;
		
		Map<Integer, Integer> countMap = new Hashtable<>();
		
		for(int i=0; i<arr.length;i++)
		{
			if(countMap.containsKey(arr[i]))
			{
				countMap.put(arr[i], countMap.get(arr[i])+1);
			}
			else
			{
				countMap.put(arr[i], 1);
			}
		}
		System.out.println("CountMap: " + countMap);
		if(countMap.size()<k)
			return -1;
		
		List<Map.Entry<Integer,Integer>> list = new ArrayList<>(countMap.entrySet());
		
		Collections.sort(list, (t1,t2)-> (t2.getValue()-t1.getValue()));
		
		System.out.println("List Sorted:" + list);
		
		return list.get(k-1).getKey();
			
	}*/
	

}
