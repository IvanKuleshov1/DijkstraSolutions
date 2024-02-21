from random import randint

def generate_test_case(N):
    test_case = []

    # Генерируем начальную вершину S и конечную вершину F
    S = randint(1, N)
    F = randint(1, N)
    while F == S:
        F = randint(1, N)

    test_case.append(f"{N} {S} {F}")

    # Генерируем матрицу смежности графа
    for _ in range(N):
        row = [randint(-1, 100) for _ in range(N)]
        test_case.append(" ".join(map(str, row)))

    return test_case

def write_to_input_file(filename, test_case):
    with open(filename, 'w') as f:
        for line in test_case:
            f.write(line + "\n")

def main():
    N = 3
    output_file_path = "input.txt"
    test_case = generate_test_case(N)
    write_to_input_file(output_file_path, test_case)

if __name__ == "__main__":
    main()
