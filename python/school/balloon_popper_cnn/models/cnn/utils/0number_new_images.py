import os

directory = os.listdir("cnn/data/images")

# os sorts weird so find the greatest number
max_num = 0
for file in directory:
    num = int(file[:-4])
    max_num = num if num > max_num else max_num

max_num += 1


sender_directory = os.listdir("cnn/data/new_images")

for file in sender_directory:
    os.rename(
        "cnn/data/new_images/" + file, "cnn/data/new_images/" + str(max_num) + ".png"
    )

    max_num += 1
