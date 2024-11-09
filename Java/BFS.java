import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BFS {
    private Map<Integer, List<Integer>> graph = new HashMap<>();

    
    public void readGraphFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int vertex = Integer.parseInt(parts[0]);
                List<Integer> neighbors = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    neighbors.add(Integer.parseInt(parts[i]));
                }
                graph.put(vertex, neighbors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void bfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            for (int neighbor : graph.getOrDefault(currentVertex, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BFS bfs = new BFS();
        bfs.readGraphFromFile("graph.txt");
        
        
        System.out.print("BFS traversal starting from vertex 0: ");
        bfs.bfs(0);
    }
}
