from random import randint

def generate_test_case(N, d, v, R):
    test_case = [f"{N}", f"{d} {v}", f"{R}"]
    for _ in range(R):
        dep_village = randint(1, N)
        dep_time = randint(0, 10000)
        arr_village = randint(1, N)
        arr_time = randint(dep_time + 1, 10000)
        test_case.append(f"{dep_village} {dep_time} {arr_village} {arr_time}")
    return test_case

def write_to_input_file(filename, test_case):
    with open(filename, 'w') as f:
        for line in test_case:
            f.write(line + "\n")

def main():
    N = 3
    d = 1
    v = 3
    R = 4
    output_file_path = "input.txt"
    test_case = generate_test_case(N, d, v, R)
    write_to_input_file(output_file_path, test_case)

if __name__ == "__main__":
    main()
