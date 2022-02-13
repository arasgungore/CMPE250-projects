
import java.util.ArrayList;
import java.util.Stack;

/**
 * DirectedGraph class.
 * @author Aras Gungore
 *
 */
public class DirectedGraph {
	
	/**
	 * Number of vertices in the graph.
	 */
	private int V;
	/**
	 * An ArrayList of ArrayLists which holds a tail (initial vertex) as index and its outer arcs as value.
	 * The adjacency ArrayList holds the arcs as pairs in which head (terminal vertex) is key and weight of the arc is value.
	 */
	private ArrayList<ArrayList<Pair<Integer, Integer>>> adjacency_lists;
	
	
	
	/**
	 * Graph constructor with one parameter, namely V.
	 * @param V Number of vertices in the graph.
	 */
	public DirectedGraph(final int V) {
		this.V = V;
		adjacency_lists = new ArrayList<ArrayList<Pair<Integer, Integer>>>(V + 1);
		for(int v = 0; v <= V; ++v)
			adjacency_lists.add(new ArrayList<Pair<Integer, Integer>>());
	}
	
	/**
	 * Adds an arc (tail, head) with given tail, head, and weight to the graph.
	 * @param tail Tail (initial vertex) of the arc.
	 * @param head Head (terminal vertex) of the arc.
	 * @param weight Weight of the arc.
	 */
	public void addArc(final int tail, final int head, final int weight) {
		adjacency_lists.get(tail).add(new Pair<Integer, Integer>(head, weight));
	}
	
	/**
	 * A utility function to find the vertex with minimum distance value, from the set of vertices not yet included in the tree.
	 * @param distances An array where dist[i] holds the shortest distance from source to i.
	 * @param visited A boolean array where sptSet[i] will be TRUE if vertex i is included in shortest path tree 
	 * or shortest distance from source to i is finalized, FALSE otherwise.
	 * @return ID of the vertex with minimum distance value.
	 */
	private int minDistance(final int[] distances, final boolean[] visited) {
		int min = Integer.MAX_VALUE, min_vertex = 1;
	    for(int v = 2; v <= V; ++v) {
	    	final int dist_v = distances[v];
	    	if(!visited[v] && dist_v < min) {
	        	min = dist_v;
	        	min_vertex = v;
	        }
	    }
	    return min_vertex;
	}
	
	/**
	 * Finds the shortest path from the source to the destination along with total weight using Dijkstra’s shortest path algorithm.
	 * @param src Source (initial vertex) of the path.
	 * @param dest Destination (terminal vertex) of the path.
	 * @return A Pair in which key is a Stack storing the IDs of the vertices in path's order and value is the total weight of the path.
	 */
	public Pair<Stack<Integer>, Integer> getShortestPath(final int src, final int dest) {
		boolean[] visited = new boolean[V + 1];
		int[] parents = new int[V + 1];
		int[] distances = new int[V + 1];
	    
	    for(int v = 1; v <= V; ++v) {
	    	visited[v] = false;
	    	parents[v] = -1;
	    	distances[v] = Integer.MAX_VALUE;
	    }
	    distances[src] = 0;
	    
	    for(int i = 1; i < V; ++i) {
	    	final int u = minDistance(distances, visited);
	    	visited[u] = true;
	        final ArrayList<Pair<Integer, Integer>> adj_u = adjacency_lists.get(u);
	        
	        if(u == dest)
	        	break;
	        
	        for(final Pair<Integer, Integer> arc : adj_u) {
	        	final int v = arc.key;
	        	final int new_dist = distances[u] + arc.value;
	        	if(!visited[v] && distances[u] != Integer.MAX_VALUE && new_dist < distances[v]) {
	        		distances[v] = new_dist;
	            	parents[v] = u;
	        	}
	        }
	    }
	    
	    Stack<Integer> path = new Stack<Integer>();
	    int u = dest;
	    if(parents[u] != -1 || u == src) {
	    	while(u != -1) {
	    		path.push(u);
	    		u = parents[u];
	    	}
	    	return new Pair<Stack<Integer>, Integer>(path, distances[dest]);
	    }
	    path.push(-1);
	    return new Pair<Stack<Integer>, Integer>(path, -1);
	}
	
	/**
	 * Returns the weight of the MST (minimum spanning tree) of the graph found using Prim’s minimum spanning tree algorithm.
	 * @return The weight of the minimum spanning tree of the graph.
	 */
	public int getMSTweight() {
		boolean[] visited = new boolean[V + 1];
		int[] parents = new int[V + 1];
		int[] weights = new int[V + 1];
	    
	    for(int v = 1; v <= V; ++v) {
	    	visited[v] = false;
	    	parents[v] = -1;
	    	weights[v] = Integer.MAX_VALUE;
	    }
	    weights[1] = 0;
	    
	    for(int i = 1; i < V; ++i) {
	    	final int u = minDistance(weights, visited);
	    	visited[u] = true;
	    	final ArrayList<Pair<Integer, Integer>> adj_u = adjacency_lists.get(u);
	    	
	        for(final Pair<Integer, Integer> arc : adj_u) {
	        	final int v = arc.key;
	        	final int weight = arc.value;
	        	if(!visited[v] && weight < weights[v]) {
	        		weights[v] = weight;
	            	parents[v] = u;
	            }
	        }
	    }
	    
	    int total_weight = 0;
	    for(int v = 1; v <= V; ++v) {
	    	final int weight = weights[v];
	    	if(weight == Integer.MAX_VALUE)
	    		return -1;
	    	total_weight += weight;
	    }
		return total_weight;
	}
	
	/**
	 * Getter method for the field "V".
	 * @return Number of vertices in the graph.
	 */
	public int getV() {
		return V;
	}
}
