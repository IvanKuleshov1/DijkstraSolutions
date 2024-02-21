package ivan.k.DijkstraTrainItineraryRealisation;

import java.io.*;
import java.util.*;

public class TaskD {
    static class Train {
        int from, to, departure, arrival;

        public Train(int from, int to, int departure, int arrival) {
            this.from = from;
            this.to = to;
            this.departure = departure;
            this.arrival = arrival;
        }
    }

    public static void main(String[] args) throws IOException {
        String inputFileName = "src/main/java/ivan/k/DijkstraTrainItineraryRealisation/input.txt";
        String outputFileName = "src/main/java/ivan/k/DijkstraTrainItineraryRealisation/output.txt";
//        String inputFileName = "input.txt";
//        String outputFileName = "output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));

        int N = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int d = Integer.parseInt(tokenizer.nextToken());
        int v = Integer.parseInt(tokenizer.nextToken());

        int R = Integer.parseInt(reader.readLine());

        List<Train> trains = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int departure = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int arrival = Integer.parseInt(tokenizer.nextToken());

            trains.add(new Train(from, to, departure, arrival));
        }

        int result = dijkstra(trains, d, v, N);

        System.out.println(result);
    }

    static int dijkstra(List<Train> trains, int source, int destination, int N) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(i -> distance[i]));

        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            int current = priorityQueue.poll();

            for (Train train : trains) {
                if (train.from == current && train.departure >= distance[current]) {
                    int newDistance = train.arrival;
                    if (newDistance < distance[train.to]) {
                        distance[train.to] = newDistance;
                        priorityQueue.add(train.to);
                    }
                }
            }
        }

        return distance[destination] == Integer.MAX_VALUE ? -1 : distance[destination];
    }
}

