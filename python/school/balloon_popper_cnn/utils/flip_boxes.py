rows = open("cnn/data/test/test.csv").read().strip().split("\n")
for row in rows:
    row = row.split(", ")
    print(
        "flipped_" + row[0],
        2880 - int(row[3]),
        row[2],
        2880 - int(row[1]),
        row[4],
        sep=", ",
    )
