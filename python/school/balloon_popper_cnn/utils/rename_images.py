import os
from PIL import Image

directory = os.listdir("cnn/data/train/images")

count = 0

for file in directory:
    image = Image.open("cnn/data/train/images/" + file)
    image.save("cnn/data/train/renamed_images/" + str(count) + ".png")

    count += 1
