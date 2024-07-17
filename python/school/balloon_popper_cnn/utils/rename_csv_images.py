import os

directory = os.listdir("cnn/data/test/images")
csv_lines = open("cnn/data/test/test.csv").read().strip().split("\n")
new_csv = open("cnn/0data/test/test.csv", "w")

for line in csv_lines:
    changed_line = line

    for i, j in enumerate(directory):
        if j in line:
            changed_line = line.replace(j, str(i) + ".png") + "\n"
            break

    new_csv.write(changed_line)
