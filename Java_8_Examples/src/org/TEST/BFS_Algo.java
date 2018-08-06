package org.TEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_Algo
{

	public static void bfs_algo(Node root)
	{
		if(root == null)
		{
			return;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(root);
		
		bfs_algo(queue);
		
	}
	
	public static void dfs_algo(Node node)
	{
		if(node == null)
		{
			return;
		}
		
		System.out.println(" - " + node.value);
		
		for(Node children : node.adjusent)
			dfs_algo(children);
		
	}
	
	
	public static void bfs_algo(Queue<Node> nodeQueue)
	{
		if(nodeQueue==null || nodeQueue.isEmpty())
		{
			return;
		}
		
		Node node = nodeQueue.remove();
		
		System.out.println(" - " + node.value);
		
		nodeQueue.addAll(node.adjusent);
		
		bfs_algo(nodeQueue);
		
		
		
	}
	
	 public static void main(String args[]){
	        Node station1 = new Node("Westminster");
	        Node station2 = new Node("Waterloo");
	        Node station3 = new Node("Trafalgar Square", station1, station2);
	        
	        Node station4 = new Node("Canary Wharf");
	        Node station5 = new Node("London Bridge");
	        Node station6 = new Node("Tottenham Court Road", station5, station4);

	        Node station7 = new Node("Main Junction",station3, station6 );
	        
	        
	        //BreadthFirstSearch bfs = new BreadthFirstSearch(station6, station1);

	        bfs_algo(station7);
	       /* if(bfs.compute())
	            System.out.print("Path Found!");*/
	        
	        System.out.print("DFS NOW....");

	        dfs_algo(station7);

	 
	 }
	
	 public static void BFS(Node root)
		{
			Queue<Node> queue = new LinkedList<Node>();
			
			queue.add(root);
			
			BFS(queue);
			
		}
		
		public static void BFS(Queue<Node> queue)
		{
			if(queue==null || queue.isEmpty())
				return;
			
			Node node = queue.remove();
			System.out.println(node.value);
			
			queue.addAll(node.adjusent);
			
			BFS(queue);
		}
	
}

class Node
{
	public List<Node> adjusent;

	public String value;
	
	public Node(String value, Node... nodes)
	{
		this.value = value;
		if(nodes != null)
		{
			adjusent = Arrays.asList(nodes);	
		}
		
	}
	

}


	
	