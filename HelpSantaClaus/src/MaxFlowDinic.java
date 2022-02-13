
import java.util.ArrayList;

/**
 * MaxFlowDinic class.
 * @author Aras Gungore
 *
 */
public class MaxFlowDinic {
	
	/**
	 * Number of vertices in the graph.
	 */
	private int V;
	/**
	 * An ArrayList of ArrayLists which holds a tail (initial vertex) as index and its outer arcs as value.
	 */
	private ArrayList<ArrayList<Arc>> graph;
	
	/**
	 * Nested static Arc class.
	 * @author Aras Gungore
	 *
	 */
	static class Arc {
		
		/**
		 * Head (terminal vertex) of the arc.
		 */
	    public int head;
	    /**
	     * Index of the corresponding reverse arc to find the reverse arc quickly.
	     */
	    public int rev;
	    /**
	     * Capacity of the arc.
	     */
	    public int cap;
	    /**
	     * Flow of the arc.
	     */
	    public int flow;
	    
	    /**
	     * Arc constructor with 3 parameters; namely head, rev, and cap.
	     * @param head Head (terminal vertex) of the arc.
	     * @param rev Index of the corresponding reverse arc.
	     * @param cap Capacity of the arc.
	     */
	    public Arc(final int head, final int rev, final int cap) {
	    	this.head = head;
	      	this.rev = rev;
	      	this.cap = cap;
	    }
	}
	
	
	
	/**
	 * MaxFlowDinic constructor with one parameter, namely V.
	 * @param V Number of vertices in the graph.
	 */
	public MaxFlowDinic(final int V) {
		this.V = V;
	    graph = new ArrayList<ArrayList<Arc>>(V);
	    for(int i = 0; i < V; ++i)
	    	graph.add(new ArrayList<Arc>());
	}
	
	/**
	 * Adds an arc (tail, head) with given tail, head, and capacity along with its corresponding reverse arc to the graph.
	 * @param tail Tail (initial vertex) of the arc.
	 * @param head Head (terminal vertex) of the arc.
	 * @param cap Capacity of the arc.
	 */
	public void addArc(final int tail, final int head, final int cap) {
		ArrayList<Arc> adj_tail = graph.get(tail);
		ArrayList<Arc> adj_head = graph.get(head);
		adj_tail.add(new Arc(head, adj_head.size(), cap));
		adj_head.add(new Arc(tail, adj_tail.size() - 1, 0));
	}
	
	/**
	 * Does a Breadth First Search and finds the distance from source to destination.
	 * @param src Source vertex.
	 * @param dest Destination vertex.
	 * @param distances An array where distances[i] holds the shortest distance from source to 'i'.
	 * @return TRUE if the distance to destination vertex is non-negative, FALSE otherwise.
	 */
	private boolean BFS(final int src, final int dest, int[] distances) {
		for(int v = 0; v < V; ++v)
			distances[v] = -1;		// Fill the distances array with "-1"s.
		distances[src] = 0;
	    int[] queue = new int[V];	// Array type queue.
	    int queue_size = 0;
	    queue[queue_size++] = src;
	    for(int i = 0; i < queue_size; ++i) {
	    	final int u = queue[i];
	    	final ArrayList<Arc> adj_u = graph.get(u);
	    	for(final Arc arc : adj_u)
	    		if(distances[arc.head] < 0 && arc.flow < arc.cap) {
	    			distances[arc.head] = distances[u] + 1;
	    			queue[queue_size++] = arc.head;
	    		}
	    }
	    return distances[dest] >= 0;
	}
	
	/**
	 * Does a Depth First Search and recursively finds the path flow to the destination vertex.
	 * @param start An array which keeps track of next edge to be explored where start[i] stores number of edges explored from 'i'.
	 * @param distances An array where distances[i] holds the shortest distance from source to 'i'.
	 * @param dest Destination vertex.
	 * @param u Current vertex.
	 * @param flow Current flow in the vertex 'u'.
	 * @return Path flow from 'u' to destination vertex.
	 */
	private int DFS(int[] start, int[] distances, final int dest, final int u, final int flow) {
		if(u == dest)
			return flow;
		final int graph_u_size = graph.get(u).size();
		for(; start[u] < graph_u_size; ++start[u]) {
			Arc arc = graph.get(u).get(start[u]);
			if(distances[arc.head] == distances[u] + 1 && arc.flow < arc.cap) {
				final int path_flow = DFS(start, distances, dest, arc.head, Math.min(flow, arc.cap - arc.flow));
				if(path_flow > 0) {
					arc.flow += path_flow;
					graph.get(arc.head).get(arc.rev).flow -= path_flow;
					return path_flow;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Returns the maximum flow from given source to given destination in the graph using Dinic's algorithm.
	 * @param src Source vertex.
	 * @param dest Destination vertex.
	 * @return Maximum flow from source 'src' to destination 'dest'.
	 */
	public int getMaximumFlow(final int src, final int dest) {
		int max_flow = 0;
		int[] distances = new int[V];
		while(BFS(src, dest, distances)) {
			int[] start = new int[V];		// Initialized as an array of zeros.
			while(true) {
				final int df = DFS(start, distances, dest, src, Integer.MAX_VALUE);
				if(df == 0)
					break;
				max_flow += df;
			}
		}
		return max_flow;
	}
}
