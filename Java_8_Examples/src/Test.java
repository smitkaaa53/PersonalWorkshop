import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.sun.xml.internal.ws.util.StringUtils;

public class Test 
{
	
	public static int[][] sort(int[][] array)
	{
		Arrays.sort(array, (a,b)->{
			return a[0]!=b[0]?-a[0]+b[0]:a[1]-b[1];
		});
		
		System.out.println("After Sorting:\t" + Arrays.deepToString(array));
		
		List<int[]> linlList = new LinkedList<int[]>();
		
		for(int[] cay:array) 
		{
			linlList.add(cay[1],cay);
			System.out.println(cay[1]+"--"+ Arrays.toString(cay) + " : " + Arrays.deepToString(linlList.toArray(new int[][] {})));
		}
		
		return linlList.toArray(new int[][] {});		
	}
	
	/*public static void main(String[] args) {
		//[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
		int[][] array = new int[][] {
			new int[] {7,0},
			new int[] {4,4},
			new int[] {7,1},
			new int[] {5,0},
			new int[] {6,1},
			new int[] {5,2},
			};
			
			System.out.println("Input:\t\t" + Arrays.deepToString(array));
			
			System.out.println("Output:\t\t" + Arrays.deepToString(sort(array)));
	}*/
	
	public void test()
	{
		int[] array = new int[] {5,6,3,2,8};

		/*List<Integer> list = new ArrayList<Integer>();
		
		list.addAll(Arrays.stream(array).);
		
		IntStream mapToLong = Arrays.stream(array);
		
		Collections.addAll(list, Arrays.stream(array).boxed().toArray(Integer[]::new));*/
		
		List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
		
	}
	
	 public static List<List<String>> accountsMerge(List<List<String>> accounts) 
	 {
		 List<List<String>> res = new ArrayList<>();
		 Map<String, AcctNode> map = new HashMap<String,AcctNode>();
		 
		 accounts.forEach((list)->{
			 String userName = list.get(0);
			 for(int j=1;j<list.size();j++)
			 {
				 String email = list.get(j);
				 if(!map.containsKey(email))
				 {
					 map.put(email,new AcctNode(email, userName));
				 }
				 
				 if(j==1)
					 continue;
				 
				 String preEmail = list.get(j-1);
				 map.get(preEmail).neighbors.add(map.get(email));
				 map.get(email).neighbors.add(map.get(preEmail));
			 }
		 });
		 
		 System.out.println(map);
		 
		 List<String> visited = new ArrayList<>();
		 
		 map.forEach((key,val)->{
			 if(!visited.contains(key))
			 {
				 visited.add(key);
				 List<String> list = new LinkedList<>();
				 list.add(key);
				 dfs(val,visited,list);
				 Collections.sort(list);
				 list.add(0, val.userName);
				 res.add(list);
			 }
		 });
		 
		 
		 
		 return res;
	 }
	 
	 public static void dfs(AcctNode root, List<String> visited, List<String> list)
	 {
		 root.neighbors.forEach((node)->{
			 if(!visited.contains(node.val))
			 {
				 visited.add(node.val);
				 list.add(node.val);
				 dfs(node,visited,list);
			 }
		 });
	 }
	 
	 /*accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]*/
	 public static void main(String[] args) 
	 {
		 List<List<String>> accounts = new ArrayList<>();
		 
		 /*accounts.add(Arrays.stream(new String[] {"John", "johnsmith@mail.com", "john00@mail.com"}).collect(Collectors.toList()));
		 accounts.add(Arrays.stream(new String[] {"John", "johnnybravo@mail.com"}).collect(Collectors.toList()));
		 accounts.add(Arrays.stream(new String[] {"John", "johnsmith@mail.com", "john_newyork@mail.com"}).collect(Collectors.toList()));
		 accounts.add(Arrays.stream(new String[] {"Mary", "mary@mail.com"}).collect(Collectors.toList()));*/
		 
		 /*accounts.add(Arrays.stream(new String[] {"John", "J1", "J2"}).collect(Collectors.toList()));
		 accounts.add(Arrays.stream(new String[] {"John", "J3"}).collect(Collectors.toList()));
		 accounts.add(Arrays.stream(new String[] {"John", "J1", "J4"}).collect(Collectors.toList()));
		 accounts.add(Arrays.stream(new String[] {"Mary", "M1"}).collect(Collectors.toList()));
		 
		 System.out.println(accounts);
		System.out.println("O/P - "+accountsMerge(accounts));*/
		 
		 System.out.println(decode(encode("abc")));
	}
	 
	 public static String encode(String msg)
	 {
		 StringBuilder stb = new StringBuilder();
		 for(int i=0;i<msg.length();i++)
		 {
			 char c = msg.charAt(i);
			 char c1 = (char) (c+i);
			 char ch = (char) (c + c1);
			// System.out.println(c+"-"+c1+"-"+ch);
			// System.out.println(Integer.toBinaryString(c)+"-"+Integer.toBinaryString(c1)+"-"+Integer.toBinaryString(ch));
			 
			 stb.append(ch);
		 }
		 return stb.toString();
	 }
	 
	 public static String decode(String msg)
	 {
		 StringBuilder stb = new StringBuilder();
		 for(int i=0;i<msg.length();i++)
		 {
			 char c = msg.charAt(i);
			 char c1 = (char) (c+i);
			 char ch = (char)(c >> c1);
			 System.out.println(c+"-"+c1+"-"+ch);
			 //System.out.println(Integer.toBinaryString(c)+"-"+Integer.toBinaryString(c1)+"-"+Integer.toBinaryString(ch));
			 
			 stb.append(ch);
		 }
		 return stb.toString();
	 }
	 
	 

}
class AcctNode
{
	 String val;//email id
	 String userName;
	 List<AcctNode> neighbors;
	 
	 AcctNode(String val, String userName)
	 {
		 this.userName = userName;
		 this.val = val;
		 neighbors = new ArrayList<>();
	 }
	 
	 @Override
	public String toString() {
		return val+"-"+userName+ " : " ;
	}
}
