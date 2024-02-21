package ivan.k.DijkstraFastHeapq;

import java.io.*;
import java.util.*;

public class TaskC {
    static class Edge {
        long to, weight;

        public Edge(long to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        String inputFileName = "src/main/java/ivan/k/DijkstraFastHeapq/input.txt";
        String outputFileName = "src/main/java/ivan/k/DijkstraFastHeapq/output.txt";
//        String inputFileName = "input.txt";
//        String outputFileName = "output.txt";
//
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int l = Integer.parseInt(tokenizer.nextToken());

            graph.get(a).add(new Edge(b, l));
            graph.get(b).add(new Edge(a, l));
        }

        tokenizer = new StringTokenizer(reader.readLine());
        int source = Integer.parseInt(tokenizer.nextToken());
        int destination = Integer.parseInt(tokenizer.nextToken());

        long result = dijkstra(graph, source, destination, n);
        System.out.println(result == -1L ? -1 : result);
    }

    static long dijkstra(List<List<Edge>> graph, int source, int destination, int n) {
        long[] distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(e -> e.weight));
        priorityQueue.add(new Edge(source, 0));

        while (!priorityQueue.isEmpty()) {
            Edge current = priorityQueue.poll();

            if (current.to == destination) {
                return distance[destination];
            }

            for (Edge neighbor : graph.get((int) current.to)) {
                long newDistance = distance[(int) current.to] + neighbor.weight;

                if (newDistance < distance[(int) neighbor.to]) {
                    distance[(int) neighbor.to] = newDistance;
                    priorityQueue.add(new Edge(neighbor.to, newDistance));
                }
            }
        }

        return -1L;
    }
}

