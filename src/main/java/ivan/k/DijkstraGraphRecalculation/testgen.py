from random import randint

def generate_test_case(N):
    test_case = [str(N)]

    # Генерируем время подготовки саней и скорость ямщиков для каждого города
    for _ in range(N):
        T = randint(0, 100)
        V = randint(1, 100)
        test_case.append(f"{T} {V}")

    # Генерируем описания дорог
    for i in range(1, N):
        A = i
        B = randint(1, N)
        while B == A:
            B = randint(1, N)
        S = randint(1, 10000)
        test_case.append(f"{A} {B} {S}")

    return test_case

def write_to_input_file(filename, test_case):
    with open(filename, 'w') as f:
        for line in test_case:
            f.write(line + "\n")

def main():
    N = 4
    output_file_path = "input.txt"
    test_case = generate_test_case(N)
    write_to_input_file(output_file_path, test_case)

if __name__ == "__main__":
    main()
