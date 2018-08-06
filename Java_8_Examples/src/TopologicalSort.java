import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class TopologicalSort {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<DirectedGraphNode> topologicalSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        if (graph == null || graph.size() == 0) {
            return graph;
        }
        
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        Map<DirectedGraphNode, Integer> map = new HashMap<DirectedGraphNode, Integer>();
        
        //calculate indegree of all the nodes
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1);
                }
            }
        }
        
        
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                result.add(node);
                for (DirectedGraphNode neighbor : node.neighbors) {
                    map.put(neighbor, map.get(neighbor) - 1);
                    if (map.get(neighbor) == 0) {
                        DFS(map, neighbor, result);
                    }
                }
            }
        }
        
        
        return result;
    }
    
    public void DFS(Map<DirectedGraphNode, Integer> map, DirectedGraphNode n, List<DirectedGraphNode> result) {
        result.add(n);
        for (DirectedGraphNode neighbor : n.neighbors) {
            map.put(neighbor, map.get(neighbor) - 1);
            if (map.get(neighbor) == 0) {
                DFS(map, neighbor, result);
            }
        }
    }
    
}
class DirectedGraphNode {
      int label;
      List<DirectedGraphNode> neighbors;
      
      DirectedGraphNode(int x) 
      { 
    	  label = x; 
    	  neighbors = new ArrayList<DirectedGraphNode>(); 
     }
};