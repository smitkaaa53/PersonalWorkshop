package org.TEST;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class BTreeZigZagIteration 
{
	public static void main(String[] args) 
	{
		BNode node_1 = new BNode(1);
		
		BNode node_2 = new BNode(2);
		BNode node_3 = new BNode(3);
		
		BNode node_4 = new BNode(4);
		BNode node_5 = new BNode(5);
		
		BNode node_6 = new BNode(6);
		BNode node_7 = new BNode(7);
		
		BNode node_8 = new BNode(8);
		BNode node_9 = new BNode(9);
		BNode node_10 = new BNode(10);
		BNode node_11 = new BNode(11);
		
		BNode node_12 = new BNode(12);
		BNode node_13 = new BNode(13);
		BNode node_14 = new BNode(14);
		BNode node_15 = new BNode(15);
		
		
		
		node_1.left =node_2;
		node_1.right =node_3;
		
		node_2.left =node_4;
		node_2.right =node_5;
		
		node_3.left =node_6;
		node_3.right =node_7;
		
		node_4.left =node_8;
		node_4.right =node_9;
		
		node_5.left =node_10;
		node_5.right =node_11;
		
		node_6.left =node_12;
		node_6.right =node_13;
		
		node_7.left =node_14;
		node_7.right =node_15;
		
		/*System.out.print("\nDFS-");
		printDfs(node_1);
		
		System.out.print("\nBFS-");
		printBfs(node_1);
		*/
		/*System.out.print("\nBFS-AtLevel");
		printBfsAtLevel(node_1);*/
		
		/*System.out.print("\nZigZag:");
		printInZigZag(node_1);*/
		
		System.out.print("\nZigZag:" + zigzagLevelOrderWithStack(node_1));
		;
		
	}
	
	public static void printDfs(BNode n)
	{
		if(n==null)
			return;
		
		System.out.print(" "+ n.val);
		printDfs(n.left);
		printDfs(n.right);
	}
	
	
	public static void printBfs(BNode n)
	{
		if(n==null)
			return;
		
		Queue<BNode> queue = new LinkedList<>();
		
		queue.add(n);
		
		while(!queue.isEmpty())
		{
			BNode node = queue.remove();
			System.out.print(" "+ node.val);

			if(node.left!=null)
				queue.add(node.left);
			
			if(node.right!=null)
				queue.add(node.right);
		}
	}
	
	public static void printBfsAtLevel(BNode n)
	{
		if(n==null)
			return;
		
		Queue<BNode> queue = new LinkedList<>();
		Map<Integer, Queue<BNode>> atLevel = new HashMap<>();
		queue.add(n);
		
		int index=1;
		atLevel.put(index, queue);
		while(atLevel.containsKey(index))
		{
			System.out.print("\nAt Level["+index+"]:");
			Queue<BNode> atLevQueue = atLevel.get(index);
			queue = new LinkedList<>();
			
			while(!atLevQueue.isEmpty())
			{
				BNode node = atLevQueue.remove();
				System.out.print(" "+ node.val);

				if(node.left!=null)
					queue.add(node.left);
				
				if(node.right!=null)
					queue.add(node.right);
			}
			++index;
			if(!queue.isEmpty())
					atLevel.put(index, queue);
		}
	}
	
	
	/* Print nodes at a given level */
    static void printGivenLevel(BNode node, int level, boolean ltr) 
    {
        if (node == null) 
            return;
        if (level == 1) 
            System.out.print(node.val + " ");
        else
        {
             printGivenLevel((ltr?node.left:node.right), level - 1, ltr);
             printGivenLevel((ltr?node.right:node.left), level - 1, ltr);
        }
    }
    
    static int height(BNode node)
    {
        if (node == null) 
            return 0;
        
        return 1+ Math.max(height(node.left), height(node.right));
    }
    
    static void printInZigZag(BNode node) 
    {
        int h = height(node);
  
        boolean ltr = false;
        for (int i = 1; i <= h; i++) 
        {
            printGivenLevel(node, i, ltr);
            ltr = !ltr;
        }
    }
    
    
    public static List<List<Integer>> zigzagLevelOrder(BNode root) {
        List<List<Integer>> res = new ArrayList<>();
        travel(res, 0, root);
        return res;
    }
    private static void travel(List<List<Integer>> res, int level, BNode cur) 
    {
        if (cur == null) return;
        
        if (res.size() <= level) 
        {
            res.add(new ArrayList<Integer>());
        }
        
        if (level % 2 == 0) {
            res.get(level).add(cur.val);
        }   else {
            res.get(level).add(0, cur.val);
        }
        travel(res, level + 1, cur.left);
        travel(res, level + 1, cur.right);
    }
    
    public static List<List<Integer>> zigzagLevelOrderWith2Stack(BNode root) 
    {
    	BNode c=root;
    	List<List<Integer>> ans =new ArrayList<List<Integer>>();
    	   if(c==null) return ans;
    	   Stack<BNode> s1=new Stack<BNode>();
    	   Stack<BNode> s2=new Stack<BNode>();
    	   s1.push(root);
    	   while(!s1.isEmpty()||!s2.isEmpty())
    	   {
    	       List<Integer> tmp=new ArrayList<Integer>();
    	        while(!s1.isEmpty())
    	        {
    	            c=s1.pop();
    	            tmp.add(c.val);
    	            if(c.left!=null) 
    	            	s2.push(c.left);
    	            if(c.right!=null) 
    	            	s2.push(c.right);
    	        }
    	        ans.add(tmp);
    	        tmp=new ArrayList<Integer>();
    	        while(!s2.isEmpty())
    	        {
    	            c=s2.pop();
    	            tmp.add(c.val);
    	            if(c.right!=null)
    	            	s1.push(c.right);
    	            if(c.left!=null)
    	            	s1.push(c.left);
    	        }
    	        if(!tmp.isEmpty()) 
    	        	ans.add(tmp);
    	   }
    	   return ans;
    	}
    

    public static List<List<BNode>> zigzagLevelOrderWithStack(BNode root) 
    {
    	BNode c=root;
    	List<List<BNode>> ans =new ArrayList<>();
    	   if(c==null) return ans;
    	   Stack<BNode> s1=new Stack<BNode>();
    	   s1.push(root);
    	   boolean ltr=false;
    	   while(!s1.isEmpty())
    	   {
    	        List<BNode> tmp=new LinkedList<BNode>();
    	        while(!s1.isEmpty())
    	        {
    	            c=s1.pop();
    	            tmp.add(c);
    	        }
    	        
    	        ans.add(tmp);

    	        for(BNode n: tmp)
    	        {
    	        	if(ltr)
    	        	{
	    	            if(n.left!=null) 
	    	            	s1.push(n.left);
	    	            if(n.right!=null) 
	    	            	s1.push(n.right);
    	        	}else
    	        	{
	    	            if(n.right!=null) 
	    	            	s1.push(n.right);
    	        		if(n.left!=null) 
	    	            	s1.push(n.left);
    	        	}
    	        }
    	        ltr=!ltr;
    	   }
    	   return ans;
    	}

    

}

class BNode 
{
	int val;
	BNode right;
	BNode left;

	BNode(int val)
	{
		this.val = val;
	}
	
	@Override
	public String toString() {
		return val+"";
	}
}
