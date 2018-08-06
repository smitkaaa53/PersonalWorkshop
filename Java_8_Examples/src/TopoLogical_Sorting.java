import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TopoLogical_Sorting
{

	public static List<DirectedGraph> topoSorting(List<DirectedGraph> graph)
	{
		if(graph==null || graph.isEmpty())
			return null;
		
		List<DirectedGraph> result = new LinkedList<>();
		Map<DirectedGraph, Integer> nodeCount = new HashMap<>();
		
		graph.forEach((node)->{
			node.neighbors.forEach((neighbor)->{
				if(nodeCount.containsKey(neighbor))
				{
					nodeCount.put(neighbor, nodeCount.get(neighbor)+1);
				}
				else
				{
					nodeCount.put(neighbor, 1);
				}
			});
		});
		
		System.out.println("counts" + nodeCount);
		graph.forEach((node)->{
			 if (!nodeCount.containsKey(node)) {
				 result.add(node);
				 System.out.println("Adding:" + node);
					node.neighbors.forEach((neighbor) -> {
						nodeCount.put(neighbor, nodeCount.get(neighbor)-1);
						if(nodeCount.get(neighbor)==0)
						{
							DFS(nodeCount, neighbor, result);
						}
					});
			 }
		});
		return result;
	}
	
	public static void DFS(Map<DirectedGraph, Integer> map, DirectedGraph n, List<DirectedGraph> result) {
		result.add(n);
		System.out.println("*Adding:" + n);
		n.neighbors.forEach((neighbor) -> {
			map.put(neighbor, map.get(neighbor)-1);
			if(map.get(neighbor)==0)
			{
				DFS(map, neighbor, result);
			}
		});
	}
	
	
    public static void main(String[] args) 
    {
    	DirectedGraph d_0 = new DirectedGraph(0);
    	DirectedGraph d_1 = new DirectedGraph(1);
    	DirectedGraph d_2 = new DirectedGraph(2);
    	DirectedGraph d_3 = new DirectedGraph(3);
    	DirectedGraph d_4 = new DirectedGraph(4);
    	DirectedGraph d_5 = new DirectedGraph(5);
    	
    	DirectedGraph d_6 = new DirectedGraph(6);
    	
    	d_0.neighbors.add(d_1);
    	d_0.neighbors.add(d_2);
    	d_0.neighbors.add(d_3);
    	
    	d_1.neighbors.add(d_4);
    	
    	//d_2.neighbors.add(d_4);
    	d_2.neighbors.add(d_5);
    	
    	//d_3.neighbors.add(d_4);
    	d_3.neighbors.add(d_5);
    	
    	
    	System.out.println(Arrays.toString(Arrays.asList(d_5,d_4,d_0,d_3,d_2,d_1,d_6).toArray()));
    	
    	
    	System.out.println(Arrays.toString(topoSorting(Arrays.asList(d_6,d_5,d_4,d_0,d_3,d_2,d_1)).toArray()));
    	
    	
	}

	
}
class DirectedGraph
{
	int value;
	List<DirectedGraph> neighbors;
	
	public DirectedGraph(int value)
	{
		this.value = value;
		this.neighbors = new ArrayList<>();
	}

	@Override
	public String toString() 
	{
		return String.valueOf(value);
	}
}