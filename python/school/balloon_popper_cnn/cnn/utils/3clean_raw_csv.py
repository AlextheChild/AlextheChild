rows = open("cnn/data/raw_csv_files/data0.csv").read().strip().split("\n")[1:]

for row in rows:
    row = row.split(",")

    path = row[0]
    box = [row[6], row[7], row[8], row[9]]

    box_vars = []
    for var in box:
        int_var = ""
        for char in var:
            if char in ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]:
                int_var += char
        box_vars.append(int_var)

    print(path, box_vars[0], box_vars[1], box_vars[2], box_vars[3], sep=", ")
