package ivan.k.Dijkstra;

import java.io.*;
import java.util.StringTokenizer;

public class TaskA {
    public static void main(String[] args) throws IOException {
        String inputFileName = "src/main/java/ivan/k/Dijkstra/input.txt";
        String outputFileName = "src/main/java/ivan/k/Dijkstra/output.txt";
//        String inputFileName = "input.txt";
//        String outputFileName = "output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int s = Integer.parseInt(tokenizer.nextToken()) - 1;
        int f = Integer.parseInt(tokenizer.nextToken()) - 1;

        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) graph[i][j] = Integer.parseInt(tokenizer.nextToken());

        }

        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) distance[i] = Integer.MAX_VALUE;


        distance[s] = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (minIndex == -1 || distance[j] < distance[minIndex])) {
                    minIndex = j;
                }
            }

            visited[minIndex] = true;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && graph[minIndex][j] != -1 && distance[minIndex] != Integer.MAX_VALUE
                        && distance[minIndex] + graph[minIndex][j] < distance[j]) {
                    distance[j] = distance[minIndex] + graph[minIndex][j];
                }
            }
        }

        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName)));
        if (distance[f] == Integer.MAX_VALUE) writer.write("-1");
        else writer.write(Integer.toString(distance[f]));

        writer.close();
    }
}


