from random import randint

def generate_test_case(N, K):
    test_case = []
    test_case.append(f"{N} {K}")
    for _ in range(K):
        a = randint(1, N)
        b = randint(1, N)
        while b == a:
            b = randint(1, N)
        l = randint(1, 10**6)
        test_case.append(f"{a} {b} {l}")
    # Добавляем номера городов А и В
    A = randint(1, N)
    B = randint(1, N)
    while B == A:
        B = randint(1, N)
    test_case.append(f"{A} {B}")
    return test_case

def write_to_input_file(filename, test_case):
    with open(filename, 'w') as f:
        for line in test_case:
            f.write(line + "\n")

def main():
    N = 6
    K = 4
    output_file_path = "input.txt"
    test_case = generate_test_case(N, K)
    write_to_input_file(output_file_path, test_case)

if __name__ == "__main__":
    main()
