import random

def generate_test_case(N):
    # Генерируем случайные значения для матрицы смежности
    adjacency_matrix = [[-1 if i == j else random.randint(0, 100) for j in range(N)] for i in range(N)]
    # Генерируем случайные начальную и конечную вершины
    start_vertex = random.randint(1, N)
    end_vertex = random.randint(1, N)
    while end_vertex == start_vertex:
        end_vertex = random.randint(1, N)
    return start_vertex, end_vertex, adjacency_matrix

def write_to_input_file(filename, start_vertex, end_vertex, adjacency_matrix):
    with open(filename, 'w') as f:
        # Записываем начальную вершину, конечную вершину и их количество
        f.write(f"{len(adjacency_matrix)} {start_vertex} {end_vertex}\n")
        # Записываем матрицу смежности
        for row in adjacency_matrix:
            f.write(" ".join(map(str, row)) + "\n")

def main():
    # Количество вершин в графе
    N = 7
    # Имя файла для записи
    filename = "input.txt"
    # Генерируем тестовый случай
    start_vertex, end_vertex, adjacency_matrix = generate_test_case(N)
    # Записываем в файл
    write_to_input_file(filename, start_vertex, end_vertex, adjacency_matrix)

if __name__ == "__main__":
    main()
