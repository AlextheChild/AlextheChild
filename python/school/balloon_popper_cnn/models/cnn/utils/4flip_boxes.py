rows = open("models/cnn/data/data.csv").read().strip().split("\n")

for row in rows:
    row = row.split(", ")

    path = row[0]
    x = int(row[1])
    y = int(row[2])
    w = int(row[3])
    h = int(row[4])

    print(
        "flipped_" + row[0],
        2880 - x - w,
        y,
        w,
        h,
        sep=", ",
    )
