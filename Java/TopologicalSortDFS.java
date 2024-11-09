import java.util.*;

public class TopologicalSortDFS {
    private int vertices;
    private LinkedList<Integer>[] adjList;

    public TopologicalSortDFS(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjList[source].add(destination);
    }

    public List<Integer> topologicalSort() {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }
        List<Integer> topologicalOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topologicalOrder.add(stack.pop());
        }
        return topologicalOrder;
    }

    private void dfs(int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack);
            }
        }
        stack.push(node);
    }

    public static void main(String[] args) {
        TopologicalSortDFS graph = new TopologicalSortDFS(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        List<Integer> topologicalOrder = graph.topologicalSort();
        System.out.println("Topological Order: " + topologicalOrder);
    }
}
    
}
