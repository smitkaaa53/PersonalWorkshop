import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_TopSort {

	public static List<DG> topoSorting(List<DG> graph)
	{
		if(graph==null || graph.isEmpty())
			return null;
		
		List<DG> result = new ArrayList<>();
		Map<DG, Integer> nodeCount = new HashMap<>();
		
		//graph.neighbors.
		graph.forEach((node)->{
			node.neighbors.forEach((subNode)->{
				if(nodeCount.containsKey(subNode))
				{
					nodeCount.put(subNode, nodeCount.get(subNode)+1);	
				}
				else
				{
					nodeCount.put(subNode, 1);
				}
			});
		});
		
		graph.forEach((node)->{
			
			if(!nodeCount.containsKey(node))
			{
				result.add(node);
				node.neighbors.forEach((subNode)->{
					nodeCount.put(subNode, nodeCount.get(subNode)-1);
					if(nodeCount.get(subNode)==0)
					{
						DFS(subNode, nodeCount, result);
					}
				});
			}
		});
		
		return result;
	}

	private static void DFS(DG node, Map<DG, Integer> nodeCount, List<DG> result) 
	{
		result.add(node);
		node.neighbors.forEach((subNode)->{
			nodeCount.put(subNode, nodeCount.get(subNode)-1);
			if(nodeCount.get(subNode)==0)
			{
				DFS(subNode, nodeCount, result);
			}
		});
	};
	
	public static void main(String[] args) 
    {
    	DG d_0 = new DG(0);
    	DG d_1 = new DG(1);
    	DG d_2 = new DG(2);
    	DG d_3 = new DG(3);
    	DG d_4 = new DG(4);
    	DG d_5 = new DG(5);
    	
    	DG d_6 = new DG(6);
    	
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
class DG
{
	int value;
	List<DG> neighbors;
	
	public DG(int value)
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