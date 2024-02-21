package ivan.k.DijkstraProofOfConcept;
import java.io.*;
import java.util.*;

public class DijkstraWithMandatoryVertices {

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {

        String inputFileName = "src/main/java/ivan/k/DijkstraProofOfConcept/input.txt";
        String outputFileName = "src/main/java/ivan/k/DijkstraProofOfConcept/output.txt";
//        String inputFileName = "input.txt";
//        String outputFileName = "output.txt";

        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        PrintWriter writer = new PrintWriter(new FileWriter(outputFileName));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int S = Integer.parseInt(tokenizer.nextToken());
        int F = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        List<Integer> mandatoryVertices = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            mandatoryVertices.add(Integer.parseInt(tokenizer.nextToken()));
        }

        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        List<Integer> shortestPath = dijkstraWithMandatoryVertices(graph, S, F, mandatoryVertices);
        if (shortestPath == null) {
            System.out.println("-1");
        } else {
            System.out.println(shortestPath.size() - 1);
            for (int vertex : shortestPath) {
                System.out.print(vertex + " ");
            }
        }
    }

    static List<Integer> dijkstraWithMandatoryVertices(int[][] graph, int source, int destination, List<Integer> mandatoryVertices) {
        int N = graph.length - 1;
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        pq.offer(new int[]{source, 0});

        boolean[] visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int dist = current[1];

            if (visited[node]) continue;
            visited[node] = true;

            if (node == destination) break;

            for (int i = 1; i <= N; i++) {
                if (graph[node][i] != -1 && !visited[i]) {
                    int newDist = dist + graph[node][i];
                    if (newDist < distance[i]) {
                        distance[i] = newDist;
                        pq.offer(new int[]{i, newDist});
                    }
                }
            }
        }

        if (distance[destination] == Integer.MAX_VALUE) return null;

        List<Integer> shortestPath = new ArrayList<>();
        shortestPath.add(destination);

        int currentNode = destination;
        while (currentNode != source) {
            for (int i = 1; i <= N; i++) {
                if (graph[i][currentNode] != -1 && distance[currentNode] == distance[i] + graph[i][currentNode]) {
                    shortestPath.add(i);
                    currentNode = i;
                    break;
                }
            }
        }

        Collections.reverse(shortestPath);
        return shortestPath;
    }
}