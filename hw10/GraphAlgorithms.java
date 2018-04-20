import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException(
                    "starting vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException(
                    "graph cannot be null");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException(
                    "graph must contain start");
        }

        HashSet<Vertex<T>> visitedSet = new HashSet<>();
        Queue<Vertex<T>> q = new LinkedList<>();
        List<Vertex<T>> searchedNodes = new LinkedList<>();

        q.add(start);
        while (q.peek() != null) {
            Vertex<T> node = q.remove();
            if (!visitedSet.contains(node)) {
                visitedSet.add(node);
                List<Edge<T>> children = graph.getAdjList().get(node);
                if (children == null) {
                    throw new IllegalArgumentException(
                            "edge list cannot be null: " + node);
                }
                for (Edge<T> edge : children) {
                    q.add(edge.getV());
                }

                searchedNodes.add(node);
            }
        }

        return searchedNodes;
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException(
                    "starting vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException(
                    "graph cannot be null");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException(
                    "graph must contain start");
        }

        HashSet<Vertex<T>> visitedSet = new HashSet<>();
        Stack<Vertex<T>> q = new Stack<>();
        List<Vertex<T>> searchedNodes = new LinkedList<>();

        return dfs(start, graph, visitedSet, q, searchedNodes);
    }


    /**
     * Recursive helper method for depth first search.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @param visitedSet the set of nodes that have already been visited
     * @param q the stack that holds the next nodes to traverse
     * @param searchedNodes the set of nodes to return from the search
     * @return list of vertices in visited order
     */
    private static <T> List<Vertex<T>> dfs(Vertex<T> start,
                                           Graph<T> graph,
                                           HashSet<Vertex<T>> visitedSet,
                                           Stack<Vertex<T>> q,
                                            List<Vertex<T>> searchedNodes) {

        searchedNodes.add(start);
        visitedSet.add(start);

        List<Edge<T>> children = graph.getAdjList().get(start);
        if (children == null) {
            throw new IllegalArgumentException(
                    "edge list cannot be null: " + start);
        }
        for (Edge<T> edge : children) {
            Vertex<T> child = edge.getV();
            if (!visitedSet.contains(child)) {
                dfs(child, graph, visitedSet, q, searchedNodes);
                q.add(child);
            }
        }

        return searchedNodes;
    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException(
                    "starting vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException(
                    "graph cannot be null");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException(
                    "graph must contain start");
        }

        HashMap<Vertex<T>, Integer> distMap = new HashMap<Vertex<T>, Integer>();
        PriorityQueue<Edge<T>> q = new PriorityQueue<>();
        HashSet<Vertex<T>> visitedSet = new HashSet<>();


        for (Vertex<T> v : graph.getVertices()) {
            distMap.put(v, Integer.MAX_VALUE);
        }
        distMap.put(start, 0);

        q.addAll(graph.getAdjList().get(start));
        while (q.peek() != null) {
            Edge<T> edge = q.poll();
            Vertex<T> neighbor = edge.getV();
            if (!visitedSet.contains(neighbor)) {
                int ogDist = distMap.get(edge.getU());
                int dist = edge.getWeight();
                if (ogDist != Integer.MAX_VALUE) {
                    dist += ogDist;
                } else {
                    dist = Integer.MAX_VALUE;
                }
                if (dist < distMap.get(neighbor)) {
                    distMap.put(neighbor, dist);
                    q.addAll(graph.getAdjList().get(neighbor));
                }
            }
        }

        return distMap;
    }


    /**
     * Runs Prim's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface, as long as it's efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException(
                    "starting vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException(
                    "graph cannot be null");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException(
                    "graph must contain start");
        }

        HashSet<Vertex<T>> visitedSet = new HashSet<>();
        HashSet<Edge<T>> mst = new HashSet<>();
        PriorityQueue<Edge<T>> q = new PriorityQueue<>();

        q.addAll(graph.getAdjList().get(start));
        while (q.peek() != null) {
            Edge<T> edge = q.remove();
            if (!visitedSet.contains((edge.getV()))) {
                visitedSet.add(edge.getV());
                mst.add(edge);
                mst.add(new Edge<T>(edge.getV(), edge.getU(),
                        edge.getWeight()));
                q.addAll(graph.getAdjList().get(edge.getV()));
            }
        }

        if (!visitedSet.equals(graph.getVertices())) {
            return null;
        }

        return mst;
    }
}
