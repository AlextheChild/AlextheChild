train_rows = open("cnn/data/train/train.csv").read().strip().split("\n")
test_rows = open("cnn/data/test/test.csv").read().strip().split("\n")

rows = train_rows

for row in rows:
    row = row.split(", ")

    path = row[0]
