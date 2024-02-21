from collections import deque
from heapq import heappop, heappush

class City:
    def __init__(self, preparation_time, speed):
        self.preparation_time = preparation_time
        self.speed = speed

def calculate_distance_matrix(n, graph):
    distance_matrix = [[float('inf')] * (n + 1) for _ in range(n + 1)]

    for start in range(1, n + 1):
        visited = [False] * (n + 1)
        distances = [float('inf')] * (n + 1)
        distances[start] = 0

        queue = deque([start])
        visited[start] = True

        while queue:
            current_node = queue.popleft()

            for neighbor, distance in graph[current_node]:
                if not visited[neighbor]:
                    visited[neighbor] = True
                    distances[neighbor] = distances[current_node] + distance
                    queue.append(neighbor)
                    distance_matrix[start][neighbor] = min(distances[neighbor], distance_matrix[start][neighbor])
                    distance_matrix[neighbor][start] = distance_matrix[start][neighbor]


    return distance_matrix



def calculate_time_matrix(n, distance_matrix, cities):
    time_matrix = [[float('inf')] * (n + 1) for _ in range(n + 1)]

    for i in range(1, n + 1):
        time_matrix[i][i] = 0
        for j in range(1, n + 1):
            if i != j and cities[i].speed != 0:
                time_matrix[i][j] = distance_matrix[i][j] / cities[i].speed + cities[i].preparation_time


    return time_matrix

def transpose_matrix(matrix):
    return list(map(list, zip(*matrix)))

def dijkstra(matrix, start):
    n = len(matrix)
    distances = [float('inf')] * n
    distances[start] = 0
    heap = [(0, start)]
    predecessors = [None] * n

    while heap:
        current_distance, current_node = heappop(heap)

        if current_distance > distances[current_node]:
            continue

        for neighbor, weight in enumerate(matrix[current_node]):
            if weight != float('inf'):
                distance = current_distance + weight
                if distance < distances[neighbor] - 1e-9:
                    distances[neighbor] = distance
                    predecessors[neighbor] = current_node
                    heappush(heap, (distance, neighbor))

    return distances, predecessors

def main():
    with open("input.txt", "r") as file:
        n = int(file.readline().strip())
        cities = [None] + [City(*map(int, file.readline().split())) for _ in range(n)]

        graph = [[] for _ in range(n + 1)]

        for i in range(n - 1):
            road_data = [int(val) for val in file.readline().split()]
            city1, city2, distance = road_data[:3]
            graph[city1].append((city2, distance))
            graph[city2].append((city1, distance))

    distance_matrix = calculate_distance_matrix(n, graph)

    time_matrix = calculate_time_matrix(n, distance_matrix, cities)

    transposed_time_matrix = transpose_matrix(time_matrix)

    distances_from_capital, predecessors = dijkstra(transposed_time_matrix, 1)

    max_time = max(distances_from_capital[1:])
    participant = distances_from_capital.index(max_time)

    route = []
    current_node = participant
    while current_node is not None:
        route.append(current_node)
        current_node = predecessors[current_node]

    route = route[::-1]

    with open("output.txt", "w") as file:
        file.write(f"{max_time:.10f}\n")
        file.write(" ".join(map(str, reversed(route))))

if __name__ == "__main__":
    main()
